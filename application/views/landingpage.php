<?php
defined('BASEPATH') or exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Uniocde -->
    <meta charset="utf-8">
    <!--[if IE]>
    <meta http-equiv="X-UA Compatible" content="IE=edge">
    <![endif]-->
    <!-- First Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Pgae Description -->
    <meta name="description" content="Appcraft portfolio Template">
    <!-- Page Kewords -->
    <meta name="keywords" content="Appcraft">
    <!-- Site Author -->
    <meta name="author" content="Appcraft">
    <!-- Title -->
    <title>Home | ROLap</title>
    <!-- Favicon -->
    <link rel="shortcut icon" href="<?= base_url('assets/landing/images/favicon.png')?>">
    <!-- Bootstrap 4 -->
    <link rel="stylesheet" href="<?= base_url('assets/landing/css/bootstrap.min.css')?>" type="text/css">
    <!-- Swiper Slider -->
    <link rel="stylesheet" href="<?= base_url('assets/landing/css/swiper.min.css')?>" type="text/css">
    <!-- Fonts -->
    <link rel="stylesheet" href="<?= base_url('assets/landing/fonts/fontawesome/font-awesome.min.css')?>">
    <!-- OWL Carousel -->
    <link rel="stylesheet" href="<?= base_url('assets/landing/css/owl.carousel.min.css')?>" type="text/css">
    <link rel="stylesheet" href="<?= base_url('assets/landing/css/owl.theme.default.min.css')?>" type="text/css">
    <!-- CSS Animate -->
    <link rel="stylesheet" href="<?= base_url('assets/landing/css/animate.min.css')?>" type="text/css">
    <!-- Style -->
    <link rel="stylesheet" href="<?= base_url('assets/landing/css/style.css')?>" type="text/css">
</head>

