package pojo;

public class UserAccountPOJO {
	private String phoneNumber, password, name, imageUrl;
	private int status;
	
	public UserAccountPOJO() {
		super();
	}

	public UserAccountPOJO(String phoneNumber, String password, String name, String imageUrl, int status) {
		super();
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.name = name;
		this.imageUrl = imageUrl;
		this.status = status;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return phoneNumber;
	}
}
