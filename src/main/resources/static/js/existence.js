function searchProduct() {
    console.log("함수가 작동 되고 있는지 확인")
    var search = document.getElementById("search");
    var date1 = document.getElementById("date1").value;
    var date2= document.getElementById("date2").value;
    console.log("date1과 date2 정보 확인하기 date1 : ", date1, ", date2 : ", date2)
    var product = search.value;

    var select = document.getElementById("selectInfo");
    var value = select.options[select.selectedIndex].value;

    $.ajax({
        url:'existenceDate?&date1='+date1+'&date2='+date2+'&product='+product,
        type : 'get',
        data: {},
        success:function (info){
            htmlLoad(info,value);
            columnChart(info,value);
        },
        error:function (info) {
            console.log("에러 받아오는 데이터 확인하기 : ", info);
            console.log("보내는 정보 보기 : ",product);
        }
    });

}

function htmlLoad(data,value) {
    var table = document.getElementById("existence_body");
    var title = document.getElementById("t-header");
    var existence = data.existenceList;

    if(value === "대분류"){
        table.innerHTML = `<td>대분류</td>
                           <td>품목명</td>
                           <td>품목코드</td>
                           <td>공급가격</td>
                           <td>재고 금액</td>
                           <td>출고 날짜</td>`
        for(var i=0; i<existence.length;i++){
            table.innerHTML=
                ` <td id="body">${existence[i].unit.unit}</td>
            <td id="body">${existence[i].productName}</td>
            <td id="body">${existence[i].product_code}</td>
            <td id="body">${existence[i].existence}</td>
            <td id="body">${existence[i].contract_pay}</td>
            <td id="body">${existence[i].existence_price}</td>
             <td id="body">${existence[i].releaseDate}</td>`;
        }
    }
    else if(value === "중분류"){

    }
    else if(value === "소분류"){

    } else
    {
        for(var i=0; i<existence.length;i++){
            table.innerHTML=
                `<td id="body">${existence[i].productName}</td>
            <td id="body">${existence[i].product_code}</td>
            <td id="body">${existence[i].unit.unit}</td>
            <td id="body">${existence[i].assy.assy}</td>
            <td id="body">${existence[i].part.part}</td>
            <td id="body">${existence[i].existence}</td>
            <td id="body">${existence[i].contract_pay}</td>
            <td id="body">${existence[i].existence_price}</td>
             <td id="body">${existence[i].releaseDate}</td>`;
        }
    }

}


google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(columnChart);

function columnChart(data) {
    var select = document.getElementById("selectInfo");
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
