<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-9 bg-black-50 text-white">
			<div class="row">
				<div id="showArea" class="bg-dark col-7 pb-3" style="height: 90vh">
					<h5>${areaClick.name}</h5>
					<c:if test="${areaClick.name != null}">
						<button id="btn-add-product" class=" rounded-pill" type="button"><span class="pb-1" data-feather="plus"></span></button>
						<input class="float-end" id="btn-change-area" type="button" value="chuyển bàn">
						
						<hr>
						<div class="overflow-auto" style="height: 50vh">

							<c:forEach var="o" items="${listOder}">
								<c:if test="${o.oderID == areaClick.areaID}">
									<table>
										<tr>
											<th style="width: 50%">Tên sản phẩm</th>
											<th style="width: 25%">Giá bán</th>
											<th style="width: 30%">Số lượng</th>
											<th>Xóa</th>
										</tr>
										<tr>
										</tr>
										<c:forEach var="i" items="${o.listItems}">

											<tr class="border border-white">
												<td><i>${i.product.name}</i></td>
												<td><i>${i.product.price}</i></td>
												<td><a
													href="add-product?action=del&pID=${i.product.productID}"><button
															class="rounded">-</button></a> ${i.quantity} <a
													href="add-product?action=add&pID=${i.product.productID}"><button
															class="rounded">+</button></a></td>
												<td><a
													href="add-product?action=remove&pID=${i.product.productID}"><span
														data-feather="trash-2"></span></a></td>

											</tr>
										</c:forEach>
									</table>

								</c:if>
							</c:forEach>

						</div>
						<hr style="margin:5px">
						<div class="row">
							<div class="col-7 pb-3">
								<h5>Tổng tiền: ${totalAmount} VNĐ</h5>
								<c:choose>
									<c:when test="${customer.name.equals('nocheck')}">
										<i>Giảm giá: 0%</i>
										<h6>Tiền phải thu: ${totalAmount} VNĐ</h6>
									</c:when>
									<c:when test="${customer!=null}">
										<i>Giảm giá: ${discount}%</i>
										<h6>Tiền phải thu: ${receivableAmount} VNĐ</h6>
									</c:when>
								</c:choose>
								<form action="pay" method="post">
									<input class="rounded w-75" type="text" name="numberPhone"
										id="number-phone" placeholder="SĐT tích điểm"
										value="${customer.numberPhone}">
									<button type="button" id="btn-search-number"
										class="rounded-circle">
										<span data-feather="search"></span>
									</button>
									<input type="hidden" value="${areaClick.areaID}" name="areaID">
									
									<!-------------------------------- cửa sổ xác nhận thanh toán ----------------------------------------------->
									<div id="block-window-pay" style="display: none"></div>
									<div id="window-pay" style="display: none">
										<p class="text-center">Xác nhận thanh toán</p>
										<i id="customer-name"></i> <br> <input
											id="btn-accept-pay"
											class="rounded text-info bg-dark ms-5 mt-2" type="submit"
											value="Xác nhận"> <input
											class="rounded text-danger bg-dark float-end me-5 mt-2"
											type="button" id="btn-cancel-pay" value="Hủy">
									</div>
								</form>
								
							</div>
							<div class="col-5">
							
								<c:choose>
									<c:when test="${customer.name.equals('nocheck')}">
										<p class="text-danger">Khách hàng không tồn tại</p>
									</c:when>
									<c:when test="${customer!=null}">
										<p>
											Tên KH: <i>${customer.name}</i>
										</p>
										<p>Điểm tích lũy :${customer.points}</p>
									</c:when>
								</c:choose>
								<input class="float-end" type="button" value="Thanh toán" id="btn-pay">
							</div>
						</div>
					</c:if>
				</div>

				<!--------------------------------------  danh sách sản phẩm ------------------------------------------------>
				<div id="list-product"
					class="container p-3 bg-warning col-5 overflow-auto"
					style="height: 90vh; display: none">
					<input class="border border-3" style="width: 96%" type="text"
						id="myInput" onkeyup="myFunction()" placeholder="Nhập tên...">
					<hr>
					<table id="myTable" class="table table-hover">
						<c:forEach var="p" items="${listP}">

							<tr class="mb-2">
								<td style="width: 50%">${p.name}</td>
								<td style="width: 30%">${p.price}</td>
								<td style="width: 20%"><a
									href="add-product?action=add&pID=${p.productID}"><button
											class="rounded">+</button></a></td>
							</tr>

						</c:forEach>
					</table>
				</div>
			</div>

		</div>
		<!-------------------------------- cửa sổ đổi bàn ----------------------------------------------->
									<div id="window-change-area" style="display: none">
									<h4>Chọn bàn muốn chuyển</h4>
									<hr>
										<div class="row overflow-auto pb-3">
											<table id="myTable" class="mb-1">
												<c:set var="tableWidth" value="3" />
												<c:forEach var="o" items="${listA}" varStatus="row">
													<c:if test="${row.index % tableWidth == 0}">
														<tr>
													</c:if>
													<td><a href="change-area?id=${o.areaID}"> 
														<c:if test="${o.status.equals('Trống')}">
															<button id="${o.areaID}" class=" btn-area rounded mt-2" style="width: 90%; height: 90px">
																	${o.name}<br> ${o.status}
															</button>
														</c:if>
													</a></td>
													<br>
													<c:if test="${row.index+1 % tableWidth == 0}">
														</tr>
													</c:if>


												</c:forEach>
											</table>
											<hr>
											<button type="button" id="btn-cancel-change">Huỷ</button>
											
										</div>
									</div>
		<!-------------------------  danh sách bàn ------------------------------------------------------------------>
		<div class="col-3">
			<%@include file="area.jsp"%>
		</div>
		<!-- ------------------------Thêm khách hàng mới---------------------------------------------------------- -->
		<%@include file="addcustomer.jsp"%>
	</div>
</div>

