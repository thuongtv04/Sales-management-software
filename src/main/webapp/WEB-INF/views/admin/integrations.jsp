<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<h3 class="title-admin text-center">Thống kê</h3>
<hr>
<div class="container overflow-auto pb-3" style="height:72vh">
	<h5 class="title-admin">Doanh thu bán hàng trong tháng: ${totalSales} VNĐ</h5>
	<button id="btn-show-sales">Xem</button>
	  
	<button id="btn-hidden-sales" class="float-end ps-2 pe-2">Ẩn</button>
	<div id="tab-sales" style="display: none">
	<input class="form-control form-control-dark bg-light" type="text" id="searchBill" onkeyup="checkBill()"
  placeholder="[Tìm kiếm hóa đơn theo ngày]" aria-label="Search">
		<table id="tableSales" class="table border border-4 text-center">
			<tr class="bg-dark text-white">
				<th>Ngày</th>
				<th>Tổng tiền</th>
				<th>Thực thu</th>
				<th>Chi tiết</th>
			</tr>
			<c:set var="sum" value="0"/>
			<c:forEach var="o" items="${listB}">
				<tr>
					<td>${o[0]}</td>
					<td>${o[1]}</td>
					<td>${o[2]}</td>
					<td><a href="detail-bill?date=${o[0]}"><span data-feather="eye"></span></a></td>
					
				</tr>
			</c:forEach>


		</table>
	</div>
	<br>
	<hr>
	<h5 class="title-admin">Lương nhân viên trong tháng: ${totalSalary} VNĐ</h5>
	<button id="btn-show-salary">Xem</button>
	<button id="btn-hidden-salary" class="float-end ps-2 pe-2">Ẩn</button>
	
	<div id="tab-salary" style="display: none">
		<input class="form-control form-control-dark bg-light" type="text" id="searchSalary" onkeyup="checkSalary()"
  placeholder="[Tìm kiếm nhân viên theo tên]" aria-label="Search">
		<table id="tableSalary" class="table border border-4 text-center">
			<tr class="bg-dark text-white">
				<th>Tên Nhân viên</th>
				<th>Mã Nhân Viên</th>
				<th>Lương / giờ</th>
				<th>Giờ công</th>
				<th>Tổng lương</th>
				<th>Chi tiết</th>
			</tr>
			<c:forEach var="i" items="${listT}">
				<tr>
					<td>${i[1]}</td>
					<td>${i[0]}</td>
					<td>${i[2]}</td>
					<td>${i[3]}</td>
					<td>${i[2]*i[3]}</td>
					<td><a href="detail-salary?id=${i[0]}"><span data-feather="eye"></span></a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</div>