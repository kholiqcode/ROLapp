<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Info extends RestController {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Info_model', 'info');
    }
    

    public function index_get()
    {

        $input = $this->input->get(null, true);

        $res = $this->info->getInfo($input);

        if ($res) {
            $this->response([
                'status' => true,
                'data' => $res
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi Kesalahan'
            ], 404);
        }
    }

}

/* End of file Info.php */
