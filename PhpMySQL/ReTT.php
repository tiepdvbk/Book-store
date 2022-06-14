<?php 
	include 'db.php';
	
	$id = $_POST['id'];
	$name = $_POST['name'];
	$sdt = $_POST['sdt'];
	$mail = $_POST['mail'];
	// $id = '11';
	// $uname = 'tiepdvbk1';
	// $pass	= '12345';
	$sql = "UPDATE user SET name='$name', sdt='$sdt', mail='$mail' WHERE id = '$id'";
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}

	$conn->close();
?>