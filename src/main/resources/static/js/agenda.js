$('#agenda').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const agenda_title = $("#agenda_title").val()
    const agenda_desc = $("#agenda_desc").val()
    const agenda_date = $("#agenda_date").val()
    const agenda_hours = $("#agenda_hours").val()
    let activeChecked = $("#activeChecked").val()
    if(document.getElementById('activeChecked').checked){
        activeChecked = "true";
    }else{
        activeChecked = "false";

    }


    const obj = {
        agenda_title: agenda_title,
        agenda_desc: agenda_desc,
        agenda_date: agenda_date,
        agenda_hours: agenda_hours,
        activeChecked: activeChecked
    }

    $.ajax({
        url: './agenda/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#agenda_title").val(" ")
                $("#agenda_desc").val(" ")
                $("#agenda_date").val(" ")
                $("#agenda_hours").val(" ")
                allAgendaResult()

            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırasında bir hata oluştu!");
        }
    })
})

//-------------------------------------------- Agenda Add - Finish --------------------------------------------//

//-------------------------------------------- Agenda list - start --------------------------------------------//

function allAgendaResult(){
    $.ajax({
        url: './agenda/list',
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
allAgendaResult()

//-------------------------------------------- Agenda table  --------------------------------------------//

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        const st =itm.activeChecked == true ? 'Aktif' : 'Aktif Değil'
        html += `<tr>
            <th scope="row">${itm.aid}</th>
            <td>${itm.agenda_title}</td>
            <td>${itm.agenda_desc}</td>
            <td>${dateToFormat(itm.agenda_date)}</td>
            <td>${itm.agenda_hours}</td>
            <td>`+st+`</td>
            <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <button onclick="fncAgendaDelete(${itm.aid})" type="button" class="btn btn-outline-danger ">Sil</button>
               </div>
          </td>

          </tr>`
    }
    $("#agendaRaw").html(html)
}
//-------------------------------------------- Agenda Delete --------------------------------------------//

function fncAgendaDelete(aid){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./agenda/delete/"+aid,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allAgendaResult()
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

//-------------------------------------------- Date Format  --------------------------------------------//

function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "-" + dt.getFullYear()
    return stReturn;
}
