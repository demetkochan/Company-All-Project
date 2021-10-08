
/*
$('#labAdd').submit((event)=>{
    console.log("Tıklandı.")
    event.preventDefault();

    const lab_type = $("#lab_type").val()
    const diagnosis=$("#diagnosis").val()
    const animalName=$("#animalName").val()
    const imageName=$("#imageName").val()

    const obj={
        lab_type:lab_type,
        diagnosis:diagnosis,
        animalName:animalName,
        imageName:imageName
    }
    $.ajax({
        url: './laboratuvar/labResult/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#lab_type").val(" ")
                $("#diagnosis").val(" ")
                $("#animalName").val(" ")
                $("#imageAnimal").val("")
                allLabResult()
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

 */
//---------------------------------Laboratuvar Ekle Bitiş-----------------------------------------------------



function allLabResult(){
    $.ajax({
        url: './laboratuvar/labResultAdd/list',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log("hhhh"+data)
            createRow(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
allLabResult()

let labResultArr=[]
function createRow(data){
    let html=``

    for (let i=0; i<data.length;i++){
        labResultArr=data
        const itm=data[i]

        let new_type=""
        if(itm.lab_type==1){
            new_type='Hemogram'
        }else if(itm.lab_type==2){
            new_type='Röntgen'
        }else if(itm.lab_type==3){
            new_type='Biyokimya'
        }
        const type=new_type

        html +=` <tr>
               
            <td>${itm.c_name} ${itm.c_surname}</td>
            <td>${itm.p_name}</td>
            <td>`+type+`</td>
            <td>${itm.diagnosis}</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
           
                <button onclick="fncLabDelete(${itm.lid})" type="button" class="btn btn-outline-success "><i class="far fa-trash-alt"></i></button>
            
              </div>
              
            </td>
        </tr>`
    }
    $("#labResultRow").html(html)

}



//-------------------------------Laboratuvar List Bitiş----------------------------------------------------

let selectedCustomer = 0;
$("#cus_no").on("change",function (){
    console.log("Tıklanıldı")
    selectedCustomer = (this.value)
    console.log(selectedCustomer)
    animalLab(selectedCustomer)
})

function animalLab(cus_no){
    $.ajax({
        url: './laboratuvar/animalLabCustomer/'+cus_no,
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            console.log(data)
            console.log("Get metodu çalıştı")
            const obj = JSON.parse(data)
            // animalName
            var html = ``;
            html+=`<option value=" ">Hasta Adı Seçiniz</option>`

            obj.forEach((item,index)=>{
                html+=`<option value="`+item.aid+`">`+ item.p_name +`</option>`
            })
            $("#animalName").html(html)
        },
        error: function (err) {
            console.log(err)
        }
    })


}
function fncLabDelete(lid){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./laboratuvar/delete/"+lid,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allLabResult()
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


