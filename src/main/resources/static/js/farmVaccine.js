$('#farmAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const farm_vaccine_name= $("#farm_vaccine_name").val()
    const farm_vaccine_unit = $("#farm_vaccine_unit").val()
    const category_group_id = $("#category_group_id").val();
    const farm_vaccine_type= $("#farm_vaccine_type").val()
    const vendor_id = $("#vendor_id").val()
    const farm_vaccine_barcode = $("#farm_vaccine_barcode").val()
    const farm_vaccine_code= $("#farm_vaccine_code").val()
    const farm_vaccine_kdv = $("#farm_vaccine_kdv").val()
    const farm_vaccine_buying= $("#farm_vaccine_buying").val()
    const farm_vaccine_sales= $("#farm_vaccine_sales").val()
    const farm_vaccine_stock= $("#farm_vaccine_stock").val()
    const farm_vaccine_tip= $("#farm_vaccine_tip").val()




    let farm_vaccine_status = $("#farm_vaccine_status").val()
    if (document.getElementById('farm_vaccine_status').checked){
        farm_vaccine_status="true";
    }else {
        farm_vaccine_status="false";
    }

    let farm_vaccine_sales_kdv = $("#farm_vaccine_sales_kdv").val()
    if (document.getElementById('farm_vaccine_sales_kdv').checked){
        farm_vaccine_sales_kdv="true";
    }else {
        farm_vaccine_sales_kdv="false";
    }

    let farm_vaccine_buying_kdv = $("#farm_vaccine_buying_kdv").val()
    if (document.getElementById('farm_vaccine_buying_kdv').checked){
        farm_vaccine_buying_kdv="true";
    }else {
        farm_vaccine_buying_kdv="false";
    }

    let farm_vaccine_amount_fixed = $("farm_vaccine_amount_fixed").val()
    if (document.getElementById('farm_vaccine_amount_fixed').checked){
        farm_vaccine_amount_fixed="true";
    }else {
        farm_vaccine_amount_fixed="false";
    }

    const obj = {
        farm_vaccine_name: farm_vaccine_name,
        farm_vaccine_unit : farm_vaccine_unit,
        category_group_id  : category_group_id,
        farm_vaccine_type :farm_vaccine_type,
        vendor_id :vendor_id,
        farm_vaccine_barcode : farm_vaccine_barcode,
        farm_vaccine_code : farm_vaccine_code,
        farm_vaccine_kdv : farm_vaccine_kdv,
        farm_vaccine_buying : farm_vaccine_buying,
        farm_vaccine_sales : farm_vaccine_sales,
        farm_vaccine_stock : farm_vaccine_stock,
        farm_vaccine_tip : farm_vaccine_tip,
        farm_vaccine_status:farm_vaccine_status,
        farm_vaccine_buying_kdv:farm_vaccine_buying_kdv,
        farm_vaccine_sales_kdv:farm_vaccine_sales_kdv,
        farm_vaccine_amount_fixed:farm_vaccine_amount_fixed,
        farm_vaccine_tip : farm_vaccine_tip,


    }

    $.ajax({
        url: './vaccineDescription/farmVaccineAdd',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#farm_vaccine_name").val(" ")
                $("#farm_vaccine_unit").val(" ")
                $("#category_group_id").val(" ")
                $("#farm_vaccine_type").val(" ")
                $("#vendor_id").val(" ")
                $("#farm_vaccine_barcode").val(" ")
                $("#farm_vaccine_code").val(" ")
                $("#farm_vaccine_kdv").val(" ")
                $("#farm_vaccine_buying").val(" ")
                $("#farm_vaccine_sales").val(" ")
                $("#farm_vaccine_stock").val(" ")
                $("#farm_vaccine_tip").val(" ")
                allFarmResult()


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
function allFarmResult(){
    $.ajax({
        url: './vaccineDescription/farmList',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            create(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
allFarmResult()
let gArr=[]
function create(data){
    let html=``

    for (let i=0; i<data.length;i++){
        gArr=data
        const itm=data[i]
        html += `<tr>
            <td>${itm.farm_vaccine_id}</td>  
            <td>${itm.farm_vaccine_name}</td>
            <td>${itm.farm_vaccine_barcode}</td>
            <td>${itm.farm_vaccine_buying}</td>
            <td>${itm.farm_vaccine_sales}</td>
            <td class="text-right" >
                <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button type="button" onclick="fncFarmDetail(`+i+`)" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#farmDetailModel"><i class="fas fa-plus"></i></button>
                
              </div>
            
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncFarmDelete(${itm.farm_vaccine_id})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
             
              </div>
            </td>

        </tr>`
    }
    $("#farm").html(html)
}

function fncFarmDelete(farm_vaccine_id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./vaccineDescription/delete/"+farm_vaccine_id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allFarmResult()
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

function fncFarmDetail(i){
    const itm = gArr[i];

    let new_unit = ""
    if(itm.farm_vaccine_unit == 1){
        new_unit = 'Adet'
    }
    else if(itm.farm_vaccine_unit == 2){
        new_unit = 'Paket'
    }
    else if(itm.farm_vaccine_unit == 3){
        new_unit = 'Şişe'
    }
    let new_type = ""
    if(itm.farm_vaccine_type == 0){
        new_type = 'Çiftlik Aşı'
    }


    let new_status = " "
    if (itm.farm_vaccine_status == true){
        new_status = 'Aktif'
    }
    else if (itm.farm_vaccine_status == false){
        new_status = 'Aktif Değil'
    }

    const status= new_status;
    const unit = new_unit;
    const type =new_type;
    $("#pr_name").text( "("+ itm.farm_vaccine_id+")"+" "+itm.farm_vaccine_name );
    $("#pr_status").text(status);
    $("#pr_barcode").text(itm.farm_vaccine_barcode);
    $("#pr_unit").text(unit);
    $("#pr_code").text(itm.farm_vaccine_code);
    $("#category_gr").text(itm.category_group_id);
    $("#pr_type").text(type);
    select_id = itm.farm_vaccine_id
    $("#farm_vac").val(itm.farm_vaccine_id).text(itm.farm_vaccine_name)

}

$('#fStockAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const farm_vac= $("#farm_vac").val()
    const warehouse = $("#warehouse").val()
    const number = $("#number").val();



    const obj = {
        farm_vac : farm_vac,
        warehouse : warehouse,
        number : number

    }
    if ( select_id != 0 ) {
        // update
        obj["fsid"] = select_id;
    }


    $.ajax({
        url: './vaccineDescription/addFarmStock',
        type: 'POST',
        data:  JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {

            if (data) {
                console.log(data)
                $("#farm_vac").val(" ")
                $("#warehouse").val(" ")
                $("#number").val(" ")

                Stock(selectDepo)
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

let selectDepo = 0;
$("#dList").on("change",function (){
    console.log("Tıklanıldı")
    selectDepo = (this.value)
    console.log(selectDepo)
    Stock(selectDepo)

})

let globalArr=[]

function Stock(fsid) {

    $.ajax({
        url: './vaccineDescription/farmStockDepo/'+fsid,
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
            <td>${itm.fsid}</td>
            <td>${itm.farm_vac}</td>
            <td>${itm.depo_name}</td>
            <td>${itm.number}</td>
            <td class="text-right" >
               <div class="btn-group" role="group">
                    <button onclick="fncFarmStockUpdate(`+i+`)" type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#farmDetailModel">Güncelle</button>
                    
               </div>
               </td>
               <td class="text-right" >
               <div class="btn-group" role="group" >
               <button onclick="fncFarmStockDelete(${itm.fsid})" type="button" class="btn btn-outline-danger "><i class="far fa-trash-alt"></i></button>
               </div>
               
          </td>

          </tr>`
            }
            $("#dRow").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })

}
Stock(selectDepo);
function fncReset(){
    select_id = 0;

    Stock(selectDepo);

}

function fncFarmStockDelete(fsid){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./vaccineDescription/farmStockDelete/"+fsid,
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


function fncFarmStockUpdate( i ) {

    const item = globalArr[i];
    select_id = item.fsid
    console.log(select_id)
    $("#farm_vac").val(item.farm_vac)
    $("#warehouse").val(item.warehouse)
    $("#number").val(item.number)



}



