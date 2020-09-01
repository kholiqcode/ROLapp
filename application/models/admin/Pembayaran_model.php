<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Pembayaran_model extends CI_Model
{

    protected $table = 'pembayaran';

    public function getPembayaran()
    {
        return $this->db->select('id,metode_pembayaran')->where('status', 1)->get($this->table)->result_array();
    }
}

/* End of file Pembayaran_model.php */
