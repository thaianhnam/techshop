package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import shop.domain.Account;
import shop.domain.Cart;
import shop.domain.Customer;
import shop.model.AdminLoginDto;
import shop.model.CustomerDto;
import shop.model.CustomerLoginDto;
import shop.service.AccountService;
import shop.service.CartService;
import shop.service.CustomerService;



@Controller
@RequestMapping("site")
public class SiteLoginController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("alogina")
	public String login(ModelMap model) {
		model.addAttribute("customer", new CustomerLoginDto());
		return "site/accounts/login";
	}
	
	@PostMapping("alogina")
	public ModelAndView login(ModelMap model, 
			@Valid @ModelAttribute("customer") CustomerLoginDto dto,
			BindingResult result) {
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			return new ModelAndView("site/accounts/login", model);
		}
		
		Customer customer = customerService.login(dto.getName(), dto.getPassword());
		
		if (customer == null) {
			model.addAttribute("message", "Invalid username or password");
			return new ModelAndView("site/accounts/login", model);
		}
		session.setAttribute("username", customer.getName());
		Object ruri = session.getAttribute("redirect-uri");
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:"+ ruri);
		}
		
		
		return new ModelAndView("redirect:/sitehomepage");
	}
	
	@RequestMapping("alogouta")
	public String logout() {
		Cart cart = (Cart) session.getAttribute("cart");
		session.invalidate();
		if (cart != null) {
			cartService.delete(cart);
		}
		
		return "redirect:/sitehomepage";
	}
}
