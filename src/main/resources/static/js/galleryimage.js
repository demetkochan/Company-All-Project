let selectedImage = 0;
$("#galleries").on("change",function (){
    console.log("Tıklanıldı")
    selectedImage = (this.value)
    console.log(selectedImage)
    galleryImage(selectedImage)
})

let globalArr=[]
function galleryImage(id) {

    $.ajax({
        url: './galleryDetail_mvc/galleryImage/'+id,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("get metodu çalıştı")
            const obj = JSON.parse(data)

            let html = ``
            for (let i = 0; i < obj.length; i++) {
                const itm = obj[i]
                globalArr = obj
                html +=`<div class="card" style="width: 14rem;float:left; margin-left: 0.5rem; margin-bottom: 0.5rem; ">
                <button onclick="fncView(`+i+`)"  data-bs-toggle="modal" data-bs-target="#ViewModel" type="submit" class="btn btn-outline-primary ">
                <img src="../uploads/gallery/${itm.g_Id}/${itm.gallery_Image}" class="card-img-top"  alt="...">
                </button>
                <div class="card-body">
                      <button onclick="fncImageDelete(`+itm.gid+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
                    
                 </div>
       
            </div >`
           }
            $("#imageAdd").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}
galleryImage(selectedImage);


function fncView( i ) {
    const itm = globalArr[i];
    $("#gallery-image").text(itm.galleryname + '/' + itm.gid)
    $("#imgID").attr('src','/uploads/gallery/'+itm.g_Id+'/'+itm.gallery_Image)

}

function fncImageDelete(p_Id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./galleryDetail_mvc/delete/"+p_Id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    galleryImage(selectedImage)
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

