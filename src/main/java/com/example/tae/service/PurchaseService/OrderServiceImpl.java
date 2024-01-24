package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.Order.dto.OrderListDto;
import com.example.tae.entity.Order.dto.OrderPopupDto;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.OrderRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProcurementPlanRepository procurementPlanRepository;
    private final ExistenceRepository existenceRepository;
    private final ContractRepository contractRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> oList = new ArrayList<>();
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAll();
        procurementPlanList.forEach(info -> {
            ProductInformationRegistration productInformationRegistration = info.getContract().getProductInformationRegistration();
            OrderDTO orderDTO = OrderDTO.builder()
                    .productName(productInformationRegistration.getProduct_name())
                    .productCode(productInformationRegistration.getProduct_code())
                    .departName(info.getContract().getCompany().getDepartName())
                    .orderState(info.getOrder_state())
                    .orderDate(info.getOrder_date())
                    .build();
            orderDTO.setProgressInspectionStatus();
            oList.add(orderDTO);
        });
        return oList;
    }

//    @Override
//    public OrderPopupHeadDto getOrderPopupHeadDto(String ordercode) {
//        Purchase purchase = orderRepository.findById(ordercode).orElse(null);
//
//        if (purchase != null) {
//            purchase.getProcurementPlan().size();
//            return OrderPopupHeadDto.OrderPopupHead(purchase);
//        } else
//            return new OrderPopupHeadDto();
//    }

    @Override
    public OrderPopupDto getOrderPopupData(String orderCode) {
        Purchase purchase = orderRepository.findById(orderCode).orElse(null);
        if (purchase != null) {
            purchase.getProcurementPlan().size();
            return OrderPopupDto.OrderPopupDtoInfo(purchase);
        } else
            return new OrderPopupDto();
    }

    public List<OrderDTO> oListSend() {
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findByProcurementPlanState();
        List<OrderDTO> oList = new ArrayList<>();
        for(ProcurementPlan procurementPlan : procurementPlanList) {
            Optional<Existence> existence = existenceRepository.findByProductCode(procurementPlan.getContract().getProductInformationRegistration());
            OrderDTO orderDTO = OrderDTO.builder()
                    .departName(procurementPlan.getContract().getCompany().getDepartName())
                    .agent(procurementPlan.getContract().getCompany().getBusinessName())
                    .email(procurementPlan.getContract().getCompany().getBusinessEmail())
                    .fax(procurementPlan.getContract().getCompany().getFax())
                    .LT(procurementPlan.getContract().getLead_time())
                    .procurementPlanCode(procurementPlan.getProcurementplan_code())
                    .num(procurementPlan.getProjectPlan().getOutPuteNum()*procurementPlan.getProductForProject().getProductCodeCount())
                    .tel(procurementPlan.getContract().getCompany().getBusinessTel())
                    .orderDate(procurementPlan.getOrder_date())
                    .projectOutPutDate(procurementPlan.getProjectPlan().getProjectOutputDate())
                    .productCode(procurementPlan.getContract().getProductInformationRegistration().getProduct_code())
                    .productName(procurementPlan.getContract().getProductInformationRegistration().getProduct_name())
                    .existence(existence.get().getReleaseCNT())
                    .build();
            oList.add(orderDTO);
        }
        return oList;
    }

    @Override
    public void orderRegister(int procurementPlanCode) {
        Optional<ProcurementPlan> procurementPlanOp = procurementPlanRepository.findById(procurementPlanCode);
        ProcurementPlan procurementPlan = procurementPlanOp.get();
        Purchase purchase = Purchase.builder().build();

        orderRepository.save(purchase);

        ProcurementPlan updateProcurementPlan = ProcurementPlan.builder()
                .procurementplan_code(procurementPlan.getProcurementplan_code())
                .projectPlan(procurementPlan.getProjectPlan())
                .order_date(procurementPlan.getOrder_date())
                .order_state("발주중")
                .contract(procurementPlan.getContract())
                .procurementplan_code(procurementPlanCode)
                .SupportProductAmount(procurementPlan.getSupportProductAmount())
                .purchase(purchase)
                .productForProject(procurementPlan.getProductForProject())
                .build();
        procurementPlanRepository.save(updateProcurementPlan);
    }
}
