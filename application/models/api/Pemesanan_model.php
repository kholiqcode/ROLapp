<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Pemesanan_model extends CI_Model
{

    protected $table = 'pemesanan';

    public function addPesanan($input)
    {
        $uid = $this->token->decrypt($input['apikey']);

        $harga = $this->getHarga($input['tid']);

        if(is_null($harga)) $harga = 0;

        $data        = [
            'id_tutor'        => $input['tid'],
            'id_users'        => $uid,
            'id_pembayaran'        => $input['pid'],
            'tanggal'    => $input['tanggal'],
            'waktu'    => $input['waktu'],
            'status'        => 0,
            'total'    => $harga
        ];

        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function getPemesanan($input = null)
    {
        $uid = $this->token->decrypt($input['apikey']);

        if (isset($input['pid']) && !empty($input['pid'])) {
            $this->db->select('pemesanan.id,pemesanan.id_tutor,pemesanan.id_users,pemesanan.id_pembayaran,tutor.nama,users.jenis_kelamin,users.telepon,tutor.alamat,tutor.total_trx,tutor.rate_avg,tutor.total_rate,tutor.foto,pembayaran.metode_pembayaran,pembayaran.nomor_rekening,pemesanan.tanggal,pemesanan.waktu,pemesanan.status,pemesanan.total');
            $this->db->where('pemesanan.id', $input['pid']);
            $this->db->where('pemesanan.id_users', $uid);
            $this->db->join('tutor', 'tutor.id=pemesanan.id_tutor');
            $this->db->join('users', 'tutor.id_users=users.id');
            $this->db->join('pembayaran', 'pembayaran.id=pemesanan.id_pembayaran')->order_by('pemesanan.status', 'desc');
            $query    = $this->db->get($this->table)->row_array();
        } else {
            $this->db->select('pemesanan.id,pemesanan.id_tutor,pemesanan.id_users,pemesanan.id_pembayaran,tutor.nama,users.telepon,users.jenis_kelamin,tutor.alamat,tutor.total_trx,tutor.rate_avg,tutor.total_rate,tutor.foto,pembayaran.metode_pembayaran,pembayaran.nomor_rekening,pemesanan.tanggal,pemesanan.waktu,pemesanan.status,pemesanan.total');
            $this->db->join('tutor', 'tutor.id=pemesanan.id_tutor');
            $this->db->where('pemesanan.id_users', $uid);
            $this->db->join('users', 'tutor.id_users=users.id');
            $this->db->join('pembayaran', 'pembayaran.id=pemesanan.id_pembayaran');
            $this->db->order_by('pemesanan.status', 'desc');
            $query    = $this->db->get($this->table)->result_array();
        }

        return $query;
    }

    public function getHarga($tid)
    {
        $query = $this->db->select('harga')->where('tutor.id', $tid)->get('tutor')->row_array();
        return $query['harga'];
    }


    public function jadwalTutor($input = null)
    {

        $uid = $this->token->decrypt($input['apikey']);

        if (isset($input['pid']) && !empty($input['pid'])) {
            $this->db->select('pemesanan.id,pemesanan.id_tutor,pemesanan.id_users,pemesanan.id_pembayaran,tutor.nama as nama_tutor,users.nama as nama_user,users.jenis_kelamin,users.telepon,tutor.alamat,tutor.total_trx,tutor.rate_avg,tutor.total_rate,tutor.foto,pembayaran.metode_pembayaran,pemesanan.tanggal,pemesanan.waktu,pemesanan.status,pemesanan.total');
            $query    = $this->db->where('pemesanan.status !=', 0)->where('tutor.id_users', $uid)->where('pemesanan.id', $input['pid'])->join('tutor', 'pemesanan.id_tutor=tutor.id')->join('pembayaran', 'pembayaran.id=pemesanan.id_pembayaran')->join('users', 'pemesanan.id_users=users.id')->order_by('pemesanan.status', 'desc')->get($this->table)->row_array();
        } else {
            $this->db->select('pemesanan.id,pemesanan.id_tutor,pemesanan.id_users,pemesanan.id_pembayaran,tutor.nama as nama_tutor,users.nama as nama_user,users.jenis_kelamin,users.telepon,tutor.alamat,tutor.total_trx,tutor.rate_avg,tutor.total_rate,tutor.foto,pembayaran.metode_pembayaran,pemesanan.tanggal,pemesanan.waktu,pemesanan.status,pemesanan.total');
            $query    = $this->db->where('pemesanan.status !=', 0)->where('tutor.id_users', $uid)->join('tutor', 'pemesanan.id_tutor=tutor.id')->join('pembayaran', 'pembayaran.id=pemesanan.id_pembayaran')->join('users', 'pemesanan.id_users=users.id')->order_by('pemesanan.status', 'desc')->get($this->table)->result_array();
        }

        return $query;
    }
    
}

/* End of file Pemesanan_model.php */
