let pageNumber = 0

let lastPageNumber = 0;
$('#contentAdd').submit((event) => {
    console.log("Tıklanıldı")
    event.preventDefault();


    const contenttitle = $("#contenttitle").val()
    const content_desc = $("#content_desc").val()
    const content_detail_desc = CKEDITOR.instances['content_detail_desc'].document.getBody().getText();
    const content_date = $("#content_date").val()
    const content_status = $("#content_status").val()


    const obj = {
        contenttitle: contenttitle,
        content_desc: content_desc,
        content_detail_desc: content_detail_desc,
        content_date: content_date,
        content_status: content_status
    }
    if ( select_id != 0 ) {
        // update
        obj["id"] = select_id;
    }

    $.ajax({
        url: './content_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#contenttitle").val(" ")
                $("#content_desc").val(" ")
                $("#content_detail_desc").val(" ")
                $("#content_date").val(" ")
                $("#content_status").val(" ")
                allContent()
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

//-------------------------------------------- Content Add - Finish --------------------------------------------//
function changeVariables(dataNumber){
    if (dataNumber == -5) {
        dataNumber = lastPageNumber-1;

    }
    pageNumber = dataNumber;
    const asearch = $("#search").val()
    if( asearch != "") {
        fncSearch();
    }
    else{
        allContent()
    }
}

allContent()

function allContent(){
    const pageSize = $("#cPage").val()
    const status = $("#cStatus").val()
    pageCount(1);
    $.ajax({
        url: './content_mvc/contentList/'+pageNumber+'/'+pageSize,
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
function fncSearch() {
    const pageSize = $("#cPage").val()
    const asearch = $("#search").val()
    if( asearch != "") {
        $.ajax({
            url: '/content_mvc/search/'+pageNumber+'/'+pageSize +'/'+asearch,
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
        allContent()
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

    const asearch = $("#search").val()
    if( asearch != "") {
        fncSearch();
    }
    else{
        allContent()
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
    const asearch = $("#search").val()
    if( asearch != "") {
        fncSearch();
    }
    else{
        allContent()
    }

}

pageCount(1)
function pageCount(countStatus){
    const pageSize = $("#cPage").val()
    $.ajax({
        url: './customer_mvc/customerList/pageCount/'+pageSize+'/'+countStatus,
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



//-------------------------------------------- Content list  --------------------------------------------//

//-------------------------------------------- Content table  --------------------------------------------//

function fncReset(){
    select_id = 0;

}

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        let new_status=""
        if(itm.content_status==1){
            new_status='Aktif'
        }else if(itm.content_status==2){
            new_status='Pasif'
        }
        const type= new_status

        html += `<tr>
          <th scope="row">`+type+`</th>
          <td>${itm.contenttitle}</td>
          <td>${itm.content_desc}</td>
          <td>${itm.content_detail_desc}</td>
          <td>${dateToFormat(itm.content_date)}</td>

           <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <button onclick="fncContentUpdate(`+i+`)" type="button" data-bs-toggle="modal" data-bs-target="#contentAddModal" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncContentDelete(`+itm.id+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>

               </div>
          </td>

        </tr>`
    }
    $("#contentRow").html(html)
}



// content delete - start
function fncContentDelete( id ) {
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./content_mvc/delete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allContent()
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

// content delete - end


//Content update - start
let select_id=0;
function fncContentUpdate(i){
    const itm = globalArr[i];

    select_id = itm.id
    console.log(itm.content_detail_desc)
    $("#contenttitle").val(itm.contenttitle)
    $("#content_desc").val(itm.content_desc)
    CKEDITOR.instances['content_detail_desc'].setData(itm.content_detail_desc)
    $("#content_date").val(itm.content_date)
}

let selectedProcess = 0;
$("#select_process").on("change",function (){
    console.log("Tıklanıldı")
    selectedProcess = (this.value)
    console.log(selectedProcess)
    if(selectedProcess ==""){
        allContent()
    }else{
        allContentResult1(selectedProcess)
    }



})


function allContentResult1(pr){
    console.log("ajax " + pr)
    $.ajax({
        url: './content_mvc/selectProcess/'+pr,
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







//Content update - end











//-------------------------------------------- Date Format   --------------------------------------------//

function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "." + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "." + dt.getFullYear()
    return stReturn;
}