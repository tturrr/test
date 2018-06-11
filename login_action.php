<?php
session_cache_limiter('nocache, must-revalidate');
	session_start();
?>



<!DOCTYPE HTML>

<html>

<head>

<!-- <meta charset="utf-8">  -->

<title>

로그인 처리 중

</title>

<script type="text/javascript">



function CHECKCODE()

{



	var p1 = document.getElementById("confcode");



	if(p1.value){

		if(origcodeDB[0] == p1.value)

		{

			// The value is correct. So, the bVerified has to be udpated to 1.

			var ref = "confirmcodecheck.php";

			ref = ref + "?confcode="+p1.value;

			ref = ref + "&emailinfo=" + origcodeDB[1];

			var window_left = (screen.width - 640)/2;

			var window_top = (screen.height  - 480)/2;

			window.open(ref, "가입_확인_진행", 'width=500, height=160, status=no, top='+window_top+', left='+window_left+'');

			location.href='main.php';

		} else {

			alert("인증 코드가 맞지 않습니다. 다시 확인해 주세요");

			p1.focus();

			return;

		}

	} else {

		alert("인증 코드를 입력해 주세요");

		p1.focus();

		return;

	}

}

</script>

</head>

<body>



<script>

	var _logoutcheck = location.href.split("?");

	var _redirect = _logoutcheck[1];



	if("logout=yes" == _logoutcheck[1]){



		// delete all session.

		<?php

		session_unset();

		?>

		alert('로그아웃 성공');

		location.href='main.php';

	}

	else if("login=yes" == _logoutcheck[1]){

		//alert("로그인 프로세스 시작");

	}

	else{

		//alert("else routine");

	}



</script>

<?php

	$_identification = $_POST["identification"];

	$_emailcompany = $_POST["emailcompany"];

	$_emailInfo = $_identification . "@" . $_emailcompany;

	$_password = $_POST["pwd"];



	$con = mysqli_connect("localhost", "root", "a1214511", "joeltestdb");

	if(mysqli_connect_errno($con)){
		session_unset();
		echo "<script>alert('데이터베이스 접속에 실패하였습니다.');location.href='main.php'; </script>";
	}
	else{
		$db_check = "SELECT * FROM login WHERE email LIKE" . " " . "'" . $_emailInfo . "'";
		$db_check_rst = mysqli_query($con, $db_check);
		$row = mysqli_fetch_array($db_check_rst);
		mysqli_close($con);
		if( ("" != $row['email']) && ($_password == $row['password'])){
			// Check bVerified is 1.
			if("1" == $row['bverified'])
			{
				// 1: Log in OK.
				$_SESSION['testuser'] = $row['nickname'];
				echo "<script>location.href='index.php';</script>";
			} else {
				// Log in 하기 위한 bVerified 값 갱신을 위해 인증 코드 확인 진행.
				$confirmcodeinDB = $row['seed'];
				// PHP 변수 값들을 Java Script에 배열로 넘기기 위한 작업.
				$phparray =  "'".$confirmcodeinDB."'".","."'".$_emailInfo."'";
				echo "가입 확인을 위해 이메일로 받으신 인증 코드를 입력해 주세요 <br>";
?>
				<script type="text/javascript">
					var origcodeDB = new Array(<?php echo $phparray; ?>);
				</script>
				<input type="text" id="confcode" name="confirmationcode" value="" >
				<input type="button" onclick="CHECKCODE()" value="인증 코드 확인">
				<form id="membershipform" align="left" action="http://13.125.107.155/membership_rewelcome.php?login=yes" method="post">
					<?php
					$con = mysqli_connect("localhost", "root", "a1214511", "joeltestdb");
					if(mysqli_connect_errno($con)){
						session_unset();
						echo "<script>alert('데이터베이스 접속에 실패하였습니다.');location.href='main.php'; </script>";
					}
					else{
						$db_check = "SELECT * FROM login WHERE email LIKE" . " " . "'" . $_emailInfo . "'";
					}
					?>
					<input type="hidden" name="emailInfo" value="<?php echo $_emailInfo ?>">
						<input type="hidden" name="nickname" value="<?php echo $_emailInfo ?>">
							<input type="hidden" name="pwd" value="<?php echo $_password ?>">
				<input type="submit" value="인증메일 재발송" />
				</form>
<?php

			}

		}

		else{

			echo "<script>alert('로그인 실패');location.href='main.php';</script>";

		}

	}

?>



</body>

</html>
