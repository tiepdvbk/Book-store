<?php
	include 'db.php';
	$id_donhang = $_POST['id_donhang'];
	class OK
	{
		function __construct($id,$id_donhang, $id_book,$image_url,$name, $price,$sl, $total_price)
		{
			$this->id = $id;
			$this->id_donhang = $id_donhang;
			$this->id_book = $id_book;
			$this->sl = $sl;
			$this->price = $price;
			$this->image_url = $image_url;
			$this->name = $name;
			$this->total_price = $total_price;
		}
	}
	$l = array();
	$sql = "SELECT * FROM chitietdonhang where id_donhang='$id_donhang'";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($l, new OK($row["id"], $row["id_donhang"] , $row["id_book"],$row["image_url"],$row["name"], $row["price"],$row["sl"],$row["total_price"]));
	}
	echo json_encode($l);
?>