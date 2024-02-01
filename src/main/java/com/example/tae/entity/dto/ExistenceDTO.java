package com.example.tae.entity.dto;

import com.example.tae.entity.DummyData.Classification.Assy;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Classification.Unit;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class ExistenceDTO {
    private String productName;
    private int product_code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime releaseDate;
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

    public ExistenceDTO existence(Existence existenceInfo,ReleaseProcess releaseProcess, ProductInformationRegistration productInformationRegistration, int contract_pay) {
        if(productInformationRegistration.getPart()==null || productInformationRegistration.getPart().getAssy()==null || productInformationRegistration.getPart().getAssy().getUnit()==null) {
            int existence = releaseProcess.getReleaseCNT();
            int existence_price = existence * contract_pay;
            Part part1 = Part.builder().part(null).assy(null).build();
            return ExistenceDTO.builder()
                    .releaseDate(releaseProcess.getModDate())
                    .productName(productInformationRegistration.getProduct_name())
                    .product_code(productInformationRegistration.getProduct_code())
                    .texture(productInformationRegistration.getTexture())
                    .weight(productInformationRegistration.getWeight())
                    .height(productInformationRegistration.getHeight())
                    .length(productInformationRegistration.getLength())
                    .releaseDate(releaseProcess.getModDate())
                    .contract_pay(contract_pay)
                    .release(releaseProcess.getReleaseCNT())
                    .existence(existence)
                    .existence_price(existence_price)
                    .product_code(productInformationRegistration.getProduct_code())
                    .unit(part1.getAssy().getUnit())
                    .assy(part1.getAssy())
                    .part(part1)
                    .build();
        }
        int existence = releaseProcess.getReleaseCNT();
        int existence_price = existence * contract_pay;
        return ExistenceDTO.builder()
                .releaseDate(releaseProcess.getModDate())
                .productName(productInformationRegistration.getProduct_name())
                .product_code(productInformationRegistration.getProduct_code())
                .texture(productInformationRegistration.getTexture())
                .weight(productInformationRegistration.getWeight())
                .height(productInformationRegistration.getHeight())
                .length(productInformationRegistration.getLength())
                .releaseDate(releaseProcess.getModDate())
                .contract_pay(contract_pay)
                .release(releaseProcess.getReleaseCNT())
                .existence(existence)
                .existence_price(existence_price)
                .product_code(productInformationRegistration.getProduct_code())
                .unit(productInformationRegistration.getPart().getAssy().getUnit())
                .assy(productInformationRegistration.getPart().getAssy())
                .part(productInformationRegistration.getPart())
                .build();
    }

}
