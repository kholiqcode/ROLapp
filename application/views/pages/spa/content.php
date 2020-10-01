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
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Spa</a></li>
                        <li class="breadcrumb-item active">Daftar Spa</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-12">
                <div class="card m-b-30">
                    <div class="card-body">
                        <table id="datatable-buttons" class="table table-striped table-bordered dt-responsive nowrap text-center" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                            <thead>
                                <tr>
                                    <th width="5%">Nomor</th>
                                    <th>Nama</th>
                                    <th>Alamat</th>
                                    <th>Harga</th>
                                    <th>Total Transaksi</th>
                                    <th>Aksi</th>
                                </tr>
                            </thead>


                            <tbody>
                                <?php $i = 1;
                                foreach ($content as $row) : ?>
                                    <tr>
                                        <td><?= $i++ ?></td>
                                        <td><?= $row['nama'] ?></td>
                                        <td><?= $row['alamat'] ?> </td>
                                        <td><?= $row['harga'] ?></td>
                                        <td><?= $row['total_trx'] ?></td>
                                        <td>
                                            <a href="<?= base_url('admin/spa/edit/' . $row['id']) ?>"><button type="button" class="btn btn-primary btn-sm mr-1">Edit</button></a>
                                            <a href="<?= base_url('admin/spa/delete/' . $row['id']) ?>"><button type="button" class="btn btn-danger btn-sm">Delete</button></a>
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