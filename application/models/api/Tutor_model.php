<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Tutor_model extends CI_Model
{

    protected $table = 'tutor';

    public function addTutor($input = null)
    {

        $uid = $this->token->decrypt($input['apikey']);
        
        if (!empty($_FILES) && $_FILES['foto']['name'] !== '') {

            $imageName    = url_title($uid, '-', true) . date('YmdHis');

            $upload = $this->uploadFoto('foto', $imageName);

            if ($upload) {
                $foto    = $upload['file_name'];
            } else {
                return false;
            }
        }

        $data        = [
            'id_users'        => $uid,
            'id_kategori'        => $input['kid'],
            'nama'        => $input['nama'],
            'alamat'    => $input['alamat'],
            'harga'        => $input['harga'],
            'total_trx'        => 0,
            'total_rate'        => 0,
            'rate_avg'        => 0,
            'role'        => 0,
            'foto'      => $foto
        ];

        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function putTutor($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $data        = [
            'nama'        => $input['nama'],
            'alamat'    => $input['alamat'],
            'harga'        => $input['harga'],
        ];

        $this->db->where('id_users', $uid)->where('id', $input['tid'])->update($this->table, $data);

        return $this->db->affected_rows();
    }

    public function deleteTutor($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $this->db->where('id_users', $uid)->where('id', $input['tid'])->delete($this->table);

        return $this->db->affected_rows();
    }

    public function getTutor($input = null)
    {

        if (isset($input['tid']) && !empty($input['tid'])) {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.role', 0)->where('tutor.id', $input['tid'])->join('users', 'tutor.id_users=users.id')->get($this->table)->row_array();
        } else if (isset($input['kid']) && !empty($input['kid'])) {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.role', 0)->where('tutor.id_kategori', $input['kid'])->join('users', 'tutor.id_users=users.id')->get($this->table)->result_array();
        } else {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.role', 0)->join('users', 'tutor.id_users=users.id')->get($this->table)->result_array();
        }

        return $query;
    }

    public function getTutorSaya($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        if (isset($input['tid']) && !empty($input['tid'])) {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.id_users', $uid)->where('tutor.role', 0)->where('tutor.id', $input['tid'])->join('users', 'tutor.id_users=users.id')->get($this->table)->row_array();
        } else {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.id_users', $uid)->where('tutor.role', 0)->join('users', 'tutor.id_users=users.id')->get($this->table)->result_array();
        }

        return $query;
    }

    public function uploadFoto($fieldName, $fileName)
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
            return false;
        }
    }
}

/* End of file Tutor_model.php */
