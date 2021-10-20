$('#contactForm').submit((event) => {

    let answer = confirm("Mesajı göndermek istediğinizden emin misiniz?")
    if(answer) {

        event.preventDefault();

        const name = $("#name").val()
        const email = $("#email").val()
        const message = $("#message").val()

        const obj = {
            name: name,
            email: email,
            message: message
        }

        $.ajax({
            url: './contact/add',
            type: 'POST',
            data: JSON.stringify(obj),
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                if (data) {
                    console.log(data)
                    $("#name").val(" ")
                    $("#email").val(" ")
                    $("#message").val(" ")

                } else {
                    console.log("Veri dönmedi.")
                }
            },
            error: function (err) {
                console.log(err)
                alert("İşlem işlemi sırısında bir hata oluştu!");
            }
        })
    }


})