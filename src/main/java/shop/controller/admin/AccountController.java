package shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import shop.domain.Account;
import shop.domain.Customer;
import shop.domain.Product;
import shop.model.AccountDto;
import shop.service.AccountService;
import shop.service.CategoryService;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("add") 
	public String add(Model model) {
		model.addAttribute("account", new AccountDto());
		
		return "admin/accounts/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("account") AccountDto dto,BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/accounts/addOrEdit");
		}
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		
		accountService.save(entity);
		
		model.addAttribute("message", "Account is saved!");
		return new ModelAndView("forward:/admin/accounts", model);
	}
	
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Account> list = accountService.findAll();
		model.addAttribute("accounts", list);
		return "admin/accounts/list";
	}
	
	
	@GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model,@PathVariable(name = "username", required = false) String username) {
		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();
		if (opt.isPresent()) {
			Account entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			
			dto.setPassword("");
			
			model.addAttribute("account", dto);
			return new ModelAndView("admin/accounts/addOrEdit", model);
		}
		model.addAttribute("message", "Account is not existed!");
		return new ModelAndView("forward:/admin/accounts", model);
	}
	
	@GetMapping("delete/{username}")
	public ModelAndView delete(ModelMap model, @PathVariable("username") String username) {
		accountService.deleteById(username);
		model.addAttribute("message", "Account is deleted!");
		return new ModelAndView("forward:/admin/accounts", model);
	}
	
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Account> list = null;
		if(StringUtils.hasText(name)) {
			list = accountService.findByUsernameContaining(name);
		}else {
			list = accountService.findAll();
		}
		model.addAttribute("accounts", list);
		return "admin/accounts/list";
	}
	
	@GetMapping("changePassword")
	public String formchangePassword() {
		return "admin/accounts/changePassword";
	}
	
	@PostMapping("changePassword")
	public String changePassword(Model model, @RequestParam(name = "currentPassword", required = false) String currentPassword, 
			@RequestParam(name = "newPassword", required = false) String newPassword) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "site/accounts/login";
		}
		Optional<Account> opt = accountService.findById(username);
		if (bCryptPasswordEncoder.matches(currentPassword, opt.get().getPassword())) {
			opt.get().setPassword(newPassword);
			accountService.save(opt.get());
			model.addAttribute("message", "Change password successfully");
			
		}else {
			model.addAttribute("message", "Invalid currentPassword or newPassword");
		}
		return "admin/accounts/changePassword";
	}
}
