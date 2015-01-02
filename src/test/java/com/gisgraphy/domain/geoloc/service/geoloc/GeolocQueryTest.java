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
package com.gisgraphy.domain.geoloc.service.geoloc;

import static com.gisgraphy.domain.valueobject.Pagination.paginate;
import static com.gisgraphy.test.GisgraphyCommonsTestHelper.createPoint;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.domain.geoloc.entity.TestEntity;
import com.gisgraphy.domain.valueobject.GisgraphyConfig;
import com.gisgraphy.domain.valueobject.Output;
import com.gisgraphy.domain.valueobject.Output.OutputStyle;
import com.gisgraphy.domain.valueobject.Pagination;
import com.gisgraphy.geoloc.GeolocQuery;
import com.gisgraphy.serializer.common.OutputFormat;
import com.vividsolutions.jts.geom.Point;

public class GeolocQueryTest {

   

    /**
     * a simple point to avoid creation of new one for each test
     */
    private static Point GENERIC_POINT = createPoint(3.2F, 2.5F);

    @Test
    public void testGeolocQueryPointRadiusPaginationOutputClassOfQextendsGisFeature() {
	Pagination pagination = paginate().from(2).to(7);
	Output output = Output.withFormat(OutputFormat.JSON).withLanguageCode(
		"FR").withStyle(OutputStyle.FULL).withIndentation();
	GeolocQuery query = new GeolocQuery(GENERIC_POINT, 3D, pagination,
		output, TestEntity.class);
	assertEquals(pagination, query.getPagination());
	assertEquals(output, query.getOutput());
	assertEquals(TestEntity.class, query.getPlaceType());
	assertEquals(GENERIC_POINT, query.getPoint());
	assertTrue(query.isOutputIndented());
	assertEquals(3D, query.getRadius(),0.0000001);
    }

    public void testGeolocQueryPointRadius() {
	Class<?> savedDefaultType = GisgraphyConfig.defaultGeolocSearchPlaceTypeClass;
	try {
	    GisgraphyConfig.defaultGeolocSearchPlaceTypeClass = TestEntity.class;
	    GeolocQuery query = new GeolocQuery(GENERIC_POINT, 3D);
	    assertEquals(Pagination.DEFAULT_PAGINATION, query.getPagination());
	    assertEquals(Output.DEFAULT_OUTPUT, query.getOutput());
	    assertEquals(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass,
		    query.getPlaceType());
	    assertEquals(GENERIC_POINT, query.getPoint());
	    assertEquals(3D, query.getRadius());
	} finally {
	    GisgraphyConfig.defaultGeolocSearchPlaceTypeClass = savedDefaultType;
	}
    }

    public void testGeolocQueryPoint() {
	Class<?> savedDefaultType = GisgraphyConfig.defaultGeolocSearchPlaceTypeClass;
	try {
	    GisgraphyConfig.defaultGeolocSearchPlaceTypeClass = TestEntity.class;
	    GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	    assertEquals(Pagination.DEFAULT_PAGINATION, query.getPagination());
	    assertEquals(Output.DEFAULT_OUTPUT, query.getOutput());
	    assertEquals(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass,
		    query.getPlaceType());
	    assertEquals(GENERIC_POINT, query.getPoint());
	    assertEquals(GeolocQuery.DEFAULT_RADIUS, query.getRadius());
	} finally {
	    GisgraphyConfig.defaultGeolocSearchPlaceTypeClass = savedDefaultType;
	}
    }

   

    @Test
    public void testGeolocQueryWithNullPointThrows() {
	Pagination pagination = paginate().from(2).to(7);
	Output output = Output.withFormat(OutputFormat.JSON).withLanguageCode(
		"FR").withStyle(OutputStyle.FULL);
	try {
	    new GeolocQuery(null, 3D, pagination, output, TestEntity.class);
	    fail("Query with null point should throws");
	} catch (IllegalArgumentException e) {

	}

	try {
	    new GeolocQuery(null, 5D);
	    fail("Query with null point should throws");
	} catch (RuntimeException e) {

	}
    }

    @Test
    public void testGeolocQueryWithWrongRadiusShouldBeSetWithDefault() {
	Pagination pagination = paginate().from(2).to(7);
	Output output = Output.withFormat(OutputFormat.JSON).withLanguageCode(
		"FR").withStyle(OutputStyle.FULL);
	// with negaive value
	GeolocQuery query = new GeolocQuery(GENERIC_POINT, -1, pagination,
		output, TestEntity.class);
	assertEquals(GeolocQuery.DEFAULT_RADIUS, query.getRadius(),0.0000001);

	// with 0
	query = new GeolocQuery(GENERIC_POINT, 0, pagination, output, TestEntity.class);
	assertEquals(GeolocQuery.DEFAULT_RADIUS, query.getRadius(),0.0000001);

    }

