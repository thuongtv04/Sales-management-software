<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!doctype html>
<html lang="vie">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>admin</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript"
    src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.js"></script>

<!-- Bootstrap core CSS -->
<link href="<c:url value='/css/assets/dist/css/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/custom/css/admin.css'/>" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="<c:url value='/css/dashboard.css'/>" rel="stylesheet">
<script src="<c:url value='/css/custom/js/admin.js'/>"></script>
<style>
.error{
	color: red;
	font-weight: bold;
	font-style: italic;
}
</style>



</head>
<body>
	<%@include file="/common/admin/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<!-- this dashboard -->
			<%@include file="/common/admin/dashboard.jsp"%>

			<!-- this body -->
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<br>
				<dec:body />
			</main>
		</div>
	</div>
	<%@include file="/common/admin/footer.jsp"%>

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
	<script>
	
	//validate form
	$("#form-add-product").validate({
        rules: {
          name: "required",
          price: "number",
        }, 
        messages: {
          name: "Vui lòng nhập tên sản phẩm",
          price: "Vui lòng Nhập giá là số"
        }
      });
	// lọc theo tìm kiếm
	function myFunction() {
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable-product");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[1];
			if (td) {
				txtValue = td.textContent || td.innerText;
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
	
	</script>

</body>
</html>
