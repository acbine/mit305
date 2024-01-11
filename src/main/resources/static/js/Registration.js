/*---------계약서 모달창--------------*/
// 계약서 작성일 구하기
Contrac_date = new Date();
Contrac_year = Contrac_date.getFullYear();
Contrac_month = Contrac_date.getMonth() + 1;
Contrac_day = Contrac_date.getDate();

document.getElementById("year").innerHTML = Contrac_year + '년';
document.getElementById("month").innerHTML = Contrac_month + '월';
document.getElementById("day").innerHTML = Contrac_day + '일';

var n;

function name_value(value) {

    if(value == 'n1') {

        n = '길승산업';
    }

    if(value == 'n2') {

        n = '마텍스';
    }
}

function contract_select() {

    document.getElementById("text").innerHTML = 'TAE 와 ' + n + ' 은 계약서를 기준으로 다음과 같이 상품을 공급한다.';


    if (n == '길승산업') {

            document.getElementById("company_num").innerHTML = 'Xxx-xxx-xxxxx';
            document.getElementById("company_name").innerHTML = n;
            document.getElementById("company_email").innerHTML = 'abcde@gmail.com';
    }

    else {

            document.getElementById("company_num").innerHTML = '사업자번호1';
            document.getElementById("company_name").innerHTML = n;
            document.getElementById("company_email").innerHTML = 'aaaa@gmail.com';
    }
}

function contract_convertToImage(){

    const h1 = document.getElementById('name_select');
    const h2 = document.getElementById('select_contract');
    const h3 = document.getElementById('save');

    h1.style.display = 'none';
    h2.style.display = 'none';
    h3.style.display = 'none';

    html2canvas(document.getElementById('screen_area')).then((canvas) => {
        const imageDataURL = canvas.toDataURL("image/jpg");

        const a = document.createElement("a");
        a.href = imageDataURL;
        a.download = "계약서.jpg";
        a.click();
    });

}