<body>
    <!-- Section Preloader -->
    <div id="section-preloader">
        <div class="boxes">
            <div class="box">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
            <div class="box">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
            <div class="box">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
            <div class="box">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>
        <p>LOADING . . .</p>
    </div>
    <!-- /.Section Preloader -->
    <!-- Section Navbar -->
    <nav class="navbar-1 navbar navbar-expand-lg">
        <div class="container navbar-container">
            <a class="navbar-brand" href="index.html"><img src="<?= base_url('assets/landing/images/logo.png')?>" alt="ROLapp"></a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            Home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="#section-features2" class="nav-link scroll-down">Fitur</a>
                    </li>
                    <li class="nav-item">
                        <a href="Contact.html" class="nav-link">Kontak</a>
                    </li>
                </ul>
            </div>
            <a href="#" class="btn-1 shadow1 style3 bgscheme">DOWNLOAD NOW</a>
            <button type="button" id="sidebarCollapse" class="navbar-toggler active" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle navigation">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </button>
        </div>
        <!-- container -->
    </nav>
    <!-- /.Section Navbar -->
    <!-- Section Slider 1 -->
    <div id="section-slider1">
        <div class="swiper-container">
            <div class="swiper-wrapper d-none">
                <!-- Item -->
                <div class="swiper-slide">
                    <div class="slider-content">
                        <div class="container">
                            <div class="row">
                                <div class="left col-12 col-sm-12 col-md-7">
                                    <h1 class="ez-animate" data-animation="fadeInLeft">Relaksasi,Olahraga Aplikasi.</h1>
                                    <p class="ez-animate" data-animation="fadeInLeft">Temukan layanan kebugaran dan tutor olahraga anda.</p>
                                    <ul>
                                        <li><a href="#"><img class="img-fluid ez-animate" src="<?= base_url('assets/landing/images/img-appstore.png')?>" alt="Appcraft" data-animation="fadeInUp"></a></li>
                                        <li><a href="#"><img class="img-fluid ez-animate" src="<?= base_url('assets/landing/images/img-googleplay.png')?>" alt="Appcraft" data-animation="fadeInUp"></a></li>
                                    </ul>
                                </div>
                                <div class="right ez-animate col-12 col-sm-12 col-md-5" data-animation="fadeInRight">
                                    <img class="img-fluid" src="<?= base_url('assets/landing/images/img-1.png')?>" alt="Appcraft">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.Item -->
            </div>
        </div>
    </div>
    <!-- /.Section Slider 1 -->

    <!-- Features Wrap -->
    <div class="features-wrap">
        <!-- Section Features 2 -->
        <div id="section-features2">
            <div class="container">
                <div class="row">
                    <div class="left col-sm-12 col-md-6">
                        <div class="img-container">
                            <img class="circleicon1 ez-animate" src="<?= base_url('assets/landing/images/img-circleicon1.png')?>" alt="Appcraft" data-animation="fadeInUp">
                            <img class="img-fluid ez-animate" src="<?= base_url('assets/landing/images/img-2.png')?>" alt="Appcraft" data-animation="fadeInLeft">
                        </div>
                    </div>
                    <div class="right my-auto col-sm-12 col-md-6">
                        <h6 class="clscheme">01 – Tutor</h6>
                        <h2>Temukan Tutor Olahraga</h2>
                        <p>Fitur ini dapat mempermudah anda dalam menemukan tutor di berbagai olahraga, anda dapat terhubung dengan mereka dan memesan layanan mereka.</p>
                        <a href="#" class="btn-2 shadow1 style3 bgscheme">MULAI SEKARANG</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.Section Features 2 -->
        <!-- Section Features 2 -->
        <div class="section-features2">
            <div class="container">
                <div class="row">
                    <div class="right my-auto col-sm-12 col-md-6">
                        <h6 class="clscheme">02 – Spa</h6>
                        <h2>Temukan Terapis Favorit</h2>
                        <p>Cari tempat atau layanan terapis favorit anda dengan mudah dalam satu aplikasi dan nikmati kemudahan dalam memesan layanan melalui ROLap.</p>
                        <a href="#" class="btn-2 shadow1 style3 bgscheme">MULAI SEKARANG</a>
                    </div>
                    <div class="left col-sm-12 col-md-6">
                        <div class="img-container">
                            <img class="circleicon1 ez-animate" src="<?= base_url('assets/landing/images/img-circleicon2.png')?>" alt="Appcraft" data-animation="fadeInUp">
                            <img class="img-fluid ez-animate" src="<?= base_url('assets/landing/images/img-3.png')?>" alt="Appcraft" data-animation="fadeInRight">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.Section Features 2 -->
    </div>
    <!-- /.Features Wrap -->
    <!-- Section APP Screen 1 -->
    <div id="section-appscreen1">
        <div class="container">
            <div class="row">
                <div class="title1 col-12">
                    <h6 class="clscheme">LAYAR APLIKASI</h6>
                    <h2>Bagaimana tampilan aplikasi kami?</h2>
                </div>
            </div>
        </div>
        <div class="container appscreen1">
            <div class="row">
                <div class="owl-carousel owl-theme">
                    <!-- Item -->
                    <div class="item">
                        <img class="img-fluid" src="<?= base_url('assets/landing/images/img-screen1.jpg')?>" alt="Appcraft">
                    </div>
                    <!-- /.Item -->
                    <!-- Item -->
                    <div class="item">
                        <img class="img-fluid" src="<?= base_url('assets/landing/images/img-screen2.jpg')?>" alt="Appcraft">
                    </div>
                    <!-- /.Item -->
                    <!-- Item -->
                    <div class="item">
                        <img class="img-fluid" src="<?= base_url('assets/landing/images/img-screen3.jpg')?>" alt="Appcraft">
                    </div>
                    <!-- /.Item -->
                    <!-- Item -->
                    <div class="item">
                        <img class="img-fluid" src="<?= base_url('assets/landing/images/img-screen4.jpg')?>" alt="Appcraft">
                    </div>
                    <!-- /.Item -->
                    <!-- /.Item -->
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
            </div>
        </div>
    </div>
    <!-- /.Section APP Sreen 1 -->
    <!-- Section Testimonial 1 -->
    <div id="section-testimonial1">
        <div class="container">
            <div class="row">
                <div class="title1 col-12">
                    <h6 class="clscheme">ULASAN PENGGUNA</h6>
                    <h2>Apa yang mereka katakan tentang kami?</h2>
                </div>
            </div>
        </div>
        <div class="container testimonial1">
            <img class="img-fluid bg-testimonial" src="<?= base_url('assets/landing/images/bg-testimonial1.jpg')?>" alt="Appcraft">
            <div class="row">
                <div class="owl-carousel owl-theme">
                    <!-- Item -->
                    <div class="item">
                        <img src="<?= base_url('assets/landing/images/img-testimonial1.png')?>" alt="Appcraft">
                        <h3>Abdul Kholiq</h3>
                        <h4>Pelanggan</h4>
                        <ul>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                        </ul>
                        <p>Wahhh aplikasinya cukup bermanfaat, memudahkan saya untuk mencari layanan pijat!</p>
                    </div>
                    <!-- /.Item -->
                    <!-- Item -->
                    <div class="item">
                        <img src="<?= base_url('assets/landing/images/img-testimonial2.png')?>" alt="Appcraft">
                        <h3>Arya Stark</h3>
                        <h4>Client</h4>
                        <ul>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                        </ul>
                        <p>As part of the classes I teach, I task my students with preparing a lot of presentations. To save time & reduce boredom, I occasionally have only a pick who presents the good work!</p>
                    </div>
                    <!-- /.Item -->
                    <!-- Item -->
                    <div class="item">
                        <img src="<?= base_url('assets/landing/images/img-testimonial3.png')?>" alt="Appcraft">
                        <h3>Arya Stark</h3>
                        <h4>Client</h4>
                        <ul>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                        </ul>
                        <p>As part of the classes I teach, I task my students with preparing a lot of presentations. To save time & reduce boredom, I occasionally have only a pick who presents the good work!</p>
                    </div>
                    <!-- /.Item -->
                    <!-- Item -->
                    <div class="item">
                        <img src="<?= base_url('assets/landing/images/img-testimonial1.png')?>" alt="Appcraft">
                        <h3>Arya Stark</h3>
                        <h4>Client</h4>
                        <ul>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                        </ul>
                        <p>As part of the classes I teach, I task my students with preparing a lot of presentations. To save time & reduce boredom, I occasionally have only a pick who presents the good work!</p>
                    </div>
                    <!-- /.Item -->
                    <!-- Item -->
                    <div class="item">
                        <img src="<?= base_url('assets/landing/images/img-testimonial2.png')?>" alt="Appcraft">
                        <h3>Arya Stark</h3>
                        <h4>Client</h4>
                        <ul>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                            <li><i class="fa fa-star"></i></li>
                        </ul>
                        <p>As part of the classes I teach, I task my students with preparing a lot of presentations. To save time & reduce boredom, I occasionally have only a pick who presents the good work!</p>
                    </div>
                    <!-- /.Item -->
                </div>
            </div>
        </div>
    </div>
    <!-- /.Section Testimonial 1 -->
    <!-- Section Subscribe 1 -->
    <div id="section-subscribe1">
        <div class="container">
            <div class="row">
                <div class="title1 col-12">
                    <h6 class="clscheme">NEWSLETTER</h6>
                    <h2>Berlangganan newsletter kami</h2>
                </div>
                <div class="form col-12 ez-animate" data-animation="fadeInUp">
                    <form action="#" id="SubscribeForm">
                        <input type="email" name="yourEmail" placeholder="Enter your email address" required="">
                        <button type="submit" class="shadow1 bgscheme">SUBSCRIBE SEKARANG</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- /.Section Subscribe 1 -->
    <!-- Section Download 1 -->
    <div id="section-download1">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1>Download Hari Ini</h1>
                    <p>dan nikmati berbagai promo menarik</p>
                    <ul>
                        <li>
                            <a href="#">
                                <img class="img-fluid ez-animate" src="<?= base_url('assets/landing/images/img-appstore.png')?>" alt="Appcraft" data-animation="fadeInUp">
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img class="img-fluid ez-animate" src="<?= base_url('assets/landing/images/img-googleplay.png')?>" alt="Appcraft" data-animation="fadeInUp">
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- /.Section Download 1 -->
    <!-- Section Footer -->
    <div id="section-footer">
        <div class="container">
            <div class="footer-widget">
                <div class="row">
                    <div class="left col-md-6">
                        <a href="index.html"><img src="<?= base_url('assets/images/logo-rolap.png') ?>" alt="Appcraft"></a>
                    </div>
                    <div class="right col-md-6">
                        <div class="social-links">
                            <a href="#"><i class="fa fa-google-plus fa-lg"></i></a>
                            <a href="#"><i class="fa fa-twitter fa-lg"></i></a>
                            <a href="#"><i class="fa fa-instagram fa-lg"></i></a>
                            <a href="#"><i class="fa fa-behance fa-lg"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-copyright container-fluid ">
            <div class="col-12">
                <p>© 2020 Copyrights <a href="https://themeforest.net/user/puricreative/portfolio">PuriCreative</a></p>
            </div>
        </div>
    </div>
    <!-- /.Section Footer -->

    <!-- Javascript Files -->
    <script src="<?= base_url('assets/landing/js/jquery.min.js') ?>"></script>
    <!-- Bootstrap -->
    <script src="<?= base_url('assets/landing/js/bootstrap.min.js') ?>"></script>
    <!-- Swiper Slider -->
    <script src="<?= base_url('assets/landing/js/swiper.min.js') ?>"></script>
    <!-- OWL Carousel -->
    <script src="<?= base_url('assets/landing/js/owl.carousel.min.js') ?>"></script>
    <!-- Waypoint -->
    <script src="<?= base_url('assets/landing/js/jquery.waypoints.min.js') ?>"></script>
    <!-- Easy Waypoint -->
    <script src="<?= base_url('assets/landing/js/easy-waypoint-animate.js') ?>"></script>
    <!-- Scripts -->
    <script src="<?= base_url('assets/landing/js/scripts.js') ?>"></script>
    <!-- Carousel Features 1 -->
    <script src="<?= base_url('assets/landing/js/carousel-features1.js') ?>"></script>
    <!-- Carousel App Screen 1 -->
    <script src="<?= base_url('assets/landing/js/carousel-appscreen1.js') ?>"></script>
    <!-- Carousel Testimonial 1 -->
    <script src="<?= base_url('assets/landing/js/carousel-testimonial1.js') ?>"></script>

</body>

</html>