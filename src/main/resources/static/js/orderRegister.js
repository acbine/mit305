/*발주서 발행*/
var progressInspectorIndex;


function orderRegister(productCode, procurementPlanCode, index) {
    var tbody = document.getElementsByClassName("orderRegisterTable");
    tbody[index].style.display = "none";
    var formData = {"productCode": productCode, "procurementPlanCode": procurementPlanCode};
    $.ajax({
        url: 'orderRegisterData',
        contentType: 'application/json',
        method: 'POST',
        data: JSON.stringify(formData),
        success: function () {
        },
        error: function () {
        }
    })

}

function orderRegisterIn(index, planCode) {
    $.ajax({
        url: 'inspectorData?planCode=' + planCode,
        method: 'get',
        success: function (inspector) {
            console.log("받아온 데이터 확인 : ", inspector)
            addInspects(inspector)
        },
        error: function (data) {
            console.error(data)
            alert(data.responseJSON.message);
        }

    })
}

function addInspects(inspector) {
    var inspectorInfo = inspector.progressInspectionList;
    var classTbodyContainerTr = document.getElementById("progressInspection");

    for (let i = 0; i < inspectorInfo.length; i++) {
        if(i===inspectorInfo.length-1&&inspectorInfo[i].progressInspectorResult===false) {
            classTbodyContainerTr.insertRow().innerHTML = `<td>${inspectorInfo[i].productName}</td>
                                                            <td>${formDate(inspectorInfo[i].orderDate)}</td>
                                                            <td Class="inspectDate">${formDate(inspectorInfo[i].progressInspectonDate)}</td>
                                                            <input type="hidden" value="${inspectorInfo[i].progressInspectionId}"/> 
                                                            <td id="buttonState"><button onclick="popup.openPopup(${i})">진척검수실행</button><button onclick="updateProgressInspection(this,${i})">수정</button><button onclick="cancel()">삭제</button></td>`
        } else if(inspectorInfo[i].progressInspectorResult===true&&inspectorInfo[i].orderState==="발주중"||inspectorInfo[i].orderState==="발주전"||inspectorInfo[i].orderState==="") {
            classTbodyContainerTr.insertRow().innerHTML =`<td>${inspectorInfo[i].productName}</td>
                                                            <td>${formDate(inspectorInfo[i].orderDate)}</td>
                                                            <td Class="inspectDate">${formDate(inspectorInfo[i].progressInspectonDate)}</td>
                                                            <input type="hidden" value="${inspectorInfo[i].progressInspectionId}"/> 
                                                            <td><font color="red">[재검수 요망]</font></td>`;
        } else if(inspectorInfo[i].progressInspectorResult===true&&inspectorInfo[i].orderState==="마감"||inspectorInfo[i].orderState==="검수처리완료"){
            classTbodyContainerTr.innerHTML =``;
            classTbodyContainerTr.insertRow().innerHTML =`<td>${inspectorInfo[i].productName}</td>
                                                            <td>${formDate(inspectorInfo[i].orderDate)}</td>
                                                            <td Class="inspectDate">${formDate(inspectorInfo[i].progressInspectonDate)}</td>
                                                            <input type="hidden" value="${inspectorInfo[i].progressInspectionId}"/> 
                                                            <td><font color="green">[검수 완료]</font></td>`;
        }
    }

    var hidden = document.getElementById("hidden");
    hidden.style.display = "block";
}




const inspectorCheck = {
    receiveIndexData: function (index) {
        progressInspectorIndex = index;
    },

    progressInspectorCheck: function () {
        var checkListCnt = 0;
        var progressInspectorResult = false;
        var classTbodyContainerTr = document.getElementById("progressInspection");
        var tData = classTbodyContainerTr.children[progressInspectorIndex];
        var checkBoxes = document.getElementsByClassName("checkbox");

        var progressInspectionId = tData.children[3].value;
        var resultTable = tData.children[4];
        var buttonColumn = document.getElementById("buttonState");
        var date  = document.getElementById("setInspectDate");

        for (let i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].checked === true) {
                ++checkListCnt;
            }
        }

        progressInspectorResult = checkListCnt >= 4;

        var formData = {
            "progressInspectionId" : progressInspectionId, "progressInspectorResult" :  progressInspectorResult
        }

        $.ajax({
                url: 'inspectorResult',
                data: JSON.stringify(formData),
                contentType:'application/json',
                method: 'put',
                success: function (result) {
                    if(result.result==="재검수 요망"){
                        resultTable.innerHTML = `
                                                        <input type="hidden" value="${progressInspectionId}">
                                                        <td><font color="red">[${result.result}]</font></td>`;
                    } else {
                        console.log(date,"버튼 데이터 정보 확인")
                        date.innerHTML = ``;
                        resultTable.innerHTML = ` <input type="hidden" value="${progressInspectionId}">
                                                        <td><font color="green">[${result.result}]</font></td>`;
                        buttonColumn.innerHTML = `<td></td>`;
                    }
                },
                error: function () {
                    console.log("검수 요청 실패")
                }
            }
        )
        closeInspect();
    }

}



