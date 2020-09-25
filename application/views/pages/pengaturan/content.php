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
                        <li class="breadcrumb-item active"><a href="javascript:void(0);">Pengaturan</a></li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">
                        <?= form_open_multipart(base_url('admin/pengaturan/edit'), ['method' => 'POST', 'id' => 'authentication-form']) ?>

                        <div class="form-group">
                            <label>Nama Admin</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama', 'class' => 'form-control', 'value' => $content['nama'], 'id' => 'nama-pengguna', 'placeholder' => 'Masukkan nama pengguna', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nomor Whatsapp</label>
                            <div>
                                <?= form_input(['type' => 'tel', 'name' => 'no_wa', 'class' => 'form-control', 'value' => $content['no_wa'], 'id' => 'telepon-pengguna', 'placeholder' => 'Masukkan nomor whatsapp pengguna', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Email Admin</label>
                            <div>
                                <?= form_input(['type' => 'email', 'name' => 'email', 'class' => 'form-control', 'value' => $content['email'], 'id' => 'email-pengguna', 'placeholder' => 'Masukkan email pengguna', 'required' => true]) ?>
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

                        <?= form_open_multipart(base_url('admin/pengaturan/ubah_password'), ['method' => 'POST', 'id' => 'authentication-form-2']) ?>

                        <div class="form-group">
                            <label>Password Lama</label>
                            <div>
                                <?= form_input(['type' => 'password', 'name' => 'password_lama', 'class' => 'form-control', 'id' => 'password-lama', 'placeholder' => 'Masukkan password lama anda', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Password baru</label>
                            <div>
                                <?= form_input(['type' => 'password', 'name' => 'password_baru', 'class' => 'form-control', 'id' => 'password-baru', 'placeholder' => 'Masukkan password baru anda', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Konfirmasi Password</label>
                            <div>
                                <?= form_input(['type' => 'password', 'name' => 'konfirmasi_password', 'class' => 'form-control', 'id' => 'konfirmasi-password', 'placeholder' => 'Konfirmasi password anda', 'required' => true]) ?>
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
        </div> <!-- end row -->

    </div>
    <!-- container-fluid -->

</div>