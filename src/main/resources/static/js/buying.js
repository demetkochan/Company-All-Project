$('#addVendor').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const vendor_name = $("#vendor").val()
    const vendor_email = $("#email").val()
    const vendor_phone = $("#phone").val()

    const obj = {
        vendor_name: vendor_name,
        vendor_email: vendor_email,
        vendor_phone: vendor_phone
    }

    $.ajax({
        url: './buying/addVendor',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#vendor").val(" ")
                $("#email").val(" ")
                $("#phone").val(" ")
                $("#phone").focus()
                allVendorResult()


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

//-------------------------------------------- Vendor Add - Finish --------------------------------------------//
function allVendorResult(){
    $.ajax({
        url: './buying/listVendor',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)

        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })
}
allVendorResult()


$('#productAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const product_name= $("#product_name").val()
    const product_unit = $("#product_unit").val()
    const category_group_cg_id = $("#category_group_cg_id").val();
    const product_type= $("#product_type").val()
    const vendor_vid = $("#vendor_vid").val()
    const product_barcode = $("#product_barcode").val()
    const product_code= $("#product_code").val()
    const product_kdv = $("#product_kdv").val()
    const product_buying= $("#product_buying").val()
    const product_sales= $("#product_sales").val()
    const product_stock= $("#product_stock").val()

    let product_status = $("#product_status").val()
    if (document.getElementById('product_status').checked){
        product_status="true";
    }else {
        product_status="false";
    }

    let product_sales_kdv = $("#product_sales_kdv").val()
    if (document.getElementById('product_sales_kdv').checked){
        product_sales_kdv="true";
    }else {
        product_sales_kdv="false";
    }

    let product_buying_kdv = $("#product_buying_kdv").val()
    if (document.getElementById('product_buying_kdv').checked){
        product_buying_kdv="true";
    }else {
        product_buying_kdv="false";
    }

    let product_amount_fixed = $("#product_amount_fixed").val()
    if (document.getElementById('product_amount_fixed').checked){
        product_amount_fixed="true";
    }else {
        product_amount_fixed="false";
    }

    const obj = {
        product_name : product_name,
        product_unit : product_unit,
        category_group_cg_id : category_group_cg_id,
        product_type : product_type,
        vendor_vid : vendor_vid,
        product_barcode : product_barcode,
        product_code : product_code,
        product_kdv : product_kdv,
        product_buying : product_buying,
        product_sales : product_sales,
        product_stock : product_stock,
        product_status : product_status,
        product_sales_kdv: product_sales_kdv,
        product_buying_kdv: product_buying_kdv,
        product_amount_fixed: product_amount_fixed

    }

    $.ajax({
        url: './buying/addProduct',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#product_name").val(" ")
                $("#product_unit").val(" ")
                $("#category_group_cg_id").val(" ")
                $("#product_type").val(" ")
                $("#vendor_vid").val(" ")
                $("#product_barcode").val(" ")
                $("#product_code").val(" ")
                $("#product_kdv").val(" ")
                $("#product_buying").val(" ")
                $("#product_sales").val(" ")
                $("#product_stock").val(" ")
                allProductResult()



            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (err) {
            console.log(err)
            alert("Ekleme işlemi sırısında bir hata oluştu!");
        }
    })
})
function allProductResult(){
    $.ajax({
        url: './buying/listProduct',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)

        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
allProductResult()



let selectedCustomer = 0;
$("#name").on("change",function (){
    console.log("Tıklanıldı")
    selectedCustomer = (this.value)
    console.log(selectedCustomer)
    vendorProduct(selectedCustomer)

})


function vendorProduct(vid) {

    $.ajax({
        url: './buying/productVendor/'+vid,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("get metodu çalıştı")
            const obj = JSON.parse(data)
            // productName
            var html = ``;
            html+=`<option value="">Ürün seçiniz Seçiniz</option>`

            obj.forEach((item,index)=>{
                html+=`<option value="`+item.product_Id+`">`+item.product_Name+`</option>`
            })
            $("#productName").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}


let selectedProduct = 0;
$("#productName").on("change",function (){
    console.log("Tıklanıldı")
    selectedProduct = (this.value)
    console.log(selectedProduct)
    productTotal(selectedProduct)

})
let total = 0;
//-------------------------------------------- Total - start --------------------------------------------//


function productTotal(pid){

    $.ajax({
        url: './buying/productTotal/'+pid,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)


            console.log("get metodu çalıştı")
            const obj = JSON.parse(data)
            // productName
            var html = ``;
            obj.forEach((item,index)=>{
                html+=`<label>ÖDENECEK TUTAR</label> : <input id="total" class="form-control" type="number" value="${item.total}">
                   `
            })
            $("#Formtotal").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}

console.log("Total değeri" +total)
//-------------------------------------------- - Add Buying  --------------------------------------------//


$('#buyingTotal').submit((event) => {
    console.log(" Add buying Tıklandı.")
    event.preventDefault();


    const name =  $("#name").val()
    const productName = $("#productName").val()
    const total = $("#total").val()
    const billNumber = $("#billNumber").val()
    const process = $("#process").val()
    const buyingNote = $("#buyingNote").val()
    const buying_date = $("#buying_date").val();



    const obj = {

        name : name,
        productName : productName,
        total : total,
        billNumber : billNumber,
        process : process,
        buyingNote : buyingNote,
        buying_date : buying_date,

    }


    $.ajax({
        url: './buying/addTotal',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log("post işlemi başarılı")
                allBuyingResult();

            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (err) {
            console.log(err)
            alert("Ekleme işlemi sırısında bir hata oluştu!");
        }
    })

})



//-------------------------------------------- - List Buying  --------------------------------------------//


function allBuyingResult(){
    $.ajax({
        url: './buying/listBuying',
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
allBuyingResult()

//-------------------------------------------- - Create Row Buying  --------------------------------------------//


let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]



        let new_process=""
        if(itm.process==1){
            new_process='Nakit'
        }else if(itm.process==2){
            new_process='Kredi Kartı'
        }else if(itm.process==3){
            new_process='Havale'
        }
        const type=new_process


        html += `<tr>
            <th scope="row">${itm.bid}</th>
            <td>${dateToFormat(itm.buying_date)}</td>
            <td>${itm.billNumber}</td>
            <td>${itm.name}</td>
            <td>`+type+`</td>
             <td>${itm.total}</td>
            

          </tr>`
    }
    $("#buyingRaw").html(html)
}


function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "-" + dt.getFullYear()
    return stReturn;
}
