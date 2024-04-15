package Ionix.dto;

public class Item {

	private String name;
	private Detail detail;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(String name, Detail detail) {
		super();
		this.name = name;
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	
	
}
