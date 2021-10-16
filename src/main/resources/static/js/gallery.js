$('#galleryAdd').submit((event) => {
    console.log("Tıklanıldı")
    event.preventDefault();


    const categoryGallery = $("#categoryGallery").val()
    const galleryname = $("#galleryname").val()
    const gallery_detail = $("#gallery_detail").val()
    const gallery_status = $("#gallery_status").val()



    const obj = {
        categoryGallery : {id :categoryGallery, gallerycategoryname:categoryGallery},
        galleryname : galleryname,
        gallery_detail : gallery_detail,
        gallery_status : gallery_status,

    }
    if ( select_id != 0 ) {
        // update
        obj["id"] = select_id;
    }

    $.ajax({
        url: './gallery_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)

                $("#categoryGallery").val(" ")
                $("#galleryname").val(" ")
                $("#gallery_detail").val(" ")
                $("#gallery_status").val(" ")

                allGalleryResult()
                fncReset()

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

//-------------------------------------------- Product Add - Finish --------------------------------------------//
//-------------------------------------------- Product list  --------------------------------------------//
function allGalleryResult(){
    $.ajax({
        url: './gallery_mvc/list',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRow(data)

        },
        error: function (err) {
            console.log(err)
            alert("İşlem sırısında bir hata oluştu!");
        }
    })
}
allGalleryResult()
//-------------------------------------------- Product table  --------------------------------------------//

function fncReset(){
    select_id = 0;

}

let globalArr = []
function createRow(data){
    let html = ``
    globalArr = data
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        html += `<div class="mt-3 mb-3"> <div  class="card" style="width: 18rem;float:left;">
                <img src=" " class="card-img-top" alt="...">
                
                <div class="card-body">
                     <h6 lass="card-title">${itm.galleryname}</h6>
                    <p class="card-text">${itm.gallery_detail}<hr></p>
                    <a href="#" class="btn btn-outline-secondary"><i class="fas fa-eye"></i></a>
                    <button onclick="fncGalleryUpdate(`+i+`)" type="button" data-bs-toggle="modal" data-bs-target="#product" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncGalleryDelete(`+itm.id+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
                    
                 </div>
            </div>
</div>`
    }
    $("#addCard").html(html)
}

//Product delete - start
function fncGalleryDelete( id ) {
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./gallery_mvc/delete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allGalleryResult()
                    fncReset()
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

//Product delete - end


//Product update - start
let select_id=0;
function fncGalleryUpdate(i){
    const itm = globalArr[i];
    console.log(itm.categoryGallery)

    select_id = itm.id
    $("#categoryGallery").val(itm.categoryGallery.id)
    $("#galleryname").val(itm.galleryname)
    $("#gallery_detail").val(itm.gallery_detail)
    $("#gallery_status").val(itm.gallery_status)
}