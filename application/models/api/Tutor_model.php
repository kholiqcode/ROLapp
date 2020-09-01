<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Tutor_model extends CI_Model
{

    protected $table = 'tutor';

    public function addTutor($input = null)
    {

        $uid = $this->token->decrypt($input['apikey']);

        $data        = [
            'id_users'        => $uid,
            'id_kategori'        => $input['kid'],
            'nama'        => $input['nama'],
            'alamat'    => $input['alamat'],
            'harga'        => $input['harga'],
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
            $query    = $this->db->where('tutor.id', $input['tid'])->get($this->table)->row_array();
        } else {
            $query    = $this->db->get($this->table)->result_array();
        }

        return $query;
    }

}

/* End of file Tutor_model.php */
