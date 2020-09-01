<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Tutor extends CI_Controller
{

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Tutor_model', 'tutor');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function get()
    {
        echo json_encode($this->tutor->getTutor());
    }
}

/* End of file Tutor.php */
