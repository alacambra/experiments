package albert.lacambra;

import java.util.Map;


public class ChekinInfo {

	String type;
	String business_id;
	Map<String, Integer> checkin_info;
	
	public String getType() {
		return type;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public Map<String, Integer> getCheckin_info() {
		return checkin_info;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public void setCheckin_info(Map<String, Integer> checkin_info) {
		this.checkin_info = checkin_info;
	}
	
	
	
}
