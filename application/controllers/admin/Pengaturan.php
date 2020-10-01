<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Pengaturan extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Pengaturan_model', 'pengaturan');
        if (!is_logged()) {
            redirect(base_url('admin/pengaturan'));
            return;
        }
    }
    

    public function index()
    {
        $res = $this->pengaturan->getAdmin($this->session->userdata('username'));

        if (!isset($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Pengaturan',
            'content' => $res,
            'page'  => 'pengaturan'
        ];

        $this->load->view('layout/app', $data);
    }

    public function edit()
    {
        $input    = $this->input->post(null, true);

        if (!$this->validate()) {

            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'.validation_errors(null, null)
            ];
            echo json_encode($res);
            return;
        }
        
        if ($this->pengaturan->putAdmin($this->session->userdata('username'), $input) > 0) {
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

    public function ubah_password()
    {
        $input    = $this->input->post(null, true);

        if (!$this->validateUbahPassword()) {
            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }

        $data['password'] = $input['password_baru'];
        
        if ($this->pengaturan->ubahPassword($this->session->userdata('username'), $data) > 0) {
            $res = [
                'status' => true,
                'message' => 'Password berhasil diubah!'
            ];
        } else {
            $res = [
                'status' => false,
                'message' => 'Oops! Gagal menambahkan data'
            ];
        }
        echo json_encode($res);
    }

    public function validateUbahPassword()
    {
        $validationRules = [
            [
                'field'    => 'password_lama',
                'label'    => 'Password Lama',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'password_baru',
                'label'    => 'Password Baru',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'konfirmasi_password',
                'label'    => 'Konfirmasi Password',
                'rules'    => 'trim|required|matches[password_baru]'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'nama',
                'label'    => 'Nama pengguna',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'email',
                'label'    => 'Email Pengguna',
                'rules'    => 'trim|required|valid_email'
            ],
            [
                'field'    => 'no_wa',
                'label'    => 'Jenis Kelamin',
                'rules'    => 'required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

}

/* End of file Pengaturan.php */
