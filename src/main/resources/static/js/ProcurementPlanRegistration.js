
function select_row() {

var startDate = $('#s_date').val();
var endDate = $('#e_date').val();

    if(!startDate && !endDate) {

        alert("날짜 선택 안됨");
    }

    else {
        $.ajax({

             method : 'GET',
             url : '/search/Project_plan',
             data : {"startDate" : startDate, "endDate" : endDate},
             success : function(data) {
                console.log("받아온 조인 결과 : " + data);
                setting_registration(data);
             },
             error: function(error) {
                alert("오류 발생");
             }
        });
    }

}

function setting_registration(join_List) {

$('#plan_table tbody').empty();

$.each(join_List, function(index, setList) {

    $('#plan_table tbody').append(

        '<tr>' +
            '<td style="display: none">' + setList.projectPlanCode + '</td>' +  // 생산 계획 코드 (숨겨짐)
            '<td style="display: none">' + setList.contractCode + '</td>' + // 계약 코드 (숨겨짐)
            '<td class="productImformationTable-data">' + setList.projectName + '</td>' + // 조달 제품명
            '<td class="productImformationTable-data">' + setList.productName + '</td>' + // 조달 품목명
            '<td class="productImformationTable-data">' + setList.productCode + '</td>' + // 품목 코드
            '<td class="productImformationTable-data">' + setList.departmentName + '</td>' + // 조달 업체명
            '<td class="productImformationTable-data">' + setList.outPutNum * setList.productCodeCount + '</td>' + // 조달 필요 수량
            '<td class="productImformationTable-data">' + '<input type="text" style = "width:50.67px; height:"29px"; font-size:15px;" class = get_num value =' + setList.outPutNum * setList.productCodeCount + '>' + '</td>' + // 조달 수량 (입력, 기본값)
            '<td class="productImformationTable-data">' + setList.leadTime + '</td>' + // L/T
            '<td class="productImformationTable-data">' + setList.projectOutputDate + '</td>' + // 제품 생산 날짜
            '<td class="productImformationTable-data"><input type="date" style = "width:150.67px; height:"29px"; font-size:15px;" class = get_date></td>' + // 발주일 (날짜 선택, 기본값)
            '<td class="productImformationTable-data">' + // 계획 처리
                '<div class="actions">' +
                     '<button class="action-button action-button-registration" onclick="procurement_registration(this); td_delete(this);">등록</button>' +
                '</div>' +
            '</td>'+
        '</tr>'

    )
});
}

// 조달 계획 등록
function procurement_registration(button) {

var now_row = $(button).closest("tr");

var plan_data = {

    id : now_row.find("td:eq(0)").text(), // 생산 계획 코드
    contract_code : now_row.find("td:eq(1)").text(), // 계약 코드
    project_name : now_row.find("td:eq(2)").text(), // 조달 제품명
    supportProductAmount : now_row.find(".get_num").val(), // 조달 수량
    order_date : now_row.find(".get_date").val(), // 발주일
    order_state : "발주 전", // 발주 상태
    ordercode : "None" // 발주 코드

};

console.log("생산 계획 코드 : " + plan_data.id);
console.log("계약 코드 : " + plan_data.contract_code);
console.log("제품명 : " + plan_data.project_name);
console.log("조달 수량 : " + plan_data.supportProductAmount);
console.log("발주일 : " + plan_data.order_date);
console.log("발주 상태 : " + plan_data.order_state);
console.log("발주 코드 : " + plan_data.ordercode);



    $.ajax({

        type : "POST",
        url : "/procurementPlan/register",
        data : JSON.stringify ([plan_data]),
        contentType: 'application/json;charset=UTF-8',

        success : function(data) {
            alert("등록 성공");
        },

        error : function(error) {
            alert("등록 실패");
        }
    });
}

// 조달 계획 전체 검색
function ProcurementPlan_list() {

    $.ajax({

        url : '/search/procurementPlan',
        method : 'GET',
        dataType : 'json',

        success : function(data) {
            console.log(data);
            set_table(data);
        },

        error : function(error) {
            console.log(error);
            console.error(error);
        }
    });
}

// 검색 결과 테이블로 세팅
function set_table(plan_list) {

$('#plan_table_info tbody').empty();

$.each(plan_list, function(index, plan) {

    $('#plan_table_info tbody').append(
        '<tr>' +
            '<td class="productImformationTable-data">' + plan.projectPlan.project.projectName + '</td>' + // 제품 생산 계획의 제품명(?)
            '<td class="productImformationTable-data">' + plan.contract.productInformationRegistration.product_name + '</td>' + // 제품 품목 수량의 품목 이름
            '<td class="productImformationTable-data">' + plan.contract.productInformationRegistration.product_code + '</td>' + // 제품 품목 수량의 품목 코드
            '<td class="productImformationTable-data">' + plan.supportProductAmount  + '</td>' + // 조달 계획의 조달 수량
            '<td class="productImformationTable-data">' + plan.contract.lead_time  + '</td>' + // 계약의 L/T
            '<td class="productImformationTable-data">' + plan.contract.company.departName  + '</td>' + // 계약의 회사명
            '<td class="productImformationTable-data">' + plan.projectPlan.projectOutputDate  + '</td>' + // 제품 생산 계획의 제품 생산 날짜
            '<td class="productImformationTable-data">' + plan.order_date  + '</td>' + // 조달 계획의 발주일
            '<td class="productImformationTable-data">' +
                '<div class="actions">' + // 계획 관리 칸
                     '<button class="action-button action-button-edit" onclick="td_regOrder('+ plan.procurementplan_code +'); td_delete(this)">발주등록</button>' +
                     '<button class="action-button action-button-edit" onclick="plan_delete('+ plan.procurementplan_code +'); td_delete(this)">삭제</button>' +
                '</div>' +
            '</td>' +
        '</tr>'
    )

});

}

// 발주 등록 js
function td_regOrder(procurement_plan_code) {

    $.ajax({

        type : "POST",
        url : "/plan_edit/" + procurement_plan_code,
        contentType : "application/json;charset=UTF-8",

        success : function(response_code) {

            console.log("발주 등록한 조달 계획: " + response_code);
            alert("발주 등록 완료. \n등록된 품목은 구매 발주서 발행 페이지에서 확인하실 수 있습니다.");
        },

        error : function(error) {
            console.error(response_code + ":" + error)
        }
    });

}

// 조달 계획 삭제
function plan_delete(procurement_plan_code) {

    $.ajax({

        type : "POST",
        url : "/plan_delete/" + procurement_plan_code,
        contentType : "application/json;charset=UTF-8",

        success : function(response_code) {

            console.log(response_code);
        },

        error : function(error) {
            console.error(response_code + ":" + error)
        }
    });
}


function td_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

