package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.OrderRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProcurementPlanRepository procurementPlanRepository;
    private final ExistenceRepository existenceRepository;
    private final ContractRepository contractRepository;
    private final ProductInformationRegistrationRepository productInformationRegistrationRepository;

    /*발주서 발행*/
    @Override
    public void orderRegister(int procurementPlanCode) {
        Optional<ProcurementPlan> procurementPlanOp = procurementPlanRepository.findById(procurementPlanCode);
        ProcurementPlan procurementPlan = procurementPlanOp.get();
        Purchase purchase = Purchase.builder().build();

        orderRepository.save(purchase);
        Date order_date = Timestamp.valueOf(purchase.getModDate());
        ProcurementPlan updateProcurementPlan = ProcurementPlan.builder()
                .procurementplan_code(procurementPlan.getProcurementplan_code())
                .projectPlan(procurementPlan.getProjectPlan())
                .order_date(order_date)
                .order_state("발주중")
                .contract(procurementPlan.getContract())
                .procurementplan_code(procurementPlanCode)
                .SupportProductAmount(procurementPlan.getSupportProductAmount())
                .purchase(purchase)
                .SupportProductAmount(procurementPlan.getSupportProductAmount())
                .build();
        procurementPlanRepository.save(updateProcurementPlan);
    }


    /*발주서 목록 가져오기*/
    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> oList = new ArrayList<>();

        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProcurementplan_orderStateNotNull();

        procurementPlanList.forEach(info -> {
            ProductInformationRegistration productInformationRegistration = info.getContract().getProductInformationRegistration();
            OrderDTO orderDTO = OrderDTO.builder()
                    .productName(productInformationRegistration.getProduct_name())
                    .productCode(productInformationRegistration.getProduct_code())
                    .departName(info.getContract().getCompany().getDepartName())
                    .procurementPlanCode(info.getProcurementplan_code())
                    .orderState(info.getOrder_state())
                    .orderDate(info.getOrder_date())
                    .build();
            orderDTO.setProgressInspectionStatus();
            log.info(orderDTO.toString());
            oList.add(orderDTO);
        });
        return oList;
    }


    /*진척검수 등록하기*/
    @Override
    public List<OrderDTO> getOrderInspectData(int productCode, int procurementPlanCode) {
        Optional<ProductInformationRegistration> productInformationRegistration = productInformationRegistrationRepository.findById(productCode);
        ProductInformationRegistration productInformation = productInformationRegistration.get();
        List<ProcurementPlan> procurementPlanList = contractRepository.findByproductInformationId(productCode);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        procurementPlanList.forEach(procurementPlan -> {
            if (procurementPlan.getProcurementplan_code() == procurementPlanCode) {
                Optional<Existence> existenceNum = Optional.of(existenceRepository.findByProductCode(productInformation).orElseGet(
                        () -> {
                            Existence existence1 = Existence.builder()
                                    .releaseCNT(0)
                                    .productCode(productInformation)
                                    .build();
                            existenceRepository.save(existence1);
                            return existence1;
                        }
                ));
                OrderDTO orderDTO = OrderDTO.builder()
                        .productName(productInformation.getProduct_name())
                        .productCode(productInformation.getProduct_code())
                        .num(procurementPlan.getSupportProductAmount())
                        .existence(existenceNum.get().getReleaseCNT())
                        .LT(procurementPlan.getContract().getLead_time())
                        .projectOutPutDate(procurementPlan.getContract().getContract_date())
                        .orderDate(procurementPlan.getOrder_date())
                        .width(productInformation.getWidth())
                        .length(productInformation.getLength())
                        .height(productInformation.getHeight())
                        .text(productInformation.getTexture())
                        .procurementPlanCode(procurementPlan.getProcurementplan_code())
                        .orderState(procurementPlan.getOrder_state())
                        .departName(procurementPlan.getContract().getCompany().getDepartName())
                        .build();
                orderDTOList.add(orderDTO);
            }
        });
        return orderDTOList;
    }


    /*발주 안 된 조달계획 정보 전부 가져오기*/
    public List<OrderDTO> oListSend() {
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProcurementplan_orderStateNull();
        List<OrderDTO> oList = new ArrayList<>();
        for (ProcurementPlan procurementPlan : procurementPlanList) {
            Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(procurementPlan.getContract().getProductInformationRegistration()).orElseGet(
                    () -> {
                        return Existence.builder()
                                .productCode(procurementPlan.getContract().getProductInformationRegistration())
                                .releaseCNT(0)
                                .build();
                    }
            ));
            OrderDTO orderDTO = OrderDTO.builder()
                    .departName(procurementPlan.getContract().getCompany().getDepartName())
                    .agent(procurementPlan.getContract().getCompany().getBusinessName())
                    .email(procurementPlan.getContract().getCompany().getBusinessEmail())
                    .fax(procurementPlan.getContract().getCompany().getFax())
                    .LT(procurementPlan.getContract().getLead_time())
                    .procurementPlanCode(procurementPlan.getProcurementplan_code())
                    .num(procurementPlan.getSupportProductAmount()) // 조달 수량 받아 오기
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

}
