package com.junior.banking.dto;

import com.junior.banking.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

	private Integer id;
	
	@NotNull(message = "Le prenom ne doit pas etre vide")
	@NotEmpty(message = "Le prenom ne doit pas etre vide")
	@NotBlank(message = "Le prenom ne doit pas etre vide")
	private String firstname;
	
	@NotNull(message = "Le nom ne doit pas etre vide")
	@NotEmpty(message = "Le nom ne doit pas etre vide")
	@NotBlank(message = "Le nom ne doit pas etre vide")
	private String lastname;
	
	@NotNull(message = "L'email ne doit pas etre vide")
	@NotEmpty(message = "L'email ne doit pas etre vide")
	@NotBlank(message = "L'email ne doit pas etre vide")
	@Email(message = "email nest pas correcte")
	private String email;
	
	@NotNull(message = "Le mot de passe ne doit pas etre vide")
	@NotEmpty(message = "Le mot de passe ne doit pas etre vide")
	@NotBlank(message = "Le mot de passe ne doit pas etre vide")
	@Size(min = 8, max = 19, message = "Le mot de passe doit etre entre 8 et 19 caractères")
	private String password;
	
	public static UserDto fromEntity(User user) {
		return UserDto.builder()
				.id(user.getId())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
	}
	
	public static User toEntity(UserDto user) {
		return User.builder()
				.id(user.getId())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
	}
}
