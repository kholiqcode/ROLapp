<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Users extends RestController {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();
        $this->load->model('api/Users_model', 'user');
    }

    public function index_get()
    {

        $input = $this->input->get(null, true);

        $res = $this->user->getUsers($input);

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

    public function index_put()
    {
        $input = $this->put();

        
        if (!$this->validatePut($input)) {
            $this->response([
                'status' => false,
                'message' => validation_errors(null, null)
            ], 404);
        }

        if ($this->user->putUsers($input) > 0) {
            $this->response([
                'status' => true,
                'message' => 'Users berhasil diperbarui'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Terjadi kesalahan'
            ], 404);
        }
    }

    public function validatePut($input)
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
                'rules'    => 'trim|required',
            ],
            [
                'field' => 'jenis_kelamin',
                'label'    => 'Jenis Kelamin',
                'rules'    => 'trim|required',
            ],
            [
                'field'    => 'telepon',
                'label'    => 'Telepon',
                'rules'    => 'trim|required|integer'
            ],
        ];

        $res = validateReq($validationRules, $input);

        return $res;
    }
}

/* End of file Users.php */