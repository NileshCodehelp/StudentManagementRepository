package in.nareshit.raghu.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Student {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String address;
	private Double feepaid;
	private String course;
	@Transient
	private String captcha;
	@Transient
	private String hidden;
	@Transient
	private String image;
	
}
