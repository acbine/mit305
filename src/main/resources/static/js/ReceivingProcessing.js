var state; //페이지 상태값

$(function ReceivingProcessing(){  //모달 관련 js
    $("#modalBTN").on("click",function(){
        console.log("모달 버튼 클릭됨");
        $("#myModal").css('display','block');
    });

    $(".button-style").on("click",function(){
        console.log("모달 닫기 클릭됨");
        $("#myModal").css('display','none');
    });


    $('.modal-content').on('click',function(){
        console.log("모달콘튼츠창클릭 백그라운드 클릭");
    });

    $("submitBTN").on('click',function(){
        console.log("모달창 안에서 데이터 전송함");
        $("#myModal").css('display','block');
    });


    const table = $('.table-style');
    const headerRow = table.find('thead tr');


    $(".table-container").on("scroll",function(){
        console.log("테이블콘테이너 스크로됨");


    });
});

 function sendData(Codeprocurementplan){
//    console.log("클릭은 되니?");
    var dos=document.getElementById("code"+Codeprocurementplan); // 조달계획 요소
    console.log("조달계획 코드 맨처음의 조달계획 코드"+Codeprocurementplan);
    var store=document.getElementById("store"+Codeprocurementplan); //input 넣고  ID

    console.log("=========입력한 값==============",store.value); //입력한 값보기 입혁한 수만큼 잘들어옴
    if (store.value === ""  ) {
        alert("값을 넣어주세요");
    }else{
        $.ajax({
                url:'ReceivingProcessStore?procurementplan_code='+Codeprocurementplan+'&store='+store.value+"&state="+state,
                type : 'get',
                success: function(ReceivingProcessStoreData) {
                    console.log("전송잘됨");
//                    console.log(ReceivingProcessStoreData);
                    console.log("ReceivingProcessStoreData.receivingProcessingDTOList 이거의 리스트 길이",ReceivingProcessStoreData.receivingProcessingDTOList.length)
                    var tableBody = document.getElementById('tableBody');//DOM 요소 가져와서
                    tableBody.innerHTML = '';//내부에 있는 내용을 비워주고

                    for (var i=0; i<ReceivingProcessStoreData.receivingProcessingDTOList.length; i++){ //리스트 크기만큼 반복

                        var Codeprocurementplan = ReceivingProcessStoreData.receivingProcessingDTOList[i].procurementplan_code;
                        var a = 1;
                        var tableBodyRow = document.createElement('tr');

                        var ordercodeCell = document.createElement('td');
                        ordercodeCell.classList.add('table-data');
                        ordercodeCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].ordercode; //발주서 코드
                        tableBodyRow.appendChild(ordercodeCell);




                        var procurementplan_codeCell = document.createElement('td');
                        procurementplan_codeCell.classList.add('table-data');
                        procurementplan_codeCell.setAttribute('hidden', 'hidden');
                        procurementplan_codeCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].procurementplan_code; //조달계획코드
                        tableBodyRow.appendChild(procurementplan_codeCell);


                        var productcodeCell = document.createElement('td');
                        productcodeCell.classList.add('table-data');
                        productcodeCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].productcode; //제품코드
                        tableBodyRow.appendChild(productcodeCell);

                        var productnameCell = document.createElement('td');
                        productnameCell.classList.add('table-data');
                        productnameCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].productname; //제품이름
                        tableBodyRow.appendChild(productnameCell);

                        var departNameCell = document.createElement('td');
                        departNameCell.classList.add('table-data');
                        departNameCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].departName; //업체명
                        tableBodyRow.appendChild(departNameCell);

                        var businessNumberCell = document.createElement('td');
                        businessNumberCell.classList.add('table-data');
                        businessNumberCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].businessNumber; //사업자번호
                        tableBodyRow.appendChild(businessNumberCell);

                        var dateOfOrderCell = document.createElement('td');
                        dateOfOrderCell.classList.add('table-data');
                        dateOfOrderCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].dateOfOrder; //발주서 발해일
                        tableBodyRow.appendChild(dateOfOrderCell);

                        var orderDateCell = document.createElement('td');
                        orderDateCell.classList.add('table-data');
                        orderDateCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].orderDate; //발주일
                        tableBodyRow.appendChild(orderDateCell);

                        var ArrivalCell = document.createElement('td'); ////////////////////////////////////////////////입고일
                        ArrivalCell.classList.add('table-data');
                        if(ReceivingProcessStoreData.receivingProcessingDTOList[i].arrival.toLocaleString() === '0001-01-01T00:00:00'){
//                            console.log("일치함")
                            ArrivalCell.innerHTML ='입고처리하지 않음';
                        }else{
//                            console.log("불일치")
                            ArrivalCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].arrival.toLocaleString();
                        }
