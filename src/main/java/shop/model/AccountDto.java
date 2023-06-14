package shop.model;


import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountDto implements Serializable {
	@NotEmpty
	@Length(min = 6)
	private String username;
	@NotEmpty
	@Length(min = 6)
	private String password;
	
	private Boolean isEdit = false;
	
}
