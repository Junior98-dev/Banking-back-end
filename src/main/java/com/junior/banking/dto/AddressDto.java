package com.junior.banking.dto;

import com.junior.banking.entities.Address;
import com.junior.banking.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDto {

	private Integer id;
	
	private String street;
	
	private Integer houseNumber;
	
	private Integer ZipCode;
	
	private String city;
	
	private String country;
	
	private Integer userId;
	
	public static AddressDto fromEntity(Address address) {
		return AddressDto.builder()
				.id(address.getId())
				.street(address.getStreet())
				.houseNumber(address.getHouseNumber())
				.city(address.getCity())
				.country(address.getCountry())
				.userId(address.getUser().getId())
				.build();
	}
	
	public static Address toEntity(AddressDto address) {
		return Address.builder()
				.id(address.getId())
				.street(address.getStreet())
				.houseNumber(address.getHouseNumber())
				.city(address.getCity())
				.country(address.getCountry())
				.user(
					User.builder()
						.id(address.getUserId())
							.build()
				)
				.build();
	}
}
