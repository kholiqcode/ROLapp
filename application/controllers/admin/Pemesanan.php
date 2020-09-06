<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Pemesanan extends CI_Controller
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
        $res = $this->pemesanan->getPesanan();

        if (empty($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Daftar Pemesanan',
            'content' => $res,
            'page'  => 'pemesanan'
        ];

        $this->load->view('layout/app', $data);
    }

    public function tambah()
    {
        if (!$this->input->post(null, true)) {

            $data = [
                'title' => 'Tambah Pemesanan',
                'page'  => 'tambah_pemesanan'
            ];
            $this->load->view('layout/app', $data);
            return;
        } else {
            $input    = $this->input->post(null, true);
        }
        
        if (!$this->validatePost()) {
            
            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }

        $harga = $this->pemesanan->getHarga($input['nama_tutor']);

        $data = [
            'id_users' => $input['nama_pengguna'],
            'id_tutor' => $input['nama_tutor'],
            'id_pembayaran' => $input['metode_pembayaran'],
            'tanggal' => $input['tanggal'],
            'waktu' => $input['waktu'],
            'total' => $harga,
            'status' => $input['status']
        ];
        
        if ($this->pemesanan->postPesanan($data)) {
            $res = [
                'status' => true,
                'message' => 'Data berhasil disimpan!'
            ];
        } else {
            $res = [
                'status' => false,
                'message' => 'Oops! Gagal menambahkan data'
            ];
        }
        echo json_encode($res);
    }

    public function edit($pid)
    {

        if (!$_POST) {

            $res = $this->pemesanan->getPesanan($pid);

            $data = [
                'title' => 'Edit Pemesanan',
                'content' => $res,
                'page'  => 'edit_pemesanan'
            ];

            $this->load->view('layout/app', $data);

            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!$this->validate()) {

            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }
        
        if ($this->pemesanan->putPesanan($pid, $input) > 0) {
            $res = [
                'status' => true,
                'message' => 'Data berhasil disimpan!'
            ];
        } else {
            $res = [
                'status' => false,
                'message' => 'Oops! Gagal menambahkan data'
            ];
        }
        echo json_encode($res);
    }

    public function validatePost()
    {
        $validationRules = [
            [
                'field'    => 'nama_pengguna',
                'label'    => 'Users Id',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'nama_tutor',
                'label'    => 'Tutor Id',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'metode_pembayaran',
                'label'    => 'Pembayaran Id',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'tanggal',
                'label'    => 'Tanggal',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'waktu',
                'label'    => 'Waktu',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'status',
                'label'    => 'Status',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'tanggal',
                'label'    => 'Tanggal',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'waktu',
                'label'    => 'Waktu',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'total',
                'label'    => 'Total',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'status',
                'label'    => 'Status',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }
}

/* End of file Pemilih.php */
