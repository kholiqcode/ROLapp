<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Spa_model extends CI_Model
{

    protected $table = 'tutor';

    public function addSpa($input = null)
    {

        $uid = $this->token->decrypt($input['apikey']);

        $data        = [
            'id_users'        => $uid,
            'id_kategori'        => $input['kid'],
            'nama'        => $input['nama'],
            'alamat'    => $input['alamat'],
            'harga'        => $input['harga'],
            'role'        => 1
        ];

        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function putSpa($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $data        = [
            'nama'        => $input['nama'],
            'alamat'    => $input['alamat'],
            'harga'        => $input['harga'],
        ];

        $this->db->where('id_users', $uid)->where('id', $input['sid'])->update($this->table, $data);

        return $this->db->affected_rows();
    }

    public function deleteSpa($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $this->db->where('id_users', $uid)->where('id', $input['sid'])->delete($this->table);

        return $this->db->affected_rows();
    }

    public function getSpa($input = null)
    {

        if (isset($input['sid']) && !empty($input['sid'])) {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.role', 1)->where('tutor.id', $input['sid'])->join('users', 'tutor.id_users=users.id')->get($this->table)->row_array();
        } else if (isset($input['kid']) && !empty($input['kid'])) {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.role', 1)->where('tutor.id_kategori', $input['kid'])->join('users', 'tutor.id_users=users.id')->get($this->table)->result_array();
        } else {
            $query    = $this->db->select('tutor.*,users.telepon,users.jenis_kelamin')->where('tutor.role', 1)->join('users', 'tutor.id_users=users.id')->get($this->table)->result_array();
        }

        return $query;
    }
}

/* End of file Spa_model.php */
