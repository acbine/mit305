
google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['미검수',     1,],
          ['검수진행중',      1],
          ['검수완료',  1],
          ['마감', 1]
        ]);

        var options = {
          pieHole: 0.45,
          colors: ['#F20505', '#F28749', '#03A64A', '#8268A6'] // 색상 변경
        
          
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        
        chart.draw(data, options);
      }

  