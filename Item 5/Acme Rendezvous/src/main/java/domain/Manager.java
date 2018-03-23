
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	private String						VATnumber;
	private Collection<Service>			services;

	
	
	public String getVATnumber() {
		return VATnumber;
	}

	public void setVATnumber(String VATnumber) {
		this.VATnumber = VATnumber;
	}

	@OneToMany(mappedBy = "manager")
	public Collection<Service> getServices() {
		return this.services;
	}

	public void setServices(final Collection<Service> services) {
		this.services = services;
	}
	

}
