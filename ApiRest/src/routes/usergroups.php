<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
//GET Get user
$app->get('/api/usergroups/{id}', function(Request $request, Response $response){
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
            echo json_encode("user not exists");
        }
        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});
//POST Create User
$app->post('/api/usergroups/add', function(Request $request, Response $response){
    $nombre = $request -> getParam('user');
    $password = $request -> getParam('password');
    $active = $request -> getParam('active');
    $administrator = $request -> getParam('administrator');
    $usergroup = $request -> getParam('usergroup');
    $sql = "insert into users(user,password,active,administrator,usergroup) 
    values (:user, :password, :active, :administrator, :usergroup)";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->prepare($sql);
        $resultado->bindParam(':user',$nombre);
        $resultado->bindParam(':password',$password);
        $resultado->bindParam(':active',$active);
        $resultado->bindParam(':administrator',$administrator);
        $resultado->bindParam(':usergroup',$usergroup);

        $resultado->execute();

        echo json_encode("new user added");

        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});
//PUT Modify User
$app->put('/api/usergroups/update/{id}', function(Request $request, Response $response){
    $id = $request->getAttribute('id');
    $nombre = $request -> getParam('user');
    $password = $request -> getParam('password');
    $active = $request -> getParam('active');
    $administrator = $request -> getParam('administrator');
    $usergroup = $request -> getParam('usergroup');
    $sql = "update users set 
    user=:user,
    password = :password,
    active= :active,
    administrator = :administrator,
    usergroup = :usergroup where id=$id";
    try{
        $db = new db();
        $db = $db->connect();
        $resultado = $db->prepare($sql);
        $resultado->bindParam(':user',$nombre);
        $resultado->bindParam(':password',$password);
        $resultado->bindParam(':active',$active);
        $resultado->bindParam(':administrator',$administrator);
        $resultado->bindParam(':usergroup',$usergroup);

        $resultado->execute();

        echo json_encode("user updated");

        $resultado = null;
        $db = null;
    }catch(PDOException $e){
        echo '{"error" : {"text":'.$e->getMessage().'}';
    }
});
//DELETE delete User
$app->delete('/api/usergroups/delete/{id}', function(Request $request, Response $response){
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