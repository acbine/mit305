/*--------------------발주 목록(orderList)--------------------*/
function openPopup(popupId) {
    document.getElementById(popupId).style.display = "block";
}

function closePopup(popupId) {
    document.getElementById(popupId).style.display = "none";
}

function showHiddenTable(){
    document.querySelector('.hidden').classList.remove('hidden');
    document.querySelector('.confirm').classList.add('hidden');
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

function addProgressInspection() {
    var classTbodyContainerTr = document.getElementById("progressInspection");
    classTbodyContainerTr.insertRow(0).innerHTML = `<td>나사</td>
                                                            <td>2023-12-13</td>
                                                            <td>입력된날짜</td>
                                                            <td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`
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

function registration_and_delete(tag) {
    tag.closest("tr").remove();
}

function deleteRow(tag) {
    tag.closest("tr").remove();
}

function toggleTables() {
    var selectedOption = document.getElementById("companyDropdown").value;

    document.getElementById("table1").classList.add("hidden");
    document.getElementById("table2").classList.add("hidden");
    document.getElementById("table3").classList.add("hidden");

    document.getElementById(selectedOption).classList.remove("hidden");
}


