/*-------------------품목 정보(ProductInformationRegistration)-------------------------*/
function info_modify_and_save(tag) {

    const tr = tag.closest("tr");

    const cell_0 = tr.children[0];
    const cell_1 = tr.children[1];
    const cell_2 = tr.children[2];
    const cell_3 = tr.children[3];
    const cell_4 = tr.children[4];
    const cell_5 = tr.children[5];
    const cell_6 = tr.children[6];
    const cell_7 = tr.children[7];
    const cell_8 = tr.children[8];


    if (cell_0.contentEditable == "true") {

        cell_0.contentEditable = "false";
        cell_1.contentEditable = "false";
        cell_2.contentEditable = "false";
        cell_3.contentEditable = "false";
        cell_4.contentEditable = "false";
        cell_5.contentEditable = "false";
        cell_6.contentEditable = "false";
        cell_7.contentEditable = "false";
        cell_8.contentEditable = "false";

        tag.innerText = "수정";

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
    } else {

        //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

        cell_0.contentEditable = "true";
        cell_1.contentEditable = "true";
        cell_2.contentEditable = "true";
        cell_3.contentEditable = "true";
        cell_4.contentEditable = "true";
        cell_5.contentEditable = "true";
        cell_6.contentEditable = "true";
        cell_7.contentEditable = "true";
        cell_8.contentEditable = "true";

        tag.innerText = "수정 완료";

        cell_0.focus();
    }
}

function info_registration_and_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}


function info_addRow() {

    const table = document.getElementById('product');
    const new_row = table.insertRow();

    const cell_length = table.rows[0].cells.length;

    for (let i = 0; i < cell_length; i++) {
        const new_cell = new_row.insertCell(i);
        let temp_html = '';

        if (i == 9) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">대분류 1</option>' +
                '<option value="second">대분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if (i == 10) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">중분류 1</option>' +
                '<option value="second">중분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if (i == 11) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">소분류 1</option>' +
                '<option value="second">소분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if (i == 12) {

            temp_html = '<td>' +
                '<input type="file" accept="image/png, image/jpg">' +
                '</td>';
        }

        new_cell.insertAdjacentHTML('afterbegin', temp_html);

    }
}

/*-------------------------발주 품목 선택----------------------*/
function registration_and_delete(tag) {
    alert("해당 품목이 선택 완료되었습니다.");
    tag.closest("tr").remove();
}

function deleteRow(tag) {
    tag.closest("tr").remove();
}

/*--------------------발주 목록(orderList)--------------------*/
function openPopup(popupId) {
    var popup = document.getElementById(popupId);
    popup.style.display = "block";
}

function closePopup(popupId) {
    var popup = document.getElementById(popupId);
    popup.style.display = "none";
}

function showHiddenTable() {
    document.querySelector('.hidden').classList.remove('hidden');
    document.querySelector('.confirm').classList.add('hidden');
}

function toggleTables() {
    var selectedOption = document.getElementById("companyDropdown").value;

    document.getElementById("table1").classList.add("hidden");
    document.getElementById("table2").classList.add("hidden");
    document.getElementById("table3").classList.add("hidden");

    document.getElementById(selectedOption).classList.remove("hidden");
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

/*------------------현황관리-------------------------------------*/

google.charts.load("current", {packages: ["corechart"]});

google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        ['미검수', 1,],
        ['검수진행중', 1],
        ['검수완료', 1],
        ['마감', 1]
    ]);

    var options = {
        pieHole: 0.45,
        colors: ['#F20505', '#F28749', '#03A64A', '#8268A6'] // 색상 변경


    };


    var chart = new google.visualization.PieChart(document.getElementById("donutchart"));

    chart.draw(data, options);
}

/*----------------------입고 처리(ReceivingProcessing)------------------*/
function openModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

function sendToDataInModal() {
    console.log("모달창 안에서 데이터 전송함");
    document.getElementById("myModal").style.display = "none"

}

