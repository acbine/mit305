function convertToImage(){

        html2canvas(document.getElementById('screenshotarea'),{scale:2}).then((canvas) => {
            const imageDataURL = canvas.toDataURL("image/jpg");

            const order= document.getElementById('Purchase').textContent;
            const adress= document.getElementById('email').textContent;
            //console.log("Perchase라는ID에서 불러온 Text값"+order);
            //console.log("email ID에서  불러온 Text값"+adress);
            //console.log("15만 이미지"+imageDataURL);

            const imageJson = { imageDataURL : imageDataURL , ordercode : order ,  emailadress : adress };

            //console.log(imageJson);

             fetch('/imageURl', {
                 method: 'post',
                 headers: {
                     "Content-Type": 'application/json',

                 },
                 body: JSON.stringify(imageJson),
             })
                 .then(response => response.json())
                 .then(data => {
                     console.log('서버로부터의 응답:', data);
                 })
                 .catch(error => {
                     console.error('오류 발생:', error);
                 });

        });

    }



