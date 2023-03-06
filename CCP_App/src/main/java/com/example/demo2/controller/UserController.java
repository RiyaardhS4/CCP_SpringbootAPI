/************************************************************************
 *                                                                      *
 *  DDDD     SSSS    AAA        Daten- und Systemtechnik Aachen GmbH    *
 *  D   D   SS      A   A       Pascalstrasse 28                        *
 *  D   D    SSS    AAAAA       52076 Aachen-Oberforstbach, Germany     *
 *  D   D      SS   A   A       Telefon: +49 (0)2408 / 9492-0           *
 *  DDDD    SSSS    A   A       Telefax: +49 (0)2408 / 9492-92          *
 *                                                                      *
 *                                                                      *
 *  (c) Copyright by DSA - all rights reserved                          *
 *                                                                      *
 ************************************************************************
 *
 * Author : Riyaardh Adam
 * Created: 2023/02/14 11:23
 *
 ***********************************************************************/
package com.example.demo2.controller;

import com.example.demo2.exception.handler.customexception.NoElementFoundException;
import com.example.demo2.exception.handler.customexception.NonUpdateFieldUserTableException;
import com.example.demo2.model.User;
import com.example.demo2.service.UserService;
import com.example.demo2.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RequestMapping("user")
@RestController
@Validated
@CrossOrigin(origins = "*")
public class UserController {

	UserService userService;

	/**
	 * Constructor for the User Controller which creates a single instance of the User Service .
	 *
	 * @param userService instance to the user service .
	 */
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Add a user to the user table.
	 *
	 * @param user User entity passing firstName and lastName
	 * @return response entity that record has been inserted
	 */
	@PostMapping()
	public ResponseEntity insertUser(@Valid @RequestBody UserViewModel user) {
		userService.insertUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Get all users .
	 *
	 * @return list of users
	 */
	@GetMapping()
	//@RolesAllowed("manager")
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	/**
	 * Update a user record.
	 *
	 * @param user the updated user information that is retrieved from the client
	 * @return a http response 200 to inform the client , the data was uploaded successfully .
	 */
	@PutMapping
	public ResponseEntity updateUser(@Valid @RequestBody UserViewModel user)
			throws NoElementFoundException, NonUpdateFieldUserTableException {
		userService.updateUser(user);
		return new ResponseEntity(HttpStatus.OK);
	}

}
