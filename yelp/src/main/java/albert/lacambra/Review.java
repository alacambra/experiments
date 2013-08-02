package albert.lacambra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"type"})
public class Review {

	@Id
	String review_id;
	@Index(name = "user_id_index")
	String user_id;
	@Index(name = "bussiness_id_index")
	String business_id;
	
	Integer funny;
	Integer useful;
	Integer cool;
	
	@Column(columnDefinition="TEXT")
	String text;
	Integer stars;
	
	@Temporal(value = TemporalType.DATE)
	Date date;
	
	@Transient
	public void setVotes(Votes votes) {
		this.funny = votes.getFunny();
		this.useful = votes.getUseful();
		this.cool = votes.getCool();
	}

	public String getReview_id() {
		return review_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public Integer getFunny() {
		return funny;
	}

	public Integer getUseful() {
		return useful;
	}

	public Integer getCool() {
		return cool;
	}

	public String getText() {
		return text;
	}

	public Integer getStars() {
		return stars;
	}

	public Date getDate() {
		return date;
	}

	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public void setFunny(Integer funny) {
		this.funny = funny;
	}

	public void setUseful(Integer useful) {
		this.useful = useful;
	}

	public void setCool(Integer cool) {
		this.cool = cool;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
