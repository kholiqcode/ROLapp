<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Pembayaran extends RestController {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Pembayaran_model','pembayaran');
    }
    

    public function index_get()
    {
        $input     = $this->input->get(null, true);

        $res = $this->pembayaran->getPembayaran($input);
        
        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan list pembayaran'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Data tidak ditemukan'
            ], 400);
        }
    }

}

/* End of file Pembayaran.php */
