/*
 Template Name: Stexo - Responsive Bootstrap 4 Admin Dashboard
 Author: Themesdesign
 File: Form Advanced Components Init
 */



!function ($) {
    "use strict";

    var Form = function () { };

    Form.prototype.init = function () {

        //onload pengguna
        $.get('http://localhost/tutor/admin/pengguna/get')
            .done(function (data) {
                var obj = JSON.parse(data);
                var len = obj.length;
                $("#select-pengguna").empty();
                $("#select-pengguna").append("<option value='default' disabled selected>Pilih Pengguna</option>");
                for (var i = 0; i < len; i++) {
                    var id = obj[i]['id'];
                    var name = obj[i]['nama'];

                    $("#select-pengguna").append("<option value='" + id + "'>" + name + "</option>");

                }
            })

        //onload tutor
        $.get('http://localhost/tutor/admin/tutor/get')
            .done(function (data) {
                var obj = JSON.parse(data);
                var len = obj.length;
                $("#select-tutor").empty();
                $("#select-tutor").append("<option value='default' disabled selected>Pilih Tutor</option>");
                for (var i = 0; i < len; i++) {
                    var id = obj[i]['id'];
                    var name = obj[i]['nama'];

                    $("#select-tutor").append("<option value='" + id + "'>" + name + "</option>");

                }
            })

            
        //onload kategori
        $.get('http://localhost/tutor/admin/kategori/get')
        .done(function (data) {
            var obj = JSON.parse(data);
            var len = obj.length;
            $("#select-kategori").empty();
            $("#select-kategori").append("<option value='default' disabled selected>Pilih Kategori</option>");
            for (var i = 0; i < len; i++) {
                var id = obj[i]['id'];
                var name = obj[i]['nama'];

                $("#select-kategori").append("<option value='" + id + "'>" + name + "</option>");

            }
        })

        //onload Pembayaran
        $.get('http://localhost/tutor/admin/pembayaran/get')
            .done(function (data) {
                var obj = JSON.parse(data);
                var len = obj.length;
                $("#select-pembayaran").empty();
                $("#select-pembayaran").append("<option value='default' disabled selected>Pilih Pembayaran</option>");
                for (var i = 0; i < len; i++) {
                    var id = obj[i]['id'];
                    var metode_pembayaran = obj[i]['metode_pembayaran'];

                    $("#select-pembayaran").append("<option value='" + id + "'>" + metode_pembayaran + "</option>");

                }
            })

        //onselect fakultas
        $("#select-tutor").change(function () {
            var tid = $(this).val();
            $.get('http://localhost/tutor/admin/tutor/get/' + tid)
                .done(function (data) {
                    var obj = JSON.parse(data);
                    var harga = obj['harga'];
                    $("#total").val(harga);
                })
        });

        $("#image-upload").change(function () {
            if (this.files && this.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#image-preview').attr('src', e.target.result);
                }

                reader.readAsDataURL(this.files[0]);
            }
        });

        $("#select-kategori").change(function () {
            $('#prodi-preview').text($("#select-kategori option:selected").text());
        });

        $("#jenis-kelamin").change(function () {
            $('#kelamin-preview').text($("#jenis-kelamin").text());
        });

        $("#nama-tutor").change(function () {
            $('#name-preview').text($("#nama-tutor").val());
        });

        $("#nama-pengguna").change(function () {
            $('#name-preview').text($("#nama-pengguna").val());
        });

        $("#visi-kandidat").change(function () {
            $('#visi-preview').html($("#visi-kandidat").val().replace(/\n/g, "<br>"));
        });

        $("#misi-kandidat").change(function () {
            $('#misi-preview').html($("#misi-kandidat").val().replace(/\n/g, "<br>"));
        });

        // $('#authentication-form').submit(function () {
        //     $.ajax({
        //         type: "POST",
        //         url: $(this).attr('action'),
        //         dataType: 'json',
        //         data: new FormData(this),
        //         processData: false,
        //         contentType: false,
        //         success: function (res) {
        //             if (res.status == false) {
        //                 Swal.fire({
        //                     title: 'Message',
        //                     text: res.message,
        //                     icon: 'error'
        //                 });
        //             } else {
        //                 Swal.fire({
        //                     title: 'Message',
        //                     text: res.message,
        //                     icon: 'success'
        //                 }).then(function () {
        //                     location.reload();
        //                 });
        //             }
        //         },
        //         error: function () {
        //             Swal.fire({
        //                 icon: 'error',
        //                 title: 'Oops...',
        //                 text: 'Sepertinya anda memasukkan data yang salah.',
        //                 // footer: '<a href>Why do I have this issue?</a>'
        //             })
        //         }
        //     });
        //     return false;
        // });

        // //select fakultas
        // $("#select-fakultas").change(function () {
        //     var deptid = $(this).val();

        //     $.ajax({
        //         url: 'getUsers.php',
        //         type: 'post',
        //         data: { depart: deptid },
        //         dataType: 'json',
        //         success: function (response) {

        //             var len = response.length;

        //             $("#sel_user").empty();
        //             for (var i = 0; i < len; i++) {
        //                 var id = response[i]['id'];
        //                 var name = response[i]['name'];

        //                 $("#sel_user").append("<option value='" + id + "'>" + name + "</option>");

        //             }
        //         }
        //     });
        // });

    },
        //init
        $.Form = new Form, $.Form.Constructor = Form
}(window.jQuery),

    //initializing
    function ($) {
        "use strict";
        $.Form.init();
    }(window.jQuery);