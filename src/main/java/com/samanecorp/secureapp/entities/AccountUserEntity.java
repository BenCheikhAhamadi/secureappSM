package com.samanecorp.secureapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_users")
public class AccountUserEntity implements Serializable {
	
	private static final long serialVersion = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	@Column(name="email",nullable=false,length=200, unique=true)
	private String email; 
	@Column(name="password",nullable=false)
	private String password;
	private boolean state;
	
	public AccountUserEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	

}
