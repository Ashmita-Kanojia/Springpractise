package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.Order;

public class UserMmDto {

	private Long id;
	private String userName;
	private List<Order> orders;
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}

