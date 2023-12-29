

$(function(){  //모달 관련 js
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

 

