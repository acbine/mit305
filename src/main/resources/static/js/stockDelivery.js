/*------------------재고산출관리--------------------------*/

function findRelease() {
    var selectData = document.getElementById("searchNameOrCode");
    var select = selectData.options[selectData.selectedIndex].value
    var searchData = document.getElementById("searchWord");
    var constraints = searchData.value;
    var state;

    if(select==="ProductName") {
        state = 0;
        var formData = {"state": state,"constraints" : constraints}
        $.ajax({
            url:'searchStockDelivery?&state='+state+'&constraints='+constraints,
            type : 'get',
            data: {},
            success:function (info){
                console.log("성공");
                console.log("데이터 정보 확인 : ",info)
                uploadHtml(info);
                console.log("함수가 다 실행되면 이 함수 실행");
            },
            error:function (info) {
                console.log("에러 받아오는 데이터 확인하기 : ", info);
            }
        });
    } else if(select==="ProductCode") {
        state = 1;
        $.ajax({
            url:'searchStockDelivery?&state='+state+'&constraints='+constraints,
            type : 'get',
            data: {},
            success:function (info){
                console.log("성공");
                console.log("데이터 정보 확인 : ",info)
                uploadHtml(info);
                console.log("함수가 다 실행되면 이 함수 실행");
            },
            error:function (info) {
                console.log("에러 받아오는 데이터 확인하기 : ", info);
            }
        });

    }
}

function submitToRelease() {
    var releaseHTML = document.getElementById("release");
    var releaseData = document.getElementById("release").value;
    var formData = {"release": releaseData}

    $.ajax({
    url:'/total/stockDelivery',
        type : 'post',
        contentType : 'application/json',
        data:JSON.stringify(formData),
        success:function(data){
        addTable(releaseHTML,data)
        console.log("성공");
        },
        error:function () {
            console.log("실패");
        }
    });

    return false;
}

function addTable(releaseHTML,data) {
    var table = document.getElementsByClassName("table-body");
    console.log("table정보 확인하기 : ", table[0].innerHTML);
    console.log("받아온 html정보 확인하기 : ",releaseHTML);
    console.log("받아온 데이터 형태확인하기 : ", data);
    var releaseCNT = data.ReleaseProcess.releaseCNT;
    var existence = data.existence;
    var releaseProcess = data.ReleaseProcess;
    console.log("releaseCNT정보 확인 , : ",releaseCNT+"existence 정보확인 : " + existence);

    releaseHTML.innerHTML = `<td class="table-body" id="inventory"><input style="width:80px" type="number" value="${releaseCNT}" name="release"></td>`
    table[9].innerHTML = `<td class="table-body">${existence}</td>`
}

function uploadHtml(data) {
    var table = document.getElementsByClassName("table_body");
    console.log("바뀌기 전 table 정보 확인하기 : ",table[0]);
    var inputHtml = [];
    var releaseInfo = data.releaseInfo;
    console.log(releaseInfo.length)
    for(var i=0; i<releaseInfo.length;i++){
        inputHtml.push(`
        <tr>
            <td class="table-body">${releaseInfo[i].productName}</td>
            <td class="table-body">${releaseInfo[i].product_code}</td>
            <td class="table-body">${releaseInfo[i].departureDate}</td>
            <td class="table-body">${releaseInfo[i].texture}</td>
            <td class="table-body">${releaseInfo[i].height}</td>
            <td class="table-body">${releaseInfo[i].length}</td>
            <td class="table-body">${releaseInfo[i].weight}</td>
            <td class="table-body" id="supplyPrice">${releaseInfo[i].contract_pay}</td>
            <td class="table-body"><input style="width:80px" type="number" value="${releaseInfo[i].release}" id="release"></td>
            <td class="table-body">${releaseInfo[i].store}</td>
            <td class="table-body">${releaseInfo[i].existence}</td>
            <td class="table-body">${releaseInfo[i].existence_price}</td>
            <td class="table-body">
                <button class="action-button action-button-registration" onclick="submitToRelease()">확인</button>
            </td>
        </tr>`);
    }

    table[table.length-1].innerHTML = inputHtml.join("");
    console.log("변경된 html 정보보기 : ",table[0]);
}
