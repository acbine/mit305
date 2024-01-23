
google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

//        console.log("drawChart() 작동")
    var startDateInput=document.getElementById('startDate'); // HTML 요소에 접근
    var endDateInput=document.getElementById('endDate');// 요소에 접근에 뒤에 값찍을려면.value

    $.ajax({
        url:'groupby?startDate='+startDateInput.value+'&endDate='+endDateInput.value,
        type : 'get',
        success: function(groupByOrderState) {
//                console.log("데이터 잘받음");
//                console.log(groupByOrderState.statGroupby);

                var InputArray = [['orderState', 'count']]
//                console.log(InputArray);

                groupByOrderState.statGroupby.forEach(item=>{
                    const forEachArray = [item.orderStateForSMString,item.count];
                    InputArray.push(forEachArray)
                });


//                console.log(InputArray);

                var data = google.visualization.arrayToDataTable(InputArray);
                var options = {
                    pieHole: 0.45,
                    colors: ['#00FFFF','#F20505', '#F28749', '#03A64A', '#8268A6'] // 색상 변경
                };

                var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
                chart.draw(data, options);

            },
        error: function(error) {
            console.log("오류발생 0");
            }
        });
}

function sendSMRURL(){
//    console.log("sendSMRURL() 작동")
    var startDateInput=document.getElementById('startDate'); // HTML 요소에 접근
    var endDateInput=document.getElementById('endDate');// 요소에 접근에 뒤에 값찍을려면.value
//    console.log("가져온START데이터"+startDateInput.value+"END데이터"+endDateInput.value); //날짜를 선택하지 않으면 가져온START데이터END데이터 이렇게 찍힘
    var varStatusManagementReportSearchData;
    $.ajax({
        url:'StatusManagementReportSearch?startDate='+startDateInput.value+'&endDate='+endDateInput.value,
        type : 'get',
        success: function(statusManagementReportSearchData) {
                console.log("잘보냄");
//                console.log(statusManagementReportSearchData);

//                console.log(statusManagementReportSearchData.statementDTOList.length) // 왜 이값이 비어있지?

                var tableBody = document.getElementById('tableBody');//DOM 요소 가져와서
                tableBody.innerHTML = '';//내부에 있는 내용을 비워주고

                for (var i=0; i<statusManagementReportSearchData.statementDTOList.length; i++){ //리스트 크기만큼 반복
                    var tableBodyRow = document.createElement('tr');

                    var productcodeCell = document.createElement('td');
                    productcodeCell.classList.add('table-data');
                    productcodeCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].productcode;
                    tableBodyRow.appendChild(productcodeCell);

                    var productnameCell = document.createElement('td');
                    productnameCell.classList.add('table-data');
                    productnameCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].productname;
                    tableBodyRow.appendChild(productnameCell);

                    var departNameCell = document.createElement('td');
                    departNameCell.classList.add('table-data');
                    departNameCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].departName;
                    tableBodyRow.appendChild(departNameCell);

                    var businessNumberCell = document.createElement('td');
                    businessNumberCell.classList.add('table-data');
                    businessNumberCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].businessNumber;
                    tableBodyRow.appendChild(businessNumberCell);

                    var dateOfOrderCell = document.createElement('td');
                    dateOfOrderCell.classList.add('table-data');
                    if(statusManagementReportSearchData.statementDTOList[i].dateOfOrder.toString() ==='0001-01-01T00:00:00'  ){
                        dateOfOrderCell.innerHTML = '발주전입니다';
                    }else{
                        dateOfOrderCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].dateOfOrder.toString();
                    }

                    tableBodyRow.appendChild(dateOfOrderCell);

                    var orderDateCell = document.createElement('td');
                    orderDateCell.classList.add('table-data');
                    orderDateCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].orderDate;
                    tableBodyRow.appendChild(orderDateCell);

                    var ArrivalCell = document.createElement('td');
                    ArrivalCell.classList.add('table-data');

                    if(statusManagementReportSearchData.statementDTOList[i].dateOfOrder.toString() ==='0001-01-01T00:00:00'  ){
                        ArrivalCell.innerHTML = '입고처리 전입니다';
                    }else{
                        ArrivalCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].arrival.toString();
                    }
                    tableBodyRow.appendChild(ArrivalCell);

                    var orderStateCell = document.createElement('td');
                    orderStateCell.classList.add('table-data');
                    orderStateCell.innerHTML = statusManagementReportSearchData.statementDTOList[i].orderState;
                    tableBodyRow.appendChild(orderStateCell);

                    tableBody.appendChild(tableBodyRow);

                }                            //리스트 행만큼 반복

            },//success 중괄호
        error: function(error) {
                console.log("오류발생 0");
            }
    });

}


