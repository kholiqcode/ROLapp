<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Kategori_model extends CI_Model
{

    protected $table = 'kategori';

    public function getKategori($input = null)
    {

        if (isset($input['kid']) && !empty($input['kid'])) {
            $query    = $this->db->select('kategori.*, COUNT(tutor.id) as total_tutor')->join('tutor', 'tutor.id_kategori = kategori.id', 'left')->where('kategori.id', $input['kid'])->get($this->table)->row_array();
        } else {
            $query    = $this->db->select('kategori.*, COUNT(tutor.id) as total_tutor')->join('tutor', 'tutor.id_kategori = kategori.id', 'left')->group_by('kategori.nama')->get($this->table)->result_array();
        }

        return $query;
    }
    
}

/* End of file Kategori_model.php */
