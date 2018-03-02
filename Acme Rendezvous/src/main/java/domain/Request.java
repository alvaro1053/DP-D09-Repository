
package domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	private String						comment;
	private String						creditCard;
	private Rende 						rende;
	private Service 					service;
	
	
	@NotBlank
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@NotNull
	@CreditCardNumber
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = true)
	public Rende getRende() {
		return rende;
	}
	public void setRende(Rende rende) {
		this.rende = rende;
	}
	
	@NotNull
	@OneToOne(optional = false)
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}


}
