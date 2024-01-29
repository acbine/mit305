/*------------------total-------------------------------*/
var state1 = 0;
var state2 = 0;
var state3 = 0;
var total = 0;

var clickTapList = new Array();
const tabList = document.getElementsByClassName("tap_list");
const taps = document.getElementsByClassName("tabClass")
const contents = document.getElementsByClassName("cont")
const contentList = document.getElementsByClassName("cont_area")

var arrayClick = []

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

    var data;

    if (String === "item") {
        data = "품목정보등록"
    } else if (String === "contract") {
        data = "계약등록"
    } else if (String === "procurementPlan") {
        data = "조달계획등록"
    } else if (String === "orderList") {
        data = "발주서목록"
    } else if (String === "orderPublish") {
        data = "발주서발행"
    } else if (String === "current") {
        data = "현황관리"
    } else if (String === "materialsProcess") {
        data = "입고처리"
    } else if (String === "TradingStatement") {
        data = "거래명세서"
    } else if (String === "InventoryCalculation") {
        data = "재고산출처리"
    } else if (String === "StatusManagement") {
        data = "현황리포트"
    } else if (String === "ProductSelect") {
        data = "발주품목선택"
    }

    childComponent.receiveData(data);
}

const childComponent = {
    receiveData: function (data) {
        for (var check = 0; check < clickTapList.length; check++) {
            if (clickTapList[check] !== data) {
                clickTapList.push(data);
            } else {
                break;
            }
        }
        clickTapList.push(data);
        var SetClickTapList = new Set(clickTapList);

        arrayClick = Array.from(SetClickTapList);

        const tabList3 = [];
        const contList = [];

        var currentPage;

        for (var i = 0; i < arrayClick.length; i++) {

            if (arrayClick[i] === "품목정보등록") {
                currentPage = "ProductInformationRegistration"
            } else if (arrayClick[i] === "계약등록") {
                currentPage = "ContractRegistration"
            } else if (arrayClick[i] === "조달계획등록") {
                currentPage = "ProcurementPlanRegistration"
            } else if (arrayClick[i] === "발주서목록") {
                currentPage = "orderList"
            } else if (arrayClick[i] === "발주서발행") {
                currentPage = "orderRegister"
            } else if (arrayClick[i] === "현황관리") {
                currentPage = "StatusManagementReport"
            } else if (arrayClick[i] === "입고처리") {
                currentPage = "ReceivingProcess"
            } else if (arrayClick[i] === "거래명세서") {
                currentPage = "TradingStatement"
            } else if (arrayClick[i] === "재고산출처리") {
                currentPage = "stockDelivery"
            } else if (arrayClick[i] === "현황리포트") {
                currentPage = "existence"
            }

            if (i === 0) {
                tabList3.push(`
                            <div class="tabClass" id="first" data-include-path='${currentPage}'>
                                <div class="btn">${arrayClick[i]}<div onclick="closePage('${arrayClick[i]}',0)">❌</div></div>
                            </div>
                      `)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'></div>`)
            } else if (arrayClick[0] === arrayClick[i]) {
                tabList3.push(`<div class="tabClass">
                            <div class="btn">${arrayClick[i]}<div onclick="closePage('${arrayClick[i]}','${i}')">❌</div></div>
                         </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'></div>`)
            } else {
                tabList3.push(`<div class="tabClass">
                            <div class="btn">${arrayClick[i]}<div onclick="closePage('${arrayClick[i]}','${i}')">❌</div></div>
                            </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'></div>`)
            }

        }
        tabList[tabList.length - 1].innerHTML = tabList3.join("");
        contentList[contentList.length - 1].innerHTML = contList.join("");

        LoadHTMLOfThePageWithClickedPageData(currentPage, i);

        var transFromITOStringI = String(i);
        var currentPageInfoOfDirectClick = [currentPage, transFromITOStringI]
        setCookie("currentPageInfoOfDirectClick", currentPageInfoOfDirectClick, 7)

        for (let k = 0; k < tabList3.length; k++) {
            var clickEvent = document.getElementsByClassName("btn");
            clickEvent[k].addEventListener('click', function (e) {
                e.preventDefault();
                for (let j = 0; j < tabList3.length; j++) {
                    contents[j].style.display = 'none';
                    taps[j].style.background = "#D3E3FD";
                }
                taps[k].style.background = "#05AFF1";
                contents[k].style.display = 'block';

                var pageInfoToString = String(k);

                LoadHTMLOfThePage(k);

                setCookie("currentPageInfoOfTapListClick", pageInfoToString, 7);
            });
        }
        setCookie("clickTapList", arrayClick, 7)
    }
};


