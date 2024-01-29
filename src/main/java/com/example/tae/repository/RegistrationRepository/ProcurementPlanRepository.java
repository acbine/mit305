package com.example.tae.repository.RegistrationRepository;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface ProcurementPlanRepository extends JpaRepository<ProcurementPlan, Integer> {

    List<ProcurementPlan> findAllByProjectPlan_Id(int prjId);

    @Query(value = "SELECT * FROM tae.procurement_plan where order_state IS Null", nativeQuery = true)
    List<ProcurementPlan> findByProcurementPlanState();

    // 조달 계획 불러오기 (발주 상태가 없는것)

    @Query(value = "SELECT pi.product_name, pi.product_code, p.out_pute_num, pp.product_code_count, c.lead_time, p.project_output_date, cp.depart_name, p.id, c.contract_code, pp.projectid_project_name " +
            "FROM contract c, product_for_project pp, project_plan p, company cp, product_information_registration pi " +
            "WHERE c.product_information_registration_product_code = pp.product_code_product_code " +
            "AND pp.projectid_project_name = p.project_project_name " +
            "AND c.company_business_number = cp.business_number " +
            "AND c.product_information_registration_product_code = pi.product_code " +
            "AND p.project_output_date BETWEEN :startDate AND :endDate " +
            "and p.project_output_date  >=  CURRENT_DATE " +
            "order by p.project_output_date ", nativeQuery = true)
    List<Object[]> JoinResult(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select pr from ProcurementPlan pr " +
            " where pr.contract.contract_code = :contractCode")
    ProcurementPlan findByContract_Contract_code(@Param("contractCode") int contractCode);

    @Query("select pr from ProcurementPlan pr where pr.order_state <> '발주전' and  pr.purchase is not null ")
    List<ProcurementPlan> findAllByProcurementplan_orderStateNotNull();

    @Query("select pr from ProcurementPlan pr where pr.order_state = '발주전' or pr.order_state = null and  pr.purchase is null ")
    List<ProcurementPlan> findAllByProcurementplan_orderStateNull();

    ProcurementPlan findByPurchase_OrderCode(String orderCode);

}
