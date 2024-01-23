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
                console.log("받아오는 데이터 정보 값이 다 잘들어왔는지 확인",info)
                console.log("성공");
                uploadHtml(info);
                console.log("함수가 다 실행되면 이 함수 실행");
            },
            error:function (info) {
                console.log("에러 받아오는 데이터 확인하기 : ", info);
            }
        });

    }
}

function submitToRelease(clickNum,procurementPlan_code) {
    var tableData = document.getElementById("release_table").getElementsByTagName("tr");
    var releaseData = tableData[clickNum].getElementsByTagName("td")[8].childNodes[0].value;
    var formData = {"release": releaseData, "procurementPlan_code":procurementPlan_code}

    $.ajax({
    url:'/total/stockDelivery',
        type : 'post',
        contentType : 'application/json',
        data:JSON.stringify(formData),
        success:function(data){
        console.log("data정보 확인하기 : ",data)
        addTable(clickNum,data)
        console.log("성공");
        },
        error:function () {
            console.log("실패");
        }
    });
}

function addTable(clickNum,data) {
    var tableData = document.getElementById("release_table").getElementsByTagName("tr");
    var inputHTML = tableData[clickNum].getElementsByTagName("td")
    console.log("받아온 데이터 정보 제대로 확인하기 ( data : ", data+", clickNum : "+ clickNum +")");

    var releaseProcess = data.ReleaseInfo;
    var existence = releaseProcess.existence;
    var existence_price = releaseProcess.existence_price
    var releaseDate = releaseProcess.departureDate

    inputHTML[2].innerHTML= ` <td class="table-body">${releaseDate}</td>`
    inputHTML[9].innerHTML= ` <td class="table-body">${existence}</td>`
    inputHTML[10].innerHTML= ` <td class="table-body">${existence_price}</td>`
}

function uploadHtml(data) {
    var table = document.getElementsByClassName("table_body");

    var inputHtml = [];
    var releaseInfo = data.releaseInfo;

    for(var i=0; i<releaseInfo.length;i++){
        inputHtml.push(`
        <tr>
            <td class="table-body">${releaseInfo[i].productName}</td>
            <td class="table-body">${releaseInfo[i].product_code}</td>
            <td class="table-body"></td>
            <td class="table-body">${releaseInfo[i].texture}</td>
            <td class="table-body">${releaseInfo[i].height}</td>
            <td class="table-body">${releaseInfo[i].length}</td>
            <td class="table-body">${releaseInfo[i].weight}</td>
            <td class="table-body" id="supplyPrice">${releaseInfo[i].contract_pay}</td>
            <td class="table-body"><input style="width:80px" type="number" id="release"></td>
            <td class="table-body">${releaseInfo[i].existence}</td>
            <td class="table-body">${releaseInfo[i].existence_price}</td>
            <input type="hidden" value="${releaseInfo[i].procurementPlan_code}">
            <td class="table-body">
               <button class="action-button action-button-registration"
                        onclick="submitToRelease(${releaseInfo[i].release},${releaseInfo[i].procurementPlan_code})">확인</button>
            </td>
        </tr>`);
    }

    table[table.length-1].innerHTML = inputHtml.join("");
}
