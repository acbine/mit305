package com.example.tae.entity.dto;

import com.example.tae.entity.DummyData.Classification.Assy;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Classification.Unit;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExistenceDTO {
    private String productName;
    private int product_code;
    private LocalDateTime departureDate;
    private String texture;
    private int weight;
    private int height;
    private int length;
    private int contract_pay;
    private int release;
    private int store;
    private int existence;
    private int existence_price;
    private Unit unit;
    private Assy assy;
    private Part part;

    public ExistenceDTO existence(ReleaseProcess releaseProcess, ProductInformationRegistration productInformationRegistration, int contract_pay, int store) {
        if(productInformationRegistration.getPart()==null || productInformationRegistration.getPart().getAssy()==null || productInformationRegistration.getPart().getAssy().getUnit()==null) {
            Part part1 = Part.builder().part(null).assy(null).build();
            return ExistenceDTO.builder()
                    .productName(productInformationRegistration.getProduct_name())
                    .product_code(productInformationRegistration.getProduct_code())
                    .departureDate(departureDate)
                    .texture(productInformationRegistration.getTexture())
                    .weight(productInformationRegistration.getWeight())
                    .height(productInformationRegistration.getHeight())
                    .length(productInformationRegistration.getLength())
                    .contract_pay(contract_pay)
                    .release(releaseProcess.getReleaseCNT())
                    .store(store)
                    .existence(existence)
                    .existence_price(existence_price)
                    .product_code(productInformationRegistration.getProduct_code())
                    .unit(part1.getAssy().getUnit())
                    .assy(part1.getAssy())
                    .part(part1)
                    .build();
        }
        int existence = store - releaseProcess.getReleaseCNT();
        int existence_price = existence * contract_pay;
        return ExistenceDTO.builder()
                .productName(productInformationRegistration.getProduct_name())
                .product_code(productInformationRegistration.getProduct_code())
                .departureDate(departureDate)
                .texture(productInformationRegistration.getTexture())
                .weight(productInformationRegistration.getWeight())
                .height(productInformationRegistration.getHeight())
                .length(productInformationRegistration.getLength())
                .contract_pay(contract_pay)
                .release(releaseProcess.getReleaseCNT())
                .store(store)
                .existence(existence)
                .existence_price(existence_price)
                .product_code(productInformationRegistration.getProduct_code())
                .unit(productInformationRegistration.getPart().getAssy().getUnit())
                .assy(productInformationRegistration.getPart().getAssy())
                .part(productInformationRegistration.getPart())
                .build();
    }

}
