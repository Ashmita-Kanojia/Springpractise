package com.example.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity // Default name of entity is Class name.
//To change the entity name we can have @Entity(name="Users")
@Table(name = "userTable")
//@JsonIgnoreProperties({"firstName","lastName"})
//Part of static filtering
//this will also not work for POST because they variables are nullable, if we make them true they will work

@JsonFilter(value = "userFilter")
public class User extends RepresentationModel<User>{

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message="Username is Mandatory field. Please provide username")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String userName;
	
	@Size(min=2, message="FirstName should have atleast 2 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "EMAIL", length = 50, nullable = false)
	private String email;
	
	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;
	
	//Note if we create a user with ssn it will give as an error as it is jsonignored & because of which it will send null value which is not allowed 
	//@Column(name = "SSN", length = 50, nullable = false, unique = true)
	//to avoid this we will make nulaable as true that means we can take null values
	//@JsonIgnore part of static filtering
	@Column(name = "SSN", length = 50, nullable = true, unique = true)
	private String ssn;
	
	@OneToMany(mappedBy="user")
	//Order object has foreign key as userId so we map it by user
	private List<Order> orders;

	//No argument constructor mandatory
	public User() {
		
	}

	//Constructor with fields Optional
	public User(Long id, String userName, String firstName, String lastName, String email, String role, String ssn) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	//ToString Optional
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

	//Getter & Setter Mandatory
	public Long getId() {
		return id;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
		
}
