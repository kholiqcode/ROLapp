<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Pengguna extends CI_Controller {

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Pengguna_model', 'pengguna');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {
        $res = $this->pengguna->getUsers();

        if (!isset($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Daftar Pengguna',
            'content' => $res,
            'page'  => 'pengguna'
        ];

        $this->load->view('layout/app', $data);
    }


    public function tambah()
    {
        if (!$this->input->post(null, true)) {

            $data = [
                'title' => 'Tambah Pengguna',
                'page'  => 'tambah_pengguna'
            ];
            $this->load->view('layout/app', $data);
            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!empty($_FILES) && $_FILES['foto']['name'] !== '') {
            $imageName    = url_title($input['nama'], '-', true) . '-' . date('YmdHis');
            $upload        = $this->pengguna->upload_image('foto', $imageName);
            if ($upload) {
                $input['foto']    = $upload['file_name'];
            } else {
                $res = [
                    'status' => false,
                    'message' => 'Oops! Gagal mengupload gambar'
                ];
                echo json_encode($res);
                return;
            }
        }

        if (!$this->validatePost()) {
            // $this->load->view('admin/tambah_kandidat', $input);
            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi. '.validation_errors(null, null)
            ];
            echo json_encode($res);
            return;
        }

        $input['password'] = password_hash($input['password'], PASSWORD_BCRYPT);

        if ($this->pengguna->addPengguna($input)) {
            $res = [
                'status' => true,
                'message' => 'Data berhasil disimpan!'
            ];
        } else {
            $res = [
                'status' => false,
                'message' => 'Oops! Gagal menambahkan data '
            ];
        }
        echo json_encode($res);
    }

    public function edit($uid)
    {
        if (!$_POST) {
            $res = $this->pengguna->getUsers($uid);
            
            $data = [
                'title' => 'Edit Pengguna',
                'content' => $res,
                'page'  => 'edit_pengguna'
            ];

            $this->load->view('layout/app', $data);

            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!empty($_FILES) && $_FILES['foto']['name'] !== '') {
            $imageName    = url_title($uid, '-', true) . '-' . date('YmdHis');
            $upload        = $this->pengguna->upload_image('foto', $imageName);
            if ($upload) {
                $input['foto']    = $upload['file_name'];
            } else {
                $res = [
                    'status' => false,
                    'message' => 'Oops! Gagal mengupload gambar'
                ];
                echo json_encode($res);
                return;
            }
        }

        if (!$this->validate()) {

            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }
        
        if ($this->pengguna->putPengguna($uid, $input) > 0) {
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

    public function delete($uid)
    {
        $this->pengguna->deletePengguna($uid);
        redirect(base_url('admin/pengguna'));
    }

    public function get()
    {
        echo json_encode($this->pengguna->getUsers());
    }


    public function validatePost()
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
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'password',
                'label'    => 'Password',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'jenis_kelamin',
                'label'    => 'Jenis Kelamin',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'telepon',
                'label'    => 'Telepon',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'alamat',
                'label'    => 'Alamat',
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
                'field'    => 'nama',
                'label'    => 'Nama pengguna',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'email',
                'label'    => 'Email Pengguna',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'jenis_kelamin',
                'label'    => 'Jenis Kelamin',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'telepon',
                'label'    => 'Telepon',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'alamat',
                'label'    => 'Alamat',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

}

/* End of file Users.php */
