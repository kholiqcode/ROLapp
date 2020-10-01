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

    <?php $this->load->view('pages/' . $page . '/css.php'); ?>


    <link href="<?= base_url('assets/css/bootstrap.min.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/metismenu.min.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/icons.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/style.css') ?>" rel="stylesheet" type="text/css">

</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <?php $this->load->view('layout/_topbar.php'); ?>
        <!-- Top Bar End -->

        <!-- ========== Left Sidebar Start ========== -->
        <?php $this->load->view('layout/_sidebar.php'); ?>
        <!-- Left Sidebar End -->

        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="content-page">
            <!-- Start content -->
            <?php $this->load->view('pages/' . $page . '/content.php'); ?>
            <!-- content -->

            <footer class="footer">
                © <?= date('Y') ?> ROLAP <span class="d-none d-sm-inline-block"> - Dikembangkan dengan <i class="mdi mdi-heart text-danger"></i> by ROLap</span>.
            </footer>

        </div>
        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    <script src="<?= base_url('assets/js/jquery.min.js') ?>"></script>
    <script src="<?= base_url('assets/js/bootstrap.bundle.min.js') ?>"></script>
    <script src="<?= base_url('assets/js/metismenu.min.js') ?>"></script>
    <script src="<?= base_url('assets/js/jquery.slimscroll.js') ?>"></script>
    <script src="<?= base_url('assets/js/waves.min.js') ?>"></script>

    <!-- Dynamic JS  -->
    <?php $this->load->view('pages/' . $page . '/js.php'); ?>

    <!-- App js -->
    <script src="<?= base_url('assets/js/app.js') ?>"></script>

</body>

</html>