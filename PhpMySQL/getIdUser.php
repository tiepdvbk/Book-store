<?php
	include 'db.php';
	class User
	{
		function __construct($id)
		{
			$this->id = $id;
		}
	}
	$lu = array();
	$sql = "SELECT id FROM user WHERE uname = '$uname' AND pass = '$pass'";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($lu, new ListBook($row["id"], $row["name"]));
	}
	echo json_encode($lu);
?>