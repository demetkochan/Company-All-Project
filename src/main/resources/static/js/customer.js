function allCustomerResult(){
    $.ajax({
        url: './customer_mvc/list',
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
allCustomerResult()


function fncReset(){
    select_id = 0;

}

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        html += `<tr>
          <th scope="row">${itm.id}</th>
          <td>${itm.cname}</td>
          <td>${itm.csurname}</td>
          <td>${itm.cemail}</td>
          <td>${itm.cphone}</td>

           <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
              
                    <button onclick="fncCustomerDelete(`+itm.id+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               
-->
               </div>
          </td>

        </tr>`
    }
    $("#customerRow").html(html)
}
function fncSearch() {
    const pageSize = $("#cPage").val()
    const asearch = $("#search").val()
    if( asearch != "") {
        $.ajax({
            url: '/customer/search/'+pageNumber+'/'+pageSize +'/'+asearch,
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
        allCustomerResult()
    }
}


function fncProductDelete( id ) {
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./customer_mvc/delete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allCustomerResult()
                    fncReset()
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