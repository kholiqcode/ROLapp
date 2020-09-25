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
                        <li class="breadcrumb-item active">Edit Tutor</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">

                        <?= form_open_multipart(base_url('admin/tutor/edit/' . $content['id']), ['method' => 'POST', 'id' => 'authentication-form']) ?>

                        <div class="form-group">
                            <label>Nama Pengguna</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama_pengguna', 'class' => 'form-control', 'id' => 'nama-pengguna', 'value' => $content['nama_pengguna'], 'placeholder' => 'Masukkan nama pengguna', 'required' => true, 'disabled' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nama Tutor</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama_tutor', 'class' => 'form-control', 'id' => 'nama-tutor', 'value' => $content['nama'], 'placeholder' => 'Masukkan nama tutor', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Alamat</label>
                            <div>
                                <?= form_textarea(['type' => 'text', 'name' => 'alamat_tutor', 'class' => 'form-control', 'id' => 'alamat-tutor', 'rows' => '3', 'value' => $content['alamat'], 'placeholder' => 'Masukkan alamat tutor', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Harga</label>
                            <div>
                                <?= form_input(['type' => 'number', 'name' => 'harga_tutor', 'class' => 'form-control', 'id' => 'harga-tutor', 'placeholder' => 'Masukkan harga tutor', 'value' => $content['harga'], 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Foto</label>
                            <div>
                                <?= form_upload(['name' => 'foto_tutor', 'class' => 'form-control', 'id' => 'image-upload', 'placeholder' => 'Upload foto tutor']) ?>
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
                                            <img src="<?= empty($content['foto'])? base_url('assets/images/users/user-6.jpg'):base_url('public/images/katalog/'.$content['foto']) ?>" alt="" id="image-preview" class="avatar-xl rounded-circle" width="200px" />
                                            <h5 class="mt-2 mb-0" id="name-preview"><?= $content['nama'] ?></h5>
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