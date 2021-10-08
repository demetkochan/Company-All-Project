$('#caseAdd').submit((event) => {
    console.log("Tıklandı.")
    event.preventDefault();

    const case_type= $("#case_type").val()
    const case_name = $("#case_name").val()

    let case_status = $("#case_status").val()
    if (document.getElementById('case_status').checked){
        case_status="true";
    }else {
        case_status="false";
    }

    const obj = {
        case_type : case_type,
        case_name: case_name,
        case_status:case_status

    }

    $.ajax({
        url: './newCaseDescription/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)

                $("#case_type").val(" ")
                $("#case_name").val(" ")
                $("#case_status").val(" ")
                $("#case_name").focus()
                allCaseResult()


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
function allCaseResult(){
    $.ajax({
        url: './newCaseDescription/list',
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
allCaseResult()

let Arr=[]
function createRow(data){
    let html=``

    for (let i=0; i<data.length;i++){
        Arr=data
        const itm=data[i]
        let new_type = " "
        if (itm.case_type == 1){
            new_type = 'Nakit'
        }else if(itm.case_type == 2){
            new_type = 'Havale'
        }else if(itm.case_type == 3){
            new_type = 'Kredi Kartı'
        }
        const casetype = new_type;
        const st = itm.case_status== true ? 'Aktif' : 'Aktif Değil'
        html += `<tr>
            <td>${itm.case_id}</td>
            <td>`+ casetype +`</td>
            <td>${itm.case_name}</td>
            <td>`+ st +`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncCaseDelete(${itm.case_id})" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
              </div>
            </td>

        </tr>`
    }
    $("#caseRow").html(html)
}
//-------------------------------------------- Case list - Finish --------------------------------------------//
//-------------------------------------------- Case delete - Start --------------------------------------------//
function fncCaseDelete(case_id){
    let answer = confirm("Silmek istediğinize emin misiniz?")
    if(answer){

        $.ajax({
            url:"./newCaseDescription/delete/"+case_id,
            type:"delete",
            dataType: 'text',
            success: function (data){
                console.log(typeof data)
                if( data != "0" ){
                    alert("Silme İşlemi Başarılı!")
                    allCaseResult()
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
//-------------------------------------------- Case delete - Finish --------------------------------------------//