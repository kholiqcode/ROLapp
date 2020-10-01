<div class="content">
    <div class="container-fluid">
        <div class="page-title-box">
            <div class="row align-items-center">
                <div class="col-sm-6">
                    <h4 class="page-title">Daftar Laporan</h4>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Dashboard</a></li>
                        <li class="breadcrumb-item active">Laporan</li>
                    </ol>
                </div>
            </div> <!-- end row -->
        </div>
        <!-- end page-title -->

        <div class="row">
            <div class="col-12">
                <div class="card m-b-30">
                    <div class="card-body">
                        <?= form_open(base_url('admin/laporan'), ['method' => 'POST', 'id' => 'authentication-form']) ?>
                        <div class="row">
                            <div class="col-6">

                                <div class="form-group">
                                    <label>Bulan</label>
                                    <?= form_dropdown(['name' => 'bulan', 'class' => 'form-control'], ['default' => 'Pilih Bulan', '1' => 'Januari', '2' => 'Februari', '3' => 'Maret', '4' => 'April', '5' => 'Mei', '6' => 'Juni', '7' => 'Juli', '8' => 'Agustus', '9' => 'September', '10' => 'Oktober', '11' => 'November', '12' => 'Desember'], 'default') ?>
                                </div>

                                <div class="form-group">
                                    <div>
                                        <button type="submit" class="btn btn-primary btn-lg waves-effect waves-light">
                                            Cari
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-6">
                                <div class="form-group">
                                    <label>Status</label>
                                    <?= form_dropdown(['name' => 'status', 'class' => 'form-control'], ['default' => 'Pilih Status', '0' => 'Belum Dibayar', '1' => 'Dibayar', '2' => 'Diproses', '3' => 'Selesai', '4' => 'Dibatalkan'], 'default') ?>
                                </div>

                                <div class="form-group">
                                    <div>
                                        <a class="btn btn-primary btn-lg waves-effect waves-light" href="<?= base_url('admin/laporan/cetak') ?>"><i class="mdi mdi-printer"></i>Cetak</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <?= form_close() ?>

                        <table id="datatable-buttons" class="table table-striped table-bordered dt-responsive nowrap text-center" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                            <thead>
                                <tr>
                                    <th width="5%">Nomor</th>
                                    <th>Pemesan</th>
                                    <th>Tutor</th>
                                    <th>Pembayaran</th>
                                    <th>Tanggal</th>
                                    <th>Jam</th>
                                    <th>Total</th>
                                    <th>Status</th>
                                </tr>
                            </thead>


                            <tbody>
                                <?php $i = 1;
                                foreach ($content as $row) : ?>
                                    <tr>
                                        <td><?= $i++ ?></td>
                                        <td><?= $row['nama_user'] ?></td>
                                        <td><?= $row['nama_tutor'] ?> </td>
                                        <td><?= $row['metode_pembayaran'] ?> </td>
                                        <td><?= $row['tanggal'] ?></td>
                                        <td><?= $row['waktu'] ?></td>
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
                                    </tr>
                                <?php endforeach ?>
                            </tbody>

                            <tfoot>
                                <tr>
                                    <td colspan="6">Total Pendapatan</td>
                                    <td colspan="2">Rp <?= $pendapatan ?></td>
                                </tr>
                            </tfoot>
                        </table>

                    </div>
                </div>
            </div> <!-- end col -->
        </div> <!-- end row -->

    </div>
    <!-- container-fluid -->

</div>