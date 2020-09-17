<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Tutor_model extends CI_Model
{

    protected $table = 'tutor';

    public function getTutor($tid = null)
    {
        if (isset($tid) || !empty($tid) || is_null($tid)) {
            $query    = $this->db->select('id,nama,alamat,total_trx,harga,foto')->where('role', 0)->get($this->table)->result_array();
        } else {
            $query    = $this->db->select('id,nama,alamat,total_trx,harga,foto')->where('role', 0)->where('id', $tid)->get($this->table)->result_array();
        }

        return $query;
    }

    public function addTutor($data = [])
    {
        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function deleteTutor($tid){
        $this->db->where('id', $tid)->delete($this->table);

        return $this->db->affected_rows();
    }

    //upload image tutor
    public function upload_image($fieldName, $fileName)
    {
        $config    = [
            'upload_path'        => './public/images/tutor',
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

/* End of file Tutor_model.php */
