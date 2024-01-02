/*--------------------발주 목록(orderList)--------------------*/
function openPopup(popupId) {
    var popup = document.getElementById(popupId);
    popup.style.display = "block";
}

function closePopup(popupId) {
    var popup = document.getElementById(popupId);
    popup.style.display = "none";
}

function showHiddenTable(){
    document.querySelector('.hidden').classList.remove('hidden');
    document.querySelector('.confirm').classList.add('hidden');
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
    console.log("이거 실행된느건가?")
    $(".TradingmyModal").css('display', 'block');
    window.print();
    $(".TradingmyModal").css('display', 'none');
}

/*---------------------계약등록-------------------*/

 function contract_modify_and_save(tag) {

        const tr = tag.closest("tr");

        const cell_0 = tr.children[0];
        const cell_5 = tr.children[5];
        const cell_6 = tr.children[6];


        if (cell_0.contentEditable == "true") {

            cell_0.contentEditable = "false";
            cell_5.contentEditable = "false";
            cell_6.contentEditable = "false";

            tag.innerText = "수정";

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
        } else {

            //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

            cell_0.contentEditable = "true";
            cell_5.contentEditable = "true";
            cell_6.contentEditable = "true";

            tag.innerText = "수정 완료";

            cell_0.focus();
        }
    }

    function contract_registration_and_delete(tag) {

        const tr = tag.closest("tr");
        tr.remove();
    }

    <!-- 선택한 품목코드로 품목명 검색-->
    function p_code_value(p_code) {

        console.log(p_code);
    }

    <!-- 선택한 사업자번호로 기업명 검색-->
    function b_code_value(b_code) {

        console.log(b_code);
    }

    function contract_add_product(tag) {

        const table = document.getElementById('product');

        const newRow = table.insertRow(2);

        for(let i = 0; i < 9; i++) {
            const new_cell = newRow.insertCell(i);
            let temp_html = '';

            if(i == 1) {

                temp_html =
                '<select onChange="p_code_value(this.options[this.selectedIndex].value)">' +
                    '<option value="p_code1">A</option>' +
                    '<option value="p_code2">ab42e</option>' +
                    '<option value="p_code3">B</option>' +
                '</select>' +

                '<button> 확인 </button>';

            }

            if(i == 8) {

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


    function contract_addRow() {

        const table = document.getElementById('product');
        const new_row = table.insertRow();

        const cell_length = table.rows[0].cells.length;

        for(let i = 0; i < cell_length; i++) {
            const new_cell = new_row.insertCell(i);
            let temp_html = '';

            if(i == 1) {

                temp_html =
                '<select onChange="p_code_value(this.options[this.selectedIndex].value)">' +
                    '<option value="p_code1">A</option>' +
                    '<option value="p_code2">ab42e</option>' +
                    '<option value="p_code3">B</option>' +
                '</select>' +

                '<button>확인</button>';

            }

             if(i == 3) {

                temp_html =
                '<select onChange="b_code_value(this.options[this.selectedIndex].value)">' +
                    '<option value="b_code1">사업자 번호1</option>' +
                    '<option value="b_code2">사업자 번호2</option>' +
                    '<option value="b_code3">사업자 번호3</option>' +
                '</select>' +

                '<button>확인</button>';

            }




            if(i == 7) {

                    temp_html = '<td>'+
                               '<input type="file" accept="image/png, image/jpg">' +
                               '</td>';
            }

            if(i == 8) {

                temp_html =
                    '<td>' +
                        '<div class="actions">' +
                            '<button class="action-button action-button-check" onclick="contract_add_product(this)">계약 품목 추가</button>'+
                            '<button class="action-button action-button-edit" onclick="contract_modify_and_save(this)">수정</button>'+
                            '<button class="action-button action-button-delete" onclick="contract_registration_and_delete(this)">삭제</button>'+
                        '</div>' +
                    '</td>';
            }

             new_cell.insertAdjacentHTML('afterbegin', temp_html);

        }


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


    function table_change(state) {

         if(state == "need_plan") {

            plan_table.style.display = "inline";
            table1.style.display = "none";
            table2.style.display = "none";
            table3.style.display = "none";

        }


        if(state == "before_proceeding") {

            plan_table.style.display = "none";
            table1.style.display = "inline";
            table2.style.display = "none";
            table3.style.display = "none";

        }

        else if(state == "order_proceeding") {

            plan_table.style.display = "none";
            table1.style.display = "none";
            table2.style.display = "inline";
            table3.style.display = "none";

        }

        else if(state == "order_completed") {

            plan_table.style.display = "none";
            table1.style.display = "none";
            table2.style.display = "none";
            table3.style.display = "inline";

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

$(document).ready(function(){

    $('button').on('click', function(){

        columnChart1();

    });

});

$(document).ready(function () {

    $('button').on('click', function () {

        columnChart1();

    });

});


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
var htmlData;
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


        for (var i = 0; i < clickTapList.length; i++) {

            var currentPage;

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
            } else if(clickTapList[i]==="현황리포트") {
                currentPage = "existence"
            }

            if (i === 0) {
                tabList3.push(`<div id = "tap1">
                            <div class="tabClass" id="is_on">
                                <div class="btn">${clickTapList[i]}<div style="color: red">X</div></div>
                            </div>
                        </div>
                        `)
                contList.push(`
                              <div class="cont" data-include-path='${currentPage}'>testcont0</div>
                            `)
            } else if (clickTapList[0] === clickTapList[i]) {
                tabList3.push(`<div class="tabClass" id="is_on">
                            <div class="btn">${clickTapList[i]}<div style="redcolor: red">X</div></div>
                         </div>
                        `)
                contList.push(`
                              <div class="cont" data-include-path='${currentPage}'>testcont${i}</div>
                            `)
            } else {
                tabList3.push(`<div class="tabClass" id="is_on">
                            <div class="btn">${clickTapList[i]}<div style="color: red">X</div></div>
                        </div>
                        `)
                contList.push(`
                              <div class="cont" data-include-path='${currentPage}'>testcont${i}</div>
                            `)
            }
        }
        tabList[tabList.length - 1].innerHTML = tabList3.join("");
        contentList[contentList.length - 1].innerHTML = contList.join("");

        let SoYouCanSeeWhatWasPressed = document.getElementById("is_on")

        for (let k = 0; k < tabList3.length; k++) {
            var clickEvent = document.getElementsByClassName("btn");
            clickEvent[k].addEventListener('click', function (e) {
                e.preventDefault();
                for (let j = 0; j < tabList3.length; j++) {
                    SoYouCanSeeWhatWasPressed.classList.remove('tabClass')
                    contents[j].style.display = 'none';
                }
                SoYouCanSeeWhatWasPressed.classList.add('tabClass')
                contents[k].style.display = 'block';
                htmlData = contents[k];
                LoadHTMLOfThePage(htmlData);
            });
        }
        setCookie("clickTapList", clickTapList, 7)
    }
};

console.log(htmlData)


function LoadHTMLOfThePage(PageData) {
    var allElements = document.getElementsByTagName("*");
    Array.prototype.forEach.call(allElements, function () {
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
    });

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

function test() {
    console.log("임의로 테스트 하는 함수");
}
