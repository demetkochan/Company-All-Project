
$("#payIn").click(function(e) {
    e.preventDefault();
    console.log("paYıN TIKLANILDI")
    allPayInResult();

})


$("#payOut").click(function(e) {
    e.preventDefault();
    console.log("paYOut TIKLANILDI")
    allPayOut1Result();

})

function allPayOut1Result(){
    $.ajax({
        url: './payAction/payOutlist',
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
}

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
            <th scope="row">${itm.cp_id}</th>
            <td>${dateToFormat(itm.box_cus_date)}</td>
            <td>${itm.customerBillNumber}</td>
            <td>${itm.c_name}</td>
            <td>`+type+`</td>
             <td>${itm.saleBoxtotal}</td>
            

          </tr>`
    }
    $("#PayInRaw").html(html)
}
let selectedProcess = 0;
$("#Process").on("change",function (){
    console.log("Tıklanıldı")
    selectedProcess = (this.value)
    console.log(selectedProcess)
    allPayOutResult(selectedProcess)

})




function allPayOutResult(pr){
    console.log("ajax " + pr)
    $.ajax({
        url: './payAction/salectProcess/'+pr,
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
}


function allPayInResult(){
    $.ajax({
        url: './payAction/payInlist',
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
allPayInResult()




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
    $("#PayInRaw").html(html)
}





function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "-" + dt.getFullYear()
    return stReturn;
}