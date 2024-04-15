package Ionix.dto;

public class ObjectJson {

	private long responseCode;
	private String description;
	private Long elapsedTime;
	private Result result;
	
	public ObjectJson() {
	}


	public ObjectJson(long responseCode, String description, Long elapsedTime, Result result) {
		super();
		this.responseCode = responseCode;
		this.description = description;
		this.elapsedTime = elapsedTime;
		this.result = result;
	}
	
	public ObjectJson(Result result) {
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

	public Long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	public static Result newResult(int registerCount) {
		return new Result(registerCount);
	}

	public static class Result{
		
		private Integer registerCount;

		public Result() {
		}

		public Result(Integer registerCount) {
			this.registerCount = registerCount;
		}

		public Integer getRegisterCount() {
			return registerCount;
		}

		public void setRegisterCount(Integer registerCount) {
			this.registerCount = registerCount;
		}
		
		
	}
}
