<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
//GET Get all users
$app->get('/api/signRegister', function(Request $request, Response $response){
    $iduser = $request->getQueryParam('iduser');
    $user = $request->getQueryParam('user');
    $idgroup = $request->getQueryParam('idgroup');
    $usergroup = $request->getQueryParam('usergroup');
    $idschedule = $request->getQueryParam('idschedule');
    $schedule = $request->getQueryParam('schedule');
    $startdate = $request->getQueryParam('startdate');
    $enddate = $request->getQueryParam('enddate');
    $movtype = $request->getQueryParam('movtype');
    $where = "";

    if($iduser != null){
        $where .= "where iduser='$iduser'";
    }
    if($user != null){
        $where .= (strlen($where)>0?" and ": " where ");
        $where .= "user='$user'";
    }
    if($idgroup  != null){
        $where .= (strlen($where)>0?" and ": " where ");
        $where .= "idgroup='$idgroup'";
    }
    if($usergroup  != null){
        $where .= (strlen($where)>0?" and ": " where ");
        $where .= "usergroup='$usergroup'";
    }
    if($idschedule!= null){
        $where .= (strlen($where)>0?" and ": " where ");
        $where .= "idschedule='$idschedule'";
    }
    if($schedule != null){
        $where .= (strlen($where)>0?" and ": " where ");
        $where .= "schedule='$schedule'";
    }
    if($startdate != null && $enddate  != null){
        $where .= strlen($where)>0?" and ": " where ";
        $where .= "date between '$startdate' and '$enddate'";
    }else{
        if($startdate != null){
            $where .= strlen($where)>0?" and ": " where ";
            $where .= "date='$startdate'";
        }
        if($enddate  != null){
            $where .= (strlen($where)>0?" and ": " where ");
            $where .= "date='$enddate'";
        }
    }
    if($movtype != null){
        $where .= (strlen($where)>0?" and ": " where ");
        $where .= "movtype='$movtype'";
    }
    $sql = "select * from vsignRegister ".$where." order by date desc";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->query($sql);
        if($resultado->rowCount() > 0){
            $users = $resultado->fetchAll(PDO::FETCH_OBJ);
            echo json_encode($users);
        }else{
            echo getJSONformat("response","signRegistersNotFound");
        }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});
//GET Login
$app->get('/api/signRegisterUsers', function(Request $request, Response $response){
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
        $sql = "select * from vsingRegisterUsers $where";
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
//POST Create User
$app->post('/api/signRegister/add', function(Request $request, Response $response){
    $iduser = $request->getParam('iduser');
    $idgroup = $request->getParam('idGroup');
    $idschedule = $request->getParam('idschedule');
    $date = $request->getParam('date');
    $movtype = $request->getParam('movtype');
    try{
        $db = new db();
        $db = $db->connect();
            $resultado = null;
            $db = null;
            $sql = "insert into signRegister(iduser,idGroup,idschedule,date,movtype) 
            values (:iduser,:idGroup,:idschedule,:date,:movtype)";
            try{
                $db = new db();
                $db = $db->connect();
                $resultado = $db->prepare($sql);
                $resultado->bindParam(':iduser',$iduser);
                $resultado->bindParam(':idGroup',$idgroup);
                $resultado->bindParam(':idschedule',$idschedule);
                $resultado->bindParam(':date',$date);
                $resultado->bindParam(':movtype',$movtype);

                $resultado->execute();
                
                echo getJSONformat("response","signAdded");
                $resultado = null;
                $db = null;
            }catch(PDOException $e){
                echo '{"error" : {"text":'.$e->getMessage().'}';
            }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});