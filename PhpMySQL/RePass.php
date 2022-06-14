<?php 
	include 'db.php';
	
	$id = $_POST['id'];
	$uname = $_POST['uname'];
	$pass	= $_POST['pass'];
	// $id = '11';
	// $uname = 'tiepdvbk1';
	// $pass	= '12345';
	$sql = "UPDATE user SET pass='$pass' WHERE id = '$id' AND uname = '$uname'";
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}

	$conn->close();
?>