<?php
	include 'db.php';
	// $id = $_POST['id'];
	// $id = 1;

	class theLoai
	{
		function __construct($id,$name)
		{
			$this->id = $id;
			$this->name = $name;
		}
	}
	$tl = array();
	// $sql = "SELECT * FROM theloai where id = '$id'";
	$sql = "SELECT * FROM theloai where id = '2'";

	$result = $conn->query($sql);
	$row = mysqli_query($conn,$sql);
	$dem = mysqli_num_rows($row);
	if ($dem == 1) {
		echo "1";
		// code...
	}else 
		echo "2";
	// while($row = $result->fetch_assoc()) {
	//     array_push($tl, new theLoai($row["id"], $row["name"]));
	// }
	// echo ""+$result;
	// echo json_encode($tl);
?>