<?php 
	include 'db.php';
	class OK
	{
		function __construct($id)
		{
			$this->id = $id;
		}
	}
	$l = array();
	$sql = "SELECT MAX(id) AS 'id' FROM donhang";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($l, new OK($row["id"]));
	}
	echo json_encode($l);

?>