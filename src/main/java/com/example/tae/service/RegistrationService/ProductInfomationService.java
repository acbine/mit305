package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.DTO.PartDTO;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;

import java.io.IOException;
import java.util.List;

// 품목 정보 서비스
public interface ProductInfomationService {


    // 품목 정보 리스트 검색
    List<ProductInformationRegistration> getAllProductInfo();

    // 선택한 품목의 계약 정보 리스트 검색
    List<ContractDTO> getListOfProductContract(int product_code);

    void saveProductInfo(ProductInformationRegistrationDTO productInformationRegistrationDTO) throws IOException;
}
