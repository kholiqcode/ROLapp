<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Form Kandidat</h4>
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

                        <?= form_open_multipart(base_url('admin/kandidat/tambah'), ['method' => 'POST', 'id' => 'authentication-form']) ?>
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
                                <?= form_input(['type' => 'text', 'name' => 'nim_kandidat', 'class' => 'form-control', 'placeholder' => 'Masukkan nim kandidat', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nama</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama_kandidat', 'class' => 'form-control', 'id' => 'nama-kandidat', 'placeholder' => 'Masukkan nama kandidat', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nomor Urut</label>
                            <div>
                                <?= form_input(['type' => 'number', 'name' => 'nourut_kandidat', 'class' => 'form-control', 'placeholder' => 'Masukkan nomor urut kandidat', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Visi</label>
                            <div>
                                <?= form_textarea(['type' => 'text', 'name' => 'visi_kandidat', 'class' => 'form-control', 'id' => 'visi-kandidat', 'rows' => '3', 'placeholder' => 'Masukkan visi kandidat', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Misi</label>
                            <div>
                                <?= form_textarea(['type' => 'text', 'name' => 'misi_kandidat', 'class' => 'form-control', 'id' => 'misi-kandidat', 'rows' => '3', 'placeholder' => 'Masukkan misi kandidat', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Foto</label>
                            <div>
                                <?= form_upload(['name' => 'foto_kandidat', 'class' => 'form-control', 'id' => 'image-upload', 'placeholder' => 'Upload foto kandidat', 'required' => true]) ?>
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
                                            <div class="mt-4 pt-3 border-top">
                                                <p class="font-20 mt-0 mb-0 font-weight-bold text-center">
                                                    Visi
                                                </p>
                                                <p class="font-15 mt-0 mb-2 text-left" id="visi-preview">Hi I'm Donny Pratama.
                                                    I am user
                                                    experience and user
                                                    interface designer.
                                                    I have been working on UI & UX since last 10 years.</p>
                                            </div>
                                            <div class="mt-4 pt-3 border-top">
                                                <p class="font-20 mt-0 mb-0 font-weight-bold text-center">
                                                    Misi
                                                </p>
                                                <p class="font-15 mt-0 mb-2 text-left" id="misi-preview">Hi I'm Donny Pratama.
                                                    I am user
                                                    experience and user
                                                    interface designer.
                                                    I have been working on UI & UX since last 10 years.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- end col -->
        </div> <!-- end row -->

        <div class="row">
            <div class="col-12">
                <div class="card m-b-30">
                    <div class="card-body">

                        <h4 class="mt-0 header-title">Daftar Kandidat</h4>
                        <p class="text-muted m-b-30">This library allows you to create
                            editable elements on your page. It can be used with any engine
                            (bootstrap, jquery-ui, jquery only) and includes both popup and inline
                            modes. Please try out demo to see how it works.</p>

                        <table class="table table-striped mb-0 text-center">
                            <thead>
                                <tr>
                                    <th style="width: 5%;">No Urut</th>
                                    <th>Nama</th>
                                    <th>Foto</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>
                                        <a href="#" id="inline-username" data-type="text" data-pk="1" data-title="Enter username">Abdul Kholiq</a>
                                    </td>
                                    <td><img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" class="avatar-xl" width="50px" /></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>
                                        <a href="#" id="inline-username" data-type="text" data-pk="1" data-title="Enter username">Abdul Kholiq</a>
                                    </td>
                                    <td><img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" class="avatar-xl" width="50px" /></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>
                                        <a href="#" id="inline-username" data-type="text" data-pk="1" data-title="Enter username">Abdul Kholiq</a>
                                    </td>
                                    <td><img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" class="avatar-xl" width="50px" /></td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>
                                        <a href="#" id="inline-username" data-type="text" data-pk="1" data-title="Enter username">Abdul Kholiq</a>
                                    </td>
                                    <td><img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" class="avatar-xl" width="50px" /></td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>
                                        <a href="#" id="inline-username" data-type="text" data-pk="1" data-title="Enter username">Abdul Kholiq</a>
                                    </td>
                                    <td><img src="<?= base_url('assets/images/users/user-6.jpg') ?>" alt="" class="avatar-xl" width="50px" /></td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div> <!-- end col -->
        </div> <!-- end row -->

    </div>
    <!-- container-fluid -->

</div>