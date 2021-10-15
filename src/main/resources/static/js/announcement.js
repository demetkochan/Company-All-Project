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


function allAnnouncementResult(){
    $.ajax({
        url: './announcement_mvc/list',
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
allAnnouncementResult()

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

//---------------------------------------News list-------------------------------//


function allNewsResult(){
    $.ajax({
        url: './announcement_mvc/newslist',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',

        success: function (data) {
            console.log(data)
            createRo(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
allNewsResult()

//---------------------------------------News Table-------------------------------//

let newsResultArr=[]
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
               <button onclick="fncView(`+i+`)"  data-bs-toggle="modal" data-bs-target="#ViewModel" type="submit" class="btn btn-outline-primary ">Görüntüle</button>
              </div>
              
            </td>
        </tr>`

    }
    $("#newsRow").html(html)

}

function fncView( i ) {
    const itm = newsResultArr[i];
    console.log(itm.news_image)
    console.log(itm.newstitle)
    $("#news_titl").text(itm.newstitle)
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




//duyuru search
$("#dsearch").keyup(function () {

    const dsearch = $("#dsearch").val()
    if( dsearch != "") {
        $.ajax({
            url: './announcement_mvc/searchAnnouncement/' + dsearch,
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
        allAnnouncementResult()
    }
})

//news search
$("#nsearch").keyup(function () {

    const nsearch = $("#nsearch").val()
    if( nsearch != "") {
        $.ajax({
            url: './announcement_mvc/searchNews/' + nsearch,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                console.log(data)
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
})


//-------------------------------------------- Date Format   --------------------------------------------//

function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "." + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "." + dt.getFullYear()
    return stReturn;
}