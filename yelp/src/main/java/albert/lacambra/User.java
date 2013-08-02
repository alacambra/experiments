package albert.lacambra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"type"})
public class User {

	@Id
	String user_id;
	String name;
	
	Float average_stars;
	Integer review_count;
	Integer funny;
	Integer useful;
	Integer cool;
	
	@Transient
	public void setVotes(Votes votes) {
		this.funny = votes.getFunny();
		this.useful = votes.getUseful();
		this.cool = votes.getCool();
	}

	public String getUser_id() {
		return user_id;
	}

	public String getName() {
		return name;
	}

	public Float getAverage_stars() {
		return average_stars;
	}

	public Integer getReview_count() {
		return review_count;
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

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAverage_stars(Float average_stars) {
		this.average_stars = average_stars;
	}

	public void setReview_count(Integer review_count) {
		this.review_count = review_count;
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
	
	
	
}
