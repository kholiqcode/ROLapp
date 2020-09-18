<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Daftar Pesanan</h4>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Dashboard</a></li>
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Pemesanan</a></li>
                        <li class="breadcrumb-item active">Daftar Pesanan</li>
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
                                    <th>Pemesan</th>
                                    <th>Tutor</th>
                                    <th>Tanggal</th>
                                    <th>Total</th>
                                    <th>Status</th>
                                    <th>Aksi</th>
                                </tr>
                            </thead>


                            <tbody>
                                <?php $i = 1;
                                foreach ($content as $row) : ?>
                                    <tr>
                                        <td><?= $i++ ?></td>
                                        <td><?= $row['nama_user'] ?></td>
                                        <td><?= $row['nama_tutor'] ?> </td>
                                        <td><?= $row['tanggal'] ?></td>
                                        <td><?= $row['total'] ?></td>
                                        <?php

                                        switch ($row['status']) {
                                            case 0:
                                                $status = 'Belum Dibayar';
                                                break;
                                            case 1:
                                                $status = 'Terbayar';
                                                break;
                                            case 2:
                                                $status = 'Diproses';
                                                break;
                                            case 3:
                                                $status = 'Selesai';
                                                break;
                                            case 4:
                                                $status = 'Dibatalkan';
                                                break;
                                            default:
                                                echo "Belum Dibayar";
                                        }
                                        ?>
                                        <td><?= $status ?></td>
                                        <td>
                                            <a href="<?= base_url('admin/pemesanan/edit/' . $row['id']) ?>"><button type="button" class="btn btn-primary btn-sm mr-1">Edit</button></a>
                                            <a href="<?= base_url('admin/pemesanan/delete/' . $row['id']) ?>"><button type="button" class="btn btn-danger btn-sm">Delete</button></a>
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