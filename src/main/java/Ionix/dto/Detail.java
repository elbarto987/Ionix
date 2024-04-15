package Ionix.dto;

public class Detail {

	private String email;
	private String phoneNumber;

	
	public Detail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Detail(String email, String phoneNumber) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
