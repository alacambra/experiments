package albert.lacambra;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"neighborhoods", "type"})
public class Bussines {
	
	@Id String business_id;
	String full_address;
	boolean open;
	
	@ManyToMany(mappedBy="bussines")
	List<Category> categories;
	String city;
	String review_count;
	String name;
	Double longitude;
	Double latitude;
	String state;
	Float stars;
	
	public String getBusiness_id() {
		return business_id;
	}
	public String getFull_address() {
		return full_address;
	}
	public boolean isOpen() {
		return open;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public String getCity() {
		return city;
	}
	public String getReview_count() {
		return review_count;
	}
	public String getName() {
		return name;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public String getState() {
		return state;
	}
	public Float getStars() {
		return stars;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setReview_count(String review_count) {
		this.review_count = review_count;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setStars(Float stars) {
		this.stars = stars;
	}
	
	
	
}