$(function ReceivingProcessing() {
    const table = $('.table-style');
    const headerRow = table.find('thead tr');


    $(".table-container").on("scroll", function () {
        console.log("테이블콘테이너 스크로됨");


    });
});

/*---------------------거래명세서-------------------*/
function OpenTradingMy() {
    $(".TradingmyModal").css('display', 'block');
}

function CloseTradingMy() {
    $(".TradingmyModal").css('display', 'none');
}

function printTradingStatement() {
    $(".TradingmyModal").css('display', 'block');
    window.print();
    $(".TradingmyModal").css('display', 'none');
}


/*---------------------계약등록-------------------*/

function contract_modify_and_save(tag) {

    const tr = tag.closest("tr");

    const cell_0 = tr.children[0];
    const cell_4 = tr.children[4];
    const cell_5 = tr.children[5];


    if (cell_0.contentEditable == "true") {

        cell_0.contentEditable = "false";
        cell_4.contentEditable = "false";
        cell_5.contentEditable = "false";

        tag.innerText = "수정";

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
    } else {

        //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

        cell_0.contentEditable = "true";
        cell_4.contentEditable = "true";
        cell_5.contentEditable = "true";

        tag.innerText = "수정 완료";

        cell_4.focus();
    }
}

function contract_registration_and_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function contract_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

<!-- 선택한 품목명로 품목코드 검색-->
function p_code_value(p_code) {

    console.log(p_code);
}

<!-- 선택한 사업자번호로 기업명 검색-->
function b_code_value(b_code) {

    console.log(b_code);
}

function contract_addRow() {

    const table = document.getElementById('contract');
    const new_row = table.insertRow();

    const cell_length = table.rows[0].cells.length;

    for (let i = 0; i < cell_length; i++) {
        const new_cell = new_row.insertCell(i);
        let temp_html = '';

        if (i == 0) {

            temp_html =
                '<select onChange="p_code_value(this.options[this.selectedIndex].value)">' +
                '<option value="p_code1">나사(A, 길승산업)</option>' +
                '<option value="p_code2">A품목(B, 마텍스)</option>' +
                '<option value="p_code3">망치(C, 길승산업)</option>' +
                '<option value="p_code4">나사(D, 마텍스)</option>' +
                '</select>' +

                '<button>확인</button>';

        }

        if (i == 2) {

            temp_html =
                '<select onChange="b_code_value(this.options[this.selectedIndex].value)">' +
                '<option value="b_code1">Xxx-xxx-xxxxx</option>' +
                '<option value="b_code2">사업자 번호1</option>' +
                '<option value="b_code3">사업자 번호2</option>' +
                '</select>' +

                '<button>확인</button>';

        }

        if (i == 6) {

            temp_html =
                '<td>' +
                '<div class="actions">' +
                '<button class="action-button action-button-edit" onclick="contract_modify_and_save(this)">수정</button>' +
                '<button class="action-button action-button-delete" onclick="contract_registration_and_delete(this)">삭제</button>' +
                '</div>' +
                '</td>';
        }

        new_cell.insertAdjacentHTML('afterbegin', temp_html);

    }
}

        // 계약서 모달창 호출
    function OpenContract() {
        $(".ContractModal").css('display', 'block');
    }

        // 계약서 모달창 닫기
    function CloseContract() {
        $(".ContractModal").css('display', 'none');
    }


/*---------조달계획등록--------------*/
function modify_and_save(tag) {

    const tr = tag.closest("tr");

    const num = tr.children[3];
    const date = tr.children[8];

    const target = document.getElementById("deleteBtn1");
    //const delete_btn_state = document.getElementsByClassName('action-button action-button-registration');

    console.log(num, date);


    if (num.contentEditable == "true") {

        num.contentEditable = "false";
        date.contentEditable = "false";


        tag.innerText = "수정";

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
    } else {

        //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

        num.contentEditable = "true";
        date.contentEditable = "true";

        tag.innerText = "수정 완료";


        //첫번째 셀에 포커스를 줘서 상태 변경에 대해 알림.
        num.focus();

    }
}