    @Test
    public void testEquals(){
	Pagination pagination = paginate().from(2).to(7);
	Output output = Output.withFormat(OutputFormat.JSON).withLanguageCode(
		"FR").withStyle(OutputStyle.FULL);
	GeolocQuery query = new GeolocQuery(createPoint(3.2F, 2.5F), 5, pagination,
		output, TestEntity.class);
	GeolocQuery queryWithDifferentLongitude = new GeolocQuery(createPoint(3.3F, 2.5F), 5, pagination,
		output, TestEntity.class);
	assertFalse("query should not be considered equals if longitude is not the same",query.equals(queryWithDifferentLongitude));
	
	GeolocQuery queryWithDifferentLat = new GeolocQuery(createPoint(3.2F, 2.6F), 5, pagination,
		output, TestEntity.class);
	assertFalse("query should not be considered equals if latitude is not the same",query.equals(queryWithDifferentLat));
	
	GeolocQuery queryWithDifferentMunicipalityFilter = new GeolocQuery(createPoint(3.2F, 2.6F), 5, pagination,
			output, TestEntity.class).withMunicipalityFilter(true);
		assertFalse("query should not be considered equals if municipalityfilter is not the same",query.equals(queryWithDifferentMunicipalityFilter));
	
	GeolocQuery queryEquals = new GeolocQuery(createPoint(3.2F, 2.5F), 5, pagination,
		output, TestEntity.class);
	assertEquals(query, queryEquals);
	
	
    }
    
    
    @Test
    public void testWithPaginationShouldBeSetToDefaultPaginationIfNull() {
	assertEquals(Pagination.DEFAULT_PAGINATION, new GeolocQuery(
		GENERIC_POINT).withPagination(null).getPagination());
    }

    @Test
    public void testWithPaginationShouldSetThePagination() {
	assertEquals(5, new GeolocQuery(GENERIC_POINT).withPagination(
		paginate().from(5).to(23)).getPagination().getFrom());
	assertEquals(23, new GeolocQuery(GENERIC_POINT).withPagination(
		paginate().from(5).to(23)).getPagination().getTo());
    }

    @Test
    public void testWithOutputShouldBeSetToDefaultOutputIfNull() {
	assertEquals(Output.DEFAULT_OUTPUT, new GeolocQuery(GENERIC_POINT)
		.withOutput(null).getOutput());
    }

    @Test
    public void testWithOutputShouldSetTheOutput() {
	GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	Pagination pagination = paginate().from(2).to(7);
	query.withPagination(pagination);
	assertEquals(pagination, query.getPagination());
    }

    @Test
    public void testWithPlaceTypeShouldBeSetToNullIfNull() {
	assertNull(new GeolocQuery(GENERIC_POINT).withPlaceType(null)
		.getPlaceType());
    }

    @Test
    public void testWithPlaceTypeShouldSetTheplaceType() {
	GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	query.withPlaceType(TestEntity.class);
	assertEquals(TestEntity.class, query.getPlaceType());
    }

    @Test
    public void testPlaceTypeShouldHaveADefaultValue() {
	Class<?> savedDefaultType = GisgraphyConfig.defaultGeolocSearchPlaceTypeClass;
	try {
	    GisgraphyConfig.defaultGeolocSearchPlaceTypeClass = TestEntity.class;
	    GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	    assertEquals(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass,
		    query.getPlaceType());
	    query = new GeolocQuery(GENERIC_POINT, 10);
	    assertEquals(GisgraphyConfig.defaultGeolocSearchPlaceTypeClass,
		    query.getPlaceType());
	} finally {
	    GisgraphyConfig.defaultGeolocSearchPlaceTypeClass = savedDefaultType;
	}
    }
    
    @Test
    public void testDistanceField(){
	GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	Assert.assertTrue("by default distanceField should be true", query.hasDistanceField());
	query.withDistanceField(false);
	assertFalse("distance field setter is broken", query.hasDistanceField());
    }
    
    @Test
    public void testMunicipalityFilter(){
	GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	Assert.assertFalse("by default municipality should be false", query.hasMunicipalityFilter());
	query.withMunicipalityFilter(true);
	assertTrue("distance field setter is broken", query.hasMunicipalityFilter());
    }
    
    @Test
    public void testCallbackOk() {
	GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	String callback ="doit";
	query.withCallback(callback);
	assertEquals(callback,query.getCallback() );
    }
    @Test
    public void testCallbackKOBecauseNonAlphanumeric() {
	GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	String callback ="doit(";
	query.withCallback(callback);
	assertNull("callback should be alphanumeric",query.getCallback() );
    }
    
    @Test
    public void testCallbackKOBecauseNnull() {
	GeolocQuery query = new GeolocQuery(GENERIC_POINT);
	query.withCallback(null);
	assertNull("callback should not be null",query.getCallback() );
    }


}
