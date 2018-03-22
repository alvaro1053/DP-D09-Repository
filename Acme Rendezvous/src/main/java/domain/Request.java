
package domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;


@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	private String						comment;
	private CreditCard					creditCard;
	private Rende 						rende;
	private Service 					service;
	
	
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
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
	@ManyToOne(optional = true)
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}


}
