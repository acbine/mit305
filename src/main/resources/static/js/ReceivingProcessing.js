var state; //페이지 상태값
var formInputData; // 검색 데이터

// $(function ReceivingProcessing(){  //모달 관련 js
//     $("#modalBTN").on("click",function(){
//         console.log("모달 버튼 클릭됨");
//         $("#myModal").css('display','block');
//     });
//
//     $(".button-style").on("click",function(){
//         console.log("모달 닫기 클릭됨");
//         $("#myModal").css('display','none');
//     });
//
//
//     $('.modal-content').on('click',function(){
//         console.log("모달콘튼츠창클릭 백그라운드 클릭");
//     });
//
//     $("submitBTN").on('click',function(){
//         console.log("모달창 안에서 데이터 전송함");
//         $("#myModal").css('display','block');
//     });
//
//
//     const table = $('.table-style');
//     const headerRow = table.find('thead tr');
//
//
//     $(".table-container").on("scroll",function(){
//         console.log("테이블콘테이너 스크로됨");
//
//
//     });
// });

function sendData(Codeprocurementplan){
//    console.log("클릭은 되니?");
    var dos=document.getElementById(Codeprocurementplan); // 조달계획 요소
    console.log("Send DATA 함수 실행시 낭오는 페이지 상태값  0  =>> 전체   1 ==>> 품목명   2 ==>> 업체명     =>>>>>>>>>>나온 상태값:",state)
    console.log("Send DATA 함수 실행시 나오는 formInputData 데이터값    =>>>>>>>>>>",formInputData)
    console.log(" SENDDATE 의 함수->   조달계획 코드 맨처음의 조달계획 코드"+Codeprocurementplan);
    var store=document.getElementById("store"+Codeprocurementplan); //input 넣고  ID

    console.log("=========입력한 값==============",store.value); //입력한 값보기 입혁한 수만큼 잘들어옴
    if (store.value === ""  ) {
        alert("값을 넣어주세요");
    }else{
        $.ajax({
                url:'ReceivingProcessStore?procurementplan_code='+Codeprocurementplan+'&store='+store.value+"&state="+state+"&formInputData="+formInputData,
                type : 'get',
                success: function(inSTRData) {
                    console.log("전송잘됨");
//                    console.log(ReceivingProcessStoreData);
                    console.log("ReceivingProcessStoreData.rProcessList 이거의 리스트 길이",inSTRData.rProcessList.length)
                    var tableBody = document.getElementById('ReceivingTableBody');//DOM 요소 가져와서
                    tableBody.innerHTML = '';//내부에 있는 내용을 비워주고

                    for (var i=0; i<inSTRData.rProcessList.length; i++){ //리스트 크기만큼 반복

                        var Codeprocurementplan = inSTRData.rProcessList[i].procurementplan_code;
                        var a = 1;
                        var tableBodyRow = document.createElement('tr');

                        var ordercodeCell = document.createElement('td');
                        ordercodeCell.classList.add('table-data');
                        ordercodeCell.innerHTML = inSTRData.rProcessList[i].ordercode; //발주서 코드
                        tableBodyRow.appendChild(ordercodeCell);




                        var procurementplan_codeCell = document.createElement('td');
                        procurementplan_codeCell.classList.add('table-data');
                        procurementplan_codeCell.setAttribute('hidden', 'hidden');
                        procurementplan_codeCell.innerHTML = inSTRData.rProcessList[i].procurementplan_code; //조달계획코드
                        tableBodyRow.appendChild(procurementplan_codeCell);


                        var productcodeCell = document.createElement('td');
                        productcodeCell.classList.add('table-data');
                        productcodeCell.innerHTML = inSTRData.rProcessList[i].productcode; //제품코드
                        tableBodyRow.appendChild(productcodeCell);

                        var productnameCell = document.createElement('td');
                        productnameCell.classList.add('table-data');
                        productnameCell.innerHTML = inSTRData.rProcessList[i].productname; //제품이름
                        tableBodyRow.appendChild(productnameCell);

                        var departNameCell = document.createElement('td');
                        departNameCell.classList.add('table-data');
                        departNameCell.innerHTML = inSTRData.rProcessList[i].departName; //업체명
                        tableBodyRow.appendChild(departNameCell);

                        var businessNumberCell = document.createElement('td');
                        businessNumberCell.classList.add('table-data');
                        businessNumberCell.innerHTML = inSTRData.rProcessList[i].businessNumber; //사업자번호
                        tableBodyRow.appendChild(businessNumberCell);

                        var dateOfOrderCell = document.createElement('td');
                        dateOfOrderCell.classList.add('table-data');
                        dateOfOrderCell.innerHTML = inSTRData.rProcessList[i].dateOfOrder; //발주서 발해일
                        tableBodyRow.appendChild(dateOfOrderCell);

                        var orderDateCell = document.createElement('td');
                        orderDateCell.classList.add('table-data');
                        orderDateCell.innerHTML = inSTRData.rProcessList[i].orderDate; //발주일
                        tableBodyRow.appendChild(orderDateCell);

                        var ArrivalCell = document.createElement('td'); ////////////////////////////////////////////////입고일
                        ArrivalCell.classList.add('table-data');
                        if(inSTRData.rProcessList[i].arrival.toLocaleString() === '0001-01-01T00:00:00'){
//                            console.log("일치함")
                            ArrivalCell.innerHTML ='입고처리하지 않음';
                        }else{
//                            console.log("불일치")
                            ArrivalCell.innerHTML = inSTRData.rProcessList[i].arrival.toLocaleString();
                        }
//                        console.log(ArrivalCell.innerHTML);
                        tableBodyRow.appendChild(ArrivalCell);

                        var supportProductAmountCell = document.createElement('td');
                        supportProductAmountCell.classList.add('table-data');
                        supportProductAmountCell.innerHTML = inSTRData.rProcessList[i].supportProductAmount; //조달 수량
                        tableBodyRow.appendChild(supportProductAmountCell);
//// 입고처리 인푹박스 시작
                        var storeCell = document.createElement('td');
                        storeCell.classList.add('table-data');

                        var inputElement = document.createElement('input');
                        inputElement.type = 'number';
                        inputElement.name = 'store';
                        inputElement.id = 'store'+inSTRData.rProcessList[i].procurementplan_code; //인풋박스
                        storeCell.appendChild(inputElement);

                        tableBodyRow.appendChild(storeCell);
////입고처리 인풋박스 끝
                        var orderStateCell = document.createElement('td');
                        orderStateCell.classList.add('table-data');
                        orderStateCell.innerHTML = inSTRData.rProcessList[i].orderState; //품목 상태
                        tableBodyRow.appendChild(orderStateCell);

                        tableBody.appendChild(tableBodyRow);

////버튼 의요소추가
                        var buttonCell = document.createElement('td');
                        orderStateCell.classList.add('table-data');
////버튼생성
                        var buttonElement = document.createElement('button');
                        buttonElement.style.backgroundColor = 'blue';
                        buttonElement.style.color = 'white';
                        buttonElement.innerText = '입고처리';
                        buttonElement.id = inSTRData.rProcessList[i].procurementplan_code;
//                        console.log("생성된 버튼의 ID",ReceivingProcessStoreData.rProcessList[i].procurementplan_code)
//                        console.log("------------------클릭되면 실행되게----대기중------")
                        buttonElement.onclick = function (){

                            var aa = event.target;
//                            console.log("SENDDATA 입고처리한후 다시 화면글 그린곳에서 클릭시 클릭된 것 아이디값 가져오기",aa.getAttribute('id'));

                            sendData(aa.getAttribute('id')); //실행될함수
                        }


                        buttonCell.appendChild(buttonElement); //요소에 버튼 추가
                        tableBodyRow.appendChild(buttonCell); //행안에 요소추가


                        tableBody.appendChild(tableBodyRow);   //행을 TbodTbodydp



                    }
                },
                error: function(error) {
                    console.log("오류발생 0");
                }
            });
    }


}

