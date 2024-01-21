function Tradingmy(event){  //모달창열기
//    console.log("트레딩모달 버튼 클릭됨");
    $(".TradingmyModal").css('display','block');

    var clickedElement = event.target;
    console.log("ID: " + clickedElement.id);

    $.ajax({
        url:'TradingStatementModal?uuid='+clickedElement.id,  // 'ida' 대신 'uuid'로 수정
        type: 'get',
        success: function(asdasData) {
            console.log("잘보냄");
             $('#externalPageContent').html(asdasData);
            // console.log(asdasData);
        },
        error: function(error) {
            console.log("오류발생 0");
        }
    });

}


function CloseTradingMy(){  //모달창열기
//    console.log("모달콘튼츠창클릭 백그라운드 클릭");
    $(".TradingmyModal").css('display','none');
}


