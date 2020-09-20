<?php

defined('BASEPATH') or exit('No direct script access allowed');

class NotFound extends CI_Controller
{

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
    }


    public function index()
    {
        $this->output->set_status_header('404');
        $this->load->view('errors/404/404.php');
    }
}

/* End of file 404.php */
