
package com.samanecorp.secureapp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.secureapp.dto.AccountUserDto;
import com.samanecorp.secureapp.entities.AccountUserEntity;
import com.samanecorp.secureapp.exceptions.EntityNotFoundException;
import com.samanecorp.secureapp.mapper.AccountUserMapper;
import com.samanecorp.secureapp.repository.impl.AccountUserRepositoryImpl;
import com.samanecorp.secureapp.repository.impl.LoginRepository;
import com.samanecorp.secureapp.repository.imterface.IAccountUserRepository;

public class LoginService {
	 private static Logger logger = LoggerFactory.getLogger(LoginService.class);
	 public LoginRepository loginRepository = new LoginRepository();
	 private IAccountUserRepository userDao = new AccountUserRepositoryImpl();
	 
	 public Optional<AccountUserDto> login(String email, String password) {
			
			if (email.equals("contact@samanecorporation.com") && password.equals("passer@1234")) {
				AccountUserDto accountUserDto = new AccountUserDto();
				accountUserDto.setEmail(email);
				accountUserDto.setPassword(password);
				
				return Optional.of(accountUserDto) ;
			} else {
				return Optional.ofNullable(null);
			}
			
		}
		
	 
	 public Optional<AccountUserDto> logException(String email, String password) {
			logger.info("Tantative email: {}pwd",email, password);
			
			return loginRepository.logException(email, password)
					.map(user->{
						AccountUserDto accountUserDto = AccountUserMapper.userEntityToUserDto(user);
						logger.info("infos correct !");
						return Optional.of(accountUserDto);
					}).orElseThrow( () -> new EntityNotFoundException("infos incorrect."));	
		}

	  
 public Optional<AccountUserDto> loginMockito (String email,String password){
		 
		 logger.info("Tentative email:{} pwd:{}",email,password);
		 Optional<AccountUserEntity> accountUserEntityOptional = loginRepository.logException(email, password);
		 if(accountUserEntityOptional.isPresent()) {
			 AccountUserEntity accountUserEntity = accountUserEntityOptional.get();
			 AccountUserDto accountUserDto = AccountUserMapper.userEntityToUserDto(accountUserEntity);
			 return Optional.of(accountUserDto);
		 } else {
			 return Optional.ofNullable(null);
		 }
	 }
	 
  public Optional<AccountUserDto> log (String email, String password) {
		
		logger.info("Tentattive email : {} pwd : {}", email, password);
		
		Optional<AccountUserEntity> accountUserEntityOption = loginRepository.log(email, password);
		if (accountUserEntityOption.isPresent()) {
			AccountUserEntity accountUserEntity = accountUserEntityOption.get();
			AccountUserDto  accountUserDto = AccountUserMapper.userEntityToUserDto(accountUserEntity);
						
			return Optional.of(accountUserDto) ;
		} else {
			return Optional.ofNullable(null);
		}
		
	}
	 

	
	public boolean save(AccountUserDto accountUserDto) {
		return userDao.add(AccountUserMapper.userDtoToUserEntity(accountUserDto));	
	}

	public LoginRepository getLoginRepository() {
		return loginRepository;
		
	}
	public void setLoginRepositry(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

}

