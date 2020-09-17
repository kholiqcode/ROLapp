<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Tutor extends CI_Controller
{

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Tutor_model', 'tutor');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {
        $res = $this->tutor->getTutor();
        
        if (empty($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Daftar Tutor',
            'content' => $res,
            'page'  => 'tutor'
        ];

        $this->load->view('layout/app', $data);
    }

    public function tambah()
    {
        if (!$this->input->post(null, true)) {

            $data = [
                'title' => 'Tambah Tutor',
                'page'  => 'tambah_tutor'
            ];
            $this->load->view('layout/app', $data);
            return;
        } else {
            $input    = $this->input->post(null, true);
        }
        
        if (!empty($_FILES) && $_FILES['foto_tutor']['name'] !== '') {
            $imageName    = url_title($input['nama_pengguna'], '-', true) . '-' . date('YmdHis');
            $upload        = $this->tutor->upload_image('foto_tutor', $imageName);
            if ($upload) {
                $input['foto_tutor']    = $upload['file_name'];
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
            'id_kategori' => $input['nama_kategori'],
            'nama' => $input['nama_tutor'],
            'alamat' => $input['alamat_tutor'],
            'harga' => $input['harga_tutor'],
            'foto' => $input['foto_tutor'],
            'role' => 0
        ];

        if ($this->tutor->addTutor($data)) {
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

    public function delete($id){

        $this->tutor->deleteTutor($id);
        redirect(base_url('admin/tutor'));
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
                'field'    => 'nama_kategori',
                'label'    => 'Kategori Id',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'nama_tutor',
                'label'    => 'nama Tutor',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'alamat_tutor',
                'label'    => 'Alamat',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'harga_tutor',
                'label'    => 'Harga Tutor',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function get()
    {
        echo json_encode($this->tutor->getTutor());
    }
}

/* End of file Tutor.php */
