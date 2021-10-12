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
//-------------------------------------------- Product table  --------------------------------------------//

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
                    <button onclick="fncCustomerUpdate(`+i+`)" type="button" data-bs-toggle="modal" data-bs-target="#product" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
                    <button onclick="fncCustomerDelete(`+itm.id+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               
-->
               </div>
          </td>

        </tr>`
    }
    $("#customerRow").html(html)
}