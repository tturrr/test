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
	$con = mysqli_connect("localhost", "root", "a1214511", "joeltestdb");
	if(mysqli_connect_errno($con))
	{
		echo "<script>alert('Failed to connect to MySQL:' . mysqli_connect_error())</script>";
	} else {
		$phpself = $_SERVER["PHP_SELF"];
		$query_string=getenv("QUERY_STRING");



		$passedcode = $_GET['confcode'];
		$emailinfo = $_GET['emailinfo'];
		$sqlquery = "SELECT * FROM login WHERE email LIKE" . " " . "'" . $emailinfo . "'";
		$queryresult = mysqli_query($con, $sqlquery);
		$row = mysqli_fetch_array($queryresult);
		$_seedofDB = $row['seed'];

		if($passedcode == $_seedofDB){

			// Update the bverified value.
			$sqlquery = "UPDATE login SET
			bverified='1' WHERE email= '$emailinfo' and seed = '$passedcode'";
			if (!mysqli_query($con, $sqlquery))
			{
				echo "Error for executing query";
				die('Error: ' . mysqli_error($con));
			}
			else
			{
				// Guide user to check his/her email to enter the confirmation code.
				$bFinalResult = true;
				mysqli_close($con);
?>
				<P> 가입이 확인 되었으니 로그인 하시면 됩니다.</P>
				<form>
				<input type="button" onclick="self.close();" value="확인">
				</form>
<?php
			}
		}else {
			mysqli_close($con);
?>
			<P> 인증코드가 일치하지 않습니다. 인증 코드를 다시 확인해 주세요. </P>
			<form>
			<input type="button" onclick="self.close()" value="닫기.">
			</form>
<?php
		}
	}
?>
</body>

</html>
