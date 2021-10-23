$('#updateCompany').submit((event) => {
    event.preventDefault();

    const companyName =   $("#companyName").val()
    const companyAddress =   $("#companyAddress").val()
    const phone =   $("#phone").val()


    const obj = {
        companyName:companyName,
        companyAddress:companyAddress,
        phone:phone

    }

    $.ajax({
        url: './settings_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#companyName").val("")
                $("#companyAddress").val("")
                $("#phone").val(" ")

            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (xhr,status,error) {
            alert(xhr.responseText)

        }
    })


})

//-------------------------------Şifre değiştirme-------------------------//

$('#PasswordUpdate').submit((event) => {
    event.preventDefault();

    const newPassword = $("#newPassword").val()
    const renewPassword =  $("#renewPassword").val()

    const obj = {
        newPassword:newPassword,
        renewPassword:renewPassword
    }


    $.ajax({
        url: './settings_mvc/changePassword',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#newPassword").val("")
                $("#renewPassword").val("")

            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (xhr,status,error) {
            alert(xhr.responseText)

        }
    })


})
