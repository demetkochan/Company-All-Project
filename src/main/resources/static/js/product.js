$('#productAdd').submit((event) => {
    console.log("Tıklanıldı")
    event.preventDefault();


    const categoryProduct = $("#categoryProduct").val()
    const productname = $("#productname").val()
    const product_desc = $("#product_desc").val()
    const product_detail = $("#product_detail").val()
    const product_price = $("#product_price").val()
    const product_type = $("#product_type").val()
    const campaign = $("#campaign").val()
    const campaign_name = $("#campaign_name").val()
    const campaign_desc = $("#campaign_desc").val()
    const address= $("#address").val()
    const latitude = $("#latitude").val()
    const longitude = $("#longitude").val()


    const obj = {
        categoryProduct : {id :categoryProduct, product_categoryName:categoryProduct},
        productname : productname,
        product_desc : product_desc,
        product_detail : product_detail,
        product_price : product_price,
        product_type : product_type,
        campaign : campaign,
        campaign_name : campaign_name,
        campaign_desc : campaign_desc,
        address : address,
        latitude : latitude,
        longitude: longitude
    }
   if ( select_id != 0 ) {
        // update
        obj["id"] = select_id;
    }

    $.ajax({
        url: './product_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)

                 $("#category_product_id").val(" ")
                 $("#productname").val(" ")
                 $("#product_desc").val(" ")
                 $("#product_detail").val(" ")
                 $("#product_price").val(" ")
                 $("#product_type").val(" ")
                 $("#campaign").val(" ")
                 $("#campaign_name").val(" ")
                 $("#campaign_desc").val(" ")
                 $("#address").val(" ")
                 $("#latitude").val(" ")
                 $("#longitude").val(" ")
                 allProductResult()
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
function allProductResult(){
    $.ajax({
        url: './product_mvc/list',
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
allProductResult()
//-------------------------------------------- Product table  --------------------------------------------//

function fncReset(){
    select_id = 0;

}

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.productname}</td>
          <td>${itm.product_desc}</td>
          <td>${itm.product_detail}</td>
          <td>${itm.categoryProduct.product_categoryName}</td>

           <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <button onclick="fncProductUpdate(`+i+`)" type="button" data-bs-toggle="modal" data-bs-target="#product" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncProductDelete(`+itm.id+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               
               </div>
          </td>

        </tr>`
    }
    $("#productRow").html(html)
}



//Product delete - start
function fncProductDelete( id ) {
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./product_mvc/delete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allProductResult()
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
function fncProductUpdate(i){
    const itm = globalArr[i];
    console.log(itm.categoryProduct)

    select_id = itm.id
    $("#categoryProduct").val(itm.categoryProduct.id)
    $("#productname").val(itm.productname)
    $("#product_desc").val(itm.product_desc)
    $("#product_detail").val(itm.product_detail)
    $("#product_price").val(itm.product_price)
    $("#product_type").val(itm.product_type)
    $("#campaign").val(itm.campaign)
    $("#campaign_name").val(itm.campaign_name)
    $("#campaign_desc").val(itm.campaign_desc)
    $("#address").val(itm.address)
    $("#latitude").val(itm.latitude)
    $("#longitude").val(itm.longitude)
}

//Product update - end
//product search
$("#psearch").keyup(function () {

    const psearch = $("#psearch").val()
    if( psearch != "") {
        $.ajax({
            url: './product_mvc/search/' + psearch,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                console.log(data)
                createRow(data)
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
    else {
        allProductResult()
    }
})






