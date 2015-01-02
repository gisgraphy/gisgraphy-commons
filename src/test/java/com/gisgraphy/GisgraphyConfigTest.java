/*******************************************************************************
 *   Gisgraphy Project 
 * 
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 * 
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details.
 * 
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA
 * 
 *  Copyright 2008  Gisgraphy project 
 *  David Masclet <davidmasclet@gisgraphy.com>
 *  
 *  
 *******************************************************************************/
/**
 * 
 */
package com.gisgraphy;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.junit.Test;
import org.slf4j.Logger;

import com.gisgraphy.domain.geoloc.entity.TestEntity;
import com.gisgraphy.domain.valueobject.GisgraphyConfig;

/**
 * 
 */
public class GisgraphyConfigTest extends TestCase {

    @Test
    public void testSetDefaultGeolocSearchPlaceTypeWithNullOrEmptyValueShouldSettheDefaultPlaceTypeClassToNULL() {
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	// set a value before testing
	gisgraphyConfig.setDefaultGeolocSearchPlaceType("TestEntity");
	assertEquals(TestEntity.class,
		GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);
	gisgraphyConfig.setDefaultGeolocSearchPlaceType(" ");
	assertNull(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);

	gisgraphyConfig.setDefaultGeolocSearchPlaceType("City");
	gisgraphyConfig.setDefaultGeolocSearchPlaceType(null);
	assertNull(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);
    }

    @Test
    public void testSetDefaultGeolocSearchPlaceTypeShouldReallySetTheClassAcordingToEntityPackage() {
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	gisgraphyConfig.setDefaultGeolocSearchPlaceType("TestEntity");
	assertEquals(TestEntity.class,
		GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);

    }

    @Test
    public void testSetDefaultGeolocSearchPlaceTypeWithWrongValueShouldSettheDefaultPlaceTypeClassToNULL() {
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	gisgraphyConfig.setDefaultGeolocSearchPlaceType("NO");
	assertNull(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);

    }

    @Test
    public void testSetDefaultGeolocSearchPlaceTypeShouldBeCaseSensitive() {
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	gisgraphyConfig.setDefaultGeolocSearchPlaceType("city");
	assertNull(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);

    }
    
    @Test
    public void testEmptyGoogleMapAPIKeyShouldLog(){
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	Logger saveLogger = GisgraphyConfig.logger;
	try {
	    Logger logger = EasyMock.createMock(Logger.class);
	    logger.warn((String) EasyMock.anyObject());
	    EasyMock.replay(logger);
	    GisgraphyConfig.logger= logger;
	    gisgraphyConfig.setGoogleMapAPIKey(" ");
	    EasyMock.verify(logger);
	} finally {
	    GisgraphyConfig.logger = saveLogger;
	}
    }
    
    @Test
    public void testNullGoogleMapAPIKeyShouldLog(){
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	Logger saveLogger = GisgraphyConfig.logger;
	try {
	    Logger logger = EasyMock.createMock(Logger.class);
	    logger.warn((String) EasyMock.anyObject());
	    EasyMock.replay(logger);
	    GisgraphyConfig.logger= logger;
	    gisgraphyConfig.setGoogleMapAPIKey(null);
	    EasyMock.verify(logger);
	    Assert.assertEquals(null, GisgraphyConfig.googleMapAPIKey);
	} finally {
	    GisgraphyConfig.logger = saveLogger;
	}
    }
    
    @Test
    public void testSetGoogleMapAPIKeyShouldLogAndSet(){
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	Logger saveLogger = GisgraphyConfig.logger;
	try {
	    Logger logger = EasyMock.createMock(Logger.class);
	    logger.info((String) EasyMock.anyObject());
	    EasyMock.replay(logger);
	    GisgraphyConfig.logger= logger;
	    String googleMapAPIKey = "key";
	    gisgraphyConfig.setGoogleMapAPIKey(googleMapAPIKey);
	    Assert.assertEquals(googleMapAPIKey,GisgraphyConfig.googleMapAPIKey);
	    EasyMock.verify(logger);
	} finally  {
	    GisgraphyConfig.logger = saveLogger;
	}
    }
    
    @Test
    public void testEmptyGoogleAnalyticsCodeShouldLog(){
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	Logger saveLogger = GisgraphyConfig.logger;
	try {
	    Logger logger = EasyMock.createMock(Logger.class);
	    logger.warn((String) EasyMock.anyObject());
	    EasyMock.replay(logger);
	    GisgraphyConfig.logger= logger;
	    gisgraphyConfig.setGoogleanalytics_uacctcode(" ");
	    EasyMock.verify(logger);
	} finally {
	    GisgraphyConfig.logger = saveLogger;
	}
    }
    
    @Test
    public void testNullGoogleAnalyticsCodeShouldLog(){
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	Logger saveLogger = GisgraphyConfig.logger;
	try {
	    Logger logger = EasyMock.createMock(Logger.class);
	    logger.warn((String) EasyMock.anyObject());
	    EasyMock.replay(logger);
	    GisgraphyConfig.logger= logger;
	    gisgraphyConfig.setGoogleanalytics_uacctcode(null);
	    EasyMock.verify(logger);
	    Assert.assertEquals(null, GisgraphyConfig.googleanalytics_uacctcode);
	} finally {
	    GisgraphyConfig.logger = saveLogger;
	}
    }
    
    @Test
    public void testSetGoogleAnalyticsCodeShouldLogAndSet(){
	GisgraphyConfig gisgraphyConfig = new GisgraphyConfig();
	Logger saveLogger = GisgraphyConfig.logger;
	try {
	    Logger logger = EasyMock.createMock(Logger.class);
	    logger.info((String) EasyMock.anyObject());
	    EasyMock.replay(logger);
	    GisgraphyConfig.logger= logger;
	    String code = "code";
	    gisgraphyConfig.setGoogleanalytics_uacctcode(code);
	    Assert.assertEquals(code,GisgraphyConfig.googleanalytics_uacctcode);
	    EasyMock.verify(logger);
	} finally  {
	    GisgraphyConfig.logger = saveLogger;
	}
    }
    

}
