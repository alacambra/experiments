package albert.lacambra.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	
	@Id String name;
	
	@ManyToMany
	Set<Bussines> bussines = new HashSet<Bussines>();
	
	public String getName() {
		return name;
	}
	
	public Category(String name){
		this.name = name;
	}
//	public Bussines getBussines() {
//		return bussines;
//	}
	public void setName(String name) {
		this.name = name;
	}
	public void addBussines(Bussines bussines) {
		this.bussines.add(bussines);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Category)) return false;
		Category c = (Category) obj;
		return c.getName().equals(name);
	}
}
