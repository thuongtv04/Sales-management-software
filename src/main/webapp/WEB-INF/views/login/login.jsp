<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>dang-nhap</title>
<!-- Bootstrap core CSS -->
<link href="<c:url value='/css/assets/dist/css/bootstrap.min.css'/>" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<c:url value='/css/signin.css'/>" rel="stylesheet">
<style>
span{
	text-shadow: 10px 10px 20px black;
}
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
.form-signin{
	background-color: #9fccc8;
	box-shadow: 5px 5px 25px black;
}
.form-signin h2{
	text-shadow: 5px 5px 5px gray;
}

.form-signin input, button{
	box-shadow: 5px 5px 5px gray;
}
</style>



</head>
<body class="text-center">

	<main class="form-signin rounded">
		<form action="login" method="post">
			<h2 class="mb-3 fw-bold">Đăng nhập</h2>
			
			<div class="form-floating">
				<input type="text" name="userName" class="form-control" id="floatingInput"
					placeholder="tài khoản"> <label for="floatingInput">Tên đăng nhập</label>
			</div>
			<br>
			<div class="form-floating">
				<input type="password" name="password" class="form-control" id="floatingPassword"
					placeholder="mật khẩu"> <label for="floatingPassword">mật khẩu</label>
			</div>
			<span class="text-danger fs-5 fst-italic">${mes}</span>
			<button class="w-100 btn btn-lg btn-primary" type="submit">Đăng nhập</button>
			<p class="mt-5 mb-3">&copy; 2022</p>
		</form>
	</main>
</body>
</html>
