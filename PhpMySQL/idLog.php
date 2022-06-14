<?php
	include 'db.php';
	$uname = $_POST['uname'];
	$pass = $_POST['pass'];
	// $uname = "tiepdvbk";
	// $pass = "123";

	class user
	{
		function __construct($id,$uname,$name,$pass,$sdt,$mail,$address)
		{
			$this->id = $id;
			$this->uname = $uname;
			$this->name = $name;
			$this->pass = $pass;
			$this->sdt = $sdt;
			$this->mail = $mail;
			$this->address = $address;
		}
	}
	$lu = array();
	$sql = "SELECT * FROM user WHERE uname='$uname' AND pass='$pass'";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($lu, new user($row["id"], $row["uname"],$row["name"],$row["pass"],$row["sdt"],$row["mail"],$row["address"]));
	}
	echo json_encode($lu);
?>