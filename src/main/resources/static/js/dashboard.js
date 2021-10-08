function allRandevuResult(){
    $.ajax({
        url: './dashboard/randevulist',
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
allRandevuResult()

let globalArr=[]
function createRow(data){
    let html=``
    for (let i=0; i<data.length;i++){
        globalArr=data
        const itm=data[i]

        html += `<tr>
            ${dateToFormat(itm.createdDate)}
            <td>${itm.title}</td>
            <td>${dateToFormat(itm.start)}</td>
            <td>${dateToFormat(itm.end)}</td>
        
            

        </tr>`
    }
    $("#randevu").html(html)
}
function dateToFormat(stDate){
    const dt = new Date(stDate)
    const stReturn =dt.getDate()+ "-" + ((dt.getMonth() + 1) > 9 ? (dt.getMonth() + 1) : "0"+(dt.getMonth() + 1) ) + "-" + dt.getFullYear()+ " " + dt.getHours()+ ":" + dt.getMinutes()+ ":" + dt.getSeconds()
    return stReturn;
}

function total(){
    $.ajax({
        url: './dashboard/total',
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
total()

let Arr=[]
function create(data){
    let html=``
    for (let i=0; i<data.length;i++){
        Arr=data
        const itm=data[i]

        html += `<strong>${itm.totalStock}</strong>`
    }
    $("#st").html(html)
}



function Customer(){
    $.ajax({
        url: './dashboard/totalCustomer',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createC(data)
        },
        error: function (err){
            console.log(err)
            alert("Hata oluştu");
        }
    })
}
Customer()

let globArr=[]
function createC(data){
    let html=``
    for (let i=0; i<data.length;i++){
        globArr=data
        const itm=data[i]

        html += `<strong>${itm.totalCustomer}</strong>`
    }
    $("#cs").html(html)
}

