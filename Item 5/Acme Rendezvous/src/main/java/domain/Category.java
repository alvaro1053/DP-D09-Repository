
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;


@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private String						name;
	private String						description;
	private Collection<Category>		parentCategories;
	private Collection<Category>		childCategories;
	private Collection<Service>			services;
	
	
	@NotBlank
	@Column(unique = true)
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull
	@ManyToMany
	public Collection<Category> getParentCategories() {
		return parentCategories;
	}
	public void setParentCategories(Collection<Category> parentCategories) {
		this.parentCategories = parentCategories;
	}
	
	@NotNull
	@ManyToMany(mappedBy = "parentCategories")
	public Collection<Category> getChildCategories() {
		return childCategories;
	}
	public void setChildCategories(Collection<Category> childCategories) {
		this.childCategories = childCategories;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@OneToMany(mappedBy = "category")
	public Collection<Service> getServices() {
		return services;
	}
	public void setServices(Collection<Service> services) {
		this.services = services;
	}


}
