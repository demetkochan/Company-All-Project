$('#retailAdd').submit((event) => {
    console.log("Sales Tıklandı.")
    event.preventDefault();

    const retail_pro_search = $("#retail_pro_search").val()
    const sales_product_amount = $("#sales_product_amount").val()
    const sales_note = $("#sales_note").val()


    const obj = {
        retail_pro_search:retail_pro_search,
        sales_product_amount:sales_product_amount,
        sales_note:sales_note,


    }

    $.ajax({
        url: './sales/boxAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#retail_pro_search").val(" ")
                $("#sales_product_amount").val(" ")
                $("#sales_note").val(" ")


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

let selectedProduct = 0;
$("#retail_pro_search").on("change",function (){
    console.log("Tıklanıldı")
    selectedProduct = (this.value)



    let amount=0;
    $("#sales_product_amount").on("change",function (){
        console.log("Tıklanıldı")
        amount = (this.value)
        console.log(selectedProduct)
        console.log(amount)
        saleTotal(selectedProduct,amount)

    })

})


function saleTotal(vid,amount){

    $.ajax({
        url: './sales/saleTotal/'+vid+'/'+amount,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("saleTotal çalıştı")
            const obj = JSON.parse(data)
            // productName
            var html = ``;
            obj.forEach((item,index)=>{
                html+=`<label>ÖDENECEK TUTAR</label>  <input id="saletotal" class="form-control" type="number" value="${item.total}">
                       <label>ÜRÜN ADI</label> : <input type="text" class="form-control" id="product_name" value="${item.product_Name}">`
            })
            $("#saleAdd").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}


//-------------------------------------------- Ödeme ekleme  --------------------------------------------//

$('#retailSaleAdd').submit((event) => {
    console.log("Ödeme Tıklandı.")
    event.preventDefault();

    const saletotal = $("#saletotal").val()
    const product_name = $("#product_name").val()
    const sale_process = $("#sale_process").val()
    const sale_date = $("#sale_date").val()


    const obj = {
        saletotal:saletotal,
        product_name:product_name,
        sale_process:sale_process,
        sale_date:sale_date
    }

    $.ajax({
        url: './sales/retailAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#saletotal").val(" ")
                $("#product_name").val(" ")
                $("#sale_process").val(" ")
                $("#sale_date").val(" ")


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


//-------------------------------------------- Kayıtlı Müşteri sepet ekleme  --------------------------------------------//

$('#customerOrder').submit((event) => {
    console.log("Customer Sepet Tıklandı.")
    event.preventDefault();

    const productName = $("#productName").val()
    const customerName = $("#customerName").val()
    const box_customer_amount = $("#box_customer_amount").val()
    const customer_note = $("#customer_note").val()



    const obj = {
        productName:productName,
        customerName: customerName,
        box_customer_amount:box_customer_amount,
        customer_note:customer_note

    }


    $.ajax({
        url: './sales/customerBoxAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#productName").val(" ")
                $("#customerName").val(" ")
                $("#customer_note").val(" ")
                $("#box_customer_amount").val(" ")


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




let selectedPid = 0;
$("#productName").on("change",function (){
    console.log("Tıklanıldı")
    selectedPid = (this.value)



    let customerName=0;
    $("#customerName").on("change",function (){
        console.log("Tıklanıldı")
        customerName = (this.value)
        console.log(selectedProduct)
        console.log(customerName)


    })

    let selectedAmount=0;
    $("#box_customer_amount").on("change",function (){
        console.log("Tıklanıldı")
        selectedAmount = (this.value)
        console.log(selectedAmount)
        console.log(selectedAmount)
        saleCustomertotal(selectedPid,customerName,selectedAmount)



    })

})


function saleCustomertotal(pid,cid,sale_amount){

    $.ajax({
        url: './sales/saleCustomerTotal/'+pid+'/'+cid+'/'+sale_amount,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("saleCustomerTotal çalıştı")
            const obj = JSON.parse(data)
            // productName
            var html = ``;
            obj.forEach((item,index)=>{
                html+=`<label>MÜŞTERİ ADI</label> : <input type="text" class="form-control" id="c_name" value="${item.c_Name}">
                       <label>ÖDENECEK TUTAR</label>  <input id="saleBoxtotal" class="form-control" type="number" value="${item.total}">
                       `
            })
            $("#saleCustomerAdd").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}
//-------------------------------------------- Kayıtlı Müşteri Ödeme ekleme  --------------------------------------------//

$('#customerSale').submit((event) => {
    console.log("Customer Sale Tıklandı.")
    event.preventDefault();


    const c_name = $("#c_name").val()
    const saleBoxtotal = $("#saleBoxtotal").val()
    const customer_process = $("#customer_process").val()
    const customerBillNumber = $("#customerBillNumber").val()
    const box_cus_date = $("#box_cus_date").val()

    const obj = {
        c_name:c_name,
        saleBoxtotal:saleBoxtotal,
        customerBillNumber:customerBillNumber,
        box_cus_date:box_cus_date,
        customer_process:customer_process
    }


    $.ajax({
        url: './sales/customerPayAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#c_name").val(" ")
                $("#saleBoxtotal").val(" ")
                $("#customerBillNumber").val(" ")
                $("#box_cus_date").val(" ")
                $("#customer_process").val(" ")
                allCustomerPayResult()

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

//-------------------------------------------- Kayıtlı Müşteri Listeleme  --------------------------------------------//

function allCustomerPayResult(){
    $.ajax({
        url: './sales/listcustomerPay',
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
allCustomerPayResult()


let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        let new_process=""
        if(itm.customer_process==1){
            new_process='Nakit'
        }else if(itm.customer_process==2){
            new_process='Kredi Kartı'
        }else if(itm.customer_process==3){
            new_process='Havale'
        }
        const type=new_process


        html += `<tr>
            <th scope="row">${itm.cp_id}</th>
            <td>${dateToFormat(itm.box_cus_date)}</td>
            <td>${itm.customerBillNumber}</td>
            <td>${itm.c_name}</td>
            <td>`+type+`</td>
             <td>${itm.saleBoxtotal}</td>
            

          </tr>`
    }
    $("#salesRaw").html(html)
}


function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "-" + dt.getFullYear()
    return stReturn;
}









