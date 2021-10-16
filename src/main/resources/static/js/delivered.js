function allDeliveredResult(){
    $.ajax({
        url: './delivered_mvc/statuslist',
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
allDeliveredResult()

//----------------------------------- Order table ----------------------------------//

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        html += `<tr>
            <th scope="row">${itm.cname} ${itm.csurname} </th>  
             <td>${itm.cphone}</td>
             <td>${itm.cemail}</td>
            <td>${itm.productname}</td>
            <td>${itm.order_count}</td>
            <td>${dateToFormat(itm.order_date)}</td>
            <td>${itm.total} ₺ </td> 
            <td class="text-right" >
               <div class="btn-group" role="group" aria-label="Basic mixed styles example">
               
                    <button onclick="fncDeliveredUpdate(`+i+`)" type="button"  class="btn btn-outline-primary "><i class="fas fa-truck"></i>
</button>
                    <button onclick="fncDeliveredDelete(`+itm.oid+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               </div>
          </td>
            
        
          </tr>`
    }
    $("#deliveredRow").html(html)
}

//---------------------------------------Delivered Update-------------------------//
let select_id=0;
function fncDeliveredUpdate(i){
    const itm = globalArr[i];

    select_id = itm.oid
    fncUpdate(select_id)
    console.log(select_id)

}

function fncUpdate(select_id){

    let answer = confirm("Ürün hazırlanıyor durumuna geçiyor, istediğinize emin misiniz?")

    if(answer) {
        $.ajax({
            url: './delivered_mvc/update/' + select_id,
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                allDeliveredResult()
                if (data) {
                    console.log(data)


                } else {
                    console.log("Veri dönmedi.")
                }
            },
            error: function (err) {
                console.log(err)
                alert("İşlem işlemi sırısında bir hata oluştu!");
            }
        })
    }



}







function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "-" + dt.getFullYear()
    return stReturn;
}
