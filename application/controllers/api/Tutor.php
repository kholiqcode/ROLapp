<?php

defined('BASEPATH') or exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Tutor extends RestController
{


    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Tutor_model', 'tutor');
    }


    public function index_post()
    {

        $input     = $this->input->post(null, true);

        if (!$this->validatePost()) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 404);
        }

        if ($this->tutor->addTutor($input)) {
            $this->response([
                'status' => true,
                'message' => 'Berhasil menambahkan tutor'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 400);
        }
    }

    public function index_put()
    {
        $input = $this->put();

        
        if (!$this->validatePut($input)) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 404);
        }

        if ($this->tutor->putTutor($input) > 0) {
            $this->response([
                'status' => true,
                'message' => 'Tutor berhasil diperbarui'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 404);
        }
    }

    public function index_delete()
    {
        $input = $this->delete();

        if (!$this->validateDelete($input)) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 404);
        }

        if ($this->tutor->deleteTutor($input) > 0) {
            $this->response([
                'status' => true,
                'message' => 'Berhasil menghapus tutor'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 404);
        }
    }

    public function index_get()
    {

        $input     = $this->input->get(null, true);

        $res = $this->tutor->getTutor($input);

        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan list tutor'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Data tidak ditemukan'
            ], 400);
        }
    }


    public function validateDelete($input = [])
    {
        $validationRules = [
            [
                'field'    => 'tid',
                'label'    => 'Tutor Id',
                'rules'    => 'trim|required|integer'
            ]
        ];

        $res = validateReq($validationRules, $input);

        return $res;
    }

    public function validatePut($input)
    {
        $validationRules = [
            [
                'field'    => 'tid',
                'label'    => 'Tutor Id',
                'rules'    => 'trim|required|integer'
            ],
            [
                'field'     => 'nama',
                'label'        => 'Nama',
                'rules'        => 'trim|required'
            ],
            [
                'field' => 'alamat',
                'label'    => 'Alamat',
                'rules'    => 'trim',
            ],
            [
                'field' => 'harga',
                'label'    => 'Harga',
                'rules'    => 'trim|required|integer',
            ],
        ];

        $res = validateReq($validationRules, $input);

        return $res;
    }

    public function validatePost()
    {
        $validationRules = [
            [
                'field'    => 'kid',
                'label'    => 'Kategori Id',
                'rules'    => 'trim|required|integer'
            ],
            [
                'field'     => 'nama',
                'label'        => 'Nama',
                'rules'        => 'trim|required'
            ],
            [
                'field' => 'alamat',
                'label'    => 'Alamat',
                'rules'    => 'trim',
            ],
            [
                'field' => 'harga',
                'label'    => 'Harga',
                'rules'    => 'trim|required|integer',
            ],
        ];

        $res = validateReq($validationRules);

        return $res;
    }
}

/* End of file Tutor.php */
