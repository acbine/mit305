/*--------------------발주 목록(orderList)--------------------*/

function openOrderInspectPopup(productCode,procurementPlanCode){  //모달창열기
    var html = document.getElementById("orderInspectPopup");
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
function searchOrderListWithDate() {
    var date1 = document.getElementsByName("startDate")[0].value;
    var date2 = document.getElementsByName("endDate")[0].value;
    $.ajax({
            url: 'order-list-with-date?&date1=' + date1 +'&date2='+date2,
            method:'get',
        success:function (info){
                console.log(info,"info 정보 확인하기")
                drawHTMl(info)
                console.log("성공");
        },
        error:function (){
            console.log("실패");
        }

    }
        )
}


function drawHTMl(info) {
    var orderBoxInfo = document.getElementsByClassName("orderList")
    for(var i = 0 ; i<orderBoxInfo.length; i++) {
        orderBoxInfo[i].innerHTML = `<td>${info.productName}</td>
                     
                                    <td>${info.productName}</td>
                                    <td>${info.productName}</td>
                                    <td>${info.productName}</td>
                                    <td>${info.productName}</td>v
                                    <td>${info.productName}</td>
`
    }

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

function formDate(data) {

    var date = new Date(data)

    var formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);

    return formattedDate;
}
