package shop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>() ;
	 
	public void addCartItem(CartItem cartItem) {
	    cartItems.add(cartItem);
	    cartItem.setCart(this);
	}

	public void removeCartItem(CartItem cartItem) {
	    cartItems.remove(cartItem);
	    cartItem.setCart(null);
	}
	
	public CartItem getCartItemByProduct(Product product) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductId()==product.getProductId()) {
                return cartItem;
            }
        }
        return null; 
    }
}
