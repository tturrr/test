<?php

	session_start();
  include_once('mailer.lib.php');
?>

<!DOCTYPE HTML>

<html>

<head>

<title>

회원 가입

</title>

<script type="text/javascript">



</script>

<?php

function _sendemail($_emailinfo, $_subject, $_emailmsg, $_header)

{

	if(mail($_emailinfo, $_subject, $_emailmsg, $_header)){

		return true;

	}else{

		return false;

	}

}

?>



</head>

<body>

	<?php

	if(!$_SESSION["testuser"])

	{

	?>





	<?php


		// Get the values.

		$_emailid = $_POST["personalemailid"];

		$_emailcompany = $_POST["emailcompany"];

		$_usernickname = $_POST["nickname"];

		$_setpw = $_POST["pwd"];

		$_pwvalidate = $_POST["pwdvalidate"];

		$_emailinfo = $_POST["emailInfo"];

		// Need to generate seed value that will be used as confirmation code.
    // mailer("보내는 사람 이름", "받는 사람 메일주소", "보내는 사람 메일주소", "제목", "내용", "1");


		$seedvalue = -1;

		//

		$statverify = 0;

		$sqlquery = "";

		$queryresult = "";

		$_registeredemail = false;

		$_usednickname = false;

		$_preregisteredemail = false;

		$_preusednickname = false;



		$bFinalResult = false;



		$con = mysqli_connect("127.0.0.1", "root", "a1214511", "joeltestdb");

		if(mysqli_connect_errno($con))

		{

			echo "<script>alert('Failed to connect to MySQL:' . mysqli_connect_error())</script>";

		}else {

			// check whether or not email info is already there.

			$sqlquery = "SELECT * FROM login WHERE email LIKE" . " " . "'" . $_emailinfo . "'";

			$queryresult = mysqli_query($con, $sqlquery);

			$row = mysqli_fetch_array($queryresult);



			if("" != $row['email']){
				// The email info is already registered.
				if(($_emailinfo == $row['email']) && (0 == $row['bverified']))
				{
					$_preregisteredemail = true;
				}
				else if(($_emailinfo == $row['email']) && (1 == $row['bverified']))
				{
					$_tempstr = "bverified value is " . $row['bverified'];
					$_registeredemail = true;
				}
			}
			$sqlquery = "SELECT * FROM login WHERE nickname LIKE" . " " . "'" . $_usernickname . "'";
			$queryresult = mysqli_query($con, $sqlquery);
			$row = mysqli_fetch_array($queryresult);
			if("" != $row['nickname']){
				// The nick name is already used by others.
				if($_usernickname == $row['nickname'])
				{
					$_tempstr = "bverified value is " . $row['bverified'];
					$_usednickname = true;
				}
			}
			if((!$_registeredemail) && (!$_usednickname)){
				// generate seed value.
				$seedvalue = rand();
				$emailmsg = "myBlog에서 인증코드를 보냈습니다." ;
				$subject = "가입을 위한 최종 확인을 부탁드립니다" . "<br /> 인증코드 : " . $seedvalue ;
				if(mailer("myBlog","jsy10835@naver.com", $_emailinfo, $emailmsg, $subject, 1)){
					// Send email is success.
					$statverify = 0;
					if($_preregisteredemail)
					{
						//echo "<br>update query <br>";
						$sqlquery = "UPDATE login SET
						email='$_emailinfo', password='$_setpw', seed='$seedvalue', bverified='$statverify', nickname='$_usernickname' WHERE
						email= '$_emailinfo'";
					}	else {
						$sqlquery = "INSERT INTO login (email, seed, bverified, nickname, password) VALUES ('$_emailinfo', '$seedvalue', '$statverify', '$_usernickname', '$_setpw')";
					}
					//$queryresult = mysqli_query($con, $sql);
					if (!mysqli_query($con, $sqlquery))
					{
						die('Error: ' . mysqli_error($con));
					}
					else
					{
						// Guide user to check his/her email to enter the confirmation code.
						$bFinalResult = true;
						//
					}
				}else{
					// Send email is failed.
					echo "Sending email is failed";
					echo "<br><br>";
				}



			}else{

				// registered email info or used nick name.

				echo "registered email info or used nick name";

				echo "<br><br>";

			}

			mysqli_close($con);



			// Guide Page Display.

			if($bFinalResult)

			{

				$display_message = "가입을 축하드립니다. 등록하신 이메일로 보내드린 인증코드를 입력해 주세요.<br>";

			}

			else

			{

				if(($_registeredemail) && ($_usednickname))

				{

					$display_message = "죄송합니다. 입력하신 아이디와 이메일이 이미 존재하고 있습니다.";

				}

				else if($_registeredemail)

				{

					$display_message = "죄송합니다. 이미 존재하는 이메일입니다.";

				}

				else if($_usednickname)

				{

					$display_message = "죄송합니다. 이미 존재하는 아이디입니다.";

				}

			}

		}

	?>

	<table align="center" width="700" border="0">

	<tr>

	<td>

			<?php echo $display_message ?>

			<?php

			if($bFinalResult)

			{

			?>
			<script>


 		history.back();

			</script>
				<!-- <input type="button" onclick="location.href='main.php'" value="입력하러가기" > -->

			<?php

			}

			?>

	</td>

	</tr>

	</table>

	<?php } else { ?>

	<p> 가입을 축하드립니다. 이메일을 확인해 주세요.

	<?php

	}

	?>

</body>

</html>
