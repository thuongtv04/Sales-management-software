<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<div class="container pb-3">

	<h3 class="title-admin">Danh sách sản phẩm</h3>
	<hr>
	<button id="btn-del-all" class="btn btn-danger float-end ps-3 pe-3">Xóa</button>
	<a href="add-product"><button class="btn btn-success float-end me-3">Thên sản phẩm</button></a>
	
	

	<table id="myTable-product"
		class="table-product table border border-4 text-center">
		<tr class="header bg-dark text-white text-center">
			<th><i>Chọn</i><input type="checkbox" id="check-all" /></th>
			<th class="border" style="width: 20%">Tên sản phẩm</th>
			<th class="border" style="width: 10%">Phân loại</th>
			<th class="border" style="width: 10%">giá bán</th>
			<th class="border" style="width: 25%">ảnh minh họa</th>
			<th class="border" style="width: 20%">Mô tả</th>
			<th class="border" style="width: 15%">chỉnh sửa/ xóa</th>
		</tr>
		<c:forEach var="o" items="${listP}" varStatus="row">
			<tr class="align-middle">
				<td><input type="checkbox" value="${o.productID}"></td>
				<td class="border"><h6>${o.name}</h6></td>
				<td class="border">${o.type}</td>
				<td class="border">${o.price}VNĐ</td>
				<td class="border"><img style='width: 100%'
					src="<c:url value='/${o.src}' />"></td>
				<td class="border">${o.description}</td>
				<td class="border"><a class="text-success ms-4 me-2"
					href="edit-product?id=${o.productID}"><span
						data-feather="edit-2"></span> </a> <a
					href="del-product?id=${o.productID}"><span
						data-feather="trash-2" class="text-danger ms-3"></span></a></td>
			</tr>
		</c:forEach>
	</table>
	<c:choose>
		<c:when test="${mes=='succes'}">
			<h5 class="text-success">
				<i>Xoá sản phẩm thành công</i>
			</h5>
		</c:when>
		<c:when test="${mes=='fail'}">
			<h5 class="text-danger text-center">
				<i>Không thể xóa sản phẩm đã bán ra</i>
			</h5>
		</c:when>
	</c:choose>
	<div id="page-product" class="text-center">
		<h5>Page:</h5>
		<c:forEach var="i" begin="1" end="${maxPage}">
			<a class="border p-1" id="${i}" href="show-product?index=${i}">${i}</a>
		</c:forEach>
	</div>
</div>


<!-- ------------ ------------cửa sổ xác nhận xóa sản phẩm ------------------------>

<div id="window-del-product" style="display: none">
	<h6 class="text-center text-white pb-2">Bạn muốn xóa sản phẩm đã chọn?</h6>
	<p class="text-white text-center"><i id="mes-er"></i></p>
	<br> 
	<input id="btn-accept-del" class="rounded text-info bg-dark ms-5 mt-2" type="submit" value="Xác nhận"> 
	<input id="btn-cancel-del" class="rounded text-danger bg-dark float-end me-5 mt-2" type="button"  value="Hủy">
</div>

<div id="product-del-succes" style="display: none">
	<p class="text-white text-center">Xóa sản phẩm thành công! </p>
	<a href="show-product?index=1" class="text-center"><button id="btn-success" class="btn btn-info">Trở lại trang trước</button></a>
</div>

<script>
	/*dung script de tao mau do cho cac trang da click vao*/
		document.getElementById('${index}').style.color = "red";
</script>
