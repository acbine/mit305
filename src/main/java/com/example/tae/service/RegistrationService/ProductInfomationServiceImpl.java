package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfomationServiceImpl implements ProductInfomationService {


    @Override
    public void insert_info(ProductInformationRegistrationDTO productInformationRegistrationDto) {

    }


    @Override
    public List<ContractDTO> getListOfProductContract(int product_code) {
        return null;
    }
}
