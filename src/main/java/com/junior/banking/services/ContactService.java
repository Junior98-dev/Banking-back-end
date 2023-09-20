package com.junior.banking.services;

import java.util.List;

import com.junior.banking.dto.ContactDto;

public interface ContactService extends AbstractService<ContactDto>{

	List<ContactDto> findAllByUserId(Integer userId);
}
;