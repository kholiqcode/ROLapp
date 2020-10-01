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
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Slider</a></li>
                        <li class="breadcrumb-item active">Tambah Slider</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">

                        <?= form_open_multipart(base_url('admin/slider/tambah'), ['method' => 'POST', 'id' => 'authentication-form']) ?>

                        <div class="form-group">
                            <label>Judul</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'judul', 'class' => 'form-control', 'id' => 'judul', 'placeholder' => 'Masukkan judul gambar', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Deskripsi</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'deskripsi', 'class' => 'form-control', 'id' => 'gambar', 'placeholder' => 'Masukkan deskripsi gambar']) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Gambar</label>
                            <div>
                                <?= form_upload(['name' => 'gambar', 'class' => 'form-control', 'id' => 'image-upload', 'placeholder' => 'Upload gambar slider', 'required' => true]) ?>
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
                            <div class="text-center mt-3">
                                <img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" id="image-preview" class="avatar-xl" width="200px" />
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- end col -->
        </div> <!-- end row -->

    </div>
    <!-- container-fluid -->

</div>