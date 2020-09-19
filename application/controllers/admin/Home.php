<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Home extends CI_Controller
{

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Home_model', 'home');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {
        $total_pemesanan = $this->home->totalPemesanan();
        $total_pendapatan = $this->home->totalPendapatan();
        $total_pengguna = $this->home->totalPengguna();
        $total_tutor = $this->home->totalTutor();
        // $belum_voting = $total_dpt > $suara_masuk ? $total_dpt - $suara_masuk : $suara_masuk - $total_dpt;
        // $kandidat = $this->kandidat->get_kandidat();

        if (!isset($total_dpt) || !isset($suara_masuk)) $total_dpt = $suara_masuk = $belum_voting = 0;
        
        $data = [
            'title' => 'Home',
            'content' => [
                'total_pemesanan'     => $total_pemesanan,
                'total_pendapatan'   => $total_pendapatan,
                'total_pengguna'  => $total_pengguna,
                'total_tutor'      => $total_tutor
            ],
            'page' => 'home'
        ];
        $this->load->view('layout/app', $data);
    }

    // public function chart()
    // {
    //     $total_dpt = $this->home->total_dpt();
    //     $kandidat = $this->kandidat->get_kandidat();
    //     foreach ($kandidat as $row) :
    //         $data[] = array(
    //             'nama' => $row['nama_kandidat'],
    //             'persentase' => $row['jmlsuara_kandidat'] / $total_dpt * 100
    //         );
    //     endforeach;
    //     echo json_encode($data);
    // }
}

/* End of file Home.php */
