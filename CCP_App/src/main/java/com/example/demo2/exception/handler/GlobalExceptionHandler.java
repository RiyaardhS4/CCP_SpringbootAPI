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
 * Created: 2023/02/21 10:27
 *
 ***********************************************************************/
package com.example.demo2.exception.handler;

import com.example.demo2.custom.exception.NoElementFountException;
import com.example.demo2.custom.exception.NonUpdateFieldException;
import com.example.demo2.custom.exception.RequiredInputNotSuppliedException;
import com.example.demo2.viewmodel.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Global exception handler that will be executed when an exception of type "Exception" is thrown.
	 * @param exception the exception that has been thrown with the exception information.
	 * @return a error model and a http response code of 500.
	 */
	@ExceptionHandler
	public ResponseEntity<?> handleException(Exception exception){
		ErrorModel errorModel = new ErrorModel("An error has occurred " + exception.getMessage(), new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/**
	 * Global exception handler that will be executed when a custom exception of type "RequiredInputNotSuppliedException" is thrown. The exception "RequiredInputNotSuppliedException" is thrown when a required value has not been supplied by the client to the controller .
	 * @param exception the exception that has been thrown with the exception information.
	 * @return a error model and a http response code of 400.
	 */
	@ExceptionHandler
	public ResponseEntity<?> handleRequiredInputNotSuppliedException(RequiredInputNotSuppliedException exception){
		ErrorModel errorModel = new ErrorModel("The required inputs have not been supplied", new Date(),HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Global exception handler that will be executed when a custom exception of type "NoElementFountException" is thrown.The exception "NoElementFountException" is thrown when an attempt to receive a record from the database , but the call return no results .
	 * @param exception the exception that has been thrown with the exception information.
	 * @return a error model and a http response code of 400.
	 */
	@ExceptionHandler
	public ResponseEntity<?> handleNoElementFountException(NoElementFountException exception){
		ErrorModel errorModel = new ErrorModel("The requested element could not be found", new Date(),HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Global exception handler that will be executed when a custom exception of type "NonUpdateFieldException" is thrown.The exception "NonUpdateFieldException" will be thrown if the system tries to update a field in the User table that is locked for editing.
	 * @param exception the exception that has been thrown with the exception information.
	 * @return a error model and a http response code of 400.
	 */
	@ExceptionHandler
	public ResponseEntity<?> handleNonUpdateFieldException(NonUpdateFieldException exception){
		ErrorModel errorModel = new ErrorModel("Username cannot be updated", new Date(),HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Global exception handler that will be executed when a custom exception of type "ConstraintViolationException" is thrown.The exception "ConstraintViolationException" is thrown when the client sends a model that is mot in the required format or is missing required fields .
	 * @param exception the exception that has been thrown with the exception information.
	 * @return a error model and a http response code of 400.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
		ErrorModel errorModel = new ErrorModel("Required fields have not been supplied or are in a invalid format", new Date(),HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Global exception handler that will be executed when a custom exception of type "MethodArgumentNotValidException" is thrown.The exception "ConstraintViolationException" is thrown when the client sends a model that is mot in the required format or is missing required fields .
	 * @param exception the exception that has been thrown with the exception information.
	 * @return a error model and a http response code of 400.
	 */
	@ExceptionHandler
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		ErrorModel errorModel = new ErrorModel("Required fields have not been supplied or are in a invalid format", new Date(),HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}




}
