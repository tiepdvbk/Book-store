<?php 
	include 'db.php';
	
	$id = $_POST['id'];
	$address = $_POST['address'];
	$sql = "UPDATE user SET address='$address' WHERE id = '$id'";
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}

	$conn->close();
?>