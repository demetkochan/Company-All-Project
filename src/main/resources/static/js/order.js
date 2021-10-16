$('#orderAdd').submit((event) => {
    event.preventDefault();

    const order_customer = $("#order_customer").val()
    const order_product = $("#order_product").val()
    const order_count = $("#order_count").val()
    const order_date = $("#order_date").val()
    const customer_address = $("#customer_address").val()
    const order_status = $("#order_status").val()


    const obj = {
        order_customer: {id:order_customer,cname:order_customer,cemail:order_customer},
        order_product: order_product,
        order_count: order_count,
        order_date: order_date,
        customer_address: customer_address,
        order_status:order_status
    }


    $.ajax({
        url: './order_mvc/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'JSON',
        contentType : 'application/json; charset=utf-8',
        success: function (data) {
            if (data) {
                console.log(data)
                $("#order_customer").val(" ")
                $("#order_product").val(" ")
                $("#order_count").val(" ")
                $("#order_date").val(" ")
                $("#customer_address").val(" ")
                $("#order_status").val(" ")
                fncReset()

            } else {
                console.log("Veri dönmedi.")
            }
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })

})


