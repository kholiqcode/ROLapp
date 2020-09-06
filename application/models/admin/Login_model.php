<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Login_model extends CI_Model {

    protected $table = 'admin';

    public function userlogin($input)
    {
        $this->db->where('username', $input['username']);
        return $this->db->get($this->table)->row_array();
    }

}

/* End of file Login_model.php */
