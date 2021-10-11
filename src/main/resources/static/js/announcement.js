$('#announcementAdd').submit((event) => {
    console.log("Duyuru ekle Tıklanıldı")
    event.preventDefault();

    const announcement_title = $("#announcement_title").val()
    const announcement_detail_desc = CKEDITOR.instances['announcement_detail_desc'].document.getBody().getText();
    const announcement_date = $("#announcement_date").val()
    const announcement_status = $("#announcement_status").val()


    const obj = {
        announcement_title: announcement_title,
        announcement_detail_desc: announcement_detail_desc,
        announcement_status: announcement_status,
        announcement_date:announcement_date
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
                $("#announcement_title").val(" ")
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
          <td>${itm.announcement_title}</td>
          <td>${itm.announcement_detail_desc}</td>
          <td>${dateToFormat(itm.announcement_date)}</td>

           <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <button  type="button" data-bs-toggle="modal" data-bs-target="#contentAddModal" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
                    <button  type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>

               </div>
          </td>

        </tr>`
    }
    $("#announcementRow").html(html)
}


//-------------------------------------------- Date Format   --------------------------------------------//

function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "." + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "." + dt.getFullYear()
    return stReturn;
}