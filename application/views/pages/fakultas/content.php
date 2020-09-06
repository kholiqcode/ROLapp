<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Daftar Fakultas</h4>
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
            <div class="col-12 mb-3">
                <!-- <button type="button" class="btn btn-primary btn-xl mr-3 float-right" id="btn-tambah">Tambah</button> -->
                <div class="text-center">
                    <!-- <p class="text-muted">Center modal</p> -->
                    <!-- Small modal -->
                    <button type="button" class="btn btn-primary waves-effect waves-light float-right" data-toggle="modal" data-target=".bs-example-modal-center">Tambah</button>
                </div>

                <div class="modal fade bs-example-modal-center" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title mt-0">Tambah Fakultas</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <?= form_open(base_url('admin/fakultas/tambah'), ['method' => 'POST', 'id' => 'authentication-form']) ?>
                                <div class="form-group">
                                    <label>Nama Fakultas</label>
                                    <div>
                                        <?= form_input(['type' => 'text', 'name' => 'nama_fakultas', 'class' => 'form-control', 'placeholder' => 'Masukkan nama fakultas', 'required' => true]) ?>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div>
                                        <button type="submit" class="btn btn-primary waves-effect waves-light">
                                            Submit
                                        </button>
                                        <button type="reset" class="btn btn-danger waves-effect m-l-5" data-dismiss="modal">
                                            Cancel
                                        </button>
                                    </div>
                                </div>
                                <?= form_close() ?>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="card m-b-30">
                    <div class="card-body">

                        <h4 class="mt-0 header-title">Buttons example</h4>
                        <!-- <p class="sub-titleplugins">The Buttons extension for DataTables
                                        provides a common set of options, API methods and styling to display
                                        buttons on a page that will interact with a DataTable. The core library
                                        provides the based framework upon which plug-ins can built.
                                    </p> -->
                        <!-- <button type="button" class="btn btn-primary btn-xl mr-1 float-right">Tambah</button> -->

                        <table id="datatable-buttons" class="table table-striped table-bordered dt-responsive nowrap text-center" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                            <thead>
                                <tr>
                                    <th width="5%">Nomor</th>
                                    <th>Nama</th>
                                    <th>Jumlah Mahasiswa</th>
                                    <th>Aksi</th>
                                </tr>
                            </thead>


                            <tbody>
                                <?php $i = 1;
                                foreach ($content['fakultas'] as $row) : ?>
                                    <tr>
                                        <td><?= $i++ ?></td>
                                        <td><?= $row['nama_fakultas'] ?></td>
                                        <td><?= $row['jmlmahasiswa_fakultas'] ?> </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm mr-1">Edit</button>
                                            <button type="button" class="btn btn-danger btn-sm">Delete</button>
                                        </td>
                                    </tr>
                                <?php endforeach ?>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div> <!-- end col -->
        </div> <!-- end row -->

    </div>
    <!-- container-fluid -->

</div>