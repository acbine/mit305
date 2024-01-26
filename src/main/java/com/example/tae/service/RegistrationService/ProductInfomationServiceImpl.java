package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductForProject.ProductForProjectEmbeddable;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;
import com.example.tae.repository.DummyRepository.PartRepository;
import com.example.tae.repository.ProductForProjectRepository;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProductInfomationServiceImpl implements ProductInfomationService {

    private ProductInformationRegistrationRepository productInformationRegistrationRepository;
    private ProductForProjectRepository productForProjectRepository;
    private PartRepository partRepository;
    private ProjectRepository projectRepository;

    @Override
    public List<ProductInformationRegistration> getAllProductInfo() {
        return productInformationRegistrationRepository.findAll();
    }

    @Override
    public List<ContractDTO> getListOfProductContract(int product_code) {
        return null;
    }

    @Transactional
    public String saveImage(MultipartFile file)  {

        try {

            String uploadDir = "images"; // 이미지 저장 경로 : 프로젝트 내의 images 폴더
            String image_name = file.getOriginalFilename(); // 이미지 이름 생성
            Path upload_path = Paths.get(uploadDir);

            log.info("생성된 파일 이름: " + image_name); // fa_download.png 확인됨

            if(!Files.exists(upload_path)) { // 저장 폴더가 없으면 생성

                Files.createDirectories(upload_path);
            }

            // 파일을 저장 경로에 복사
            Path filePath = upload_path.resolve(image_name);
            Files.copy(file.getInputStream(), filePath , StandardCopyOption.REPLACE_EXISTING);

            return image_name;

        } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException("이미지 저장 실패");

        }
    }

    @Override
    @Transactional
    public void saveProductInfo(ProductInformationRegistrationDTO productInformationRegistrationDTO) throws IOException {

        System.out.println("///// 품목 정보 등록 서비스 시작 //////");

        ProductInformationRegistration productInformationRegistration = new ProductInformationRegistration();

        productInformationRegistration.setProduct_name(productInformationRegistrationDTO.getProduct_name()); // 이름
        productInformationRegistration.setProduct_abbreviation(productInformationRegistrationDTO.getProduct_abbreviation()); // 약칭
        productInformationRegistration.setTexture(productInformationRegistrationDTO.getTexture()); // 재질
        productInformationRegistration.setWidth(productInformationRegistrationDTO.getWidth()); // 가로
        productInformationRegistration.setLength(productInformationRegistrationDTO.getLength()); // 세로
        productInformationRegistration.setHeight(productInformationRegistrationDTO.getHeight()); // 높이
        productInformationRegistration.setWeight(productInformationRegistrationDTO.getWeight()); // 중량
        Part part = partRepository.findById(productInformationRegistrationDTO.getId()) // 소분류
                .orElseThrow(() -> new IllegalArgumentException("부모 데이터를 찾을 수 없습니다."));
        productInformationRegistration.setPart(part);


        String image_name = saveImage(productInformationRegistrationDTO.getImage_name());

        productInformationRegistration.setImage_name(image_name);


        productInformationRegistrationRepository.save(productInformationRegistration);

        Project projectId = projectRepository.findById("스마트폰").get();

        Optional<ProductForProject> productForProject = Optional.of(productForProjectRepository.findByProdcutId(productInformationRegistration.getProduct_code()).orElseGet(
                ()->{
                    ProductForProjectEmbeddable productForProjectEmbeddable = ProductForProjectEmbeddable.builder()
                            .productCode(productInformationRegistration)
                            .projectID(projectId)
                            .build();
                    Random random = new Random();
                    return ProductForProject.builder()
                            .productCode(productInformationRegistration)
                            .productCodeCount(random.nextInt(1,100))
                            .projectID(projectId)
                            .build();
                }
        ));
       productForProjectRepository.save(productForProject.get());
    }
}
