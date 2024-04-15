package Ionix.dto;

import java.util.List;

public class Result {

	private List<Item> items;

	public Result() {
	}

	public Result(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
	
}
