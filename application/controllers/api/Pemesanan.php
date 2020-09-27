<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Pemesanan extends RestController {

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Pemesanan_model','pemesanan');
    }

    public function index_get()
    {
        $input     = $this->input->get(null, true);

        $res = $this->pemesanan->getPemesanan($input);
        
        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan list pemesanan'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Data tidak ditemukan'
            ], 400);
        }
    }

    public function index_post()
    {

        $data     = $this->input->post(null, true);

        if (!$this->validate()) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 400);
        }

        if(!$this->pemesanan->validateUsers($data)){
            $this->response([
                'status' => false,
                'message' => 'Tidak dapat memesan tutor anda'
            ], 200);
        }

        $res = $this->pemesanan->addPesanan($data);
        
        if ($res) {
            $this->response([
                'status' => true,
                'message' => 'Pemesanan berhasil'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 400);
        }
    }

    public function jadwal_get()
    {

        $input     = $this->input->get(null, true);

        $res = $this->pemesanan->jadwalTutor($input);

        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan list jadwal'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Data tidak ditemukan'
            ], 400);
        }
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'tid',
                'label'    => 'Tutor Id',
                'rules'    => 'trim|required|integer'
            ],
            [
                'field'    => 'pid',
                'label'    => 'Pembayaran Id',
                'rules'    => 'trim|required|integer'
            ],
            [
                'field'     => 'tanggal',
                'label'        => 'Tanggal',
                'rules'        => 'trim|required'
            ],
            [
                'field'     => 'waktu',
                'label'        => 'Waktu',
                'rules'        => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function status_put()
    {
        $input = $this->put();

        
        if (!$this->validatePutStatus($input)) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 404);
        }

        if ($this->pemesanan->putStatus($input) > 0) {
            $this->response([
                'status' => true,
                'message' => 'Status berhasil diperbarui'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 404);
        }
    }

    public function validatePutStatus($input)
    {
        $validationRules = [
            [
                'field'    => 'pid',
                'label'    => 'Pemesanan Id',
                'rules'    => 'trim|required|integer'
            ],
            [
                'field'     => 'status',
                'label'        => 'Status',
                'rules'        => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules, $input);

        return $res;
    }

}

/* End of file Pemesanan.php */
