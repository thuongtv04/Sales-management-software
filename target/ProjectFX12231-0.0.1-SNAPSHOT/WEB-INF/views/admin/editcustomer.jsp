<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container" style="height:82vh">
	<h3 class="title-admin text-center">Cập nhật thông tin khách hàng</h3>

	<form:form action="edit-customer" method="post" modelAttribute="customer">

		<hr>
		<br>
		<table class="table m-auto border border-5 shadow-lg bg-light" style="width:50%">
			<form:input path="customerID" type="hidden" />
			<form:input path="customerType.customerTypeID" type ="hidden"/>
			<tr>
				<td class="fw-bolder">Tên khách hàng</td>
				<td><form:input path="name" style="width: 100%" class="border"/></td>
			</tr>
			<tr>
				<td class="fw-bolder">Số điện thoại</td>
				<td><form:input path="numberPhone" style="width: 100%" class="border" /></td>
			</tr>
			<tr>
				<td class="fw-bolder">Điểm tích lũy</td>
				<td><form:input path="points" style="width: 100%" class="border"/></td>
			</tr>

		</table>
		<br>
		<c:if test="${mesage != null}">
			<h3 class="text-success text-center">${mesage}</h3>
		</c:if>
		<c:if test="${mesage == null}">
			<input class="btn-add m-auto" type="submit" value="Cập nhật" />
		</c:if>
	</form:form>
	</div>
    