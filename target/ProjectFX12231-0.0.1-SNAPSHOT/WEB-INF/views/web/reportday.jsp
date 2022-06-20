<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="container" style="height:82vh">
	<button id="btn-print" class="ps-2 pe-2 rounded-3" data-bs-toggle="tooltip" data-bs-placement="right" title="In báo cáo"><span data-feather="printer"></span></button>
	<div class="m-auto border pb-5">
		<h5 class="text-center mt-3 fw-bolder">BÁO CÁO NGÀY</h5>
		<hr>
		<p class="ms-2 fw-normal">
			Ngày: <i>${date}.</i>
		</p>
		<p class="ms-2 fw-normal">
			Nhân viên báo cáo: <i>${employee.name}.</i>
		</p>
		<hr>
		<table class="m-auto pb-5 border border-5 table table-striped" style="width: 90%">
			<tr class="m-auto border border-3 bg-dark text-white">
				<th>Mã hóa đơn</th>
				<th>Tên Khách hàng</th>
				<th>Nhân viên thanh toán</th>
				<th>Tổng tiền</th>
				<th>Thực thu</th>
			</tr>

			<c:forEach var="o" items="${listB}">
				<tr class="border">
					<td class="border">${o.billID}</td>
					<td class="border">${o.customer.name}</td>
					<td class="border">${o.employee.name}</td>
					<td class="border">${o.totalAmount} VNĐ</td>
					<td class="border">${o.receivableAmount} VNĐ</td>
				</tr>

			</c:forEach>
		</table>
		<hr>
		<div class="float-end">
			<h6>Doanh thu thực: ${totalSales} VNĐ.</h6>
			<i>Đơn vị tính .000 VNĐ</i>
		</div>
	</div>
</div>