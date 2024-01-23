function searchProduct() {
    console.log("함수가 작동 되고 있는지 확인")
    var search = document.getElementById("search");
    var date1 = document.getElementById("date1").value;
    var date2= document.getElementById("date2").value;
    console.log("date1과 date2 정보 확인하기 date1 : ", date1, ", date2 : ", date2)
    var product = search.value;

    $.ajax({
        url:'existenceDate?&date1='+date1+'&date2='+date2+'&product='+product,
        type : 'get',
        data: {},
        success:function (info){
            console.log("받아오는 데이터 정보 값이 다 잘들어왔는지 확인",info)
            console.log("성공");
            htmlLoad(info);
            columnChart(info);
            console.log("보내는 정보 보기 : ",product);
        },
        error:function (info) {
            console.log("에러 받아오는 데이터 확인하기 : ", info);
            console.log("보내는 정보 보기 : ",product);
        }
    });

}

function htmlLoad(data) {
    var table = document.getElementsByClassName("existence_body");

    var inputHtml = [];
    var existence = data.existenceList;

    for(var i=0; i<existence.length;i++){
        inputHtml.push(`
        <tr>
            <td>${existence[i].productName}</td>
            <td>${existence[i].product_code}</td>
            <td>${existence[i].releaseDate}</td>
            <td>${existence[i].texture}</td>
            <td>${existence[i].length}</td>
            <td>${existence[i].weight}</td>
            <td>${existence[i].length}</td>
            <td>${existence[i].unit.unit}</td>
            <td>${existence[i].assy.assy}</td>
            <td>${existence[i].part.part}</td>
            <td>${existence[i].existence}</td>
            <td>${existence[i].contract_pay}</td>
            <td>${existence[i].existence_price}</td>
        </tr>`);
    }

    table[table.length-1].innerHTML = inputHtml.join("");
}


google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(columnChart);

function columnChart(data) {
    select = document.getElementById("selectInfo");
    var value = select.options[select.selectedIndex].value;
    var charData;

    data1 = [['분류', '재고금액']];
    var groupedData = {};

    data.existenceList.forEach(x => {
        var key;

        if (value === "대분류") {
            key = x.unit.unit;
        } else if (value === "중분류") {
            key = x.assy.assy;
        } else if (value === "소분류") {
            key = x.part.part;
        } else {
            key = x.productName;
        }

        if (!groupedData[key]) {
            groupedData[key] = 0;
        }

        groupedData[key] += x.existence_price;
    });

    for (var key in groupedData) {
        data1.push([key, groupedData[key]]);
    }

    charData = google.visualization.arrayToDataTable(data1);

    var options1 = {
        title: value,
        width: 1000,
        height: 600,
        isStacked: true,
    };

    var chart1 = new google.visualization.ColumnChart(document.getElementById('chart_div1'));
    chart1.draw(charData, options1);
}
