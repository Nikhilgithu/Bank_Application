package com.techlab.banking.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtAuthResponse {

	private String accessToken;
	private String tokenType="Bearer";
	private String userType;
	public void setUserType(String userType) {
		this.userType = userType;
		
	}
}
