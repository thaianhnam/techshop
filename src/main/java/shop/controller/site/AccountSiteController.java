package shop.controller.site;



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import shop.domain.Account;
import shop.domain.Customer;
import shop.model.AccountDto;
import shop.model.CustomerDto;
import shop.service.CustomerService;




@Controller
@RequestMapping("site/accounts")
public class AccountSiteController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("registration")
	public String registration(ModelMap model) {
		model.addAttribute("customer", new CustomerDto());
		System.out.println("username: "+ session.getAttribute("username"));
		return "site/accounts/registration";
	}

	@PostMapping("registration")
	public ModelAndView saveOrUpdate(@Valid @ModelAttribute("customer") CustomerDto dto, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			return new ModelAndView("/site/accounts/registration");
		}
		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);
		entity.setRegisteredDate(new Date());
		
		
		customerService.save(entity);
		redirectAttributes.addFlashAttribute("message", "Customer successfully registered!");
		return new ModelAndView("redirect:/site/alogina");
	}
	
	@GetMapping("editProfile")
	public String showEditProfilePage(ModelMap model) {
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		if (username == null) {
			return "site/accounts/login";
		}
		Optional<Customer> opt = customerService.findByName(username);
		System.out.println(opt.isPresent());
		Customer customer = opt.get();
		model.addAttribute("customer", customer);

		return "site/accounts/editProfile";
	}
	
	@PostMapping("editProfile")
	public String editProfile(ModelMap model,@ModelAttribute("customer") Customer customer) {
		String username = (String) session.getAttribute("username");
		Optional<Customer> opt = customerService.findByName(username);
		customer.setCustomerId(opt.get().getCustomerId());
		customer.setRegisteredDate(opt.get().getRegisteredDate());
		customerService.save(customer);
		model.addAttribute("message", "Custumer edited successfully");
		session.setAttribute("username", customer.getName());

		return "site/accounts/editProfile";
	}
	
	@GetMapping("changePassword")
	public String formchangePassword() {
		return "site/accounts/changePassword";
	}
	
	@PostMapping("changePassword")
	public String changePassword(Model model, @RequestParam(name = "currentPassword", required = false) String currentPassword, 
			@RequestParam(name = "newPassword", required = false) String newPassword) {
		System.out.println(currentPassword);
		System.out.println(newPassword);
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "site/accounts/login";
		}
		Optional<Customer> opt = customerService.findByName(username);
		if (bCryptPasswordEncoder.matches(currentPassword, opt.get().getPassword())) {
			opt.get().setPassword(newPassword);
			customerService.save(opt.get());
			model.addAttribute("message", "Change password successfully");
			
		}else {
			model.addAttribute("message", "Invalid currentPassword or newPassword");
		}
		return "site/accounts/changePassword";
	}
	
}
