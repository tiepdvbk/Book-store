<?php 
	include 'db.php';
	$id_donhang = $_POST['id_donhang'];
	$id_card= $_POST['id_card'];

	$sql = "INSERT INTO chitietdonhang(id_donhang,id_book,image_url,name,price,sl,total_price)
	SELECT '$id_donhang',id_book,image_url,name,price,sl,total_price 
	from chitietcard
	where chitietcard.id_card = '$id_card'";
	if ($conn->query($sql) === TRUE) {
		echo "yes";
	} else {
	    echo "no";
	}

	$conn->close();
?>