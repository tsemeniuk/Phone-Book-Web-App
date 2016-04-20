$(document).ready(function () {

    console.log("DOCUMENT READY");

    $('#addContact').click(function () {
        $.ajax({
            type: 'POST',
            url: '/add',
            dataType: 'html',
            data: {
                firstName: $("#firstName").val(),
                secondName: $("#secondName").val(),
                lastName: $("#lastName").val(),
                phoneMobile: $("#phoneMobile").val(),
                phoneHome: $("#phoneHome").val(),
                address: $("#address").val(),
                email: $("#email").val(),
            },
            success: function (res, status, xhr) {
                //window.location.reload(true);
                window.location.href = '/';
                //$(this).html(res);
            }
        });
    });
    $('#editContact').click(function () {
        $.ajax({
            type: 'POST',
            url: '/edit',
            dataType: 'html',
            data: {
                firstName: $("#firstName").val(),
                secondName: $("#secondName").val(),
                lastName: $("#lastName").val(),
                phoneMobile: $("#phoneMobile").val(),
                phoneHome: $("#phoneHome").val(),
                address: $("#address").val(),
                email: $("#email").val(),
            },
            success: function (res, status, xhr) {
                //alert(xhr.getResponseHeader("info"));
                window.location.href = '/';
            }
        });
    });

    $(function () {
        $('[data-toggle="tooltip"]').tooltip({
//            placement: "bottom",
            trigger: "hover",
            'container': 'body'
        })

    })

    //$('#deleteContact').click(function () {
    //    $.ajax({
    //        type: 'DELETE',
    //        url: '/delete',
    //        dataType: 'html',
    //        data: {
    //            id: $("#id").val(),
    //        },
    //        success: function (res, status, xhr) {
    //            //alert(xhr.getResponseHeader("info"));
    //            window.location.href = '/';
    //        }
    //    });
    //});
});