function registration_and_delete(tag) {

    alert("등록 완료");

    const tr = tag.closest("tr");
    tr.remove();
}

function td_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function select_row() {

    const table = document.getElementById('plan_table');

    const cell_length = table.rows[0].cells.length;

    const num = 3;

    for (let i = 1; i <= num; i++) {

        const new_row = table.insertRow(i);

        for (let j = 0; j < cell_length; j++) {
            let temp_html = '';
            const new_cell = new_row.insertCell(j);

            if (i == 1) {

                if (j == 0) {
                    temp_html = '<td>나사</td>';
                }

                if (j == 1) {
                    temp_html = '<td>A</td>';
                }

                if (j == 2) {
                    temp_html = '<td>16</td>';
                }

                if (j == 3) {
                    temp_html = '<td>4</td>';
                }

                if (j == 4) {
                    temp_html = '<td>5일</td>';
                }

                if (j == 5) {
                    temp_html = '<td>24/01/16</td>';
                }

                if (j == 6) {
                    temp_html = '<td>길승산업</td>';
                }

                if (j == 7) {
                    temp_html = '<td>12</td>';
                }

                if (j == 8) {
                    temp_html = '<td>24/01/10</td>';
                }

                if (j == 9) {
                    temp_html =
                        '<td>' +
                        '<div class="actions">' +
                        '<button class="action-button action-button-edit" onclick="modify_and_save(this)">수정</button>' +
                        '<button class="action-button action-button-registration" onclick="registration_and_delete(this)">등록</button>' +
                        '</div>' +
                        '</td>';
                }

            }

            if (i == 2) {

                if (j == 0) {
                    temp_html = '<td>A품목</td>';
                }

                if (j == 1) {
                    temp_html = '<td>B</td>';
                }

                if (j == 2) {
                    temp_html = '<td>12</td>';
                }

                if (j == 3) {
                    temp_html = '<td>7</td>';
                }

                if (j == 4) {
                    temp_html = '<td>8일</td>';
                }

                if (j == 5) {
                    temp_html = '<td>24/01/23</td>';
                }

                if (j == 6) {
                    temp_html = '<td>길승산업</td>';
                }

                if (j == 7) {
                    temp_html = '<td>5</td>';
                }

                if (j == 8) {
                    temp_html = '<td>24/01/15</td>';
                }

                if (j == 9) {
                    temp_html =
                        '<td>' +
                        '<div class="actions">' +
                        '<button class="action-button action-button-edit" onclick="modify_and_save(this)">수정</button>' +
                        '<button class="action-button action-button-registration" onclick="registration_and_delete(this)">등록</button>' +
                        '</div>' +
                        '</td>';
                }

            }

            if (i == 3) {

                if (j == 0) {
                    temp_html = '<td>망치</td>';
                }

                if (j == 1) {
                    temp_html = '<td>C</td>';
                }

                if (j == 2) {
                    temp_html = '<td>20</td>';
                }

                if (j == 3) {
                    temp_html = '<td>8</td>';
                }

                if (j == 4) {
                    temp_html = '<td>8일</td>';
                }

                if (j == 5) {
                    temp_html = '<td>24/02/13</td>';
                }

                if (j == 6) {
                    temp_html = '<td>길승산업</td>';
                }

                if (j == 7) {
                    temp_html = '<td>12</td>';
                }

                if (j == 8) {
                    temp_html = '<td>24/02/03</td>';
                }

                if (j == 9) {
                    temp_html =
                        '<td>' +
                        '<div class="actions">' +
                        '<button class="action-button action-button-edit" onclick="modify_and_save(this)">수정</button>' +
                        '<button class="action-button action-button-registration" onclick="registration_and_delete(this)">등록</button>' +
                        '</div>' +
                        '</td>';
                }

            }

            new_cell.insertAdjacentHTML('afterbegin', temp_html);
        }
    }
}


