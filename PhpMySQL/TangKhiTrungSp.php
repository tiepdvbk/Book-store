<?php 
	include 'db.php';
	
	$id_card = $_POST['id_card'];
	$id_book = $_POST['id_book'];
	$Sl	= $_POST['Sl'];
	// $id_card = '1';
	// $id_book = '2';
	// $Sl	= '5';
	$sql = "UPDATE chitietcard SET sl=sl+'$Sl',total_price=sl*price WHERE id_book = '$id_book' AND id_card = '$id_card'";
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}

	$conn->close();
?>