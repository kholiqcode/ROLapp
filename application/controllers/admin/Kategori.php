<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Kategori extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Kategori_model', 'kategori');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {
        $res = $this->kategori->getKategori();

        if (!isset($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Daftar Kategori',
            'content' => $res,
            'page'  => 'kategori'
        ];

        $this->load->view('layout/app', $data);
    }

    public function tambah()
    {
        if (!$this->input->post(null, true)) {

            $data = [
                'title' => 'Tambah Kategori',
                'page'  => 'tambah_kategori'
            ];
            $this->load->view('layout/app', $data);
            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!empty($_FILES) && $_FILES['foto_kategori']['name'] !== '') {
            $imageName    = url_title($input['nama_kategori'], '-', true) . '-' . date('YmdHis');
            $upload        = $this->kategori->upload_image('foto_kategori', $imageName);
            if ($upload) {
                $data = [
                    'nama' => $input['nama_kategori'],
                    'foto' => $upload['file_name'],
                ];
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
            // $this->load->view('admin/tambah_kandidat', $input);
            $res = [
                'status' => false,
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'
            ];
            echo json_encode($res);
            return;
        }

        if ($this->kategori->addKategori($data)) {
            $res = [
                'status' => true,

                'message' => 'Kategori berhasil ditambahkan!'
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
            $res = $this->kategori->getKategori($tid);
            
            $data = [
                'title' => 'Edit Kategori',
                'content' => $res,
                'page'  => 'edit_kategori'
            ];
            
            $this->load->view('layout/app', $data);

            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!empty($_FILES) && $_FILES['foto']['name'] !== '') {
            $imageName    = url_title($tid, '-', true) . '-' . date('YmdHis');
            $upload        = $this->kategori->upload_image('foto', $imageName);
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
        
        if ($this->kategori->putKategori($tid, $input) > 0) {
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
        $this->kategori->deleteKategori($id);
        redirect(base_url('admin/kategori'));
    }

    public function get()
    {
        echo json_encode($this->kategori->getKategori());
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'nama',
                'label'    => 'Nama Kategori',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

}

/* End of file Kategori.php */
