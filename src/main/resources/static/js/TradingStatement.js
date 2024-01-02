
$(function Tradingmy(){  //모달창열기
    $("#show").on("click",function(){
        console.log("트레딩모달 버튼 클릭됨");
        $(".TradingmyModal").css('display','block');  
        
    });

    $(".TradingmyModal").on('click',function(){
        console.log("모달콘튼츠창클릭 백그라운드 클릭");
        $(".TradingmyModal").css('display','none');
    });
    
});
 

