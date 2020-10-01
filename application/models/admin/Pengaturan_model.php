<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Pengaturan_model extends CI_Model {

    protected $table = 'admin';

    public function getAdmin($username = null)
    {
        if (isset($username) || !empty($username)) {
            $query    = $this->db->select('id,nama,email,no_wa')->where('username', $username)->get($this->table)->row_array();
        } else {
            $query    = $this->db->select('id,nama,email,no_wa')->get($this->table)->result_array();
        }

        return $query;
    }

    public function putAdmin($username = null, $data = null)
    {
        $this->db->where('username', $username)->update($this->table, $data);

        return $this->db->affected_rows();
    }

    public function ubahPassword($username = null, $data = null)
    {
        $this->db->where('username', $username)->update($this->table, $data);

        return $this->db->affected_rows();
    }

}

/* End of file Pengaturan_model.php */
