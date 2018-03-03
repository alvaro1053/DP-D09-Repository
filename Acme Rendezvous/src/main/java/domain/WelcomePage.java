package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


@Entity
@Access(AccessType.PROPERTY)
public class WelcomePage extends DomainEntity {
	
	private String			englishName;
	
	private String			spanishName;

	private String			Banner;

	private String			englishWelcome;

	private String			spanishWelcome;
	
	
	
	@NotBlank
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	
	@NotBlank
	public String getSpanishName() {
		return spanishName;
	}

	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}

	@NotBlank
	@URL
	public String getBanner() {
		return Banner;
	}

	public void setBanner(String banner) {
		Banner = banner;
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
