package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.TradingStatement.TradingStatementModalDTO;
import com.example.tae.entity.dto.ImageDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

@Service
@AllArgsConstructor

public class TradingStatementServiceImpl implements TradingStatementService{


    ReceivingProcessingRepository receivingProcessingRepository;
    @Override
    @Transactional
//----------------------------------------------------------------버그가 난 부분 시작
    public List<TradingStatementModalDTO> showListOrderByorderCode() {

        List<Object[]> groupByOrderCodeList=receivingProcessingRepository.groupByOrderCode(); // 발주서 코드 및 그에해당하는 갯수 전부 불러오기 String 과 Long
        List<TradingStatementModalDTO> retrunList = new ArrayList<>(); // 리턴용 리스트

        System.out.println("발주서 코드의 행은 5행"+groupByOrderCodeList.size());

        List<String> uuidlist = new ArrayList<>();

        for (int i = 0; i < groupByOrderCodeList.size(); i++) { //리스트 크기가 5 니까 5번 반복
            System.out.println(i + "반복횟수==================================");

            System.out.println("UUID는-*******************************" + (String) groupByOrderCodeList.get(i)[0]);
            List<ProcurementPlan> procurementPlanListend = receivingProcessingRepository.listByOrderCodeend((String) groupByOrderCodeList.get(i)[0]); //발주서 코드로 발주서  품목의 상태가 마감 인 품목찾음
            System.out.println("마감   --------------------발주마감 된것만 불러온------ 리스트 크기?" + procurementPlanListend.size());

            if ((long) groupByOrderCodeList.get(i)[1] == (long) procurementPlanListend.size()) { //조달계획의 품목 사이즈와 각  발주서코드해당하는 갯수 를 비교 같야야 출력
//              여기서 알수있는정보는 발주서 코드가얼마나 몇개 있는지    ::: 그발주서 코드에 해당하는 정보와 발주서 그게 몇게 있는지(발주서코드는 상단에서 바뀜)
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++" + i + "번쨰 인덱스UUID는" + groupByOrderCodeList.get(i)[0] + "    이UUID를 가지고있는 품목의 갯수    " + groupByOrderCodeList.get(i)[1]);
                System.out.println("***************************UUID가 " + (String) groupByOrderCodeList.get(i)[0] + "이거인 목록중에 리스트의 크기는" + procurementPlanListend.size());
                System.out.println("통과 UUID는" + procurementPlanListend.get(0).getPurchase().getOrdercode());
                uuidlist.add(procurementPlanListend.get(0).getPurchase().getOrdercode());
            } // 같아야지 품목 DTO가 나오고 나온거를  리스트로 만들어줌
            else {
//                System.out.println("불통과한  UUID는" + procurementPlanListend.get(i).getPurchase().getOrdercode());
            }

        }
        uuidlist.forEach(x-> System.out.println("//////////////////////////////////////최종으로 들어있는 리스트//////////////////////////////////"+x));
        //거래명세서가 될 발주서 코드만 있음
        for(int aa=0; aa<uuidlist.size(); aa++){ //발주서 코드 추출? uuidlist.get(aa)-> 발주서 코드
            List<ProcurementPlan> list =receivingProcessingRepository.listByOrderCodeend(uuidlist.get(aa)); //해당 발주서 코드 해당하는 모든 품목을 리스트로 받아옴
            ProcurementPlan pp = list.get(0);// 그 품목리스트중 맨 첫번째 인덱스에서만 출력-> 품목에대한 조달꼐획 가져옴
            TradingStatementModalDTO addListDTO = TradingStatementModalDTO.builder()
                    .orderCode(pp.getPurchase().getOrdercode())
                    .businessNumber(pp.getContract().getCompany().getBusinessNumber())
                    .departName(pp.getContract().getCompany().getDepartName())
                    .businessName(pp.getContract().getCompany().getBusinessName())
                    .businessEmail(pp.getContract().getCompany().getBusinessEmail())
                    .fax(pp.getContract().getCompany().getFax())
                    .businessTel(pp.getContract().getCompany().getBusinessTel())
                    .build(); //리스트에 넣어줄 품목에대한 회사 정보를 너어줄 객체생성
            retrunList.add(addListDTO);
        }



        return retrunList;
    }
//-----------------------------------------------------------버그가 난부분------------수정완료 ---------------------------------------------------------------------------------------------------
    @Override
    @Transactional
    public List<TradingStatementModalDTO> Listppuuid(String uuid) {
        List<TradingStatementModalDTO> listDto = new ArrayList<>();
        List<ProcurementPlan> listpp=receivingProcessingRepository.listByOrderCodeend(uuid);

        for(int i=0; i<listpp.size(); i++){
            ReceivingProcessing  receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(listpp.get(i).getProcurementplan_code());
            TradingStatementModalDTO modalDTO = TradingStatementModalDTO.builder()
                    .prouctName(listpp.get(i).getContract().getProductInformationRegistration().getProduct_name())
                    .price(listpp.get(i).getContract().getProduct_price())
                    .count(receivingProcessing.getStore()) //입고된수량
                    .pc(listpp.get(i).getContract().getProduct_price()*receivingProcessing.getStore())
                    .businessNumber(listpp.get(i).getContract().getCompany().getBusinessNumber())
                    .departName(listpp.get(i).getContract().getCompany().getDepartName())
                    .businessName(listpp.get(i).getContract().getCompany().getBusinessName())
                    .businessEmail(listpp.get(i).getContract().getCompany().getBusinessEmail())
                    .fax(listpp.get(i).getContract().getCompany().getFax())
                    .businessTel(listpp.get(i).getContract().getCompany().getBusinessTel())
                    .build();
            listDto.add(modalDTO);

        }
        return listDto;
    }


    public void imageUpload(@RequestBody ImageDTO imageDTO) {
        System.out.println("포스트요청 들어옴");

        String folderPath="../../Desktop/이미지 테스트/거래명세서4/"; //폴더주소

        try {
            createFolderAndDownloadBase64Image(folderPath,imageDTO);
            System.out.println("이미지가 무사히 저장됨");
        } catch (IOException e) {
            e.printStackTrace();
        }////////////////URL을 이미지로 변환

        final String user_email= "ghostjaewoongp@gmail.com"; // 구글 이메일
        final String user_pw = "rxks poyq biif qcfq"; //구글 앱 비밀번호
        final String smtp_host = "smtp.gmail.com"; //구글에서 제공하는 smtp
        final int smtp_port = 465;  // TLS : 587, SSL : 465

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", smtp_host);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user_email, user_pw);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            // 보내는 이메일 주소
            message.setFrom(new InternetAddress(user_email));
            // 받는 이메일 주소
            message.setRecipients( Message.RecipientType.TO,   InternetAddress.parse("youngjjag28@gmail.com")  );
            // 이메일 제목
            message.setSubject("TAE 202401-01거래명세서 메일입니다.");
            Multipart multipart = new MimeMultipart();

            // 메일 내용 부분
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("하단의 첨부파일에 거래명세서가 보이면 잘 전송이 된것입니다");
            multipart.addBodyPart(textPart);

            // 이미지 파일 경로
            String imageurl = folderPath+imageDTO.ordercode+"거래명세서.jpg";

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
        String destinationPath = folderPath+dto.ordercode+"거래명세서.jpg";

        //폴더에 파일저장
        Path destination = Path.of(destinationPath);
        Files.write(destination, imageBytes);
    }
}
