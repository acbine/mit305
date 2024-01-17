package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDto;

import java.util.List;

// 품목 정보 서비스
public interface ProductInfomationService {

    // 대분류 리스트 검색

    // 중분류 리스트 검색

    // 소분류 리스트 검색

    // 품목 정보 리스트 검색
    List<ProductInformationRegistrationDto> getListOfProduct(int product_code);

    // 품목 정보 추가
    void insert_info();

    // 선택한 품목의 계약 정보 리스트 검색
    List<ContractDTO> getListOfProductContract(int product_code);
}
