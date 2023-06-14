package shop.controller.site;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import shop.domain.Cart;
import shop.domain.CartItem;
import shop.domain.Product;
import shop.service.CartItemService;
import shop.service.CartService;
import shop.service.ProductService;

@Controller
@RequestMapping("site/cart")
public class CartController {
	@Autowired
	ProductService productService;
	@Autowired
	private HttpSession session;

	@Autowired
	CartService cartService;

	@Autowired
	CartItemService cartItemService;
	@GetMapping("")
	public String Cart(Model model) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			model.addAttribute("listCartItem", cart.getCartItems());
			int total = 0;
			for (CartItem item : cart.getCartItems()) {
				total += (item.getQuantity() * item.getProduct().getUnitPrice());
			}
			model.addAttribute("total", total);
		}
		return "site/contents/cart";
	}

	@RequestMapping("/{productId}")
	public String home(Model model, @PathVariable(name = "productId") Long productId) {
		Optional<Product> opt = productService.findById(productId);
		Product product = opt.get();
		if (product == null || product.getQuantity() <= 0) {
			model.addAttribute("message", "The product is out of stock");
			return "site/contents/cart";
		}

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			cartService.save(cart);
			session.setAttribute("cart", cart);
		}

		CartItem cartItem = cart.getCartItemByProduct(product);
		if (cartItem != null) {
			int newQuantity = cartItem.getQuantity() + 1;
			cartItem.setQuantity(newQuantity);
			cartItemService.save(cartItem);
			
		} else {

			CartItem newcartItem = new CartItem();
			newcartItem.setCart(cart);
			newcartItem.setProduct(product);
			newcartItem.setQuantity(1);

			cartItemService.save(newcartItem);
			cart.getCartItems().add(newcartItem);
			cartService.save(cart);
		}
		model.addAttribute("listCartItem", cart.getCartItems());
		int total = 0;
		for (CartItem item : cart.getCartItems()) {
			total += (item.getQuantity() * item.getProduct().getUnitPrice());
		}
		model.addAttribute("total", total);
		return "site/contents/cart";
	}

	@GetMapping("delete/{cartItemId}")
	public String delete(Model model, @PathVariable(name = "cartItemId") Long cartItemId) {
		Cart cart = (Cart) session.getAttribute("cart");
	    cartItemService.deleteById(cartItemId);
	    Optional<Cart> opt = cartService.findById(cart.getCartId());
	    Cart cart1 = opt.get();

	    model.addAttribute("listCartItem", cart1.getCartItems());
	    session.setAttribute("cart", cart1);
	    int total = 0;
		for (CartItem item : cart1.getCartItems()) {
			total += (item.getQuantity() * item.getProduct().getUnitPrice());
		}
		model.addAttribute("total", total);
	    return "site/contents/cart";

	}
}
