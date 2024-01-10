package com.example.tae.controller;

import com.example.tae.dto.ImageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

@RestController
public class BinRestController {

    @PostMapping("imageURl")
    public ImageDTO imageupload( @RequestBody ImageDTO imageDTO){
        System.out.println("포스트요청 들어옴");

        String folderPath="C:/Users/BIn/Desktop/이미지 테스트/거래명세서2/"; //폴더주소

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
            message.setRecipients( Message.RecipientType.TO,   InternetAddress.parse(imageDTO.emailadress)  );
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

        return imageDTO;
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
