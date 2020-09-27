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
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Tutor</a></li>
                        <li class="breadcrumb-item active">Tambah Tutor</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">

                        <?= form_open_multipart(base_url('admin/tutor/tambah'), ['method' => 'POST', 'id' => 'authentication-form']) ?>

                        <div class="form-group">
                            <label>Pengguna</label>
                            <?= form_dropdown(['name' => 'nama_pengguna', 'class' => 'form-control', 'id' => 'select-pengguna', 'required' => true], ['default' => 'Pilih Pengguna'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label>Kategori</label>
                            <?= form_dropdown(['name' => 'nama_kategori', 'class' => 'form-control', 'id' => 'select-kategori', 'required' => true], ['default' => 'Pilih Kategori'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label>Nama Tutor</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama_tutor', 'class' => 'form-control', 'id' => 'nama-tutor', 'placeholder' => 'Masukkan nama tutor', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Alamat</label>
                            <div>
                                <?= form_textarea(['type' => 'text', 'name' => 'alamat_tutor', 'class' => 'form-control', 'id' => 'alamat-tutor', 'rows' => '3', 'placeholder' => 'Masukkan alamat tutor', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Harga</label>
                            <div>
                                <?= form_input(['type' => 'number', 'name' => 'harga_tutor', 'class' => 'form-control', 'id' => 'harga-tutor', 'placeholder' => 'Masukkan harga tutor', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Foto</label>
                            <div>
                                <?= form_upload(['name' => 'foto_tutor', 'class' => 'form-control', 'id' => 'image-upload', 'placeholder' => 'Upload foto tutor', 'required' => true]) ?>
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
                                            <h5 class="mt-2 mb-0" id="name-preview"></h5>
                                            <h6 class="text-muted font-weight-normal mt-2 mb-4" id="prodi-preview">
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