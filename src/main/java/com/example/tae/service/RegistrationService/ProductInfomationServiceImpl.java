package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.DTO.PartDTO;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductInfomationServiceImpl implements ProductInfomationService {


    @Override
    public void insert_info() {

    }

    @Override
    public List<ContractDTO> getListOfProductContract(int product_code) {
        return null;
    }
}
