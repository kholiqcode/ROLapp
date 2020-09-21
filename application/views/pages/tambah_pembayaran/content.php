<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title"><?= $title ?></h4>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Dashboard</a></li>
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Pembayaran</a></li>
                        <li class="breadcrumb-item active">Tambah Metode Pembayaran</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">

                        <?= form_open_multipart(base_url('admin/pembayaran/tambah'), ['method' => 'POST', 'id' => 'authentication-form']) ?>

                        <div class="form-group">
                            <label>Metode Pembayaran</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'metode_pembayaran', 'class' => 'form-control', 'id' => 'metode-pembayaran', 'placeholder' => 'Masukkan metode pembayaran', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nomor Rekening</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nomor_rekening', 'class' => 'form-control', 'id' => 'nomor-rekening', 'placeholder' => 'Masukkan nomor rekening', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <div>
                                <button type="submit" class="btn btn-primary waves-effect waves-light">
                                    Submit
                                </button>
                                <button type="reset" class="btn btn-danger waves-effect m-l-5">
                                    Cancel
                                </button>
                            </div>
                        </div>
                        <?= form_close() ?>

                    </div>
                </div>
            </div> <!-- end col -->

            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">
                        <h4 class="mt-0 header-title">Preview</h4>
                        <div class="row justify-content-center">
                            <div class="col-xl-6">
                                <div class="card">
                                    <div class="card-body pb-0">
                                        <div class="text-center mt-3">
                                            <img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" id="image-preview" class="avatar-xl rounded-circle" width="200px" />
                                            <h5 class="mt-2 mb-0" id="name-preview">Donny Pratama</h5>
                                            <h6 class="text-muted font-weight-normal mt-2 mb-4" id="prodi-preview">Teknik
                                                Informatika
                                            </h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- end col -->
        </div> <!-- end row -->

    </div>
    <!-- container-fluid -->

</div>