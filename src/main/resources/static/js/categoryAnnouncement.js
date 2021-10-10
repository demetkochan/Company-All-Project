$('#addNewsCategory').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const news_categoryName = $("#news_categoryName").val()

    const obj = {
        news_categoryName: news_categoryName,
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
                $("#news_categoryName").val(" ")
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

//-------------------------------------------- News Category list  --------------------------------------------//
function allNewsCategoryResult(){
    $.ajax({
        url: './category_mvc/announcementList',
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
allNewsCategoryResult()

//-------------------------------------------- News Table  --------------------------------------------//
let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.news_categoryName}</td>
           <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncNewsCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryNewsUpdateModal">Güncelle</button>
                    <button onclick="fncNewsCategoryDelete(${itm.id})" type="button" class="btn btn-outline-danger ">Sil</button>
               </div>
          </td>

        </tr>`
    }
    $("#tableRow").html(html)
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
//-------------------------------------------- News Update Function --------------------------------------------//
function fncNewsCategoryUpdate( i ) {

    const item = globalArr[i];
    select_id = item.id
    console.log(select_id)
    $("#news_categoryName").val(item.news_categoryName)
}