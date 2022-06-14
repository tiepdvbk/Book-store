<?php 
	include 'db.php';

	$id = $_POST['id'];

	$sql = "DELETE FROM donhang WHERE id='$id'";

	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} 
	else {
	  echo "no";
	}

	$conn->close();
?>