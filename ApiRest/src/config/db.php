<?php
    class db{
        private $host = 'db5000086936.hosting-data.io';
        private $user = 'dbu53469';
        private $password = 'Nocter&toor-2317';
        private $name = 'dbs81664';

        public function connect(){
            $mysqlConnect = "mysql:host=$this->host;dbname=$this->name";
            $connection = new PDO($mysqlConnect, $this->user,$this->password);
            $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            return $connection;
        }
    }