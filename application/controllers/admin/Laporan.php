<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Laporan extends CI_Controller
{

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Pemesanan_model', 'pemesanan');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {

        $bulan = null;
        $status = null;
        $pendapatan = null;
        if (!$_POST) {

            $res = $this->pemesanan->getLaporan($bulan, $status);

            $pendapatan  = $this->pemesanan->totalPendapatan();
        } else {

            $input    = $this->input->post(null, true);

            if ($input['bulan'] != 'default') {
                $bulan = $input['bulan'];
                $this->session->set_userdata(['bulan' => $bulan]);
                $pendapatan  = $this->pemesanan->totalPendapatan($bulan);
            }
            if ($input['status'] != 'default') {
                $status = $input['status'];
                $this->session->set_userdata(['status' => $status]);
            }

            $res = $this->pemesanan->getLaporan($bulan, $status);
        }


        $data = [
            'title' => 'Daftar Laporan',
            'content' => $res,
            'pendapatan' => $pendapatan,
            'page'  => 'laporan'
        ];
        $this->load->view('layout/app', $data);
    }

    public function cetak()
    {
        $bulan = null;
        $status = null;
        $pendapatan  = $this->pemesanan->totalPendapatan();

        if (!is_null($this->session->userdata('bulan'))) {
            $bulan = $this->session->userdata('bulan');
            $pendapatan  = $this->pemesanan->totalPendapatan($bulan);
            $res = $this->pemesanan->getLaporan($bulan);
        } else if (!is_null($this->session->userdata('status'))) {
            $status = $this->session->userdata('status');
            $res = $this->pemesanan->getLaporan($bulan, $status);
        } else {
            $res = $this->pemesanan->getLaporan();
        }

        $data = [
            'title' => 'Cetak Laporan',
            'content' => $res,
            'pendapatan' => $pendapatan,
        ];

        $this->load->view('pages/cetak_laporan/content', $data);
    }
}

/* End of file Laporan.php */
