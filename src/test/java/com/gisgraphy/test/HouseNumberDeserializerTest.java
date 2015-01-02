package com.gisgraphy.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.street.HouseNumberDto;
import com.gisgraphy.street.HouseNumberDeserializer;

public class HouseNumberDeserializerTest {

	
	@Test
	public void deserializeList(){
		HouseNumberDeserializer serializer = new HouseNumberDeserializer();
		List<HouseNumberDto> housenumbers = serializer.deserializeList("1:9.6,10.3  2:9.7,10.4");
		Assert.assertEquals(2, housenumbers.size());
		HouseNumberDto houseNumber1 = housenumbers.get(0);
		Assert.assertEquals("1", houseNumber1.getNumber());
		Assert.assertTrue(houseNumber1.getLatitude().toString(),houseNumber1.getLongitude().toString().startsWith("9.6"));
		Assert.assertTrue(houseNumber1.getLongitude().toString(), houseNumber1.getLatitude().toString().startsWith("10.3"));
		HouseNumberDto houseNumber2 = housenumbers.get(1);
		Assert.assertEquals("2", houseNumber2.getNumber());
		Assert.assertTrue(houseNumber2.getLatitude().toString(),houseNumber2.getLongitude().toString().startsWith("9.7"));
		Assert.assertTrue(houseNumber2.getLongitude().toString(), houseNumber2.getLatitude().toString().startsWith("10.4"));
	}
	
	@Test
	public void deserializeListWithNullOrEmpty(){
		HouseNumberDeserializer serializer = new HouseNumberDeserializer();
		Assert.assertNotNull(serializer.deserializeList(""));
		Assert.assertEquals(0,serializer.deserializeList("").size());
		
		Assert.assertNotNull(serializer.deserializeList(null));
		Assert.assertEquals(0,serializer.deserializeList(null).size());
	}
	
	@Test
	public void deserialize(){
		HouseNumberDeserializer serializer = new HouseNumberDeserializer();
		HouseNumberDto houseNumber1 = serializer.deserialize("1:9.6,10.3");
		Assert.assertEquals("1", houseNumber1.getNumber());
		Assert.assertTrue(houseNumber1.getLongitude().toString().startsWith("9.6"));
		Assert.assertTrue( houseNumber1.getLatitude().toString().startsWith("10.3"));
		
		HouseNumberDto houseNumberError = serializer.deserialize("1:10.3");
		Assert.assertNull(houseNumberError);
		
		HouseNumberDto houseNumber2 = serializer.deserialize("2:9.7,10.4");
		Assert.assertEquals("2", houseNumber2.getNumber());
		Assert.assertTrue(houseNumber2.getLongitude().toString().startsWith("9.7"));
		Assert.assertTrue( houseNumber2.getLatitude().toString().startsWith("10.4"));
	}
	
	@Test
	public void deserializeWithNullOrEmpty(){
		HouseNumberDeserializer serializer = new HouseNumberDeserializer();
		Assert.assertNull(serializer.deserialize(""));
		
		Assert.assertNull(serializer.deserialize(null));
	}

}
