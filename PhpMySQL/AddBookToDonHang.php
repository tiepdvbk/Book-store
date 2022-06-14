<?php 
	include 'db.php';
	$id_donhang = $_POST['id_donhang'];
	$id_book = $_POST['id_book'];
	$image_url = $_POST['image_url'];
	$name	= $_POST['name'];
	$price	= $_POST['price'];
	$sl	= $_POST['sl'];
	$total_price = $_POST['total_price'];
	$sql = "INSERT INTO chitietdonhang(id_donhang,id_book,image_url,name,price,sl,total_price)
	VALUES ( '$id_donhang', '$id_book', '$image_url', '$name', '$price', '$sl', '$total_price')";
	if ($conn->query($sql) === TRUE) {
	  echo "yes";
	} else {
	  echo "no";
	}
	$conn->close();
?>