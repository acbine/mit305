/*--------------------발주 목록(orderList)--------------------*/

function openOrderInspectPopup(productCode,procurementPlanCode,orderListIndex){
    var html = document.getElementById("orderInspectPopup");
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                html.style.display = "block";
                html.innerHTML = this.responseText;
            }
        };
        xhttp.open('GET','orderInspect?productCode='+productCode+'&procurementPlanCode='+procurementPlanCode+'&orderIndex='+orderListIndex, true);
        xhttp.send();

}
function searchOrderListWithDate() {
    var date1 = document.getElementsByName("startDate")[0].value;
    var date2 = document.getElementsByName("endDate")[0].value;
    $.ajax({
            url: 'order-list-with-date?&date1=' + date1 +'&date2='+date2,
            method:'get',
        success:function (info){
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
function putOrderAmount(productCode, planCode, index) {
    var putOrderTableInfo = document.getElementsByClassName("planNum");
    var btnList = document.getElementsByClassName("orderRegisterBtnContainer");
    var changeToInput = putOrderTableInfo[index];
    var num =putOrderTableInfo[index].innerHTML;


    console.log(btnList)
    changeToInput.innerHTML=`<input style="width: 40px" class="inputPlanNum" type="number">`
    btnList[0].innerHTML = `<td>
                       <button class="orderRGSearchButton" onclick="putPlanNum(${productCode},${planCode},${index})">수정 완료</button>
                       <button class="orderRGSearchButton" onclick="cancelPlanPut(${productCode},${planCode},${index},${num})"> 취소 </button>
                 </td>`

}

function putPlanNum(productCode,planCode,index) {
    var input = document.getElementsByClassName("inputPlanNum");
    var changeToInput = input[index];

    console.log(changeToInput.innerHTML)
    var btnList = document.getElementsByClassName("orderRegisterBtnContainer");


    var inputData = input[index].value;//받아온 데이터 값
    changeToInput.outerHTML=`<td class="planNum">${inputData}</td>`

    btnList[0].innerHTML =`<td>
                    <button class="orderRGSearchButton" onclick="putOrderAmount(${productCode},${planCode},${index})">수정</button>
                    <button class="orderRGSearchButton" onclick="orderRegister(${productCode},${planCode},${index})">등록</button>
                    <button class="orderRGSearchButton" onclick="cancel(${productCode},${planCode},${index})">취소</button>
                </td>`

    var formData = { "procurementPlanCode" : planCode, "num" : inputData};

    console.log(planCode);
    $.ajax({
        url: "putOrderNum",
        method: "put",
        contentType: 'application/json',
        data : JSON.stringify(formData),
        success:function () {
            console.log("성공")
        },
        error : function (error) {
            alert(error.responseJSON.msg)
        }
    })

}
function cancelPlanPut(productCode,planCode,index,num) {
    var putOrderTableInfo = document.getElementsByClassName("planNum");
    var btnList = document.getElementsByClassName("orderRegisterBtnContainer");
    var changeToInput = putOrderTableInfo[index];

    changeToInput.innerHTML=`<td>${num}</td>`

    btnList[0].innerHTML =`<td>
                    <button class="orderRGSearchButton" onclick="putOrderAmount(${productCode},${planCode},${index})">수정</button>
                    <button class="orderRGSearchButton" onclick="orderRegister(${productCode},${planCode},${index})">등록</button>
                    <button class="orderRGSearchButton" onclick="cancel(${productCode},${planCode},${index})">취소</button>
                </td>`
    // btnList[1] =`<td><button class="orderRGSearchButton" onclick="orderRegister(${productCode},${planCode},${index})">등록</button></td>`
    // btnList[2] = `<td><button class="orderRGSearchButton" onclick="cancel(${productCode},${planCode},${index})">취소</button></td>`

}
/*--------------------발주 목록 팝업창(orderListPopup)--------------------*/
function openOrder(procurementPlanCode) {
    var html = document.getElementById("orderPopup");
    var orderHtml = document.getElementById("order-popup-content")
    $.ajax({
        url:"open-order/"+procurementPlanCode,
        method: "get",
        success:function (order){
            html.style.display = "block";
            orderHtml.style.display="block";
            orderHtml.innerHTML = order;
        },
        error:function (){
            console.log("실패")
        }
    })
    console.log("버튼 동작 확인")
}

function closeOrder() {
    document.getElementById("orderPopup").style.display = "none";
}

/*-------------------진척 검수 관리-------------------------------------------*/

function formDate(data) {

    var date = new Date(data)

    var formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);

    return formattedDate;
}
