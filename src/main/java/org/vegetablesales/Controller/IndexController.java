package org.vegetablesales.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vegetablesales.Model.VegetableDTO;
import org.vegetablesales.Service.IVegetableMgmtService;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private IVegetableMgmtService vegetableService;
	@GetMapping("/fetchcat")
	public String vegCategory(@RequestParam("vegName") String vegName,Model model) {
		List<VegetableDTO> list = vegetableService.viewVegetableList(vegName);
		if(list==null || list.isEmpty())
			return "index/catnotfound";
		else
		{
			model.addAttribute("list",list);
			return "index/searchcatfound";
		}
	}
}
