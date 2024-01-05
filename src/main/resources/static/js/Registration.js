/*-------------------품목 정보(ProductInformationRegistration)-------------------------*/
function info_show(get) {

   document.getElementById('product_info').style.display = 'none';

   console.log(get);

   if(get == 'in') {

       document.getElementById('product_info').style.display = 'inline-block';
   }

}

function info_modify_and_save(tag) {

    const tr = tag.closest("tr");

    const cell_0 = tr.children[0];
    const cell_1 = tr.children[1];
    const cell_2 = tr.children[2];
    const cell_3 = tr.children[3];
    const cell_4 = tr.children[4];
    const cell_5 = tr.children[5];
    const cell_6 = tr.children[6];
    const cell_7 = tr.children[7];
    const cell_8 = tr.children[8];


    if (cell_0.contentEditable == "true") {

        cell_0.contentEditable = "false";
        cell_1.contentEditable = "false";
        cell_2.contentEditable = "false";
        cell_3.contentEditable = "false";
        cell_4.contentEditable = "false";
        cell_5.contentEditable = "false";
        cell_6.contentEditable = "false";
        cell_7.contentEditable = "false";
        cell_8.contentEditable = "false";

        tag.innerText = "수정";

    } else {

        cell_0.contentEditable = "true";
        cell_1.contentEditable = "true";
        cell_2.contentEditable = "true";
        cell_3.contentEditable = "true";
        cell_4.contentEditable = "true";
        cell_5.contentEditable = "true";
        cell_6.contentEditable = "true";
        cell_7.contentEditable = "true";
        cell_8.contentEditable = "true";

        tag.innerText = "수정 완료";

        cell_0.focus();
    }
}

function info_registration_and_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function info_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function info_addRow() {

    const table = document.getElementById('product');
    const new_row = table.insertRow();

    const cell_length = table.rows[0].cells.length;

    for (let i = 0; i < cell_length; i++) {
        const new_cell = new_row.insertCell(i);
        let temp_html = '';

        if (i == 9) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">대분류 1</option>' +
                '<option value="second">대분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if (i == 10) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">중분류 1</option>' +
                '<option value="second">중분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if (i == 11) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">소분류 1</option>' +
                '<option value="second">소분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if (i == 12) {

            temp_html = '<td>' +
                '<input type="file" accept="image/png, image/jpg">' +
                '</td>';
        }

         if(i == 13) {

             temp_html =
                  '<td>' +
                       '<div class="actions">' +
                             '<button class="action-button action-button-edit" onclick="info_modify_and_save(this)">수정</button>' +
                             '<button class="action-button action-button-delete" onclick="info_registration_and_delete(this)">등록</button>' +
                       '</div>' +
                  '</td>';

         }

        new_cell.insertAdjacentHTML('afterbegin', temp_html);

    }
}

/*---------------------계약등록-------------------*/

function contract_show(get) {

    document.getElementById('contract_info').style.display = 'none';

    console.log(get);

    if(get == 'co') {

        document.getElementById('contract_info').style.display = 'inline-block';
    }
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

function contract_registration_and_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function contract_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

<!-- 선택한 품목명로 품목코드 검색-->
function p_code_value(p_code) {

    console.log(p_code);
}

<!-- 선택한 사업자번호로 기업명 검색-->
function b_code_value(b_code) {

    console.log(b_code);
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
                '<select onChange="p_code_value(this.options[this.selectedIndex].value)">' +
                '<option value="p_code1">나사(A, 길승산업)</option>' +
                '<option value="p_code2">A품목(B, 마텍스)</option>' +
                '<option value="p_code3">망치(C, 길승산업)</option>' +
                '<option value="p_code4">나사(D, 마텍스)</option>' +
                '</select>' +

                '<button>확인</button>';

        }

        if (i == 2) {

            temp_html =
                '<select onChange="b_code_value(this.options[this.selectedIndex].value)">' +
                '<option value="b_code1">Xxx-xxx-xxxxx</option>' +
                '<option value="b_code2">사업자 번호1</option>' +
                '<option value="b_code3">사업자 번호2</option>' +
                '</select>' +

                '<button>확인</button>';

        }

        if (i == 6) {

            temp_html =
                '<td>' +
                '<div class="actions">' +
                '<button class="action-button action-button-edit" onclick="contract_modify_and_save(this)">수정</button>' +
                '<button class="action-button action-button-delete" onclick="contract_registration_and_delete(this)">삭제</button>' +
                '</div>' +
                '</td>';
        }

        new_cell.insertAdjacentHTML('afterbegin', temp_html);

    }
}

        // 계약서 모달창 호출
