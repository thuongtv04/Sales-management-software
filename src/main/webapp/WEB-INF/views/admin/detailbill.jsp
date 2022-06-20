<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<div class="overflow-auto container pb-3" style="height:82vh">
<h3 class="title-admin">Chi tiết doanh thu theo ngày</h3>
<hr>
<table id="myTable" class="table text-center">
<tr>
	<th>Mã HĐ</th>
	<th>Tên KH</th>
	<th>Loại</th>
	<th>SĐT</th>
	<th>Tên NV</th>
	<th>Ngày</th>
	<th>Tổng tiền</th>
	<th>Giảm giá(%)</th>
	<th>Thực thu</th>
	
</tr>
<c:forEach var="o" items="${listB}">
<tr>
	<td>${o.billID}</td>
	<td>${o.customer.name}</td>
	<td>${o.customer.customerType.name}</td>
	<td>${o.customer.numberPhone}</td>
	<td>${o.employee.name}</td>
	<td>${o.receivedDate}</td>
	<td>${o.totalAmount}</td>
	<td>${o.customer.customerType.discount}</td>
	<td>${o.receivableAmount}</td>
</tr>	
</c:forEach>
</table>
</div>