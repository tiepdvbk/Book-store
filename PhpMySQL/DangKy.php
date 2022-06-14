<?php 
	include 'db.php';

	$uname = $_POST['uname'];
	$name = $_POST['name'];
	$pass = $_POST['pass'];
	$sdt = $_POST['sdt'];
	$mail = $_POST['mail'];
	// $uname = '3';
	// $name = '3';
	// $pass = '3';
	// $sdt = '3';
	// $mail = '3';

	$sql = "INSERT INTO user (uname, name, pass, sdt, mail) 
			VALUES ('$uname', '$name', '$pass','$sdt','$mail')";
			
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} 
	else {
	  echo "no";
	}
	$conn->close();
?>