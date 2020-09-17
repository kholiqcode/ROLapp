<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Kategori_model extends CI_Model {

    protected $table = 'kategori';

    public function getKategori($kid = null)
    {
        if (isset($kid) || !empty($kid) || is_null($kid)) {
            $query    = $this->db->select('id,nama')->get($this->table)->result_array();
        } else {
            $query    = $this->db->select('id,nama')->where('id', $kid)->get($this->table)->result_array();
        }
        
        return $query;
    }

}

/* End of file Kategori_model.php */
