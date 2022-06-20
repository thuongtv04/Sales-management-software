	$(document).ready(function() {
							// ẩn hiển danh sách sản phẩm
							$("#btn-add-product").click(function() {
								$("#list-product").slideUp();
								$("#list-product").slideDown();
							})
							//kiểm tra sdt nhập vào
							$("#btn-search-number").click(function(event) {
												//ajax kiểm tra sdt nhập vào
												var num = $("#number-phone").val();
												$.ajax({
														url : "/ProjectFX12231/api/check-phone",
														type : "GET",
														data : {
															numberPhone : num
														},
														success : function() {
																link = window.location.href;
																window.location = link;
														}
												});
												
											});
							//mở cửa sổ xác nhận thanh toán
							$("#btn-pay").click(function(){
								$("#block-window").slideDown();
								$("#window-pay").slideDown();
							})
							//đóng cửa sổ xác nhận thanh toán
							$("#btn-cancel-pay").click(function() {
								$("#window-pay").slideUp();
								$("#block-window").slideUp();
							})
							//hiển thị cửa sổ đăng ký khách hàng
							$("#btn-add-customer").click(function() {
								$("#window-add-customer").slideDown();
								$("#block-window").slideDown();
							})
							//đóng cửa sổ đăng ký khách hàng
							$("#btn-cancel-add").click(function(){
								$("#window-add-customer").slideUp();
								$("#block-window").slideUp();
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
							

							//xử lý cửa sổ thay đổi mật khẩu
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
							//mở cửa sổ thay đổi bàn
							$("#btn-change-area").click(function(){
								$("#block-window").slideDown();
								$("#window-change-area").slideDown();
							})
							//đóng cửa sổ đổi bàn
							$("#btn-cancel-change").click(function(){
								$("#block-window").slideUp();
								$("#window-change-area").slideUp();
							})

	});

		/* lọc sản phẩm theo tìm kiếm */
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