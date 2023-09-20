package com.junior.banking.services;

import com.junior.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

	Integer validateAccount(Integer id);
	
	Integer invalidateAccount(Integer id);

}
