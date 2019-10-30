<?php
function checkCredentials(){
    $invalidKey='invalid secure key';
    $secureKey = '^STp2P$gturU4QPtG2HnTYzxLbwfMQ$H*tq@K8e5!vLSHG!mtJZUTv4rbeKjabu@FYXKnV7PzWh@fKea9Kdb5EQPjr';
    if(isset($_GET['key'])){
        //echo $_GET['key'];
        if($_GET['key']!=$secureKey){
            echo json_encode(getJSONformat("response",$invalidKey));
            return false; 
        }
    }else{
        echo json_encode(getJSONformat("response",$invalidKey));
        return false; 
    }
    return true;
}    
