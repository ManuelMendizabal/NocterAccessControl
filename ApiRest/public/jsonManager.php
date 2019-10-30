<?php
function getJSONformat($name, $value){
    $res = array($name=>$value);
    return json_encode($res);
}