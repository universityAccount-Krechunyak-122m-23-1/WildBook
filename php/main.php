<?php
    //Зв'язок з базою 
    $connection = new mysqli("ieugene.mysql.tools", "ieugene_admin", "23042000Eugennyy_Admin", "ieugene_wildbook");

    mysqli_set_charset($connection, "UTF8");
 
    if (!$connection) {
        echo "Виникла помилка під час встановлення з'єднання з базою даних. Будь-ласка, перезавантажте сторінку.";
        exit;
    };
    
    if (isset($_POST['name']) && isset($_POST['telephone']) && isset($_POST['email']) && isset($_POST['select'])) {
        $name = trim($_POST['name']);
        $telephone = trim($_POST['telephone']);
        $email = trim($_POST['email']);
        $bookName = trim($_POST['select']);

        //Занесення даних користувача до БД та створення заявки
        $query = "INSERT INTO `Clients` SET `id` = not null, `name` = '$name', `phone_number` = '$telephone', `email` = '$email'; ";
        $query .= "INSERT INTO `Tickets` SET `id` = not null, `name` = '$bookName', `client_phone_number` = '$telephone', `status` = 'Очікування'; ";

        $connection->multi_query($query);
    };

    mysqli_close($connection);
?>