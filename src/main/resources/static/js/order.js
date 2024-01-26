/*--------------------발주 목록(orderList)--------------------*/

function openOrderInspectPopup(productCode,procurementPlanCode){  //모달창열기
    var html = document.getElementById("orderInspectPopup");
console.log(procurementPlanCode)
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

function closePopup() {
    document.getElementById("orderInspectPopup").style.display = "none";
}
function closeInspect(){
    console.log("일단 닫기 버튼")
}

/*--------------------발주 목록 팝업창(orderListPopup)--------------------*/
function downloadImage(){
    /*html2canvas(document.getElementById('screen_area'),{scale:2}).then((canvas) => {
        const imageDataURL = canvas.toDataURL("image/jpg");

        const a = document.createElement("a");
        a.href = imageDataURL;
        a.download = "발주서.jpg";
    }*/
}

/*-------------------진척 검수 관리-------------------------------------------*/


var ProgressInspectionState = 0;

function ProgressInspection() {
    var classTbodyContainerTr = document.getElementById("progressInspection");
    if (ProgressInspectionState === 0) {
        for (var i = 0; i < classTbodyContainerTr.innerHTML.length; i++) {
            classTbodyContainerTr.innerHTML[i] = `<td>나사</td>
                                                <td>2023-12-13</td>
                                                <td>입력된날짜</td>
                                                <td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`
        }
    } else if (ProgressInspectionState === 1) {
        for (var i = 0; i < classTbodyContainerTr.innerHTML.length; i++) {
            classTbodyContainerTr.innerHTML[i] = `<td>나사</td>
                                                <td>2023-12-13</td>
                                                <td>입력된날짜</td>
                                                <td><button onclick="openPopup('popup')">진척검수실행</button></td>`
        }
    } else if (ProgressInspectionState === 2) {
        for (var i = 0; i < classTbodyContainerTr.innerHTML.length; i++) {
            classTbodyContainerTr.innerHTML[i] = `<td>나사</td>
                                                <td>2023-12-13</td>
                                                <td>입력된날짜</td>
                                                <td>진척 검수 완료</td>`
        }
    }
}

function addProgressInspection(productName, planId) {
    var classTbodyContainerTr = document.getElementById("progressInspection");
    var dateValue = document.getElementById("setInspectDate").childNodes[0].value;

    if(dateValue) {
        var formData = {
            "inspectDate" : dateValue,
            "planId" : planId
        }
        $.ajax({
            url : "orderInspect",
            contentType : 'application/json',
            data : JSON.stringify(formData),
            method : "post",
            success : function () {
                console.log("성공")
            },
            error : function () {
                console.log("보내는 데이터 형태 확인 : ", formData)
                console.error("잘못된 응답");
            }
        })
        classTbodyContainerTr.insertRow(0).innerHTML = `<td>나사</td>
                                                            <td>2023-12-13</td>
                                                            <td Class="inspectDate">입력된날짜</td>
                                                            <td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`
    } else {
        alert("진척 검수 계획일을 입력해주세요")
    }

}

function updateProgressInspection(info) {
    var updateDate = info.closest("tr");

    var date = updateDate.children[2];
    var updateButton = updateDate.children[3];
    updateButton.innerHTML = `<td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateConfirm(this)">등록</button><button>삭제</button></td>`;
    date.innerHTML = `<input type="date">`;
    console.log(updateDate);
}

function updateConfirm(info) {
    var update = info.closest("tr");

    var updateDateConfirm = update.children[2];
    updateDateConfirm.innerHTML = `<td>입력된날짜</td>`;
    var updateConfirm = update.children[3];
    updateConfirm.innerHTML = `<td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`;
}


function toggleTables() {
    var selectedOption = document.getElementById("companyDropdown").value;

    document.getElementById("table1").classList.add("hidden");
    document.getElementById("table2").classList.add("hidden");
    document.getElementById("table3").classList.add("hidden");

    document.getElementById(selectedOption).classList.remove("hidden");
}


