$('#contentAdd').submit((event) => {
    console.log("Tıklanıldı")
    event.preventDefault();


    const content_title = $("#content_title").val()
    const content_desc = $("#content_desc").val()
    const content_detail_desc = CKEDITOR.instances['content_detail_desc'].document.getBody().getText();
    const content_date = $("#content_date").val()
    const content_status = $("#content_status").val()


    const obj = {
        content_title: content_title,
        content_desc: content_desc,
        content_detail_desc: content_detail_desc,
        content_date: content_date,
        content_status: content_status
    }

    $.ajax({
        url: './content/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#content_title").val(" ")
                $("#content_desc").val(" ")
                $("#content_detail_desc").val(" ")

            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })











})