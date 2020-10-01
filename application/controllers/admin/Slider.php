<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Slider extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Slider_model', 'slider');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function index()
    {
        $res = $this->slider->getSlider();

        if (!isset($res)) redirect(base_url('admin'));

        $data = [
            'title' => 'Daftar Slider',
            'content' => $res,
            'page'  => 'slider'
        ];

        $this->load->view('layout/app', $data);
    }

    public function tambah()
    {
        if (!$this->input->post(null, true)) {

            $data = [
                'title' => 'Tambah Slider',
                'page'  => 'tambah_slider'
            ];
            $this->load->view('layout/app', $data);
            return;
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!empty($_FILES) && $_FILES['gambar']['name'] !== '') {
            $imageName    = date('YmdHis');
            $upload        = $this->slider->upload_image('gambar', $imageName);
            if ($upload) {
                $data = [
                    'judul' => $input['judul'],
                    'deskripsi' => $input['deskripsi'],
                    'gambar' => $upload['file_name'],
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
                'message' => 'Oops! Terjadi suatu kesalahan saat validasi'.validation_errors(null, null)
            ];
            echo json_encode($res);
            return;
        }

        if ($this->slider->addSlider($data)) {
            $res = [
                'status' => true,

                'message' => 'Slider berhasil ditambahkan!'
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
        $this->slider->deleteSlider($id);
        redirect(base_url('admin/slider'));
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'judul',
                'label'    => 'Judul',
                'rules'    => 'trim|required'
            ], 
            [
                'field'    => 'deskripsi',
                'label'    => 'Deskripsi',
                'rules'    => 'trim'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

}

/* End of file Slider.php */
