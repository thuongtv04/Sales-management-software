<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="container pb-3" style="height: 82vh">
	<h3 class="title-admin">Danh sách khách hàng</h3>
	<hr>
	<table id="myTable" class="table border border-4 m-auto  text-center">
		<tr class="header bg-dark text-white text-center">
			<th class="border" style="width: 20%">Tên Khách hàng</th>
			<th class="border" style="width: 15%">Số điện thoại</th>
			<th class="border" style="width: 10%">Điểm tích lũy</th>
			<th class="border" style="width: 15%">Loại khách hàng</th>
			<th class="border" style="width: 10%">Chỉnh sửa</th>
		</tr>
		<c:forEach var="c" items="${listC}">
			<tr>
				<td>${c.name}</td>
				<td>${c.numberPhone}</td>
				<td>${c.points}</td>
				<td>${c.customerType.name}</td>
				<td><a class="text-success m-auto" href="edit-customer?id=${c.customerID}"><span data-feather="edit-2"></span></a>
					
			<tr>
		</c:forEach>
	</table>
</div>