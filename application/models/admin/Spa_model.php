<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Spa_model extends CI_Model
{

    protected $table = 'tutor';

    public function getSpa($sid = null)
    {
        if (isset($sid) || !empty($sid)) {
            $query    = $this->db->select('tutor.id,users.nama as nama_pengguna,tutor.nama,tutor.alamat,tutor.total_trx,tutor.harga,tutor.foto')->where('role', 1)->where('tutor.id', $sid)->join('users','tutor.id_users=users.id')->get($this->table)->row_array();
        } else {
            $query    = $this->db->select('tutor.id,users.nama as nama_pengguna,tutor.nama,tutor.alamat,tutor.total_trx,tutor.harga,tutor.foto')->where('role', 1)->join('users','tutor.id_users=users.id')->get($this->table)->result_array();
        }

        return $query;
    }

    public function addSpa($data = [])
    {
        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function deleteSpa($sid){
        $this->db->where('id', $sid)->delete($this->table);

        return $this->db->affected_rows();
    }

    public function putSpa($sid = null, $data = null)
    {
        $this->db->where('id', $sid)->update($this->table, $data);

        return $this->db->affected_rows();
    }

    //upload image tutor
    public function upload_image($fieldName, $fileName)
    {
        $config    = [
            'upload_path'        => './public/images/katalog',
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
