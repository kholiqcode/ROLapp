<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Info_model extends CI_Model
{

    protected $table = 'admin';

    public function getInfo($input)
    {
        $uid = $this->token->decrypt($input['apikey']);

        if ($this->db->where('id', $uid)->count_all_results('users') > 0) {
            $this->db->select('no_wa');

            $query = $this->db->get($this->table)->result_array();

            return $query;
        } else {
            return false;
        }
    }
}

/* End of file Info_model.php */
