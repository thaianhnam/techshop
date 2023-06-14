package shop.model;

import java.io.Serializable;
import java.util.Date;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginDto implements Serializable {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String password;
	
	
	private Boolean rememberMe = false;
}
