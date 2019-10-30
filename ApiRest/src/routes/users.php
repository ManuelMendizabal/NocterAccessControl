<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
//GET Get all users
$app->get('/api/users', function(Request $request, Response $response){
    $user = $request->getQueryParam('user');
    $where = "";
    if($user != null){
        $where = "where user like '%$user%'";
    }
    $sql = "select * from users ".$where." order by user asc";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->query($sql);
        if($resultado->rowCount() > 0){
            $users = $resultado->fetchAll(PDO::FETCH_OBJ);
            echo json_encode($users);
        }else{
            echo json_encode("there aren't users");
        }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});

//GET Get user
$app->get('/api/users/{id}', function(Request $request, Response $response){
    $id = $request->getAttribute('id');
    $sql = "select * from users where id=$id";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->query($sql);
        if($resultado->rowCount() > 0){
            $users = $resultado->fetchAll(PDO::FETCH_OBJ);
            echo json_encode($users);
        }else{
            echo getJSONformat("response","noUsersFound");
        }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});
//POST Create User
$app->post('/api/users/add', function(Request $request, Response $response){
    $name= $request -> getParam('user');
    $password = $request -> getParam('password');
    $active = $request -> getParam('active');
    $administrator = $request -> getParam('administrator');
    $usergroup = $request -> getParam('usergroup');
    $idSchedule = $request -> getParam('idSchedule');
    $nfcCode = $request -> getParam('nfcCode');
    $sql = "select * from users where user='$name'";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->query($sql);
        if($resultado->rowCount() > 0){
            echo getJSONformat("response","userAlreadyExists");
        }else{
            $resultado = null;
            $db = null;
            $sql = "insert into users(user,password,active,administrator,usergroup,idSchedule, nfcCode) 
            values (:user, :password, :active, :administrator, :usergroup, :idSchedule, :nfcCode)";
            try{
                $db = new db();
                $db = $db->connect();
                $resultado = $db->prepare($sql);
                $resultado->bindParam(':user',$name);
                $resultado->bindParam(':password',$password);
                $resultado->bindParam(':active',$active);
                $resultado->bindParam(':administrator',$administrator);
                $resultado->bindParam(':usergroup',$usergroup);
                $resultado->bindParam(':idSchedule',$idSchedule);
                $resultado->bindParam(':nfcCode',$nfcCode);

                $resultado->execute();

                echo getJSONformat("response","newUseradded");
                $resultado = null;
                $db = null;
            }catch(PDOException $e){
                echo '{"error" : {"text":'.$e->getMessage().'}';
            }
        }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});
//PUT Modify User
$app->put('/api/users/update/{id}', function(Request $request, Response $response){
    $id = $request->getAttribute('id');
    $nombre = $request -> getParam('user');
    $password = $request -> getParam('password');
    $active = $request -> getParam('active');
    $administrator = $request -> getParam('administrator');
    $usergroup = $request -> getParam('usergroup');
    $idSchedule = $request -> getParam('idSchedule');
    $nfcCode = $request -> getParam('nfcCode');
    $sql = "update users set 
    user=:user,
    password = :password,
    active= :active,
    administrator = :administrator,
    usergroup = :usergroup ,
    idSchedule = :idSchedule,
    nfcCode = :nfcCode
    where id=$id";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->prepare($sql);
        $resultado->bindParam(':user',$nombre);
        $resultado->bindParam(':password',$password);
        $resultado->bindParam(':active',$active);
        $resultado->bindParam(':administrator',$administrator);
        $resultado->bindParam(':usergroup',$usergroup);
        $resultado->bindParam(':idSchedule',$idSchedule);
        $resultado->bindParam(':nfcCode',$nfcCode);

        $resultado->execute();

        echo getJSONformat("response","userUpdated");

        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});
//DELETE delete User
$app->delete('/api/users/delete/{id}', function(Request $request, Response $response){
    $id = $request->getAttribute('id');
    $sql = "delete from users where id=$id";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->prepare($sql);
        $resultado->execute();

        if($resultado->rowCount() > 0){
            echo json_encode("user deleted");
        }else{
            echo json_encode("user not exists");
        }

        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});