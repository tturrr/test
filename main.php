<?php

session_start();

?>

<!DOCTYPE php>
<php lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- Custom styles for this template -->
    <link href="css/clean-blog.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Electrical Service Form Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
    Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    		function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->

    <!-- //custom-theme -->
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

    <!-- js -->
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script language="javascript"></script>
    <!-- //js -->

    <!-- google fonts -->
    <link href="//fonts.googleapis.com/css?family=Source+Sans+Pro:200,200i,300,300i,400,400i,600,600i,700,700i,900,900i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">
    <!-- google fonts -->
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <script language="javascript">
    function checkemail(_emailinfo){
    	// check email type.
    	if("" == _emailinfo){
    		alert("이메일 정보를 입력해 주세요!");
    		return false;
    	}
    	// Need to suplement the empty string check code.

    	return true;

    }



    function SubmitLogin(){

      	var _IdArray = document.getElementById("loginform");

    	var emailinfo = _IdArray[0].value;

    	var passinfo = _IdArray[2].value;



    	if(checkemail(emailinfo, _IdArray)){

    		if("" != passinfo){

    			_IdArray.submit();

    		}

    		else{

    			alert("비밀번호를 입력해 주세요!");

    		}

    	}

    }



    function submitMembership(){

    	var _sub = document.getElementById("membershipform");

    	_sub.submit();

    }



    function SubmitLogOut(){

    	var _subaction = document.getElementById("logoutform");

    	_subaction.submit();

    }

    </script>

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand" href="index.php">
          <?php if(isset($_SESSION['testuser'])){
         echo $_SESSION['testuser'].'님의 블로그';
       }else { echo '블로그'; }?></a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="index.php">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="about.php">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="post.php">Sample Post</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contact.php">Contact</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="register.php">회원가입</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Header -->

      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="post-heading">

            </div>
          </div>
        </div>
      </div>


    <!-- Post Content -->
    <article >
      <div class="top-content">

          <div class="inner-bg">
              <div class="container">

                  <div class="row">
                      <div class="col-sm-6 col-sm-offset-3 form-box" >
                        <div class="form-top">
                          <div class="form-top-left">
                            <h3>로그인</h3>
                              <p>이메일과 비밀번호를 입력해주세요.</p>
                          </div>
                          <div class="form-top-right">
                            <i class="fa fa-key"></i>
                          </div>
                          </div>
                          <div class="form-bottom">

                            <?php

                            	if($_SESSION['testuser'])

                            	{
                            	?>

                            	<?php } else { ?>

                            	<form id="loginform" align="left" action="http://13.125.107.155/login_action.php?login=yes" method="post">

                            		<label>이메일</label><br>

                                    <input type="text" name="identification" value="">@<input align="left" type="text" name="emailcompany" value=""> <br>

                            		<label>비밀번호</label><br>

                            			<input type="password" name="pwd" value=""><br>

                            	</form>

                            	<form id="membershipform" action="http://13.125.107.155/register.php" method="post">
                               </form>

                            	<?php } ?>

                            	</form>

                            	</td></tr>

                            	<tr align="left"><td>

                            	<br>

                            	<?php

                            	if($_SESSION["testuser"])

                            	{

                            	?>

                            	<input type="button" value="로그 아웃하기" onclick="location.href='login_action.php?logout=yes';" />
                            	<?php } else { ?>
                            	<input type="button" onclick="SubmitLogin()" value="로그인하기" />

                            	<input type="button" onclick="submitMembership()" value="회원 가입하기" />

                            	<?php } ?>


                      </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-6 col-sm-offset-3 social-login">
                        <h3>.. 비밀번호 찾기:</h3>

                      </div>
                  </div>
              </div>
          </div>

      </div>

    </article>

    <hr>

    <!-- Footer -->


    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/clean-blog.min.js"></script>

    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>


  </body>

</php>
