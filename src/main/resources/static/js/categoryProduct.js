$('#addProductCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const product_categoryName = $("#product_categoryName").val()

    const obj = {
        product_categoryName: product_categoryName,
    }

    $.ajax({
        url: './category_mvc/productAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#product_categoryName").val(" ")
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
//-----------------------------------------Product Category Add Finish--------------------------------------------------------//

//--------------------------------------------Product Category list  --------------------------------------------//
function allProductCategoryResult(){
    $.ajax({
        url: './category_mvc/productList',
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
allProductCategoryResult()

//-------------------------------------------- News Table  --------------------------------------------//
let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.product_categoryName}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncProductCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryNewsUpdateModal">Güncelle</button>
                    <button onclick="fncProductCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableProductRow").html(html)
}
//-------------------------------------------- News Delete Function --------------------------------------------//
function fncProductCategoryDelete(id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./category_mvc/productDelete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allProductCategoryResult()
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
//-------------------------------------------- Product Update Function --------------------------------------------//
function fncProductCategoryUpdate( i ) {

    const item = globalArr[i];
    select_id = item.id
    console.log(select_id)
    $("#product_categoryName").val(item.product_categoryName)
}

