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
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Pengguna</a></li>
                        <li class="breadcrumb-item active">Edit Pengguna</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">

                        <?= form_open_multipart(base_url('admin/pengguna/edit/'.$content['id']), ['method' => 'POST', 'id' => 'authentication-form']) ?>

                        <div class="form-group">
                            <label>Nama Pengguna</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama', 'class' => 'form-control','value' => $content['nama'],'id' => 'nama-pengguna', 'placeholder' => 'Masukkan nama pengguna', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Email Pengguna</label>
                            <div>
                                <?= form_input(['type' => 'email', 'name' => 'email', 'class' => 'form-control','value' => $content['email'], 'id' => 'email-pengguna', 'placeholder' => 'Masukkan email pengguna', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Jenis Kelamin</label>
                            <?= form_dropdown(['name' => 'jenis_kelamin', 'class' => 'form-control','value' => $content['jenis_kelamin'], 'id' => 'jenis-kelamin', 'required' => true], ['default' => 'Pilih Jenis Kelamin', 'L' => 'Laki-laki', 'P' => 'Perempuan'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label>Telepon Pengguna</label>
                            <div>
                                <?= form_input(['type' => 'tel', 'name' => 'telepon', 'class' => 'form-control','value' => $content['telepon'], 'id' => 'telepon-pengguna', 'placeholder' => 'Masukkan nomor telepon pengguna', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Alamat</label>
                            <div>
                                <?= form_textarea(['type' => 'text', 'name' => 'alamat', 'class' => 'form-control','value' => $content['alamat'], 'id' => 'alamat-pengguna', 'rows' => '3', 'placeholder' => 'Masukkan alamat pengguna', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Foto</label>
                            <div>
                                <?= form_upload(['name' => 'foto', 'class' => 'form-control', 'id' => 'image-upload', 'placeholder' => 'Upload foto tutor']) ?>
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
                                            <img src="<?= empty($content['foto']) ? base_url('assets/images/users/user-6.jpg') : base_url('public/images/users/' . $content['foto']) ?>" alt="" id="image-preview" class="avatar-xl rounded-circle" width="200px" />
                                            <h5 class="mt-2 mb-0" id="name-preview"><?= $content['nama'] ?></h5>
                                            <h6 class="text-muted font-weight-normal mt-2 mb-4" id="prodi-preview"><?= $content['telepon'] ?>
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