$('#customerAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const c_name = $("#c_name").val()
    const c_surname = $("#c_surname").val()
    const c_phone = $("#c_phone").val()
    const c_mobile_phone = $("#c_mobile_phone").val()
    const c_email = $("#c_email").val()
    const tax_administration = $("#tax_administration").val()
    const c_note = $("#c_note").val()
    const tc_no = $("#tc_no").val()
    const il = $("#il").val()
    const ilce = $("#ilce").val()
    const address = $("#address").val()
    const c_code = $("#c_code").val()
    const c_no = $("#c_no").val()

    const obj={
        c_name:c_name,
        c_surname:c_surname,
        c_phone:c_phone,
        c_mobile_phone:c_mobile_phone,
        c_email:c_email,
        tax_administration:tax_administration,
        c_note:c_note,
        tc_no:tc_no,
        il:il,
        ilce:ilce,
        address:address,
        c_code:c_code,
        c_no:c_no
    }

    $.ajax({
        url:'./newCustomerAdd/add',
        type:'POST',
        data:JSON.stringify(obj),
        dataType:'JSON',
        contentType : 'application/json; charset=utf-8',
        success:function (data){
            if(data){
                console.log(data)
                $("#c_name").val(" ")
                $("#c_surname").val(" ")
                $("#c_phone").val(" ")
                $("#c_mobile_phone").val(" ")
                $("#c_email").val(" ")
                $("#tax_administration").val(" ")
                $("#c_note").val(" ")
                $("#tc_no").val(" ")
                $("#il").val(" ")
                $("#ilce").val(" ")
                $("#address").val(" ")
                $("#c_code").val(" ")
                $("#c_no").val(" ")
            }else{
                console.log("Veri bulunamadı.")
            }
        },
        error:function (err){
            console.log(err);
            alert("İşlem sırasında bir hata oluştu!");
        }
    })


})
//Customer-List
function allCustomer(){
    $.ajax({
        url: './newCustomerAdd/list',
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
allCustomer()

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]
        html += `<tr>
            <th scope="row">${itm.cid}</th>
            <td>${itm.c_name} ${itm.c_surname}</td>
            <td>${itm.c_phone}</td>
            <td>${itm.c_email}</td>
            <td>${itm.tc_no}</td>
            <td>${itm.c_note}</td>
            <td>${itm.address}</td>
       
            
          </tr>`

    }
    $("#customerTable").html(html)
}

function fncCustomerDetail(cid){
    let html = ``;
    $.ajax({
        url:'./laboratuvar/detail/'+cid,
        type:"GET",
        dataType: 'text',
        contentType : 'application/json; charset=utf-8',
        success: function (data){
            console.log("geldi")
            console.log(data)
            const obj = JSON.parse(data)

            for (let i = 0; i < data.length; i++) {
                globalArr = data
                const itm = data[i]
                html += `<tr>
            <th scope="row">${itm.cid}</th>
            <td>${itm.c_name}</td>
            <td>${itm.c_surname}</td>
            <td>${itm.c_phone}</td>
            <td>${itm.c_email}</td>
            <td>${itm.tc_no}</td>
            <td>${itm.c_note}</td>
            <td>${itm.address}</td> 
          </tr>`
            }

        },
        error: function (err){
            console.log(err)
        }
    })
    $("#customerTable").html(html)
}




let a = 0;
$('#btnAdd').click(function (){
    console.log("Tıklandı1")
    a++;
    const newForm=`<form class="row p-3">
       <div class="col-md-4 mb-3">
                <label for="`+a+`_p_name" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Hasta Adı</label>
                <input type="text" name="p_name" id="`+a+`_p_name" class="form-control" />
            </div>
            <div class="col-md-4 mb-3">
                <label for="0_cus_no" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Müşteri No</label>
                <input name="cus_no" type="text" id="`+a+`_cus_no" class="form-control" placeholder="Müşteri No Giriniz" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="0_cip_no" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Çip Numarası</label>
                <input type="number" name="cip_no" id="`+a+`_cip_no" class="form-control" />
            </div>
            <div class="col-md-4 mb-3">
                <label for="0_karne_no" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Karne/Küpe Numarası</label>
                <input type="number" name="karne_no" id="`+a+`_karne_no" class="form-control" />
            </div>
            <div class="col-md-4 mb-3">
                <label for="p_birth" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Doğum Tarihi</label>
                <input type="text" name="p_birth" id="`+a+`_p_birth" class="form-control" />
            </div>


            <div class="col-md-4 mb-3">
                <label for="0_p_type" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Tür</label>
                <select class="form-select" name="p_type" id="`+a+`_p_type">
                    <option value="">Seçiniz</option>
                    <option value="1"></option>
                    <option value="2"></option>
                    <option value="3"></option>
                </select>
            </div>
            <div class="col-md-4 mb-4">
            <div class="form-check" style="border-inline: #4f5050">
                <label class="form-check-label" for="stayed">
                    Kısırlaştırılmış
                </label>
                <input class="form-check-input" type="checkbox" value="" id="`+a+`_stayed">

            </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="p_race" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Irkı</label>
                <select class="form-select" name="p_race" id="`+a+`_p_race">
                    <option value="-1">Seçiniz</option>
                    <option value="0"></option>
                    <option value="1"></option>
                    <option value="2"></option>
                </select>
            </div>

            <div class="col-md-4 mb-3">
                <label for="pcolor" class="form-label" style="font-family: Bahnschrift;font-size: large;color: #5c636a">Rengi</label>
                <input type="text" name="pcolor" id="`+a+`_pcolor" class="form-control" />
            </div>

            <div class="col-md-4 mb-3">
                <label  style="font-family: Bahnschrift;font-size: large;color: #5c636a">Cinsiyet</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="gender" id="`+a+`_gender" value="option1" >
                <label class="form-check-label" for="gender">
                    Dişi
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
                <label class="form-check-label" for="exampleRadios2">
                    Erkek
                </label>

            </div>
            </div>
            <div class="col-md-3"> <button type="submit" class="btn btn-danger mr-1 p-6"> <i class="fas fa-trash"></i> Sil</button></div>
</form>`
    $("#addForms").append(newForm);
});
$('#btnsubmitSend').click(function (){
    for(let j=0;j<=a;j++){
        const p_name=$("#"+j+"_p_name").val()
        const cus_no=$("#"+j+"_cus_no").val()
        const pcolor=$("#"+j+"_pcolor").val()
        const karne_no=$("#"+j+"_karne_no").val()
        const p_birth=$("#"+j+"_p_birth").val()
        const p_type=$("#"+j+"_p_type").val()
        const spayed=$("#"+j+"_spayed").val()
        const p_race=$("#"+j+"_p_race").val()
        const gender=$("#"+j+"_gender").val()


        console.log(p_name)
    }
})

