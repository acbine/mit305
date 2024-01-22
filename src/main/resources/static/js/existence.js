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
            <td>${existence[i].length}</td>
            <td>${existence[i].weight}</td>
            <td>${existence[i].length}</td>
            <td>${existence[i].unit.unit}</td>
            <td>${existence[i].assy.assy}</td>
            <td>${existence[i].part.part}</td>
            <td>${existence[i].release}</td>
            <td>${existence[i].existence}</td>
            <td>${existence[i].existence_price}</td>
        </tr>`);
    }

    table[table.length-1].innerHTML = inputHtml.join("");
}


google.charts.load('current', {packages: ['corechart']});

google.charts.load('current', {packages: ['corechart']});

function columnChart1() {

    var arr = [

        ['품목명', '총금액'],

        ['Unit', 740],

        ['Assy', 460],

        ['Part', 280],


    ];


    var dataTable = google.visualization.arrayToDataTable(arr);


    var options = {

        title: '현황 그래프',

        hAxis: {

            title: '현황 그래프',

            titleTextStyle: {

                color: 'black'

            }

        }

    };

    var objDiv = document.getElementById('column_chart_div1');

    var chart = new google.visualization.ColumnChart(objDiv);


    chart.draw(dataTable, options);

}

$(document).ready(function () {

    $('button').on('click', function () {

        columnChart1();

    });

});

var script = document.createElement("script");
script.async = true;
script.src = "https://www.gstatic.com/charts/loader.js";
document.head.appendChild(script);
