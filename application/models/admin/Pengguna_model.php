<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Pengguna_model extends CI_Model {

    protected $table = 'users';

    public function getUsers($uid = null)
    {
        if (isset($uid) || !empty($uid)) {
            $query    = $this->db->select('id,nama,email,jenis_kelamin,telepon,alamat,foto')->where('id', $uid)->get($this->table)->row_array();
        } else {
            $query    = $this->db->select('id,nama,email,jenis_kelamin,telepon,alamat,foto')->get($this->table)->result_array();
        }

        return $query;
    }

    public function addPengguna($data = [])
    {
        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    //upload image tutor
    public function upload_image($fieldName, $fileName)
    {
        $config    = [
            'upload_path'        => './public/images/users',
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

/* End of file Users_model.php */