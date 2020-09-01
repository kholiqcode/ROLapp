<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Edit Pemesanan</h4>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Dashboard</a></li>
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Pemesanan</a></li>
                        <li class="breadcrumb-item active">Edit Pemesanan</li>
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

                        <?= form_open(base_url('admin/pemesanan/edit/'.$content['id']), ['method' => 'POST', 'id' => 'authentication-form']) ?>
                        
                        <div class="form-group">
                            <label>Nama Pemesan</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama_pemesan', 'class' => 'form-control', 'value' => $content['nama_user'], 'placeholder' => 'Masukkan nama pemesan', 'required' => true, 'disabled' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nama Tutor</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'nama_tutor', 'class' => 'form-control', 'value' => $content['nama_tutor'], 'placeholder' => 'Masukkan nama tutor', 'required' => true, 'disabled' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="example-date-input">Date</label>
                            <div>
                                <?= form_input(['type' => 'date', 'name' => 'tanggal', 'id' => 'example-date-input', 'class' => 'form-control', 'value' => $content['tanggal'], 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="example-time-input">Jam</label>
                            <div>
                                <?= form_input(['type' => 'time', 'name' => 'waktu', 'id' => 'example-time-input', 'class' => 'form-control', 'value' => $content['waktu'], 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Total</label>
                            <div>
                                <?= form_input(['type' => 'text', 'name' => 'total', 'class' => 'form-control', 'value' => $content['total'], 'placeholder' => 'Masukkan total harga', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Status</label>
                            <?= form_dropdown(['name' => 'status', 'class' => 'form-control', 'required' => true], ['default' => 'Pilih Status', '0' => 'Belum Dibayar', '1' => 'Dibayar', '2' => 'Diproses', '3' => 'Selesai', '4' => 'Dibatalkan'], $content['status']) ?>
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