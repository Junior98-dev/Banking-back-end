package com.junior.banking.services;

import com.junior.banking.dto.AuthenticationRequest;
import com.junior.banking.dto.AuthenticationResponse;
import com.junior.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

	Integer validateAccount(Integer id);
	
	Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto user);

	AuthenticationResponse authenticate(AuthenticationRequest request);
}
