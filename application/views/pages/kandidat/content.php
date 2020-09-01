<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Daftar Kandidat</h4>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Stexo</a></li>
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Forms</a></li>
                        <li class="breadcrumb-item active">Form Validation</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row justify-content-center">
        <?php foreach ($content['kandidat'] as $row) : ?>
            <div class="col-xl-3">
                <div class="card">
                    <div class="card-body pb-0">
                        <div class="text-center mt-3">
                            <img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" class="avatar-xl rounded-circle" />
                            <h5 class="mt-2 mb-0"><?= $row['nama_kandidat'] ?></h5>
                            <h6 class="text-muted font-weight-normal mt-2 mb-4"><?= $row['nama_prodi'] ?>
                            </h6>
                            <button type="button" class="btn btn-primary btn-sm mr-1">Edit</button>
                            <button type="button" class="btn btn-danger btn-sm">Delete</button>
                            <div class="mt-4 pt-3 border-top">
                                <p class="font-20 mt-0 mb-0 font-weight-bold text-center">
                                    Visi
                                </p>
                                <p class="font-15 mt-0 mb-2 text-left"><?= nl2br(htmlentities($row['visi_kandidat'], ENT_QUOTES, 'UTF-8')) ?></p>
                            </div>
                            <div class="mt-4 pt-3 border-top">
                                <p class="font-20 mt-0 mb-0 font-weight-bold text-center">
                                    Misi
                                </p>
                                <p class="font-15 mt-0 mb-2 text-left"><?= nl2br(htmlentities($row['misi_kandidat'], ENT_QUOTES, 'UTF-8')) ?></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <?php endforeach ?>
            
        </div> <!-- end row -->

    </div>
    <!-- container-fluid -->

</div>