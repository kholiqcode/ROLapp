<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Kategori_model extends CI_Model
{

    protected $table = 'kategori';

    public function getKategori($kid = null)
    {
        if (isset($kid) || !empty($kid)) {
            $query    = $this->db->select('id,nama,foto')->where('id', $kid)->get($this->table)->row_array();
        } else {
            $query    = $this->db->select('id,nama,foto')->get($this->table)->result_array();
        }

        return $query;
    }

    public function addKategori($data = [])
    {
        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function deleteKategori($tid){
        $this->db->where('id', $tid)->delete($this->table);

        return $this->db->affected_rows();
    }

    public function putKategori($tid = null, $data = null)
    {
        $this->db->where('id', $tid)->update($this->table, $data);

        return $this->db->affected_rows();
    }

    //upload image tutor
    public function upload_image($fieldName, $fileName)
    {
        $config    = [
            'upload_path'        => './public/images/kategori',
            'file_name'            => $fileName,
            'allowed_types'        => 'jpg|gif|png|jpeg|JPG|PNG',
            'max_size'            => 1024,
            'max_width'            => 0,
            'max_height'        => 0,
            'overwrite'            => true,
            'file_ext_tolower'    => true
        ];

        $this->load->library('upload', $config);

        if ($this->upload->do_upload($fieldName)) {
            return $this->upload->data();
        } else {
            $this->session->set_flashdata('image_error', $this->upload->display_errors('', ''));
            return false;
        }
    }
}

/* End of file Kategori_model.php */
