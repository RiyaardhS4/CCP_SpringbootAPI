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
 * Created: 2023/02/15 08:11
 *
 ***********************************************************************/
package com.example.demo2.service;

import com.example.demo2.custom.exception.NoElementFountException;
import com.example.demo2.custom.exception.NonUpdateFieldException;
import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private UserRepository userRepository;

	/**
	 * Constructor for user service to create an instance to the user repository.
	 */
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Retrieving a list of all users from the repository .
	 *
	 * @return list of users
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Adding a user to the user collection .
	 *
	 * @param user User firstName and lastName
	 */
	public void insertUser(UserViewModel user) {
			User userData = new User(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail());
			userRepository.insert(userData);
	}

	/**
	 * Updating user details and checking that the username field has not been updated .
	 *
	 * @param user User firstName , lastName , email and username.
	 */
	public void updateUser(UserViewModel user) {

		User foundUser;

		var data = userRepository.findById(user.getId());
		if (!data.isEmpty()) {
			foundUser = data.get();
			if (user.getUserName().equals(foundUser.getUserName())) {
				foundUser.setFirstName(user.getFirstName());
				foundUser.setLastName(user.getLastName());
				foundUser.setEmail(user.getEmail());
				userRepository.save(foundUser);

			} else {
				throw new NonUpdateFieldException();
			}
		} else {
			throw new NoElementFountException();
		}
	}
}
