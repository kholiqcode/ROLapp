<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Daftar_model extends CI_Model
{

    protected $table = 'users';

    public function postDaftar($data)
    {
        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }
}

/* End of file Daftar_model.php */
