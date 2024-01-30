function orderImage(){
    html2canvas(document.getElementById('screenOrderArea')).then((canvas) => {
        const imageDataURL = canvas.toDataURL("image/jpg");

        const imageJson = {imageDataURL : imageDataURL}

        fetch('/order-imageURl', {
            method: 'post',
            headers: {
                "Content-Type": 'application/json',
            },
            body: JSON.stringify(imageJson),
        })

    });

}