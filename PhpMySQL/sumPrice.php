<?php
	include 'db.php';
	$id_card = $_POST['id_card'];

	$sql = "SELECT SUM(total_price) AS sum FROM chitietcard WHERE id_card = '$id_card'";
	$result = $conn->query($sql);
	$row = $result->fetch_assoc();
	$sum = $row["sum"];
	echo $sum;
?>