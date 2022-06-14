<?php 
	include 'db.php';

	$id_donhang = $_POST['id_donhang'];

	$sql = "DELETE FROM chitietdonhang WHERE id_donhang='$id_donhang'";

	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} 
	else {
	  echo "no";
	}

	$conn->close();
?>