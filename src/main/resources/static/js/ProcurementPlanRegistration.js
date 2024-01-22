
function modify_and_save(tag) {

    const tr = tag.closest("tr");

    const num = tr.children[3];
    const date = tr.children[8];

    const target = document.getElementById("deleteBtn1");
    //const delete_btn_state = document.getElementsByClassName('action-button action-button-registration');

    console.log(num, date);


    if (num.contentEditable == "true") {

        num.contentEditable = "false";
        date.contentEditable = "false";


        tag.innerText = "수정";

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
    } else {

        //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

        num.contentEditable = "true";
        date.contentEditable = "true";

        tag.innerText = "수정 완료";


        //첫번째 셀에 포커스를 줘서 상태 변경에 대해 알림.
        num.focus();

    }
}

function registration_and_delete(tag) {

    alert("조달 계획 등록 완료");

    const tr = tag.closest("tr");
    tr.remove();
}

function td_regOrder(tag) { // 발주 등록 js
    alert("발주 등록 완료. \n등록된 품목은 구매 발주서 발행 페이지에서 확인하실 수 있습니다.");

    const tr = tag.closest("tr");
    tr.remove();
}

function td_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function select_row() {

var startDate = $('#s_date').val();
var endDate = $('#e_date').val();

    if(!startDate && !endDate) {
        alert("날짜 선택 안됨");

    } else {
        $.ajax({

            url : '/search/Project_plan',
            method : 'GET',
            data : {startDate : startDate, endDate : endDate},
            success : function(data) {
                console.log(data);
                setting_registration(data);
            },
             error: function(error) {
                console.log(error);
             }
        });


    }

}

function setting_registration(plan) {


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
            '<td>' + plan.projectPlan.product.productName + '</td>' + // 제품 생산 계획의 제품명(?)
            '<td>' + plan.productForProject.productCode.product_name + '</td>' + // 제품 품목 수량의 품목 이름
            '<td>' + plan.productForProject.productCode.product_code + '</td>' + // 제품 품목 수량의 품목 코드
            '<td>' + plan.projectPlan.outPuteNum  + '</td>' + // 제품 생산 계획의 필요 수량
            '<td>' + plan.supportProductAmount  + '</td>' + // 조달 계획의 조달 수량
            '<td>' + plan.contract.lead_time  + '</td>' + // 계약의 L/T
            '<td>' + plan.contract.company.departName  + '</td>' + // 계약의 회사명
            '<td>' + plan.projectPlan.projectOutputDate  + '</td>' + // 제품 생산 계획의 제품 생산 날짜
            '<td>' + plan.productForProject.productCodeCount  + '</td>' + // 제품 품목 수량의 현재 품목 수량
            '<td>' + plan.order_date  + '</td>' + // 조달 계획의 발주일
            '<td>' +
                '<div class="actions">' + // 계획 관리 칸
                     '<button class="action-button action-button-edit" onclick="td_regOrder(this)">발주등록</button>' +
                     '<button class="action-button action-button-edit" onclick="td_delete(this)">삭제</button>' +
                '</div>' +
            '</td>' +
        '</tr>'
    )

});

}

