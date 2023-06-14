package shop.config;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import shop.domain.Cart;
import shop.service.CartService;

@WebListener
public class CartSessionListener implements HttpSessionListener {
	@Autowired
	CartService cartService;
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		Cart cart = (Cart) se.getSession().getAttribute("cart");
        if (cart != null) {
            cartService.delete(cart);
        }
	}
}
