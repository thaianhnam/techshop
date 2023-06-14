package shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.domain.Product;
import shop.service.ProductService;

@Controller
@RequestMapping("adminhomepage")
public class AdminHomepageController {
	@Autowired
	ProductService productService;
	
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
		return "admin/products/content";
	}
}
