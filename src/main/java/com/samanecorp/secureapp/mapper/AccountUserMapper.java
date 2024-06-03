package com.samanecorp.secureapp.mapper;

import com.samanecorp.secureapp.dto.AccountUserDto;
import com.samanecorp.secureapp.entities.AccountUserEntity;

public class AccountUserMapper {
	public static AccountUserEntity userDtoToUserEntity(AccountUserDto userDto) {
		AccountUserEntity userEntity = new AccountUserEntity();
		userEntity.setId(userDto.getId());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(userDto.getPassword());
		
		return userEntity;
	}
	
	public static AccountUserDto userEntityToUserDto(AccountUserEntity userEntity) {
		
		AccountUserDto userDto = new AccountUserDto();
		userDto.setId(userEntity.getId());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPassword(userEntity.getPassword());
		
		return userDto;
	}

}
