function orderImage(){
    html2canvas(document.getElementById('forSendEmail')).then((canvas) => {
        const imageDataURL = canvas.toDataURL("image/jpg");
        const departName = document.getElementById("departName").innerText;
        const imageJson = {imageDataURL : imageDataURL,"departName":departName}

        fetch('/order-imageURl', {
            method: 'post',
            headers: {
                "Content-Type": 'application/json',
            },
            body: JSON.stringify(imageJson),
        })

    });


}