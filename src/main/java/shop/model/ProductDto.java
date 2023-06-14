package shop.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
	
	private Long productId;
	
	private String name;
	
	private int quantity;
	
	private double unitPrice;
	
	private String image;
	
	private MultipartFile imageFile;
	
	private String description;
	
	private double discount;
	
	private Date enteredDate;
	
	private short status;
	
	private Long categoryId;
	
	private Boolean isEdit = false;
	
}
