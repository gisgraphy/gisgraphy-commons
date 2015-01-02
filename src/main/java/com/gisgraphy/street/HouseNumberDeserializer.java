package com.gisgraphy.street;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gisgraphy.helper.GeolocHelper;
import com.vividsolutions.jts.geom.Point;

public class HouseNumberDeserializer {
	
	 /**
     * The logger
     */
    protected static final Logger logger = LoggerFactory
	    .getLogger(HouseNumberDeserializer.class);
	
	public static final String LAT_LON_SEPARATOR = ",";
	public static final String HOUSE_NUMBERS_SEPARATOR = "  ";
	public static final String HOUSENUMBER_AND_LOCATION_SEPARATOR = ":";
	private static final Pattern ASSOCIATED_HOUSE_NUMBER_REGEXP = Pattern.compile("(\\w+)"+HOUSENUMBER_AND_LOCATION_SEPARATOR+"([0-9\\.]+)"+LAT_LON_SEPARATOR+"([0-9\\.]+)(?:\\s\\s)?");

	
	
	public List<HouseNumberDto> deserializeList(String serializedString){
		List<HouseNumberDto> houseNumbers = new ArrayList<HouseNumberDto>();
		if (serializedString==null || "".equals(serializedString)){
			return houseNumbers;
		}
		Matcher matcher = ASSOCIATED_HOUSE_NUMBER_REGEXP.matcher(serializedString);
		int i = 0;
		while (matcher.find()) {
			HouseNumberDto number = new HouseNumberDto();
			if (matcher.groupCount() != 3) {
				logger.warn("wrong number of fields for housenumber");
				continue;
			}
			String name = matcher.group(1);
			if (name==null|| "".equals(name)){
				continue;
			}
			number.setNumber(name);
			try {
				String lng = matcher.group(2);
				if (lng==null|| "".equals(lng)){
					continue;
				}
				String lat = matcher.group(3);
				if (lat==null|| "".equals(lat)){
					continue;
				}
				Double latAsDouble = Double.parseDouble(lat);
				Double lngAsDouble = Double.parseDouble(lng);
				Point point = GeolocHelper.createPoint(lngAsDouble, latAsDouble);
				number.setLocation(point);
			} catch (Exception e) {
				logger.error("can not parse housenumbers for "+serializedString,e.getMessage());
			}
			houseNumbers.add(number);
			
		}
		return houseNumbers;
	}
	
	public HouseNumberDto deserialize(String serializedString){
		if (serializedString==null || "".equals(serializedString)){
			return null;
		}
		Matcher matcher = ASSOCIATED_HOUSE_NUMBER_REGEXP.matcher(serializedString);
		int i = 0;
		if (matcher.matches()) {
			HouseNumberDto number = new HouseNumberDto();
			if (matcher.groupCount() != 3) {
				logger.warn("wrong number of fields for housenumber");
				return null;
			}
			String name = matcher.group(1);
			if (name==null|| "".equals(name)){
				return null;
			}
			try {
				number.setNumber(name);
				String lat = matcher.group(3);
				if (lat==null|| "".equals(lat)){
					return null;
				}
				String lng = matcher.group(2);
				if (lng==null|| "".equals(lng)){
					return null;
				}
				Double latAsDouble = Double.parseDouble(lat);
				Double lngAsDouble = Double.parseDouble(lng);
				Point point = GeolocHelper.createPoint(lngAsDouble, latAsDouble);
				number.setLocation(point);
			} catch (Exception e) {
				logger.error("can not parse housenumbers for "+serializedString,e.getMessage());
			}
			return number;
			
		}
		return null;
	}

}
