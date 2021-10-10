$('#contentAdd').submit((event) => {
    console.log("Tıklanıldı")
    event.preventDefault();


    const content_title = $("#content_title").val()
    const content_desc = $("#content_desc").val()
    const content_detail_desc = CKEDITOR.instances['content_detail_desc'].document.getBody().getText();
    const content_date = $("#content_date").val()
    const content_status = $("#content_status").val()


    const obj = {
        content_title: content_title,
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
                $("#content_title").val(" ")
                $("#content_desc").val(" ")
                $("#content_detail_desc").val(" ")
                allContentResult()

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


//-------------------------------------------- Content list  --------------------------------------------//
function allContentResult(){
    $.ajax({
        url: './content_mvc/list',
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
allContentResult()
//-------------------------------------------- Content table  --------------------------------------------//

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
          <td>${itm.content_title}</td>
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

function fncReset() {
    select_id = 0;

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
                    allContentResult()
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

function fncContentUpdate(i){
    const itm = globalArr[i];

    select_id = itm.id
    console.log(itm.content_detail_desc)
    $("#content_title").val(itm.content_title)
    $("#content_desc").val(itm.content_desc)
    CKEDITOR.instances['content_detail_desc'].setData(itm.content_detail_desc)
    $("#content_date").val(itm.content_date)
}







//Content update - end









//-------------------------------------------- Date Format   --------------------------------------------//

function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "." + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "." + dt.getFullYear()
    return stReturn;
}