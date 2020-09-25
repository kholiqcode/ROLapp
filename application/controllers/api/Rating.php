<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Rating extends RestController {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Rating_model','rating');
    }
    

    public function index_post()
    {

        $data     = $this->input->post(null, true);

        if (!$this->validate()) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 404);
        }

        $res = $this->rating->addRating($data);
        
        if ($res) {
            $this->response([
                'status' => true,
                'message' => 'Berhasil mengirim rating'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 400);
        }
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'pid',
                'label'    => 'Pemesanan Id',
                'rules'    => 'trim|required|integer'
            ],
            [
                'field'    => 'rate',
                'label'    => 'Rating',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function check_post(){
        $input     = $this->input->post(null, true);

        $res = $this->rating->getStatus($input);

        if ($res) {
            $this->response([
                'status' => true,
                'message' => 'Anda sudah memberikan penilaian'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Belum ada penilaian'
            ], 200);
        }
    }

}

/* End of file Rating.php */
