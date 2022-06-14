<?php 
	include 'db.php';

	$id = $_POST['id'];

	$sql = "INSERT INTO card (id)VALUES ('$id')";
			
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} 
	else {
	  echo "no";
	}
	$conn->close();
?>