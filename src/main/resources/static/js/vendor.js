$('#addVendor').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const vendor_name = $("#vendor").val()
    const vendor_email = $("#email").val()
    const vendor_phone = $("#phone").val()

    const obj = {
        vendor_name: vendor_name,
        vendor_email: vendor_email,
        vendor_phone: vendor_phone
    }

    $.ajax({
        url: './vendor/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#vendor_name").val(" ")
                $("#vendor_email").val(" ")
                $("#vendor_phone").focus()
                allVendorResult()
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

//-------------------------------------------- Vendor Add - Finish --------------------------------------------//


//-------------------------------------------- Vendor list  --------------------------------------------//
function allVendorResult(){
    $.ajax({
        url: './vendor/list',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRow(data)
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })
}
allVendorResult()

//-------------------------------------------- Vendor table  --------------------------------------------//


let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.vid}</th>
          <td>${itm.vendor_name}</td>
          <td>${itm.vendor_email}</td>
          <td>${itm.vendor_phone}</td>
           <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <button onclick="fncVendorDelete(${itm.vid})" type="button" class="btn btn-outline-primary ">Sil</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableRow").html(html)
}

//-------------------------------------------- Vendor delete  --------------------------------------------//

function fncVendorDelete(vid){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./vendor/delete/"+vid,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allVendorResult()
                }else {
                    alert("Silme sırasında bir hata oluştu.")
                }
            },
            error: function (err){
                console.log(err)
            }
        })
    }
}


