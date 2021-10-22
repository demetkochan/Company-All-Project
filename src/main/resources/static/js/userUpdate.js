$('#updateuser').submit((event) => {
    event.preventDefault();

    const firstName =  $("#firstName").val()
    const lastName =  $("#lastName").val()
    const email =  $("#email").val()


    const obj = {
        firstName:firstName,
        lastName:lastName,
        email:email
    }

    $.ajax({
        url: './userUpdate_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#firstName").val(" ")
                $("#lastName").val(" ")
                $("#email").val(" ")

            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (xhr,status,error) {
            alert(xhr.responseText)

        }
    })

})