//-------------------------------------------- News Category list  --------------------------------------------//
let pageNumber = 0
let globalArr = []
let lastPageNumber = 0;
$('#addNewsCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const newscategoryname = $("#newscategoryname").val()

    const obj = {
        newscategoryname: newscategoryname,
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
                $("#newscategoryname").val(" ")
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
       allNewsCategoryResult()
    }

}

allNewsCategoryResult()

// Content List - Start
function allNewsCategoryResult(){
    const pageSize = $("#cPage").val()
    const status = $("#cStatus").val()
    pageCount(1);
    $.ajax({
        url: './category_mvc/newsList/'+pageNumber+'/'+pageSize,
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
//------------------------------------------- News Table  --------------------------------------------//

function createRow(data){
    globalArr = data
    let html = ``
    for (let i = 0; i < data.length; i++) {

        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.newscategoryname}</td>
           <td class="text-right" >
               <div class="btn-group" aria-label="Basic outlined example" role="group">
                    <button onclick="fncNewsCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryNewsAddModal"><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncNewsCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               </div>
          </td>

        </tr>`
    }
    $("#tableRow").html(html)
}

function fncSearch() {
    const pageSize = $("#cPage").val()
    const casearch = $("#search").val()
    if( casearch != "") {
        $.ajax({
            url: '/category_mvc/search/'+pageNumber+'/'+pageSize +'/'+casearch,
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
        allNewsCategoryResult()
    }
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
        allNewsCategoryResult()
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
        allNewsCategoryResult()
    }


}

pageCount(1)
function pageCount(countStatus){
    const pageSize = $("#cPage").val()
    $.ajax({
        url: './category_mvc/List/pageCount/'+pageSize+'/'+countStatus,
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

//-------------------------------------------- News Update Function --------------------------------------------//
let select_id=0;
function fncNewsCategoryUpdate( i ) {
    const item = globalArr[i];
    select_id = item.id
    console.log(select_id)
    $("#newscategoryname").val(item.newscategoryname)
}

//-------------------------------------------------Product------------------------------------------------------//
let PageNumber = 0
let globalAr = []
let LastPageNumber = 0;

$('#addProductCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const productcategoryname = $("#productcategoryname").val()

    const obj = {
        productcategoryname: productcategoryname,
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
                $("#productcategoryname").val(" ")
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


function ChangeVariables(dataNumber){
    if (dataNumber == -5) {
        dataNumber = LastPageNumber-1;

    }
    PageNumber = dataNumber;
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        fncProductSearch();
    }
    else{
        allProductCategoryResult()
    }

}

allProductCategoryResult()


function allProductCategoryResult(){
    const pageSize = $("#pPage").val()
    const status = $("#pStatus").val()
    PageCount(1);
    $.ajax({
        url: './category_mvc/productList/'+PageNumber+'/'+pageSize,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRo(data)
        },
        error: function (err){
            console.log(err)
        }
    })
}
//-------------------------------------------- Product Table  --------------------------------------------//

function createRo(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalAr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.productcategoryname}</td>
           <td class="text-right" >
               <div class="btn-group" aria-label="Basic outlined example" role="group">
                    <button onclick="fncProductCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryProductAddModal"><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncProductCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               </div>
          </td>

        </tr>`
    }
    $("#tableProductRow").html(html)
}
//-------------------------------------------- Product Delete Function --------------------------------------------//
function fncProductSearch() {
    const pageSize = $("#pPage").val()
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        $.ajax({
            url: '/category_mvc/productSearch/'+PageNumber+'/'+pageSize +'/'+cpsearch,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                console.log(data)
                PageCount(2)
                createRo(data)
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
    else {
        allProductCategoryResult()
    }
}


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
    $("#productcategoryname").val(item.productcategoryname)
}

function PagePlus(){
    const pageSize = $("#pPage").val()
    let plusNumber = globalAr.length
    let pageNumberx = PageNumber
    if( plusNumber < pageSize ){
        PageNumber = pageNumberx
    }
    else{
        PageNumber++
    }
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        fncProductSearch();
    }
    else{
        allProductCategoryResult()
    }


}
function PageMinus(){
    console.log('GlobalAr Length : '+globalAr.length)
    if(PageNumber <= 0){
        PageNumber=0
    }else {
        PageNumber--
    }
    /*    lastPage()*/
    console.log(PageNumber)
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        fncProductSearch();
    }
    else{
        allProductCategoryResult()
    }


}

PageCount(1)
function PageCount(countStatus){
    const PageSize = $("#pPage").val()
    $.ajax({
        url: './category_mvc/productList/pageCount/'+PageSize+'/'+countStatus,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            $("#ptotalPageNumber").text(PageNumber+1 + '/' + data)
            LastPageNumber = data;
        },
        error: function (err){
            console.log(err)
        }
    })
}







