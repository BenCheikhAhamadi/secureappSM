package com.samanecorp.secureapp.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.samanecorp.secureapp.dto.AccountUserDto;
import com.samanecorp.secureapp.entities.AccountUserEntity;
import com.samanecorp.secureapp.exceptions.EntityNotFoundException;
import com.samanecorp.secureapp.repository.impl.LoginRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoginServiceTestMockito {
	@InjectMocks
private LoginService loginService;
private LoginRepository loginRepository;

@BeforeEach
void init() {
	loginService = new LoginService();
	loginRepository = mock(LoginRepository.class);
	loginService.setLoginRepositry(loginRepository);
}

@Test
void loginOK () {

	AccountUserEntity  accountUserEntity = new AccountUserEntity();
	accountUserEntity.setId(1L);
	accountUserEntity.setEmail("contact@samanecorporation.com");
	accountUserEntity.setPassword("passer@123");
	
	when(loginRepository.logException(anyString(), anyString()))
		.thenReturn(Optional.ofNullable(accountUserEntity));
	
	AccountUserDto accountUserDto = new AccountUserDto();
	accountUserDto.setEmail("contact@samanecorporation.com");
	accountUserDto.setPassword("passer@123");
	
	Optional<AccountUserDto> user = loginService.loginMockito(accountUserDto.getEmail(), accountUserDto.getPassword());
	
	Assertions.assertTrue(user.isPresent());
}

@Test 
void loginKO () {

	  Mockito.lenient().when(loginRepository.logException(anyString(), anyString()))
	  .thenReturn(Optional.ofNullable(null));
	  
	  AccountUserDto accountUserDto = new AccountUserDto();
		accountUserDto.setEmail("ben@samanecorporation.com");
		accountUserDto.setPassword("passer@123");
	  
	  Optional<AccountUserDto> user = loginService.loginMockito(accountUserDto.getEmail(),
			  accountUserDto.getPassword());
	  
	  Assertions.assertTrue(user.isEmpty()); 
}

	@Test 
void  loginExceptionOK () {
	AccountUserEntity accountUserEntity = new AccountUserEntity();
	accountUserEntity.setId(1L);
	accountUserEntity.setEmail("contact@samanecorporation.com");
	accountUserEntity.setPassword("passer@123");
	when(loginRepository.logException(anyString(),anyString()))
	.thenReturn(Optional.ofNullable(accountUserEntity));
	
	AccountUserDto accountUserDto = new AccountUserDto();
	accountUserDto.setEmail("contact@samanecorporation.com");
	accountUserDto.setPassword("passer@123");
	
	Optional<AccountUserDto> user = loginService.loginMockito(accountUserDto.getEmail(),accountUserDto.getPassword());
	Assertions.assertTrue(user.isPresent());
}
	@Test
	void loginExceptionKO () {
		Mockito.lenient().when(loginRepository.logException(anyString(), anyString()))
		.thenReturn(Optional.ofNullable(null));
		
		EntityNotFoundException entityNotFoundException = assertThrows(
				EntityNotFoundException.class,
				()->loginService.logException("ben@samanecorporation.com","passer@123"));
		
	}
}
