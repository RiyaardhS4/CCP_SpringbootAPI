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

import com.example.demo2.exception.handler.customexception.DatabaseNotAccessibleException;
import com.example.demo2.exception.handler.customexception.NoElementFoundException;
import com.example.demo2.exception.handler.customexception.NonUpdateFieldUserTableException;
import com.example.demo2.model.User;
import com.example.demo2.repository.IUserRepository;
import com.example.demo2.viewmodel.UserViewModel;
import gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

	private IUserRepository userRepository;
	private UserGateway userGateway;

	/**
	 * Constructor for user service to create an instance to the user repository.
	 */
	@Autowired
	public UserService(IUserRepository userRepository, UserGateway userGateway) {
		this.userRepository = userRepository;
		this.userGateway = userGateway;
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
		User userData = userGateway.convertUserToUserModel(user);
		userRepository.insert(userData);
	}

	/**
	 * Updating user details and checking that the username field has not been updated .
	 *
	 * @param user User firstName , lastName , email and username.
	 */
	public void updateUser(UserViewModel model)
			throws NoElementFoundException, NonUpdateFieldUserTableException, DatabaseNotAccessibleException {

		try {

			var data = userRepository.findById(model.getId());
			if (!data.isEmpty()) {
				User user = userGateway.convertUserToUserModel(model);
				User foundUser = data.get();
				if (user.getUserName().equals(foundUser.getUserName())) {
					userRepository.save(user);

				} else {
					throw new NonUpdateFieldUserTableException();
				}
			} else {
				throw new NoElementFoundException();
			}
		} catch (DatabaseNotAccessibleException exception) {
			throw exception;
		}
	}
}
