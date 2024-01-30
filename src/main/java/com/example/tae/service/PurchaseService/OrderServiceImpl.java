package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.dto.ImageDTO;
import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
@PropertySource("classpath:application.properties")
public class OrderServiceImpl implements OrderService {
//    @Value("${database.email}")
//    private String email;
//    @Value("${database.pw}")
//    private String password;

    private final OrderRepository orderRepository;
    private final ProcurementPlanRepository procurementPlanRepository;
    private final ExistenceRepository existenceRepository;
    private final ContractRepository contractRepository;
    private final ProductInformationRegistrationRepository productInformationRegistrationRepository;


    /*발주서 발행*/
    @Override
    public void orderRegister(int procurementPlanCode) {
        Optional<ProcurementPlan> procurementPlanOp = procurementPlanRepository.findById(procurementPlanCode);
        ProcurementPlan procurementPlan = procurementPlanOp.get();
        Purchase purchase = Purchase.builder().build();

        orderRepository.save(purchase);
        Date order_date = Timestamp.valueOf(purchase.getModDate());
        ProcurementPlan updateProcurementPlan = ProcurementPlan.builder()
                .procurementplan_code(procurementPlan.getProcurementplan_code())
                .projectPlan(procurementPlan.getProjectPlan())
                .order_date(order_date)
                .order_state("발주중")
                .contract(procurementPlan.getContract())
                .project(procurementPlan.getProject())
                .procurementplan_code(procurementPlanCode)
                .SupportProductAmount(procurementPlan.getSupportProductAmount())
                .purchase(purchase)
                .SupportProductAmount(procurementPlan.getSupportProductAmount())
                .build();
        procurementPlanRepository.save(updateProcurementPlan);
    }

    @Override
    public void cancelOrder(int procurementPlanCode) {
        Optional<ProcurementPlan> procurementPlan = procurementPlanRepository.findById(procurementPlanCode);

        ProcurementPlan orderProcurementPlan = procurementPlan.get();
        ProcurementPlan cancelToPurchase = ProcurementPlan.builder()
                .procurementplan_code(orderProcurementPlan.getProcurementplan_code())
                .contract(orderProcurementPlan.getContract())
                .order_date(orderProcurementPlan.getOrder_date())
                .project(orderProcurementPlan.getProject())
                .SupportProductAmount(orderProcurementPlan.getSupportProductAmount())
                .projectPlan(orderProcurementPlan.getProjectPlan())
                .build();
        procurementPlanRepository.save(cancelToPurchase);
    }


    /*발주서 목록 가져오기*/
    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> oList = new ArrayList<>();

        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProcurementplan_orderStateNotNull();

