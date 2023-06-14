package shop.controller.site;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.domain.Category;
import shop.domain.Product;
import shop.service.CategoryService;
import shop.service.ProductService;

@Controller
@RequestMapping("sitehomepage")
public class SiteHomepageController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping("")
	public String home(Model model, @RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(0);
		int pageSize = 8;
		
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		
		Page<Product> resultPage = productService.findAll(pageable) ;
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if (totalPages > 5) {
				if (end == totalPages) start = end - 5 ;
				else if (start == 1) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
	                .boxed()
	                .collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		return "site/contents/content";
	}

	@RequestMapping("/{name}")
	public String home1(Model model, @PathVariable(name = "name", required = false) String name, @RequestParam("page") Optional<Integer> page) {
		System.out.println(name);
		if (name == null) {
			List<Product> products = productService.findAll();
			model.addAttribute("products", products);
			return "site/contents/content";
		}
		int currentPage = page.orElse(0);
		int pageSize = 8;
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		Page<Product> resultPage = productService.findByCategoryName(name, pageable);
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if (totalPages > 5) {
				if (end == totalPages) start = end - 5 ;
				else if (start == 1) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
	                .boxed()
	                .collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		model.addAttribute("name", name);
		return "site/contents/contentcategory";
	}

	@GetMapping("search")
	public String search(Model model, @RequestParam(name="nameproduct", required = false) String name, @RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(0);
		int pageSize = 8;
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		Page<Product> resultPage = productService.findByNameContaining(name, pageable);
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if (totalPages > 5) {
				if (end == totalPages) start = end - 5 ;
				else if (start == 1) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
	                .boxed()
	                .collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("name", name);
		model.addAttribute("productPage", resultPage);
		return "site/contents/contentsearch";
	}

}
