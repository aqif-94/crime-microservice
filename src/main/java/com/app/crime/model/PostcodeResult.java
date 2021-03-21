package com.app.crime.model;

import java.math.BigDecimal;

public class PostcodeResult {
	
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

}
