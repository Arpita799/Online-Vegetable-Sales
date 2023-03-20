package org.vegetablesales.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.vegetablesales.Model.Cart;
import org.vegetablesales.Model.Customer;
import org.vegetablesales.Model.Orders;
import org.vegetablesales.Model.VegetableDTO;
import org.vegetablesales.Service.ICustomerService;
import org.vegetablesales.Service.IOrdersService;

@Controller
@SessionAttributes("customerId")
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private IOrdersService orderService;
	@Autowired
	private ICustomerService customerService;
	@GetMapping("/addorder")
	public String addOrder(Model model) {
		Orders order = new Orders();
		Integer customerId = (Integer) model.getAttribute("customerId");
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		Double amount = 0.0;
		for(VegetableDTO veg : list) {
			amount += veg.getPrice()*veg.getQuantity();
			order.getVegetableList().add(veg);
		}
		//order.setVegetableList(list);
		order.setCustomer(customer);
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("Placed");
		order.setTotalAmount(amount);
		orderService.addOrder(order);
		return "order/orderplaced";
	}
	@GetMapping("/vieworder")
	public String viewOrder(Model model) {
		Integer customerId = (Integer) model.getAttribute("customerId");
		List<Orders> list = orderService.viewAllOrders(customerId);
		if(list==null)
			return "order/ordernotfound";
		else
		{
			model.addAttribute("list",list);
			return "order/orderfound";
		}
	}
	@GetMapping("/viewveg")
	public String viewVeg(@RequestParam("orderId") Integer orderId,Model model) {
		Orders order = orderService.viewOrder(orderId);
		List<VegetableDTO> list = order.getVegetableList();
		model.addAttribute("list", list);
		return "order/veges";
	}
	@GetMapping("/canorder")
	public String cancelOrder(@RequestParam("orderId") Integer orderId,Model model) {
		Orders order = orderService.viewOrder(orderId);
		order.setOrderStatus("Cancelled");
		orderService.updateOrder(order);
		return "order/ordercan";
	}
}
