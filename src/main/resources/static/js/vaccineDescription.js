$('#petAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const pet_vaccine_name= $("#pet_vaccine_name").val()
    const pet_vaccine_unit = $("#pet_vaccine_unit").val()
    const category_group_cg_id = $("#category_group_cg_id").val();
    const pet_vaccine_type= $("#pet_vaccine_type").val()
    const vendor_vid = $("#vendor_vid").val()
    const pet_vaccine_barcode = $("#pet_vaccine_barcode").val()
    const pet_vaccine_code= $("#pet_vaccine_code").val()
    const pet_vaccine_kdv = $("#pet_vaccine_kdv").val()
    const pet_vaccine_buying= $("#pet_vaccine_buying").val()
    const pet_vaccine_sales= $("#pet_vaccine_sales").val()
    const pet_vaccine_stock= $("#pet_vaccine_stock").val()
    const pet_vaccine_tip= $("#pet_vaccine_tip").val()
    const pet_vaccine_number = $("#pet_vaccine_number").val()



    let pet_vaccine_status = $("#pet_vaccine_status").val()
    if (document.getElementById('pet_vaccine_status').checked){
        pet_vaccine_status="true";
    }else {
        pet_vaccine_status="false";
    }

    let pet_vaccine_sales_kdv = $("#pet_vaccine_sales_kdv").val()
    if (document.getElementById('pet_vaccine_sales_kdv').checked){
        pet_vaccine_sales_kdv="true";
    }else {
        pet_vaccine_sales_kdv="false";
    }

    let pet_vaccine_buying_kdv = $("#pet_vaccine_buying_kdv").val()
    if (document.getElementById('pet_vaccine_buying_kdv').checked){
        pet_vaccine_buying_kdv="true";
    }else {
        pet_vaccine_buying_kdv="false";
    }

    let pet_vaccine_amount_fixed = $("pet_vaccine_amount_fixed").val()
    if (document.getElementById('pet_vaccine_amount_fixed').checked){
        pet_vaccine_amount_fixed="true";
    }else {
        pet_vaccine_amount_fixed="false";
    }

    const obj = {
        pet_vaccine_name: pet_vaccine_name,
        pet_vaccine_unit : pet_vaccine_unit,
        category_group_cg_id : category_group_cg_id,
        pet_vaccine_type : pet_vaccine_type,
        vendor_vid : vendor_vid,
        pet_vaccine_barcode : pet_vaccine_barcode,
        pet_vaccine_code : pet_vaccine_code,
        pet_vaccine_kdv  : pet_vaccine_kdv,
        pet_vaccine_buying : pet_vaccine_buying,
        pet_vaccine_sales : pet_vaccine_sales,
        pet_vaccine_stock : pet_vaccine_stock,
        pet_vaccine_status:pet_vaccine_status,
        pet_vaccine_buying_kdv:pet_vaccine_buying_kdv,
        pet_vaccine_sales_kdv:pet_vaccine_sales_kdv,
        pet_vaccine_amount_fixed:pet_vaccine_amount_fixed,
        pet_vaccine_tip : pet_vaccine_tip,
        pet_vaccine_number : pet_vaccine_number


    }

    $.ajax({
        url: './vaccineDescription/petVaccineAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#pet_vaccine_name").val(" ")
                $("#pet_vaccine_unit").val(" ")
                $("#category_group_cg_id").val(" ")
                $("#pet_vaccine_type").val(" ")
                $("#vendor_vid").val(" ")
                $("#pet_vaccine_barcode").val(" ")
                $("#pet_vaccine_code").val(" ")
                $("#pet_vaccine_kdv").val(" ")
                $("#pet_vaccine_buying").val(" ")
                $("#pet_vaccine_sales").val(" ")
                $("#pet_vaccine_stock").val(" ")
                $("#pet_vaccine_tip").val(" ")
                $("#pet_vaccine_number").val(" ")
                allPetResult()


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
function allPetResult(){
    $.ajax({
        url: './vaccineDescription/petList',
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
allPetResult()

let Arr=[]
function createRow(data){
    let html=``

    for (let i=0; i<data.length;i++){
        Arr=data
        const itm=data[i]
        html += `<tr>
               
            <td>${itm.pet_vaccine_id}</td>
            <td>${itm.pet_vaccine_name}</td>
            <td>${itm.pet_vaccine_barcode}</td>
            <td>${itm.pet_vaccine_buying}</td>
            <td>${itm.pet_vaccine_sales}</td>
            <td class="text-right" >
            <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button type="button" onclick="fncPetDetail(`+i+`)" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#petDetailModel"><i class="fas fa-plus"></i></button>
                
              </div>
            
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncPetDelete(${itm.pet_vaccine_id})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
             
              </div>
            </td>
            
        </tr>`
    }
    $("#vaccine").html(html)
}
function fncPetDelete(pet_vaccine_id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./vaccineDescription/delete/"+pet_vaccine_id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allPetResult()
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




//petStock start
function fncPetDetail(i){
    const itm = Arr[i];

    let new_unit = ""
    if(itm.pet_vaccine_unit == 1){
        new_unit = 'Adet'
    }
    else if(itm.pet_vaccine_unit == 2){
        new_unit = 'Kutu'
    }

    let new_type = ""
    if(itm.pet_vaccine_type == 0){
        new_type = 'Pet Aşı'
    }


    let new_status = " "
    if (itm.pet_vaccine_status == true){
        new_status = 'Aktif'
    }
    else if (itm.pet_vaccine_status == false){
        new_status = 'Aktif Değil'
    }

    const status= new_status;
    const unit = new_unit;
    const type =new_type;
    $("#p_name").text( "("+ itm.pet_vaccine_id+")"+" "+itm.pet_vaccine_name );
    $("#p_status").text(status);
    $("#p_barcode").text(itm.pet_vaccine_barcode);
    $("#p_unit").text(unit);
    $("#p_code").text(itm.pet_vaccine_code);
    $("#category_group").text(itm.category_group_cg_id);
    $("#p_type").text(type);
    select_id = itm.pet_vaccine_id
    $("#pet_vac").val(itm.pet_vaccine_id).text(itm.pet_vaccine_name)

}

$('#stockAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const pet_vac= $("#pet_vac").val()
    const depo = $("#depo").val()
    const amount = $("#amount").val();



    const obj = {
        pet_vac : pet_vac,
        depo : depo,
        amount : amount

    }
    if ( select_id != 0 ) {
        // update
        obj["psid"] = select_id;
    }


    $.ajax({
        url: './vaccineDescription/addPetStock',
        type: 'POST',
        data:  JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {

            if (data) {
                console.log(data)
                $("#pet_vac").val(" ")
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

let globlArr=[]

function depoStock(psid) {

    $.ajax({
        url: './vaccineDescription/petStockDepo/'+psid,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("get metodu çalıştı")
            const obj = JSON.parse(data)
            let html = ``
            for (let i = 0; i < obj.length; i++) {
                globlArr = obj
                const itm = obj[i]
                html +=` <tr>
            <td>${itm.psid}</td>
            <td>${itm.pet_vac}</td>
            <td>${itm.depo_name}</td>
            <td>${itm.amount}</td>
            <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncPetStockUpdate(`+i+`)" type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#petDetailModel">Güncelle</button>
                    
               </div>
               </td>
               <td class="text-right" >
               <div class="btn-group" role="group" >
               <button onclick="fncPetStockDelete(${itm.psid})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
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

function fncPetStockDelete(psid){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./vaccineDescription/petStockDelete/"+psid,
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


function fncPetStockUpdate( i ) {

    const item = globlArr[i];
    select_id = item.psid
    console.log(select_id)
    $("#pet_vac").val(item.pet_vac)
    $("#depo").val(item.depo)
    $("#amount").val(item.amount)



}
//finisshh

