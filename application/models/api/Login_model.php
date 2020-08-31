<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Login_model extends CI_Model {

    protected $table = 'users';

    public function getLogin($email){
        $query    = $this->db->where('email', strtolower($email))->get($this->table)->row_array();

		return $query;
    }

    public function updateKeys($uid,$data){
        $this->table = 'keys';
        $query    = $this->db->where('id_users', strtolower($uid))->update($this->table, $data);

		return $this->db->affected_rows();
    }

}

/* End of file Login_model.php */