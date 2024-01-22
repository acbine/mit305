package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderRegisterDto;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRegisterServiceImpl implements OrderRegisterService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderRegisterDto> getProcurementPlansWithNullPurchase(String departName) {
        String queryString = "SELECT pp FROM ProcurementPlan pp WHERE pp.purchase IS NULL";
        if (departName != null && !departName.isEmpty())
            queryString += " AND pp.contract.company.departName = :departName";

        TypedQuery<ProcurementPlan> query = entityManager.createQuery(queryString, ProcurementPlan.class);

        if (departName != null && !departName.isEmpty())
            query.setParameter("departName", departName);

        List<OrderRegisterDto> orderRegisterDtos = new ArrayList<>();
        for (ProcurementPlan procurementPlan : query.getResultList())
            orderRegisterDtos.add(OrderRegisterDto.builder().departName(procurementPlan.getContract().getCompany().getDepartName()).build());

        return orderRegisterDtos;
    }

    @Override
    public void cancelProcurementPlan(int procurementplan_code) {
        ProcurementPlan procurementPlan = entityManager.find(ProcurementPlan.class, procurementplan_code);
        if (procurementPlan != null)
            entityManager.remove(procurementPlan);
    }
}