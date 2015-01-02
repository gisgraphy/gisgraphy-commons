package com.gisgraphy.reversegeocoding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import junit.framework.Assert;

import org.junit.Test;

import com.gisgraphy.domain.valueobject.Output;
import com.gisgraphy.geoloc.GeolocQuery;
import com.gisgraphy.helper.GeolocHelper;
import com.gisgraphy.serializer.common.OutputFormat;

public class ReverseGeocodingQueryTest {

	@Test
	public void testConstructorPoint() {
		ReverseGeocodingQuery query = new ReverseGeocodingQuery(GeolocHelper.createPoint(3F, 4F));
		Assert.assertEquals(4D,query.getLatitude());
		Assert.assertEquals(3D,query.getLongitude());
		Assert.assertEquals(Output.DEFAULT_OUTPUT, query.getOutput());
	}
	
	
	@Test
	public void testConstructorPointOutput() {
		Output output = Output.withFormat(OutputFormat.JSON).withIndentation();
		ReverseGeocodingQuery query = new ReverseGeocodingQuery(GeolocHelper.createPoint(3F, 4F),output);
		Assert.assertEquals(4D,query.getLatitude());
		Assert.assertEquals(3D,query.getLongitude());
		Assert.assertEquals(output, query.getOutput());
	}
	
	@Test
	public void WithOutput() {
		Output output = Output.withFormat(OutputFormat.JSON).withIndentation();
		ReverseGeocodingQuery query = new ReverseGeocodingQuery(GeolocHelper.createPoint(3F, 4F));
		Assert.assertEquals(4D,query.getLatitude());
		Assert.assertEquals(3D,query.getLongitude());
		query.withOutput(output);
		Assert.assertEquals(output, query.getOutput());
	}

	
    @Test
    public void testCallbackOk() {
    	ReverseGeocodingQuery query = new ReverseGeocodingQuery(GeolocHelper.createPoint(3F, 4F));
	String callback ="doit";
	query.withCallback(callback);
	assertEquals(callback,query.getCallback() );
    }
    @Test
    public void testCallbackKOBecauseNonAlphanumeric() {
    	ReverseGeocodingQuery query = new ReverseGeocodingQuery(GeolocHelper.createPoint(3F, 4F));
	String callback ="doit(";
	query.withCallback(callback);
	assertNull("callback should be alphanumeric",query.getCallback() );
    }
    
    @Test
    public void testCallbackKOBecauseNnull() {
    	ReverseGeocodingQuery query = new ReverseGeocodingQuery(GeolocHelper.createPoint(3F, 4F));
	query.withCallback(null);
	assertNull("callback should not be null",query.getCallback() );
    }
    

	@Test
	public void setApiKey() {
		ReverseGeocodingQuery query = new ReverseGeocodingQuery(GeolocHelper.createPoint(3F, 4F));
		Assert.assertNull(query.getApikey());
		query.setApikey("31234");
		Assert.assertEquals("31234", query.getApikey());
		
	}

}
