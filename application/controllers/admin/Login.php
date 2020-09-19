<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Login extends CI_Controller
{
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Login_model','login');
        if (is_logged()) {
            redirect(base_url('admin/home'));
            return;
        }
    }
    
    public function index()
    {
        if (!$_POST) {
            $input    = $this->getDefaultValues();
        } else {
            $input    = $this->input->post(null, true);
        }

        if (!$this->validate()) {
            
            $data['input']    = $input;
            $data['title']      = 'Admin Login - ROLap';
            $this->load->view('login/admin', $data);
            return;
        }

        $query = $this->login->userlogin($input);

        if (!empty($query) && $input['password'] == $query['password']) {
            $sess_data = [
                'id_admin'        => $query['id'],
                'nama_admin'        => $query['nama'],
                'is_login'    => true,
            ];
            $this->session->set_userdata($sess_data);

            $res = [
                'status' => true,
                'message' => 'Berhasil melakukan login!'
            ];
            echo json_encode($res);
        } else {
            
            $res = [
                'status' => false,
                'message' => 'E-Mail atau Password salah atau akun Anda sedang tidak aktif!'
            ];
            echo json_encode($res);
        }
    }

    public function validate()
    {
        $validationRules = [
            [
                'field'    => 'username',
                'label'    => 'Username',
                'rules'    => 'trim|required'
            ],
            [
                'field'    => 'password',
                'label'    => 'Password',
                'rules'    => 'trim|required'
            ]
        ];

        $res = validateReq($validationRules);

        return $res;
    }

    public function getDefaultValues()
    {
        return [
            'username'        => '',
            'password'    => '',
        ];
    }
}

/* End of file Login.php */
