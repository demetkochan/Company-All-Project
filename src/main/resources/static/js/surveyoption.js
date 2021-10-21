$('#addSurveyOptions').submit((event) => {
    event.preventDefault();

    const survey_id = $("#survey_id").val()
    const optiontitle = $("#optiontitle").val()

    const obj = {
        survey_id:survey_id,
        optiontitle:optiontitle

    }

    $.ajax({
        url: './surveydetail_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#survey_id").val(" ")
                $("#optiontitle").val(" ")

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

//---------------------------------------------------create table---------------------------------------//

let selectedSurvey = 0;
$("#surveys").on("change",function (){
    console.log("Tıklanıldı")
    selectedSurvey = (this.value)
    console.log(selectedSurvey)
    surveyOption(selectedSurvey)
})

let globalArr=[]
function surveyOption(id) {

    $.ajax({
        url: './surveydetail_mvc/surveyOption/'+id,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("get metodu çalıştı")
            const obj = JSON.parse(data)
            let html = ``
            for (let i = 0; i < obj.length; i++) {
                globalArr = obj
                const itm = obj[i]
                html +=` <tr>
            <td>${itm.id}</td>
            <td>${itm.optiontitle}</td>
            
         
            <td class="text-right" >
               <div class="btn-group" role="group">
                  <button onclick="fncView(`+i+`)"  data-bs-toggle="modal" data-bs-target="#ViewModel" type="submit" class="btn btn-outline-primary "><i class="fas fa-eye"></i></button>
                   <button onclick="fncImageDelete(${itm.p_Id})" type="button" class="btn btn-outline-success "><i class="far fa-trash-alt"></i></button>
              </div>
            </td>
          </tr>`
            }
            $("#surveyOptionRow").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}
surveyOption(selectedSurvey);

