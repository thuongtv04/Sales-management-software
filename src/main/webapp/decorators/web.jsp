<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<title>ban-hang</title>
<!-- Bootstrap core CSS -->
<link href="<c:url value='/css/assets/dist/css/bootstrap.min.css'/>"rel="stylesheet">
<link href="<c:url value='/css/custom/css/web.css'/>"rel="stylesheet">
<style>
</style>


<!-- Custom styles for this template -->
<link href="<c:url value='/css/dashboard.css'/>" rel="stylesheet">
</head>
<body>
	<%@include file="/common/web/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<!-- this dashboard -->
			<%@include file="/common/web/dashboard.jsp"%>

			<!-- this body -->
			
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<br>
				<dec:body />
			</main>
		</div>
	</div>
	<%@include file="/common/web/footer.jsp"%>

	<script
		src="<c:url value='/css/assets/dist/js/bootstrap.bundle.min.js'/>"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"
		integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"
		integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha"
		crossorigin="anonymous"></script>
	<script src="<c:url value='/css/dashboard.js'/>"></script>
	<script src="<c:url value='/css/custom/js/web.js'/>"></script>


	<script>
		/*
		 * ajax lay gia tri ban click
		 */
/* 		function areaClick(idA) {
			console.log(idA);
			$.ajax({
				url : "http://localhost:8080/ProjectFX12231/api/area",
				type : "GET",
				data : {
					id : idA
				},
				success : function(value) {
					if (value == idA)
						duongdan = window.location.href;
					window.location = duongdan;

				}
			});
		}

		function addProduct(idP) {
			console.log(idP);
			$.ajax({
				url : "http://localhost:8080/ProjectFX12231/api/oder",
				type : "GET",
				data : {
					id : idP
				},
				success : function(value) {
					if (value == idP)
						duongdan = window.location.href;
					window.location = duongdan;
				}
			});
		}
		/*
		 * thanh toán tiền
		 */
		/*function payMent(idA) {
			$.ajax({
				url : "http://localhost:8080/ProjectFX12231/api/pay",
				type : "GET",
				data : {
					id : idA
				},
				success : function(value) {
					if (value == idA)
						duongdan = window.location.href;
					window.location = duongdan;
				}
			});
		}
 */
		
	</script>
</body>
</html>
