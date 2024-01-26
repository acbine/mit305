
/*발주서 발행*/
function orderRegister(productCode, procurementPlanCode, index) {
    var tbody = document.getElementsByClassName("orderRegisterTable");
    tbody[index].style.display = "none";
    var formData = {"productCode": productCode, "procurementPlanCode" : procurementPlanCode};
    $.ajax({
        url:'orderRegisterData',
        contentType : 'application/json',
        method : 'POST',
        data : JSON.stringify(formData),
        success : function () {
        },
        error : function () {
        }
    })

}

function orderRegisterIn(index, planCode) {
    console.log("확인시 받아오는 데이터 확인",index , "           ", planCode);
    $.ajax({
        url:'inspectorData?planCode='+planCode,
        method: 'get',
        success:function (inspector) {
            console.log(inspector)
            addInspect(inspector)
            console.log("성공")
        },
        error:function (){
            console.error("실패")
        }

    })
}

function addInspect(inspector) {
    console.log("넘어오는 데이터 확인 : ", inspector.progressInspectionList);
    var inspectorInfo = inspector.progressInspectionList;
    var classTbodyContainerTr = document.getElementById("progressInspection");

    for(let i = 0; i<inspectorInfo.length; i++) {

        classTbodyContainerTr.insertRow(0).innerHTML = `<td>${inspectorInfo[i].productName}</td>
                                                            <td>${formDate(inspectorInfo[i].orderDate)}</td>
                                                            <td Class="inspectDate">${formDate(inspectorInfo[i].progressInspectonDate)}</td>
                                                            <input hidden="${inspectorInfo[i].progressInspectionId}"/>
                                                            <td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`
    }

    var hidden = document.getElementById("hidden");
    hidden.style.display ="block";
}

function updateProgressInspection(info) {
    var updateDate = info.closest("tr");

    var date = updateDate.children[2];
    var updateButton = updateDate.children[3];
    updateButton.innerHTML = `<td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateConfirm(this)">등록</button><button>삭제</button></td>`;
    date.innerHTML = `<input type="date">`;
    console.log(updateDate);
}

function cancel(productCode, index) {
    var tbody = document.getElementsByClassName("orderRegisterTable");
    tbody[index].style.display = "none";
    var formData = {"productCode": productCode};



}

function formDate(data) {

    var date = new Date(data)

    var formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);

    return formattedDate;
}