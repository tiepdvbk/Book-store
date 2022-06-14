<?php
	include 'db.php';
	$id_tl = $_POST['id_tl'];
	class book
	{
		function __construct($id,$name,$price,$gprice,$id_tl,$author,$img_url)
		{
			$this->id = $id;
			$this->name = $name;
			$this->price = $price;
			$this->gprice = $gprice;
			$this->id_tl = $id_tl;
			$this->author = $author;
			$this->img_url = $img_url;
		}
	}
	$lb = array();
	$sql = "SELECT * FROM book where id_tl = '$id_tl'";
	$result = $conn->query($sql);
	while($row = $result->fetch_assoc()) {
	    array_push($lb, new book($row["id"], $row["name"], $row["price"], $row["gprice"], $row["id_tl"], $row["author"], $row["img_url"]));
	}
	echo json_encode($lb);
?>