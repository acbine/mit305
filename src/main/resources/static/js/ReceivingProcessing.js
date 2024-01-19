

$(function ReceivingProcessing(){  //모달 관련 js
    $("#modalBTN").on("click",function(){
        console.log("모달 버튼 클릭됨");
        $("#myModal").css('display','block');
    });

    $(".button-style").on("click",function(){
        console.log("모달 닫기 클릭됨");
        $("#myModal").css('display','none');
    });


    $('.modal-content').on('click',function(){
        console.log("모달콘튼츠창클릭 백그라운드 클릭");
    });

    $("submitBTN").on('click',function(){
        console.log("모달창 안에서 데이터 전송함");
        $("#myModal").css('display','block');
    });


    const table = $('.table-style');
    const headerRow = table.find('thead tr');


    $(".table-container").on("scroll",function(){
        console.log("테이블콘테이너 스크로됨");


    });
});

 function sendData(procurementplanCode){


    var form = document.createElement('form');
    form.action = 'ReceivingProcessStore';  // 폼이 전송될 URL
    form.method = 'get';  // 폼 전송 방식
    console.log("샌드데이터에서");

    var aa = document.createElement('input');
    aa.type = 'text';
    aa.name = 'procurementplan_code';  // 폼 데이터의 이름
    aa.value =procurementplanCode;  // 폼 데이터의 값
    form.appendChild(aa);

    var bb = document.createElement('input');
    bb.type = 'text';
    bb.name = 'store';  // 폼 데이터의 이름
    bb.value = 9999;  // 폼 데이터의 값
    form.appendChild(bb);

    document.body.appendChild(form);

    form.submit();
    console.log("데이터 전송완료");

 }

