<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Rating_model extends CI_Model
{

    protected $table = 'rating';

    public function addRating($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $data        = [
            'id_pemesanan'        => $input['pid'],
            'rate'        => $input['rate']
        ];

        if ($this->db->where('id_pemesanan', $input['pid'])->count_all_results($this->table) > 0) {
            return false;
        }

        if ($this->db->where('id', $input['pid'])->where('id_users', $uid)->count_all_results('pemesanan') == 0) {
            return false;
        }

        $this->db->insert($this->table, $data);
        $query = $this->db->insert_id();

        $tid = $this->getTID($uid,$input['pid']);

        $dataTutor        = [
            'total_rate'        => $this->sumRating($tid),
            'rate_avg'    => $this->avgRating($tid)
        ];

        $this->db->where('id', $tid)->update('tutor', $dataTutor);

        return $query;
    }

    public function sumRating($tid)
    {
        $this->db->select('pemesanan.id_tutor')->select_sum('rate')->where('tutor.id', $tid)->join('pemesanan', 'rating.id_pemesanan=pemesanan.id')->join('tutor', 'tutor.id=pemesanan.id_tutor')->group_by('pemesanan.id_tutor');

        $query = $this->db->get($this->table)->row_array();

        return $query['rate'];
    }

    public function avgRating($tid)
    {
        $this->db->select('pemesanan.id_tutor')->select_avg('rate')->where('tutor.id', $tid)->join('pemesanan', 'rating.id_pemesanan=pemesanan.id')->join('tutor', 'tutor.id=pemesanan.id_tutor')->group_by('pemesanan.id_tutor');

        $query = $this->db->get($this->table)->row_array();

        return $query['rate'];
    }

    public function getTID($uid,$pid)
    {
        $this->db->select('id_tutor')->where('id', $pid)->where('id_users', $uid);

        $query = $this->db->get('pemesanan')->row_array();

        return $query['id_tutor'];
    }

    public function getStatus($input)
    {
        $uid = $this->token->decrypt($input['apikey']);

        if($this->db->where('pemesanan.id', $input['pid'])->where('pemesanan.id_users', $uid)->where('pemesanan.status', 3)->count_all_results('pemesanan') > 0){
            if ($this->db->where('pemesanan.id', $input['pid'])->where('pemesanan.id_users', $uid)->join('pemesanan','pemesanan.id=rating.id_pemesanan')->count_all_results($this->table) > 0) {
                return true; //sudah menilai
            }else{
                return false;
            }
        }else{
            return true;
        }
    }
}

/* End of file Rating_model.php */
