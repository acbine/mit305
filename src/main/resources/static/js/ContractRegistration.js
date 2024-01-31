/* 계약 */

function OpenContract() {
    $(".ContractModal").css('display', 'block');
}

function CloseContract() {
    $(".ContractModal").css('display', 'none');
}



// 확인 클릭시 서버에 회사명으로 요청하는 함수
function get_cname(button) {

var companyName = $(button).closest("tr").find(".c_input").val();
var resultCell2 = $(button).closest("tr").find(".search_c");

$.ajax({
    type : "POST",
    url : "/search/com",
    data : {"comName" : companyName},
    success : function(data) {
          console.log(data);
          resultCell2.val(data);
    },
    error : function() {
        console.log("사업자 번호 검색 실패함");
    }

});

}

// 계약 등록
function contract_registration(button) {

var data = {

    product_code : $(button).closest("tr").find("td:eq(1)").text(), // 품목 코드

    businessNumber : $(button).closest("tr").find(".search_c").val(), // 사업자 번호

    lead_time : $(button).closest("tr").find(".l_time").val(), // LeadTime

    product_price : $(button).closest("tr").find(".price").val(), // 단가

    payment_method : $(button).closest("tr").find(".payment").val(), // 지급 수단

};
    console.log("함수 작동 확인",$(button).closest("tr").find(".search_p").val(),"      ",$(button).closest("tr").find(".price").val(),"      ",$(button).closest("tr").find(".payment").val());
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

// 행 삭제
function contract_row_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

var dataFromServer; // 전역 변수 (데이터 비교용)

// 계약 검색 결과
function contract_All() {

    $.ajax({

        url : '/search/contract',
        method : 'GET',
        dataType : 'json',

        success : function(data) {

            dataFromServer = data;

            $('#contract_info tbody').empty();

            $.each(data, function (index, con) {

                $('#contract_info tbody')
                    .append(
                    '<tr>' +
                        '<td class="productImformationTable-data">' + con.productInformationRegistration.product_name  + '</td>' + // 품목명
                        '<td class="productImformationTable-data">' + con.productInformationRegistration.product_code  +'</td>' + // 품목 코드
                        '<td class="productImformationTable-data">' + con.company.departName  + '</td>' + // 회사명
                        '<td class="productImformationTable-data">' + con.company.businessNumber + '</td>' + // 사업자 번호
                        '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"29px"; font-size:15px;" class = now_lead_time value =' + con.lead_time + '>' + '</td>' + // L/T
                        '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"29px"; font-size:15px;" class = now_product_price value =' + con.product_price + '>' + '</td>' + // 단가
                        '<td class="productImformationTable-data">' + '<input type="text" style = "width:115.67; height:"29"; font-size:15px;" class = now_payment value =' + con.payment_method + '>' + '</td>' + // 지급 수단
                        '<td class="productImformationTable-data">' +
                            '<div class="actions">' +
                                '<button class="action-button action-button-edit" onclick="contract_modify('+ con.contract_code +')">수정</button>' +
                                '<button class="action-button action-button-edit" onclick="contract_to_contractPage('+ con.contract_code +'); contract_row_delete(this);">계약서에 추가</button>' +
                                '<button class="action-button action-button-delete" onclick="contract_delete('+ con.contract_code +') contract_row_delete(this)">삭제</button>' +
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


// 계약서 db에 계약 등록
function contract_to_contractPage(contract_code) {

    $.ajax({
        type : "POST",
        url : "/registration_page/" + contract_code,
        success : function(response) {
            console.log("계약서에 등록된 계약 코드: " + contract_code);
        },
        error : function(error) {
            console.error(contract_code, error)
        }
    });

}

// 등록된 계약 수정
function contract_modify(contract_code) {

var lead_time = $(".now_lead_time").eq(contract_code-1); // LeadTime
var product_price = $(".now_product_price").eq(contract_code-1);// 단가
var payment_method = $(".now_payment").eq(contract_code-1); // 지급 수단

var updatedFields = {};

if (lead_time.val() !== dataFromServer[contract_code - 1].lead_time) { // 수정된 lead_time이 있을 경우 추가
    updatedFields.lead_time = lead_time.val();
} else {
    updatedFields.lead_time = dataFromServer[contract_code - 1].lead_time;
}

if (product_price.val() !== dataFromServer[contract_code - 1].product_price) {
    updatedFields.product_price = product_price.val();
} else {
    updatedFields.product_price = dataFromServer[contract_code - 1].product_price;
}

if (payment_method.val() !== dataFromServer[contract_code - 1].payment_method) {
    updatedFields.payment_method = payment_method.val();
} else {
     updatedFields.payment_method = dataFromServer[contract_code - 1].payment_method;
}


    $.ajax({
        type : "POST",
        url : "/edit/" + contract_code,
        data : JSON.stringify (updatedFields),
        contentType : "application/json;charset=UTF-8",
        success : function(response) {
            console.log("수정된 계약 코드: " + contract_code);
        },
        error : function(error) {
            console.error(contract_code + ":" + error)
        }

    });
}

// 등록된 계약 삭제
function contract_delete(contract_code) {

    $.ajax({
        type : "GET",
        url : "/delete/" + contract_code,
        success : function(response) {
            console.log("삭제된 계약 코드: " + contract_code);
        },
        error : function(error) {
            console.error(contract_code, error)
        }
    });
}

// 계약서 전송 팝업창 열기
function SendContract() {

    var popup = window.open("/ContractSend", "ContractSend", "width=500,height=200");

}
