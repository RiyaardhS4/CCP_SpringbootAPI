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
 * Created: 2023/02/23 08:39
 *
 ***********************************************************************/
package com.example.demo2.queue.listener;

import com.example.demo2.model.Licence;
import com.example.demo2.model.User;
import com.example.demo2.service.LicenceService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import com.google.gson.Gson;

@EnableBinding(Sink.class)
public class LicenceListener {

	LicenceService licenceService;

	/**
	 * Constructor for  the licence listener .
	 *
	 * @param licenceService instance of the licence service that is used to connect to the database.
	 */
	public LicenceListener(LicenceService licenceService) {
		this.licenceService = licenceService;
	}

	/**
	 * Queue listener that waits for licences from the queue and inserts licences to the database on licence arrival.
	 *
	 * @param licence the licence information that will be inserted to the database .
	 */
	@StreamListener(Sink.INPUT)
	public void queueListener(String licenceData) {
		Licence licence = new Gson().fromJson(licenceData, Licence.class);
		licenceService.addLicence(licence);
	}
}
