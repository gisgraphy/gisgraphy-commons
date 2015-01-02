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
package com.gisgraphy.domain.valueobject;

import java.util.SortedSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gisgraphy.helper.URLUtils;
import com.gisgraphy.street.HouseNumberDto;
import com.gisgraphy.street.StreetType;
import com.vividsolutions.jts.geom.Point;

/**
 * Value object that represents a StreetOSM with a distance The JAXB node name
 * is {@link Constants#STREETDISTANCE_JAXB_NAME}
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
@XmlRootElement(name = Constants.STREETDISTANCE_JAXB_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
public class StreetDistance {

    public static class StreetDistanceBuilder {

	public static StreetDistanceBuilder streetDistance() {
	    return new StreetDistanceBuilder();
	}

	private final StreetDistance streetDistance;

	private StreetDistanceBuilder() {
	    streetDistance = new StreetDistance();
	}

	public StreetDistance build() {

	    streetDistance.updateFields();
	    return streetDistance;
	}

	public StreetDistanceBuilder withName(String name) {
	    streetDistance.name = name;
	    return this;
	}
	
	public StreetDistanceBuilder withOpenstreetMapId(Long openstreetmapId) {
	    streetDistance.openstreetmapId = openstreetmapId;
	    return this;
	}

	public StreetDistanceBuilder withLocation(Point location) {
	    streetDistance.location = location;
	    return this;
	}

	public StreetDistanceBuilder withDistance(Double distance) {
	    streetDistance.distance = distance;
	    return this;
	}

	public StreetDistanceBuilder withGid(Long gid) {
	    streetDistance.gid = gid;
	    return this;
	}

	public StreetDistanceBuilder withStreetType(StreetType streetType) {
	    streetDistance.streetType = streetType;
	    return this;
	}

	public StreetDistanceBuilder withOneWay(Boolean oneWay) {
	    streetDistance.oneWay = oneWay;
	    return this;
	}

	public StreetDistanceBuilder withLength(Double length) {
	    streetDistance.length = length;
	    return this;
	}

	public StreetDistanceBuilder withCountryCode(String countryCode) {
	    if (countryCode != null) {
		streetDistance.countryCode = countryCode.toUpperCase();
	    }
	    return this;
	}
	
	public StreetDistanceBuilder withIsIn(String isIn) {
		streetDistance.isIn = isIn;
	    return this;
	}
	
	public StreetDistanceBuilder withIsInPlace(String isInPlace) {
		streetDistance.isInPlace = isInPlace;
	    return this;
	}
	
	
	public StreetDistanceBuilder withIsInAdm(String isInAdm) {
		streetDistance.isInAdm = isInAdm;
	    return this;
	}
	
	public StreetDistanceBuilder withIsInZip(String isInZip) {
		streetDistance.isInZip = isInZip;
	    return this;
	}
	
	public StreetDistanceBuilder withFullyQualifiedAddress(String fullyQualifiedAddress) {
		streetDistance.fullyQualifiedAddress = fullyQualifiedAddress;
	    return this;
	}

    }

    @XmlTransient
    protected static final Logger logger = LoggerFactory
	    .getLogger(StreetDistance.class);

    private String name;

    @XmlTransient
    private Point location;

    private Double distance;

    private Long gid;
    
    private Long openstreetmapId;

    private StreetType streetType;

    private Boolean oneWay;

    private String countryCode;

    private Double length;

    private Double lat;

    private Double lng;
    
    private String isIn;
    
    private String isInPlace;
    
    private String isInAdm;
    
    private String isInZip;
    
    private String fullyQualifiedAddress;
    
   // private SortedSet<HouseNumberDto> housenumbers;

   

	/**
     * Default Constructor needed by cglib
     */
    public StreetDistance() {
	super();
    }
    
       /**
     * update the calculated fields (lat,lng,...)
     * 
     */
    public void updateFields() {
	if (this.location != null) {
	    this.lat = location.getY();
	    this.lng = location.getX();
	}
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the distance
     */
    public Double getDistance() {
	return distance;
    }

    /**
     * @return the gid
     */
    public Long getGid() {
	return gid;
    }
    
    public Long getOpenstreetmapId() {
        return openstreetmapId;
    }

    /**
     * @return the streetType
     */
    public StreetType getStreetType() {
	return streetType;
    }

    /**
     * @return the oneWay
     */
    public Boolean getOneWay() {
	return oneWay;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
	return countryCode;
    }

    /**
     * @return the length
     */
    public Double getLength() {
	return length;
    }

    /**
     * @return the lat
     */
    public Double getLat() {
	return lat;
    }

    /**
     * @return the lng
     */
    public Double getLng() {
	return lng;
    }

    /**
     * @return the location
     */
    @XmlTransient
    public Point getLocation() {
	return this.location;
    }
    
    /**
	 * @return the isIn
	 */
	public String getIsIn() {
		return isIn;
	}

	/**
	 * @return the isInPlace
	 */
	public String getIsInPlace() {
		return isInPlace;
	}

	/**
	 * @return the isInAdm
	 */
	public String getIsInAdm() {
		return isInAdm;
	}

	/**
	 * @return the isInZip
	 */
	public String getIsInZip() {
		return isInZip;
	}

	/**
	 * @return the fullyQualifiedAddress
	 */
	public String getFullyQualifiedAddress() {
		return fullyQualifiedAddress;
	}
	
	 public String getOpenstreetmapUrl(){
	    	return URLUtils.createOpenstreetmapMapUrlForStreet(location);
	 }

	/*public SortedSet<HouseNumberDto> getHousenumbers() {
		return housenumbers;
	}

	public void setHousenumbers(SortedSet<HouseNumberDto> housenumbers) {
		this.housenumbers = housenumbers;
	}*/

	

}
