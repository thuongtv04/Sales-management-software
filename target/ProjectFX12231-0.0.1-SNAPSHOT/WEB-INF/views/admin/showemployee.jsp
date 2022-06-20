<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container pb-3 overflow-auto" style="height:82vh">
		<h5 class="title-admin">Danh sách nhân viên</h5>
		<hr>
	<table id="myTable" class="table-employee table border border-4 m-auto ">
				<tr class="header bg-dark text-white text-center">
				<th class="border" style="width:20%">Tên nhân viên</th>
				<th class="border" style="width:15%">vị trí làm việc</th>
				<th class="border" style="width:20%">Ngày vào làm</th>
				<th class="border" style="width:10%">Lương cơ bản</th>
				<th class="border" style="width:15%">Chi tiết / xóa</th>
				
			</tr>
		<c:forEach var="o" items="${listE}" varStatus="row">
		<c:if test="${!o.name.equals('admin')}">
			<tr>
				<td>${o.employeeID}: ${o.name}
				</td>
				<td>${o.position}</td>
				<td>${o.startDay}</td>
				<td>${o.salary}</td>
				<td>
					<a class="text-success ms-5" href="detail-employee?id=${o.employeeID}"><span data-feather="loader"></span> </a>
					<a class="btn-del-employeee ms-4" onclick="delEmployee('${o.employeeID}')" href="#">
					<input type="hidden" value="${o.employeeID}"/>
					<span data-feather="trash-2" class="text-danger"></span></a>
					
					
				</td>
			</tr>
			</c:if>
		</c:forEach>
	</table>
</div>
	<!--  cửa sổ xóa nhân viên -->
<div id="window-del-employee" style="display: none">
	<h6 class="text-center">Bạn muốn xóa nhân viên này?</h6>
	<hr>
	<input id="btn-accept-del-emp"
		class="rounded text-info bg-dark ms-5 mt-2" type="submit"
		value="Xác nhận"> <input
		class="rounded text-danger bg-dark float-end me-5 mt-2" type="button"
		id="btn-cancel-del-emp" value="Hủy">
</div>
<div id="employee-del-success" style="display: none">
	<p class="text-white text-center">Đã xóa</p>
	<a href="show-employee" class="text-center"><button id="btn-success" class="btn btn-info">Đóng</button></a>
</div>
<script>
	
</script>

</html>