package com.n2s.model;

public class City {

	private String originCity;

	private String destnCity;

	public City(String originCity, String destnCsity) {

		this.setOriginCity(originCity);
		this.setDestnCity(destnCsity);
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestnCity() {
		return destnCity;
	}

	public void setDestnCity(String destnCity) {
		this.destnCity = destnCity;
	}

}
