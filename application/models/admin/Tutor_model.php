<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Tutor_model extends CI_Model {

    protected $table = 'tutor';

    public function getTutor()
    {
        return $this->db->select('id,nama')->get($this->table)->result_array();
    }
}

/* End of file Tutor_model.php */
