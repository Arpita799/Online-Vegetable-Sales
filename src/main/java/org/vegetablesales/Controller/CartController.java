package org.vegetablesales.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.vegetablesales.Model.Cart;
import org.vegetablesales.Model.Customer;
import org.vegetablesales.Model.VegetableDTO;
import org.vegetablesales.Service.ICartService;
import org.vegetablesales.Service.ICustomerService;
import org.vegetablesales.Service.IVegetableMgmtService;

@Controller
@RequestMapping("/cart")
@SessionAttributes("customerId")
public class CartController {
	@Autowired
	private ICartService cartService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IVegetableMgmtService vegetableService;
	@GetMapping("/addtocart")
	public String addToCart(@RequestParam("vegId") Integer vegId,Model model) {
		Integer customerId = (Integer) model.getAttribute("customerId");
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		VegetableDTO vegetable = vegetableService.viewVegetable(vegId);
		for(VegetableDTO vege:cart.getVegetable()) {
			if(vege.getVegId()==vegId)
				return "cart/alreadyPresent";
		}
		vegetable.setQuantity(1);
		cart.getVegetable().add(vegetable);
		cartService.addCart(cart);
		return "cart/addtocartsuccess";
	}
	@GetMapping("/viewcart")
	public String viewCart(Model model) {
		Integer customerId = (Integer) model.getAttribute("customerId");
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		if(list.isEmpty()) {
			return "cart/cartempty";
		}
		else {
			model.addAttribute("list",list);
			return "cart/cartitems";
		}
	}
	@GetMapping("/incqnty")
	public String increaseQuantity(@RequestParam("vegId") Integer vegId,Model model) {
		Integer customerId = (Integer) model.getAttribute("customerId");
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		for(VegetableDTO vege:list) {
			if(vege.getVegId()==vegId) {
				Integer qnty = vege.getQuantity()+1;
				if(qnty<=5)
					vege.setQuantity(qnty);
			}
		}
		cart.setVegetable(list);
		cartService.addCart(cart);
		model.addAttribute("list",list);
		return "cart/cartitems";
	}
	@GetMapping("/decqnty")
	public String decreaseQuantity(@RequestParam("vegId") Integer vegId,Model model) {
		Integer customerId = (Integer) model.getAttribute("customerId");
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		for(VegetableDTO vege:list) {
			if(vege.getVegId()==vegId) {
				Integer qnty = vege.getQuantity()-1;
				if(qnty>0)
					vege.setQuantity(qnty);
			}
		}
		cart.setVegetable(list);
		cartService.addCart(cart);
		model.addAttribute("list",list);
		return "cart/cartitems";
	}
	@GetMapping("/remove")
	public String removeVege(@RequestParam("vegId") Integer vegId,Model model)
	{
		Integer customerId = (Integer) model.getAttribute("customerId");
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		VegetableDTO veg = vegetableService.viewVegetable(vegId);
		list.remove(veg);
		if(list.isEmpty())
			return "cart/cartempty";
		cart.setVegetable(list);
		cartService.addCart(cart);
		model.addAttribute("list",list);
		return "cart/cartitems";	
	}
	@GetMapping("/empty")
	public String emptyCart(Model model)
	{
		Integer customerId = (Integer) model.getAttribute("customerId");
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		list.clear();
		cart.setVegetable(list);
		cartService.addCart(cart);
		return "cart/cartempty";	
	}
}
