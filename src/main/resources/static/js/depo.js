//-------------------------------------------- Depo Add - Start --------------------------------------------//
$('#depo').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const depo_name= $("#depo_name").val()
    let depo_status = $("#depo_status").val()
    if (document.getElementById('depo_status').checked){
        depo_status="true";
    }else {
        depo_status="false";
    }

    const obj = {
        depo_name: depo_name,
        depo_status: depo_status

    }

    $.ajax({
        url: './depo/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)

                $("#depo_name").val(" ")
                $("#depo_name").focus()
                allDepoResult()


            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (err) {
            console.log(err)
            alert("Ekleme işlemi sırısında bir hata oluştu!");
        }
    })
})
//-------------------------------------------- Depo Add - Finish --------------------------------------------//
//-------------------------------------------- Depo List - Start --------------------------------------------//
function allDepoResult(){
    $.ajax({
        url: './depo/list',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRow(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
allDepoResult()

let globalArr=[]
function createRow(data){
    let html=``
    for (let i=0; i<data.length;i++){
        globalArr=data
        const itm=data[i]
        const st = itm.depo_status== true ? 'Aktif' : 'Aktif Değil'
        html += `<tr>
            <td>${itm.depo_id}</td>
            <td>${itm.depo_name}</td>
            <td>`+st+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncDepoDelete(${itm.depo_id})" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
              </div>
            </td>

        </tr>`
    }
    $("#depoRow").html(html)
}
//-------------------------------------------- Depo list - Finish --------------------------------------------//
//-------------------------------------------- Depo delete - Start --------------------------------------------//
function fncDepoDelete(depo_id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./depo/delete/"+depo_id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allDepoResult()
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
//-------------------------------------------- Depo delete - Finish --------------------------------------------//