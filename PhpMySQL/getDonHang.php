<?php
	include 'db.php';
	$id_user = $_POST['id_user'];
	class OK
	{
		function __construct($id,$id_user, $date_time,$price,$trangthai)
		{
			$this->id = $id;
			$this->id_user = $id_user;
			$this->date_time = $date_time;
			$this->price = $price;
			$this->trangthai = $trangthai;
		}
	}
	$l = array();
	$sql = "SELECT * FROM donhang where id_user='$id_user'";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($l, 
	    	new OK($row["id"], $row["id_user"] , $row["date_time"],$row["price"],$row["trangthai"]));
	}
	echo json_encode($l);
?>