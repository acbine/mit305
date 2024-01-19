package com.example.tae.entity.Order.dto;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class OrderPopupDto {
    private String product_name;
    private int product_code;
    private int supportProductAmount;

    private Date projectOutputDate;
    private int lead_time;
    private Date order_date;

    public static OrderPopupDto OrderPopupDtoInfo(Purchase purchase){
        if (purchase == null)
            return new OrderPopupDto();

        ProcurementPlan pp = purchase.getProcurementPlan().get(0);
        ProductInformationRegistration getPIR = pp.getContract().getProductInformationRegistration();

        return OrderPopupDto.builder().product_name(getPIR.getProduct_name()).product_code(getPIR.getProduct_code())
                .supportProductAmount(pp.getSupportProductAmount()).projectOutputDate(pp.getProjectPlan().getProjectOutputDate())
                .lead_time(pp.getContract().getLead_time()).order_date(pp.getOrder_date()).build();
    }
}