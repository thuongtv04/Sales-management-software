package com.thuongtran.ProjectFX12231.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongtran.ProjectFX12231.dao.AreaDAO;
import com.thuongtran.ProjectFX12231.dao.BillDAO;
import com.thuongtran.ProjectFX12231.dao.CustomerDAO;
import com.thuongtran.ProjectFX12231.dao.ProductDAO;
import com.thuongtran.ProjectFX12231.entity.Area;
import com.thuongtran.ProjectFX12231.entity.Bill;
import com.thuongtran.ProjectFX12231.entity.Customer;
import com.thuongtran.ProjectFX12231.entity.DetailBill;
import com.thuongtran.ProjectFX12231.entity.Employee;
import com.thuongtran.ProjectFX12231.entity.Oder;
import com.thuongtran.ProjectFX12231.entity.Product;
import com.thuongtran.ProjectFX12231.entity.User;

@Controller
public class SellingController {

	/**
	 * hàm lấy danh sách tất cả các bàn
	 */
	private List<Area> getAllArea() {
		AreaDAO areaDAO = new AreaDAO();
		List<Area> listA = areaDAO.getAllArea();
		return listA;
	}

	/**
	 * hàm lấy toàn bộ sản phẩm
	 */
	private List<Product> getAllProduct() {
		ProductDAO productDAO = new ProductDAO();
		List<Product> listP = productDAO.getAllProduct();
		return listP;
	}

	/**
	 * controller trang bán hàng
	 */
	@RequestMapping(value = "web/selling", method = RequestMethod.GET)
	public String homeSelling(HttpSession session) {
		if(session.getAttribute("userLogin")==null) {
			return "redirect:/login";
		}else {
			session.setAttribute("listA", getAllArea());
			session.setAttribute("listP", getAllProduct());
		return "web/selling";
		}
	}

