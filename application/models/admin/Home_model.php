<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Home_model extends CI_Model
{

    protected $table = '';

    public function totalPemesanan()
    {
        $query = $this->db->where('status !=', 0)->get('pemesanan');
        return $query->num_rows();
    }

    public function totalPendapatan()
    {
        $query = $this->db->select_sum('total')->where('status !=', 0)->get('pemesanan')->row_array();
        return $query['total'];
    }

    public function totalTutor()
    {
        $query = $this->db->get('tutor');
        return $query->num_rows();
    }

    public function totalPengguna()
    {
        $query = $this->db->get('users');
        return $query->num_rows();
    }
}

/* End of file Home_model.php */
