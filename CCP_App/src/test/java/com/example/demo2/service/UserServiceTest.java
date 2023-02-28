package com.example.demo2.service;

import com.example.demo2.custom.exception.NonUpdateFieldException;
import com.example.demo2.exception.handler.GlobalExceptionHandler;
import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.viewmodel.UserViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
 * Created: 2023/02/15 09:01
 *
 ***********************************************************************/

public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	UserService service;
	GlobalExceptionHandler exceptionHandler;

	@BeforeEach
	public void init() {
		exceptionHandler = new GlobalExceptionHandler();
		MockitoAnnotations.openMocks(this);
		User user = new User("Riyaardh", "Adam", "DZAC6AG", "riyaardhadam@gmail.com");
		List<User> mockData = new ArrayList<>();
		mockData.add(user);
		when(userRepository.findAll()).thenReturn((List<User>) mockData);
		when(userRepository.findById(any(String.class))).thenReturn(Optional.of((User) user));
		service = new UserService(userRepository);
	}

	@Test
	public void getAllUsers_NotEmptyDatabase_AssertListSizeEqual1() {
		var result = service.getAllUsers();
		assertEquals(1, result.size());
	}

	@Test
	public void insertUser_InsertSuccessful_VerifyInsertCalled() {


		UserViewModel userViewModel = new UserViewModel();
		userViewModel.setFirstName("Riyaardh");
		userViewModel.setLastName("Adam");
		userViewModel.setUserName("DZAC6AG");
		userViewModel.setEmail("riyaardhadam@gmail.com");
		service.insertUser(userViewModel);
		verify(userRepository,times(1)).insert(any(User.class));
	}
	@Test
	public void updateUser_UpdateSuccessful_VerifyUpdateCalled() {


		UserViewModel userViewModel = new UserViewModel();
		userViewModel.setId("1");
		userViewModel.setFirstName("Riyaardh2");
		userViewModel.setLastName("Adam2");
		userViewModel.setUserName("DZAC6AG");
		userViewModel.setEmail("riyaardhadam@gmail.com");
		service.updateUser(userViewModel);
		verify(userRepository,times(1)).save(any(User.class));
	}

	@Test
	public void updateUser_NonUpdateExceptionThrown_VerifyUsernameCannotBeUpdated() {


		UserViewModel userViewModel = new UserViewModel();
		userViewModel.setId("1");
		userViewModel.setFirstName("Riyaardh2");
		userViewModel.setLastName("Adam2");
		userViewModel.setUserName("Test1");
		userViewModel.setEmail("riyaardhadam@gmail.com");

		assertThrows(NonUpdateFieldException.class,()->{
			service.updateUser(userViewModel);
		});
	}
}
