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
 * Created: 2023/02/22 14:16
 *
 ***********************************************************************/
package com.example.demo2.exception.handler;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorModel {
	private String errorMessage;
	private Date errorTimeStamp;

	/**
	 * Constructor for ErrorModel Class.
	 *
	 * @param errorMessage   the error that was thrown .
	 * @param errorTimeStamp the time the error occurred.
	 */
	public ErrorModel(String error, Date errorTimeStamp, String errorResponseCode) {
		this.errorMessage = error;
		this.errorTimeStamp = errorTimeStamp;
	}
}
