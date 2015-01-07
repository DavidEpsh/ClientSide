package model.algorithm;

import java.io.Serializable;

public class Action implements Serializable {

	String description;
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private double price;
	
	public Action() {
		
	}
	
	public Action(String description) {
		this.description=description;
	}
	
	@Override
	public int hashCode(){
		return description.hashCode();
	}
	
	@Override
	public String toString(){
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
