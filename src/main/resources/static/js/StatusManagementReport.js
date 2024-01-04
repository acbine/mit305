
google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['발주서 미발행',     2,],
          ['발주서 발행 ',     3,],
          ['발주서 검수 진행중 ',      5],
          ['발주서 검수처리 완료',  4],
          ['마감', 3]
        ]);

        var options = {
          pieHole: 0.45,
          colors: ['#00FFFF','#F20505', '#F28749', '#03A64A', '#8268A6'] // 색상 변경


        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));

        chart.draw(data, options);
      }



