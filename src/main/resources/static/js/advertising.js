function allAdvertisingResult(){
    $.ajax({
        url: './advertising_mvc/advList',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',

        success: function (data) {
            console.log(data)
            createRow(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
allAdvertisingResult()

let advResultArr=[]
function createRow(data){
    advResultArr=data
    let html=``

    for (let i = 0; i < data.length; i++) {

        const itm=data[i]


        html += ` <tr>
               
             <th scope="row">`+itm.id+`</th>
            <td>${itm.advTitle}</td>
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
    $("#adv_Title").text(itm.advTitle)
    $("#imgID").attr('src','/uploads/advertesing/'+itm.imageName)

}

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