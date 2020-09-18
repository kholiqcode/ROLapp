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
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Pemesanan</a></li>
                        <li class="breadcrumb-item active">Tambah Pemesanan</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-lg-6">
                <div class="card m-b-30">
                    <div class="card-body">

                        <?= form_open(base_url('admin/pemesanan/tambah'), ['method' => 'POST', 'id' => 'authentication-form']) ?>

                        <div class="form-group">
                            <label>Pengguna</label>
                            <?= form_dropdown(['name' => 'nama_pengguna', 'class' => 'form-control', 'id' => 'select-pengguna', 'required' => true], ['default' => 'Pilih Pengguna'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label>Tutor</label>
                            <?= form_dropdown(['name' => 'nama_tutor', 'class' => 'form-control', 'id' => 'select-tutor', 'required' => true], ['default' => 'Pilih Tutor'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label>Metode Pembayaran</label>
                            <?= form_dropdown(['name' => 'metode_pembayaran', 'class' => 'form-control', 'id' => 'select-pembayaran', 'required' => true], ['default' => 'Pilih Pembayaran'], 'default') ?>
                        </div>

                        <div class="form-group">
                            <label for="example-date-input">Tanggal</label>
                            <div>
                                <?= form_input(['type' => 'date', 'name' => 'tanggal', 'id' => 'example-date-input', 'class' => 'form-control', 'required' => true]) ?>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="example-time-input">Jam</label>
                            <div>
                                <?= form_input(['type' => 'time', 'name' => 'waktu', 'id' => 'example-time-input', 'class' => 'form-control', 'required' => true]) ?>
                            </div>
                        </div>

                        <!-- <div class="form-group">
                            <label>Total</label>
                            <div>
                                <?php //form_input(['type' => 'text', 'name' => 'total', 'id' => 'total', 'class' => 'form-control', 'placeholder' => 'Masukkan total harga', 'required' => true]) ?>
                            </div>
                        </div> -->

                        <div class="form-group">
                            <label>Status</label>
                            <?= form_dropdown(['name' => 'status', 'class' => 'form-control', 'required' => true], ['default' => 'Pilih Status', '0' => 'Belum Dibayar', '1' => 'Dibayar', '2' => 'Diproses', '3' => 'Selesai', '4' => 'Dibatalkan'], 'default') ?>
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