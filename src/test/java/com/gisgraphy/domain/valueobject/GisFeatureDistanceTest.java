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
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import junit.framework.Assert;

import org.junit.Test;

import com.gisgraphy.test.FeedChecker;
import com.gisgraphy.test.GisgraphyCommonsTestHelper;

public class GisFeatureDistanceTest  {


    @Test
    public void testGisFeatureDistanceShouldBeMappedWithJAXBAndHaveCalculatedFieldsWhenConstructWithGisFeatureAndDistance() {
	GisFeatureDistance result = null;
	try {
	    JAXBContext context = JAXBContext
		    .newInstance(GisFeatureDistance.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    result = GisgraphyCommonsTestHelper
		    .createFullFilledGisFeatureDistanceWithConstructor();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    m.marshal(result, outputStream);
	    FeedChecker.checkGisFeatureDistanceJAXBMapping(result, outputStream.toString(Constants.CHARSET),"");
	} catch (PropertyException e) {
	    fail(e.getMessage());
	} catch (JAXBException e) {
	    fail(e.getMessage());
	} catch (UnsupportedEncodingException e) {
	    fail(e.getMessage());
	}
    }

   
    

   

   @Test
   public void testEqualsShouldTakeFeatureIdIntoAccount(){
	Long featureId = 123L;
	GisFeatureDistance gisFeatureDistance = new GisFeatureDistance();
	gisFeatureDistance.setFeatureId(featureId);
	GisFeatureDistance gisFeatureDistanceEquals =  new GisFeatureDistance();
	gisFeatureDistanceEquals.setFeatureId(featureId);
	GisFeatureDistance gisFeatureDistanceNotEquals =  new GisFeatureDistance(); 
	gisFeatureDistanceNotEquals.setFeatureId(featureId+1);
	Assert.assertEquals("gisFeaturedistance with the same featureid should be equals", gisFeatureDistance, gisFeatureDistanceEquals);
	Assert.assertFalse("gisFeaturedistance without the same featureid should not be equals", gisFeatureDistance.equals(gisFeatureDistanceNotEquals));
   }

    private List<String> inspectGisFeatureDistance(){
	Class<?> clazzParent = GisFeatureDistance.class;
	List<String> introspectedFields = new ArrayList<String>();
	    do {
		int searchMods = 0x0;
		searchMods |= modifierFromString("private");

		Field[] flds = clazzParent.getDeclaredFields();
		for (Field f : flds) {
		    int foundMods = f.getModifiers();
		    if ((foundMods & searchMods) == searchMods
			    && !f.isSynthetic() && f.getType() != List.class
			    && !Modifier.isFinal(foundMods)) {
			introspectedFields.add(f.getName());
		    }
		}
		clazzParent = (Class<?>) clazzParent.getSuperclass();
	    } while (clazzParent != Object.class);
	return introspectedFields;
    }
    
    private static int modifierFromString(String s) {
	int m = 0x0;
	if ("public".equals(s))
	    m |= Modifier.PUBLIC;
	else if ("protected".equals(s))
	    m |= Modifier.PROTECTED;
	else if ("private".equals(s))
	    m |= Modifier.PRIVATE;
	else if ("static".equals(s))
	    m |= Modifier.STATIC;
	else if ("final".equals(s))
	    m |= Modifier.FINAL;
	else if ("transient".equals(s))
	    m |= Modifier.TRANSIENT;
	else if ("volatile".equals(s))
	    m |= Modifier.VOLATILE;
	return m;
    }
    
    @Test
    public void testGetlat(){
	GisFeatureDistance g = new GisFeatureDistance();
	Assert.assertNull(g.getLat());
	
	float latitude = 2F;
	g.setLocation(GisgraphyCommonsTestHelper.createPoint(3F, latitude));
	Assert.assertEquals(latitude,g.getLat().floatValue());
	g.setLat(33D);
	Assert.assertEquals(33D, g.getLat());
    }
    
    @Test
    public void testGetlng(){
	GisFeatureDistance g = new GisFeatureDistance();
	Assert.assertNull(g.getLng());
	
	float longitude = 3F;
	g.setLocation(GisgraphyCommonsTestHelper.createPoint(longitude, 2F));
	Assert.assertEquals(longitude,g.getLng().floatValue());
	g.setLng(33D);
	Assert.assertEquals(33D, g.getLng());
	
    }
    
}
