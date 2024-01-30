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
            '<td class="productImformationTable-data" style="width:125.42px;"><input type="text" class = "get_name" style = "width:60px; font-size:15px;"></td>' + // 품목명
            '<td class="productImformationTable-data" style="width:109px;"><input type="text"  maxlength="3" class = "get_abbreviation" style = "width:60px;  font-size:15px;"></td>' + // 약칭
            '<td class="productImformationTable-data" style="width:109px;"><input type="text" class = "get_texture" style = "width:60px;  font-size:15px;"></td>' + // 재질
            '<td class="productImformationTable-data" style="width:120px;"><input type="text" class = "get_width" style = "width:60px; ; font-size:15px;"></td>' + // 가로
            '<td class="productImformationTable-data" style="width:125px;"><input type="text" class = "get_length" style = "width:60px;  font-size:15px;"></td>' + // 세로
            '<td class="productImformationTable-data" style="width:125px;"><input type="text" class = "get_height" style = "width:60px;  font-size:15px;"></td>' + // 높이
            '<td class="productImformationTable-data" style="width:125px;"><input type="text" class = "get_weight" style = "width:60px;  font-size:15px;"></td>' + // 중량
            '<td class="productImformationTable-data" style="width:220px;">' + // 소분류 선택
                '<select class = "selectParts" style = "text-align:center; width:180px; " >' + dropdownOptions + '</select>' +
            '</td>' +

            '<td class="productImformationTable-data" style="width:220px;"> '+
                '<input type="file" class="image" onchange = "fileCheck(this)" accept="image/png, image/jpg" style = "width:180px;">' +
            '</td>' + // 파일 업로드

            '<td class="productImformationTable-data" style="width:118.97px;" >' +
                '<button onclick="Product_registration(this); info_delete(this);">등록</button>' +
                '<button class="action-button action-button-delete" onclick="info_delete(this)">삭제</button>'+
            '</td>'+
        '</tr>';

        $("#product tbody").append(newInfo);
}

function fileCheck(file_obj) {

    path_point = file_obj.value.lastIndexOf('.');
    file_point = file_obj.value.substring(path_point+1, file_obj.length);
    file_type = file_point.toLowerCase();

    if(file_type == 'jpg' || file_type == 'png') {

    } else {
        alert("이미지 파일 선택 요청");
    }

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
            alert(error.responseJSON.msg)
        }

    });
}

var infoFromServer; // 전역 변수

// 조인 테스트
function select_test() {

    $.ajax({

        url : '/search/test',
        method : 'GET',
        dataType : 'json',

        success : function(data) {
            console.log(data);

            infoFromServer = data;

            resultTable2(data);
        },

        erorr : function(error) {
            console.error(error);
        }

    });

}

function resultTable2(data_info) {

$('#product_info tbody').empty();

    $.each(data_info, function(index, info) {

        var name = decodeURIComponent(info.image_name);

        $('#product_info tbody')
            .append(
                '<tr>' +
                    '<td class="productImformationTable-data">' + info.product_name + '</td>' + // 품목명
                    '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_product_abbreviation value =' + info.product_abbreviation + '>' + '</td>' + // 약칭
                    '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_texture value =' + info.texture + '>' + '</td>' + // 재질
                    '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_width value =' + info.width + '>' + '</td>' + // 가로
                    '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_length value =' + info.length + '>' + '</td>' + // 세로
                    '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_height value =' + info.height + '>' + '</td>' + // 높이
                    '<td class="productImformationTable-data">' + '<input type="text" style = "width:69.67px; height:"46px"; font-size:15px;" class = now_weight value =' + info.weight + '>' + '</td>' + // 중량
                    '<td class="productImformationTable-data">' + info.unit + '</td>' + // 대분류
                    '<td class="productImformationTable-data">' + info.assy + '</td>' + // 중분류
                    '<td class="productImformationTable-data">' + info.part + '</td>' + // 소분류
                    '<td class="productImformationTable-data">' +
                        '<img src="/images/Product/' + name + '" alt="'+ info.image_name +'" style="max-width: 100px; max-height: 100px;">' +
                    '</td>' + // 사진 이름

                    '<td class="productImformationTable-data">' +
                        (info.contract_code == 0 ? "계약 안됨" : "계약 됨") +
                    '</td>' +
                    '<td class="productImformationTable-data">' +
                         '<div class="actions">' +
                             '<button class="action-button action-button-edit" onclick="product_modify('+ info.product_code +')">수정</button>' +
                             '<button class="action-button action-button-delete" onclick="product_delete('+ info.product_code +'); info_delete(this)">삭제</button>' +
                         '</div>' +
                    '</td>' +
                '</tr>'
            )
    });

}

// 품목 정보 수정
function product_modify(product_code) {

var product_abbreviation = $(".now_product_abbreviation").eq(product_code-1);
var texture = $(".now_texture").eq(product_code-1);
var width = $(".now_width").eq(product_code-1);
var length = $(".now_length").eq(product_code-1);
var height = $(".now_height").eq(product_code-1);
var weight = $(".now_weight").eq(product_code-1);

var updateInfo = {};

    // 약칭
    if (product_abbreviation.val() !== infoFromServer[product_code - 1].product_abbreviation) {
        updateInfo.product_abbreviation = product_abbreviation.val();
    } else {
        updateInfo.product_abbreviation = infoFromServer[product_code - 1].product_abbreviation;
    }

    // 재질
    if (texture.val() !== infoFromServer[product_code - 1].texture) {
        updateInfo.texture = texture.val();
    } else {
        updateInfo.texture = infoFromServer[product_code - 1].texture;
    }

    // 가로
    if (width.val() !== infoFromServer[product_code - 1].width) {
        updateInfo.width = width.val();
    } else {
        updateInfo.width = infoFromServer[product_code - 1].width;
    }

    // 세로
    if (length.val() !== infoFromServer[product_code - 1].length) {
        updateInfo.length = length.val();
    } else {
        updateInfo.length = infoFromServer[product_code - 1].length;
    }

    // 높이
    if (height.val() !== infoFromServer[product_code - 1].height) {
        updateInfo.height = height.val();
    } else {
        updateInfo.height = infoFromServer[product_code - 1].height;
    }

    // 중량
    if (weight.val() !== infoFromServer[product_code - 1].weight) {
        updateInfo.weight = weight.val();
    } else {
        updateInfo.weight = infoFromServer[product_code - 1].weight;
    }


    $.ajax({

        type : "POST",
        url : "/info_edit/" + product_code,
        data : JSON.stringify (updateInfo),
        contentType : "application/json;charset=UTF-8",

        success : function(response) {
            console.log("수정된 품목 코드: " + product_code);
        },
        error : function(error) {
            console.error(product_code + ":" + error)
        }

    });

}

// 품목 정보 삭제
function product_delete(product_code) {

    $.ajax({

        type : "GET",
        url : "/delete/product/" + product_code,

        success : function(response) {

            console.log("삭제한 품목 코드: " + product_code);
        },

        error : function(error) {
            alert("삭제 실패");
            console.error(product_code, error);
        }

    });

}


// 단순한 html 행 삭제
function info_delete(tag) {

    const tr = tag.closest("tr");
    tr.remove();
}