//**********************************************************************************************************

$('#addAnimal').submit((event) => {
    console.log("Tıklandı.2")
    event.preventDefault();

    const p_name= $("#p_name").val()
    const cip_no= $("#cip_no").val()
    const karne_no= $("#karne_no").val()
    const p_birth= $("#p_birth").val()
    const p_type= $("#p_type").val()
    const p_race= $("#p_race").val()
    const pcolor= $("#pcolor").val()

    const cus_no= $("#cus_no").val()

    let spayed = $("#spayed").val()
    if (document.getElementById('spayed').checked){
        spayed="true";
    }else {
        spayed="false";
    }
    let gender = $("#gender1").val()
    if(document.getElementById('gender1').click()){
        gender=1
    }else{
        gender=2
    }


    const obj={
        p_name:p_name,
        cip_no:cip_no,
        karne_no:karne_no,
        p_birth:p_birth,
        p_type:p_type,
        p_race:p_race,
        pcolor:pcolor,
        gender:gender,
        spayed:spayed,
        cus_no:cus_no
    }

    $.ajax({
        url: './newCustomerAdd/animal',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success:function(data){
            if (data) {
                console.log(data)
                $("#p_name").val(" ")
                $("#cip_no").val(" ")
                $("#karne_no").val(" ")
                $("#p_birth").val(" ")
                $("#p_type").val(" ")
                $("#p_race").val(" ")
                $("#pcolor").val(" ")
                $("#gender").val(" ")
                $("#cus_no").val(" ")



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


function dateToFormat(stDate) {
    const dt = new Date(stDate)
    const stReturn = dt.getDate() + "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0" + (dt.getMonth() + 1)) + "-" + dt.getFullYear()
    return stReturn;
}


/*  let animal_name = "";
  let customer_no = 0
  let chip_number =0 ;
  let earring_number = 0;
  let animal_birth;
  let animal_type = 0;
  let neutered;
  let animal_race;
  let animal_color;
  let animal_gender;*/

/*   for (let j = 0; j <=i ; j++) {
       const animal_name = $("#"+j+"_animal_name").val();
       const customer_no= $("#"+j+"_customer_no").val();
       const chip_number= $("#"+j+"_chip_number").val();
       const earring_number= $("#"+j+"_earring_number").val();
       const animal_birth= $("#"+j+"_animal_birth").val();
       const animal_type= $("#"+j+"_animal_type").val();
       const neutered= $("#"+j+"_neutered").val();
       const animal_race= $("#"+j+"_animal_race").val();
       const animal_color= $("#"+j+"_animal_color").val();
       const animal_gender= $("#"+j+"_animal_gender").val();

       const obj = {
           animal_name: animal_name,
           customer_no: customer_no,
           chip_number: chip_number,
           earring_number: earring_number,
           animal_birth: animal_birth,
           animal_type: animal_type,
           animal_race: animal_race,
           animal_color: animal_color,
           animal_gender: animal_gender,
           neutered: neutered

       }

       $.ajax({
           url: './newCustomerAdd/animal',
           type: 'POST',
           data: JSON.stringify(obj),
           dataType: 'json',
           contentType : 'application/json; charset=utf-8',
           async: true,
           success: function (data) {
               if (data) {
                   console.log(data)
                   console.log("Ekleme İşlemi Başarılı")
                   animal_name = $("#"+j+"_animal_name").val("");
                    customer_no= $("#"+j+"_customer_no").val("");
                    chip_number= $("#"+j+"_chip_number").val("");
                    earring_number= $("#"+j+"_earring_number").val("");
                    animal_birth= $("#"+j+"_animal_birth").val("");
                    animal_type= $("#"+j+"_animal_type").val("");
                    neutered= $("#"+j+"_neutered").val("");
                    animal_race= $("#"+j+"_animal_race").val("");
                    animal_color= $("#"+j+"_animal_color").val("");
                    animal_gender= $("#"+j+"_animal_gender").val("");

               } else {
                   console.log("Veri dönmedi.")
               }
           },
           error: function (err) {
               console.log(err)
               alert("İşlem işlemi sırısında bir hata oluştu!");
           }
       })

   }*/




