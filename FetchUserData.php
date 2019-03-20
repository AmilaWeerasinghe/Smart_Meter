<?php
$con=mysqli_connect("localhost","my_user","my_password","my_db");
$password=$_POST["password"];
$username=$_POST["username"];

$statement= mysqli_prepare($con,"SELECT * FROM User WHERE username = ? AND password = ?");
mysqli_stmt_bind_param($statement,"ss",$username,$password);
mysqli_stmt_execute($statement);
mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$userId,$name,$username,$password,$age);
$user=array();

while(mysqli_stmt_fetch($statement)){
	$user[name]=$name;
	$user[username]=$username;
	$user[password]=$password;
	$user[age]=$age;
}

echo json_encode($user);
mysqli_stmt_close($statement);
mysqli_close($con);
?>