function searchButton(){ //검색 정보를 넣고 검색요청 검색된 내용을 그려줌
    console.log("검색 버튼 잘 클릭");
    formInputData=document.getElementById("inputData").value;
    var formsearchData=document.getElementById("searchData").value;
    state=0; //전체
    console.log(formInputData);
    console.log(formsearchData);
    if(formInputData==""){
//        console.log("formInputData=== 이값이 빔 =>");
    }else{
//        console.log("값이 존재함")
        if(formsearchData=="productname"){
//            console.log("품목이름")
            state=1;
        }else{
//            console.log("업체이름")
            state=2;
        }
    }
    console.log("검색 요청후 나온 --------------->최종상태",state)
    console.log("검색 요청후 나온 --------------->검색내용?",formInputData)

    $.ajax({

        url:"rPSearch?inputData="+formInputData+"&searchData="+formsearchData+"&state="+state ,
        type : 'get',
        success: function(searchList) {
            console.log("검색용 에이작스 잘보냄");
            console.log(searchList);
            console.log(searchList.searchDTOList.length)
            var tableBody = document.getElementById('ReceivingTableBody');//DOM 요소 가져와서
            tableBody.innerHTML = '';//내부에 있는 내용을 비워주고

            for (var i=0; i<searchList.searchDTOList.length; i++){ //리스트 크기만큼 반복

                var procurementplanCode = searchList.searchDTOList[i].procurementplan_code;
                var a = 1;
                var tableBodyRow = document.createElement('tr');

                var ordercodeCell = document.createElement('td');
                ordercodeCell.classList.add('table-data');
                ordercodeCell.innerHTML = searchList.searchDTOList[i].ordercode; //발주서 코드
                tableBodyRow.appendChild(ordercodeCell);




                var procurementplan_codeCell = document.createElement('td');
                procurementplan_codeCell.classList.add('table-data');
                procurementplan_codeCell.setAttribute('hidden', 'hidden');
                procurementplan_codeCell.id = 'code' +searchList.searchDTOList[i].procurementplan_code;
                procurementplan_codeCell.innerHTML = searchList.searchDTOList[i].procurementplan_code; //조달계획코드
                tableBodyRow.appendChild(procurementplan_codeCell);


                var productcodeCell = document.createElement('td');
                productcodeCell.classList.add('table-data');
                productcodeCell.innerHTML = searchList.searchDTOList[i].productcode; //품목코드
                tableBodyRow.appendChild(productcodeCell);

                var productnameCell = document.createElement('td');
                productnameCell.classList.add('table-data');
                productnameCell.innerHTML = searchList.searchDTOList[i].productname; //품목이름
                tableBodyRow.appendChild(productnameCell);

                var departNameCell = document.createElement('td');
                departNameCell.classList.add('table-data');
                departNameCell.innerHTML = searchList.searchDTOList[i].departName; //업체명
                tableBodyRow.appendChild(departNameCell);

                var businessNumberCell = document.createElement('td');
                businessNumberCell.classList.add('table-data');
                businessNumberCell.innerHTML = searchList.searchDTOList[i].businessNumber; //사업자번호
                tableBodyRow.appendChild(businessNumberCell);

                var dateOfOrderCell = document.createElement('td');
                dateOfOrderCell.classList.add('table-data');
                dateOfOrderCell.innerHTML = searchList.searchDTOList[i].dateOfOrder; //발주서 발해일
                tableBodyRow.appendChild(dateOfOrderCell);

                var orderDateCell = document.createElement('td');
                orderDateCell.classList.add('table-data');
                orderDateCell.innerHTML = searchList.searchDTOList[i].orderDate; //발주일
                tableBodyRow.appendChild(orderDateCell);

                var ArrivalCell = document.createElement('td'); ////////////////////////////////////////////////입고일
                ArrivalCell.classList.add('table-data');
                if(searchList.searchDTOList[i].arrival.toLocaleString() === '0001-01-01T00:00:00'){
//                            console.log("일치함")
                    ArrivalCell.innerHTML ='입고처리하지 않음';
                }else{
//                            console.log("불일치")
                    ArrivalCell.innerHTML = searchList.searchDTOList[i].arrival.toLocaleString();
                }
//                        console.log(ArrivalCell.innerHTML);
                tableBodyRow.appendChild(ArrivalCell);

                var supportProductAmountCell = document.createElement('td');
                supportProductAmountCell.classList.add('table-data');
                supportProductAmountCell.innerHTML = searchList.searchDTOList[i].supportProductAmount; //조달 수량
                tableBodyRow.appendChild(supportProductAmountCell);
//// 입고처리 인푹박스 시작
                var storeCell = document.createElement('td');
                storeCell.classList.add('table-data');

                var inputElement = document.createElement('input');
                inputElement.type = 'number';
                inputElement.name = 'store';
                inputElement.id = 'store'+searchList.searchDTOList[i].procurementplan_code; //인풋박스
//                console.log(searchList.searchDTOList[i].procurementplan_code,"인풋에 있는것=======================");
                storeCell.appendChild(inputElement);

                tableBodyRow.appendChild(storeCell);
////입고처리 인풋박스 끝
                var orderStateCell = document.createElement('td');
                orderStateCell.classList.add('table-data');
                orderStateCell.innerHTML = searchList.searchDTOList[i].orderState; //품목 상태
                tableBodyRow.appendChild(orderStateCell);

                tableBody.appendChild(tableBodyRow);

////버튼 의요소추가
                var buttonCell = document.createElement('td');
                orderStateCell.classList.add('table-data');
////버튼생성
                var buttonElement = document.createElement('button');
//                console.log(searchList.searchDTOList[i].procurementplan_code,"버튼 위치에 있는것 =======================");
                buttonElement.style.backgroundColor = 'blue';
                buttonElement.style.color = 'white';
                buttonElement.id = searchList.searchDTOList[i].procurementplan_code;
//                 console.log(document.getElementById(searchList.searchDTOList[i].procurementplan_code))
                buttonElement.classList = "receivingInput";
                buttonElement.innerText = '입고처리';
                buttonElement.onclick = function (){
                 //해당 버튼의 i값 읽기

                var bb = event.target;
                console.log(bb.getAttribute('id'));
                sendData(bb.getAttribute('id')); //숫자를 sendData에 넣어주고

                }


                buttonCell.appendChild(buttonElement); //요소에 버튼 추가
                tableBodyRow.appendChild(buttonCell); //행안에 요소추가

                tableBody.appendChild(tableBodyRow);   //행을 TbodTbodydp

            }

        },
        error: function(error) {
            console.log("오류발생 0");
        }


    });




}
