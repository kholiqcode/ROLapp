<?php
defined('BASEPATH') or exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<title>404 Not Found | ROLap</title>
	<meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
	<meta content="Themesdesign" name="author" />
	<link rel="shortcut icon" href="assets/images/favicon.ico">

	<link href="<?= base_url('assets/css/bootstrap.min.css')?>" rel="stylesheet" type="text/css">
	<link href="<?= base_url('assets/css/metismenu.min.css')?>" rel="stylesheet" type="text/css">
	<link href="<?= base_url('assets/css/icons.css')?>" rel="stylesheet" type="text/css">
	<link href="<?= base_url('assets/css/style.css')?>" rel="stylesheet" type="text/css">

</head>

<body>

	<!-- Begin page -->
	<div class="error-bg"></div>
	<div class="home-btn d-none d-sm-block">
		<a href="index.html" class="text-white"><i class="fas fa-home h2"></i></a>
	</div>

	<div class="account-pages">

		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-5 col-md-8">
					<div class="card shadow-lg">
						<div class="card-block">
							<div class="text-center p-3">

								<h1 class="error-page mt-4"><span>404!</span></h1>
								<h4 class="mb-4 mt-5">Sorry, page not found</h4>
								<p class="mb-4">It will be as simple as Occidental in fact, it will Occidental <br> to an English person</p>
								<a class="btn btn-primary mb-4 waves-effect waves-light" href="<?= base_url()?>"><i class="mdi mdi-home"></i> Back to Dashboard</a>
							</div>

						</div>
					</div>

				</div>
			</div>
			<!-- end row -->
		</div>
	</div>
	<!-- END wrapper -->

	<!-- jQuery  -->
	<script src="<?= base_url('assets/js/jquery.min.js')?>"></script>
	<script src="<?= base_url('assets/js/bootstrap.bundle.min.js')?>"></script>
	<script src="<?= base_url('assets/js/metismenu.min.js')?>"></script>
	<script src="<?= base_url('assets/js/jquery.slimscroll.js')?>"></script>
	<script src="<?= base_url('assets/js/waves.min.js')?>"></script>

	<!-- App js -->
	<script src="<?= base_url('assets/js/app.js')?>"></script>

</body>

</html>