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
package com.gisgraphy.domain.valueobject;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.junit.Test;

import com.gisgraphy.domain.Constants;
import com.gisgraphy.geoloc.GeolocResultsDto;
import com.gisgraphy.test.FeedChecker;
import com.gisgraphy.test.GisgraphyCommonsTestHelper;

public class GeolocResultsDtoTest {
    

    @Test
    public void geolocResultsDtoShouldBeMappedWithJAXB() {
	try {
	    JAXBContext context = JAXBContext
		    .newInstance(GeolocResultsDto.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    GeolocResultsDto geolocResultsDto = GisgraphyCommonsTestHelper.createGeolocResultsDto(300L);
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    m.marshal(geolocResultsDto, outputStream);
	    FeedChecker.checkGeolocResultsDtoJAXBMapping(geolocResultsDto, outputStream.toString(Constants.CHARSET));
	} catch (PropertyException e) {
	    fail(e.getMessage());
	} catch (JAXBException e) {
	    fail(e.getMessage());
	} catch (UnsupportedEncodingException e) {
	    fail(e.getMessage());
	}
    }

    @Test
    public void geolocResultsDtoForEmptyListShouldreturnValidXML() {
	try {
	    JAXBContext context = JAXBContext
		    .newInstance(GeolocResultsDto.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    List<GisFeatureDistance> list = new ArrayList<GisFeatureDistance>();
	    GeolocResultsDto geolocResultsDto = new GeolocResultsDto(list, 200L);
	    long qTime = geolocResultsDto.getQTime();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    m.marshal(geolocResultsDto, outputStream);
	    try {
		FeedChecker.assertQ(
			"GeolocResultsDto for an Empty List should return valid XML",
			outputStream.toString(Constants.CHARSET), "/"
				+ GeolocResultsDto.GEOLOCRESULTSDTO_JAXB_NAME + "",
			"/" + GeolocResultsDto.GEOLOCRESULTSDTO_JAXB_NAME
				+ "/numFound[.='0']", "/"
				+ GeolocResultsDto.GEOLOCRESULTSDTO_JAXB_NAME
				+ "/QTime[.='" + qTime + "']");
	    } catch (UnsupportedEncodingException e) {
		fail("unsupported encoding exception for " + Constants.CHARSET);
	    }
	} catch (PropertyException e) {
	    fail(e.getMessage());
	} catch (JAXBException e) {
	    fail(e.getMessage());
	}
    }

   

}
