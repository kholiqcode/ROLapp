<?php

defined('BASEPATH') or exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Daftar extends RestController
{

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Daftar_model','daftar');
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

        $key = $this->run($data);
        
        if ($key) {
            $this->response([
                'status' => true,
                'token' => $key,
                'message' => 'User berhasil daftar'
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
                'field'    => 'nama',
                'label'    => 'Nama',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'telepon',
                'label'    => 'Nomor HP',
                'rules'    => 'trim'
            ],
            [
                'field'     => 'email',
                'label'        => 'E-Mail',
                'rules'        => 'trim|required|valid_email|is_unique[users.email]',
                'errors'    => [
                    'is_unique' => 'This %s already'
                ]
            ],
            [
                'field' => 'password',
                'label'    => 'Password',
                'rules'    => 'required|min_length[3]',
            ],
            [
                'field' => 'konfirmasi_password',
                'label'    => 'Konfirmasi Password',
                'rules'    => 'required|matches[password]',
            ],
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function run($input)
    {
        $data        = [
            'nama'        => $input['nama'],
            'email'        => strtolower($input['email']),
            'password'    => password_hash($input['password'], PASSWORD_BCRYPT),
            'telepon'        => $input['telepon'],
        ];

        $uid = $this->daftar->postDaftar($data);
        // var_dump($uid);
        return releaseToken($uid);
    }
}

/* End of file Daftar.php */
