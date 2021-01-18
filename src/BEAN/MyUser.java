package BEAN;

public class MyUser {
	private int id;
	private String fullName;
	private String username;
	private String password;
	private String address;
	private String phone;
	private String hash;
	private int active;
	private Role role;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public MyUser(int id, String fullName, String username, String password, String address, String phone, String hash,
			int active, Role role) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.hash = hash;
		this.active = active;
		this.role = role;
	}

	public MyUser() {
		super();
	}
	
}
