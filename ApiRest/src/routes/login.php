<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
//GET Login
$app->get('/api/login', function(Request $request, Response $response){
    try{
        //echo '1223';
        $user = $request->getQueryParam('user');
        $password = $request->getQueryParam('password');
        $NFCCode = $request->getQueryParam('NFCCode');
        if($user != null && $password != null){
            $where = "where user='$user' and password='$password'";
        }
        if($NFCCode != null){
            $where .= (strlen($where)>0?" or ": " where ");
            $where .= "nfcCode='$NFCCode'";
        }
        $sql = "select * from users $where";
        $db = new db();
        $db = $db->connect();
        $resultado = $db->query($sql);
        if($resultado->rowCount() > 0){
            $users = $resultado->fetchAll(PDO::FETCH_OBJ);
            echo json_encode($users);
        }else{
            echo getJSONformat("response","incorrectUserPassword");
        }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo getJSONformat("error",$e->getMessage());
    }
});
$app->get('/api/admlogin', function(Request $request, Response $response){
    try{
        //echo '1223';
        $user = $request->getQueryParam('user');
        $password = $request->getQueryParam('password');
        $sql = "select * from users where user='$user' and password='$password' and administrator=1";
        $db = new db();
        $db = $db->connect();
        $resultado = $db->query($sql);
        if($resultado->rowCount() > 0){
            $users = $resultado->fetchAll(PDO::FETCH_OBJ);
            echo json_encode($users);
        }else{
            echo getJSONformat("response","incorrectUserPassword");
        }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo getJSONformat("error",$e->getMessage());
    }
});