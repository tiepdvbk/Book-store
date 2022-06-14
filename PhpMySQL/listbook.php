<?php
	include 'db.php';
	class ListBook
	{
		function __construct($id,$name)
		{
			$this->id = $id;
			$this->name = $name;
		}
	}
	$lb = array();
	$sql = "SELECT * FROM theloai";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($lb, new ListBook($row["id"], $row["name"]));
	}
	echo json_encode($lb);
?>