<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Kategori extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
        //Do your magic here
        $this->load->model('admin/Kategori_model', 'kategori');
        if (!is_logged()) {
            redirect(base_url('admin/login'));
            return;
        }
    }
    

    public function get()
    {
        echo json_encode($this->kategori->getKategori());
    }

}

/* End of file Kategori.php */
