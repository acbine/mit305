
// 입력한 회사명 검색
function company_select() {

let today = new Date();

let Contrac_year = today.getFullYear(); // 년도
let Contrac_month = today.getMonth() + 1;  // 월
let Contrac_day = today.getDate();  // 날짜


var get_name = document.getElementById('get_company').value;
console.log("검색한 회사명: " + get_name);

    $.ajax({
        type : "POST",
        url : "/search/com",
        data : {"comName" : get_name},
        success : function(data) {
            find_contract_code(data);

            document.getElementById("text").innerHTML = 'TAE 와 ' + get_name + ' 은 계약서를 기준으로 다음과 같이 상품을 공급한다.';
            document.getElementById("year").textContent = Contrac_year + '년';
            document.getElementById("month").innerHTML = Contrac_month + '월';
            document.getElementById("day").innerHTML = Contrac_day + '일';

            console.log(data);
        },
        error : function() {
            console.log("사업자 번호 검색 실패함");
        }

    });

}

// 회사명에 맞는 계약서의 계약 코드 검색
function find_contract_code(businessNumber) {

    $.ajax({
        type : "POST",
        url : "/search/codes/" + businessNumber,
        success : function(c_codes) {

            console.log("사업자 번호초찾은 계약 코드들이 리턴"+c_codes);
            console.log();

            contract_select(c_codes);
        },
        error : function() {
            console.log("계약 코드 검색 실패");
        }

    });


}

// 받은 계약 코드의 계약 정보를 검색해서 테이블
function contract_select(c_code) {

$("#cp_table tbody").empty();

    $.each(c_code, function(index, result) {

        document.getElementById("company_num").innerHTML = result.contract.company.businessNumber;
        document.getElementById("company_name").innerHTML = result.contract.company.departName;
        document.getElementById("company_email").innerHTML = result.contract.company.businessEmail;


        $("#cp_table tbody")
            .append(
                '<TR class = "cell">' +

                    '<TD style="display: none">' + result.contract.contract_code + '</TD>' +  // 계약 코드 (숨겨짐)

                    '<TD colspan="3" width="135" height="22" valign="middle">' + // 품목명
                        '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                            '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +
                                result.contract.productInformationRegistration.product_name +
                            '</SPAN>' +
                        '</P>' +
                    '</TD>' +

                    '<TD colspan="2" width="63" height="22" valign="middle">' + // L/T
                        '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                            '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +
                                result.contract.lead_time +
                            '</SPAN>' +
                        '</P>' +
                    '</TD>' +

                    '<TD width="95" height="28" valign="middle">' + // 단가
                        '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                            '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +
                                result.contract.product_price +
                            '</SPAN>' +
                        '</P>' +
                    '</TD>' +

                    '<TD colspan="2" width="110" height="22" valign="middle" class="cell">' + // 지급 수단
                        '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                            '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +
                                result.contract.payment_method +
                            '</SPAN>' +
                        '</P>' +
                    '</TD>' +

                    '<TD colspan="4" width="80" height="22" valign="middle">' + // 비고
                        '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                            '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +
                            '</SPAN>' +
                        '</P>' +
                    '</TD>' +

                '</TR>')
    });
}

// 다시 계약서를 불렀을때 버튼이 숨겨진 채로 존재 = 새로 고침이 되지 않고 기존의 상태가 남아있음
//$(document).ready(function() {
//
//h1.style.display = 'inline-block';
//h2.style.display = 'inline-block';
//h3.style.display = 'inline-block';
//
//});

function get_contract_code(con_code) {

console.log(con_code);

var table = document.getElementById("cp_table");
var rows = table.getElementsByTagName("tr");
var columnArray = [];


for (var i = 1; i< rows.length; i++) {

    var cells = rows[i].getElementsByTagName("td");
    columnArray.push(cells[con_code].innerText);
}

console.log("계약 코드들: ", columnArray);

    $.ajax({

        type : "POST",
        contentType: "application/json",
        url : "/update/code",
        data : JSON.stringify(columnArray),

        success: function() {

            console.log("날짜 변경 성공");
        },

        error: function (error) {
            console.error("변경 오류 발생: ", error);
        }

    });


/* 빈 행 추가 하는 코드 */
const row_num = document.getElementById('cp_table').rows.length;

if(row_num < 11) {

    for(var i = 0; i<11 - row_num; i++) {

        $("#cp_table tbody")
            .append(
                '<TR>' +

                     '<TD style="display: none"></TD>' +

                     '<TD colspan="3" width="107" height="16" valign="middle" class="cell">' +
                        '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                            '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +

                            '</SPAN>' +
                        '</P>' +
                     '</TD>' +

                     '<TD colspan="2" width="63" height="16" valign="middle" style="border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt">' +
                          '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                                '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +

                                '</SPAN>' +
                          '</P>' +
                     '</TD>' +

                     '<TD width="95" height="16" valign="middle" style="border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt">' +
                          '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                                '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +

                                '</SPAN>' +
                          '</P>' +
                     '</TD>' +

                     '<TD colspan="4" width="120" height="16" valign="middle" style="border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt">' +
                            '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                                    '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +

                                    '</SPAN>' +
                            '</P>' +
                     '</TD>' +

                     '<TD colspan="2" width="87" height="16" valign="middle" style="border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt">' +
                            '<P CLASS=HStyle0 STYLE="text-align:center;">' +
                                '<SPAN STYLE="font-size:8.0pt;line-height:160%;">' +

                                '</SPAN>' +
                            '</P>' +
                     '</TD>' +
                '</TR>'
            )
    }

}
/* 빈 행 추가 하는 코드 끝 */

}


function contract_convertToImage() {




    const h1 = document.getElementById('get_company');
    const h2 = document.getElementById('select_contract');
    const h3 = document.getElementById('print');

    h1.style.display = 'none';
    h2.style.display = 'none';
    h3.style.display = 'none';

//    html2canvas(document.getElementById('screen_area'),{scale:2}).then((canvas) => {
//        const imageDataURL = canvas.toDataURL("image/jpg");
//
//        const a = document.createElement("a");
//        a.href = imageDataURL;
//        a.download = "계약서.jpg";
//        a.click();
//
//    });

}