        procurementPlanList.forEach(info -> {
            ProductInformationRegistration productInformationRegistration = info.getContract().getProductInformationRegistration();
            OrderDTO orderDTO = OrderDTO.builder()
                    .productName(productInformationRegistration.getProduct_name())
                    .productCode(productInformationRegistration.getProduct_code())
                    .departName(info.getContract().getCompany().getDepartName())
                    .procurementPlanCode(info.getProcurementplan_code())
                    .orderState(info.getOrder_state())
                    .orderDate(info.getOrder_date())
                    .build();
            orderDTO.setProgressInspectionStatus();
            oList.add(orderDTO);
        });
        return oList;
    }


    /*발주서 목록 가져오기*/
    @Override
    public List<OrderDTO> getOrderInspectData(int productCode, int procurementPlanCode) {
        Optional<ProductInformationRegistration> productInformationRegistration = productInformationRegistrationRepository.findById(productCode);
        ProductInformationRegistration productInformation = productInformationRegistration.get();
        List<ProcurementPlan> procurementPlanList = contractRepository.findByproductInformationId(productCode);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        procurementPlanList.forEach(procurementPlan -> {
            if (procurementPlan.getProcurementplan_code() == procurementPlanCode) {
                Optional<Existence> existenceNum = Optional.of(existenceRepository.findByProductCode(productInformation).orElseGet(
                        () -> {
                            Existence existence1 = Existence.builder()
                                    .releaseCNT(0)
                                    .productCode(productInformation)
                                    .build();
                            existenceRepository.save(existence1);
                            return existence1;
                        }
                ));
                OrderDTO orderDTO = OrderDTO.builder()
                        .productName(productInformation.getProduct_name())
                        .productCode(productInformation.getProduct_code())
                        .num(procurementPlan.getSupportProductAmount())
                        .existence(existenceNum.get().getReleaseCNT())
                        .LT(procurementPlan.getContract().getLead_time())
                        .projectOutPutDate(procurementPlan.getProjectPlan().getProjectOutputDate())
                        .orderDate(procurementPlan.getOrder_date())
                        .width(productInformation.getWidth())
                        .length(productInformation.getLength())
                        .height(productInformation.getHeight())
                        .text(productInformation.getTexture())
                        .procurementPlanCode(procurementPlan.getProcurementplan_code())
                        .orderState(procurementPlan.getOrder_state())
                        .departName(procurementPlan.getContract().getCompany().getDepartName())
                        .build();
                orderDTOList.add(orderDTO);
            }
        });
        return orderDTOList;
    }


    /*발주 안 된 조달계획 정보 전부 가져오기*/
    public List<OrderDTO> oListSend() {
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProcurementplan_orderStateNull();
        List<OrderDTO> oList = new ArrayList<>();
        for (ProcurementPlan procurementPlan : procurementPlanList) {
            Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(procurementPlan.getContract().getProductInformationRegistration()).orElseGet(
                    Existence.builder()
                            .productCode(procurementPlan.getContract().getProductInformationRegistration())
                            .releaseCNT(0)::build
            ));
            OrderDTO orderDTO = OrderDTO.builder()
                    .departName(procurementPlan.getContract().getCompany().getDepartName())
                    .agent(procurementPlan.getContract().getCompany().getBusinessName())
                    .email(procurementPlan.getContract().getCompany().getBusinessEmail())
                    .fax(procurementPlan.getContract().getCompany().getFax())
                    .LT(procurementPlan.getContract().getLead_time())
                    .procurementPlanCode(procurementPlan.getProcurementplan_code())
                    .num(procurementPlan.getSupportProductAmount()) // 조달 수량 받아 오기
                    .tel(procurementPlan.getContract().getCompany().getBusinessTel())
                    .orderDate(procurementPlan.getOrder_date())
                    .projectOutPutDate(procurementPlan.getProjectPlan().getProjectOutputDate())
                    .productCode(procurementPlan.getContract().getProductInformationRegistration().getProduct_code())
                    .productName(procurementPlan.getContract().getProductInformationRegistration().getProduct_name())
                    .existence(existence.get().getReleaseCNT())
                    .build();
            oList.add(orderDTO);
        }
        return oList;
    }

    @Override
    public List<OrderDTO> getOrderListWithDate(LocalDateTime date1, LocalDateTime date2) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<Purchase> orderList = orderRepository.findOrderListWithDate(date1,  date2);
        orderList.forEach( order -> {
            Date order_date = Timestamp.valueOf(order.getModDate());
            ProcurementPlan procurementPlan = procurementPlanRepository.findByPurchase_OrderCode(order.getOrderCode());
            OrderDTO orderDTO = OrderDTO.builder()
                    .productName(procurementPlan.getContract().getProductInformationRegistration().getProduct_name())
                    .productCode(procurementPlan.getContract().getProductInformationRegistration().getProduct_code())
                    .procurementPlanCode(procurementPlan.getProcurementplan_code())
                    .orderDate(order_date)
                    .departName(procurementPlan.getContract().getCompany().getDepartName())
                    .orderState(procurementPlan.getOrder_state())
                    .build();
                    orderDTOList.add(orderDTO);
        });
        return orderDTOList;
    }

    @Override
    public OrderDTO getOrderPopup(int procurementPlanCode) {
        Optional<ProcurementPlan> pr = Optional.of(procurementPlanRepository.findById(procurementPlanCode).orElseThrow(
                ()->new IllegalArgumentException("조달 파업청 열기 실패 : 존재하지 않는 조달계획 아이디 ")
        ));
        ProcurementPlan procurementPlan = pr.get();

        Optional<Existence> existence = Optional.of(
                existenceRepository.findByProductCode(procurementPlan.getContract().getProductInformationRegistration()).orElseGet(
                        () -> {
                            Existence ex = Existence.builder()
                                    .productCode(procurementPlan.getContract().getProductInformationRegistration())
                                    .releaseCNT(0)
                                    .build();
                            existenceRepository.save(ex);
                            return ex;
                        }
                )
        );

        return OrderDTO.builder()
                .productName(procurementPlan.getContract().getProductInformationRegistration().getProduct_name())
                .businessNum(procurementPlan.getContract().getCompany().getBusinessNumber())
                .departName(procurementPlan.getContract().getCompany().getDepartName())
                .businessName(procurementPlan.getContract().getCompany().getBusinessName())
                .registerOrderDate(procurementPlan.getPurchase().getRegDate())
                .productCode(procurementPlan.getContract().getProductInformationRegistration().getProduct_code())
                .supportProductAmount(procurementPlan.getSupportProductAmount())
                .existence(existence.get().getReleaseCNT())
                .projectOutPutDate(procurementPlan.getProjectPlan().getProjectOutputDate())
                .LT(procurementPlan.getContract().getLead_time())
                .orderDate(procurementPlan.getOrder_date())
                .email(procurementPlan.getContract().getCompany().getBusinessEmail())
                .tel(procurementPlan.getContract().getCompany().getBusinessTel())
                .fax(procurementPlan.getContract().getCompany().getFax())
                .build();
    }


    public void orderUpload(ImageDTO imageDTO) {

        System.out.println("포스트요청 들어옴");

        String folderPath="../../Desktop/이미지 테스트/발주서/"; //폴더주소

        try {
            createFolderAndDownloadBase64Image(folderPath,imageDTO);
            System.out.println("이미지가 무사히 저장됨");
        } catch (IOException e) {
            e.printStackTrace();
        }////////////////URL을 이미지로 변환

        final String user_email= ""; // 구글 이메일
        final String user_pw = ""; //구글 앱 비밀번호
        final String smtp_host = "smtp.gmail.com"; //구글에서 제공하는 smtp
        final int smtp_port = 465;  // TLS : 587, SSL : 465

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", smtp_host);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user_email, user_pw);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            // 보내는 이메일 주소
            message.setFrom(new InternetAddress(user_email));
            // 받는 이메일 주소
            message.setRecipients( Message.RecipientType.TO,   InternetAddress.parse("b1gdd@naver.com")  );
            // 이메일 제목
            message.setSubject("TAE 발주서 이메일 기능 확인.");
            Multipart multipart = new MimeMultipart();

            // 메일 내용 부분
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("하단의 첨부파일에 거래명세서가 보이면 잘 전송이 된것입니다");
            multipart.addBodyPart(textPart);

            // 이미지 파일 경로
            String imageurl = folderPath+imageDTO.ordercode+"발주서.jpg";

            //이미지 첨부 부분
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(imageurl);//
            multipart.addBodyPart(imagePart);

            message.setContent(multipart);

            // 발송
            Transport.send(message);
            System.out.println("발송완료됨");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    /**
     * base64 이미지 URL을 바이너리로 디코딩 한고 폴더가 미존재시 폴더를 생성하고 이미지파일을 저장하는 함수입니다
     * @param folderPath 저장할파일 위치 넣어주세요
     * @param dto ImageDTO객체
     */
    private static void createFolderAndDownloadBase64Image(String folderPath , ImageDTO dto) throws IOException {
        // Base64 문자열에서 이미지 데이터 부분 추출
        String imageData = dto.imageDataURL.split(",")[1];
        // Base64 디코딩
        byte[] imageBytes = Base64.getDecoder().decode(imageData);
        // 지정한 위치에 폴더 미존재시 폴더생성
        Path path = Paths.get(folderPath);
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        //이미지파일의 저장폴더위치와 이름을 정하고
        String destinationPath = folderPath+dto.ordercode+"발주서.jpg";

        //폴더에 파일저장
        Path destination = Path.of(destinationPath);
        Files.write(destination, imageBytes);
    }

}
