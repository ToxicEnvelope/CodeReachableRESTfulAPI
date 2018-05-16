package com.codereachable.restful.webservices.v2.user.account.details;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.codereachable.restful.webservices.v2.user.UserV2Secret;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"secret","origin","timestamp"})
public class Details {

//	@NotNull
	private Alias _alias;
	@Past
	private Date _bDate;
	@NotNull
	// Optional custom @Pattern: 
	// https://stackoverflow.com/questions/4459474/hibernate-validator-email-accepts-askstackoverflow-as-valid
	private String _email;
	@NotNull
	private Integer _score;
	@NotNull
	@Size(min=8, max=14)
	private UserV2Secret _secret;
	@NotNull
	private Address _address;
	private Long origin;
	private Long timestamp;
	
	public Details() {}
	public Details(Alias alias, Date date, String email, UserV2Secret secret) {
		this._alias = alias;
		this._bDate = date;
		this._email = email;
		this._score = 0;
		this._secret = secret;
	}
	public Details(Alias alias, Address addr, Date date, String email, UserV2Secret secret) {
		this._alias = alias;
		this._address = addr;
		this._bDate = date;
		this._email = email;
		this._score = 0;
		this._secret = secret;
	}
	
	
	// Getters & Setters
	public Alias getAlias() {
		return _alias;
	}
	
	public void setAlias(Alias alias) {
		this._alias = alias;
	}
	
	public Date getDateOfBirth() {
		return _bDate;
	}
	
	public void setDateOdBirth(Date date) {
		this._bDate = date;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		this._email = email;
	}
	
	public Integer getScore() {
		return _score;
	}
	
	public void setScore(Integer s) {
		this._score = s;
	}
	
	public UserV2Secret getSecret() {
		return _secret;
	}
	
	public void setSecret(UserV2Secret phrase) {
		this._secret = phrase;
	}
	public Address getAddress() {
		return _address;
	}
	public void setAddress(Address addr) {
		this._address = addr;
	}
	public Long getOrigin() {
		return origin;
	}
	public void setOrigin() {
		this.origin = System.currentTimeMillis();
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp() {
		this.timestamp = System.currentTimeMillis();
	}
	
	public String toString() {
		return "Details : {alias=" + getAlias() + ", address="+ getAddress() +", birthdate=" + getDateOfBirth() +", email=" + getEmail() + ", score=" + getScore() + ", secret=" + getSecret() + "}";
	}
	
}
