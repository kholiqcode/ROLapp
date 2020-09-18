<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Home_model extends CI_Model
{

    protected $table = '';

    public function totalPemesanan()
    {
        $query = $this->db->where('status !=', 0)->get('pemesanan');
        return $query->num_rows();
    }
}

/* End of file Home_model.php */