function OpenContract() {
    $(".ContractModal").css('display', 'block');
}

        // 계약서 모달창 닫기
function CloseContract() {
    $(".ContractModal").css('display', 'none');
}

/*---------조달계획등록--------------*/
function contract_show(get) {

   if(get == 'co') {

      document.getElementById('plan_table_info').style.display = 'inline-block';
   }
}


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

    alert("등록 완료");

    const tr = tag.closest("tr");
    tr.remove();
}

function td_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

function select_row() {

    const table = document.getElementById('plan_table');

    const cell_length = table.rows[0].cells.length;

    const num = 3;

    for (let i = 1; i <= num; i++) {

        const new_row = table.insertRow(i);

        for (let j = 0; j < cell_length; j++) {
            let temp_html = '';
            const new_cell = new_row.insertCell(j);

            if (i == 1) {

                if (j == 0) {
                    temp_html = '<td>나사</td>';
                }

                if (j == 1) {
                    temp_html = '<td>A</td>';
                }

                if (j == 2) {
                    temp_html = '<td>16</td>';
                }

                if (j == 3) {
                    temp_html = '<td>4</td>';
                }

                if (j == 4) {
                    temp_html = '<td>5일</td>';
                }

                if (j == 5) {
                    temp_html = '<td>24/01/16</td>';
                }

                if (j == 6) {
                    temp_html = '<td>길승산업</td>';
                }

                if (j == 7) {
                    temp_html = '<td>12</td>';
                }

                if (j == 8) {
                    temp_html = '<td>24/01/10</td>';
                }

                if (j == 9) {
                    temp_html =
                        '<td>' +
                        '<div class="actions">' +
                        '<button class="action-button action-button-edit" onclick="modify_and_save(this)">수정</button>' +
                        '<button class="action-button action-button-registration" onclick="registration_and_delete(this)">등록</button>' +
                        '</div>' +
                        '</td>';
                }

            }

            if (i == 2) {

                if (j == 0) {
                    temp_html = '<td>A품목</td>';
                }

                if (j == 1) {
                    temp_html = '<td>B</td>';
                }

                if (j == 2) {
                    temp_html = '<td>12</td>';
                }

                if (j == 3) {
                    temp_html = '<td>7</td>';
                }

                if (j == 4) {
                    temp_html = '<td>8일</td>';
                }

                if (j == 5) {
                    temp_html = '<td>24/01/23</td>';
                }

                if (j == 6) {
                    temp_html = '<td>길승산업</td>';
                }

                if (j == 7) {
                    temp_html = '<td>5</td>';
                }

                if (j == 8) {
                    temp_html = '<td>24/01/15</td>';
                }

                if (j == 9) {
                    temp_html =
                        '<td>' +
                        '<div class="actions">' +
                        '<button class="action-button action-button-edit" onclick="modify_and_save(this)">수정</button>' +
                        '<button class="action-button action-button-registration" onclick="registration_and_delete(this)">등록</button>' +
                        '</div>' +
                        '</td>';
                }

            }

            if (i == 3) {

                if (j == 0) {
                    temp_html = '<td>망치</td>';
                }

                if (j == 1) {
                    temp_html = '<td>C</td>';
                }

                if (j == 2) {
                    temp_html = '<td>20</td>';
                }

                if (j == 3) {
                    temp_html = '<td>8</td>';
                }

                if (j == 4) {
                    temp_html = '<td>8일</td>';
                }

                if (j == 5) {
                    temp_html = '<td>24/02/13</td>';
                }

                if (j == 6) {
                    temp_html = '<td>길승산업</td>';
                }

                if (j == 7) {
                    temp_html = '<td>12</td>';
                }

                if (j == 8) {
                    temp_html = '<td>24/02/03</td>';
                }

                if (j == 9) {
                    temp_html =
                        '<td>' +
                        '<div class="actions">' +
                        '<button class="action-button action-button-edit" onclick="modify_and_save(this)">수정</button>' +
                        '<button class="action-button action-button-registration" onclick="registration_and_delete(this)">등록</button>' +
                        '</div>' +
                        '</td>';
                }

            }

            new_cell.insertAdjacentHTML('afterbegin', temp_html);
        }
    }
}