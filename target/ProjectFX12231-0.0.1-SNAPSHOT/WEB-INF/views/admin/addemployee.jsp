<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container" style="height: 82vh">
	<h3 class="text-center title-admin">Thêm nhân viên mới</h3>
	<hr>
	<form:form action="${action}" method="post"
		modelAttribute="newEmployee">

		<table class="table m-auto border border-5 shadow-lg bg-light" style="width:50%">
			<form:input path="employeeID" type="hidden" />
			<tr>
				<td class="fw-bolder">Tên nhân viên</td>
				<td><form:input path="name" style="width: 100%" class="border"/></td>
			</tr>
			<tr>
				<td class="fw-bolder">Tuổi</td>
				<td><form:input path="age"  style="width: 100%" class="border"/></td>
			</tr>
			<tr>
				<td class="fw-bolder">Số điện thoại</td>
				<td><form:input path="numberPhone" style="width: 100%" class="border" /></td>
			</tr>
			<tr>
				<td class="fw-bolder">vị trí làm việc</td>
				<td><form:select path="position">
						<form:option value="Phục vụ" label="Phục vụ" />
						<form:option value="Thu ngân" label="Thu ngân" />
						<form:option value="Pha chế" label="Pha chế" />
					</form:select></td>
			</tr>
			<tr>
				<td class="fw-bolder">giới tính</td>
				<td><form:select path="sex">
						<form:option value="0" label="Nữ" />
						<form:option value="1" label="Nam" />
					</form:select></td>
			</tr>
			<tr>
				<td class="fw-bolder">Ngày vào làm</td>
				<td><form:input type="date" path="startDay" style="width: 100%" class="border" /></td>
			</tr>
			<tr>
				<td class="fw-bolder">Mức lương</td>
				<td><form:input path="salary" style="width: 100%" class="border" /></td>
			</tr>

		</table>
		<br>
		<c:if test="${mesage != null}">
		<div class="row text-center">
			<div class="text-success col-6">${mesage}</div>
			<div class="col-6">
			<h6 class="text-success"><i>Tiếp tục Thêm nhân viên mới</i></h6>
			<ul class="list-inline">				
				<li class="list-inline-item"><a href="add-employee" class="text-success fs-5 me-3 fw-bolder">có</a></li>
				<li class="list-inline-item"><a href="employee" class="text-danger fs-5 ms-3 fw-bolder">không</a></li>
			</ul>
			</div>
			</div>
		</c:if>
		<c:if test="${mesage == null}">
			<input class="btn-add m-auto" type="submit" value="${typeBTN}" />
		</c:if>
	</form:form>
</div>