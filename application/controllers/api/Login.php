<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Login extends RestController {

    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('api/Login_model', 'login');
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
                'message' => 'User berhasil login'
            ], 200);
        } else {
            $this->response([
                'status' => false,
                'message' => 'Email atau password anda salah'
            ], 200);
        }
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'     => 'email',
                'label'        => 'E-Mail',
                'rules'        => 'trim|required|valid_email',
                'errors'    => [
                    'valid_email' => '%s tidak valid'
                ]
            ],
            [
                'field' => 'password',
                'label'    => 'Password',
                'rules'    => 'required|min_length[3]',
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function run($input)
    {

        $query    = $this->login->getLogin($input['email']);
        
        if (is_array($query) && password_verify($input['password'], $query['password'])) {
            // var_dump($query);
            return renewToken($query['id']);
        }

        return false;
    }

}

/* End of file Login.php */
