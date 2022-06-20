<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!-- cửa sổ đăng ký thành viên -->
<div id="block-window" style="display: none"></div>
<div id="window-add-customer" style="display: none">
	<br>
	<h5>Thêm khách hàng mới</h5>
	<hr>
	<form action="add-customer" method="post">
		<table class="border m-auto">
			<tr>
				<td>Tên khách hàng:</td>
				<td><input type="text" name="customerName" /></td>
			</tr>
			<tr>
				<td>Số điện thoại:</td>
				<td><input type="text" name="numberPhone" /></td>
			</tr>
		</table>
		<hr>
		<br>
		<button class="bg-success text-white border-0 rounded p-1"
			type="submit">
			<span data-feather="check"></span>Thêm
		</button>
		<button id="btn-cancel-add" class="bg-danger text-white border-0 rounded p-1" type="button">
			<span data-feather="x"></span>Hủy
		</button>
	</form>
	
</div>