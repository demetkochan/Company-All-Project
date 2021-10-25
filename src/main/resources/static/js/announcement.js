let pageNumber = 0

let lastPageNumber = 0;
$('#announcementAdd').submit((event) => {
    console.log("Duyuru ekle Tıklanıldı")
    event.preventDefault();

    const announcementtitle = $("#announcementtitle").val()
    const announcement_detail_desc = CKEDITOR.instances['announcement_detail_desc'].document.getBody().getText();
    const announcement_date = $("#announcement_date").val()
    const announcement_status = $("#announcement_status").val()


    const obj = {
        announcementtitle: announcementtitle,
        announcement_detail_desc: announcement_detail_desc,
        announcement_status: announcement_status,
        announcement_date:announcement_date
    }

    if ( select_id != 0 ) {
        // update
        obj["id"] = select_id;
    }


    $.ajax({
        url: './announcement_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#announcementtitle").val(" ")
                $("#announcement_detail_desc").val(" ")
                $("#announcement_status").val(" ")
                $("#announcement_date").val(" ")
                allAnnouncementResult()

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

//-------------------------------------------- Announcement Add - Finish --------------------------------------------//

//-------------------------------------------- Announcement list  --------------------------------------------//

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
        allAnnouncementResult()
    }

}

allAnnouncementResult()


function allAnnouncementResult(){
    const pageSize = $("#cPage").val()
    const status = $("#cStatus").val()
    pageCount(1);
    $.ajax({
        url: './announcement_mvc/aList/'+pageNumber+'/'+pageSize,
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

//-------------------------------------------- Announcement table  --------------------------------------------//

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        let new_status=""
        if(itm.announcement_status==1){
            new_status='Aktif'
        }else if(itm.announcement_status==2){
            new_status='Pasif'
        }
        const type= new_status

        html += `<tr>
          <th scope="row">`+type+`</th>
          <td>${itm.announcementtitle}</td>
          <td>${itm.announcement_detail_desc}</td>
          <td>${dateToFormat(itm.announcement_date)}</td>

           <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <button onclick="fncAnnouncementUpdate(`+i+`)" type="button" data-bs-toggle="modal" data-bs-target="#announcementAddModal" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncAnnouncementDelete(`+itm.id+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>

               </div>
          </td>

        </tr>`
    }
    $("#announcementRow").html(html)
}
function fncSearch() {
    const pageSize = $("#cPage").val()
    const casearch = $("#search").val()
    if( casearch != "") {
        $.ajax({
            url: '/announcement_mvc/asearch/'+pageNumber+'/'+pageSize +'/'+casearch,
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
        allAnnouncementResult()
    }
}

//-------------------------------------------- Delete Announcement   --------------------------------------------//

function fncAnnouncementDelete( id ) {
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./announcement_mvc/delete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allAnnouncementResult()
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

let selectedStatus = 0;
$("#announcement_status_select").on("change",function (){
    console.log("Tıklanıldı")
    selectedStatus = (this.value)
    console.log(selectedStatus)
    if(selectedStatus ==""){
        allAnnouncementResult()
    }else{
        allAnnouncementResult1(selectedStatus)
    }

})

function allAnnouncementResult1(a){
    console.log("ajax " + a)
    $.ajax({
        url: './announcement_mvc/selectedStatus/'+a,
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






//-------------------------------------------- Update Announcement   --------------------------------------------//
let select_id=0;
function fncAnnouncementUpdate(i){
    const itm = globalArr[i];

    select_id = itm.id
    $("#announcementtitle").val(itm.announcementtitle)
    CKEDITOR.instances['announcement_detail_desc'].setData(itm.announcement_detail_desc)
    $("#announcement_date").val(itm.announcement_date)
    $("#announcement_status").val(itm.announcement_status)
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
        allAnnouncementResult()
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
        allAnnouncementResult()
    }


}

pageCount(1)
function pageCount(countStatus){
    const pageSize = $("#cPage").val()
    $.ajax({
        url: './announcement_mvc/aList/pageCount/'+pageSize+'/'+countStatus,
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







//---------------------------------------News list-------------------------------//
let PageNumber = 0
let newsResultArr=[]
let LastPageNumber = 0;

function ChangeVariables(dataNumber){
    if (dataNumber == -5) {
        dataNumber = LastPageNumber-1;

    }
    PageNumber = dataNumber;
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        fncNewsSearch();
    }
    else{
        allNewsResult()
    }

}

allNewsResult()


function allNewsResult(){
    const pageSize = $("#pPage").val()
    const status = $("#pStatus").val()
    PageCount(1);
    $.ajax({
        url: './announcement_mvc/nList/'+PageNumber+'/'+pageSize,
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
//---------------------------------------News Table-------------------------------//


function createRo(data){
    newsResultArr=data
    let html=``

    for (let i = 0; i < data.length; i++) {

        const itm=data[i]


        let news_type=""
        if(itm.news_status==1){
            news_type='Aktif'
        }else if(itm.news_status==2){
            news_type='Pasif'
        }
        const type=news_type

        html += ` <tr>
               
             <th scope="row">`+type+`</th>
            <td>${itm.news_category}</td>
            <td>${itm.newstitle}</td>
            <td>${itm.news_desc}</td>
             <td>${itm.news_detail_desc}</td>
            <td>${dateToFormat(itm.createdDate)}</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncNewsDelete(${itm.id})" type="button" class="btn btn-outline-success "><i class="far fa-trash-alt"></i></button>
               <button onclick="fncView(`+i+`)"  data-bs-toggle="modal" data-bs-target="#ViewModel" type="submit" class="btn btn-outline-primary "><i class="fas fa-eye"></i></button>
              </div>
              
            </td>
        </tr>`

    }
    $("#newsRow").html(html)

}
function fncNewsSearch() {
    const pageSize = $("#pPage").val()
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        $.ajax({
            url: '/announcement_mvc/nsearch/'+PageNumber+'/'+pageSize +'/'+cpsearch,
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
        allNewsResult()
    }
}

function fncView( i ) {
    const itm = newsResultArr[i];
    console.log(itm.news_image)
    console.log(itm.newstitle)
    $("#newstitle").text(itm.newstitle)
    $("#desc").text(itm.news_desc)
    $("#imgID").attr('src','/uploads/'+itm.news_image)

}




//------------------------------------------news Delete------------------------------------------//

function fncNewsDelete(id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./announcement_mvc/newsdelete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allNewsResult()
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
//----------------------------------------------Kategoriye göre Haber Listeleme--------------------//

let selectedCategory = 0;
$("#selectedCategory").on("change",function (){
    console.log("Tıklanıldı")
    selectedCategory = (this.value)
    console.log(selectedCategory)
    if(selectedCategory ==""){
        allNewsResult()
    }else{
        allNewsResult1(selectedCategory)
    }

})

let selectedStatusNews = 0;
$("#news_status_select").on("change",function (){
    console.log("Tıklanıldı")
    selectedStatusNews = (this.value)
    console.log(selectedStatusNews)
    if(selectedStatusNews ==""){
        allNewsResult()
    }else{
        allNewsResult2(selectedStatusNews)
    }

})


function allNewsResult1(pr){
    console.log("ajax " + pr)
    $.ajax({
        url: './announcement_mvc/selectedCategory/'+pr,
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


function allNewsResult2(n){
    console.log("ajax " + n)
    $.ajax({
        url: './announcement_mvc/selectedStatusNews/'+n,
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

function PagePlus(){
    const pageSize = $("#pPage").val()
    let plusNumber = newsResultArr.length
    let pageNumberx = PageNumber
    if( plusNumber < pageSize ){
        PageNumber = pageNumberx
    }
    else{
        PageNumber++
    }
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        fncNewsSearch();
    }
    else{
        allNewsResult()
    }


}
function PageMinus(){
    console.log('newsResultArrLength : '+newsResultArr.length)
    if(PageNumber <= 0){
        PageNumber=0
    }else {
        PageNumber--
    }
    /*    lastPage()*/
    console.log(PageNumber)
    const cpsearch = $("#psearch").val()
    if( cpsearch != "") {
        fncNewsSearch()
    }
    else{
        allNewsResult()
    }


}

PageCount(1)
function PageCount(countStatus){
    const PageSize = $("#pPage").val()
    $.ajax({
        url: './announcement_mvc/nList/pageCount/'+PageSize+'/'+countStatus,
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










//-------------------------------------------- Date Format   --------------------------------------------//

function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "." + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "." + dt.getFullYear()
    return stReturn;
}