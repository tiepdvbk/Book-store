<?php
	include 'db.php';
	$id_book = $_POST['id_book'];
	$id_card = $_POST['id_card'];
	$sql = "SELECT * FROM chitietcard where id_book = '$id_book' AND id_card = '$id_card'";
	// $sql = "SELECT * FROM chitietcard where id_book = '23'";

	$result = $conn->query($sql);
	$row = mysqli_query($conn,$sql);
	$dem = mysqli_num_rows($row);
	if ($dem > 0) {
		echo "yes";
	}else {
		echo "no";
	}
?>