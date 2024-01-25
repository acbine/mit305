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
    @Query("SELECT pp FROM ProcurementPlan pp WHERE pp.projectPlan.projectOutputDate BETWEEN :start AND :end ORDER BY pp.projectPlan.projectOutputDate ")
    List<ProcurementPlan> StatMentRepostSearch(@Param("start") Date start,@Param("end") Date end); //발주현황관리의 조달계획리스트를 검색 기간동안 불러오기  정렬은?->생산계획의 오른차순으로

    @Query("SELECT pp FROM ProcurementPlan pp ORDER BY pp.projectPlan.projectOutputDate ")
    List<ProcurementPlan> statementAllSearch(); //조달계획의 품목리스트를 전부  불러오기    정렬은?-> 생산계획의 오른차순으로

    @Query("SELECT pp.order_state , Count(pp) FROM ProcurementPlan pp WHERE pp.projectPlan.projectOutputDate BETWEEN :start AND :end GROUP BY pp.order_state ORDER BY pp.order_state ")
    List<Object[]> groupByOrderState(@Param("start") Date start, @Param("end") Date end); //발주현황관리의 조달계획리스트를 발주 상태별로 묶어서 각 발주 상태가 갯수가 얼마나되는지 정렬은?-> 발주서 의 이름 순서대로 오른차순
//----------------------------------------------------------입고처리------------------------------------------------------------------
    @Query("SELECT pp FROM ProcurementPlan pp where pp.order_state='검수처리완료' ORDER BY pp.purchase.regDate LIMIT 10")
    List<ProcurementPlan> RECEIVING_PROCESSING_DTO_LIST(); //조달계획의 품목 상태가 검수처리완료  모든 조달계획 리스트를 10개만  불러오기 정렬은?->발주서 발행일을 기준으로 오름차순      > 입고처리 맨처음 보여줄시 사용

    @Query("SELECT pp FROM ProcurementPlan pp WHERE pp.order_state='검수처리완료' AND pp.contract.productInformationRegistration.product_name= :inputData ORDER BY pp.purchase.regDate ") //검색내용을 넣어주고 검색타입은품목명 검수완료처리된 조달계획을 불러오는 것  정렬은?->발주서 발행일을 기준 오름차순
    List<ProcurementPlan> rPSearchByProductname(@Param("inputData") String inputData);

    @Query("SELECT pp FROM ProcurementPlan pp WHERE pp.order_state='검수처리완료' AND pp.contract.company.departName=:inputData ORDER BY pp.purchase.regDate ") //검색내용을 넣어주고 업체명을 가지고 검수완료처리된 조달계획을 불러오는 것  정렬은?->발주서 발행일을 기준 오름차순
    List<ProcurementPlan> rPSearchByDepartname(@Param("inputData") String inputData);

    @Query("SELECT pp FROM ProcurementPlan pp where pp.procurementplan_code=:ppcode ")
    ProcurementPlan productplane (@Param("ppcode") int pp); //조달계획코드 값을 이용해 조달계획1개 불러오기     정렬은?-> 조달계획코드가 하나라 필요없음

    @Modifying
    @Query("UPDATE ProcurementPlan pp SET pp.order_state='마감' WHERE pp.procurementplan_code=:ppcode ")
    int updateProcumentPlan (@Param("ppcode") int pp); //조달 계획 코드를 넣어주면 검수완료 상태값을 마감으로 변경해누즌 코드   정렬은?-> 조달계획코드가 하나라 필요없음

    @Query("SELECT rp FROM ReceivingProcessing rp where rp.procurementPlan.procurementplan_code=:ppcode ")
    ReceivingProcessing findByProcumentPlanCode (@Param("ppcode") int pp); //입고처리 엔티티에서 조달계획 코드가 pp인 것 불러오기 정렬은?-> 조달계획코드가 하나라 필요없음

//-----------------------------------------------------------거래명세서-----------------------------------------------------------------------------------------
@Query("SELECT pp FROM ProcurementPlan pp where pp.order_state='검수처리완료' And pp.purchase.orderCode=:od")
List<ProcurementPlan> listByOrderCodestrard(@Param("od") String OrderCode); //조달계획의 품목 상태가 검수처리완료 이면서 OrderCode로 조달계획 리스트 불러오기(여기에만 정렬기능 오름차순)

@Query("SELECT pp FROM ProcurementPlan pp where pp.order_state='마감' And pp.purchase.orderCode=:od")
List<ProcurementPlan> listByOrderCodeend(@Param("od") String OrderCode); //조달계획의 품목 상태가 마감 이면서 OrderCode로 조달계획 리스트 불러오기(여기에만 정렬기능 오름차순)

@Query("SELECT pp.purchase.orderCode , Count(pp)  FROM ProcurementPlan pp GROUP BY pp.purchase.orderCode ORDER BY pp.purchase.orderCode ")
List<Object[]> groupByOrderCode(); //조달계획리스트를 발주 코드로 묶어서  각 발주코드별 가 갯수가 얼마나되는지





//------------------------------------------------------자재관리에서 사용하는 쿼리-----------------------------------------------------------------------------------

    @Query(value = "select * from tae.receiving_processing " +
            "where procurement_plan_procurementplan_code = :pcmPlanCode " +
            "order by mod_date desc limit 1", nativeQuery = true
    )
    Optional<ReceivingProcessing> findTop1ByOrderByModDateDesc(@Param("pcmPlanCode") int pcmPlanCode);

}
