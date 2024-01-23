

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

 function sendData(procurementplanCode){
//    console.log("클릭은 되니?");
    var dos=document.getElementById("code"+procurementplanCode); // 조달계획 요소

    var store=document.getElementById("store"+procurementplanCode); //input 넣고  ID

    var deleteRow = document.getElementById("row"+procurementplanCode);//삭제하기위한 행의 아이디
    console.log(deleteRow);
//    console.log(store.value); //입력한 값보기 입혁한 수만큼 잘들어옴
    if (store.value === ""  ) {
        alert("값을 넣어주세요");
    }else{
//        sendData();
        $.ajax({
                url:'ReceivingProcessStore?procurementplan_code='+procurementplanCode+'&store='+store.value,
                type : 'get',
                success: function(ReceivingProcessStoreData) {
                    deleteRow.parentNode.removeChild(deleteRow)
//                    console.log("전송잘됨");
//                    console.log(ReceivingProcessStoreData);
//                    console.log(ReceivingProcessStoreData.receivingProcessingDTOList.length)

//                    var tableBody = document.getElementById('tableBody');//DOM 요소 가져와서

//                    console.log("안녕");
//                    tableBody.innerHTML = '';//내부에 있는 내용을 비워주고
//
//                    for (var i=0; i<ReceivingProcessStoreData.receivingProcessingDTOList.length; i++){ //리스트 크기만큼 반복
//
//                        var tableBodyRow = document.createElement('tr');
//
//                        var productcodeCell = document.createElement('td');
//                        productcodeCell.classList.add('table-data');
//                        productcodeCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].productcode;
//                        tableBodyRow.appendChild(productcodeCell);
//
//                        var productnameCell = document.createElement('td');
//                        productnameCell.classList.add('table-data');
//                        productnameCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].productname;
//                        tableBodyRow.appendChild(productnameCell);
//
//                        var departNameCell = document.createElement('td');
//                        departNameCell.classList.add('table-data');
//                        departNameCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].departName;
//                        tableBodyRow.appendChild(departNameCell);
//
//                        var businessNumberCell = document.createElement('td');
//                        businessNumberCell.classList.add('table-data');
//                        businessNumberCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].businessNumber;
//                        tableBodyRow.appendChild(businessNumberCell);
//
//                        var dateOfOrderCell = document.createElement('td');
//                        dateOfOrderCell.classList.add('table-data');
//                        dateOfOrderCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].dateOfOrder;
//                        tableBodyRow.appendChild(dateOfOrderCell);
//
//                        var orderDateCell = document.createElement('td');
//                        orderDateCell.classList.add('table-data');
//                        orderDateCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].orderDate;
//                        tableBodyRow.appendChild(orderDateCell);
//
//                        var ArrivalCell = document.createElement('td');
//                        ArrivalCell.classList.add('table-data');
//                        if(ReceivingProcessStoreData.receivingProcessingDTOList[i].arrival.toLocaleString() === '0001-01-01T00:00:00'){
////                            console.log("일치함")
//                            ArrivalCell.innerHTML ='입고처리하지 않음';
//                        }else{
////                            console.log("불일치")
//                            ArrivalCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].arrival.toLocaleString();
//                        }
////                        console.log(ArrivalCell.innerHTML);
//                        tableBodyRow.appendChild(ArrivalCell);
//
//                        var supportProductAmountCell = document.createElement('td');
//                        supportProductAmountCell.classList.add('table-data');
//                        supportProductAmountCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].supportProductAmount;
//                        tableBodyRow.appendChild(supportProductAmountCell);
// 입고처리 인푹박스
//                        var storeCell = document.createElement('td');
//                        storeCell.classList.add('table-data');
//
//                        var inputElement = document.createElement('input');
//                        inputElement.type = 'number';
//                        inputElement.name = 'store';
//                        inputElement.id = 'code'+ReceivingProcessStoreData.receivingProcessingDTOList[i].procurementplan_code;
//                        storeCell.appendChild(inputElement);
//
//                        tableBodyRow.appendChild(storeCell);
////입고처리 인풋박스
//                        var orderStateCell = document.createElement('td');
//                        orderStateCell.classList.add('table-data');
//                        orderStateCell.innerHTML = ReceivingProcessStoreData.receivingProcessingDTOList[i].orderState;
//                        tableBodyRow.appendChild(orderStateCell);
//
//                        tableBody.appendChild(tableBodyRow);
//
////버튼 의요소추가
//                        var buttonCell = document.createElement('td');
//                        orderStateCell.classList.add('table-data');
////버튼생성
//                         var buttonElement = document.createElement('button');
//                         buttonElement.style.backgroundColor = 'blue';
//                         buttonElement.style.color = 'white';
//                         buttonElement.innerText = '입고처리';
//                         buttonElement.onclick = function(){
//                            console.log("잘클릭고됨")
//                            console.log(inputElement);
////                            sendData(ReceivingProcessStoreData.receivingProcessingDTOList[i].procurementplan_code); //실행될함수
//                         }
//
//
//                         buttonCell.appendChild(buttonElement); //요소에 버튼 추가
//                         tableBodyRow.appendChild(buttonCell); //행안에 요소추가
//
//
//                         tableBody.appendChild(tableBodyRow);   //행을 TbodTbodydp
//
//
//
//                    }
                },
                error: function(error) {
                    console.log("오류발생 0");
                }
            });
//            sendData();
    }


 }

//function searchButton(){
//    console.log("잘클릭");
//    var formInputData=document.getElementById("inputData");
//    var formsearchData=document.getElementById("searchData");
//    var formsearchState=document.getElementById("searchState");
//    console.log(formInputData.value);
//    console.log(formsearchData.value);
//    console.log(formsearchState.value);
// }

