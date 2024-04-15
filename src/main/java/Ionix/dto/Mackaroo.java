package Ionix.dto;

public class Mackaroo {

	private long responseCode;
	private String description;
	private Result result;
	
	public Mackaroo() {
}

	public Mackaroo(long responseCode, String description, Result result) {
		this.responseCode = responseCode;
		this.description = description;
		this.result = result;
	}

	public long getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	
}
