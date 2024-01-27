/*발주서 발행*/
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
            console.log(inspector)
            addInspect(inspector)
            console.log("성공")
        },
        error: function (data) {
            console.error(data)
            console.error("실패")
        }

    })
}

function addInspect(inspector) {
    var inspectorInfo = inspector.progressInspectionList;
    var classTbodyContainerTr = document.getElementById("progressInspection");

    for (let i = 0; i < inspectorInfo.length; i++) {

        classTbodyContainerTr.insertRow(0).innerHTML = `<td>${inspectorInfo[i].productName}</td>
                                                            <td>${formDate(inspectorInfo[i].orderDate)}</td>
                                                            <td Class="inspectDate">${formDate(inspectorInfo[i].progressInspectonDate)}</td>
                                                            <input type="hidden" value="${inspectorInfo[i].progressInspectionId}"/> 
                                                            <td><button onclick="openPopup(this)">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`
    }

    var hidden = document.getElementById("hidden");
    hidden.style.display = "block";
}

function updateProgressInspection(info) {
    var updateData = info.closest("tr");
    console.log(updateData);
    var date = updateData.children[2];
    var updateButton = updateData.children[4];
    console.log(updateButton)
    updateButton.innerHTML = `<td><button onclick="openPopup(this)">진척검수실행</button><button onclick="updateConfirm(this)">등록</button><button>삭제</button></td>`;
    date.innerHTML = `<input type="date">`;

}

function cancel(procurementplan_code, index) {
    var tbody = document.getElementsByClassName("orderRegisterTable");
    tbody[index].style.display = "none";

    $.ajax({
        url: 'cancelOrder/' + procurementplan_code,
        method: 'delete',
        data: {},
        success: function () {
            console.log("성공")
        },
        error: function () {
            console.log("취소")
        }
    })


}

function openPopup() {
    var progressInspectorPopup = document.getElementById('popup');
    progressInspectorPopup.style.display = "block";

}

function updateConfirm(html) {
    var updateData = html.closest("tr");
    var dateHtml = updateData.children[2];
    var updateButton = updateData.children[4];

    console.log("바꿀 html 정보 확인하기 : ", updateButton)
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
            console.log("성공")
            console.log("html 정보 찍어보기", dateHtml)
            dateHtml.innerHTML = `<td class="inspectDate">${transDate}</td>`
            console.log("바뀐 html정보 확인하기 : ", dateHtml)
            updateButton.innerHTML = `<td><button onclick="openPopup(this)">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`;
        },
        error: function () {
            console.log("실패")
        }
    })

}

function inspectorCheck(info) {

    var checkListCnt = 0;
    var progressInspectorResult = false;
    var checkBoxes = document.getElementsByClassName("checkbox");
    for (let i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked === true) {
            ++checkListCnt;
        }
    }
    if (checkListCnt < 4) {
        progressInspectorResult = false;
    } else {
        progressInspectorResult =true;
    }
    openPopup('popup')
}


function formDate(data) {

    var date = new Date(data)

    var formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);

    return formattedDate;
}
