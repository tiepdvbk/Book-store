<?php 
	include 'db.php';
	
	$id_card = $_POST['id_card'];
	$id_book = $_POST['id_book'];
	$sl	= $_POST['sl'];
	$total_price = $_POST['total_price'];
	$sql = "UPDATE chitietcard SET sl='$sl',total_price='$total_price' WHERE id = '$id_book' AND id_card = '$id_card'";
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}

	$conn->close();
?>