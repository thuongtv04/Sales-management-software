package com.thuongtran.ProjectFX12231.controller.admin;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.thuongtran.ProjectFX12231.dao.BillDetailDAO;
import com.thuongtran.ProjectFX12231.dao.ProductDAO;
import com.thuongtran.ProjectFX12231.entity.Product;

@Controller
public class ProductController {

	/**
	 * Controller thêm sản phẩm mới
	 */
	@RequestMapping(value = "admin/add-product", method = RequestMethod.GET)
	public String AddProduct(Model model, String name, String type, String src, String description) {
		double price = 0;
		Product pr = new Product(name, type, price, src, description);
		model.addAttribute("newProduct", pr);
		model.addAttribute("typeBTN", "Thêm");
		model.addAttribute("action", "add-product");
		return "admin/addproduct";
	}
	
	
	/**
	 * Xử lý thêm sản phẩm mới
	 */
	@RequestMapping(value = "admin/add-product", method = RequestMethod.POST)
	public String insertProduct(@ModelAttribute("newProduct") Product product, Model model, @RequestParam("image") CommonsMultipartFile file,HttpSession session) {
		
		//lấy đường dẫn lưu file ảnh
		String path=session.getServletContext().getRealPath("/css/images/product");  
		//lấy ra tên file ảnh tải lên
        String filename=file.getOriginalFilename();
        //set đường dẫn cho file ảnh
        product.setSrc("css\\images\\product"+"/"+filename);
        //thực hiện ghi file lên sv
        try{  
            byte barr[]=file.getBytes();  
              
            BufferedOutputStream bout=new BufferedOutputStream(  
                     new FileOutputStream(path+"/"+filename));  
            bout.write(barr);  
            bout.flush();  
            bout.close();  
            }catch(Exception e){
            	System.out.println(e);
            	}  
        //lưu sản phẩm vào csdl
		ProductDAO productDAO = new ProductDAO();
		productDAO.addProduct(product);
		model.addAttribute("mesage", "thêm sản phẩm thành công");
		model.addAttribute("typeBTN", "Thêm");
		return "admin/addproduct";
	}
	
	/**
	 * Controller hiển thị danh sách toàn bộ sản phẩm
	 * @param index : số trang người dùng click
	 */
	@RequestMapping(value = "/admin/show-product", method = RequestMethod.GET)
	public String showProduct(Model model,@RequestParam("index") int index) {
		//biến lưu số sản phẩm hiển thị trên trang
		int productInPage = 5;
		//Khởi tạo biến lưu số trang tối đa
		int maxPage = 0;
		//lấy ra danh sách sản phẩm từ csdl
		ProductDAO productDAO = new ProductDAO();
		List<Product> listP = productDAO.getAllProduct();
		
		//lấy ra sản phẩm trên từng trang theo index người dùng click
		List<Product> listInPage = productDAO.listPageProduct(index, productInPage);
		
		//tính số trang tối đa cần sử dụng theo số lượng sản phẩm
		if(listP.size() % productInPage == 0) {
			maxPage = listP.size()/productInPage;
		}else {
			maxPage = listP.size()/productInPage + 1;
		}
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("listP", listInPage);
		model.addAttribute("index", index);
		return "admin/showproduct";
	}
	
	/**
	 * Controller xóa sản phẩm
	 */
	@RequestMapping(value = "admin/del-product", method = RequestMethod.GET)
	public String delProduct(@RequestParam("id") int id, Model model) {
		
		//kiểm tra sản phẩm có tồn tại trên hóa đơn hay không.nếu có ko cho xóa sản phẩm
		boolean check = new BillDetailDAO().checkProduct(id);
		if(check) {
			model.addAttribute("mes", "fail");
			
		}else {
			ProductDAO productDAO = new ProductDAO();
			productDAO.delProduct(id);
			model.addAttribute("mes", "succes");
		}
		return showProduct(model, 1);
	}
	
	/**
	 * Controller lấy thông tin sản phẩm cần chỉnh sửa
	 */
	@RequestMapping(value = "admin/edit-product", method = RequestMethod.GET)
	public String editProduct(@RequestParam("id") int id,
			Model model) {
		ProductDAO productDAO = new ProductDAO();
		for(Product p: productDAO.getAllProduct()) {
			if(p.getProductID() == id) {
				model.addAttribute("newProduct", p);
				model.addAttribute("typeBTN", "Cập nhật");
				model.addAttribute("action", "edit-product");
				return "admin/addproduct";
			}
		}
		return null;
	}
	
	/**
	 * Controller thực hiện thao tác thay đổi thông tin sản phẩm
	 */
	@RequestMapping(value = "admin/edit-product", method = RequestMethod.POST)
	public String editProduct(@ModelAttribute("newProduct")Product product ,
							@RequestParam("image") CommonsMultipartFile file, 
							Model model, HttpSession session) {
		//lấy đường dẫn lưu file
		String path=session.getServletContext().getRealPath("/css/images/product");  
		//lấy tên file ảnh
        String filename=file.getOriginalFilename();
        //set đường dẫn hình ảnh cho sản phẩm
        product.setSrc("css\\images\\product"+"/"+filename);
        //thực hiện ghi file
        try{  
            byte barr[]=file.getBytes();  
              
            BufferedOutputStream bout=new BufferedOutputStream(  
                     new FileOutputStream(path+"/"+filename));  
            bout.write(barr);  
            bout.flush();  
            bout.close();  
              
            }catch(Exception e){
            	System.out.println(e);
            	}  
        //cập nhật sản phẩm lên csdl
		ProductDAO productDAO = new ProductDAO();
		productDAO.addProduct(product);
		model.addAttribute("mesage", "cập nhật sản phẩm thành công");
		model.addAttribute("action", "edit-product");
		model.addAttribute("typeBTN", "Cập nhật");
		return "admin/addproduct";
	}
}
