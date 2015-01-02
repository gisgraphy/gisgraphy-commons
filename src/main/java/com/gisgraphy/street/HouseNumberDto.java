package com.gisgraphy.street;

import javax.xml.bind.annotation.XmlTransient;

import com.gisgraphy.fulltext.FulltextResultsDto;
import com.vividsolutions.jts.geom.Point;

/**
 * Java Dto for a solr housenumber fulltext response. it is used by
 * {@link FulltextResultsDto}
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 * 
 */
public class HouseNumberDto {

	@XmlTransient
	private Point location;
	private String number;
	
	public HouseNumberDto() {
		super();
	}
	
	public HouseNumberDto(Point location, String number) {
		super();
		this.location = location;
		this.number = number;
	}
	public Point getLocation() {
		return this.location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	 /**
     * @return Returns the latitude (north-south) from the Location
     *         {@link #getLocation()}.
     * @see #getLongitude()
     * @see #getLocation()
     */
    public Double getLatitude() {
	Double latitude = null;
	if (this.location != null) {
	    latitude = this.location.getY();
	}
	return latitude;
    }
    
    /**
     * @return Returns the longitude (east-west) from the Location
     *         {@link #getLocation()}.
     * @see #getLongitude()
     * @see #getLocation()
     */
    public Double getLongitude() {
	Double longitude = null;
	if (this.location != null) {
	    longitude = this.location.getX();
	}
	return longitude;
    }

    @Override
	public String toString() {
		return String.format("HouseNumberDto [location=%s, number=%s]",
				location, number);
	}

}
