<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Users extends CI_Controller {

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Users_model', 'users');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function get()
    {
        echo json_encode($this->users->getUsers());
    }

}

/* End of file Users.php */
