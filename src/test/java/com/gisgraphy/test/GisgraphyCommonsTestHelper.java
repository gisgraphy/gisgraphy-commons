package com.gisgraphy.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import com.gisgraphy.domain.valueobject.GisFeatureDistance;
import com.gisgraphy.domain.valueobject.SRID;
import com.gisgraphy.domain.valueobject.StreetDistance;
import com.gisgraphy.domain.valueobject.StreetDistance.StreetDistanceBuilder;
import com.gisgraphy.domain.valueobject.StreetSearchResultsDto;
import com.gisgraphy.geoloc.GeolocResultsDto;
import com.gisgraphy.street.StreetType;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

@Ignore
public class GisgraphyCommonsTestHelper {
    

    public static StreetDistance createStreetDistance() {
	return StreetDistanceBuilder.streetDistance().withName("streetName").withCountryCode("FR").withGid(123L).withLength(3.6D).withOneWay(true)
	.withStreetType(StreetType.MOTORWAY).withLocation(createPoint(25.2F, 54.5F)).withDistance(43.5D).withCountryCode("fr").build();
    }
    
    public static StreetSearchResultsDto createStreetSearchResultsDto() {
	List<StreetDistance> list = new ArrayList<StreetDistance>();
	list.add(createStreetDistance());
	return new StreetSearchResultsDto(list,1L,"query");
    }
    
    public static GisFeatureDistance createFullFilledGisFeatureDistanceWithConstructor() {
	GisFeatureDistance gisFeatureDistance = new GisFeatureDistance();
	gisFeatureDistance.setAdm1Code("A1");
	gisFeatureDistance.setAdm2Code("B2");
	gisFeatureDistance.setAdm3Code("C3");
	gisFeatureDistance.setAdm4Code("D4");

	gisFeatureDistance.setAdm1Name("adm1 name");
	gisFeatureDistance.setAdm2Name("adm2 name");
	gisFeatureDistance.setAdm3Name("adm3 name");
	gisFeatureDistance.setAdm4Name("adm4 name");

	gisFeatureDistance.setAsciiName("ascii");
	gisFeatureDistance.setCountryCode("FR");
	gisFeatureDistance.setElevation(3);
	gisFeatureDistance.setFeatureClass("P");
	gisFeatureDistance.setFeatureCode("PPL");
	gisFeatureDistance.setFeatureId(1002360L);
	gisFeatureDistance.setGtopo30(30);
	gisFeatureDistance.setLocation(GisgraphyCommonsTestHelper.createPoint(2.3F, 4.5F));
	gisFeatureDistance.setLng(2.3D);
	gisFeatureDistance.setLat(4.5D);
	gisFeatureDistance.setName("a name");
	gisFeatureDistance.setPopulation(1000000);
	gisFeatureDistance.setTimezone("gmt+1");
	gisFeatureDistance.addZipCode("75000");
	gisFeatureDistance.setPlaceType("GisFeature");
	gisFeatureDistance.setDistance(3.6D);
	gisFeatureDistance.setGoogle_map_url("google_map_url");
	gisFeatureDistance.setYahoo_map_url("yahoo_map_url");
	gisFeatureDistance.setCountry_flag_url("country_flag_url");
	return gisFeatureDistance;

    }
    
    public static GeolocResultsDto createGeolocResultsDto(Long Time) {
	GisFeatureDistance gisFeatureDistance = createFullFilledGisFeatureDistanceWithConstructor();
	List<GisFeatureDistance> list = new ArrayList<GisFeatureDistance>();
	list.add(gisFeatureDistance);
	return new GeolocResultsDto(list, 300L);
    }
    
    public static GisFeatureDistance createFullFilledGisFeatureDistance() {
	GisFeatureDistance gisFeatureDistance = new GisFeatureDistance();
	gisFeatureDistance.setAdm1Code("A1");
	gisFeatureDistance.setAdm2Code("B2");
		gisFeatureDistance.setAdm3Code("C3");
		gisFeatureDistance.setAdm4Code("D4");

		gisFeatureDistance.setAdm1Name("adm1 name");
		gisFeatureDistance.setAdm2Name("adm2 name");
		gisFeatureDistance.setAdm3Name("adm3 name");
		gisFeatureDistance.setAdm4Name("adm4 name");

		gisFeatureDistance.setAsciiName("ascii");
		gisFeatureDistance.setCountryCode("FR");
		gisFeatureDistance.setElevation(3);
		gisFeatureDistance.setFeatureClass("P");
		gisFeatureDistance.setFeatureCode("PPL");
		gisFeatureDistance.setFeatureId(1000L);
		gisFeatureDistance.setGtopo30(30);
		gisFeatureDistance.setLocation(createPoint(2F, 4F));
		gisFeatureDistance.setLat(4D);
		gisFeatureDistance.setLng(2D);
		gisFeatureDistance.setName("a name");
		gisFeatureDistance.setPopulation(1000000);
		gisFeatureDistance.setPlaceType("GisFeature");
		gisFeatureDistance.setDistance(3.6D);
		gisFeatureDistance.setTimezone("gmt+1");
		gisFeatureDistance.setGoogle_map_url("google_map_url");
		gisFeatureDistance.setYahoo_map_url("yahoo_map_url");
		gisFeatureDistance.setCountry_flag_url("country_flag_url");
	return gisFeatureDistance;
    }

