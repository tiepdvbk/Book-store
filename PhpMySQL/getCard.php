<?php
	include 'db.php';
	$id_card = $_POST['id_card'];
	class OK
	{
		function __construct($id,$id_card, $id_book,$image_url,$name, $price,$sl, $total_price)
		{
			$this->id = $id;
			$this->id_card = $id_card;
			$this->id_book = $id_book;
			$this->sl = $sl;
			$this->price = $price;
			$this->image_url = $image_url;
			$this->name = $name;
			$this->total_price = $total_price;
		}
	}
	$l = array();
	$sql = "SELECT * FROM chitietcard where id_card='$id_card'";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($l, new OK($row["id"], $row["id_card"] , $row["id_book"],$row["image_url"],$row["name"], $row["price"],$row["sl"],$row["total_price"]));
	}
	echo json_encode($l);
?>