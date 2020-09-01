<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Users_model extends CI_Model {

    protected $table = 'users';

    public function getUsers()
    {
        return $this->db->select('id,nama')->get($this->table)->result_array();
    }
}

/* End of file Users_model.php */