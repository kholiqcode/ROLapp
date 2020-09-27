<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Spa extends CI_Controller
{

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Spa_model', 'spa');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {
        $res = $this->spa->getSpa();

        if (!isset($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Daftar Spa',
            'content' => $res,
            'page'  => 'spa'
        ];

        $this->load->view('layout/app', $data);
    }

    public function tambah()
    {
        if (!$this->input->post(null, true)) {

            $data = [
                'title' => 'Tambah Spa',
                'page'  => 'tambah_spa'
            ];
            $this->load->view('layout/app', $data);
            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!empty($_FILES) && $_FILES['foto_spa']['name'] !== '') {
            $imageName    = url_title($input['nama_pengguna'], '-', true) . '-' . date('YmdHis');
            $upload        = $this->spa->upload_image('foto_spa', $imageName);
            if ($upload) {
                $input['foto_spa']    = $upload['file_name'];
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
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }

        $data = [
            'id_users' => $input['nama_pengguna'],
            'nama' => $input['nama_spa'],
            'alamat' => $input['alamat_spa'],
            'harga' => $input['harga_spa'],
            'foto' => $input['foto_spa'],
            'role' => 1
        ];

        if ($this->spa->addSpa($data)) {
            $res = [
                'status' => true,

                'message' => 'Spa berhasil ditambahkan!'
            ];
        } else {
            $res = [
                'status' => false,
                'message' => 'Oops! Gagal menambahkan data'
            ];
        }
        echo json_encode($res);
    }

    public function edit($tid)
    {
        if (!$_POST) {
            $res = $this->spa->getSpa($tid);
            
            $data = [
                'title' => 'Edit Spa',
                'content' => $res,
                'page'  => 'edit_spa'
            ];

            $this->load->view('layout/app', $data);

            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!empty($_FILES) && $_FILES['foto_spa']['name'] !== '') {
            $imageName    = url_title($tid, '-', true) . '-' . date('YmdHis');
            $upload        = $this->spa->upload_image('foto_spa', $imageName);
            if ($upload) {
                $input['foto_spa']    = $upload['file_name'];
            } else {
                $res = [
                    'status' => false,
                    'message' => 'Oops! Gagal mengupload gambar'
                ];
                echo json_encode($res);
                return;
            }
        }else{
            $input['foto_spa'] = '';
        }

        if (!$this->validate()) {

            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }

        $data = [
            'nama' => $input['nama_spa'],
            'alamat' => $input['alamat_spa'],
            'harga' => $input['harga_spa'],
            'foto' => $input['foto_spa']
        ];
        
        if ($this->spa->putSpa($tid, $data) > 0) {
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

    public function delete($id)
    {
        $this->spa->deleteSpa($id);
        redirect(base_url('admin/spa'));
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'nama_spa',
                'label'    => 'Nama Spa',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'alamat_spa',
                'label'    => 'Alamat Spa',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'harga_spa',
                'label'    => 'Harga Spa',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
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
                'field'    => 'nama_spa',
                'label'    => 'Nama Spa',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'alamat_spa',
                'label'    => 'Alamat Spa',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'harga_spa',
                'label'    => 'Harga Spa',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function get()
    {
        echo json_encode($this->spa->getSpa());
    }
}

/* End of file Spa.php */
