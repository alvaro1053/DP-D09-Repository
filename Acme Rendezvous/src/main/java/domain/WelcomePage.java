package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


@Entity
@Access(AccessType.PROPERTY)
public class WelcomePage extends DomainEntity {
	
	private String			name;

	private String			banner;

	private String			englishWelcome;

	private String			spanishWelcome;
	
	
	
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@NotBlank
	@URL
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@NotBlank
	public String getEnglishWelcome() {
		return englishWelcome;
	}

	public void setEnglishWelcome(String englishWelcome) {
		this.englishWelcome = englishWelcome;
	}

	@NotBlank
	public String getSpanishWelcome() {
		return spanishWelcome;
	}

	public void setSpanishWelcome(String spanishWelcome) {
		this.spanishWelcome = spanishWelcome;
	}
	
	
}
