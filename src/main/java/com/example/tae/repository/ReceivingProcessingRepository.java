package com.example.tae.repository;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReceivingProcessingRepository extends JpaRepository<ReceivingProcessing,Integer> {
//---------------------------------------------------------발주 현황괄리 리포트------------------------------------------------------
    @Query("SELECT pp FROM ProcurementPlan pp WHERE pp.projectPlan.projectOutputDate BETWEEN :start AND :end ")
    List<ProcurementPlan> StatMentRepostSearch(@Param("start") Date start,@Param("end") Date end); //발주현황관리의 조달계획리스트를 검색 기간동안 불러오기

    @Query("SELECT pp.order_state , Count(pp) FROM ProcurementPlan pp WHERE pp.projectPlan.projectOutputDate BETWEEN :start AND :end GROUP BY pp.order_state ")
    List<Object[]> groupByOrderState(@Param("start") Date start, @Param("end") Date end); //발주현황관리의 조달계획리스트를 발주 상태별로 묶어서 각 발주 상태가 갯수가 얼마나되는지
//----------------------------------------------------------입고처리------------------------------------------------------------------
    @Query("SELECT pp FROM ProcurementPlan pp where pp.order_state='검수처리완료' ORDER BY pp.projectPlan.projectOutputDate ")
    List<ProcurementPlan> RECEIVING_PROCESSING_DTO_LIST(); //조달계획의 품목 상태가 발주전인 조달계획 리스트 불러오기(여기에만 정렬기능 오름차순)
    @Query("SELECT pp FROM ProcurementPlan pp where pp.procurementplan_code=:ppcode ")
    ProcurementPlan productplane (@Param("ppcode") int pp); //조달계획코드 값을 이용해 조달계획1개 불러오기

    @Modifying
    @Query("UPDATE ProcurementPlan pp SET pp.order_state='마감' WHERE pp.procurementplan_code=:ppcode ")
    int updateProcumentPlan (@Param("ppcode") int pp); //발주전 상태값을 마감으로

    @Query("SELECT rp FROM ReceivingProcessing rp where rp.procurementPlan.procurementplan_code=:ppcode ")
    ReceivingProcessing findByProcumentPlanCode (@Param("ppcode") int pp); //입고처리 엔티티에서 조달계획 코드가 pp인 것 불러오기


//        @Query("SELECT pp FROM ProcurementPlan pp where pp.order_state=:searchState and pp.contract.company.businessName = :inputData ")
////                ("CASE WHEN :searchData = 'searchDepartName' then pp.contract.company.businessName = :inputData"))
//        List<ProcurementPlan> searchProcurementPlansBNOS(@Param("searchState")String searchState,@Param("inputData") String inputData); //검색도된 정보로 불러오기 (발주서 와 업체명으로)

//        @Query("SELECT pp FROM ProcurementPlan pp where pp.order_state=:searchState and pp.contract.company.businessName = :inputData ")
////                ("CASE WHEN :searchData = 'searchProductName' then pp.productForProject.productCode = :inputData ")+
////                ("CASE WHEN :searchData = 'searchDepartName' then pp.contract.company.businessName = :inputData"))
//        List<ProcurementPlan> searchProcurementPlansPNOS(@Param("searchState")String searchState,@Param("inputData") String inputData); //검색도된 정보로 불러오기 (발주서 와 업체명으로)

//    @Query("SELECT pp FROM ProcurementPlan pp " +
//            "WHERE pp.order_state = :searchState " +
//            "AND (:searchData = 'searchProductName' AND pp.productForProject.productCode = :inputData " +
//            "     OR :searchData = 'searchDepartName' AND pp.contract.company.businessName = :inputData)")
//        List<ProcurementPlan> searchProcurementPlansPNOS(@Param("searchState")String searchState,@Param("inputData") String inputData , @Param("searchData") String searchData); //검색도된 정보로 불러오기 (발주서 와 업체명으로)


    Optional<ReceivingProcessing> findTop1ByOrderByModDateDesc();

    @Query(value = "select * from tae.receiving_processing " +
            "where procurement_plan_procurementplan_code = :pcmPlanCode " +
            "order by mod_date desc limit 1", nativeQuery = true
    )
    Optional<ReceivingProcessing> findTop1ByOrderByModDateDesc(@Param("pcmPlanCode") int pcmPlanCode);

}
