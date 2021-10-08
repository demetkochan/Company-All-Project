//-------------------------------------------- Category Add - Start --------------------------------------------//
$('#categoryAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const cg_name= $("#cg_name").val()
    let cg_status = $("#cg_status").val()
    if (document.getElementById('cg_status').checked){
        cg_status="true";
    }else {
        cg_status="false";
    }

    const obj = {
        cg_name: cg_name,
        cg_status:cg_status

    }

    $.ajax({
        url: './categoryDescription/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)

                $("#cg_name").val(" ")
                $("#cg_name").focus()
                allCategoryResult()


            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (err) {
            console.log(err)
            alert("Eklem işlemi sırısında bir hata oluştu!");
        }
    })
})
//-------------------------------------------- Category Add - Finish --------------------------------------------//
//-------------------------------------------- Category List - Start --------------------------------------------//
function allCategoryResult(){
    $.ajax({
        url: './categoryDescription/list',
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
allCategoryResult()

let globalArr=[]
function createRow(data){
    let html=``
    for (let i=0; i<data.length;i++){
        globalArr=data
        const itm=data[i]
        const st = itm.cg_status== true ? 'Aktif' : 'Aktif Değil'
        html += `<tr>
            <td>${itm.cg_id}</td>
            <td>${itm.cg_name}</td>
            <td>`+st+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncCategoryDelete(${itm.cg_id})" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
              </div>
            </td>

        </tr>`
    }
    $("#categoryRow").html(html)
}
//-------------------------------------------- Category list - Finish --------------------------------------------//
//-------------------------------------------- Category delete - Start --------------------------------------------//
function fncCategoryDelete(cg_id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./categoryDescription/delete/"+cg_id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allCategoryResult()
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
//-------------------------------------------- Category delete - Finish --------------------------------------------//