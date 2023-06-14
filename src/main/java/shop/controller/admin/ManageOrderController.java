package shop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shop.domain.Order;
import shop.domain.OrderDetail;
import shop.service.OrderService;

@Controller
@RequestMapping("admin/order")
public class ManageOrderController {
	@Autowired
	OrderService orderService;
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Order> list = orderService.findAll();
		model.addAttribute("orders", list);
		return "admin/orders/list";
	}
	
	@RequestMapping("orderDetails/{orderId}")
	public String orderDetails(ModelMap model, @PathVariable(name = "orderId", required = false) Long orderId) {
		Optional<Order> opt = orderService.findById(orderId);
		List<OrderDetail> list = opt.get().getOrderDetails();
		model.addAttribute("orderDetails", list);
		model.addAttribute("total", opt.get().getAmount());
		model.addAttribute("orderId", orderId);
		return "admin/orders/orderdetail";
	}
	
	@RequestMapping("orderConfirmation/{orderId}")
	public String orderConfirmation(ModelMap model, @PathVariable(name = "orderId", required = false) Long orderId) {
		Optional<Order> opt = orderService.findById(orderId);
		List<OrderDetail> list = opt.get().getOrderDetails();
		model.addAttribute("orderDetails", list);
		model.addAttribute("total", opt.get().getAmount());
		model.addAttribute("orderId", orderId);
		opt.get().setStatus((short) 1);
		orderService.save(opt.get());
		model.addAttribute("message", "Order received");
		return "admin/orders/orderdetail";
	}
	
	@GetMapping("delete/{oderId}")
	public ModelAndView delete(ModelMap model, @PathVariable("oderId") Long oderId) {
		orderService.deleteById(oderId);
		model.addAttribute("message", "Order is deleted!");
		return new ModelAndView("forward:/admin/order", model);
	}
}
