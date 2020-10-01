<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title><?= $title ?></title>
    <meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
    <meta content="Themesdesign" name="author" />
    <link rel="shortcut icon" href="<?= base_url('assets/images/favicon.ico') ?>">

    <link href="<?= base_url('assets/css/bootstrap.min.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/metismenu.min.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/icons.css') ?>" rel="stylesheet" type="text/css">
    <link href="<?= base_url('assets/css/style.css') ?>" rel="stylesheet" type="text/css">

</head>

<body onload="window.print()">
    <div class="content">
        <div class="container-fluid">
            <table id="datatable-buttons" class="table table-striped table-bordered dt-responsive nowrap text-center" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                <thead>
                    <tr>
                        <th colspan="8" align="center">
                            <br><br>
                            PT. ROLAP INDONESIA <br>
                            Laporan Transaksi Aplikasi ROLap  <?php if(is_null($this->session->userdata('bulan'))) echo "Bulan ke-".$this->session->userdata('bulan') ?>
                            <br><br><br>
                        </th>
                    </tr>
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
</body>

<!-- jQuery  -->
<script src="<?= base_url('assets/js/jquery.min.js') ?>"></script>
<script src="<?= base_url('assets/js/bootstrap.bundle.min.js') ?>"></script>
<script src="<?= base_url('assets/js/metismenu.min.js') ?>"></script>
<script src="<?= base_url('assets/js/jquery.slimscroll.js') ?>"></script>
<script src="<?= base_url('assets/js/waves.min.js') ?>"></script>

<!-- App js -->
<script src="<?= base_url('assets/js/app.js') ?>"></script>

</body>

</html>