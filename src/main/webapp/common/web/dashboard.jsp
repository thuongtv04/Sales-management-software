<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="sidebarMenu"
	class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">

	<div class=" position-sticky pt-3">
		<div class="dropdown mt-0  p-1">
		<c:choose>
				<c:when test="${userLogin == null}">
					<a class="ms-4 text-danger fw-bolder fs-5" href="/ProjectFX12231/login"><span data-feather="log-in"></span> đăng nhập</a>
				</c:when>
				<c:otherwise>
			<a href="#"
				class="d-flex align-items-center text-decoration-none dropdown-toggle"
				id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
				<img src="<c:url value='/css/images/employee/admin.jpg'/>" width="80px" height="50px"
				class="rounded-circle me-2"> <strong>${userLogin.userName}</strong>
			</a>

			<ul class="dropdown-menu dropdown-menu-dark text-small shadow"
				aria-labelledby="dropdownUser1">
				<li><a class="dropdown-item" href="#"><span data-feather="meh"></span> Thông tin cá nhân</a></li>
				<li><a class="dropdown-item" href="#" id="btn-change-password"><span data-feather="key"></span> Đổi mật khẩu</a></li>
				<li><hr class="dropdown-divider"></li>
				<li><a class="dropdown-item" href="logout"><span data-feather="log-out"></span> Đăng xuất</a></li>
			</ul>
			</c:otherwise>
			</c:choose>
			
			<div class="text-center" id="window-change-password" style="display:none">
				<h4>Đổi mật khẩu</h4>
				<hr>
				<form action="change-password" method="post">
					<table class="m-auto p-auto">
						<tr>
							<td>Tên tài khoản</td>
							<td>: <input type="text" id="userName"><span class="text-danger">*</span></td>
							
						</tr>
						<tr>
							<td>Mật khẩu cũ</td>
							<td>: <input type="password" id="password"><span class="text-danger">*</span></td>
							
						</tr>
						<tr>
							<td>Mật khẩu mới</td>
							<td>: <input type="password" id="newPassword"><span class="text-danger">*</span></td>
							
						</tr>
						<tr>
							<td>Nhập lại mật khẩu</td>
							<td>: <input type="password" id="repeartPassword"><span class="text-danger">*</span></td>
							
						</tr>
						</table>
						<span id="error-mes" class="text-danger"></span>
						<br>
							<button id="btn-update-password" class="bg-success text-white border-0 rounded p-1" type="button"><span data-feather="check"></span>Cập nhật</button>
					<button id="btn-cancel" class="bg-danger text-white border-0 rounded p-1" type="button"><span data-feather="x"></span>Hủy</button>
				</form>
			</div>
			<div id="window-change-succes" style="display:none">
				<h5 class="mt-2">Thay đổi mật khẩu thành công</h5>
				<br>
				<button id="btn-close" class="text-white border-0 rounded p-1">Đóng</button>
			</div>
		</div>

		<hr>
		<div class="d-flex flex-column flex-grow-0 pb-5">
			<ul class="nav d-flex flex-column mb-auto">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="home"> <span data-feather="home"></span>
						Trang chủ
				</a></li>

				<li class="nav-item"><a class="nav-link" href="selling"> <span
						data-feather="shopping-cart"></span> Bán hàng
				</a></li>
				<li class="nav-item"><a class="nav-link" id="btn-add-customer" href="#"> <span
						data-feather="users"></span> Đăng kí thành viên
				</a></li>
				<li class="nav-item"><a class="nav-link" href="report"> <span
						data-feather="dollar-sign"></span> Tổng kết ca
				</a></li>
			</ul>

		</div>
		<hr>
		<div class="ms-2">
		<h5 >Quyền lợi thành viên:</h5>
		<ul>
			<li>300 điểm : VIP (giảm 20%)</li>
			<li>200 điểm: Thân thiết (giảm 10%)</li>
			<li>100 điểm: Thành viên (giảm 5%)</li>
			<li><100 điểm: khách mới (giảm 2%)</li>
		</ul>
		<i class="text-danger fw-bolder">20.000 VNĐ = 1 điểm.</i>
		</div>
		<hr>
		<c:if test="${userLogin!=null}">
		<c:choose>
			<c:when test="${inTime==null}">
				<a class="m-auto" href="check-in?id=${userLogin.employee.employeeID}"><input class="btn-in p-2 ms-5" type="button" value="Check in" style="width:100px;"></a>
			</c:when>
			<c:otherwise>
				<i class="text-center ms-4">Giờ vào: ${inTime}</i><br><br>
				<a class="m-auto" href="check-out" ><input class="btn-out bg-danger p-2 ms-5" type="button" value="Check Out" style="width:100px;"></a>
			</c:otherwise>
		</c:choose>
		</c:if>
		


	</div>

</nav>
