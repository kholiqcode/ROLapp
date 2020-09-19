<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title><?= $title ?></title>
    <meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
    <meta content="Themesdesign" name="author" />
    <link rel="shortcut icon" href="<?= base_url('assets/images/favicon.ico') ?>">

    <!-- Sweet Alert -->
    <link href="<?= base_url('assets/plugins/sweet-alert2/sweetalert2.css') ?>" rel="stylesheet" type="text/css">

    <link href="<?= base_url('assets/css/bootstrap.min.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/metismenu.min.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/icons.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/style.css') ?>" rel="stylesheet" type="text/css">

</head>

<body>

    <!-- Begin page -->
    <div class="accountbg"></div>
    <div class="home-btn d-none d-sm-block">
        <a href="<?= base_url() ?>" class="text-white"><i class="fas fa-home h2"></i></a>
    </div>
    <div class="wrapper-page">
        <div class="card card-pages shadow-none">

            <div class="card-body">
                <div class="text-center m-t-0 m-b-15">
                    <a href="<?= base_url('admin') ?>" class="logo logo-admin"><img src="<?= base_url('assets/images/logo-dark.png') ?>" alt="" height="24"></a>
                </div>
                <h5 class="font-18 text-center">Masuk untuk melanjutkan ke Admin Panel.</h5>
                <?= form_open(base_url('admin/login'), ['method' => 'POST', 'id' => 'authentication-form', 'class' => 'form-horizontal m-t-30']) ?>

                <div class="form-group">
                    <div class="col-12">
                        <label>Username</label>
                        <?= form_input(['type' => 'text', 'name' => 'username', 'id' => 'username', 'value' => '', 'class' => 'form-control', 'placeholder' => 'Masukkan username', 'required' => true]) ?>
                        <?= form_error('username') ?>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-12">
                        <label>Password</label>
                        <?= form_password(['type' => 'password', 'name' => 'password', 'class' => 'form-control', 'id' => 'password', 'placeholder' => 'Masukkan password', 'required' => true]) ?>
                        <?= form_error('password') ?>
                    </div>
                </div>

                <div class="form-group text-center m-t-20">
                    <div class="col-12">
                        <button class="btn btn-primary btn-block btn-lg waves-effect waves-light" type="submit">Log
                            In</button>
                    </div>
                </div>
                <?= form_close() ?>
            </div>

        </div>
    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    <script src="<?= base_url('assets/js/jquery.min.js') ?>"></script>
    <script src="<?= base_url('assets/js/bootstrap.bundle.min.js') ?>"></script>
    <script src="<?= base_url('assets/js/metismenu.min.js') ?>"></script>
    <script src="<?= base_url('assets/js/jquery.slimscroll.js') ?>"></script>
    <script src="<?= base_url('assets/js/waves.min.js') ?>"></script>

    <!-- Sweet-Alert  -->
    <script src="<?= base_url('assets/plugins/sweet-alert2/sweetalert2.min.js') ?>"></script>
    <script src="<?= base_url('assets/pages/sweet-alert.init.js') ?>"></script>

    <!-- App js -->
    <script src="<?= base_url('assets/js/app.js') ?>"></script>

</body>

</html>