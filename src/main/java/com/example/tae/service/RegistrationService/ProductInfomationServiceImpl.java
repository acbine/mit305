package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;
import com.example.tae.repository.DummyRepository.PartRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class ProductInfomationServiceImpl implements ProductInfomationService {

    @Autowired
    private ProductInformationRegistrationRepository productInformationRegistrationRepository;

    @Autowired
    PartRepository partRepository;

    @Override
    public List<ProductInformationRegistration> getAllProductInfo() {
        return productInformationRegistrationRepository.findAll();
    }


    @Override
    public List<ContractDTO> getListOfProductContract(int product_code) {
        return null;
    }

    //    private String saveImage(MultipartFile multipartFile) throws IOException {
//
//
//        // 여기에 이미지를 저장하는 로직을 추가
//        // 예를 들어, 저장 경로는 /resources/images/ 디렉토리로 가정
//
//        String uploadDir = "src/main/resources/static/images/";
//
//        // 원본 파일명을 사용하여 저장할 파일명 생성
//        String originalFilename = multipartFile.getOriginalFilename();
//        System.out.println("현재 파일명: " + originalFilename);
//
//        String fileName = System.currentTimeMillis() + "_" + originalFilename;
//        System.out.println(""+ fileName);
//
//        String filePath = uploadDir + fileName;
//
//        // 이미지를 서버에 저장
//        File dest = new File(filePath);
//        multipartFile.transferTo(dest);
//
//        // 이미지의 경로를 반환
//        return fileName;
//    }

    @Override
    @Transactional
    public void saveProductInfo(ProductInformationRegistrationDTO productInformationRegistrationDTO) throws IOException {

        System.out.println("///// 서비스 시작 //////");

        ProductInformationRegistration productInformationRegistration = new ProductInformationRegistration();

        // 이름
        productInformationRegistration.setProduct_name(productInformationRegistrationDTO.getProduct_name());

        // 약칭
        productInformationRegistration.setProduct_abbreviation(productInformationRegistrationDTO.getProduct_abbreviation());

        // 재질
        productInformationRegistration.setTexture(productInformationRegistrationDTO.getTexture());

        // 가로
        productInformationRegistration.setWidth(productInformationRegistrationDTO.getWidth());

        // 세로
        productInformationRegistration.setLength(productInformationRegistrationDTO.getLength());

        // 높이
        productInformationRegistration.setHeight(productInformationRegistrationDTO.getHeight());

        // 중량
        productInformationRegistration.setWeight(productInformationRegistrationDTO.getWeight());

        // 소분류
        Part part = partRepository.findById(productInformationRegistrationDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("부모 데이터를 찾을 수 없습니다."));
        productInformationRegistration.setPart(part);

//        System.out.println("upload 값: " + productInformationRegistrationDTO.getUpload());

        // 이미지
//        String imageName = saveImage(productInformationRegistrationDTO.getUpload());
//        productInformationRegistration.setImageName(imageName);


        productInformationRegistrationRepository.save(productInformationRegistration);
    }
}
