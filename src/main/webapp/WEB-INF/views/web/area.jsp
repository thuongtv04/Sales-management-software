<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="row overflow-auto pb-3">
	<table id="myTable" class="mb-3">
		<c:set var="tableWidth" value="3" />
		<c:forEach var="o" items="${listA}" varStatus="row">
			<c:if test="${row.index % tableWidth == 0}">
				<tr>
			</c:if>
			<td><a href="detail-area?id=${o.areaID}"> <c:choose>
						<c:when test="${o.status.equals('Bận')}">
							<button id="${o.areaID}"
								class="btn-area bg-danger text-white rounded mt-2"
								style="width: 90%; height: 90px">
						</c:when>
						<c:otherwise>
							<button id="${o.areaID}" class=" btn-area rounded mt-2"
								style="width: 90%; height: 90px">
						</c:otherwise>
					</c:choose> ${o.name}<br> ${o.status}

					</button>
			</a></td>
			<br>
			<c:if test="${row.index+1 % tableWidth == 0}">
				</tr>
			</c:if>


		</c:forEach>
	</table>
	<hr>
	<i class="mb-1 text-center fw-bolder">DANH SÁCH BÀN</i>
	<hr>
</div>
