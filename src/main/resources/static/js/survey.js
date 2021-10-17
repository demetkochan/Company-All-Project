$('#addSurvey').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const surveytitle = $("#surveytitle").val()

    const obj = {
      surveytitle: surveytitle,
    }
    if ( select_id != 0 ) {
        // update
        obj["id"] = select_id;
    }

    $.ajax({
        url: './survey_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#surveytitle").val(" ")
                allSurveyResult()
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
//-----------------------------------------Survey Add Finish--------------------------------------------------------//

//-------------------------------------------- Survey list  --------------------------------------------//
/*let selectedSize = 0;
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
*/
function allSurveyResult(){
    $.ajax({
        url: './survey_mvc/surveyList',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRow(data)
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırasında bir hata oluştu!");
        }
    })
}
allSurveyResult()

//-------------------------------------------- survey Table  --------------------------------------------//
let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.surveytitle}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncSurveyUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#surveyAddModal">Güncelle</button>
                    <button onclick="fncSurveyDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
                     <button onclick="fncOptionAdd(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#surveyOptionAddModal">Seçenek Ekle</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableRow").html(html)
}
//-------------------------------------------- Survey Delete Function --------------------------------------------//
function fncSurveyDelete(id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./survey_mvc/surveyDelete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allSurveyResult()
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
//-------------------------------------------- Survey Update Function --------------------------------------------//
let select_id=0;
function fncSurveyUpdate( i ) {
    const item = globalArr[i];
    select_id = item.id
    console.log(select_id)
    $("#surveytitle").val(item.surveytitle)
}
//----------------------------------------Survey Option Add-----------------------------------------------------//
let select=0;
function fncOptionAdd(i){
    const item = globalAr[i];
    select = item.id
    console.log(select)
  //  allSurveyOptionResult(select)
//    fncsurveyOption(select)



}



function fncsurveyOption() {
$.ajax({
    url: './survey_mvc/optAdd',
    type: 'POST',
    data: JSON.stringify(obj),
    dataType: 'json',
    contentType : 'application/json; charset=utf-8',
    success: function (data) {
        if (data) {
            console.log(data)
            $("#optiontitle").val(" ")
            allSurveyOptionResult(select)
        } else {
            console.log("Veri dönmedi.")
        }
    },
    error: function (err) {
        console.log(err)
        alert("İşlem işlemi sırasında bir hata oluştu!");
    }
})
}
/*
function allSurveyOptionResult( select ){
    $.ajax({
        url: './survey_mvc/optionList/'+select,
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRow(data)
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırasında bir hata oluştu!");
        }
    })
}
allSurveyOptionResult(select)

//-------------------------------------------- survey Table  --------------------------------------------//
let globalAr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalAr = data
        const itm = data[i]
        html += `
            <h6>${itm.surveytitle}</h6>
            <hr/>
            <tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.optiontitle}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncSurveyOptionDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
                    
               </div>
          </td>

        </tr>`
    }
    $("#tableOptionRow").html(html)
}*/