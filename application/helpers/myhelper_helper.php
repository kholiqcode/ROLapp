<?php

/**
 * Membuat status dinamis disetiap return
 * @param [type] $pesan
 * @param [type] $status
 * @return void
 */

function status($pesan = "Terjadi kesalahan", $status = false, $data = null)
{
    return [
        'status' => $status,
        'data' => $data,
        'pesan' => $pesan
    ];
}

function validateReq($validationRules = [], $data = null)
{
    $CI = &get_instance();
    $CI->load->library('form_validation');

    if(!is_null($data) && !empty($data)){
        $CI->form_validation->set_data($data);
    }
    
    $CI->form_validation->set_rules($validationRules);

    return $CI->form_validation->run();
}

function releaseToken($uid)
{
    $CI = &get_instance();
    $CI->table = 'keys';
    $CI->load->library('Token');
    $key = $CI->token->encrypt($uid);

    $keys = [
        'id_users' => $uid,
        'key' => $key
    ];
    // var_dump($key);
    $CI->db->insert($CI->table, $keys);
    if ($CI->db->insert_id()) {
        return $key;
    }

    return false;
}

function renewToken($uid)
{
    $CI = &get_instance();
    $CI->table = 'keys';
    $CI->load->library('Token');
    $key = $CI->token->encrypt($uid);
    $data = [
        'key' => $key,
        'updated_at' => date('Y-m-d H:i:s')
    ];

    // var_dump($uid);
    if ($CI->login->updateKeys($uid, $data) > 0) {
        return $key;
    }

    return false;
}
