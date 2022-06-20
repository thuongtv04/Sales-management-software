<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


	<div class="container" style="height:82vh">
		<h5 class="title-admin text-center">Thông tin chi tiết nhân viên</h5>
		<hr>
		<table class="table m-auto border border-5 shadow-lg" style="width:40%">
			<tr>
				<td class="border fw-bolder">Mã nhân viên</td>
				<td>${employee.employeeID}</td>
			</tr>
			<tr>
				<td class="border fw-bolder">Tên nhân viên</td>
				<td>${employee.name}</td>
			</tr>
			<tr>
				<td class="border fw-bolder">Số điện thoại</td>
				<td>${employee.numberPhone}</td>
			</tr>
			<tr>
				<td class="border fw-bolder">Tài khoản đăng nhập</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td class="border fw-bolder">ngày vào làm</td>
				<td>${employee.startDay}</td>
			</tr>
			<tr>
				<td class="border fw-bolder">Vị trí làm việc</td>
				<td>${employee.position}</td>
			</tr>
			<tr>
				<td class="border fw-bolder">Lương cơ bản</td>
				<td>${employee.salary}</td>
			</tr>

		</table>
		<br>
		<h5 class="text-success text-center">${mesage}</h5>
		<c:if test="${mesage==null}">
		<form:form action="rs-user" method="get">
		<input type = "hidden" name ="id" value="${user.userID}">
			<input class="btn-add m-auto" style="display:block" type = "submit" value= "reset mật khẩu">
		</form:form>
		</c:if>
	</div>
