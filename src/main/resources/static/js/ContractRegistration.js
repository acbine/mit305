/* 계약 */

function OpenContract() {
    $(".ContractModal").css('display', 'block');
}

function CloseContract() {
    $(".ContractModal").css('display', 'none');
}

function contract_modify_and_save(tag) {

    const tr = tag.closest("tr");

    const cell_0 = tr.children[0];
    const cell_4 = tr.children[4];
    const cell_5 = tr.children[5];


    if (cell_0.contentEditable == "true") {

        cell_0.contentEditable = "false";
        cell_4.contentEditable = "false";
        cell_5.contentEditable = "false";

        tag.innerText = "수정";

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
    } else {

        //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

        cell_0.contentEditable = "true";
        cell_4.contentEditable = "true";
        cell_5.contentEditable = "true";

        tag.innerText = "수정 완료";

        cell_4.focus();
    }
}

function contract_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function contract_addRow() {

    const table = document.getElementById('contract');
    const new_row = table.insertRow();

    const cell_length = table.rows[0].cells.length;

    for (let i = 0; i < cell_length; i++) {
        const new_cell = new_row.insertCell(i);
        let temp_html = '';

        if (i == 0) {

            temp_html =
                '<input type="text" class = "p_input" style="width:73px; height:25px; font-size:15px; text-align:center;">' +

                '<button onclick="get_pname(this)">확인</button>';
        }

        if (i == 1) {

            temp_html = '<input type="text" class = "search_p" style="width:84px; height:25px; font-size:15px; text-align:center;">';
        }

        if (i == 2) {

            temp_html =
                '<input type="text" class = "c_input" style="width:131px; height:25px; font-size:15px; text-align:center;">' +

                '<button onclick="get_cname(this)">확인</button>';

        }

        if (i == 3) {

            temp_html = '<input type="text" class = "search_c" style="width:132px; height:25px; font-size:15px; text-align:center;">';
        }

        if (i == 4) {

            temp_html = '<input type="text" class = "l_time" style="width:69px; height:25px; font-size:15px; text-align:center;">';
        }

        if (i == 5) {

            temp_html = '<input type="text" class = "price" style="width:69px; height:25px; font-size:15px; text-align:center;">';
        }


        if (i == 6) {

            temp_html =
                '<td><input class ="cs_date" type="date" name="startDate"></td>' +
                '<td> ~ </td>' +
                '<td><input class ="ce_date" type="date" name="endDate"></td>';
        }

        if (i == 7) {

            temp_html = '<input type="text" class = "payment" style="width:115px; height:25px; font-size:15px; text-align:center;">';
        }

        if (i == 8) {

            temp_html =
                '<td>' +
                    '<div class="actions">' +
                        '<button class="action-button action-button-delete" onclick="contract_registration(this);" width = "74px" height = "35px">등록</button>' +
                        '<button class="action-button action-button-delete" onclick="contract_row_delete(this);" width = "74px" height = "35px">삭제</button>' +
                    '</div>' +
                '</td>';
        }

        new_cell.insertAdjacentHTML('afterbegin', temp_html);

    }
}

// 확인 클릭시 서버에 품목명으로 요청하는 함수
function get_pname(button) {

var productName = $(button).closest("tr").find(".p_input").val();
var resultCell = $(button).closest("tr").find(".search_p");

//console.log("입력받은 값: " + productName);

$.ajax({
    type : "POST",
    url : "/search/pro",
    data : {"name" : productName},
    success : function(data) {
          console.log("품목 코드" + data);
          resultCell.val(data);
    },
    error : function() {
        console.log("검색 실패함");
    }

});

}

// 확인 클릭시 서버에 회사명으로 요청하는 함수
function get_cname(button) {

var companyName = $(button).closest("tr").find(".c_input").val();
var resultCell2 = $(button).closest("tr").find(".search_c");

$.ajax({
    type : "POST",
    url : "/search/com",
    data : {"name" : companyName},
    success : function(data) {
          console.log(data);
          resultCell2.val(data);
    },
    error : function() {
        console.log("검색 실패함");
    }

});

}

// 계약 등록
function contract_registration(button) {

var data = {

    product_code : $(button).closest("tr").find(".search_p").val(), // 품목 코드

    businessNumber : $(button).closest("tr").find(".search_c").val(), // 사업자 번호

    lead_time : $(button).closest("tr").find(".l_time").val(), // LeadTime

    product_price : $(button).closest("tr").find(".price").val(), // 단가

    start_date : $(button).closest("tr").find(".cs_date").val(), // 계약 시작일 : yyyy-mm-dd

    end_date : $(button).closest("tr").find(".ce_date").val(), // 계약 종료일

    payment_method : $(button).closest("tr").find(".payment").val(), // 지급 수단

    // 계약 등록일 (빈값)

    // 계약 등록여부 (False)
    true_false : false

};

$.ajax({

    type : "POST",
    url : "/register",
    contentType: "application/json;charset=UTF-8",
    data : JSON.stringify ([data]),

    success : function(data) {
        alert("등록 성공");
    },

    error : function(error) {
        alert("등록 실패");
    }

});

}

// 계약 등록 버튼 눌린 행 삭제
function contract_row_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

// 계약 검색
function contract_All() {

    $.ajax({

        url : '/search/contract',
        method : 'GET',
        dataType : 'json',

        success : function(data) {

            console.log(data);

            $('#contract_info tbody').empty();

            $.each(data, function (index, con) {

                $('#contract_info tbody')
                    .append(
                    '<tr>' +
                        '<span class = now_code>' + con.contract_code + '</span>' +
                        '<td height="25">' + '<input style = "width=128.67; text-align:center;" class = now_product_code value =' + con.productInformationRegistration.product_name + '>' + '</td>' + // 품목명
                        '<td height="25">' + con.productInformationRegistration.product_code + '</td>' + // 품목 코드
                        '<td height="25">' + con.company.departName + '</td>' + // 회사명
                        '<td height="25">' + con.company.businessNumber + '</td>' + // 사업자 번호
                        '<td height="25">' + con.lead_time + '</td>' + // L/T
                        '<td height="25">' + con.product_price + '</td>' + // 단가

                        '<td height="25">' + con.start_date + ' ~ ' + con.end_date + '</td>' + // 계약 기간
                        '<td height="25">' + con.payment_method + '</td>' + // 지급 수단
                        '<td height="25">' +
                            '<div class="actions">' +
                                '<button class="action-button action-button-edit" onclick="contract_delete(this)">계약서에 추가</button>' +
                                '<button class="action-button action-button-edit" onclick="contract_modify_and_save(this)">수정</button>' +
                                '<button class="action-button action-button-delete" onclick="contract_delete(this)">삭제</button>' +
                            '</div>' +
                        '</td>' + // 계약 처리
                    '</tr>')
            });
        },

        error : function(error) {
            console.error(error);
        }

    });

}