/*-------------------------------산출 관리 리포트--------------------------------------*/
src = "https://www.gstatic.com/charts/loader.js";

google.charts.load('current', {packages: ['corechart']});

google.charts.load('current', {packages: ['corechart']});

function columnChart1() {

    var arr = [

        ['품목명', '총금액'],

        ['Unit', 740],

        ['Assy', 460],

        ['Part', 280],


    ];


    var dataTable = google.visualization.arrayToDataTable(arr);


    var options = {

        title: '현황 그래프',

        hAxis: {

            title: '현황 그래프',

            titleTextStyle: {

                color: 'black'

            }

        }

    };

    var objDiv = document.getElementById('column_chart_div1');

    var chart = new google.visualization.ColumnChart(objDiv);


    chart.draw(dataTable, options);

}

$(document).ready(function () {

    $('button').on('click', function () {

        columnChart1();

    });

});

/*------------------재고산출관리--------------------------*/
function table() {
    var tableList = document.getElementsByClassName("table_body")
    var inputHtml = [];

    $(document).ready(function () {

        $('button').on('click', function () {

            columnChart1();

        });

    });

    for (var i = 0; i < tableList.length; i++) {
        inputHtml.push(`<tr>
            <td class="table-body">A품목</td>
            <td class="table-body">asdew</td>
            <td class="table-body">asdew</td>
            <td class="table-body">asdew</td>
            <td class="table-body">asdew</td>
            <td class="table-body">80원</td>
            <td class="table-body" id="inventory"><input style="width:80px" type="number" value="5"></td>
            <td class="table-body">11</td>
            <td class="table-body"><input style="width:80px" type="number" value="34"></td>
            <td class="table-body">24324원</td>
            <td class="table-body"><button class="action-button action-button-registration">확인</button></td>
        </tr>`)
    }

    console.log(tableList);
    tableList[tableList.length - 1].innerHtml = inputHtml.join("");
}

/*------------------total-------------------------------*/
var state1 = 0;
var state2 = 0;
var state3 = 0;
var total = 0;

var clickTapList = new Array();
const tabList = document.getElementsByClassName("list");
const contents = document.getElementsByClassName("cont")
const contentList = document.getElementsByClassName("cont_area")
let activeCont = ''; /*현재 활성화 된 컨텐츠 (기본:#tab1 활성화)*/

/*탭 버튼 클릭 리스너 초기화 함수*/
function initializeTabListeners() {
    /*"조달관리" 탭 버튼에 대한 클릭 이벤트 리스너*/
    document.getElementById("part-filed").addEventListener('click', function () {
        resetStates();
        state1 = 1;
        updateButtonState("part-filed", state1);
    });

    /*"발주관리" 탭 버튼에 대한 클릭 이벤트 리스너*/
    document.getElementById("part-filed2").addEventListener('click', function () {
        resetStates();
        state2 = 2;
        updateButtonState("part-filed2", state2);
    });

    /*"자재관리" 탭 버튼에 대한 클릭 이벤트 리스너*/
    document.getElementById("part-filed3").addEventListener('click', function () {
        resetStates();
        state3 = 3;
        updateButtonState("part-filed3", state3);
    });

    /*"Total" 탭 버튼에 대한 클릭 이벤트 리스너*/
    document.getElementById("total").addEventListener('click', function () {
        resetStates();
        total = 4;
        updateButtonState("total", total);
    });
}

document.getElementById("part-filed").addEventListener('click', function () {
    resetStates();
    state1 = 1;
    updateButtonState("part-filed", state1);
});

document.getElementById("part-filed2").addEventListener('click', function () {
    resetStates();
    state2 = 2;
    updateButtonState("part-filed2", state2);
});

document.getElementById("part-filed3").addEventListener('click', function () {
    resetStates();
    state3 = 3;
    updateButtonState("part-filed3", state3);
});

document.getElementById("total").addEventListener('click', function () {
    resetStates();
    total = 4;
    updateButtonState("total", total);
});

