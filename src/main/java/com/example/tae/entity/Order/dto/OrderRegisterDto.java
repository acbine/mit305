package com.example.tae.entity.Order.dto;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class OrderRegisterDto {
    private String departName;

    private String product_name;
    private int product_code;
    private int supportProductAmount;

    private Date projectOutputDate;
    private Date order_date;
    private int lead_time;

    public OrderRegisterDto OrderRegisterInfo(ProcurementPlan procurementPlan){
        return OrderRegisterDto.builder().product_name(procurementPlan.getContract().getProductInformationRegistration().getProduct_name())
                .product_code(procurementPlan.getContract().getProductInformationRegistration().getProduct_code())
                .supportProductAmount(procurementPlan.getSupportProductAmount())
                .projectOutputDate(procurementPlan.getProjectPlan().getProjectOutputDate()).order_date(procurementPlan.getOrder_date())
                .lead_time(procurementPlan.getContract().getLead_time()).departName(procurementPlan.getContract().getCompany().getDepartName()).build();
    }
}


/*<tr>
<td>ProductInformationRegistration.product_name</td>
<td>ProductInformationRegistration.product_code</td>
<td>ProcurementPlan.product_amount</td>
<td>현재 재고량</td>
<td>ProjectPlan.projectOutputDate</td>
<td>ProcurementPlan.order_date</td>
<td>Contract.lead_time</td>
</tr>*/
