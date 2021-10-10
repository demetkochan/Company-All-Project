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
let selectedSize = 0;
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

function allNewsCategoryResult(page,size){
    $.ajax({
        url: './category_mvc/newsList/'+page+'/'+size,
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
                    <button onclick="fncNewsCategoryUpdate(`+i+`)" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#categoryNewsAddModal">Güncelle</button>
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