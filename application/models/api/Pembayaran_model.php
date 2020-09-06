<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Pembayaran_model extends CI_Model {

    protected $table = 'pembayaran';

    public function getPembayaran($input = null){

        if (isset($input['pid']) && !empty($input['pid'])) {
            $query    = $this->db->where('pembayaran.id', $input['pid'])->where('status',1)->get($this->table)->row_array();
        } else {
            $query    = $this->db->where('status',1)->get($this->table)->result_array();
        }

        return $query;
    }
}

/* End of file Pembayaran_model.php */
