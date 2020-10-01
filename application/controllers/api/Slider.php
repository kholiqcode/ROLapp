<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Slider extends RestController {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Slider_model', 'slider');
    }

    public function index_get()
    {

        $input     = $this->input->get(null, true);

        $res = $this->slider->getSlider($input);

        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res,
                'message' => 'Berhasil mendapatkan slider'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Data tidak ditemukan'
            ], 400);
        }
    }

}

/* End of file Slider.php */
