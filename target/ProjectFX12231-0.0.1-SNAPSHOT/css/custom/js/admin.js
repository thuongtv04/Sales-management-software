$(document).ready(function() {
	
	
	//đóng cửa sổ xóa nhân viên
	$("#btn-cancel-del-emp").click(function(){
		$("#block-window").slideUp();
		$("#window-del-employee").slideUp();
	})
	
	

	//đóng mở cửa sổ xóa sản phẩm
	$("#btn-del-all").click(function(){
		$("#block-window").slideDown();
		$("#window-del-product").slideDown();
	})
	$("#btn-cancel-del").click(function(){
		$("#block-window").slideUp();
		$("#window-del-product").slideUp();
	})
	
	
	//xử lý xóa sản phẩm
	$("#btn-accept-del").click(function(){
 		var arr = [];
			$(".table-product td input:checked").each(function(){
			arr.push($(this).val())
		})
		var arrayToString = JSON.stringify(arr);
			
			/* JSON.stringify(Object.assign({}, arr));	 */
		$.ajax({
				url: "/ProjectFX12231/api/del-product",
				type:"POST",
				/* dataType: 'json', */
				data:{
					pID: arrayToString,
				},
				success: function(value){
					if(value =='fail'){
						$("#mes-er").html("Không thể xóa vì có sản phẩm đã bán ra.Vui lòng chọn chỉnh sửa hoặc xóa từng sản phẩm.");
					}if(value =='success'){
						$("#product-del-succes").slideDown();
						$("#window-del-product").slideUp();
					}
				}
			})

	})
	
	//check toàn bộ checkbox
	$("#check-all").change(function(){
		if(this.checked){
			$(".table-product input").each(function(){
				$(this).attr("checked",true);
			})
		}else{
			$(".table-product input").each(function(){
				$(this).attr("checked",false);
			})
		}
	})
							
							
							//mở cửa sổ chỉnh sửa thông tin bàn
							$(".btn-edit-area").click(function(){
								$("#window-edit-area").slideDown();
							})
							//mở cửa sổ tạo bàn mới
							$("#btn-new-area").click(function(){
								$("#window-edit-area").slideUp();
								$("#block-window").slideDown();
								$("#window-create-area").slideDown();
							})
							//đóng cửa sổ tạo bàn
							$("#close").click(function(){
								$("#block-window").slideUp();
								$("#window-create-area").slideUp();
							})
							
							//ajax tạo bàn mới
							$("#btn-create-area").click(function(){
								var name = $("#area-name").val();
								$.ajax({
									url: "/ProjectFX12231/api/create-area",
									type:"POST",
									data:{
										areaName : name
									},
									success : function(value) {
										if(value == 'succes'){
											$("#mesage").text("Tạo bàn thành công");
											/* link = window.location.href;
											window.location = link; */
										}
									}
								})
							})
							//mở cửa sổ xem bảng lương
							$("#btn-show-salary").click(function(){
								$("#tab-salary").slideDown();
							})
							//đóng cửa sổ xem bảng lương
							$("#btn-hidden-salary").click(function(){
								$("#tab-salary").slideUp();
							})
							//mở cửa sổ xem thống kê doanh thu
							$("#btn-show-sales").click(function(){
								$("#tab-sales").slideDown();
							})
							//đóng cửa sổ xem thống kê doanh thu
							$("#btn-hidden-sales").click(function(){
								$("#tab-sales").slideUp();
							})
							//mở cửa sổ thay đổi mật khẩu
							$("#btn-change-password").click(function() {
								$("#block-window").slideDown();
								$("#window-change-password").slideDown();
							})

							//đóng cửa sổ thay đổi mật khẩu
							$("#btn-cancel").click(function() {
								$("#block-window").slideUp();
								$("#window-change-password").slideUp();
							})
							//đóng cửa sổ đổi mk thành công
							$("#btn-close").click(function(){
								$("#block-window").slideUp();
								$("#window-change-succes").slideUp();
							})
							
							//xử lý thay đổi mật khẩu
							$("#btn-update-password").click(function(event) {
												//ajax
												var user = $("#userName").val();
												var pass = $("#password").val();
												var newPass = $("#newPassword").val();
												var repeatPass = $("#repeartPassword").val();
												$.ajax({
															url : "/ProjectFX12231/api/change-pass",
															type : "POST",
															data : {
																userName : user,
																password : pass,
																newPassword : newPass,
																repeatPassword : repeatPass
															},
															success : function(value) {
																if (value == 'false') {
																	$("#error-mes").text("Sai thông tin tài khoản hoặc mật khẩu");
																} else if (value == 'repeatFail') {
																	$("#error-mes").text("Mật khẩu lặp lại không đúng");
																} else if (value == 'blank') {
																	$("#error-mes").text("Thông tin không được để trống.");
																} else {
																	/* link = window.location.href; */
																	$("#window-change-password").slideUp();
																	$("#window-change-succes").slideDown();
																}
															}
														})

								})
						
					});
function delEmployee(id){
	$("#block-window").slideDown();
	$("#window-del-employee").slideDown();
	$("#btn-accept-del-emp").click(function (){
		$.ajax({
			url: "/ProjectFX12231/api/del-employee",
			type:"POST",
			data:{
				eID: id,
			},
			success: function(){
				$("#window-del-employee").slideUp();
				$("#employee-del-success").slideDown()
			}
		})
	})
		
	}
		// lọc theo tìm kiếm
		function myFunction() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[0];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
		//lọc tìm kiếm hóa đơn theo ngày
		function checkBill() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("searchBill");
			filter = input.value.toUpperCase();
			table = document.getElementById("tableSales");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[0];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
		//lọc tìm kiếm bảng lương theo tên nhân viên
		function checkSalary() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("searchSalary");
			filter = input.value.toUpperCase();
			table = document.getElementById("tableSalary");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[0];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
