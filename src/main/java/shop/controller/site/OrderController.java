package shop.controller.site;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import shop.domain.Cart;
import shop.domain.CartItem;
import shop.domain.Customer;
import shop.domain.Order;
import shop.domain.OrderDetail;
import shop.service.CartService;
import shop.service.CustomerService;
import shop.service.OrderDetailService;
import shop.service.OrderService;

@Controller
@RequestMapping("site/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService detailService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartService cartService;

	@Autowired
	HttpSession session;
	
	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("")
	public String order(Model model) {
		String username = (String) session.getAttribute("username");
		Optional<Customer> customer = customerService.findByName(username);
		Cart cart = (Cart) session.getAttribute("cart");
		Optional<Cart> opt = cartService.findById(cart.getCartId());
	    Cart cart1 = opt.get();
	    int total = 0;
		for (CartItem item : cart1.getCartItems()) {
			total += (item.getQuantity() * item.getProduct().getUnitPrice());
		}
		
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setAmount(total);
		order.setCustomer(customer.get());
		orderService.save(order);
		
		for (CartItem item : cart.getCartItems()) {
	        OrderDetail orderDetail = new OrderDetail();
	        orderDetail.setOrder(order);
	        orderDetail.setProduct(item.getProduct());
	        orderDetail.setQuantity(item.getQuantity());
	        orderDetail.setUnitPrice(item.getProduct().getUnitPrice());
	        
	        orderDetailService.save(orderDetail);	        
	    }
		session.removeAttribute("cart");
		
        
        model.addAttribute("message", "Order Success!");
        model.addAttribute("total", total);
        model.addAttribute("listCartItem", cart1.getCartItems());
        
        cartService.deleteById(cart1.getCartId());
        
		return "site/contents/cart";
	}
}