	/**
	 * controler xem chi tiết bàn
	 */
	@RequestMapping(value = "web/detail-area", method = RequestMethod.GET)
	public String detailArea(@RequestParam("id") int id, Model model, HttpSession session) {
		AreaDAO areadao = new AreaDAO();
		Area area = areadao.getAreaByID(id);
		String areaID = ""+ area.getAreaID();
		
		//kiểm tra danh sách oder. nếu chưa có khởi tạo danh sách
		if (session.getAttribute("listOder") == null) {
			List<Oder> listOder = new ArrayList<>();
			session.setAttribute("listOder", listOder);
		}else {
			if(session.getAttribute(areaID) != null) {
				Oder oder = (Oder) session.getAttribute(areaID);
				session.setAttribute("totalAmount", oder.getAmount());
			}else {
				session.removeAttribute("totalAmount");
				session.removeAttribute("customer");
			}
			
		}
		
		//đánh dấu bàn được click lên session theo mã bàn
		session.setAttribute("areaClick", area);
		model.addAttribute("listA", getAllArea());
		return "web/selling";
	}

	
	/**
	 * controller thêm sản phẩm vào đơn hàng:
	 */
	@RequestMapping(value = "web/add-product", method = RequestMethod.GET)
	public String addProducts(@RequestParam("action") String action, @RequestParam("pID") int id, HttpSession session) {
		// lấy ra id bàn cần thêm món
		Area area = (Area) session.getAttribute("areaClick");
		String areaID = "" + area.getAreaID();

		try {
			// neu bàn chưa tồn tại thực hiện mở bàn và tạo phiên cho bàn đó
			if (session.getAttribute(areaID) == null) {
				session.setAttribute(areaID, new Oder(area.getAreaID()));
			}
			// lấy thông tin sản phẩm từ csdl
			Product p = new ProductDAO().getProductByID(id);

			// lấy thông tin oder từ session
			Oder oder = (Oder) session.getAttribute(areaID);

			// thêm sản phẩm vào oder
			if (action.equals("add")) {
				oder.addProduct(p);
			//xóa sản phẩm khỏi oder	
			} else if (action.equals("del")) {
				oder.delProduct(p);
			//xóa toàn bộ sản phẩm khỏi oder
			} else if (action.equals("remove")) {
				oder.removeFromOder(p.getProductID());
			}
			// lay danh sach toan bo oder
			@SuppressWarnings("unchecked")
			List<Oder> listOder = (List<Oder>) session.getAttribute("listOder");

			// kiểm tra bàn đã tồn tại chưa
			boolean flag = true;
			for (int i = 0; i < listOder.size(); i++) {
				if (oder.getOderID() == listOder.get(i).getOderID()) {
					flag = false;
				}
			}
			// nếu bàn chưa tồn tại đưa bàn đó vào listOder
			if (flag) {
				listOder.add(oder);
			}

			session.setAttribute("totalAmount", oder.getAmount());
			// set listOder lên session
			session.setAttribute("listOder", listOder);

			// cập nhật trạng thái bàn
			area.setStatus("Bận");
			new AreaDAO().updateArea(area);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:selling";
	}
	
	/**
	 * controller chuyển bàn
	 */
	@RequestMapping(value = "web/change-area", method = RequestMethod.GET)
	public String changeArea(@RequestParam("id")int id, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Oder> listO = (List<Oder>) session.getAttribute("listOder");
		//lấy ra bàn cũ
		Area area = (Area) session.getAttribute("areaClick");
		//chuyển oder sang bàn mới
		for(int i = 0; i < listO.size(); i++) {
			if(listO.get(i).getOderID() == area.getAreaID()) {
				listO.get(i).setOderID(id);
			}
		}
		//cập nhật bàn cũ thành trống
		area.setStatus("Trống");
		new AreaDAO().updateArea(area);
		
		//cập nhật bàn chuyển tới sang trạng thái bận
		Area a = new AreaDAO().getAreaByID(id);
		a.setStatus("Bận");
		new AreaDAO().updateArea(a);
		
		//cập nhật lại listOder
		session.setAttribute("listOder", listO);
		return "redirect:selling";
	}
	
	/**
	 * controller thanh toán
	 * @param id:          mã bàn
	 * @param numberPhone: sđt khách hàng
	 */
	@RequestMapping(value = "web/pay", method = RequestMethod.POST)
	public String payMent(@RequestParam(value = "areaID") int id,
						@RequestParam(value = "numberPhone") String numberPhone, HttpSession session) {
		System.out.println("SDT :" + numberPhone);
		String areaID = "" + id;

		Oder curOder = new Oder();
		Area area = (Area) session.getAttribute("areaClick");

		// xóa bàn đã thanh toán trong list oder
		@SuppressWarnings("unchecked")
		List<Oder> listOder = (List<Oder>) session.getAttribute("listOder");
		System.out.println("kick thuoc mang: " + listOder.size());
		for (int i = 0; i < listOder.size(); i++) {
			if (listOder.get(i).getOderID() == id) {
				curOder = listOder.get(i);
				listOder.remove(i);
			}
		}
		// lấy ngày hiện tại
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		// Tính giá trị hóa đơn
		double totalAmount = curOder.getAmount();
//				(double) session.getAttribute("totalAmount");

		// Tính thực thu hóa đơn
		double receivableAmount;
		if (session.getAttribute("receivableAmount") != null) {
			receivableAmount = (double) session.getAttribute("receivableAmount");
		} else {
			receivableAmount = totalAmount;
		}
		// lấy ra thông tin khách hàng
		Customer customer = new CustomerDAO().getCusomerByNumber(numberPhone);
		//nếu khách hàng không tồn tại thì đưa vào khách lẻ
		if(customer == null) {
			customer = new Customer("Khách lẻ", "", 0);
		}else {
			int points = (int) (receivableAmount / 20);
			points += customer.getPoints();
			customer.setPoints(points);
			if (points >= 300) {
				// điểm tích lũy từ 300 cập nhật khách vip
				customer.getCustomerType().setCustomerTypeID(3);
			} else if (points >= 200) {
				// điểm tích lũy từ 200 cập nhật lên khách hàng thân thiết
				customer.getCustomerType().setCustomerTypeID(2);
			} else if (points >= 100) {
				// điểm tích lũy từ 100 cập nhật khách hàng thành viên
				customer.getCustomerType().setCustomerTypeID(1);
			}
		}
			new CustomerDAO().updateCustomer(customer);
		
		
		// tạo list chi tiết hóa đơn tương ứng với oder
		List<DetailBill> listBD = new ArrayList<>();
		for (int i = 0; i < curOder.getListItems().size(); i++) {
			Product p = curOder.getListItems().get(i).getProduct();
			int quantity = curOder.getListItems().get(i).getQuantity();
			DetailBill bd = new DetailBill();
			bd.setProduct(p);
			bd.setQuantity(quantity);
			bd.setAmount(p.getPrice() * quantity);
			listBD.add(bd);
		}
		// lấy ra nhân viên từ session khi đăng nhập
		User u = (User) session.getAttribute("userLogin");
		Employee employee = u.getEmployee();

		// tạo hóa đơn mới
		Bill bill = new Bill();
		bill.setArea(area);
		bill.setCustomer(customer);
		bill.setEmployee(employee);
		bill.setReceivedDate(date);
		bill.setTotalAmount(totalAmount);
		bill.setReceivableAmount(receivableAmount);
		bill.setListBill(listBD);

		// lưu hóa đơn vào csdl
		new BillDAO().addBill(bill);

		// xóa dữ liệu bàn khỏi session
		session.removeAttribute("totalAmount");
		session.removeAttribute("receivableAmount");
		session.removeAttribute("customer");
		session.removeAttribute(areaID);
		// cập nhật bàn thành trống
		area.setStatus("Trống");
		new AreaDAO().updateArea(area);

		// gán lại danh sách bàn mới sau khi xóa
		session.setAttribute("listOder", listOder);
		return homeSelling(session);
	}
}
