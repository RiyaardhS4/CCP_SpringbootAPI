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
 * Created: 2023/02/14 11:54
 *
 ***********************************************************************/
package com.example.demo2.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "User")
public class User {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;

	/**
	 * Model for user class.
	 *
	 * @param firstName user first name
	 * @param lastName  user last name
	 * @param email  user email address
	 * @param userName user username
	 */
	public User(String firstName, String lastName,String userName,String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName= userName;
		this.email=email;
	}
}
