$('#orderAdd').submit((event) => {
    event.preventDefault();

    const order_customer = $("#order_customer").val()
    const order_product = $("#order_product").val()
    const order_count = $("#order_count").val()
    const order_date = $("#order_date").val()
    const customer_address = $("#customer_address").val()
    const order_status = "1"

    if ( select_id != 0 ) {
        // update
        obj["id"] = select_id;
    }


    const obj = {
        order_customer: {id:order_customer,cname:order_customer,cemail:order_customer},
        order_product: order_product,
        order_count: order_count,
        order_date: order_date,
        customer_address: customer_address,
        order_status:order_status
    }


    $.ajax({
        url: './order_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#order_customer").val(" ")
                $("#order_product").val(" ")
                $("#order_count").val(" ")
                $("#order_date").val(" ")
                $("#customer_address").val(" ")
                $("#order_status").val(" ")
                allOrderResult()

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


//--------------------------------Order listeleme----------------------------------//

function allOrderResult(){
    $.ajax({
        url: './order_mvc/list',
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
allOrderResult()

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
               
                    <button onclick="fncOrderUpdate(`+i+`)" type="button"  class="btn btn-outline-primary "><i class="fas fa-truck"></i>
</button>
                    <button onclick="fncOrderDelete(`+itm.oid+`)" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               </div>
          </td>
            
        
          </tr>`
    }
    $("#orderRow").html(html)
}

//---------------------------------Delete Order--------------------------------------//

//Order delete - start
function fncOrderDelete( id ) {
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){
        console.log(id)

        $.ajax({
            url:"./order_mvc/delete/"+id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allOrderResult()

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

//Order delete - end

//----------------------------------------OrderUpdate-------------------------------------------//
let select_id=0;
function fncOrderUpdate(i){
    const itm = globalArr[i];

    select_id = itm.oid
    console.log(select_id)
    $("#order_status").change()



}





function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "-" + dt.getFullYear()
    return stReturn;
}


