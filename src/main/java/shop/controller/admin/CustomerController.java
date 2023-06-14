package shop.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
import shop.domain.Category;
import shop.domain.Customer;
import shop.model.CustomerDto;
import shop.service.CustomerService;



@Controller
@RequestMapping("admin/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
		
	@Autowired
	private HttpSession session;
	
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Customer> list = customerService.findAll();
		model.addAttribute("customers", list);
		return "admin/customer/list";
	}
	
	@RequestMapping("add")
	public String registration(ModelMap model) {
		model.addAttribute("customer", new CustomerDto());
		System.out.println("username: "+ session.getAttribute("username"));
		return "admin/customer/addOrEdit";
	}

	@PostMapping("add")
	public ModelAndView saveOrUpdate(@Valid @ModelAttribute("customer") CustomerDto dto, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			return new ModelAndView("admin/customer/list");
		}
		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);
		entity.setRegisteredDate(new Date());
		
		
		customerService.save(entity);
		redirectAttributes.addFlashAttribute("message", "Successfully added a new customer!");
		return new ModelAndView("redirect:/admin/customers");
	}
	
	@GetMapping("delete/{id}")
	public ModelAndView delete(ModelMap model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		customerService.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "Customer is deleted!");
		return new ModelAndView("redirect:/admin/customers");
	}
	
	@GetMapping("edit/{id}")
	public String showEditProfilePage(ModelMap model, @PathVariable("id") Long id) {
		Optional<Customer> opt = customerService.findById(id);
		System.out.println(opt.isPresent());
		Customer customer = opt.get();
		model.addAttribute("customer", customer);

		return "admin/customer/editProfile";
	}
	
	@PostMapping("edit/{id}")
	public String editProfile(ModelMap model,@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
		
		Optional<Customer> opt = customerService.findById(id);
		customer.setCustomerId(opt.get().getCustomerId());
		customer.setRegisteredDate(opt.get().getRegisteredDate());
		customerService.save(customer);
		redirectAttributes.addFlashAttribute("message", "Custumer edited successfully");
		return "redirect:/admin/customers";
	}
	
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Customer> list = null;
		if(StringUtils.hasText(name)) {
			list = customerService.findByNameContaining(name);
		}else {
			list = customerService.findAll();
		}
		model.addAttribute("customers", list);
		return "admin/customer/list";
	}
	
	
}
