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

        $foto =  base64_decode($input['foto']);

        $imageName = url_title($uid, '-', true) . date('YmdHis').'.jpg';

        $success = file_put_contents('./public/images/users/'.url_title($uid, '-', true) . date('YmdHis').'.jpg',$foto);
        
        if ($success) {
            $data        = [
                'nama'        => $input['nama'],
                'jenis_kelamin'    => $input['jenis_kelamin'],
                'alamat'    => $input['alamat'],
                'telepon'        => $input['telepon'],
                'foto'        => $imageName
            ];
        } else {
            return false;
        }

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

    public function uploadFoto($fieldName, $fileName)
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
            return false;
        }
    }
}

/* End of file Users_model.php */
