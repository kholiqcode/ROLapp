<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Users_model extends CI_Model
{

    protected $table = 'users';

    public function getUsers($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        return $this->db->select('id,nama,email,jenis_kelamin,telepon,alamat,foto')->where('id', $uid)->get($this->table)->result_array();
    }

    public function putUsers($input)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $data        = [
            'nama'        => $input['nama'],
            'jenis_kelamin'    => $input['jenis_kelamin'],
            'alamat'    => $input['alamat'],
            'telepon'        => $input['telepon'],
        ];

        $this->db->where('id', $uid)->update($this->table, $data);

        return $this->db->affected_rows();
    }

    public function ubahPasswordUsers($input)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $data        = [
            'password'        => password_hash($input['password_baru'], PASSWORD_BCRYPT)
        ];

        $this->db->where('id', $uid)->update($this->table, $data);

        return $this->db->affected_rows();
    }

    public function verifyPasswordLama($input)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $query = $this->db->select('password')->where('id', $uid)->get($this->table)->row_array();

        if (is_array($query) && password_verify($input['password_lama'], $query['password'])) {
            // var_dump($query);
            return true;
        }

        return false;
    }
}

/* End of file Users_model.php */
