function contract_show(get) {

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

        if(i == 6) {

            temp_html =
                '<select onChange="payment(this.options[this.selectedIndex].value)">' +
                '<option value="cash">현금 입금</option>' +
                '<option value="check">수표</option>' +
                '</select>'
        }

        if (i == 7) {

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

document.write(`<script th:inline="javascript">

    function contract_show(get) {

        document.getElementById('contract_info').style.display = 'none';

        console.log(get);

        if(get == 'co') {

                document.getElementById('contract_info').style.display = 'inline-block';
        }
    }


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

    function contract_registration_and_delete(tag) {

        alert("계약 등록 완료");

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

        for(let i = 0; i < cell_length; i++) {
            const new_cell = new_row.insertCell(i);
            let temp_html = '';

            if(i == 0) {

                temp_html =
                 '<input type="text" style="width:70px; height:25px; font-size:15px; text-align:center;">' +

                '<button onclick="get_pname()">확인</button>';

            }

            if(i == 2) {

                temp_html =
                 '<input type="text" style="width:70px; height:25px; font-size:15px; text-align:center;">' +

                '<button onclick="get_cname()">확인</button>';

            }

            if(i == 4) {

                temp_html = '<input type="text" style="width:50px; height:25px; font-size:15px; text-align:center;">';
            }

            if(i == 5) {

                temp_html = '<input type="text" style="width:50px; height:25px; font-size:15px; text-align:center;">';
            }


            if(i == 6) {

                temp_html =
                    '<td><input id="cs_date" type="date" name="startDate"></td>' +
                    '<td>~</td>' +
                    '<td><input id="ce_date" type="date" name="endDate"></td>';
            }

            if(i == 7) {

                temp_html =

                    '<select id="payment_select" onChange="name_value(this.options[this.selectedIndex].value)">' +
                        '<option value="none">=== 선택 ===</option>' +
                        '<option value="n1">현금 지불</option>' +
                        '<option value="n2">수표</option>' +
                    '</select>';
            }

            if(i == 8) {

                temp_html =
                    '<td>' +
                        '<div class="actions">' +
                            '<button class="action-button action-button-delete" onclick="contract_registration_and_delete(this)" width = "74px" height = "35[x">등록</button>' +
                        '</div>' +
                    '</td>';
            }

             new_cell.insertAdjacentHTML('afterbegin', temp_html);

        }
    }

</script>`)