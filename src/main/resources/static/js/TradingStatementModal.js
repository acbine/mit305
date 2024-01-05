function convertToImage(){
        html2canvas(document.getElementById('screenshotarea'),{scale:10}).then((canvas) => {
            const imageDataURL = canvas.toDataURL("image/jpg");

            const a = document.createElement("a");
            a.href = imageDataURL;
            a.download = "거래명세서.jpg";
            a.click();
        });

    }

