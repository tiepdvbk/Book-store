<?php 
	include 'db.php';
	
	$id_user = $_POST['id_user'];
	$date_time = $_POST['date_time'];
	$price = $_POST['price'];
	$trangthai	= $_POST['trangthai'];
	$sql = "INSERT INTO donhang (id_user,date_time,price,trangthai)
	VALUES ( '$id_user', '$date_time', '$price', '$trangthai')";
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}

	$conn->close();
?>