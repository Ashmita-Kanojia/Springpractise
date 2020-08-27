package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Default name of entity is Class name.
//To change the entity name we can have @Entity(name="Users")
@Table(name = "userTable")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String userName;
	
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "EMAIL", length = 50, nullable = false)
	private String email;
	
	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;
	
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	private String ssn;
	
	@Column(name = "Address", length = 50, nullable = false, unique = true)
	private String address;

	//No argument constructor mandatory
	public User() {
		
	}

	//Constructor with fields Optional
	public User(Long id, String userName, String firstName, String lastName, String email, String role, String ssn,String address) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.address = address;
	}
	//Getter & Setter Mandatory
	public Long getId() {
		return id;
	}
	
	//ToString Optional
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", address=" + address + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
