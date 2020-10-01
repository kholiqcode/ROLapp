<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Pemesanan_model extends CI_Model
{

    protected $table = 'pemesanan';

    // get pesanan
    function getPesanan($pid = null)
    {
        if (isset($pid) && !empty($pid)) {
            $this->db->select('pemesanan.id,pemesanan.id_tutor,pemesanan.id_users,pemesanan.id_pembayaran,tutor.nama as nama_tutor,users.nama as nama_user,users.jenis_kelamin,tutor.alamat,tutor.total_trx,tutor.rate_avg,tutor.total_rate,tutor.foto,pembayaran.metode_pembayaran,pemesanan.tanggal,pemesanan.waktu,pemesanan.status,pemesanan.total');
            $this->db->where('pemesanan.id', $pid);
            $this->db->join('tutor', 'tutor.id=pemesanan.id_tutor');
            $this->db->join('users', 'tutor.id_users=users.id');
            $this->db->join('pembayaran', 'pembayaran.id=pemesanan.id_pembayaran')->order_by('pemesanan.status', 'desc');
            $query    = $this->db->get($this->table)->row_array();
        } else {
            $this->db->select('pemesanan.id,pemesanan.id_tutor,pemesanan.id_users,pemesanan.id_pembayaran,tutor.nama as nama_tutor,users.nama as nama_user,users.jenis_kelamin,tutor.alamat,tutor.total_trx,tutor.rate_avg,tutor.total_rate,tutor.foto,pembayaran.metode_pembayaran,pemesanan.tanggal,pemesanan.waktu,pemesanan.status,pemesanan.total');
            $this->db->join('tutor', 'tutor.id=pemesanan.id_tutor');
            $this->db->join('users', 'tutor.id_users=users.id');
            $this->db->join('pembayaran', 'pembayaran.id=pemesanan.id_pembayaran');
            $this->db->order_by('pemesanan.status', 'desc');
            $query    = $this->db->get($this->table)->result_array();
        }
        return $query;
    }

    function getLaporan($bulan = null,$status = null)
    {
        $this->db->select('pemesanan.id,pemesanan.id_tutor,pemesanan.id_users,pemesanan.id_pembayaran,tutor.nama as nama_tutor,users.nama as nama_user,users.jenis_kelamin,tutor.alamat,tutor.total_trx,tutor.rate_avg,tutor.total_rate,tutor.foto,pembayaran.metode_pembayaran,pemesanan.tanggal,pemesanan.waktu,pemesanan.status,pemesanan.total');
        if (!is_null($bulan)) $this->db->where('MONTH(pemesanan.created_at)', $bulan);
        if (!is_null($status)) $this->db->where('pemesanan.status', $status);
        $this->db->where('pemesanan.status !=', 0);
        $this->db->join('tutor', 'tutor.id=pemesanan.id_tutor');
        $this->db->join('users', 'tutor.id_users=users.id');
        $this->db->join('pembayaran', 'pembayaran.id=pemesanan.id_pembayaran');
        $this->db->order_by('pemesanan.status', 'desc');
        $query    = $this->db->get($this->table)->result_array();
        return $query;
    }

    public function totalPendapatan($bulan = null)
    {
        $this->db->select_sum('total');
        if (!is_null($bulan)) $this->db->where('MONTH(created_at)', $bulan);
        $this->db->where('status !=', 0);
        $query = $this->db->where('status !=', 0)->get($this->table)->row_array();
        return $query['total'];
    }

    public function putPesanan($pid = null, $data = null)
    {
        $this->db->where('id', $pid)->update($this->table, $data);

        return $this->db->affected_rows();
    }

    public function deleteTutor($pid)
    {
        $this->db->where('id', $pid)->delete($this->table);

        return $this->db->affected_rows();
    }

    public function postPesanan($data)
    {
        $this->db->insert($this->table, $data);
        return $this->db->insert_id();
    }

    public function getHarga($tid)
    {
        $query = $this->db->select('harga')->where('tutor.id', $tid)->get('tutor')->row_array();
        return $query['harga'];
    }
}

/* End of file Pemilih_model.php */
