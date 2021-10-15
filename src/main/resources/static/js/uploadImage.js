let selectedProduct = 0;
$("#products").on("change",function (){
    console.log("Tıklanıldı")
    selectedProduct = (this.value)
    console.log(selectedProduct)
    productImage(selectedProduct)
})

let globalArr=[]
function productImage(id) {

    $.ajax({
        url: './uploadImage_mvc/productImage/'+id,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("get metodu çalıştı")
            const obj = JSON.parse(data)
            let html = ``
            for (let i = 0; i < obj.length; i++) {
                globalArr = obj
                const itm = obj[i]
                html +=` <tr>
            <td>${itm.p_Id}</td>
            <td>${itm.product_Image}</td>
            <td><img src="../uploads/products/${itm.id}/${itm.product_Image}" style="height: 50px; weight:50px;"></td>
         
            <td class="text-right" >
               <div class="btn-group" role="group">
                  <button onclick="fncView(`+i+`)"  data-bs-toggle="modal" data-bs-target="#ViewModel" type="submit" class="btn btn-outline-primary "><i class="fas fa-eye"></i></button>
                   <button onclick="fncImageDelete(${itm.p_Id})" type="button" class="btn btn-outline-success "><i class="far fa-trash-alt"></i></button>
              </div>
            </td>
          </tr>`
            }
            $("#imageRow").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}
productImage(selectedProduct);

function fncView( i ) {
    const itm = globalArr[i];
    console.log(itm.product_Image)
    $("#productname").text(itm.productname + '/' + itm.p_Id)
    $("#imgID").attr('src','/uploads/products/'+itm.id+'/'+itm.product_Image)

}

function fncImageDelete(p_Id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./uploadImage_mvc/delete/"+p_Id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    productImage(selectedProduct)
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