function LoadHTMLOfThePageWithClickedPageData(clickData, contCnt) {

    setCookie("openInfo", "Direct", 7);
    var LoadForHtml = document.getElementById(clickData);
    var contList = document.getElementsByClassName("cont");


    for (var j = 0; j < contents.length; j++) {
        contents[j].style.display = "none";
    }

    var selectCont = contList[contCnt - 1];

    selectCont.style.display = "block";

    var includePath = LoadForHtml.dataset.includePath;
    if (includePath) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                loadJS(includePath);
                selectCont.innerHTML = this.responseText;
            }
        };
        xhttp.open('GET', includePath, true);
        xhttp.send();
    }
}

function LoadHTMLOfThePage(cnt) {
    var PageData = contents[cnt];

    setCookie("openInfo", "Tap", 7);

    for (var j = 0; j < contents.length; j++) {
        contents[j].style.display = "none";
    }

    var includePath = PageData.dataset.includePath;
    if (includePath) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                loadJS(includePath);
                PageData.style.display = "block";
                PageData.innerHTML = this.responseText;
            }
        };
        xhttp.open('GET', includePath, true);
        xhttp.send();
    }
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

function loadCurrentPageWithDirectClickFromCookie() {
    var currentPageInfoOfDirectClick = getCookie("currentPageInfoOfDirectClick");
    if (currentPageInfoOfDirectClick) {
        return currentPageInfoOfDirectClick;
    }
}

function loadCurrentPageWithTapListClickFromCookie() {
    var currentPageInfoOfTapListClick = getCookie("currentPageInfoOfTapListClick");
    if (currentPageInfoOfTapListClick) {
        return currentPageInfoOfTapListClick;
    }
}

function checkingState() {
    var openInfo = getCookie("openInfo");
    if (openInfo) {
        return openInfo;
    }
}

function checkCookieExistence() {
    var cookies = document.cookie.split(';');
    return !!cookies;
}


window.onload = function () {
    checkingState();
    loadCurrentPageWithDirectClickFromCookie();
    loadCurrentPageWithTapListClickFromCookie();
    loadClickTapListFromCookie();
    initializeTabListeners();
};


(function RetrieveClickTapListInformationFromCookie() {
    if (checkCookieExistence()) {

        var cookieList = loadClickTapListFromCookie().split(",");
        var State = checkingState();

        for (let i = 0; i < cookieList.length; i++) {
            childComponent.receiveData(cookieList[i]);
        }
        if (State === "Direct") {
            var directInfo = loadCurrentPageWithDirectClickFromCookie().split(",")
            LoadHTMLOfThePageWithClickedPageData(directInfo[0], parseInt(directInfo[1]));
        } else if (State === "Tap") {
            var data = parseInt(loadCurrentPageWithTapListClickFromCookie())
            LoadHTMLOfThePage(data);
        }
    }
})()

function closePage(pageData, cnt) {
    var taps = document.getElementsByClassName("tabClass");
    var firstTap = document.getElementsByClassName("tabClassOne")
    var conts = document.getElementsByClassName("cont");

    var closeData = arrayClick.indexOf(pageData)

    clickTapList = clickTapList.filter((value) => value !== pageData);

    arrayClick.splice(closeData, 1);

    taps[cnt].style.display = "none";
    conts[cnt].style.display = "none";

    setCookie("clickTapList", arrayClick, 7)

}


function test() {
    console.log("임의로 테스트 하는 함수");
}

function loadJS(includePath) {
    // 스크립트를 추가할 경로와 제거할 경로를 정의
    var scriptPaths = {
        "ProductInformationRegistration": ["/js/ProductInformationRegistration.js"],
        "ContractRegistration": ["/js/ContractRegistration.js", "/js/ContractRegistrationModal.js", "/js/Registration.js"],
        "ProcurementPlanRegistration": ["/js/ProcurementPlanRegistration.js"],
        "orderRegister": ["/js/orderRegister.js", "/js/order.js"],
        "orderList": ["/js/orderListPopup.js", "/js/order.js"],
        "StatusManagementReport": ["/js/StatusManagementReport.js"],
        "stockDelivery": ["/js/stockDelivery.js"],
        "existence": ["/js/existence.js"],
        "ReceivingProcess": ["/js/ReceivingProcessing.js"],
        "TradingStatement": ["/js/TradingStatementModal.js", "/js/TradingStatement.js"]
    };


    // 주어진 includePat// 주어진 includePath에 따라 스크립트를 추가 또는 제거
    if (scriptPaths[includePath]) {
        scriptPaths[includePath].forEach(function (path) {
            addScript(path);
        });
    }


    // 주어진 includePath 이외의 경우 해당 스크립트를 모두 제거
    for (var path in scriptPaths) {
        scriptPaths[path].forEach(function (pathToRemove) {
            if (pathToRemove !== includePath) {
                removeScript(pathToRemove);
            }
        });

    }
}


function addScript(src) {
    var script = document.createElement("script");
    script.async = true;
    script.src = src;
    document.head.appendChild(script);
}

function removeScript(scriptElement) {
    if (scriptElement && scriptElement.parentNode) {
        scriptElement.parentNode.removeChild(scriptElement);
    }
}