//--------------------------Gallery-------------------------------------------------//
let globlarr = []
let PageNumbe = 0
let LastPageNumbe = 0;

$('#addGalleryCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const gallerycategoryname = $("#gallerycategoryname").val()

    const obj = {
        gallerycategoryname: gallerycategoryname,
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
                $("#gallerycategoryname").val(" ")
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


function ChangeVariable(dataNumber){
    if (dataNumber == -5) {
        dataNumber = LastPageNumbe-1;

    }
    PageNumbe = dataNumber;
    const cgsearch = $("#gsearch").val()
    if( cgsearch != "") {
        fncGallerySearch();
    }
    else{
        allGalleryCategoryResult()
    }

}
allGalleryCategoryResult()


function  allGalleryCategoryResult(){
    const pageSize = $("#gPage").val()
    const status = $("#gStatus").val()
    PageCoun(1);
    $.ajax({
        url: './category_mvc/galleryList/'+PageNumbe+'/'+pageSize,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            cRow(data)
        },
        error: function (err){
            console.log(err)
        }
    })
}




function cRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globlarr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.gallerycategoryname}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncGalleryCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryGalleryAddModal"><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncGalleryCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               </div>
          </td>

        </tr>`
    }
    $("#tableGalleryRow").html(html)
}

function fncGallerySearch() {
    const pageSize = $("#gPage").val()
    const cgsearch = $("#gsearch").val()
    if( cgsearch != "") {
        $.ajax({
            url: '/category_mvc/gallerySearch/'+PageNumbe+'/'+pageSize +'/'+cgsearch,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                console.log(data)
                PageCoun(2)
                cRow(data)
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
    else {
        allGalleryCategoryResult()
    }
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
    const item = globlarr[i];
    select_gid = item.id
    console.log(select_gid)
    $("#gallerycategoryname").val(item.gallerycategoryname)

}


function PagePlu(){
    const pageSize = $("#gPage").val()
    let plusNumber = globlarr.length
    let pageNumberx = PageNumbe
    if( plusNumber < pageSize ){
        PageNumbe = pageNumberx
    }
    else{
        PageNumbe++
    }
    const cgsearch = $("#gsearch").val()
    if( cgsearch != "") {
        fncGallerySearch();
    }
    else{
        allGalleryCategoryResult()
    }


}
function PageMinu(){
    console.log('globlarr Length : '+globlarr.length)
    if(PageNumbe <= 0){
        PageNumbe=0
    }else {
        PageNumbe--
    }
    /*    lastPage()*/
    console.log(PageNumbe)
    const cgsearch = $("#gsearch").val()
    if( cgsearch != "") {
        fncGallerySearch();
    }
    else{
        allGalleryCategoryResult()
    }


}

PageCoun(1)
function PageCoun(countStatus){
    const PageSize = $("#gPage").val()
    $.ajax({
        url: './category_mvc/galleryList/pageCount/'+PageSize+'/'+countStatus,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            $("#gtotalPageNumber").text(PageNumbe+1 + '/' + data)
            LastPageNumbe = data;
        },
        error: function (err){
            console.log(err)
        }
    })
}





