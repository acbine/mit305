package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationJoinContractDTO;
import com.example.tae.entity.dto.ImageDTO;
import com.example.tae.repository.RegistrationRepository.ContractPageRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import com.example.tae.service.RegistrationService.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Controller
public class ContractController {

    @Autowired
    ContractServiceImpl contractService;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ProductInformationRegistrationRepository productInformationRegistrationRepository;

    @GetMapping("ContractRegistration")
    public String ContractRegistration(Model model) {
        List<Object[]> productInformationRegistrationList = productInformationRegistrationRepository.findProductWithContract();
        List<ProductInformationJoinContractDTO> nonContractProducts = productInformationRegistrationList.stream().map( result ->
            new ProductInformationJoinContractDTO(
                    (int) result[0], // 품목 코드
                    (String) result[1], // 품목명
                    (String) result[2], // 약자
                    (String) result[3], // 재질
                    (int) result[4], // 가로
                    (int) result[5], // 세로
                    (int) result[6], // 높이
                    (int) result[7], // 중량
                    (String) result[8], // 파일 이름
                    (Long) result[9], // 계약 코드
                    (String) result[10],
                    (String) result[11],
                    (String) result[12]
            )).toList();

        model.addAttribute("nonContractProducts",nonContractProducts);

        return "ContractRegistration";
    }


    // 계약서 모달창 요청 매핑
    @GetMapping("ContractRegistrationModal")
    public String ContractRegistrationModal() {
        return "ContractRegistrationModal";
    }


    @GetMapping("ContractSend")
    public String ContractSendPopup () {return  "ContractSend"; }

    // 품목 전체 검색
    @GetMapping("/search/contract")
    @ResponseBody
    public List<Contract> getAll(){

        return contractService.getAllContracts();
    }

    // 계약 수정한 값으로 db에 수정
    @PostMapping("/edit/{contract_code}")
    @ResponseBody
    public String updateContract(@PathVariable(value = "contract_code") int contract_code, @RequestBody Contract contractUpdate) {

        Contract contract = contractRepository.findById(contract_code).orElseThrow(() -> new IllegalArgumentException("Invalid entity ID: " + contract_code));

        contract.setLead_time(contractUpdate.getLead_time());
        contract.setProduct_price(contractUpdate.getProduct_price());
        contract.setPayment_method(contractUpdate.getPayment_method());

        contractRepository.save(contract);

        return "수정된 계약 코드: " + contract_code;
    }

    // 계약 삭제
    @GetMapping("/delete/{contract_code}")
    @ResponseBody
    public String deleteContract(@PathVariable(value = "contract_code") int contract_code) {

        contractRepository.deleteById(contract_code);
        return "삭제된 계약 코드: " + contract_code;
    }

    // 이메일 전송
    @PostMapping("/contractUpload")
    public String SendContract(@RequestParam(value="email") String email, @RequestParam(value="text") String text, @RequestParam(value="file") MultipartFile contractMultipartFile) throws IOException {

        System.out.println("이메일: " + email + "이메일 내용: " + text + "파일: " + contractMultipartFile);
        

        System.out.println("아래부터는 메일 전송 하기위한 코드");

        // 사용자의 홈 디렉터리를 결정합니다.
        String userHome = System.getProperty("user.home");

        // 다운로드 폴더 경로를 정의합니다.
        String downloadFolderPath = userHome + File.separator + "Desktop";

        // 검증을 위해 다운로드 폴더 경로를 출력합니다.
        System.out.println("다운로드 폴더 경로: " + downloadFolderPath);

        // 다운로드 폴더가 존재하지 않으면 생성합니다.
        File downloadFolder = new File(downloadFolderPath);
        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs();
        }


        // 해당 저장 경로는 본인에게 맞는 곳으로 위치 수정해야함
        File file = new File(downloadFolderPath, contractMultipartFile.getOriginalFilename());
        contractMultipartFile.transferTo(file);


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
            message.setRecipients( Message.RecipientType.TO,   InternetAddress.parse("youngjjag28@gmail.com")  );
            // 이메일 제목
            message.setSubject("TAE 계약서에 관한  메일입니다.");
            Multipart multipart = new MimeMultipart();

            // 메일 내용 부분
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(text);
            multipart.addBodyPart(textPart);


            //이미지 첨부 부분
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(file);//
            multipart.addBodyPart(imagePart);

            message.setContent(multipart);

            // 발송
            Transport.send(message);
            System.out.println("발송완료됨");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return "ContractSend";
    }


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
        String destinationPath = folderPath+"발주서.jpg";

        //폴더에 파일저장
        Path destination = Path.of(destinationPath);
        Files.write(destination, imageBytes);
    }

}
