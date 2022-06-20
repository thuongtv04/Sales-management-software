<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<div class="container overflow-auto" style="height:82vh">
<h3 class="title-admin">Chi tiết giờ công nhân viên</h3>
<i>Mã NV-${listS.get(0).employee.employeeID}</i>
<hr>
<table id="myTable" class="table text-center">
<tr class="bg-dark text-white">
	<th>Ngày</th>
	<th>Tên NV</th>
	<th>SĐT</th>
	<th>Giờ đến</th>
	<th>Giờ về</th>
	<th>Số giờ làm</th>
	<th>Mức lương/giờ</th>
	<th>Lương</th>
	
</tr>
<c:forEach var="o" items="${listS}">
<tr>
	<td>${o.date}</td>
	<td>${o.employee.name}</td>
	<td>${o.employee.numberPhone}</td>
	<td>${o.inTime}</td>
	<td>${o.outTime}</td>
	<td>${o.timeWork}</td>
	<td>${o.employee.salary} VNĐ</td>
	<td>${o.timeWork * o.employee.salary} VNĐ</td>
</tr>	
</c:forEach>
</table>
</div>