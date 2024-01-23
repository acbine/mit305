package com.example.tae.service.dummyService;

import com.example.tae.entity.DummyData.Classification.Assy;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Classification.Unit;
import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.DummyData.DTO.CompanyDTO;
import com.example.tae.repository.DummyRepository.*;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
@AllArgsConstructor
public class DummyServiceImpl implements DummyService {

    private CompanyRepository companyRepository;
    private ProjectRepository projectRepository;
    private UnitRepository unitRepository;
    private PartRepository partRepository;
    private AssyRepository assyRepository;

    public void dummyDataInjection() {
        String[] nameData = {
                "홍연지", "추동연", "남시정", "예대원", "안승현", "풍시정", "허형철", "황보지윤", "허태일", "추철호", "예동현", "사공승언", "탁선미", "백홍자", "표동민", "백명우", "전경원", "풍수경", "전은아", "남궁하현", "황보인옥", "황보희옥", "유호성", "봉시연", "예정태", "배태지", "남궁승연", "오혜훈", "송경윤",
                "서샘", "양달", "최한길", "이으뜸", "임우람", "안으뜸", "심나라우람", "탁달", "풍나라우람", "강나라우람", "신나라우람", "윤믿음", "백달", "표한길", "허빛가람", "탁우람", "황우람", "손으뜸", "전나길", "추버들", "임샘", "황보힘찬", "남궁한길", "탁힘찬", "봉버들", "추우람", "허으뜸", "사공한길", "손나길", "표나라우람",
                "사공솔", "풍보름", "남궁자람", "정솔", "한마음", "황고은", "강빛나", "홍아롱", "하잔디", "탁아람", "김두리", "설하루", "이달래", "최단비", "임별찌", "한단비", "배빛나", "홍새벽", "강겨울", "허여름", "제갈잔디", "사공아리", "문다래", "설두리", "제갈아롱", "한기쁨", "추누리", "남보람", "문나비", "김마음",
                "배훈", "배혁", "정웅", "서훈", "배호", "전혁", "서호", "권웅", "서광", "윤훈", "최혁", "추호", "탁철", "사공웅", "허호", "제갈훈", "예훈", "탁건", "서건", "박혁", "이혁", "고호", "남철", "추호", "추광", "강철", "서웅", "이광", "심훈", "하훈",
                "탁리", "안지", "신란", "탁은", "한린", "추란", "배은", "문설", "김린", "남재", "송란", "탁란", "안설", "봉리", "윤설", "송상", "성설", "백지", "유란", "배현", "배린", "최지", "성설", "홍란", "허상", "사공지", "전상", "홍란", "사공성", "고란",
                "탁요한", "제갈덕수", "황철순", "남광조", "성요한", "심병헌", "문강민", "안강민", "권성한", "윤재섭", "유경모", "박재섭", "설경택", "강광조", "남요한", "조승헌", "한일성", "류광조", "허경택", "탁경택", "제갈동건", "장일성", "권요한", "임현승", "고재범", "설무열", "배이수", "서승헌", "권요한", "문덕수",
                "정자경", "표가영", "안유리", "성혜림", "봉남순", "배나영", "홍재신", "신경완", "류미란", "하남순", "한유리", "윤나영", "최민서", "백이경", "남미래", "박애정", "박장미", "신미란", "장재신", "풍미란", "문남순", "배혜린", "김경완", "배유리", "김남순", "백애정", "남지해", "유가영", "백자경", "조영신"
        };

        String[] companyNameData = {
                "(유)길승산업",
                "(주) 제이아이테크",
                "(주) 제이아이테크",
                "(주)KDC",
                "(주)경도전자",
                "(주)경신이티엠",
                "(주)그린피앤엘",
                "(주)남성기계",
                "(주)넥스큐브",
                "(주)뉴텍웰니스김해지점",
                "(주)니즈원",
                "(주)대명",
                "(주)동우씨엠",
                "(주)두산에너지산업",
                "(주)두원코리아",
                "(주)디에스피",
                "(주)디자인기린",
                "(주)루바니",
                "(주)르네상스환경디자인산업",
                "(주)마텍스",
                "(주)마하니트",
                "(주)무한랜드",
                "(주)미래메탈",
                "(주)미쥬",
                "(주)브로우맥스",
                "(주)삼성애드",
                "(주)서광알미늄",
                "(주)성일정판",
                "(주)세명하이테크",
                "(주)세양",
                "(주)스마트휴먼텍",
                "(주)스텔라컴퍼니",
                "(주)신영목재",
                "(주)신우공업사",
                "(주)신일코스넷",
                "(주)쓰리원",
                "(주)씨피씨",
                "(주)아리체",
                "(주)에스에이치엘",
                "(주)에어졸랜드",
                "(주)에이스그린텍",
                "(주)에이스엔지니어링",
                "(주)에이펙스테크놀로지",
                "(주)엔에스테크",
                "(주)엘시시",
                "(주)엠아이팜",
                "(주)엠앤이",
                "(주)엠케이정공",
                "(주)예일전자",
                "(주)오이엔",
                "(주)오토싱",
                "(주)와트레인",
                "(주)용진",
                "(주)유남테크",
                "(주)유텍",
                "(주)이엑스케어",
                "(주)이오닉스",
                "(주)이한산업",
                "(주)재성테크",
                "(주)제스파",
                "(주)제이앤지",
                "(주)제이앤지",
                "(주)제이에스",
                "(주)중심티엔씨",
                "(주)지에이치엘",
                "(주)진명단조",
                "(주)청산카플링",
                "(주)충전공영개발",
                "(주)티에스에어테크",
                "(주)티제이텍",
                "(주)프리텍코리아",
                "(주)한빛코리아",
                "(주)한성테크",
                "(주)한양인더스트리",
                "(주)행복S&P제지",
                "(주)휴코",
                "G. S테크",
                "G. S테크",
                "J.C몰드(주)",
                "KMTechnology",
                "abo",
                "㈜덕원산업",
                "강동테크",
                "경보산업(주)",
                "경진스틸",
                "계원정밀",
                "광동레이져",
                "국광플랜",
                "그랜드",
                "금수산업",
                "금하기계(주)",
                "나노쿼츠",
                "네오테크(APEX)",
                " 다유정공",
                "다함건설산업(주)",
                "대성",
                " 대성고무",
                "대성고무",
                "대성주철공업(주)",
                "대신정밀",
                "대연정밀",
                "대영골드(주)",
                "덕화푸드",
                "동서기전",
                "디에이치 테크",
                "디케이에스",
                "래트론",
                "로얄델리스침대",
                "롤텍",
                "마이다스펌프(주)",
                "명승산업",
                "모리스",
                "무진테크",
                "미르테크",
                "민영개발(주)",
                "부석종합건설(주)",
                "부성에버텍(주)",
                "부성에버텍(주)",
                "부일엔지니어링",
                "브이티컴퍼지트(주)",
                "비비씨 주식회사",
                "비씨엔씨(주)",
                "산성정밀 (주)",
                "선인전자",
                "성신아크릴",
                "성일 하이텍",
                "세동정밀주식회사",
                "세신실업주식회사",
                "세이프인",
                "세일산업",
                "세트온",
                "신덕산업(주)",
                "신영델텍주식회사",
                "신일정공",
                "신한이피에스(주)",
                "신한테크(주)",
                "씨엘",
                "아이케미칼(주)",
                "알무스이앤티",
                "알앤티테크",
                "애드바이오텍",
                "에스브이엠테크",
                "에스에이주식회사",
                "에스에이치엘",
                "에스엠테크",
                "에스제이티",
                "에이아이코리아",
                "에이유커머스",
                "에이치앤에이치",
                "엔에스엠시우",
                "엘에스테크",
                "엘테크",
                "엠프로",
                "영남테크",
                "영진전자",
                "영진테크",
                "와이엠아이컴퍼니주식회사",
                "우진금속",
                "원심정공",
                "유.에스.티",
                "유광일렉트로닉스",
                "유림제이에스",
                "유웨이브",
                "인우이엔지",
                "인테크(INTECH)",
                "일성산업",
                "제이아이테크",
                "제이티에스코리아",
                "제일스프링공업주식회사",
                "제일하이텍(주)",
                "젬블로",
                "조양세미켐(주)",
                "조양세미켐(주)",
                "주식회사 뉴온",
                "주식회사 대성로프",
                "주식회사 맥앤로건",
                "주식회사 미래플러스",
                "주식회사 비에스이",
                "주식회사 상아피에스",
                "주식회사 셀랩",
                "주식회사 신원튜브",
                "주식회사 에이원이앤씨",
                "주식회사 엔티엘",
                "주식회사 엘에스신소재",
                "주식회사 엘에스신소재",
                "주식회사 오선",
                "주식회사 위더스",
                "주식회사 이유코리아",
                "주식회사 임창",
                "주식회사 제이에스티",
                "주식회사 제타",
                "주식회사 창문에안전",
                "주식회사 휴먼에이치알",
                "주식회사미준테크",
                "주안이엔지㈜",
                "중앙특장",
                "중앙플랜텍 주식회사",
                "진일부품",
                "청산정밀",
                "카호코리아(주)",
                "케이에프엠",
                "케이와이티",
                "코더엔지니어링",
                "코스텍시스템",
                "태광애드",
                "태광정공",
                "태성금속",
                "태승엔지니어링",
                "태일테크 원",
                "테라세라믹스",
                "특수건설",
                "티에스텍",
                "평창팜",
                "필스전자 주식회사",
                "하나시스템",
                " 한린정밀용접기",
                "한빛정공",
                "한양씨앤씨",
                "한은이엔씨",
                "현민산업",
                "현진테크",
                "혜훈정밀"
        };

        String[] companyBusinessNumberList = {
                "A403-81-80895",
                "A130-86-14788",
                "A130-86-14788",
                "A442-88-00181",
                "A312-81-40374",
                "A615-81-61720",
                "A198-81-00081",
                "A609-81-33710",
                "A114-86-35933",
                "A606-85-46077",
                "A140-81-69515",
                "A607-81-82587",
                "A205-81-36868",
                "A142-81-74595",
                "A137-81-89403",
                "A137-81-60842",
                "A409-86-08300",
                "A409-86-39332",
                "A141-81-14288",
                "A263-81-00347",
                "A119-81-18308",
                "A354-88-00598",
                "A514-81-96979",
                "A108-86-04814",
                "A124-87-31644",
                "A137-81-83393",
                "A131-86-46643",
                "A615-81-55252",
                "A122-86-23495",
                "A613-81-21469",
                "A135-86-23166",
                "A388-81-00476",
                "A104-81-29424",
                "A615-86-00261",
                "A131-81-96319",
                "A129-86-91806",
                "A123-86-06349",
                "A101-86-36391",
                "A123-86-21641",
                "A295-81-00422",
                "A125-86-06326",
                "A215-86-65967",
                "A229-81-12052",
                "A131-81-91518",
                "A606-81-91690",
                "A778-81-00058",
                "A124-86-27459",
                "A126-81-66619",
                "A312-86-19099",
                "A130-81-95151",
                "A503-81-67493",
                "A119-86-01307",
                "A867-88-00089",
                "A613-81-46081",
                "A877-81-00879",
                "A314-81-25990",
                "A128-87-08015",
                "A119-86-78505",
                "A312-81-41316",
                "A615-81-91302",
                "A126-81-44726",
                "A312-86-03660",
                "A312-86-03660",
                "A129-86-58715",
                "A783-87-00504",
                "A132-86-07323",
                "A780-88-00794",
                "A137-86-28056",
                "A105-81-48157",
                "A109-81-75355",
                "A135-86-45777",
                "A312-86-19876",
                "A128-87-03271",
                "A135-86-30084",
                "A124-81-73716",
                "A317-81-39440",
                "A317-81-26678",
                "A124-43-56016",
                "A124-43-56016",
                "A124-81-83999",
                "A124-03-43330",
                "A211-10-17018",
                "A514-81-51399",
                "A305-81-39321",
                "A608-81-63185",
                "A606-30-82369",
                "A606-12-58453",
                "A129-08-74643",
                "A212-81-33699",
                "A224-81-22540",
                "A124-28-79715",
                "A139-81-34511",
                "A126-81-81997",
                "A131-34-27161",
                "A124-47-10696",
                "A613-81-68367",
                "A124-46-25891",
                "A130-03-75332",
                "A137-81-00714",
                "A603-81-47176",
                "A620-21-70775",
                "A124-81-64027",
                "A603-81-18351",
                "A609-81-09165",
                "A621-17-30726",
                "A142-09-18622",
                "A314-81-20879",
                "A615-15-40394",
                "A157-05-00164",
                "A615-86-13131",
                "A608-09-97767",
                "A123-86-16769",
                "A143-81-00764",
                "A301-27-48049",
                "A508-81-20061",
                "A105-86-22263",
                "A143-81-14270",
                "A143-81-14270",
                "A222-01-89387",
                "A109-81-95365",
                "A314-81-98760",
                "A126-81-72260",
                "A312-86-34222",
                "A130-81-77231",
                "A494-86-00359",
                "A520-88-00609",
                "A622-81-15578",
                "A621-81-48421",
                "A142-81-78999",
                "A137-81-69071",
                "A504-81-59318",
                "A128-81-66954",
                "A125-81-15951",
                "A608-81-85398",
                "A137-15-32453",
                "A139-81-43140",
                "A411-81-52366",
                "A514-81-81461",
                "A620-81-27554",
                "A119-81-97657",
                "A234-01-04101",
                "A215-81-93416",
                "A122-86-19123",
                "A129-81-45267",
                "A224-81-07461",
                "A143-01-71449",
                "A329-86-00604",
                "A135-86-46965",
                "A132-81-91336",
                "A393-87-00295",
                "A844-88-00655",
                "A609-21-92108",
                "A124-44-33306",
                "A123-32-84866",
                "A609-04-80279",
                "A119-81-21399",
                "A134-86-81727",
                "A738-87-00498",
                "A603-09-20808",
                "A133-01-30591",
                "A130-35-87687",
                "A312-86-06497",
                "A220-86-05199",
                "A134-86-48179",
                "A606-29-59203",
                "A608-28-62556",
                "A303-30-61830",
                "A401-81-53863",
                "A135-86-22885",
                "A140-81-83986",
                "A606-81-32987",
                "A101-81-93005",
                "A215-81-82485",
                "A130-86-69361",
                "A121-86-12073",
                "A211-88-23974",
                "A611-81-16484",
                "A124-87-23479",
                "A621-81-75743",
                "A130-81-36865",
                "A126-81-80120",
                "A137-86-16676",
                "A124-86-32059",
                "A268-87-00567",
                "A268-87-00567",
                "A283-81-00278",
                "A688-81-00357",
                "A310-88-00133",
                "A234-81-06907",
                "A124-87-05845",
                "A134-86-19577",
                "A140-81-83822",
                "A412-87-01066",
                "A140-81-79560",
                "A129-86-45355",
                "A136-05-64083",
                "A609-81-75743",
                "A122-81-20305",
                "A609-44-60570",
                "A312-86-16957",
                "A311-81-45304",
                "A140-04-16303",
                "A109-81-68586",
        };

        ArrayList<String> unitData = new ArrayList<String>(Arrays.asList(
                "장착부",
                "제어부",
                "투입부",
                "용접부",
                "주입부",
                "측정부",
                "정렬부",
                "취출부",
                "관제부",
                "압착부",
                "절단부",
                "충전부"
        ));

        ArrayList<String> assyData = new ArrayList<String>(Arrays.asList(
                "감지기",
                "고정대",
                "제어판 이슈",
                "MOTOR",
                "구동축",
                "실린더CYLINDER",
                "LASER",
                "SERVOMOTOR",
                "PUMP",
                "전자저울WEIGHIGINDICATOR",
                "커넥터",
                "투영기",
                "iNDEXUNiTDRiVE",
                "여과장치",
                "TOUTCHSCREEN",
                "이동 컨베이어",
                "PULLEY",
                "VSCONTROLLER",
                "컴퓨터COMPUTER",
                "Heater",
                "robot arm",
                "POWERSUPPLY",
                "CableHeader접속부포함",
                "PUSHER"
        ));

        ArrayList<String> partData = new ArrayList<String>(Arrays.asList(
                "SENSOR",
                "BRACKET",
                "릴레이Relay",
                "GEAR",
                "솔레노이드밸브SolenoidValve",
                "FLASHLAMP",
                "PLC",
                "SERVOCONTROLLER",
                "엔코더Encoder",
                "BEARiNG",
                "CABLE",
                "Transmission변감속기",
                "JIG",
                "BOLTNUT",
                "FITTING류",
                "HOSE",
                "CHAiN",
                "BELT",
                "CLUTCHBRAKE",
                "GUIDE",
                "STOPPER",
                "진공밸부VacuumValve",
                "FILTERHOUSING",
                "퓨즈Fuse",
                "SHAFT",
                "CUTTER",
                "COUPLiNG",
                "PiN",
                "TRAY",
                "GRIPPER",
                "VACUUMPAD",
                "NOZZLE",
                "FLOATiNGJOiNT"
                ));


            /*중복 데이터 필터기 */
//        List<String> newList = partData.stream().distinct().collect(Collectors.toList());
//        newList.forEach(System.out::println);


        /*-------------대중소 테이블 저장--------------------*/

        for (String p : partData) {
            if (p.equals("FLASHLAMP")) {
                String a = "LASER";
                String u = "용접부";
                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            } else if (p.equals("진공밸부VacuumValve")) {
                String a = "PUMP";
                String u = "주입부";

                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            } else if (p.equals("CUTTER") || p.equals("GRIPPER")) {
                String a = "robot arm";
                String u = "취출부";
                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();

                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            } else if (p.equals("SENSOR") || p.equals("솔레노이드밸브SolenoidValve") || p.equals("SHAFT")) {
                String a = "감지기";
                String u = "장착부";

                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();

                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);
            } else if (p.equals("BRACKET") || p.equals("JIG") || p.equals("FITTING류") || p.equals("BOLTNUT") || p.equals("PiN") || p.equals("FLOATiNGJOiNT")) {
                String a = "고정대";
                String u = "장착부";

                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            } else if (p.equals("SERVOCONTROLLER") || p.equals("엔코더Encoder")) {
                String a = "SERVOMOTOR";
                String u = "주입부";

                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            } else if (p.equals("CLUTCHBRAKE") || p.equals("BEARiNG") || p.equals("GEAR")) {
                String a = "구동축";
                String u = "투입부";
                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);
            } else if (p.equals("솔레노이드밸브SolenoidValve") || p.equals("HOSE") || p.equals("FILTERHOUSING") || p.equals("COUPLiNG")) {
                String a = "여과장치";
                String u = "주입부";

                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            } else if (p.equals("CHAiN") || p.equals("BELT") || p.equals("GUIDE") || p.equals("TRAY") || p.equals("STOPPER")) {
                String a = "이동 컨베이어";
                String u = "취출부";
                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            } else if (p.equals("릴레이Relay") || p.equals("PLC")) {
                String a = "제어판 이슈";
                String u = "제어부";
                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);
            } else if (p.equals("CABLE") || p.equals("NOZZLE")) {
                String a = "커넥터";
                String u = "투입부";
                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);

            } else if (p.equals("Transmission변감속기") || p.equals("퓨즈Fuse")) {
                String a = "투영기";
                String u = "정렬부";
                Unit unit = Unit.builder()
                        .unit(u)
                        .build();
                Assy assy = Assy.builder()
                        .unit(unit)
                        .assy(a)
                        .build();
                Part part = Part.builder()
                        .part(p)
                        .assy(assy)
                        .build();
                unitRepository.save(unit);
                assyRepository.save(assy);
                partRepository.save(part);

            }

//            //회사 정보 데이터
//            for (int value = 0; value < companyBusinessNumberList.length; value++) {
//                CompanyDTO companyDTO = CompanyDTO.builder()
//                        .businessNumber(companyBusinessNumberList[value])
//                        .businessName(nameData[value])
//                        .departName(companyNameData[value])
//                        .businessEmail(value + "@naver.com")
//                        .businessTel(value + "-" + value + "-" + value)
//                        .fax(value + "")
//                        .build();
//                Company company = companyDTO.com();
//                companyRepository.save(company);
//            }

        }
    }
}
