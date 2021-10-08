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
        url: './productDescription/add',
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
//-------------------------------------------- Case Add - Finish --------------------------------------------//
//-------------------------------------------- Case List - Start --------------------------------------------//
function allProductResult(){
    $.ajax({
        url: './productDescription/list',
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
allProductResult()

let Arr=[]
function createRow(data){
    let html=``
    for (let i=0; i<data.length;i++){
        Arr=data
        const itm=data[i]
        html += `<tr>
               
            <td>${itm.product_id}</td>
            <td>${itm.product_name}</td>
            <td>${itm.product_barcode}</td>
            <td>${itm.product_buying}</td>
            <td>${itm.product_sales}</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button type="button" onclick="fncProductDetail(`+i+`)" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#productDetailModel"><i class="fas fa-plus"></i></button>
                
              </div>
            </td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
           
                <button onclick="fncProductDelete(${itm.product_id})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
            
              </div>
              
            </td>

        </tr>`
    }
    $("#productRow").html(html)
}
//-------------------------------------------- Case list - Finish --------------------------------------------//
//-------------------------------------------- Case delete - Start --------------------------------------------//
function fncProductDelete(product_id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./productDescription/delete/"+product_id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allProductResult()
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

function fncProductDetail(i){
    const itm = Arr[i];

    let new_unit = ""
    if(itm.product_unit == 1){
        new_unit = 'Adet'
    }
    else if(itm.product_unit == 2){
        new_unit = 'Paket'
    }
    else if(itm.product_unit == 3){
        new_unit = 'Şişe'
    }
    let new_type = ""
    if(itm.product_type == 1){
        new_type = 'Vitamin'
    }
    else if(itm.product_type == 2){
        new_type = 'İlaç'
    }
    else if(itm.product_type == 3){
        new_type = 'Mama'
    }

    let new_status = " "
    if (itm.product_status == true){
        new_status = 'Aktif'
    }
    else if (itm.product_status == false){
        new_status = 'Aktif Değil'
    }

    const status= new_status;
    const unit = new_unit;
    const type =new_type;
    $("#p_name").text( "("+ itm.product_id+")"+" "+itm.product_name );
    $("#p_status").text(status);
    $("#p_barcode").text(itm.product_barcode);
    $("#p_unit").text(unit);
    $("#p_code").text(itm.product_code);
    $("#category_group").text(itm.category_group_cg_id);
    $("#p_type").text(type);
    select_id = itm.product_id
    $("#product").val(itm.product_id).text(itm.product_name)

}

$('#stockAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const product= $("#product").val()
    const depo = $("#depo").val()
    const amount = $("#amount").val();



    const obj = {
        product : product,
        depo : depo,
        amount : amount

    }
    if ( select_id != 0 ) {
        // update
        obj["sid"] = select_id;
    }


    $.ajax({
        url: './productDescription/addStock',
        type: 'POST',
        data:  JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {

            if (data) {
                console.log(data)
                $("#product").val(" ")
                $("#depo").val(" ")
                $("#amount").val(" ")

                depoStock(selectedDepo)
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

let selectedDepo = 0;
$("#depoList").on("change",function (){
    console.log("Tıklanıldı")
    selectedDepo = (this.value)
    console.log(selectedDepo)
    depoStock(selectedDepo)

})

let globalArr=[]

function depoStock(sid) {

    $.ajax({
        url: './productDescription/stockDepo/'+sid,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("get metodu çalıştı")
            const obj = JSON.parse(data)
            let html = ``
            for (let i = 0; i < obj.length; i++) {
                globalArr = obj
                const itm = obj[i]

                html +=` <tr>
            <td>${itm.sid}</td>
            <td>${itm.product}</td>
            <td>${itm.depo_name}</td>
            <td>${itm.amount}</td>
            <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncStockUpdate(`+i+`)" type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#productDetailModel">Güncelle</button>
                    
               </div>
               </td>
               <td class="text-right" >
               <div class="btn-group" role="group" >
               <button onclick="fncStockDelete(${itm.sid})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               </div>
               
          </td>

          </tr>`
            }
            $("#depoRow").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}
depoStock(selectedDepo);
function fncReset(){
    select_id = 0;

    depoStock(selectedDepo);

}

function fncStockDelete(sid){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./productDescription/stockDelete/"+sid,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
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


function fncStockUpdate( i ) {

    const item = globalArr[i];
    select_id = item.sid
    console.log(select_id)
    $("#product").val(item.product)
    $("#depo").val(item.depo)
    $("#amount").val(item.amount)



}


