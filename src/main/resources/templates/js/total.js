/*--------------------입고 처리--------------------*/
function openPopup(popupId) {
    var popup = document.getElementById(popupId);
    popup.style.display = "block";
}

function closePopup(popupId) {
    var popup = document.getElementById(popupId);
    popup.style.display = "none";
}


/*----------------------발주 목록------------------*/
function openModal() {
    console.log("눌리는거 확인")
    var modal =  document.getElementById("myModal");
    modal.style.display="block";
}

function closeModal() {
    var modal =  document.getElementById("myModal");
    modal.style.display="none";
}

/*---------------------계약등록-------------------*/
function modify_and_save(tag) {

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


function registration_and_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function p_code_value(p_code) {

    console.log(p_code);
}

function b_code_value(b_code) {

    console.log(b_code);
}

function addRow() {

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

            temp_html = '<td><button>계약서 업로드</button></td>';
        }

        if(i == 8) {

            temp_html =
                '<td>' +
                '<div class="actions">' +
                '<button class="action-button action-button-edit" onclick="modify_and_save(this)">수정</button>' +
                '<button class="action-button action-button-delete" onclick="registration_and_delete(this)">삭제</button>' +
                '</div>' +
                '</td>';
        }

        new_cell.insertAdjacentHTML('afterbegin', temp_html);

    }


}


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
        target.disabled = false;

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
    } else {

        //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

        num.contentEditable = "true";
        date.contentEditable = "true";

        target.disabled = true;
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

    if(state == "before_proceeding") {

        table1.style.display = "inline";
        table2.style.display = "none";
        table3.style.display = "none";

    }

    else if(state == "order_proceeding") {

        table1.style.display = "none";
        table2.style.display = "inline";
        table3.style.display = "none";

    }

    else if(state == "order_completed") {

        table1.style.display = "none";
        table2.style.display = "none";
        table3.style.display = "inline";

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
        target.disabled = false;

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
    } else {

        //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

        num.contentEditable = "true";
        date.contentEditable = "true";

        target.disabled = true;
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

    if(state == "before_proceeding") {

        table1.style.display = "inline";
        table2.style.display = "none";
        table3.style.display = "none";

    }

    else if(state == "order_proceeding") {

        table1.style.display = "none";
        table2.style.display = "inline";
        table3.style.display = "none";

    }

    else if(state == "order_completed") {

        table1.style.display = "none";
        table2.style.display = "none";
        table3.style.display = "inline";

    }


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
        document.getElementById("materials").style.display = "";
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
                currentPage = "품목정보등록.html"
            } else if (clickTapList[i] === "계약등록") {
                currentPage = "계약등록.html"
            } else if (clickTapList[i] === "조달계획등록") {
                currentPage = "조달계획등록.html"
            } else if (clickTapList[i] === "발주서목록") {
                currentPage = "orderList.html"
            } else if (clickTapList[i] === "발주서발행") {
                currentPage = "orderRegister.html"
            } else if (clickTapList[i] === "현황관리") {
                currentPage = "StatusManagementReport.html"
            } else if (clickTapList[i] === "입고처리") {
                currentPage = "ReceivingProcess.html"
            } else if (clickTapList[i] === "거래명세서") {
                currentPage = "TradingStatement.html"
            } else if (clickTapList[i] === "재고산출처리<br> 현황리포트") {
                currentPage = "existence.html"
            }

            if (i === 0) {
                tabList3.push(`<div id = "tap1">
                            <div class="tabClass" id="is_on">
                                <div class="btn">${clickTapList[i]}</div>
                            </div>
                        </div>
                        `)
                contList.push(`
                              <div class="cont" data-include-path='${currentPage}'>testcont0</div>
                            `)
            } else if (clickTapList[0] === clickTapList[i]) {
                tabList3.push(`<div class="tabClass" id="is_on">
                            <div class="btn">${clickTapList[i]}</div>
                         </div>
                        `)
                contList.push(`
                              <div class="cont" data-include-path='${currentPage}'>testcont${i}</div>
                            `)
            } else {
                tabList3.push(`<div class="tabClass" id="is_on">
                            <div class="btn">${clickTapList[i]}</div>
                        </div>
                        `)
                contList.push(`
                              <div class="cont" data-include-path='${currentPage}'>testcont${i}</div>
                            `)
            }
        }
        tabList[tabList.length - 1].innerHTML = tabList3.join("");
        contentList[contentList.length - 1].innerHTML = contList.join("");

        var SoYouCanSeeWhatWasPressed = document.getElementById("is_on")

        for (let k = 0; k < tabList3.length; k++) {
            var clickEvent = document.getElementsByClassName("btn");
            clickEvent[k].addEventListener('click', function (e) {
                e.preventDefault();
                for (let j = 0; j < tabList3.length; j++) {
                    SoYouCanSeeWhatWasPressed.classList.remove('tabClass')
                    contents[j].style.display = 'none';
                }
                SoYouCanSeeWhatWasPressed.classList.add('tabClass')
                activeCont = document.getElementsByClassName("cont");
                activeCont[k].style.display = 'block';
                console.log(contents[k]);
                LoadHTMLOfThePage(activeCont[k])
            });
        }
        setCookie("clickTapList",clickTapList,7)
    }
};


function LoadHTMLOfThePage(PageData) {
    var allElements = document.getElementsByTagName("*");
    Array.prototype.forEach.call(allElements, function() {
        var includePath =PageData.dataset.includePath;
        if (includePath) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    PageData.innerHTML = this.responseText;
                }
            };
            xhttp.open('GET', includePath, true);
            xhttp.overrideMimeType('mime');
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
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function loadClickTapListFromCookie() {
    var clickTapListString = getCookie("clickTapList");
    if (clickTapListString) {
        console.log("현재 저장된 clickTapListString으로 보여주기 : ",clickTapListString)
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
    if(checkCookieExistence()) {
        var cookieList = loadClickTapListFromCookie().split(",");
        for(let i=0; i<cookieList.length; i++) {
            childComponent.receiveData(cookieList[i]);
        }
    }
})()
