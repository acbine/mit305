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

function closePopup() {
    document.getElementById("orderInspectPopup").style.display = "none";
}
function closeInspect(){
    console.log("일단 닫기 버튼")
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

function addProgressInspection(productName, planId) {
    var classTbodyContainerTr = document.getElementById("progressInspection");
    var dateValue = document.getElementById("setInspectDate").childNodes[0].value;

    if(dateValue) {
        var formData = {
            "inspectDate" : dateValue,
            "planId" : planId
        }
        $.ajax({
            url : "orderInspect",
            contentType : 'application/json',
            data : JSON.stringify(formData),
            method : "post",
            success : function (data) {
                addInspectorOne(data);
                console.log("성공")
            },
            error : function () {
                console.log("보내는 데이터 형태 확인 : ", formData)
                console.error("잘못된 응답");
            }
        })

    } else {
        alert("진척 검수 계획일을 입력해주세요")
    }

}

function addInspectorOne(data) {
    var inspector = data.progressInspection;


    var orderDate = formDate(inspector.orderDate)
    var progressInspectorDate = formDate(inspector.progressInspectonDate);



    var classTbodyContainerTr = document.getElementById("progressInspection");
    classTbodyContainerTr.insertRow(0).innerHTML = `<td>${inspector.productName}</td>
                                                            <td>${orderDate}</td>
                                                            <td class="inspectDate">${progressInspectorDate}</td>
                                                            <td><button onclick="openPopup('popup')">진척검수실행</button><button onclick="updateProgressInspection(this)">수정</button><button>삭제</button></td>`
}

function toggleTables() {
    var selectedOption = document.getElementById("companyDropdown").value;

    document.getElementById("table1").classList.add("hidden");
    document.getElementById("table2").classList.add("hidden");
    document.getElementById("table3").classList.add("hidden");

    document.getElementById(selectedOption).classList.remove("hidden");
}

function formDate(data) {

    var date = new Date(data)

    var formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);

    return formattedDate;
}

