<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Pembayaran extends CI_Controller {

    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Pembayaran_model', 'pembayaran');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }

    public function get()
    {
        echo json_encode($this->pembayaran->getPembayaran());
    }

}

/* End of file Pembayaran.php */
