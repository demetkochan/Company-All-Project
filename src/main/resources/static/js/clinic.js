$(document).ready(function (){

    console.log("klinik sale Girildi")
    $.ajax({
        url: './clinicalStatistics/clinicList',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            cRow(data)
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })

})


let globalAa = []
function cRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalAa = data
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
            <td>${itm.c_name}</td>
             <td>`+type+`</td>
             <td>${itm.saleBoxtotal}</td>
            
          </tr>`
    }
    $("#clinicSale").html(html)
}


//-------------------------------------------- list Process  --------------------------------------------//
$(document).ready(function (){

    console.log("klinik process Girildi")
    $.ajax({
        url: './clinicalStatistics/listProcess',
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

})



let globalArray = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArray = data
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
              <td>${itm.total}</td>
              <td>`+type+`</td>
              <td>${dateToFormat(itm.calendar)}</td>
           
           
            
          </tr>`
    }
    $("#listProcess").html(html)
}




function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =  dt.getFullYear()
    return stReturn;
}

function Animal(){
    $.ajax({
        url: './clinicalStatistics/totalAnimal',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createA(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
Animal()

let gArr=[]
function createA(data){
    let html=``
    for (let i=0; i<data.length;i++){
        gArr=data
        const itm=data[i]

        html += `<strong>${itm.totalAnmal}</strong>`
    }
    $("#a").html(html)
}
function Schedule(){
    $.ajax({
        url: './clinicalStatistics/totalSchedule',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createS(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
Schedule()

let gloArr=[]
function createS(data){
    let html=``
    for (let i=0; i<data.length;i++){
        gloArr=data
        const itm=data[i]

        html += `<strong>${itm.totalSchedule}</strong>`
    }
    $("#rd").html(html)
}