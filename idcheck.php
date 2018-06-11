<?php

	session_start();

?>

<!DOCTYPE HTML>

<html>

<head>

<title>

</title>

</head>

<body>



<?php

	$_usernickname = $_POST["nickname"];

	$sqlquery = "";

	$queryresult = "";

	$_usednickname = false;

	$bFinalResult = false;



	$con = mysqli_connect("127.0.0.1", "root", "a1214511", "joeltestdb");

	if(mysqli_connect_errno($con))

	{

		echo "<script>alert('Failed to connect to MySQL:' . mysqli_connect_error())</script>";

	}else {



		$checkid = '<script>var _passParam0 = location.href.split("?");var _passParam1 = _passParam0[1].split("=");

				 document.writｅ(_passParam1[1]);</script>';

		$_checkid = $checkid;

		$phpself = $_SERVER["PHP_SELF"];

		$query_string=getenv("QUERY_STRING");

		list($param1, $param2) = explode("=", $query_string);
		echo "<script>alert($param2)</script>";
		$sqlquery = "SELECT * FROM login WHERE nickname LIKE" . " " . "'" . $param2 . "'";



		$queryresult = mysqli_query($con, $sqlquery);



		$row = mysqli_fetch_array($queryresult);

		$_tmpgetresult = $row['nickname'];



		mysqli_close($con);



		if($param2 != $_tmpgetresult ){

			//echo "can't use";

		?>

			사용 가능 합니다.

			<form>
			<input type="button" onclick="self.close()" value="이 아이디 사용하기">

			</form>



		<?php

		}else{

		?>
			이미 등록되어 있습니다. 다른 아이디로 신청하여 주세요.
			<form>
			<input type="button" onclick="self.close()" value="닫기.">
			</form>

		<?php

		}

		?>

<?php

	}

?>

</body>

</html>
