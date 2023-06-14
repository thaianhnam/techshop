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
public class CustomerDto implements Serializable {
	
	private Long customerId;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String phone;
	
	private Date registeredDate;
	
	private short status;
}
