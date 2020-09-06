<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Dashboard</h4>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Stexo</a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>
                </div>
            </div>
            <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">

            <div class="col-sm-6 col-xl-3">
                <div class="card">
                    <div class="card-heading p-4">
                        <div class="mini-stat-icon float-right">
                            <i class="mdi mdi-cube-outline bg-primary  text-white"></i>
                        </div>
                        <div>
                            <h5 class="font-16">Total Pemesanan</h5>
                        </div>
                        <h3 class="mt-4"><?= number_format($content['total_dpt']) ?></h3>
                        <div class="progress mt-4" style="height: 4px;">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        <!-- <p class="text-muted mt-2 mb-0">Persentase<span class="float-right">100%</span>
                                    </p> -->
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-xl-3">
                <div class="card">
                    <div class="card-heading p-4">
                        <div class="mini-stat-icon float-right">
                            <i class="mdi mdi-briefcase-check bg-success text-white"></i>
                        </div>
                        <div>
                            <h5 class="font-16">Total Pendapatan</h5>
                        </div>
                        <h3 class="mt-4"><?= number_format($content['suara_masuk']) ?></h3>
                        <div class="progress mt-4" style="height: 4px;">
                            <div class="progress-bar bg-warning" role="progressbar" style="width: <?= ($content['suara_masuk']/$content['total_dpt']*100).'%' ?>" aria-valuenow="68" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        <!-- <p class="text-muted mt-2 mb-0">Persentase<span class="float-right">68%</span>
                        </p> -->
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-xl-3">
                <div class="card">
                    <div class="card-heading p-4">
                        <div class="mini-stat-icon float-right">
                            <i class="mdi mdi-tag-text-outline bg-warning text-white"></i>
                        </div>
                        <div>
                            <h5 class="font-16">Total Pengguna</h5>
                        </div>
                        <h3 class="mt-4"><?= number_format($content['belum_voting']) ?></h3>
                        <div class="progress mt-4" style="height: 4px;">
                            <div class="progress-bar bg-danger" role="progressbar" style="width: <?= ($content['belum_voting']/$content['total_dpt']*100).'%' ?>" aria-valuenow="82" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        <!-- <p class="text-muted mt-2 mb-0">Persentase<span class="float-right">82%</span>
                        </p> -->
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-xl-3">
                <div class="card">
                    <div class="card-heading p-4">
                        <div class="mini-stat-icon float-right">
                            <i class="mdi mdi-buffer bg-danger text-white"></i>
                        </div>
                        <div>
                            <h5 class="font-16">Total Tutor </h5>
                        </div>
                        <h3 class="mt-4"><?= number_format(($content['suara_masuk'] / $content['total_dpt']) * 100) ?>%</h3>
                        <div class="progress mt-4" style="height: 4px;">
                            <div class="progress-bar bg-danger" role="progressbar" style="width: <?= ($content['suara_masuk']/$content['total_dpt']*100).'%' ?>%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        <!-- <p class="text-muted mt-2 mb-0">Persentase<span class="float-right">82%</span>
                                    </p> -->
                    </div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-xl-4">
                <div class="card m-b-30">
                    <div class="card-body">
                        <h4 class="mt-0 header-title mb-4">Perolehan Data Sementara</h4>
                        <div class="friends-suggestions">
                            <!-- <?php //foreach ($content['kandidat'] as $row) : ?>
                                <a href="#" class="friends-suggestions-list">
                                    <div class="border-bottom position-relative">
                                        <div class="float-left mb-0 mr-3">
                                            <img src="<?php //echo base_url('assets/images/users/user-2.jpg') ?>" alt="" class="rounded-circle thumb-md">
                                        </div>
                                        <div class="float-right mt-2 pt-1">
                                            <h5><?php //echo number_format(($row['jmlsuara_kandidat'] / $content['total_dpt']) * 100) ?>%</h5>
                                        </div>

                                        <div class="desc">
                                            <h5 class="font-14 mb-1 pt-2 text-dark"><?php //echo $row['nama_kandidat'] ?></h5>
                                            <p class="text-muted"><?php //echo $row['nama_prodi'] ?></p>
                                        </div>
                                    </div>
                                </a>
                            <?php //endforeach ?> -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- end col -->

            <div class="col-xl-8">
                <div class="card m-b-30">
                    <div class="card-body">

                        <h4 class="mt-0 header-title">Grafik Perolehan Suara</h4>
                        <p class="sub-title d-inline-block text-truncate w-100">A very simple pie chart with
                            label
                            interpolation to show percentage instead of the actual data series
                            value.
                        </p>

                        <ul class="list-inline widget-chart m-t-20 m-b-15 text-center">
                            <li class="list-inline-item">
                                <h5>3654</h5>
                                <p class="text-muted">Marketplace</p>
                            </li>
                            <li class="list-inline-item">
                                <h5>954</h5>
                                <p class="text-muted">Last week</p>
                            </li>
                            <li class="list-inline-item">
                                <h5>8462</h5>
                                <p class="text-muted">Last Month</p>
                            </li>
                        </ul>

                        <div id="pie-chart"></div>
                    </div>

                </div>
            </div>
        </div>
        <!-- end col -->
    </div>
    <!-- end row -->

</div>