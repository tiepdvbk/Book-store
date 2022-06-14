<?php 
	include 'db.php';
	$id_card= $_POST['id_card'];

	$conn = new mysqli($servername, $username, $password, $dbname);
	$sql = "DELETE FROM chitietcard WHERE id_card='$id_card'";

	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}

	$conn->close();
?>