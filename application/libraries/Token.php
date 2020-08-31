<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Token
{
    protected $ci;

    public function __construct()
    {
        $this->ci = &get_instance();
        $this->ci->load->library('encryption');
    }

    public function encrypt($uid)
    {
        $uid = str_pad($uid, 4, 0, STR_PAD_LEFT) . '.' . date('YmdHis');
        $keys = base64_encode($this->ci->encryption->encrypt($uid));
        return $keys;
    }

    public function decrypt($key)
    {
        $keys = $this->ci->encryption->decrypt(base64_decode($key));
        $explode = explode('.', $keys);
        $uid = intval(ltrim($explode[0], '0'));
        return $uid;
    }
}

/* End of file LibraryName.php */
