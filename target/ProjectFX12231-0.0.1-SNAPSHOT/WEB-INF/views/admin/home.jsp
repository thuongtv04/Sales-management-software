<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<div  id="block-home">
      <!-- <canvas class="my-4 w-100" id="myChart" width="600" height="180"></canvas> -->
      <h3 class="text-white pt-1 text-center">Top 5 mặt hàng bán chạy nhất</h3>
      <hr>
      <div class="container pb-5">
        <table style="width:80%; display:block" id ="myTable" class="table text-center m-auto">
            <tr class="bg-secondary text-white fs-5 border border-4">
              <th style="width:30%">Tên SP</th>
              <th style="width:30%">Ảnh</th>
              <th style="width:20%">Giá bán</th>
              <th style="width:20%">Số lượng bán</th>
            </tr>
          <c:forEach var="o" items="${listBD}">
            <tr class="bg-light align-middle">
              <td><i class="fs-5">${o[0]}</i></td>
              <td ><img style="width:100%"src="<c:url value='/${o[1]}'/>"></td>
              <td>${o[2]}00 VNĐ</td>
              <td>${o[3]}</td>
            </tr>
            </c:forEach>
        </table>
      </div>
</div>