package com.example.tae.RegistratioinTest.Repository;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProcurementPlanRepositoryTest {

//    @Autowired // 생산 계획
//    ProjectPlanRepository projectPlanRepository;
//
//
//    @Autowired // 계약
//    ContractRepository contractRepository;
//
//    @Autowired
//    ProductForProjectRepository productForProjectRepository; //제품에대해 품목 몇개있는지
//
//    @Autowired
//    ProjectRepository projectRepository;//제품
//
//    @Autowired
//    ProductInformationRegistrationRepository productInfomationRepository;// 품목
//
//    @Autowired
//    OrderRepository orderRepository; // 발주서
//
//    @Autowired // 조달 계획
//    ProcurementPlanRepository procurementPlanRepository;
//
//
//    Date date1 = new Date(2024,01,18);
//
//    @Test
//    public void Test1() {
//
//        // 생산계획 아이디
//        ProjectPlan projectPlan = projectPlanRepository.findById(1).get();
//
//        // 계약 코드
//        Contract contract = contractRepository.findById(1).get();
//
//        //발주서 코드
//        Purchase purchase = Purchase.builder().build();
//        orderRepository.save(purchase);
//
//        //품목에대한계약코드
//        Project project=projectRepository.findById("스마트폰").get();
//        ProductInformationRegistration productInformationRegistration = productInfomationRepository.findById(1).get();
//        ProductForProjectEmbeddable productForProjectEmbeddable = new ProductForProjectEmbeddable(productInformationRegistration, project);
//        ProductForProject productForProject = productForProjectRepository.findById(productForProjectEmbeddable).get();
//
//
//        ProcurementPlan procurementPlan = ProcurementPlan.builder()
//                .projectPlan(projectPlan)//생산계획
//                .contract(contract)//계약코드
////                .purchase(purchase)//발주서코드
//
//                .productForProject() //제품에대한 품목의수량
//
//                .SupportProductAmount(5)//조달수량
//                .order_date(date1)//발주일
//                .order_state("ㅎㅇㄶㄹㄴ 전")
//                .build();
//
//        procurementPlanRepository.save(procurementPlan);
//
//    }
//
//    @Test
//    public void aaa(){
//        ProcurementPlan procurementPlan = procurementPlanRepository.findById(1).get();
//        System.out.println(procurementPlan.getContract());
//    }

//    @Test
//    public void ProjectPlan() {//생산계획 가져오는 것
//        procurementPlanRepository.findProjectPlan("스마트폰").forEach(x-> System.out.println(x));
//    }
//
//    @Test
//    public void ProductForProject() {//제품에 해당하는 품목이 몇개 들어있는지
//        productForProjectRepository.findAll().forEach(x-> System.out.println(x));
//    }
//
//
//    @Test
//    public void JoinQuery () {
//
//        procurementPlanRepository.findAllList().forEach(x -> System.out.println(x));
//
//    }
//
//    @Test
//    public void findContract() {// 계약에서 품목 코드로 검색
//        List<ProductForProject>  aa = productForProjectRepository.findAll();
//
//        for(int i = 0; i < aa.size(); i++) {
//            procurementPlanRepository.findContract(aa.get(i).getProductCode().getProduct_code()).forEach(x -> System.out.println(x));
//        }
//
//    }

}
