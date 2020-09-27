<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Tutor_model extends CI_Model
{

    protected $table = 'tutor';

    public function addTutor($input = null)
    {

        $uid = $this->token->decrypt($input['apikey']);

        $foto =  base64_decode($input['foto']);

        $imageName = url_title($uid, '-', true) . date('YmdHis') . '.jpg';

        $success = file_put_contents('./public/images/katalog/' . url_title($uid, '-', true) . date('YmdHis') . '.jpg', $foto);

        if ($success) {
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
                'foto'      => $imageName
            ];
        } else {
            return false;
        }

        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function putTutor($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        if (!empty($input['foto'])) {
            $foto =  base64_decode($input['foto']);

            $imageName = url_title($uid, '-', true) . date('YmdHis') . '.jpg';

            $success = file_put_contents('./public/images/katalog/' . url_title($uid, '-', true) . date('YmdHis') . '.jpg', $foto);

            if ($success) {
                $data['foto'] = $imageName;
            } else {
                return false;
            }
        }

        $data['nama'] = $input['nama'];
        $data['alamat'] = $input['alamat'];
        $data['harga'] = $input['harga'];

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

    public function getInfoTutor($input = null)
    {

        $uid = $this->token->decrypt($input['apikey']);

        $query    = $this->db->select_sum('pemesanan.total')->where('tutor.id_users', $uid)->where('tutor.role', 0)->join('pemesanan', 'tutor.id=pemesanan.id_tutor')->get($this->table)->result_array();

        return $query;
    }
}

/* End of file Tutor_model.php */
