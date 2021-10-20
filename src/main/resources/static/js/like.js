function allProductLikeResult(){
    $.ajax({
        url: './like_mvc/likeResult',
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
allProductLikeResult()


//------------------------------------dislikeProduct----------------------------------------//
function allProductDislikeResult(){
    $.ajax({
        url: './like_mvc/dislikeResult',
        type: 'GET',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            console.log(data)
            createRo(data)
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })
}
allProductDislikeResult()


//-------------------------------------create table -----------------------------------------//

let globalArr = []
function createRow(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalArr = data
        const itm = data[i]

        html += `<tr>
            <th scope="row">${itm.id}  </th>  
             <td>${itm.productname}</td>
            <td>${itm.product_desc}</td>
            <td>${itm.product_price}</td>
      
  
          </tr>`
    }
    $("#productLikeRow").html(html)
}



let globalAr = []
function createRo(data){
    let html = ``
    for (let i = 0; i < data.length; i++) {
        globalAr = data
        const itm = data[i]

        html += `<tr>
            <th scope="row">${itm.id}  </th>  
             <td>${itm.productname}</td>
            <td>${itm.product_desc}</td>
            <td>${itm.product_price}</td>
      
  
          </tr>`
    }
    $("#dislikeRow").html(html)
}

