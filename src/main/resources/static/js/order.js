/*--------------------발주 목록(orderList)--------------------*/

function openOrderInspectPopup(productCode,procurementPlanCode){  //모달창열기
    var html = document.getElementById("orderInspectPopup");
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                html.style.display = "block";
                html.innerHTML = this.responseText;
            }
        };
        xhttp.open('GET','orderInspect?productCode='+productCode+'&procurementPlanCode='+procurementPlanCode, true);
        xhttp.send();

}
function searchOrderListWithDate() {
    var date1 = document.getElementsByName("startDate")[0].value;
    var date2 = document.getElementsByName("endDate")[0].value;
    $.ajax({
            url: 'order-list-with-date?&date1=' + date1 +'&date2='+date2,
            method:'get',
        success:function (info){
                console.log(info.oList[0],"info 정보 확인하기")
                drawHTMl(info)
                console.log("성공");
        },
        error:function (){
            console.log("실패");
        }

    }
        )
}


function drawHTMl(info) {
    var orderBoxInfo = document.getElementsByClassName("orderList")

    for(var i = 0 ; i<orderBoxInfo.length; i++) {
        var data = info.oList[i].orderDate;
        if(info.oList[i].progressInspectionStatus===false) {
            orderBoxInfo[i].innerHTML = `<td>${info.oList[i].productName}</td>
                                    <td>${ formDate(data)}</td>
                                    <td>${info.oList[i].departName}</td>
                                    <td>${info.oList[i].orderState}</td>
                                    <td onclick="openOrderInspectPopup(${info.oList[i].productCode, info.oList[i].procurementPlanCode})" ></td>
                                    <td onclick="openOrder(${info.oList[i].procurementPlanCode})">🔍️</td>`
        } else {
            orderBoxInfo[i].innerHTML = `<td>${info.oList[i].productName}</td>
                                    <td>${ formDate(data)}</td>
                                    <td>${info.oList[i].departName}</td>
                                    <td>${info.oList[i].orderState}</td>
                                    <td onclick="openOrder(${info.oList[i].procurementPlanCode})">🔍️</td>`
        }
    }

}
/*--------------------발주 목록 팝업창(orderListPopup)--------------------*/
function openOrder(procurementPlanCode) {
    var html = document.getElementById("orderPopup");
    $.ajax({
        url:"open-order/"+procurementPlanCode,
        method: "get",
        success:function (order){
            console.log(order)
            html.style.display = "block";
            html.innerHTML = order;
            console.log("성공")
        },
        error:function (){
            console.log("실패")
        }
    })
    console.log("버튼 동작 확인")
}



/*-------------------진척 검수 관리-------------------------------------------*/

function formDate(data) {

    var date = new Date(data)

    var formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);

    return formattedDate;
}
