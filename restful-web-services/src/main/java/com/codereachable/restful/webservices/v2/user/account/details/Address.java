package com.codereachable.restful.webservices.v2.user.account.details;

import javax.validation.constraints.NotNull;

public class Address {

	@NotNull
	private String _country;
	
	@NotNull
	private String _state;
	
	@NotNull
	private Integer _countryCode;
	
	@NotNull
	private String _city;
	
	@NotNull
	private String _address;
	
	@NotNull
	private Integer _zipCode;

	public Address() {}
	
	public Address(String country, String state, Integer countryCode, String city,
		   String address, Integer zipCode) {
		this._country = country;
		this._state = state;
		this._countryCode = countryCode;
		this._city = city;
		this._address = address;
		this._zipCode = zipCode;
	}
	
	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		this._country = country;
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		this._state = state;
	}

	public Integer getCountryCode() {
		return _countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this._countryCode = countryCode;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		this._address = address;
	}

	public Integer getZipCode() {
		return _zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this._zipCode = zipCode;
	}
	
	public String getCity() {
		return _city;
	}
	
	public void setCity(String city) {
		this._city = city;
	}
	
	@Override
	public String toString() {
		return "Address : {country=" + getCountry() + ", state=" + getState() + ", countryCode=" + getCountryCode()+ ", city="+ getCity() + ", address="
				+ getAddress() + ", zipCode=" + getZipCode() + "}";
	}

	

}
