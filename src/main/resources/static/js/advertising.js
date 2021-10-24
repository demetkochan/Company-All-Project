let pageNumber = 0

let lastPageNumber = 0;

allAdvertisingResult()
function allAdvertisingResult(){
    const pageSize = $("#cPage").val()
    const status = $("#cStatus").val()
    pageCount(1);
    $.ajax({
        url: './advertising_mvc/advList/'+pageNumber+'/'+pageSize,
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


let advResultArr=[]
function createRow(data){
    advResultArr=data
    let html=``

    for (let i = 0; i < data.length; i++) {

        const itm=data[i]


        html += ` <tr>
               
             <th scope="row">`+itm.id+`</th>
            <td>${itm.advtitle}</td>
            <td>${itm.screentime}</td>
            <td>${itm.height}</td>
            <td>${itm.width}</td>
            <td>${itm.starttime}</td>
            <td>${itm.endtime}</td>
            <td>${itm.link}</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncAdvertisingDelete(${itm.id})" type="button" class="btn btn-outline-success "><i class="far fa-trash-alt"></i></button>
               <button onclick="fncView(`+i+`)"  data-bs-toggle="modal" data-bs-target="#ViewModel" type="submit" class="btn btn-outline-primary ">Görüntüle</button>
              </div>
              
            </td>
        </tr>`

    }
    $("#advRow").html(html)

}

function fncView( i ) {
    const itm = advResultArr[i];
    console.log(itm.imageName)
    $("#adv_Title").text(itm.advtitle)
    $("#imgID").attr('src','/uploads/advertesing/'+itm.imageName)

}
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
        allAdvertisingResult()
    }

}

allAdvertisingResult()

//------------------------------Advertising Delete-----------------------------------//
function fncAdvertisingDelete(id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./advertising_mvc/advDelete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allAdvertisingResult()
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

//advertising search
function fncSearch() {
    const pageSize = $("#cPage").val()
    const casearch = $("#search").val()
    if( casearch != "") {
        $.ajax({
            url: '/advertising_mvc/search/'+pageNumber+'/'+pageSize +'/'+casearch,
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
        allAdvertisingResult()
    }
}

function pagePlus(){
    const pageSize = $("#cPage").val()
    let plusNumber = advResultArr.length
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
        allAdvertisingResult()
    }


}
function pageMinus(){
    console.log('advResultArrr Length : '+advResultArr.length)
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
        allAdvertisingResult()
    }


}

pageCount(1)
function pageCount(countStatus){
    const pageSize = $("#cPage").val()
    $.ajax({
        url: './advertising_mvc/List/pageCount/'+pageSize+'/'+countStatus,
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






