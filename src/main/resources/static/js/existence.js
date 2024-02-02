function searchProduct() {
    console.log("함수가 작동 되고 있는지 확인")
    var search = document.getElementById("search");
    var date1 = document.getElementById("date1").value;
    var date2 = document.getElementById("date2").value;
    console.log("date1과 date2 정보 확인하기 date1 : ", date1, ", date2 : ", date2)
    var product = search.value;

    var select = document.getElementById("selectInfo");
    var value = select.options[select.selectedIndex].value;

    $.ajax({
        url: 'existenceDate?&date1=' + date1 + '&date2=' + date2 + '&product=' + product,
        type: 'get',
        data: {},
        success: function (info) {
            htmlLoad(info, value);
            columnChart(info, value);
        },
        error: function (info) {
            console.log("에러 받아오는 데이터 확인하기 : ", info);
            console.log("보내는 정보 보기 : ", product);
        }
    });

}

function htmlLoad(data, value) {
    var table = document.getElementsByClassName("existence_body");
    var title = document.getElementsByClassName("t-header");
    var existence;

    if (data.existenceList) {
        existence = data.existenceList;
    }

    var classification = [];

    for (var ex in existence) {
        console.log(existence[ex]);
    }

    var inputHTML = [];

    var totalAmount = 0;
    var totalEx = 0;

    if (value === "대분류" || value === "중분류" || value === "소분류") {
        table[table.length - 1].length = 0;
        inputHTML = [];
        totalEx = 0;
        classification.length = 0;

        var headerHTML = `<thead class="t-header">
                            <tr style="position: sticky;  top: 0;  background-color: #f2f2f2;">
                                <td class="existence_head">${value}</td>
                                <td class="existence_head">현재고</td>
                                <td class="existence_head">공급가</td>
                                <td class="existence_head">재고 금액</td>
                            </tr>
                           </thead>`;

        title[0].innerHTML = headerHTML;

        for (var i = 0; i < existence.length; i++) {
            var classificationValue;

            if (value === "대분류") {
                classificationValue = existence[i].unit.unit;
            } else if (value === "중분류") {
                classificationValue = existence[i].assy.assy;
            } else if (value === "소분류") {
                classificationValue = existence[i].part.part;
            }

            if (!classification.includes(classificationValue)) {
                classification.push(classificationValue);

                var totalAmountPerClassification = 0;
                var totalExPerClassification = 0;

                for (var j = 0; j < existence.length; j++) {
                    if (
                        (value === "대분류" && classificationValue === existence[j].unit.unit) ||
                        (value === "중분류" && classificationValue === existence[j].assy.assy) ||
                        (value === "소분류" && classificationValue === existence[j].part.part)
                    ) {
                        totalAmountPerClassification = existence[j].existence_price;
                        totalExPerClassification = existence[j].existence;
                    }
                }

                inputHTML.push(
                    `<tbody class="existence_body"> 
                        <td id="body">${classificationValue}</td>
                        <td id="body">${totalExPerClassification}</td>
                        <td id="body">${totalAmountPerClassification}</td>
                        <td id="body">${totalAmountPerClassification * totalExPerClassification}</td>
                    <tbody>`
                );
            }
        }

        table[table.length - 1].innerHTML = inputHTML.join('');
    } else {
        table[table.length - 1].length = 0;
        var inputHTML = [];
        var classification = [];
        var totalAmount = 0;
        var totalEx = 0;

        inputHTML.length = 0;

        title[0].innerHTML = `<thead class="stockDelivery_head">
                                  <tr style="position: sticky;  top: 0;  background-color: #f2f2f2;">
                                    <th class="existence_head">품목명</th>
                                    <th class="existence_head">품목코드</th>
                                    <th class="existence_head">대분류</th>
                                    <th class="existence_head">중분류</th>
                                    <th class="existence_head">소분류</th>
                                    <th class="existence_head">재고</th>
                                    <th class="existence_head">공급 가격</th>
                                    <th class="existence_head">재고금액</th>
                                  </tr>
                                </thead>`;

        for (var i = 0; i < existence.length; i++) {
            classification.push(existence[i].productName);
        }

        const set = new Set(classification);

        var totalRelease = 0;

        for (const productClassification of set) {
            var groupedInfo = existence.filter(item => item.productName === productClassification);
            var firstItem = groupedInfo[0];

            totalAmount += firstItem.existence_price;
            totalEx += firstItem.existence;
            totalRelease += firstItem.release


            inputHTML.push(
                `<tbody class="existence_body"> 
                  <td id="body">${productClassification}</td>
                  <td id="body">${firstItem.product_code}</td>
                  <td id="body">${firstItem.unit.unit}</td>
                  <td id="body">${firstItem.assy.assy}</td>
                  <td id="body">${firstItem.part.part}</td>
                  <td id="body">${firstItem.existence}</td>
                  <td id="body">${firstItem.contract_pay}</td>
                    <td id="body">${firstItem.existence_price}</td>
                </tbody>`
            );
        }
        table[table.length - 1].innerHTML = inputHTML.join('')
    }

}


google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(columnChart);

function columnChart(data) {
    var select = document.getElementById("selectInfo");
    var value = select.options[select.selectedIndex].value;
    var charData;

    data1 = [['분류', '재고']];
    var groupedData = {};
if(data.existenceList) {
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

        groupedData[key] = x.existence;
    });

    for (var key in groupedData) {
        data1.push([key, groupedData[key]]);
    }

    charData = google.visualization.arrayToDataTable(data1);

    var options1 = {
        title: value,
        width: 600,
        height: 500,
        isStacked: true,
    };

    var chart1 = new google.visualization.ColumnChart(document.getElementById('chart_div1'));
    chart1.draw(charData, options1);
}

}