function updateProgressInspection(info, index) {
    var updateData = info.closest("tr");
    console.log(updateData);
    var date = updateData.children[2];
    var updateButton = updateData.children[4];

    updateButton.innerHTML = `<td id="buttonState"><button onclick="popup.openPopup(${index})">진척검수실행</button><button onclick="updateConfirm(this,${index})">등록</button><button onclick="cancel()">삭제</button></td>`;
    date.innerHTML = `<input type="date">`;

}

function cancel(progressInspectorId, index) {
    var classTbodyContainerTr = document.getElementById("progressInspection");
    var tData = classTbodyContainerTr.children[classTbodyContainerTr.children.length-1];
    var progressInspectionId = tData.children[3].value;
    console.log(tData)

    $.ajax({
        url: 'inspectorData/' + progressInspectionId,
        method: 'delete',
        success: function () {
            tData.style.display="none";
            tData.innerHTML="";
            console.log("성공")
        },
        error: function () {
            console.log("취소")
        }
    })


}

const popup ={
    openPopup: function (){
        var progressInspection = document.getElementById("progressInspection");
        var index =  progressInspection.children.length-1;
        var progressInspectorPopup = document.getElementById('popup');
        progressInspectorPopup.style.display = "block";
        inspectorCheck.receiveIndexData(index);
    }

}

function updateConfirm(html, index) {
    var updateData = html.closest("tr");
    var dateHtml = updateData.children[2];
    var updateButton = updateData.children[4];

    var date = updateData.children[2].children[0].value;
    var inspectorId = updateData.children[3].value;
    var transDate = formDate(date);


    let formData = {
        "progressInspectonDate": transDate, "progressInspectionId": inspectorId
    }

    $.ajax({
        url: "inspectorNewDate",
        method: "put",
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function () {
            dateHtml.innerHTML = `<td class="inspectDate">${transDate}</td>`
            updateButton.innerHTML = `<td id="buttonState"><button onclick="popup.openPopup(${index})">진척검수실행</button><button onclick="updateProgressInspection(this, ${index})">수정</button><button onclick="cancel()">삭제</button></td>`;
        },
        error: function () {
            console.log("실패")
        }
    })
}

function addProgressInspection(productName, planId, info) {
    var dateValue = document.getElementById("setInspectDate").childNodes[0].value;
    var index = info.target;
    if (dateValue) {
        var formData = {
            "inspectDate": dateValue,
            "planId": planId
        }
        $.ajax({
            url: "orderInspect",
            contentType: 'application/json',
            data: JSON.stringify(formData),
            method: "post",
            success: function (data) {
                addInspectorOne(data, index);
                console.log("성공")
            },
            error: function (data) {
                alert(data.responseJSON.message);
                console.error("잘못된 응답");
            }
        })

    } else {
        alert("진척 검수 계획일을 입력해주세요")
    }

}

function addInspectorOne(data, index) {
    var inspector = data.progressInspection;

    var orderDate = formDate(inspector.orderDate)
    var progressInspectorDate = formDate(inspector.progressInspectonDate);

    var classTbodyContainerTr = document.getElementById("progressInspection");
    classTbodyContainerTr.insertRow().innerHTML = `<td>${inspector.productName}</td>
                                                            <td>${orderDate}</td>
                                                            <td class="inspectDate">${progressInspectorDate}</td>
                                                            <input type="hidden" value="${inspector.progressInspectionId}"/>
                                                            <td id="buttonState"><button onclick="popup.openPopup(${index})">진척검수실행</button><button onclick="updateProgressInspection(this,${index})">수정</button><button onclick="cancel()">삭제</button></td>`
}

function toggleTables() {
    var selectedOption = document.getElementById("companyDropdown").value;

    document.getElementById("table1").classList.add("hidden");
    document.getElementById("table2").classList.add("hidden");
    document.getElementById("table3").classList.add("hidden");

    document.getElementById(selectedOption).classList.remove("hidden");
}

function closePopup() {
    window.close();
}

function closeInspect() {
    document.getElementById("popup").style.display = "none";
}


function formDate(data) {

    var date = new Date(data)

    var formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);

    return formattedDate;
}
