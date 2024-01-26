var state;

function Tradingmy(event){  //모달창열기
//    console.log("트레딩모달 버튼 클릭됨");
    $(".TradingmyModal").css('display','block');

//    var clickedElement = event.target;
//    console.log("ID: " + clickedElement.id);

    $.ajax({
        url:'TradingStatementModal?uuid='+event,  // 'ida' 대신 'uuid'로 수정
        type: 'get',
        success: function(asdasData) {
            console.log("잘보냄");
             $('#externalPageContent').html(asdasData);
            // console.log(asdasData);
        },
        error: function(error) {
            console.log("오류발생 0");
        }
    });

}


function CloseTradingMy(){  //모달창열기
//    console.log("모달콘튼츠창클릭 백그라운드 클릭");
    $(".TradingmyModal").css('display','none');
}





function searchTSButton(){ //검색 정보를 넣고 검색요청 검색된 내용을 그려줌
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

        url:"TSSearch?inputData="+formInputData+"&searchData="+formsearchData+"&state="+state ,
        type : 'get',
        success: function(searchTSList) {
            console.log("거래명세서 검색용 에이작스 잘보냄");
            console.log("searchTSList 여기에 든내용",searchTSList);
            console.log("searchTSList에 나온 리스트의 크기",searchTSList.tsDTO.length)
            var tableBody = document.getElementById('tradingStatementTableBody');//DOM 요소 가져와서
            tableBody.innerHTML = '';//내부에 있는 내용을 비워주고

            for (var i=0; i<searchTSList.tsDTO.length; i++){ //리스트 크기만큼 반복

                var tableBodyRow = document.createElement('tr');

                var ordercodeCell = document.createElement('td');
                ordercodeCell.classList.add('table-data');
                ordercodeCell.innerHTML = searchTSList.tsDTO[i].orderCode; //발주서 코드
                tableBodyRow.appendChild(ordercodeCell);

                var businessNumberCell = document.createElement('td');
                businessNumberCell.classList.add('table-data');
                businessNumberCell.innerHTML = searchTSList.tsDTO[i].businessNumber; //사업자번호
                tableBodyRow.appendChild(businessNumberCell);

                var departNameCell = document.createElement('td');
                departNameCell.classList.add('table-data');
                departNameCell.innerHTML = searchTSList.tsDTO[i].departName; //업체명
                tableBodyRow.appendChild(departNameCell);
/////해야됨
                var prouctNameCell = document.createElement('td');
                prouctNameCell.classList.add('table-data');
                prouctNameCell.innerHTML = searchTSList.tsDTO[i].prouctName; //입고품목명
                tableBodyRow.appendChild(prouctNameCell);

                var countCell = document.createElement('td');
                countCell.classList.add('table-data');
                countCell.innerHTML = searchTSList.tsDTO[i].count; //입고수량
                tableBodyRow.appendChild(countCell);

                var ArrivalCell = document.createElement('td');
                ArrivalCell.classList.add('table-data');
                ArrivalCell.innerHTML = searchTSList.tsDTO[i].arrival.toString(); //입고일
                tableBodyRow.appendChild(ArrivalCell);

                var businessTelCell = document.createElement('td');
                businessTelCell.classList.add('table-data');
                businessTelCell.innerHTML = searchTSList.tsDTO[i].businessTel; //업체전화번호
                tableBodyRow.appendChild(businessTelCell);

                var businessEmailCell = document.createElement('td');
                businessEmailCell.classList.add('table-data');
                businessEmailCell.innerHTML = searchTSList.tsDTO[i].businessEmail; //업체이메일
                tableBodyRow.appendChild(businessEmailCell);

                var faxCell = document.createElement('td');
                faxCell.classList.add('table-data');
                faxCell.innerHTML = searchTSList.tsDTO[i].fax; //팩스
                tableBodyRow.appendChild(faxCell);

                var sendAndPrintCell = document.createElement('td');
                sendAndPrintCell.classList.add('table-data');
                sendAndPrintCell.innerHTML = "🔍"; //전송및 인쇄
                sendAndPrintCell.id = searchTSList.tsDTO[i].orderCode;
                sendAndPrintCell.onclick = function (){
                    console.log("거래명세서의 돋보기클릭됨?");
                    var cc = event.target;
                    console.log("거래명세서의 아이디의 값===>",cc.getAttribute('id'));
                    Tradingmy(cc.getAttribute('id')); //숫자를 sendData에 넣어주고
                }
                tableBodyRow.appendChild(sendAndPrintCell);


                tableBody.appendChild(tableBodyRow);   //행을 TbodTbodydp
            }

        },
        error: function(error) {
            console.log("오류발생 0");
        }
    });
}


