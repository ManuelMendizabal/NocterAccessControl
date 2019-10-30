<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';
use Chadicus\Books\FileRepository;
use Chadicus\Slim\OAuth2\Routes;
use Chadicus\Slim\OAuth2\Middleware;

use Slim\Http;
use Slim\Views;

require '../src/config/db.php';

require 'security.php';
require 'jsonManager.php';
if(!checkCredentials()) //check valid crdentials
    return;

$app = new \Slim\App;
$container = $app->getContainer();



$app = new \Slim\App;

require '../src/routes/login.php';
require '../src/routes/users.php';
require '../src/routes/signRegister.php';
$app->run();