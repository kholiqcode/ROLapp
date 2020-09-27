<?php

defined('BASEPATH') or exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Spa extends RestController
{


    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Spa_model', 'spa');
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

        if ($this->spa->addSpa($input)) {
            $this->response([
                'status' => true,
                'message' => 'Berhasil menambahkan spa'
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

        if ($this->spa->putSpa($input) > 0) {
            $this->response([
                'status' => true,
                'message' => 'Spa berhasil diperbarui'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 404);
        }
    }

    public function delete_post()
    {
        $input = $this->post();

        if (!$this->validateDelete($input)) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 404);
        }

        if ($this->spa->deleteSpa($input) > 0) {
            $this->response([
                'status' => true,
                'message' => 'Berhasil menghapus spa'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 400);
        }
    }

    public function index_get()
    {

        $input     = $this->input->get(null, true);

        $res = $this->spa->getSpa($input);

        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan list spa'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Data tidak ditemukan'
            ], 400);
        }
    }

    public function spa_saya_get()
    {

        $input     = $this->input->get(null, true);

        $res = $this->spa->getSpaSaya($input);

        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan list spa'
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
                'field'    => 'sid',
                'label'    => 'Spa Id',
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
                'field'    => 'sid',
                'label'    => 'Spa Id',
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

    public function info_get()
    {
        $input     = $this->input->get(null, true);

        $res = $this->spa->getInfoSpa($input);

        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan info spa'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Data tidak ditemukan'
            ], 400);
        }
    }
}

/* End of file Tutor.php */