function resetStates() {
    state1 = 0;
    state2 = 0;
    state3 = 0;

    total = 0;

    updateButtonState("part-filed", state1);
    updateButtonState("part-filed2", state2);
    updateButtonState("part-filed3", state3);
    updateButtonState("total", total);
}

function updateButtonState(buttonId, state) {
    var button = document.getElementById(buttonId);
    if (state === 1) {
        button.style.opacity = '100';
        button.innerHTML = `<span class="icon1">&#9660;</span> <strong>조달관리</strong>`;
        document.getElementById("procurement").style.display = "";
        document.getElementById("order").style.display = "none";
        document.getElementById("materials").style.display = "none";
    } else if (state === 2) {
        button.style.opacity = '100';
        button.innerHTML = `<span class="icon2">&#9660;</span> <strong>발주관리</strong>`;
        document.getElementById("procurement").style.display = "none";
        document.getElementById("order").style.display = "";
        document.getElementById("materials").style.display = "none";
    } else if (state === 3) {
        button.style.opacity = '100';
        button.innerHTML = `<span class="icon3">&#9660;</span> <strong>자재관리</strong>`;
        document.getElementById("procurement").style.display = "none";
        document.getElementById("order").style.display = "none";
        document.getElementById("materials").style.display = "block";
    } else if (state === 0) {
        button.style.opacity = '0.2';
        document.getElementById("part-filed").innerHTML = `<span class="icon1">&#9650;</span> 조달관리`
        document.getElementById("part-filed2").innerHTML = `<span class="icon2">&#9650;</span> 발주관리`
        document.getElementById("part-filed3").innerHTML = `<span class="icon3">&#9650;</span> 자재관리`
        document.getElementById("procurement").style.display = "none";
        document.getElementById("order").style.display = "none";
        document.getElementById("materials").style.display = "none";
    } else if (state === 4) {
        button.style.opacity = '100';
        document.getElementById("total").innerHTML = `<strong>Total</strong>`
        document.getElementById("procurement").style.display = "none";
        document.getElementById("order").style.display = "none";
        document.getElementById("materials").style.display = "none";
    }
}

function sendDateToChild(String) {
    const data = document.getElementById(String).innerHTML.valueOf();
    childComponent.receiveData(data);
}

const childComponent = {
    receiveData: function (data) {

        clickTapList.push(data);

        const tabList3 = [];
        const contList = [];

        var currentPage;

        for (var i = 0; i < clickTapList.length; i++) {

            if (clickTapList[i] === "품목정보등록") {
                currentPage = "ProductInformationRegistration"
            } else if (clickTapList[i] === "계약등록") {
                currentPage = "ContractRegistration"
            } else if (clickTapList[i] === "조달계획등록") {
                currentPage = "ProcurementPlanRegistration"
            } else if (clickTapList[i] === "발주서목록") {
                currentPage = "orderList"
            } else if (clickTapList[i] === "발주서발행") {
                currentPage = "orderRegister"
            } else if (clickTapList[i] === "현황관리") {
                currentPage = "StatusManagementReport"
            } else if (clickTapList[i] === "입고처리") {
                currentPage = "ReceivingProcess"
            } else if (clickTapList[i] === "거래명세서") {
                currentPage = "TradingStatement"
            } else if (clickTapList[i] === "재고산출처리") {
                currentPage = "stockDelivery"
            } else if (clickTapList[i] === "현황리포트") {
                currentPage = "existence"
            } else if (clickTapList[i] === "발주품목선택") {
                currentPage = "ProductSelect"
            } else if (clickTapList[i] === "진척검수등록") {
                currentPage = "orderInspect1_1"
            }


            if (i === 0) {
                tabList3.push(`<div id = "tap1">
                            <div class="tabClass" id="is_on" data-include-path='${currentPage}'>
                                <div class="btn">${clickTapList[i]}<div onclick="closePage('${clickTapList[i]}','${i}')">❌</div></div>
                            </div>
                        </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'>testcont0</div>`)
            } else if (clickTapList[0] === clickTapList[i]) {
                tabList3.push(`<div class="tabClass" id="is_on">
                            <div class="btn">${clickTapList[i]}<div onclick="closePage('${clickTapList[i]}','${i}')">❌</div></div>
                         </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'>testcont${i}</div>`)
            } else {
                tabList3.push(`<div class="tabClass" id="is_on" >
                            <div class="btn">${clickTapList[i]}<div onclick="closePage('${clickTapList[i]}','${i}')">❌</div></div>
                            </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'>testcont${i}</div>`)
            }
        }
        tabList[tabList.length - 1].innerHTML = tabList3.join("");
        contentList[contentList.length - 1].innerHTML = contList.join("");

        let SoYouCanSeeWhatWasPressed = document.getElementById("is_on")

        LoadHTMLOfThePageWithClickedPageData(currentPage, i);

        for (let k = 0; k < tabList3.length; k++) {
            var clickEvent = document.getElementsByClassName("btn");
            clickEvent[k].addEventListener('click', function (e) {
                e.preventDefault();
                for (let j = 0; j < tabList3.length; j++) {
                    SoYouCanSeeWhatWasPressed.classList.remove('is_on')
                    contents[j].style.display = 'none';
                }
                SoYouCanSeeWhatWasPressed.classList.add('is_on')
                contents[k].style.display = 'block';
                LoadHTMLOfThePage(contents[k])
            });
        }
        setCookie("clickTapList", clickTapList, 7)
    }
};

