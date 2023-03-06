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
 * Created: 2023/02/23 08:44
 *
 ***********************************************************************/
package com.example.demo2.service;

import com.example.demo2.model.Licence;
import com.example.demo2.repository.ILicenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenceService {

	private ILicenceRepository licenceRepository;

	/**
	 * Constructor for licence service to create an instance to the licence repository.
	 */
	@Autowired
	public LicenceService(ILicenceRepository licenceRepository) {
		this.licenceRepository = licenceRepository;
	}

	/**
	 * Using the licence repository to add a licence to the database .
	 */
	public void addLicence(Licence licence) {
		licenceRepository.insert(licence);
	}

}
