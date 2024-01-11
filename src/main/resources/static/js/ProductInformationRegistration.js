function info_show(get) {

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

    for(let i = 0; i < cell_length; i++) {
        const new_cell = new_row.insertCell(i);
        let temp_html = '';

        temp_html = '<input type="text" style="width:50px; height:25px; font-size:15px; text-align:center;">';

        if(i == 1) {

            temp_html = '<input type="text" style="width:73px; height:25px; font-size:15px; text-align:center;">';
        }

        if(i == 4) {

            temp_html = '<input type="text" style="width:73px; height:25px; font-size:15px; text-align:center;">';
        }

        if(i == 5) {

            temp_html = '<input type="text" style="width:73px; height:25px; font-size:15px; text-align:center;">';
        }

        if(i == 6) {

            temp_html = '<input type="text" style="width:73px; height:25px; font-size:15px; text-align:center;">';
        }

        if(i == 9) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">대분류 1</option>' +
                '<option value="second">대분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if(i == 10) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">중분류 1</option>' +
                '<option value="second">중분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if(i == 11) {

            temp_html =
                '<td>' +
                '<select name="state">' +
                '<option value="first">소분류 1</option>' +
                '<option value="second">소분류 2</option>' +
                '</select>' +
                '</td>';
        }

        if(i == 12) {

            temp_html = '<td>'+
                '<input type="file" accept="image/png, image/jpg">' +
                '</td>';
        }

        if(i == 13) {

            temp_html =
                '<td>' +
                '<div class="actions">' +
                '<button class="action-button action-button-delete" onclick="info_registration_and_delete(this)">등록</button>' +
                '</div>' +
                '</td>';

        }

        new_cell.insertAdjacentHTML('afterbegin', temp_html);

    }
}

document.write(`<script th:inline="javascript">

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


        if (cell_0.contentEditable == "true") {

            cell_0.contentEditable = "false";
            cell_1.contentEditable = "false";
            cell_2.contentEditable = "false";
            cell_3.contentEditable = "false";
            cell_4.contentEditable = "false";
            cell_5.contentEditable = "false";
            cell_6.contentEditable = "false";
            cell_7.contentEditable = "false";

            tag.innerText = "수정";

        //첫번째 셀의 contenteditable 속성이 false라면(나머지 셀들의 속성 동일)
        } else {

            //각 셀들의 contenteditable 속성 true로 모두 변경하여 수정 가능하게 함

            cell_0.contentEditable = "true";
            cell_1.contentEditable = "true";
            cell_2.contentEditable = "true";
            cell_3.contentEditable = "true";
            cell_4.contentEditable = "true";
            cell_5.contentEditable = "true";
            cell_6.contentEditable = "true";
            cell_7.contentEditable = "true";

            tag.innerText = "수정 완료";

            cell_0.focus();
        }
    }

    function info_registration_and_delete(tag) {

        var cnt = $('.cnt');

        cnt.change(function () {

            for (var i = 0; i< cnt.length; i++) {

                console.log(i + '번째: ' + cnt.eq(i).val());
            }

        });


        alert("품목 등록 완료");

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

        for(let i = 0; i < cell_length; i++) {
            const new_cell = new_row.insertCell(i);
            let temp_html = '';

            temp_html =

                 '<input type="text" class = "cnt" style="width:50px; height:25px; font-size:15px; text-align:center;">';

            if(i == 1) {

                temp_html =
                    '<input type="text" class = "cnt" style="width:73px; height:25px; font-size:15px; text-align:center;">';
            }

            if(i == 4) {

                temp_html =
                    '<input type="text" class = "cnt" style="width:73px; height:25px; font-size:15px; text-align:center;">';
            }

            if(i == 5) {

                temp_html =
                    '<input type="text" class = "cnt" style="width:73px; height:25px; font-size:15px; text-align:center;">';
            }

            if(i == 6) {

                temp_html =
                    '<input type="text" class = "cnt" style="width:73px; height:25px; font-size:15px; text-align:center;">';
            }

            if(i == 8) {

                temp_html =
                    '<td>' +
                        '<select name="state">' +
                            '<option value="first">대분류 1</option>' +
                            '<option value="second">대분류 2</option>' +
                        '</select>' +
                    '</td>';
            }

            if(i == 9) {

                temp_html =
                    '<td>' +
                        '<select name="state">' +
                            '<option value="first">중분류 1</option>' +
                            '<option value="second">중분류 2</option>' +
                        '</select>' +
                    '</td>';
            }

            if(i == 10) {

                temp_html =
                    '<td>' +
                        '<select name="state">' +
                            '<option value="first">소분류 1</option>' +
                            '<option value="second">소분류 2</option>' +
                        '</select>' +
                    '</td>';
            }

            if(i == 11) {

                temp_html = '<td>'+
                               '<input type="file" accept="image/png, image/jpg">' +
                               '</td>';
            }

            if(i == 12) {

                temp_html =
                '<td>' +
                        '<div class="actions">' +
                            '<button class="action-button action-button-delete" onclick="info_registration_and_delete(this)">등록</button>' +
                        '</div>' +
                '</td>';
            }

             new_cell.insertAdjacentHTML('afterbegin', temp_html);

        }
    }


</script>`)