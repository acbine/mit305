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