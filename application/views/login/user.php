<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>E-Voting - Login</title>

    <!-- Custom fonts for this template-->
    <link href="<?= base_url('assets/css/icons.css') ?>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Sweet Alert -->
    <link href="<?= base_url('assets/plugins/sweet-alert2/sweetalert2.css') ?>" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="<?= base_url('assets/css/bootstrap.custom.css') ?>" rel="stylesheet">
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center mt-5">

            <div class="col-lg-6">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg">
                                <div class="p-5">
                                    <div class="text-center">
                                        <img src="<?= base_url('assets/fonts/vote-yea-solid.svg') ?>" alt="" class="img-responsive rotate-n-15" width="70">
                                        <h1 class="h4 text-gray-900 mb-4 mt-4">Login Pemilih</h1>
                                    </div>
                                    <?= form_open(base_url('user/login'), ['method' => 'POST', 'id' => 'authentication-form', 'class' => 'user m-t-30']) ?>
                                    <div class="form-group">
                                        <?= form_input(['type' => 'text', 'name' => 'username', 'id' => 'username', 'class' => 'form-control form-control-user', 'placeholder' => 'Masukkan username', 'required' => true]) ?>
                                    </div>
                                    <div class="form-group">
                                        <?= form_input(['type' => 'password', 'name' => 'password', 'id' => 'password', 'class' => 'form-control form-control-user', 'placeholder' => 'Masukkan password', 'required' => true]) ?>
                                    </div>
                                    <button class="btn btn-primary btn-user btn-block" type="submit">Login</button>
                                    <?= form_close() ?> <span class="flash-data" data-flashdata=""></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="<?= base_url('assets/js/jquery.min.js') ?>"></script>
    <script src="<?= base_url('assets/js/bootstrap.bundle.min.js') ?>"></script>

    <!-- Sweet-Alert  -->
    <script src="<?= base_url('assets/plugins/sweet-alert2/sweetalert2.min.js') ?>"></script>
    <script src="<?= base_url('assets/pages/sweet-alert.init.js') ?>"></script>

</body>

</html>