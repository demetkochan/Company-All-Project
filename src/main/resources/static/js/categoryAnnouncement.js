$('#addNewsCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const news_categoryName = $("#news_categoryName").val()

    const obj = {
        news_categoryName: news_categoryName,
    }
    if ( select_id != 0 ) {
        // update
        obj["id"] = select_id;
    }

    $.ajax({
        url: './category_mvc/announcementAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#news_categoryName").val(" ")
                allNewsCategoryResult()
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
let selectedSize = 0;
$("#size").on("change",function (){
    console.log("Tıklanıldı")
    selectedSize = (this.value)
    console.log(selectedSize)
    if(selectedSize==1){
        selectedSize=5
    }else if(selectedSize==2){
        selectedSize=10
    }else if(selectedSize==3){
        selectedSize=15
    }
    const size= selectedSize

    let selectedPage=0;
    $("#page").on("change",function (){
        console.log("Tıklanıldı")
        selectedPage = (this.value)
        if(selectedPage ==1){
            selectedPage =0
        }else if(selectedPage ==2){
            selectedPage =1
        }else if(selectedPage ==3){
            selectedPage =2
        }
        const page= selectedPage

        allNewsCategoryResult(page,size)
    })




})

function allNewsCategoryResult(page,size){
    $.ajax({
        url: './category_mvc/newsList/'+page+'/'+size,
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
allNewsCategoryResult()

//-------------------------------------------- News Table  --------------------------------------------//
let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.news_categoryName}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncNewsCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryNewsAddModal">Güncelle</button>
                    <button onclick="fncNewsCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableRow").html(html)
}
//-------------------------------------------- News Delete Function --------------------------------------------//
function fncNewsCategoryDelete(id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./category_mvc/newsDelete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allNewsCategoryResult()
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
let select_id=0;
function fncNewsCategoryUpdate( i ) {
    const item = globalArr[i];
    select_id = item.id
    console.log(select_id)
    $("#news_categoryName").val(item.news_categoryName)
}

//-------------------------------------------------Product------------------------------------------------------//

$('#addProductCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const product_categoryName = $("#product_categoryName").val()

    const obj = {
        product_categoryName: product_categoryName,
    }
    if ( select_pid != 0 ) {
        // update
        obj["id"] = select_pid;
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
                allProductCategoryResult()
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
            createRo(data)
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })
}
allProductCategoryResult()

//-------------------------------------------- Product Table  --------------------------------------------//
let globalAr = []
function createRo(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalAr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.product_categoryName}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncProductCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryProductAddModal">Güncelle</button>
                    <button onclick="fncProductCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableProductRow").html(html)
}
//-------------------------------------------- Product Delete Function --------------------------------------------//
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
let select_pid=0;
function fncProductCategoryUpdate( i ) {

    const item = globalAr[i];
    select_pid = item.id
    console.log(select_pid)
    $("#product_categoryName").val(item.product_categoryName)
}
//--------------------------Gallery-------------------------------------------------//
$('#addGalleryCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const gallery_categoryName = $("#gallery_categoryName").val()

    const obj = {
        gallery_categoryName: gallery_categoryName,
    }
    if ( select_gid != 0 ) {
        // update
        obj["id"] = select_gid;
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
                $("#gallery_categoryName").val(" ")
                allGalleryCategoryResult()
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
//-----------------------------------------Gallery Category Add Finish--------------------------------------------------------//

//-------------------------------------------- Gallery Category list  --------------------------------------------//
function allGalleryCategoryResult(){
    $.ajax({
        url: './category_mvc/galleryList',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            cRow(data)
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })
}
allGalleryCategoryResult()

//-------------------------------------------- Gallery Table  --------------------------------------------//
let globalarr = []
function cRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalarr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.gallery_categoryName}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncGalleryCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryGalleryAddModal">Güncelle</button>
                    <button onclick="fncGalleryCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableGalleryRow").html(html)
}
//-------------------------------------------- Gallery Delete Function --------------------------------------------//
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
//-------------------------------------------- Gallery Update Function --------------------------------------------//
let select_gid=0;
function fncGalleryCategoryUpdate( i ) {
    const item = globalarr[i];
    select_gid = item.id
    console.log(select_gid)
    $("#gallery_categoryName").val(item.gallery_categoryName)
}