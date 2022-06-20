<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="row pb-3"style="height: 82vh">
	<div class="col-4 overflow-auto" style="height:58vh">
	<table id="myTable" class="mb-3 bg-secondary ">
	
		<c:set var="tableWidth" value="3" />
		<c:forEach var="o" items="${listA}" varStatus="row">
			<c:if test="${row.index % tableWidth == 0}">
				<tr>
			</c:if>
			<td style="width: 30%">
					<button class="btn-edit-area rounded mt-2 ms-1"
						style="width: 90%; height: 110px">${o.name}
					<br>--------
					<p>Mã bàn : ${o.areaID}	</p>
					</button>
			</td>
			<c:if test="${row.index+1 % tableWidth == 0}">
				</tr>
			</c:if>
		</c:forEach>
		<tr><td><button id="btn-new-area" class="rounded m-auto ms-2"
			style="width: 50%; height: 40px"> +</button>
			</td></tr>
		
	</table>
	</div>
	<div class="col-7 bg-secondary text-white ms-3 text-center" id="window-edit-area" style="display:none;height:58vh">
		<h5 class="mt-3 text-center">Điền thông tin bàn</h5>
		<hr>
	<form action="edit-area" method="post">
		<table class="table bg-light m-auto mt-5 mb-5" style="display:block;width:20vw">
			<tr>
				<td>Mã bàn</td>
				<td><input type="text" name="areaID" /></td>
			</tr>
			<tr>
				<td>Tên bàn</td>
				<td><input type="text" name="areaName"/></td>
			</tr>
		</table>
		<i class="text-white ">Lưu ý: điền đúng mã bàn muốn thay đổi thông tin</i>
		<hr>
		<button style="display:block" class="m-auto btn-add" type="submit">Cập nhật</button>
		</form>
		
	</div>
<!-- 	<div class="col-7 bg-secondary text-white ms-3 text-center" id="window-new-area" style="display:none;height:58vh">
		<h5 class="mt-3 text-center">Điền thông tin bàn</h5>
		<hr>
	 <form action="new-area" method="post">
		<table class="table bg-light m-auto mt-5 mb-5" style="display:block;width:20vw">
			<tr>
				<td>Mã bàn</td>
				<td><input type="text" name="areaID" /></td>
			</tr>
			<tr>
				<td>Tên bàn</td>
				<td><input type="text" name="areaName"/></td>
			</tr>
		</table>
		<i class="text-white ">Lưu ý: điền đúng mã bàn muốn thay đổi thông tin</i>
		<hr>
		<button style="display:block" class="m-auto p-1 rounded bg-danger border border-none" type="submit">Cập nhật</button>
		</form>
	</div> -->
	<hr>
	<i class="mb-1 text-center fw-bolder">DANH SÁCH BÀN</i>
	<hr>
</div>

<!-- cửa sổ tạo bàn mới -->
<div id="window-create-area" class="bg-black" style="display:none;">
<a id="close" class="float-end fw-bold bg-dark" href="#"><span data-feather="x" ></span></a>
<span id="mesage" class="bg-white text-success"></span>
<p class="mt-3">Nhập tên bàn</p><input id="area-name" type="text" />
<button id="btn-create-area" style="display:block" class="m-auto btn-add" type="button">Tạo</button>
</div>

