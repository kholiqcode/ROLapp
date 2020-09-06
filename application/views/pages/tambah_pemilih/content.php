<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Form Pemilih</h4>
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

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">

                        <h4 class="mt-0 header-title">Validation type</h4>
                        <p class="sub-title">Parsley is a javascript form validation
                            library. It helps you provide your users with feedback on their form
                            submission before sending it to your server.</p>

                        <?= form_open(base_url('admin/pemilih/tambah'), ['method' => 'POST', 'id' => 'authentication-form']) ?>
                        <div class="form-group">
                            <label>Fakultas</label>
                            <?= form_dropdown(['name' => 'nama_fakultas', 'class' => 'form-control', 'id' => 'select-fakultas', 'required' => true], ['default' => 'Pilih Fakultas'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label>Prodi</label>
                            <?= form_dropdown(['name' => 'nama_prodi', 'class' => 'form-control', 'id' => 'select-prodi', 'required' => true], ['default' => 'Pilih Prodi'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label>NIM</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nim_pemilih', 'class' => 'form-control', 'placeholder' => 'Masukkan nim pemilih', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nama</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama_pemilih', 'class' => 'form-control', 'placeholder' => 'Masukkan nama pemilih', 'required' => true]) ?>
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