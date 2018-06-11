<?php

	session_start();

?>



<!DOCTYPE HTML>

<html>

<head>

<!-- <meta charset="utf-8">  -->

<title>

회원 가입

</title>



<script>



function checkPassword(){

	var p1 = document.getElementById("personalPwd");

	var p2 = document.getElementById("personalPwdvalidate");

	if(p1.value != p2.value)	return false;

	else	return true;

}



function submitBack(){

	// To make sure, to let this user to be reached to the default main page.

	<?php

		session_unset();

	?>

	location.href='main.php';

}



function submitForm(){

	// 1. Check whether the email info is valid.

	// 2. Check if the id info is not empty.

	// 3. Check the password that the value what user want to use.

	var _submit = document.getElementById("membershipform");

	null_isthere = -1;

	for(var i=0; i < _submit.length; i++){

		if("" == _submit.elements[i].value)

			null_isthere = i;

	}

	if("-1" != null_isthere){

		alert("null is there" + null_isthere);

		switch(null_isthere){

		case 0:

			alert("이메일 정보를 입력해 주세요");

			break;

		case 1:

			alert("이메일 정보를 입력해 주세요");

			break;

		case 2:

			alert("아이디를 입력해 주세요");

			break;

		case 3:

			alert("비밀번호를 입력해 주세요");

			break;

		case 4:

			alert("비밀번호를 다시 한번 더 입력 해 주세요");

			break;

		}

	}else{

		if(checkPassword())

			_submit.submit();

		else

			alert("비밀번호를 확인해 주세요")

	}

}



function checkDupID()

{



	var p1 = document.getElementById("personalNickname");



	if(!p1.value){

		alert("사용하실 아이디를 입력해 주세요.");

		p1.focus();

		return;

	}else{

		ref = "idcheck.php";

		ref = ref + "?checkid="+p1.value;

		var window_left = (screen.width - 640)/2;

		var window_top = (screen.height  - 480)/2;

		//window.open(ref);

		window.open(ref, "checkIDWin", 'width=250, height=160, status=no, top='+window_top+', left='+window_left+'');

	}

}

</script>



</head>

<body>



<br>

<br>

<table id="thewordtable" align="center" border="0" align="left" width="900">

<tr> <td>

(창세기 1장 1절)<br>

태초에 하나님이 천지를 창조하시니라.

</td> </tr>

<tr> <td>

<br>

(사도행전 4:11 ~ 12)<br>

 이 예수는 너희 건축자들의 버린 돌로서 집 모퉁이의 머릿돌이 되었느니라.<br>

 다른 이로써는 구원을 받을 수 없나니 천하 사람 중에 구원을 받을 만한 다른 이름을 우리에게 주신 일이 없음이라 하였더라.

</td> </tr>

<tr> <td>

<br>

(요한계시록 22장 21절)<br>

주 예수의 은혜가 모든 자들에게 있을지어다. 아멘.

</td> </tr>

</table>



<br>

<br>



<table id="notificationtable" border="0" align="center" width="900">

	<tr>

	<th align="left"> * 광고 게시글은 삭제되며 해당 게시자의 계정 또한 삭제될 수 있습니다.</th>

	</tr>

	<tr>

	<th align="left"> * 이 사이트에 적합하지 않은(음란성, 광고성) 글들은 임의 삭제되며 해당 게시자의 계정 또한 삭제될 수 있습니다. </th>

	</tr>

</table>

<br>



<table align="center" width="900" border="0">

<tr>

<td>

<form id="membershipform" align="left" action="http://localhost/membership_welcome.php?login=yes" method="post">

<label>개인 이메일(예: 개인 이메일@daum.net, 최초 로그인 시 필요한 가입 코드가 기입하신 이메일로 발송됩니다)</label><br>

<input id="emailid" type="text" name="personalemailid" value="">@<input align="left" type="text" name="emailcompany" value="">

<br>

<label>아이디 (아이디는 다른 사람에게 보이는 이름입니다.)</label><br>



<input type="text" name="nickname" value="" id="personalNickname"> <input type="button" onclick="checkDupID()" value="중복 검사">



<br><br>

<label>비밀번호</label><br>

<input type="password" name="pwd" value="" id="personalPwd"><br>

<label>비밀번호 확인</label><br>

<input type="password" name="pwdvalidate" value="" id="personalPwdvalidate">

</form>

</td>

</tr>

<tr align="center">

<td>

<input type="button" onclick="submitForm()" value="가입하기" >

<input type="button" onclick="submitBack()" value="되돌아가기">

</td>

</tr>

</table>



</body>

</html>
