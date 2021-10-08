$('#c_search').click(function () {
    console.log("Tıklandı.")
    var customerSearchText = $("#name").val()
    console.log(customerSearchText)
    if (customerSearchText.length == 0) {
        alert("Herhangi bir Değer Yazınız")
    } else {
        $.ajax({
            url: './customerSearch?name='+customerSearchText,
            type: 'GET',
            dataType: 'Json',
            success: function (data) {

                $("#customerTable").remove();
                cRow(data)
            },
            error: function (err) {
                console.log(err)
            }
        })

    }

})

let globalA = []
function cRow(data){
    console.log("KKK"+data)
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalA = data
        const itm = data[i]

        html +=` <tr>
            <th scope="row"></th>
            <td>${itm.c_name} ${itm.c_surname}</td>
            <td>${itm.c_phone}</td>
            <td>${itm.c_email}</td>
            <td>${itm.tc_no}</td>
           <td>${itm.c_note}</td>
           <td>${itm.address}</td>

          </tr>`
    }
    $("#customerTable").html(html)
}
