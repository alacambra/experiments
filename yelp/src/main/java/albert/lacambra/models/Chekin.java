package albert.lacambra.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"type"})
public class Chekin {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	@Index(name = "business_id_index")
	String business_id;
	String date;
	Integer chekins;
	
	
	public Chekin(){
		
	}
	
	public Chekin(String business_id, String date, Integer chekins) {
		super();
		this.business_id = business_id;
		this.date = date;
		this.chekins = chekins;
	}



	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getDate() {
		return date;
	}
	public Integer getChekins() {
		return chekins;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setChekins(Integer chekins) {
		this.chekins = chekins;
	}
	
	
}
