<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Pembayaran_model extends CI_Model
{

    protected $table = 'pembayaran';

    public function getPembayaran()
    {
        return $this->db->select('id,metode_pembayaran,nomor_rekening')->where('status', 1)->get($this->table)->result_array();
    }

    public function addPembayaran($data = [])
    {
        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function deletePembayaran($tid){
        $this->db->where('id', $tid)->delete($this->table);

        return $this->db->affected_rows();
    }
}

/* End of file Pembayaran_model.php */