function LoadHTMLOfThePageWithClickedPageData(clickData, contCnt) {
    console.log("보내주는 데이터 값 확인하기",clickData,contCnt)
    var LoadForHtml = document.getElementById(clickData);
    var contList = document.getElementsByClassName("cont");

    var selectCont = contList[contCnt - 1];
    for (var i = 0; i < contList.length; i++) {
        contList[i].style.display = "none";
    }
    selectCont.style.display = "block";

    var includePath = LoadForHtml.dataset.includePath;
    if (includePath) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                selectCont.innerHTML = this.responseText;
            }
        };
        xhttp.open('GET', includePath, true);
        xhttp.send();
    }
    ;
}

function LoadHTMLOfThePage(PageData) {
    console.log("보내주는 데이터 값 확인하기",PageData)
    var includePath = PageData.dataset.includePath;
    if (includePath) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                PageData.innerHTML = this.responseText;
            }
        };
        xhttp.open('GET', includePath, true);
        xhttp.send();
    }
    ;
}

/*쿠키 저장하기(이름, 값, 저장일 수)*/
function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

/* 쿠키를 이름값으로 가져오기*/
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function loadClickTapListFromCookie() {
    var clickTapListString = getCookie("clickTapList");
    if (clickTapListString) {
        return clickTapListString;
    }
}


function checkCookieExistence() {
    var cookies = document.cookie.split(';');
    return !!cookies;
}


window.onload = function () {
    loadClickTapListFromCookie();
    initializeTabListeners();
};


(function RetrieveClickTapListInformationFromCookie() {
    if (checkCookieExistence()) {
        var cookieList = loadClickTapListFromCookie().split(",");
        for (let i = 0; i < cookieList.length; i++) {
            childComponent.receiveData(cookieList[i]);
        }
    }
})()

function closePage(pageData, cnt) {
    console.log(pageData, cnt);
    // let PressTheDeleteButtonClickTapList = clickTapList.filter(value=> value!==pageData);
    // setCookie("clickTapList", PressTheDeleteButtonClickTapList, 7);
    for (var k = 0; k < tabList.length; k++) {
        console.log(tabList[k]);
    }
    console.log("탭리스트 Html정보 확인해보기 : ");
    console.log("여기까지는 작동 중인지 확인해보기");
    // contentList[cnt].innerHTML.style.display="none"
};


function test() {
    console.log("임의로 테스트 하는 함수");
}
