package com.usermanagement;

public class Studentdetails {
	String firstname;
	String Lastname;
	String city;
	String email;
	String Username;
	String Password;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "studentdetqails [firstname=" + firstname + ", Lastname=" + Lastname + ", city=" + city + ", email="
				+ email + ", Username=" + Username + ", Password=" + Password + "]";
	}

	public Studentdetails(String firstname, String lastname, String city, String email, String username,
			String password) {

		this.firstname = firstname;
		this.Lastname = lastname;
		this.city = city;
		this.email = email;
		this.Username = username;
		this.Password = password;
	}

}
