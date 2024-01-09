/*------------------total-------------------------------*/
var state1 = 0;
var state2 = 0;
var state3 = 0;
var total = 0;

var clickTapList = new Array();
const tabList = document.getElementsByClassName("list");
const contents = document.getElementsByClassName("cont")
const contentList = document.getElementsByClassName("cont_area")
var OpenPageState;

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
    } else if (String === "진척검수등록") {
        data = "orderInspect1_1"
    }

    childComponent.receiveData(data);
}

const childComponent = {
    receiveData: function (data) {
        clickTapList.push(data);
        var SetClickTapList = new Set(clickTapList);

        var arrayClick = Array.from(SetClickTapList);


        const tabList3 = [];
        const contList = [];

        var currentPage;
        
        for (var i=0; i<arrayClick.length; i++) {

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
            } else if (arrayClick[i] === "발주품목선택") {
                currentPage = "ProductSelect"
            } else if (arrayClick[i] === "진척검수등록") {
                currentPage = "orderInspect1_1"
            }

            if (i===0) {
                tabList3.push(`<div id = "tap1">
                            <div class="tabClass" id="is_on" data-include-path='${currentPage}'>
                                <div class="btn">${arrayClick[i]}<div onclick="closePage('${arrayClick[i]}',0)">❌</div></div>
                            </div>
                        </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'>testcont</div>`)
            } else if (arrayClick[0] === arrayClick[i]) {
                tabList3.push(`<div class="tabClass" id="is_on">
                            <div class="btn">${arrayClick[i]}<div onclick="closePage('${arrayClick[i]}','${i}')">❌</div></div>
                         </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'>testcont</div>`)
            } else {
                tabList3.push(`<div class="tabClass" id="is_on" >
                            <div class="btn">${arrayClick[i]}<div onclick="closePage('${arrayClick[i]}','${i}')">❌</div></div>
                            </div>`)
                contList.push(`<div class="cont" data-include-path='${currentPage}' id = '${currentPage}'>testcont</div>`)
            }

        }
        tabList[tabList.length - 1].innerHTML = tabList3.join("");
        contentList[contentList.length - 1].innerHTML = contList.join("");


        console.log("실행순서 확인 : 여기는 리스트로 불러온 다이랙트")
        LoadHTMLOfThePageWithClickedPageData(currentPage, i);

        var transFromITOStringI = String(i);
        

        var currentPageInfoOfDirectClick = [currentPage,transFromITOStringI]

        setCookie("currentPageInfoOfDirectClick",currentPageInfoOfDirectClick,7)

        let SoYouCanSeeWhatWasPressed = document.getElementById("is_on")

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

                var pageInfoToString = String(k);

                console.log("실행순서 확인 : 여기는 리스트로 불러온 탭")
                LoadHTMLOfThePage(k);

                console.log("쿠키에 저장되는 값 확인하기", pageInfoToString);

                setCookie("currentPageInfoOfTapListClick",pageInfoToString,7);
            });
        }
        setCookie("clickTapList", arrayClick, 7)
    }
};


function LoadHTMLOfThePageWithClickedPageData(clickData, contCnt) {
    console.log("넘어온 click Data 확인 : ",clickData,"넘어온 contCnt 값 확인",contCnt);
    setCookie("openInfo","Direct",7);
    var LoadForHtml = document.getElementById(clickData);
    var contList = document.getElementsByClassName("cont");


    for(var j = 0; j<contents.length; j++) {
        contents[j].style.display = "none";
    }

    var selectCont = contList[contCnt - 1];

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

function LoadHTMLOfThePage(cnt) {
    var PageData =  contents[cnt];
    console.log("tap 페이지 불러오기 실행 (넘어온 데이터) : ", cnt)
    setCookie("openInfo","Tap",7);

    for(var j = 0; j<contents.length; j++) {
        contents[j].style.display = "none";
    }

    var includePath = PageData.dataset.includePath;
    if (includePath) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                PageData.style.display = "block";
                PageData.innerHTML = this.responseText;
            }
        };
        xhttp.open('GET', includePath, true);
        xhttp.send();
    };
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
    if(currentPageInfoOfDirectClick) {
        return currentPageInfoOfDirectClick;
    }
}

function loadCurrentPageWithTapListClickFromCookie() {
    var currentPageInfoOfTapListClick = getCookie("currentPageInfoOfTapListClick");
    if(currentPageInfoOfTapListClick) {
        return currentPageInfoOfTapListClick;
    }
}

function checkingState() {
    var openInfo = getCookie("openInfo");
    if(openInfo) {
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
        console.log("함수가 실행 중인가?")
        var cookieList = loadClickTapListFromCookie().split(",");
        var State = checkingState();
        console.log("State 값 확인해보기 :", State)
        for (let i = 0; i < cookieList.length; i++) {
            console.log(cookieList[i]);
            childComponent.receiveData(cookieList[i]);
        }
        if(State==="Direct"){
            console.log("다이렉트는 실행 됨")
            console.log("실행순서 확인 : 여기는 다이랙트")
            var directInfo =  loadCurrentPageWithDirectClickFromCookie().split(",")
            LoadHTMLOfThePageWithClickedPageData(directInfo[0],parseInt(directInfo[1]));
            console.log("이 밑으로는 실행 중이가?")

        } else if(State==="Tap"){
            console.log("탭도 실행됨")
            console.log("실행순서 확인 : 여기는 탭")
            var data = parseInt(loadCurrentPageWithTapListClickFromCookie())
            console.log("데이터 값 확인해보기",data)
            LoadHTMLOfThePage(data);
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



document.write(`<script src="/js/existence.js"></script>`)
document.write(`<script src="/js/stockDelivery.js"></script>`)
document.write(`<script src="/js/TradingStatementModal.js"></script>`)
document.write(`<script src="/js/Registration.js"></script>`)
document.write(`<script src="/js/ReceivingProcessing.js"></script>`)
document.write(`<script src="/js/TradingStatement.js"></script>`)
document.write(`<script src="/js/order.js"></script>`)
document.write(`<script src="/js/StatusManagementReport.js"></script>`)
