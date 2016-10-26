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

import java.util.Set;
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
	

	
	public StreetDistanceBuilder withLanes(Integer lanes) {
		streetDistance.lanes = lanes;
	    return this;
	}
	
	public StreetDistanceBuilder withToll(Boolean toll) {
		streetDistance.toll = toll;
	    return this;
	}
	
	public StreetDistanceBuilder withSurface(String surface) {
		streetDistance.surface = surface;
	    return this;
	}
	
	public StreetDistanceBuilder withMaxSpeed(String maxSpeed) {
		streetDistance.maxSpeed = maxSpeed;
	    return this;
	}
	
	public StreetDistanceBuilder withMaxSpeedBackward(String maxSpeedBackward) {
		streetDistance.maxSpeedBackward = maxSpeedBackward;
	    return this;
	}
	
	public StreetDistanceBuilder withAzimuthStart(Integer azimuthStart) {
		streetDistance.azimuthStart = azimuthStart;
	    return this;
	}
	
	public StreetDistanceBuilder withAzimuthEnd(Integer azimuthEnd) {
		streetDistance.azimuthEnd = azimuthEnd;
	    return this;
	}
	
	public StreetDistanceBuilder withFullyQualifiedName(String fullyQualifiedName) {
		streetDistance.fullyQualifiedName = fullyQualifiedName;
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
    
    private String adm1Name;
    
    private String adm2Name;
    
    private String adm3Name;
    
    private String adm4Name;
    
    private String adm5Name;
    
    private String isInZip;
    
    private String fullyQualifiedName;
    
    private Integer lanes;
    
    private Boolean toll;
    
    private String surface;
    
    private String maxSpeed;
    
    private SpeedMode speedMode;
    

	private String maxSpeedBackward;
    
    private Integer azimuthStart;
    
    private Integer azimuthEnd;
    
    private String label;
    
    private String labelPostal;
    
    private Set<String> alternateLabels;
    
   
    
   // private SortedSet<HouseNumberDto> housenumbers;

   

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the labelPostal
	 */
	public String getLabelPostal() {
		return labelPostal;
	}

	/**
	 * @return the alternateLabels
	 */
	public Set<String> getAlternateLabels() {
		return alternateLabels;
	}

	/**
	 * @return the lanes
	 */
	public Integer getLanes() {
		return lanes;
	}

	/**
	 * @return the toll
	 */
	public Boolean getToll() {
		return toll;
	}

	/**
	 * @return the surface
	 */
	public String getSurface() {
		return surface;
	}

	/**
	 * @return the maxSpeed
	 */
	public String getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @return the maxSpeedBackward
	 */
	public String getMaxSpeedBackward() {
		return maxSpeedBackward;
	}

	/**
	 * @return the azimuthStart
	 */
	public Integer getAzimuthStart() {
		return azimuthStart;
	}

	/**
	 * @return the azimuthEnd
	 */
	public Integer getAzimuthEnd() {
		return azimuthEnd;
	}

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
	 * @return the adm1Name
	 */
	public String getAdm1Name() {
		return adm1Name;
	}

	/**
	 * @param adm1Name the adm1Name to set
	 */
	public void setAdm1Name(String adm1Name) {
		this.adm1Name = adm1Name;
	}

	/**
	 * @return the adm2Name
	 */
	public String getAdm2Name() {
		return adm2Name;
	}

	/**
	 * @param adm2Name the adm2Name to set
	 */
	public void setAdm2Name(String adm2Name) {
		this.adm2Name = adm2Name;
	}

	/**
	 * @return the adm3Name
	 */
	public String getAdm3Name() {
		return adm3Name;
	}

	/**
	 * @param adm3Name the adm3Name to set
	 */
	public void setAdm3Name(String adm3Name) {
		this.adm3Name = adm3Name;
	}

	/**
	 * @return the adm4Name
	 */
	public String getAdm4Name() {
		return adm4Name;
	}

	/**
	 * @param adm4Name the adm4Name to set
	 */
	public void setAdm4Name(String adm4Name) {
		this.adm4Name = adm4Name;
	}

	/**
	 * @return the adm5Name
	 */
	public String getAdm5Name() {
		return adm5Name;
	}

	/**
	 * @param adm5Name the adm5Name to set
	 */
	public void setAdm5Name(String adm5Name) {
		this.adm5Name = adm5Name;
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
	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}
	
	 public String getOpenstreetmapUrl(){
	    	return URLUtils.createOpenstreetmapMapUrlForStreet(location);
	 }
	 
	 /**
		 * @return the speedMode
		 */
		public SpeedMode getSpeedMode() {
			return speedMode;
		}

	/*public SortedSet<HouseNumberDto> getHousenumbers() {
		return housenumbers;
	}

	public void setHousenumbers(SortedSet<HouseNumberDto> housenumbers) {
		this.housenumbers = housenumbers;
	}*/

	

}
