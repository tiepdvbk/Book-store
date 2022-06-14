<?php
	include 'db.php';
	$uname = $_POST['uname'];
	$pass = $_POST['pass'];
	// $uname = "tiepdvbk";
	// $pass = "123";
	$sql = "SELECT * FROM user WHERE uname='$uname' AND pass='$pass'";
	$row = mysqli_query($conn,$sql);
	$dem = mysqli_num_rows($row);

	if ($dem>0) {
	  echo "yes";
	} else {
	  echo "no";
	}
	$conn->close();
?>