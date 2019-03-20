<?php
$con=mysqli_connect("localhost","my_user","my_password","my_db");

$name=$_POST["name"];
$age=$_POST["age"];
$password=$_POST["password"];

$username=$_POST["username"];

$statement= mysqli_prepare($con,"INSERT INTO User(name,username,password,age)VALUES(?,?,?,?)");
mysqli_stmt_bind_param($statement,"sssi",$name,$username,$password,$age);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);
mysqli_close($con);

?>