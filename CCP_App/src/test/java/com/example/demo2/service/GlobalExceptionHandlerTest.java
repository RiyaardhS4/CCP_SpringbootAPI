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
 * Created: 2023/03/06 07:28
 *
 ***********************************************************************/
package com.example.demo2.service;

import com.example.demo2.exception.handler.GlobalExceptionHandler;
import com.example.demo2.exception.handler.customexception.NoElementFoundException;
import com.example.demo2.exception.handler.customexception.NonUpdateFieldUserTableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

	GlobalExceptionHandler handler;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		handler = new GlobalExceptionHandler();

	}

	@Test
	public void NonUpdateFieldUserTableException_ThrowsException_AssertResponseCodeMethodNotAllowed() {

		NonUpdateFieldUserTableException exception = new NonUpdateFieldUserTableException();
		var response = handler.handleNonUpdateFieldUserTableException(exception);
		assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Test
	public void NoElementFoundException_ThrowsException_AssertResponseCodeBadRequest() {

		NoElementFoundException exception = new NoElementFoundException();
		var response = handler.handleNoElementFoundException(exception);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void Exception_ThrowsException_AssertResponseCodeInternalServerError() {

		Exception exception = new Exception();
		var response = handler.handleException(exception);
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	public void ConstraintViolationException_ThrowsException_AssertResponseCodeBadRequest() {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set violations = validator.validate(String.class);
		ConstraintViolationException exception = new ConstraintViolationException(violations);
		var response = handler.handleConstraintViolationException(exception);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void MethodArgumentNotValidException_ThrowsException_AssertResponseCodeBadRequest()
			throws NoSuchMethodException {

		var method = this.getClass().getMethod("mockMethod");
		MethodParameter parameter = new MethodParameter(method, -1);
		BindingResult bindingResult = mock(BindingResult.class);
		MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);
		var response = handler.handleMethodArgumentNotValidException(exception);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	public void mockMethod() {}

}
