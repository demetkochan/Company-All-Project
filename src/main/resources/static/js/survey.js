let pageNumber = 0
let lastPageNumber = 0;
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
        allSurveyResult()
    }

}

allSurveyResult()

function allSurveyResult(){
    const pageSize = $("#cPage").val()
    const status = $("#cStatus").val()
    pageCount(1);
    $.ajax({
        url: './survey_mvc/surveyList/'+pageNumber+'/'+pageSize,
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
                    <button onclick="fncSurveyUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#surveyAddModal"><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncSurveyDelete(${itm.id})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
                     <a href="surveydetail_mvc" type="button" class="btn btn-outline-danger" ><i class="fas fa-plus-circle"></i></a>
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
function fncSearch() {
    const pageSize = $("#cPage").val()
    const casearch = $("#search").val()
    if( casearch != "") {
        $.ajax({
            url: '/survey_mvc/search/'+pageNumber+'/'+pageSize +'/'+casearch,
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
        allSurveyResult()
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
        allSurveyResult()
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
        allSurveyResult()
    }


}

pageCount(1)
function pageCount(countStatus){
    const pageSize = $("#cPage").val()
    $.ajax({
        url: './survey_mvc/List/pageCount/'+pageSize+'/'+countStatus,
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




