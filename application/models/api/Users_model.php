<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Users_model extends CI_Model
{

	protected $table = 'users';

	public function getUsers($input = null)
	{
		$uid = $this->token->decrypt($input['apikey']);

		$this->db->where('id', $uid)->get($this->table);

		return $this->db->result_array();
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
}

/* End of file Users_model.php */
