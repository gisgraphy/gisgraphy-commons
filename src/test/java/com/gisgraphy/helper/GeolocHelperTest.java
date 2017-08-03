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
package com.gisgraphy.helper;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.domain.placetype.City;
import com.gisgraphy.domain.placetype.GisFeature;
import com.gisgraphy.domain.valueobject.SRID;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class GeolocHelperTest {
	

    @Test
    public void createPointWithFloat() {
	Assert.assertNotNull(GeolocHelper.createPoint(54.2F, -46.5F));
    }

    @Test
    public void createPointWithWrongLatitudeShouldThrowsAnIllegalArgumentException() {
	try {
	    GeolocHelper.createPoint(54.2F, -95F);
	    Assert.fail("createPoint should only accept -90 < lattitude < 90");
	} catch (IllegalArgumentException e) {
	    // ok
	}

	try {
	    GeolocHelper.createPoint(54.2F, +95F);
	    Assert.fail("createPoint should only accept -90 < lattitude < 90");
	} catch (IllegalArgumentException e) {
	    // ok
	}
    }

    @Test
    public void createPointWithWrongLongitudeShouldThrowsAnIllegalArgumentException() {
	try {
	    GeolocHelper.createPoint(-180.5F, 35F);
	    Assert.fail("createPoint should only accept -180 < longitude < 180");
	} catch (IllegalArgumentException e) {
	    // ok
	}

	try {
	    GeolocHelper.createPoint(180.5F, +95F);
	    Assert.fail("createPoint should only accept -180 < longitude < 180");
	} catch (IllegalArgumentException e) {
	    // ok
	}
    }

    @Test
    public void distanceShouldReturnCorrectDistance() {
	Point point1 = GeolocHelper.createPoint(48.867F, 2.333F);

	Point point2 = GeolocHelper.createPoint(49.017F, 2.467F);

	Assert.assertEquals(Math.round(GeolocHelper.distance(point1, point2)), Math
		.round(GeolocHelper.distance(point2, point1)));
	Assert.assertEquals(22313, Math.round(GeolocHelper.distance(point1, point2)));
	
	
	point1 = GeolocHelper.createPoint(113.636667,-46.071667); //-46.071667 113.636667
	point2 = GeolocHelper.createPoint(-65.571667, 47.003333); // 47.003333 -65.571667
	//GeolocHelper.distance(point1, point2);
	Assert.assertEquals(19857619,new Double(GeolocHelper.distance(point1, point2)).intValue());
    }

    
    @Test
    public void distance2ShouldReturnCorrectDistance() {
	Point point1 = GeolocHelper.createPoint(48.867F, 2.333F);

	Point point2 = GeolocHelper.createPoint(49.017F, 2.467F);

	Assert.assertEquals(Math.round(GeolocHelper.distanceByMathematic(point1, point2)), Math
		.round(GeolocHelper.distanceByMathematic(point2, point1)));
	Assert.assertEquals(22312, Math.round(GeolocHelper.distanceByMathematic(point1, point2)));
	
	
	point1 = GeolocHelper.createPoint(113.636667,-46.071667); //-46.071667 113.636667
	point2 = GeolocHelper.createPoint(-65.571667, 47.003333); // 47.003333 -65.571667
	Assert.assertEquals(19857619,new Double(GeolocHelper.distanceByMathematic(point1, point2)).intValue());
    }


    @Test
    public void parseInternationalDoubleShouldAcceptPointOrCommaAsDecimalSeparator() {
	try {
	    Assert.assertEquals(
		    "parseInternationalDouble should accept point as a decimal separator ",
		    3.2F, GeolocHelper.parseInternationalDouble("3.2"),0.1);
	    Assert.assertEquals(
		    "parseInternationalDouble should accept comma as a decimal separator ",
		    3.2F, GeolocHelper.parseInternationalDouble("3,2"),0.1);
	    Assert.assertEquals(
		    "parseInternationalDouble should accept numeric value without comma or point ",
		    3.0F, GeolocHelper.parseInternationalDouble("3"),0.1);
	    Assert.assertEquals(
		    "parseInternationalDouble should accept numeric value that ends with point ",
		    3.0F, GeolocHelper.parseInternationalDouble("3."),0.1);
	    Assert.assertEquals(
		    "parseInternationalDouble should accept numeric value that ends with comma ",
		    3.0F, GeolocHelper.parseInternationalDouble("3,"),0.1);
	    Assert.assertEquals(
			    "parseInternationalDouble should accept numeric value that are not trimmed ",
			    3.0F, GeolocHelper.parseInternationalDouble(" 3,1"),0.1);
	} catch (ParseException e) {
	    Assert.fail();
	}
    }
    
    @Test
    public void createMultiLineString(){
	//test with negative and Float values
	String[] wktLineStrings={"LINESTRING (0 0, 10 10, 20 20)","LINESTRING (30 30, 40.5 40, 50 -50)"};
	MultiLineString multiLineString = GeolocHelper.createMultiLineString(wktLineStrings);
	Assert.assertNotNull(multiLineString);
	Assert.assertEquals("wrong SRID",SRID.WGS84_SRID.getSRID(), multiLineString.getSRID());
	Assert.assertTrue("createMultiLineString is not of MultiLineString type", multiLineString instanceof MultiLineString);
    }

    @Test
    public void createMultiLineStringShouldThrowIllegalArgumentExceptionIfStringsAreNotCorrect(){
	String[] wktLineStrings={"LINESTRING (0 0, 10 10, 20 20)","LINESTRING (30 30, 40 40, 50 )"};
	
	try {
	    GeolocHelper.createMultiLineString(wktLineStrings);
	    Assert.fail("createMultiLineString should throw illegalArgumentException if string are not correct");
	} catch (Exception e) {
	   //ok
	}
	
    }
    

    @Test
    public void createLineStringShouldThrowIllegalArgumentExceptionIfStringIsNotCorrect(){
	String wktLineString="LINESTRING (30 30, 40 40, 50 )";
	
	try {
	    GeolocHelper.createLineString(wktLineString);
	    Assert.fail("createLineString should throw illegalArgumentException if string is not correct");
	} catch (Exception e) {
	   //ok
	}
	
    }
    
    @Test
    public void createLineString(){
	//test with negative and Float values
	String wktLineString="LINESTRING (0 0, 10 10, 20 20)";
	LineString lineString = GeolocHelper.createLineString(wktLineString);
	Assert.assertNotNull(lineString);
	Assert.assertEquals("wrong SRID",SRID.WGS84_SRID.getSRID(), lineString.getSRID());
    }
    
    @Test
    public void createPolygonBoxShouldNotAcceptWrongParameters(){
	
	
	try {
	    GeolocHelper.createPolygonBox(4F, 3F, 0F);
	    Assert.fail("distance can not be 0");
	} catch (IllegalArgumentException e) {
	    //ok
	}
	
	try {
	    GeolocHelper.createPolygonBox(4F, 3F, -1F);
	    Assert.fail("distance can not be <0");
	} catch (IllegalArgumentException e) {
	    //ok
	}
	
    }
    
    @Test
    public void createPolygonBox(){
	Float distance = 5F;
	    Polygon polygon = GeolocHelper.createPolygonBox(10F, 30F, distance);
	    Assert.assertEquals(SRID.WGS84_SRID.getSRID(), polygon.getSRID());
	    Polygon polygon2 = GeolocHelper.createPolygonBox(1F, 13F, distance);
	    Assert.assertEquals("Area of polygon calculate for the same distance should be equals",polygon.getArea(), polygon2.getArea(),0.01);
	    
	    
	Coordinate[] coordinates = polygon.getCoordinates();
	Assert.assertEquals(5,coordinates.length);
	Point pointBottomLeft = GeolocHelper.createPoint(Double.valueOf(coordinates[0].x).floatValue(), Double.valueOf(coordinates[0].y).floatValue());
	Point pointBottomRight = GeolocHelper.createPoint(Double.valueOf(coordinates[1].x).floatValue(), Double.valueOf(coordinates[1].y).floatValue());
	Point pointTopRight = GeolocHelper.createPoint(Double.valueOf(coordinates[2].x).floatValue(), Double.valueOf(coordinates[2].y).floatValue());
	Point poitnTopLeft = GeolocHelper.createPoint(Double.valueOf(coordinates[3].x).floatValue(), Double.valueOf(coordinates[3].y).floatValue());
		
		
	Assert.assertEquals("The first point should be the same as the last one",coordinates[0].x, coordinates[4].x,0.01);
	Assert.assertEquals("The first point should be the same as the last one",coordinates[0].y, coordinates[4].y,0.01);
	Assert.assertEquals(2*distance,GeolocHelper.distance(pointBottomLeft, pointBottomRight),1);
	Assert.assertEquals(2*distance,GeolocHelper.distance(pointBottomRight, pointTopRight),1);
	Assert.assertEquals(2*distance,GeolocHelper.distance(poitnTopLeft, pointBottomLeft),1);
	
    }
    
    @Test
    public void convertFromHEXEWKBToGeometryForPoint(){
	Point point = (Point) GeolocHelper.convertFromHEXEWKBToGeometry("010100000006C82291A0521E4054CC39B16BC64740");
	Coordinate coordinate = point.getCoordinate();
	Assert.assertEquals("invalid long",7.580690639255414,coordinate.x,0.1);
	Assert.assertEquals("invalid lat",47.5,coordinate.y,0.1);
	Assert.assertEquals("invalid SRID",SRID.WGS84_SRID.getSRID() ,point.getSRID());
   }
    
    @Test
    public void convertFromHEXEWKBToGeometryForMultilineString(){
	MultiLineString line = (MultiLineString) GeolocHelper.convertFromHEXEWKBToGeometry("010500000001000000010200000005000000591EFF603B531E40F88667AE78C64740446ADAC534531E40348BAB2578C647405BB164332C531E407033CB5477C64740754A51781A521E403A56CE8360C64740CD63833B06521E409A081B9E5EC64740");
	Assert.assertEquals("invalid length",0.001422,line.getLength(),0.001);
	Assert.assertEquals("invalid SRID",SRID.WGS84_SRID.getSRID() ,line.getSRID());
   }
    
    @Test
    public void midPoint(){
    	 Point point1 = GeolocHelper.createPoint(1f, 2F);
    	Point point2 = GeolocHelper.createPoint(8F, 9F);
    	Point result;
    	//point 1 < point 2 hn1 < hn2
    	result = GeolocHelper.interpolatedPoint(point1, point2, 101, 108,104);
    	Assert.assertEquals(4, result.getX(),0.0001);
    	Assert.assertEquals(5, result.getY(),0.0001);

    	//point 1 < point 2 hn1 > hn2
    	 result = GeolocHelper.interpolatedPoint(point1, point2, 108, 101,104);
    	 Assert.assertEquals(5, result.getX(),0.0001);
    	 Assert.assertEquals(6, result.getY(),0.0001);
    	 
    	 //point1  > point2 hn1 >hn2
    	 result = GeolocHelper.interpolatedPoint(point2, point1, 108, 101,104);
    	 Assert.assertEquals(4, result.getX(),0.0001);
    	 Assert.assertEquals(5, result.getY(),0.0001);
    	 
    	 
    	 //point1  > point2 hn1 <hn2
    	 result = GeolocHelper.interpolatedPoint(point2, point1, 101, 108,104);
    	 Assert.assertEquals(5, result.getX(),0.0001);
    	 Assert.assertEquals(6, result.getY(),0.0001);
    	 
    	 //out of range <
    	//point 1 < point 2 hn1 < hn2
     	result = GeolocHelper.interpolatedPoint(point1, point2, 101, 108,100);
     	Assert.assertEquals(1, result.getX(),0.0001);
     	Assert.assertEquals(2, result.getY(),0.0001);

     	//point 1 < point 2 hn1 > hn2
     	 result = GeolocHelper.interpolatedPoint(point1, point2, 108, 101,100);
     	 Assert.assertEquals(8, result.getX(),0.0001);
     	 Assert.assertEquals(9, result.getY(),0.0001);
     	 
     	 //point1  > point2 hn1 >hn2
     	 result = GeolocHelper.interpolatedPoint(point2, point1, 108, 101,100);
     	 Assert.assertEquals(1, result.getX(),0.0001);
     	 Assert.assertEquals(2, result.getY(),0.0001);
     	 
     	 
     	 //point1  > point2 hn1 <hn2
     	 result = GeolocHelper.interpolatedPoint(point2, point1, 101, 108,100);
     	 Assert.assertEquals(8, result.getX(),0.0001);
     	 Assert.assertEquals(9, result.getY(),0.0001);
     	 
     	 //out of range >
     	//point 1 < point 2 hn1 < hn2
      	result = GeolocHelper.interpolatedPoint(point1, point2, 101, 108,110);
      	Assert.assertEquals(8, result.getX(),0.0001);
      	Assert.assertEquals(9, result.getY(),0.0001);

      	//point 1 < point 2 hn1 > hn2
      	 result = GeolocHelper.interpolatedPoint(point1, point2, 108, 101,110);
      	 Assert.assertEquals(1, result.getX(),0.0001);
      	 Assert.assertEquals(2, result.getY(),0.0001);
      	 
      	 //point1  > point2 hn1 >hn2
      	 result = GeolocHelper.interpolatedPoint(point2, point1, 108, 101,110);
      	 Assert.assertEquals(8, result.getX(),0.0001);
      	 Assert.assertEquals(9, result.getY(),0.0001);
      	 
      	 
      	 //point1  > point2 hn1 <hn2
      	 result = GeolocHelper.interpolatedPoint(point2, point1, 101, 108,110);
      	 Assert.assertEquals(1, result.getX(),0.0001);
      	 Assert.assertEquals(2, result.getY(),0.0001);
    	   }

    
}