    public static GisFeatureDistance createFullFilledGisFeatureDistanceForCity() {
	GisFeatureDistance gisFeatureDistance = new GisFeatureDistance();
	gisFeatureDistance.setAdm1Code("A1");
	gisFeatureDistance.setAdm2Code("B2");
	gisFeatureDistance.setAdm3Code("C3");
	gisFeatureDistance.setAdm4Code("D4");

	gisFeatureDistance.setAdm1Name("adm1 name");
	gisFeatureDistance.setAdm2Name("adm2 name");
	gisFeatureDistance.setAdm3Name("adm3 name");
	gisFeatureDistance.setAdm4Name("adm4 name");

	gisFeatureDistance.setAsciiName("ascii");
	gisFeatureDistance.setCountryCode("FR");
	gisFeatureDistance.setElevation(3);
	gisFeatureDistance.setFeatureClass("P");
	gisFeatureDistance.setFeatureCode("PPL");
	gisFeatureDistance.setFeatureId(1000L);
	gisFeatureDistance.setGtopo30(30);
	gisFeatureDistance.setLocation(createPoint(2F, 4F));
	gisFeatureDistance.setName("a name");
	gisFeatureDistance.setPopulation(1000000);
	gisFeatureDistance.setTimezone("gmt+1");
	gisFeatureDistance.addZipCode("3456");
	gisFeatureDistance.addZipCode("3457");
	gisFeatureDistance.setDistance(3.6D);
	gisFeatureDistance.setPlaceType("city");
	gisFeatureDistance.setLng(2.3D);
	gisFeatureDistance.setLat(4.5D);
	return gisFeatureDistance;

    }
    
    public static GisFeatureDistance createFullFilledGisFeatureDistanceForAdm() {
	 GisFeatureDistance gisFeatureDistance =  new GisFeatureDistance();
	gisFeatureDistance.setAdm1Code("A1");
	gisFeatureDistance.setAdm2Code("B2");
	gisFeatureDistance.setAdm3Code("C3");
	gisFeatureDistance.setAdm4Code("D4");

	gisFeatureDistance.setAdm1Name("adm1 name");
	gisFeatureDistance.setAdm2Name("adm2 name");
	gisFeatureDistance.setAdm3Name("adm3 name");
	gisFeatureDistance.setAdm4Name("adm4 name");

	gisFeatureDistance.setAsciiName("ascii");
	gisFeatureDistance.setCountryCode("FR");
	gisFeatureDistance.setElevation(3);
	gisFeatureDistance.setFeatureClass("P");
	gisFeatureDistance.setFeatureCode("PPL");
	gisFeatureDistance.setFeatureId(1000L);
	gisFeatureDistance.setGtopo30(30);
	gisFeatureDistance.setLocation(createPoint(2F, 4F));
	gisFeatureDistance.setName("a name");
	gisFeatureDistance.setPopulation(1000000);
	gisFeatureDistance.setTimezone("gmt+1");
	gisFeatureDistance.setDistance(3D);
	gisFeatureDistance.setLng(2.3D);
	gisFeatureDistance.setLat(4.5D);
	return gisFeatureDistance;

    }
    
   
    public static Point createPoint(Float longitude, Float latitude) {
	if (longitude < -180 || longitude > 180) {
	    throw new IllegalArgumentException(
		    "Longitude should be between -180 and 180");
	}
	if (latitude < -90 || latitude > 90) {
	    throw new IllegalArgumentException(
		    "latitude should be between -90 and 90");
	}
	GeometryFactory factory = new GeometryFactory(new PrecisionModel(
		PrecisionModel.FLOATING), SRID.WGS84_SRID.getSRID());
	Point point = (Point) factory.createPoint(new Coordinate(longitude,
		latitude));
	return point;
    }
    
}
