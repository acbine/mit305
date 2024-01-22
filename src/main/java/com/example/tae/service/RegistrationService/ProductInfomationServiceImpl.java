package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfomationServiceImpl implements ProductInfomationService {

    @Autowired
    private ProductInformationRegistrationRepository productInformationRegistrationRepository;

    @Override
    public List<ProductInformationRegistration> getAllProductInfo() {
        return productInformationRegistrationRepository.findAll();
    }

    @Override
    public void insert_info(ProductInformationRegistrationDTO productInformationRegistrationDto) {

    }


    @Override
    public List<ContractDTO> getListOfProductContract(int product_code) {
        return null;
    }
}
