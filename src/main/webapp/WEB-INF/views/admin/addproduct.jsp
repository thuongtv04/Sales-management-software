<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container" style="height:82vh">
	<c:choose>
		<c:when test="${action=='add-product'}"> <h3 class="title-admin text-center">Thêm sản phẩm mới</h3></c:when>
		<c:when test="${action=='edit-product'}"><h3 class="title-admin text-center">Cập nhật sản phẩm</h3></c:when>
	</c:choose>

	<form:form id="form-add-product" action="${action}" method="post" modelAttribute="newProduct" enctype="multipart/form-data">

		<hr>
		<br>
		<table class="table m-auto border border-5 shadow-lg bg-light" style="width:50%">
			<form:input path="productID" type="hidden" />
			<tr>
				<td class="fw-bolder">Tên sản phẩm</td>
				<td><form:input name="name" path="name" style="width: 100%" class="border" /></td>
			</tr>
			<tr>
				<td class="fw-bolder">Giá bán</td>
				<td><form:input name="price" path="price" style="width: 100%" class="border" /></td>
			</tr>
			<tr>
				<td class="fw-bolder">Hình ảnh</td>
				<td><input id="image-product" type="file" name="image"/></td>
			</tr>
			<tr>
				<td class="fw-bolder">phân loại</td>
				<td><form:select path="type">
						<form:option value="#" label="Select" />
						<form:option value="đồ uống" label="đồ uống" />
						<form:option value="đồ ăn" label="đồ ăn" />						
					</form:select></td>
			</tr>
			<tr>
				<td class="fw-bolder">Mô tả</td>
				<td><form:textarea style="width: 100%; height: 180px" path="description"/></td>
			</tr>

		</table>
		<br>
		<c:if test="${mesage != null}">
		<div class="row text-center pb-5">
			<div class="text-success col-6">${mesage}</div>
			<div class="col-6">
			<h6 class="text-success"><i>Tiếp tục Thêm Sản phẩm mới</i></h6>
			<ul class="list-inline">				
				<li class="list-inline-item"><a href="add-product" class="text-success fs-5 me-3 fw-bolder">có</a></li>
				<li class="list-inline-item"><a href="products" class="text-danger fs-5 ms-3 fw-bolder">không</a></li>
			</ul>
			</div>
			</div>
		</c:if>
		<c:if test="${mesage == null}">
		<div class="text-center m-auto" style="width: 50%; display: block">
			<input class="btn-add " type="submit" value="${typeBTN}" />
			<input type="reset" id="btn-reset" class="btn btn-info ms-3" value="Reset"/>
		</div>
		</c:if>
		
	</form:form>
	</div>
