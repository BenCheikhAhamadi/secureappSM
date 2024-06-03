package com.samanecorp.secureapp.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samanecorp.secureapp.dto.AccountUserDto;

public class LoginServiceTest {
	private LoginService loginService;
	
	@BeforeEach
	void init() {
		loginService = new LoginService();
	}
	
	void logOK() {
		String email ="contact@samanecorporation.com";
		String password = "passer@123";
      Optional<AccountUserDto> user = loginService.log(email, password);
		
		Assertions.assertTrue(user.isPresent());
	}
	@Test
	void loginOK () {
		
		AccountUserDto accountUserDto = new AccountUserDto();
		accountUserDto.setEmail("contact@samanecorporation.com");
		accountUserDto.setPassword("passer123@");
		
		Optional<AccountUserDto> user = loginService.login(accountUserDto.getEmail(), accountUserDto.getPassword());
		
		Assertions.assertTrue(user.isPresent());
	}
	@Test
	void loginKO () {
		
		AccountUserDto  accountUserDto = new AccountUserDto();
		accountUserDto.setEmail("ben@samanecorporation.com");
		accountUserDto.setPassword("passer@123");
		
		Optional<AccountUserDto> user = loginService.login(accountUserDto.getEmail(), accountUserDto.getPassword());
		
		Assertions.assertTrue(user.isEmpty());
	}
	

}
