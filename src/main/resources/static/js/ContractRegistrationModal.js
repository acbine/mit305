/*---------계약서 모달창--------------*/
var data = [
    ["나사", "10", 250, "24/01/10", "24/01,20", "현금 지불"],
    ["A품목", "8", 200, "24/01/21", "24/02/03", "현금 지불"]
];



var n;

function name_value(value) {

    if(value == 'n1') {

        n = '길승산업';
    }

    if(value == 'n2') {

        n = '마텍스';
    }
}

function contract_select() {

    let today = new Date();

    let Contrac_year = today.getFullYear(); // 년도
    let Contrac_month = today.getMonth() + 1;  // 월
    let Contrac_day = today.getDate();  // 날짜

    document.getElementById("year").textContent = Contrac_year + '년';
    document.getElementById("month").innerHTML = Contrac_month + '월';
    document.getElementById("day").innerHTML = Contrac_day + '일';


    document.getElementById("text").innerHTML = 'TAE 와 ' + n + ' 은 계약서를 기준으로 다음과 같이 상품을 공급한다.';

    var list = $(".list tbody"); // 계약 품목 테이블의 class 확인

    const cp_table = document.getElementById('cp_table');

    console.log(cp_table.rows.length);

    if (n == '길승산업') {

        document.getElementById("company_num").innerHTML = 'Xxx-xxx-xxxxx';
        document.getElementById("company_name").innerHTML = n;
        document.getElementById("company_email").innerHTML = 'abcde@gmail.com';

        data.forEach(function(val) {
               list.append(
                        "<tr class='cell'>" +

                                    "<td colspan='3' width='107' height='22' valign='middle'>" +
                                        "<P CLASS=HStyle0 STYLE='text-align:center;'>" +
                                            "<SPAN STYLE='font-size:8.0pt;line-height:160%;'>" +
                                                    val[0] +
                                            "</SPAN>" +
                                        "</P>" +
                                    "</td>" +

                                    "<td colspan='2' width='63' height='22' valign='middle'>" +
                                        "<P CLASS=HStyle0 STYLE='text-align:center;'>" +
                                             "<SPAN STYLE='font-size:8.0pt;line-height:160%;'>" +
                                                    val[1] +
                                             "</SPAN>" +
                                        "</P>" +
                                    "</td>" +

                                    "<td width='95' height='22' valign='middle'>" +
                                        "<P CLASS=HStyle0 STYLE='text-align:center;'>" +
                                              "<SPAN STYLE='font-size:8.0pt;line-height:160%;'>" +
                                                    val[2] +
                                              "</SPAN>" +
                                        "</P>" +
                                    "</td>" +

                                    "<td colspan='4' width='120' height='22' valign='middle'>" +
                                         "<P CLASS=HStyle0 STYLE='text-align:center;'>" +
                                                "<SPAN STYLE='font-size:8.0pt;line-height:160%;'>" +
                                                    val[3] + " ~ " + val[4] +
                                                "</SPAN>" +
                                         "</P>" +
                                    "</td>" +

                                    "<td colspan='2' width='87' height='22' valign='middle' class='cell'>" +
                                         "<P CLASS=HStyle0 STYLE='text-align:center;'>" +
                                                 "<SPAN STYLE='font-size:8.0pt;line-height:160%;'>" +
                                                    val[5] +
                                                 "</SPAN>" +
                                         "</P>" +
                                    "</td>" +
                        "</tr>");
        });

    }

    else {

        document.getElementById("company_num").innerHTML = '사업자번호1';
        document.getElementById("company_name").innerHTML = n;
        document.getElementById("company_email").innerHTML = 'aaaa@gmail.com';
    }
}

function contract_convertToImage() {

    const h1 = document.getElementById('name_select');
    const h2 = document.getElementById('select_contract');
    const h3 = document.getElementById('save');

    h1.style.display = 'none';
    h2.style.display = 'none';
    h3.style.display = 'none';

    html2canvas(document.getElementById('screen_area'),{scale:2}).then((canvas) => {
        const imageDataURL = canvas.toDataURL("image/jpg");

        const a = document.createElement("a");
        a.href = imageDataURL;
        a.download = "계약서.jpg";
        a.click();

    });

}

