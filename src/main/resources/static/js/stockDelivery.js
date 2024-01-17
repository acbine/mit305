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
            url:'/total/stockDelivery',
            type : 'get',
            contentType : 'application/json',
            data:JSON.stringify(formData),
            success:function (){
                console.log("성공");
                console.log("보내고 있는 데이터 확인하기 : ", data);
            },
            error:function () {
                console.log("에러 보내고 있는 데이터 형식 확인하기 : ",formData);
                console.log("보내고 있는 데이터 확인하기 : ", data);
            }
        });
    } else if(select==="ProductCode") {
        state = 1;
        var formData = {"state": state,"constraints" : constraints}
        $.ajax({
            url:'/total/stockDelivery',
            type : 'get',
            contentType : 'application/json',
            data:JSON.stringify(formData),
            success:function (){
                console.log("성공")
                console.log("보내고 있는 데이터 확인해보기 : ",data)
            },
            error:function () {
                console.log("에러 보내고 있는 데이터 형식 확인하기 : ",formData)
            }
        });

    }
    var tableList = document.getElementsByClassName("table_body")
    var inputHtml = [];


    for (var i = 0; i < tableList.length; i++) {
        inputHtml.push(`
             <form method="post" action="total/stockDelivery">
            <tr>
            <td class="table-body">A품목</td>
            <td class="table-body">asdew</td>
            <td class="table-body">2024-01-09</td>
            <td class="table-body">asdew</td>
            <td class="table-body">asdew</td>
            <td class="table-body">asdew</td>
            <td class="table-body">80원</td>
            <td class="table-body" id="inventory"><input style="width:80px" type="number" value="5" name="release"></td>
            <td class="table-body">11</td>
            <td class="table-body"><input style="width:80px" type="number" value="34"></td>
            <td class="table-body">24324원</td>
           <td class="table-body">
                <button class="action-button action-button-registration" type="submit">확인</button>
            </td>
        </tr>
    </form>`)
    }

    tableList[tableList.length - 1].innerHtml = inputHtml.join("");
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



