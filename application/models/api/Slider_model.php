<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Slider_model extends CI_Model {

    protected $table = 'slider';

    public function getSlider()
    {

        $query    = $this->db->get($this->table)->result_array();

        return $query;
    }

}

/* End of file Slider_model.php */
