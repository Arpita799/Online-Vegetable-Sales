package org.vegetablesales.Controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vegetablesales.Model.Admin;
import org.vegetablesales.Model.Customer;
import org.vegetablesales.Model.Feedback;
import org.vegetablesales.Model.Orders;
import org.vegetablesales.Model.VegetableDTO;
import org.vegetablesales.Service.IAdminService;
import org.vegetablesales.Service.ICustomerService;
import org.vegetablesales.Service.IFeedbackService;
import org.vegetablesales.Service.IOrdersService;
import org.vegetablesales.Service.IVegetableMgmtService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	@Autowired
	private ICustomerService custService;
	@Autowired
	private IFeedbackService feedService;
	@Autowired
	private IVegetableMgmtService vegetableService;
	@Autowired
	private IOrdersService orderService;
	@GetMapping("/addadmin")
	public String addAdmin(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin",admin);
		return "admin/addadmin";
	}
	@PostMapping("/addadmin")
	public String processAddAdmin(@ModelAttribute("admin") Admin admin) {
		Admin a = adminService.addAdmin(admin);
		if(a==null) {
			return "admin/addadminunsuccess";
		}else {
			return "admin/addadminsuccess";
		}
	}
	
	
	@GetMapping("/updateadmin")
	public String updateAdmin(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin",admin);
		return "admin/updateadmin";
	}
	@PostMapping("/fetchupdateadmin")
	public String fetchUpdateAdmin(@ModelAttribute("admin") Admin admin,Model model) {
		admin = adminService.viewAdmin(admin.getAdminId());
		if(admin==null)
			return "admin/adminnotfound";
		else
		{
			model.addAttribute("admin",admin);
			return "admin/adminfound";
		}
			
	}
	@PostMapping("/updateadminprocess")
	public String updateAdminProcess(@ModelAttribute("admin") Admin admin,Model model) {
		Admin ad = adminService.updateAdmin(admin);
		if(ad==null)
			return "admin/adminupdatefailed";
		else
		{
			return "admin/adminupdatesuccess";
		}
			
	}
	
	@GetMapping("/deleteadmin")
	public String deleteAdmin(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin",admin);
		return "admin/deleteadmin";
	}
	@PostMapping("/fetchdeleteadmin")
	public String fetchDeleteAdmin(@ModelAttribute("admin") Admin admin,Model model) {
		admin = adminService.viewAdmin(admin.getAdminId());
		if(admin==null)
			return "admin/deleteadminnotfound";
		else
		{
			model.addAttribute("admin",admin);
			return "admin/deleteadminfound";
		}
			
	}
	@PostMapping("/deleteadminprocess")
	public String deleteAdminProcess(@ModelAttribute("admin") Admin admin,Model model) {
		Admin ad = adminService.removeAdmin(admin.getAdminId());
		if(ad==null)
			return "admin/deleteadminfailed";
		else
			return "admin/deleteadminsuccess";
	}
	@GetMapping("/searchadmin")
	public String searchAdmin(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin",admin);
		return "admin/searchadmin";
	}
	@PostMapping("/fetchadmin")
	public String fetchAdmin(@ModelAttribute("admin") Admin admin,Model model) {
		admin = adminService.viewAdmin(admin.getAdminId());
		if(admin==null)
			return "admin/searchadminnotfound";
		else
		{
			model.addAttribute("admin",admin);
			return "admin/searchadminfound";
		}
	}
	@GetMapping("/viewadmin")
	public String viewAlladmin(Model model) {
		List<Admin> list = adminService.viewAdminList();
		model.addAttribute("list", list);
		return "admin/viewalladmin";
	}
	
	@GetMapping("/searchcust")
	public String searchCustomer(Model model) {
		Customer cust = new Customer();
		model.addAttribute("cust",cust);
		return "admin/searchcust";
	}
	@PostMapping("/fetchcust")
	public String fetchCustomer(@ModelAttribute("cust") Customer cust,Model model) {
		cust = custService.viewCustomer(cust.getCustomerId());
		if(cust==null)
			return "admin/custnotfound";
		else
		{
			model.addAttribute("cust",cust);
			return "admin/searchcustfound";
		}
	}
	@GetMapping("/viewcust")
	public String viewAllcust(Model model) {
		List<Customer> list = custService.viewCustomerList();
		model.addAttribute("list", list);
		return "admin/viewallcust";
	}
	
	@GetMapping("/viewfeedcust")
	public String viewFeedByCust(Model model) {
		Customer cust = new Customer();
		model.addAttribute("cust",cust);
		return "admin/viewfeedcust";
	}
	@PostMapping("/viewfeedcust")
	public String fetchFeedCust(@ModelAttribute("cust") Customer cust,Model model) {
		Customer customer = custService.viewCustomer(cust.getCustomerId());
		if(customer==null)
			return "admin/feedcustnotfound";
		else
		{
			List<Feedback> list = feedService.viewFeedback(cust.getCustomerId());
			if(list==null)
				return "admin/nofeed";
			else
			{
				model.addAttribute("list",list);
				return "admin/viewfeed";
			}
		}
		
	}
	
	@GetMapping("/viewfeedveg")
	public String viewFeedByVege(Model model) {
		VegetableDTO veg = new VegetableDTO();
		model.addAttribute("veg",veg);
		return "admin/viewfeedveg";
	}
	@PostMapping("/viewfeedveg")
	public String fetchFeedVege(@ModelAttribute("veg") VegetableDTO veg,Model model) {
		VegetableDTO vegetable = vegetableService.viewVegetable(veg.getVegId());
		if(vegetable==null)
			return "admin/vegenotfound";
		else
		{
			List<Feedback> list = feedService.viewFeedback(veg.getVegId());
			if(list==null)
				return "admin/vegnofeed";
			else
			{
				model.addAttribute("list",list);
				return "admin/viewfeed";
			}
		}
	}
	@GetMapping("/searchorder")
	public String searchOrder(Model model) {
		Orders order = new Orders();
		model.addAttribute("order",order);
		return "admin/searchorder";
	}
	@PostMapping("/fetchorder")
	public String fetchOrder(@ModelAttribute("order") Orders order,Model model) {
		Orders od = orderService.viewOrder(order.getOrderId());
		if(od==null)
			return "admin/ordernotfound";
		else
		{
			model.addAttribute("order",od);
			return "admin/searchorderfound";
		}
	}
	
	@GetMapping("/viewveg")
	public String viewVeg(@RequestParam("orderId") Integer orderId,Model model) {
		Orders order = orderService.viewOrder(orderId);
		List<VegetableDTO> list = order.getVegetableList();
		model.addAttribute("list", list);
		return "admin/veges";
	}
	@GetMapping("updatestatus")
	public String updateStatus(@RequestParam("orderId") Integer orderId,Model model) {
		Orders order = orderService.viewOrder(orderId);
		List<VegetableDTO> list=order.getVegetableList();
		model.addAttribute("orderId",order.getOrderId());
		model.addAttribute("list",list);
		model.addAttribute("order",order);
		return "admin/updateorderfound";
	}
	@PostMapping("updateorderprocess")
	public String updateOrderProcess(@ModelAttribute("order") Orders order,Model model) {
		Orders od = orderService.updateOrder(order);
		if(od==null)
			return "admin/orderupdatefailed";
		else
		{
			return "admin/orderupdatesuccess";
		}
			
	}
	@GetMapping("/custorder")
	public String searchOrderByCust(Model model) {
		Customer cust = new Customer();
		model.addAttribute("cust", cust);
		return "admin/custorder";
	}
	@PostMapping("/custorder")
	public String fetchOrderByCust(@ModelAttribute("cust") Customer cust,Model model) {
		Integer customerId = cust.getCustomerId();
		cust = custService.viewCustomer(customerId);
		if(cust==null)
			return "admin/custordernotfound";
		else {
			List<Orders> list = orderService.viewAllOrders(customerId);
			if(list==null)
				return "admin/noorders";
			else {
				model.addAttribute("list",list);
				return "admin/orderlist";
			}
		}
		
	}
	@GetMapping("/dateorder")
	public String searchOrderByDate(Model model) {
		Orders order = new Orders();
		model.addAttribute("order", order);
		return "admin/dateorder";
	}
	@PostMapping("/dateorder")
	public String fetchOrderByDate(@ModelAttribute("order") Orders order,Model model) {
			List<Orders> list = orderService.viewOrderBydate(order.getOrderDate());
			if(list==null)
				return "admin/noorders";
			else {
				model.addAttribute("list",list);
				return "admin/orderlist";
			}
	}
	@GetMapping("/allorders")
	public String viewAllOrders(Model model) {
		List<Orders> list = orderService.viewOrderList();
		if(list==null)
			return "admin/noorders";
		else {
			model.addAttribute("list", list);
			return "admin/orderlist";
		}
	}
	
}
