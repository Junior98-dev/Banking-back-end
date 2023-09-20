package com.junior.banking.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junior.banking.dto.UserDto;
import com.junior.banking.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	
	private final UserService service;
	
	
	@PostMapping("/")
	public ResponseEntity<Integer> save(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(service.save(userDto));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> findAll(){
		return ResponseEntity.ok(service.findAll());	
	}
	
	@GetMapping("/{user-id}")
	public ResponseEntity<UserDto> findById(@PathVariable("user-id") Integer userId){
		return ResponseEntity.ok(service.findById(userId));
	}
	
	@PatchMapping("/valide/{user-id}")
	public ResponseEntity<Integer> valideAccount(@PathVariable("user-id") Integer userId){
		return ResponseEntity.ok(service.validateAccount(userId));
	}
	
	@PatchMapping("/invalide/{user-id}")
	public ResponseEntity<Integer> invalideAccount(@PathVariable("user-id") Integer userId){
		return ResponseEntity.ok(service.invalidateAccount(userId));
	}
	
	@DeleteMapping("/delete/{user-id}")
	public ResponseEntity<Void> delete(@PathVariable("user-id") Integer userId){
		service.delecte(userId);
		return ResponseEntity.accepted().build();
	}
	
	
	
}
