/*------------------재고산출관리--------------------------*/
function table() {
    var tableList = document.getElementsByClassName("table_body")
    var inputHtml = [];

    $(document).ready(function () {

        $('button').on('click', function () {

            columnChart1();

        });

    });

    for (var i = 0; i < tableList.length; i++) {
        inputHtml.push(`<tr>
            <td class="table-body">A품목</td>
            <td class="table-body">asdew</td>
            <td class="table-body">2024-01-09</td>
            <td class="table-body">asdew</td>
            <td class="table-body">asdew</td>
            <td class="table-body">asdew</td>
            <td class="table-body">80원</td>
            <td class="table-body" id="inventory"><input style="width:80px" type="number" value="5"></td>
            <td class="table-body">11</td>
            <td class="table-body"><input style="width:80px" type="number" value="34"></td>
            <td class="table-body">24324원</td>
            <td class="table-body"><button class="action-button action-button-registration">확인</button></td>
        </tr>`)
    }

    tableList[tableList.length - 1].innerHtml = inputHtml.join("");
}