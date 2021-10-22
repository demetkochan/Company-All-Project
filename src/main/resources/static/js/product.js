let pageNumber = 0
let globalArr = []
let lastPageNumber = 0;
$('#productAdd').submit((event) => {
    console.log("Tıklanıldı")
    event.preventDefault();


    const categoryProducts = $("#categoryProducts").val()
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
    const productlike = "0"

    let categories = []

    for(let i= 0 ; i<categoryProducts.length; i++){
        const categoryObj = {
            id: categoryProducts[i]
        }
        categories.push(categoryObj)
    }
    const obj = {
        categoryProducts : categories,
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
        longitude: longitude,
        productlike:productlike
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

                 $("#categoryProducts").val(" ")
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

function changeVariables(dataNumber){
    if (dataNumber == -5) {
        dataNumber = lastPageNumber-1;

    }
    pageNumber = dataNumber;
    const casearch = $("#search").val()
    if( casearch != "") {
        fncSearch();
    }
    else{
        allProductResult()
    }

}

allProductResult()


function allProductResult(){
    const pageSize = $("#cPage").val()
    const status = $("#cStatus").val()
    pageCount(1);
    $.ajax({
        url: './product_mvc/productList/'+pageNumber+'/'+pageSize,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRow(data)
        },
        error: function (err){
            console.log(err)
        }
    })
}

function fncReset(){
    select_id = 0;

}


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
          <td>${itm.product_price}</td>

           <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic outlined example">
                    <button onclick="fncProductUpdate(`+i+`)" type="button" data-bs-toggle="modal" data-bs-target="#product" class="btn btn-outline-success"><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncProductDelete(`+itm.id+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
                     <button onclick="fncProductLike(`+i+`)"class="btn btn-outline-primary" id="like-btn"><i class="fas fa-heart"></i></button>
                     <button onclick="fncProductDislike(`+i+`)"class="btn btn-outline-secondary" id="dislike-btn"><i class="fas fa-heart-broken"></i></button>

               </div>
          </td>


        </tr>`
    }
    $("#productRow").html(html)
}
function fncSearch() {
    const pageSize = $("#cPage").val()
    const casearch = $("#search").val()
    if( casearch != "") {
        $.ajax({
            url: '/product_mvc/search/'+pageNumber+'/'+pageSize +'/'+casearch,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                console.log(data)
                pageCount(2)
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


    select_id = itm.id

    $('#categoryProducts').val(itm.categoryProducts[i].id)
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

let selected_id=0;
function fncProductLike(i){
    const itm = globalArr[i];

    selected_id = itm.id
    fncLike(selected_id)
    console.log(selected_id)

}

let disselected_id = 0;
function fncProductDislike(i){
    const itm = globalArr[i];

    disselected_id = itm.id
    fncDislike(disselected_id)
    console.log(disselected_id)

}


function fncDislike(disselected_id){

    let answer = confirm("Ürünü beğenmediniz mi?  ")
    if(answer) {
        $.ajax({
            url: './product_mvc/dislike/' + disselected_id,
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                allProductResult()
                if (data) {
                    console.log(data)


                } else {
                    console.log("Veri dönmedi.")
                }
            },
            error: function (err) {
                console.log(err)
                alert("İşlem işlemi sırısında bir hata oluştu!");
            }
        })
    }



}



function fncLike(selected_id){

    let answer = confirm("Ürünü beğenmek  istediğinize emin misiniz?")
    if(answer) {
        $.ajax({
            url: './product_mvc/like/' + selected_id,
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                allProductResult()
                if (data) {
                    console.log(data)


                } else {
                    console.log("Veri dönmedi.")
                }
            },
            error: function (err) {
                console.log(err)
                alert("İşlem işlemi sırısında bir hata oluştu!");
            }
        })
    }



}


function pagePlus(){
    const pageSize = $("#cPage").val()
    let plusNumber = globalArr.length
    let pageNumberx = pageNumber
    if( plusNumber < pageSize ){
        pageNumber = pageNumberx
    }
    else{
        pageNumber++
    }
    const casearch = $("#search").val()
    if( casearch != "") {
        fncSearch();
    }
    else{
        allProductResult()
    }


}
function pageMinus(){
    console.log('GlobalArr Length : '+globalArr.length)
    if(pageNumber <= 0){
        pageNumber=0
    }else {
        pageNumber--
    }
    /*    lastPage()*/
    console.log(pageNumber)
    const casearch = $("#search").val()
    if( casearch != "") {
        fncSearch();
    }
    else{
        allProductResult()
    }


}

pageCount(1)
function pageCount(countStatus){
    const pageSize = $("#cPage").val()
    $.ajax({
        url: './product_mvc/List/pageCount/'+pageSize+'/'+countStatus,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            $("#totalPageNumber").text(pageNumber+1 + '/' + data)
            lastPageNumber = data;
        },
        error: function (err){
            console.log(err)
        }
    })
}






