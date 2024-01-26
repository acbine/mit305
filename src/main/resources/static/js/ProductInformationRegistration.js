/*------------품목의 계약 정보 모달창 관련한 js 코드------------*/
function select_pname(get_name) {

    const pname_tr = get_name.closest("tr");

    const name_cell = pname_tr.children[1]; // 버튼 눌린 행의 품목 코드

}



function OpenProductContract() {

    $(".Product_Contract_Modal").css('display', 'block');
}

function CloseProductContract() {

    $(".Product_Contract_Modal").css('display', 'none');
}

/*------------품목 등록 관련한 js 코드------------*/

// 소분류 검색
function select_part() {

    $.ajax({

        type : "POST",
        url : "/getPart",
        success : function(part) {
            //console.log(part);
            info_addRow(part);
        },

        error : function (error) {
            console.error("소분류 불러오기 실패");
        }
    });

}

// 품목 정보 추가
function info_addRow(partList) {

    var dropdownOptions = "";

    dropdownOptions += '<option value="none">=== 선택 ===</option>';

    for (var i = 0; i < partList.length; i++) {
        dropdownOptions += '<option value = "' + partList[i].id + '">' + partList[i].part + '</option>';
    }

    var newInfo =
        '<tr>' +
            '<td><input type="text" class = "get_name" style = "width:90.67px; height:29px; font-size:15px;"></td>' + // 품목명
            '<td><input type="text" class = "get_abbreviation" style = "width:75.67px; height:29px; font-size:15px;"></td>' + // 약칭
            '<td><input type="text" class = "get_texture" style = "width:75.67px; height:29px; font-size:15px;"></td>' + // 재질
            '<td><input type="text" class = "get_width" style = "width:85.67px; height:29px; font-size:15px;"></td>' + // 가로
            '<td><input type="text" class = "get_length" style = "width:89.67px; height:29px; font-size:15px;"></td>' + // 세로
            '<td><input type="text" class = "get_height" style = "width:89.67px; height:29px; font-size:15px;"></td>' + // 높이
            '<td><input type="text" class = "get_weight" style = "width:89.67px; height:29px; font-size:15px;"></td>' + // 중량
            '<td>' + // 소분류 선택
                '<select class = "selectParts" style = "text-align:center;">' + dropdownOptions + '</select>' +
            '</td>' +
            '<td><input type="file" class="image"></td>' + // 파일 업로드
            '<td>' +
                '<div class="actions">' +
                    '<button onclick="Product_registration(this); info_delete(this);">등록</button>' +
                    '<button class="action-button action-button-delete" onclick="info_delete(this)">삭제</button>'
                '</div>' +
            '</td>'
        '</tr>';

        $("#product tbody").append(newInfo);
}

function Product_registration(button) {

// 품목명
var product_name = $(button).closest("tr").find(".get_name").val();

// 소분류
var id = $(button).closest("tr").find(".selectParts").val();

// 약칭
var product_abbreviation = $(button).closest("tr").find(".get_abbreviation").val();

// 재질
var texture = $(button).closest("tr").find(".get_texture").val();

// 가로
var width = $(button).closest("tr").find(".get_width").val();

// 세로
var length = $(button).closest("tr").find(".get_length").val();

// 높이
var height = $(button).closest("tr").find(".get_height").val();

// 중량
var weight = $(button).closest("tr").find(".get_weight").val();

//이미지 이름
var imageName = $(button).closest("tr").find(".image")[0];


var formData = new FormData();

formData.append("product_name", product_name);
formData.append("id", id);
formData.append("product_abbreviation", product_abbreviation);
formData.append("texture", texture);
formData.append("width", width);
formData.append("length", length);
formData.append("height", height);
formData.append("weight", weight);
formData.append("image_name", imageName.files[0]);

for(var fom of formData.entries()) {
    console.log(fom[0] + ":" + fom[1]);
}


    $.ajax({

        type : "POST",
        url : "/saveData",
        contentType : false,
        processData : false,
        data : formData,

        success : function (response) {

            alert("품목 정보 등록 완료");
        },
        error : function(error) {
            alert("품목 정보 등록 오류")
        }

    });
}

function info_modify_and_save(tag) {

}


// 단순한 html 행 삭제
function info_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}

// 전체 품목 정보 검색
function select_info() {

    $.ajax({

        url : '/search/product',
        method : 'GET',
        dataType : 'json',

        success : function(data) {
            console.log(data);
            resultTable(data);
        },

        erorr : function(error) {
            console.error(error);
        }

    });
}

// 검색한 결과 테이블로 출력
function resultTable(data_info) {

$('#product_info tbody').empty();

    $.each(data_info, function(index, info) {

        $('#product_info tbody')
            .append(
                '<tr>' +
                    '<td>' + info.product_name + '</td>' + // 품목명
//                    '<td>' + info.product_code + '</td>' + // 품목 코드
                    '<td>' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_product_name value =' + info.product_abbreviation + '>' + '</td>' + // 약칭
                    '<td>' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_product_name value =' + info.texture + '>' + '</td>' + // 재질
                    '<td>' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_product_name value =' + info.width + '>' + '</td>' + // 가로
                    '<td>' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_product_name value =' + info.length + '>' + '</td>' + // 세로
                    '<td>' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_product_name value =' + info.height + '>' + '</td>' + // 높이
                    '<td>' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_product_name value =' + info.weight + '>' + '</td>' + // 중량
                    '<td>' + info.part.assy.unit.unit + '</td>' + // 대분류
                    '<td>' + info.part.assy.assy + '</td>' + // 중분류
                    '<td>' + info.part.part + '</td>' + // 소분류
                    '<td>' + info.image_name+ '</td>' + // 사진 이름
                    '<td>' +
                        '계약 안됨' +
                    '</td>' +
                    '<td>' +
                         '<div class="actions">' +
                             '<button class="action-button action-button-edit" onclick="info_modify_and_save(this)">수정</button>' +
                             '<button class="action-button action-button-delete" onclick="info_delete(this)">삭제</button>' +
                         '</div>' +
                    '</td>' +
                '</tr>'
            )
    });

}

// 조인 (계약 상태 확인) 검색 결과
//function select_product_contract() {
//
//        $.ajax({
//
//            url : '/search/product/contract',
//            method : 'GET',
//            dataType : 'json',
//
//            success : function(data) {
//                console.log(data);
//            },
//
//            erorr : function(error) {
//                console.error(error);
//            }
//
//        });
//
//}


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

        for (var i = 0; i < cnt.length; i++) {

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
