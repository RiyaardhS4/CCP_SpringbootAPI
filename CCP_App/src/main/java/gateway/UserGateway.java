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
 * Created: 2023/03/06 07:23
 *
 ***********************************************************************/
package gateway;

import com.example.demo2.model.User;
import com.example.demo2.viewmodel.UserViewModel;
import org.keycloak.jose.jwk.JWK;

public class UserGateway {

	/**
	 * Converts a User View Model to a User Model that can be used by the user repository .
	 * @param model user view model .
	 * @return user model .
	 */
	public User convertUserToUserModel(UserViewModel model){

		User user = new User(model.getFirstName(), model.getLastName(), model.getUserName(), model.getEmail());

		return user;
	}

}