//                        console.log(ArrivalCell.innerHTML);
                        tableBodyRow.appendChild(ArrivalCell);

                        var supportProductAmountCell = document.createElement('td');
                        supportProductAmountCell.classList.add('table-data');
                        supportProductAmountCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].supportProductAmount; //조달 수량
                        tableBodyRow.appendChild(supportProductAmountCell);
//// 입고처리 인푹박스 시작
                        var storeCell = document.createElement('td');
                        storeCell.classList.add('table-data');

                        var inputElement = document.createElement('input');
                        inputElement.type = 'number';
                        inputElement.name = 'store';
                        inputElement.id = 'store'+ReceivingProcessStoreData.receivingProcessingDTOList[i].procurementplan_code; //인풋박스
                        storeCell.appendChild(inputElement);

                        tableBodyRow.appendChild(storeCell);
////입고처리 인풋박스 끝
                        var orderStateCell = document.createElement('td');
                        orderStateCell.classList.add('table-data');
                        orderStateCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].orderState; //품목 상태
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
                         buttonElement.onclick = function (){
                            console.log("잘클릭고됨")
                            console.log(inputElement);
                            console.log("값 받아오는지 확인", typeof Codeprocurementplan.toString())
                            sendData(Codeprocurementplan.toString()); //실행될함수
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
    var formInputData=document.getElementById("inputData").value;
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
//    console.log("최종상태",state)
    $.ajax({

        url:"rPSearch?inputData="+formInputData+"&searchData="+formsearchData+"&state="+state ,
        type : 'get',
        success: function(searchList) {
            console.log("검색용 에이작스 잘보냄");
            console.log(searchList);
            console.log(searchList.searchDTOList.length)
            var tableBody = document.getElementById('tableBody');//DOM 요소 가져와서
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
                productcodeCell.innerHTML = searchList.searchDTOList[i].productcode; //제품코드
                tableBodyRow.appendChild(productcodeCell);

                var productnameCell = document.createElement('td');
                productnameCell.classList.add('table-data');
                productnameCell.innerHTML = searchList.searchDTOList[i].productname; //제품이름
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
                 console.log(searchList.searchDTOList[i].procurementplan_code,"인풋에 있는것=======================");
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
                 console.log(searchList.searchDTOList[i].procurementplan_code,"버튼 위치에 있는것 =======================");
                 buttonElement.style.backgroundColor = 'blue';
                 buttonElement.style.color = 'white';
                 buttonElement.id = searchList.searchDTOList[i].procurementplan_code;
//                 console.log(document.getElementById(searchList.searchDTOList[i].procurementplan_code))
                 buttonElement.classList = "receivingInput";
                 buttonElement.innerText = '입고처리';
                 buttonElement.onclick = function (e){
                    onCLickFn();
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


function onCLickFn() {
     var inputButton = document.getElementsByClassName("receivingInput");

    for(let j = 0 ; j<inputButton.length; j++) {
    inputButton[j].addEventListener('click', function (e) {
     e.preventDefault();
     console.log("---------------------",j)
      var code = inputButton[j].getAttribute('id');
       console("-----------------",code);

                }
            )
        }

}
