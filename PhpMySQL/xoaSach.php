<?php 
	include 'db.php';

	$id = $_POST['id'];
	// $id = "1";

	$sql = "DELETE FROM chitietcard WHERE id='$id'";

	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} 
	else {
	  echo "no";
	}

	$conn->close();
?>