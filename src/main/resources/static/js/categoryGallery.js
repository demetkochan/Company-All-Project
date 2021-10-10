$('#addGalleryCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const gallery_categoryName = $("#gallery_categoryName").val()

    const obj = {
        gallery_categoryName: gallery_categoryName,
    }

    $.ajax({
        url: './category_mvc/galleryAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#news_categoryName").val(" ")
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
//-----------------------------------------News Category Add Finish--------------------------------------------------------//

//-------------------------------------------- News Category list  --------------------------------------------//
function allGalleryCategoryResult(){
    $.ajax({
        url: './category_mvc/galleryList',
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
allGalleryCategoryResult()

//-------------------------------------------- News Table  --------------------------------------------//
let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.gallery_categoryName}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncGalleryCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryNewsUpdateModal">Güncelle</button>
                    <button onclick="fncGalleryCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableGalleryRow").html(html)
}
//-------------------------------------------- News Delete Function --------------------------------------------//
function fncGalleryCategoryDelete(id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./category_mvc/galleryDelete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allGalleryCategoryResult()
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
//-------------------------------------------- News Update Function --------------------------------------------//
function fncGalleryCategoryUpdate( i ) {

    const item = globalArr[i];
    select_id = item.id
    console.log(select_id)
    $("#gallery_categoryName").val(item.gallery_categoryName)
}