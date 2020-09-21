<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Pembayaran extends CI_Controller {

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Pembayaran_model', 'pembayaran');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {
        $res = $this->pembayaran->getPembayaran();

        if (!isset($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Metode Pembayaran',
            'content' => $res,
            'page'  => 'pembayaran'
        ];

        $this->load->view('layout/app', $data);
    }

    public function tambah()
    {
        if (!$this->input->post(null, true)) {

            $data = [
                'title' => 'Tambah Metode Pembayaran',
                'page'  => 'tambah_pembayaran'
            ];
            $this->load->view('layout/app', $data);
            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!$this->validate()) {
            // $this->load->view('admin/tambah_kandidat', $input);
            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }

        $input['status'] = 1;

        if ($this->pembayaran->addPembayaran($input)) {
            $res = [
                'status' => true,
                'message' => 'Metode Pembayaran berhasil ditambahkan!'
            ];
        } else {
            $res = [
                'status' => false,
                'message' => 'Oops! Gagal menambahkan data'
            ];
        }
        echo json_encode($res);
    }

    public function delete($id)
    {
        $this->pembayaran->deletePembayaran($id);
        redirect(base_url('admin/pembayaran'));
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'metode_pembayaran',
                'label'    => 'Metode Pembayaran',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'nomor_rekening',
                'label'    => 'Nomor Rekening',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function get()
    {
        echo json_encode($this->pembayaran->getPembayaran());
    }

}

/* End of file Pembayaran.php */
