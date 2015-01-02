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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gisgraphy.street.StreetType;
import com.vividsolutions.jts.geom.Point;

/**
 * Value object that represents a gisFeature with a distance The JAXB node name
 * is {@link Constants#GISFEATUREDISTANCE_JAXB_NAME}
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
@XmlRootElement(name = GisFeatureDistance.GISFEATUREDISTANCE_JAXB_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
public class GisFeatureDistance {
    
    /**
     * The node name for {@link GisFeatureDistance} node in JAXB
     */
    public final static String GISFEATUREDISTANCE_JAXB_NAME = "result";
    
    protected static final Logger logger = LoggerFactory
    .getLogger(GisFeatureDistance.class);


    @XmlTransient
    private Long id;

    @XmlTransient
    private Point location;

    private Double distance;

    private String name;

    private String adm1Code;

    private String adm2Code;

    private String adm3Code;

    private String adm4Code;
    
    private String adm5Code;

    private String adm1Name;

    private String adm2Name;

    private String adm3Name;

    private String adm4Name;
    
    private String adm5Name;

    private String asciiName;

    private String countryCode;

    private Integer elevation;

    private String featureClass;

    private String featureCode;

    private Long featureId;

    private Integer gtopo30;

    private Integer population;

    private String timezone;

    private Double lat;

    private Double lng;

    private String placeType;
    
    private Boolean oneWay;

    private StreetType streetType;
    
    private Long openstreetmapId;

    private Double length;
   
    @XmlElementWrapper(name="zipCodes")
    @XmlElements({
    @XmlElement(name="zipCode") }
    )
    private Set<String> zipCodes;

    private String google_map_url;

    private String yahoo_map_url;
    
    private String openstreetmap_map_url;

    private String country_flag_url;
    
    private Integer level;
    
    private Double area;

    private String tld;

    private String capitalName;

    private String continent;

    private String postalCodeRegex;
    
    private String currencyCode;

    private String currencyName;

    private String equivalentFipsCode;

    private String fipsCode;

    private String iso3166Alpha2Code;

    private String iso3166Alpha3Code;

    private Integer iso3166NumericCode;
    
    private String phonePrefix;

    private String postalCodeMask;
    
    private String isIn;
    
    private String isInPlace;
    
    private String isInAdm;
    
    private String isInZip;
    
    private String amenity;
    
	private String fullyQualifiedAddress;

    public GisFeatureDistance() {
	super();
    }

 

    /**
     * @return The distance
     */
    public Double getDistance() {
	return distance;
    }

   
    /**
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * @return the location
     */
    @XmlTransient
    public Point getLocation() {
	return this.location;
    }

    /**
     * @return the adm1Code
     */
    public String getAdm1Code() {
	return this.adm1Code;
    }

    /**
     * @return the adm2Code
     */
    public String getAdm2Code() {
	return this.adm2Code;
    }

    /**
     * @return the adm3Code
     */
    public String getAdm3Code() {
	return this.adm3Code;
    }

    /**
     * @return the adm4Code
     */
    public String getAdm4Code() {
	return this.adm4Code;
    }

    /**
     * @return the adm1Name
     */
    public String getAdm1Name() {
	return this.adm1Name;
    }

    /**
     * @return the adm2Name
     */
    public String getAdm2Name() {
	return this.adm2Name;
    }

    /**
     * @return the adm3Name
     */
    public String getAdm3Name() {
	return this.adm3Name;
    }

    /**
     * @return the adm4Name
     */
    public String getAdm4Name() {
	return this.adm4Name;
    }

    /**
     * @return the asciiName
     */
    public String getAsciiName() {
	return this.asciiName;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
	return this.countryCode;
    }

    /**
     * @return the elevation
     */
    public Integer getElevation() {
	return this.elevation;
    }

    /**
     * @return the featureClass
     */
    public String getFeatureClass() {
	return this.featureClass;
    }

    /**
     * @return the featureCode
     */
    public String getFeatureCode() {
	return this.featureCode;
    }

    /**
     * @return the featureId
     */
    public Long getFeatureId() {
	return this.featureId;
    }

    /**
     * @return the gtopo30
     */
    public Integer getGtopo30() {
	return this.gtopo30;
    }

    /**
     * @return the population
     */
    public Integer getPopulation() {
	return this.population;
    }

    /**
     * @return the timezone
     */
    public String getTimezone() {
	return this.timezone;
    }

    /**
     * @return the lat
     */
    public Double getLat() {
	Double latitude = this.lat;
	if (this.location != null && this.lat==null) {
	    latitude = this.location.getY();
	}
	return latitude;
    }

    /**
     * @return the lng
     */
    public Double getLng() {
	Double longitude = this.lng;
	if (this.location != null && this.lng==null) {
	    longitude = this.location.getX();
	}
	return longitude;
    }

    /**
     * @return the zipCode
     */
    public Set<String> getZipCodes() {
	return this.zipCodes;
    }
    
   
    /**
     * add a zipcode to the list of zipcode
     * @param zipCode the zipcode to add
     */
    public void addZipCode(String zipCode) {
	 if (this.zipCodes == null){
		this.zipCodes = new HashSet<String>();
	 }
	 this.zipCodes.add(zipCode);//TODO tests
    }
    

    /**
     * Set the zipcodes
     * @param zipCodes the zipcodes to add
     */
    public void setZipCodes(Set<String> zipCodes) { 
	 this.zipCodes = zipCodes;//TODO tests 
    }

    /**
     * @return the placeType
     */
    public String getPlaceType() {
	return placeType;
    }

    /**
     * @return the google_map_url
     */
    public String getGoogle_map_url() {
	return this.google_map_url;
    }

    /**
     * @return the yahoo_map_url
     */
    public String getYahoo_map_url() {
	return this.yahoo_map_url;
    }
    
    /**
     * @return the yahoo_map_url
     */
    public String getOpensteetmap_map_url() {
	return this.openstreetmap_map_url;
    }

    /**
     * @return the country_flag_url
     */
    public String getCountry_flag_url() {
	return this.country_flag_url;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @return the area
     */
    public Double getArea() {
        return area;
    }

    /**
     * @return the tld
     */
    public String getTld() {
        return tld;
    }

    /**
     * @return the capitalName
     */
    public String getCapitalName() {
        return capitalName;
    }

    /**
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @return the postalCodeRegex
     */
    public String getPostalCodeRegex() {
        return postalCodeRegex;
    }

    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @return the currencyName
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * @return the equivalentFipsCode
     */
    public String getEquivalentFipsCode() {
        return equivalentFipsCode;
    }

    /**
     * @return the fipsCode
     */
    public String getFipsCode() {
        return fipsCode;
    }

    /**
     * @return the iso3166Alpha2Code
     */
    public String getIso3166Alpha2Code() {
        return iso3166Alpha2Code;
    }

    /**
     * @return the iso3166Alpha3Code
     */
    public String getIso3166Alpha3Code() {
        return iso3166Alpha3Code;
    }

    /**
     * @return the iso3166NumericCode
     */
    public Integer getIso3166NumericCode() {
        return iso3166NumericCode;
    }

    /**
     * @return the phonePrefix
     */
    public String getPhonePrefix() {
        return phonePrefix;
    }

    /**
     * @return the postalCodeMask
     */
    public String getPostalCodeMask() {
        return postalCodeMask;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureId == null) ? 0 : featureId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final GisFeatureDistance other = (GisFeatureDistance) obj;
		if (featureId == null) {
			if (other.featureId != null)
				return false;
		} else if (!featureId.equals(other.featureId))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public double getLength() {
	    return length;
	}

	public Boolean isOneWay() {
	    return oneWay;
	}

	/**
	 * @return used for street place : the street type
	 */
	public StreetType getStreetType() {
	    return streetType;
	}
	
	 /**
	 * @return used for street place : return the openstreetmap unique Id of the street
	 */
	public Long getOpenstreetmapId() {
	        return openstreetmapId;
	    }

	/**
	 * @return it is used for street :  Any information that indicate where the street is (generally the city)
	 */
	public String getIsIn() {
		return isIn;
	}

	/**
	 * @return the place where the street is located, 
	 * this field is filled when {@link OpenStreetMap#isIn}
	 *  is filled and we got more specific details (generally quarter, neighborhood)
	 */
	public String getIsInPlace() {
		return isInPlace;
	}

	/**
	 * @param isInPlace the most precise information on where the street is located,
	 * generally quarter neighborhood
	 */
	public void setIsInPlace(String isInPlace) {
		this.isInPlace = isInPlace;
	}

	/**
	 * @return the adm (aka administrative division) where the street is located.
	 */
	public String getIsInAdm() {
		return isInAdm;
	}

	/**
	 * @param isInAdm  the adm (aka administrative division) where the street is located
	 */
	public void setIsInAdm(String isInAdm) {
		this.isInAdm = isInAdm;
	}

	/**
	 * @return the zipcode where the street is located
	 */
	public String getIsInZip() {
		return isInZip;
	}

	/**
	 * @param isInZip the zipcode where the street is located.
	 */
	public void setIsInZip(String isInZip) {
		this.isInZip = isInZip;
	}


	public String getFullyQualifiedAddress() {
		return fullyQualifiedAddress;
	}

	public void setFullyQualifiedAddress(String fullyQualifiedAddress) {
		this.fullyQualifiedAddress = fullyQualifiedAddress;
	}


	public void setId(Long id) {
	    this.id = id;
	}



	public void setLocation(Point location) {
	    this.location = location;
	}



	public void setDistance(Double distance) {
	    this.distance = distance;
	}



	public void setName(String name) {
	    this.name = name;
	}



	public void setAdm1Code(String adm1Code) {
	    this.adm1Code = adm1Code;
	}



	public void setAdm2Code(String adm2Code) {
	    this.adm2Code = adm2Code;
	}



	public void setAdm3Code(String adm3Code) {
	    this.adm3Code = adm3Code;
	}



	public void setAdm4Code(String adm4Code) {
	    this.adm4Code = adm4Code;
	}



	public void setAdm1Name(String adm1Name) {
	    this.adm1Name = adm1Name;
	}



	public void setAdm2Name(String adm2Name) {
	    this.adm2Name = adm2Name;
	}



	public void setAdm3Name(String adm3Name) {
	    this.adm3Name = adm3Name;
	}



	public void setAdm4Name(String adm4Name) {
	    this.adm4Name = adm4Name;
	}



	public void setAsciiName(String asciiName) {
	    this.asciiName = asciiName;
	}



	public void setCountryCode(String countryCode) {
	    this.countryCode = countryCode;
	}



	public void setElevation(Integer elevation) {
	    this.elevation = elevation;
	}



	public void setFeatureClass(String featureClass) {
	    this.featureClass = featureClass;
	}



	public void setFeatureCode(String featureCode) {
	    this.featureCode = featureCode;
	}



	public void setFeatureId(Long featureId) {
	    this.featureId = featureId;
	}



	public void setGtopo30(Integer gtopo30) {
	    this.gtopo30 = gtopo30;
	}



	public void setPopulation(Integer population) {
	    this.population = population;
	}



	public void setTimezone(String timezone) {
	    this.timezone = timezone;
	}



	public void setLat(Double lat) {
	    this.lat = lat;
	}



	public void setLng(Double lng) {
	    this.lng = lng;
	}



	public void setPlaceType(String placeType) {
	    this.placeType = placeType;
	}



	public void setOneWay(Boolean oneWay) {
	    this.oneWay = oneWay;
	}



	public void setStreetType(StreetType streetType) {
	    this.streetType = streetType;
	}



	public void setOpenstreetmapId(Long openstreetmapId) {
	    this.openstreetmapId = openstreetmapId;
	}



	public void setLength(double length) {
	    this.length = length;
	}



	public void setGoogle_map_url(String google_map_url) {
	    this.google_map_url = google_map_url;
	}



	public void setYahoo_map_url(String yahoo_map_url) {
	    this.yahoo_map_url = yahoo_map_url;
	}
	
	public void setOpenstreetmap_map_url(String openstreetmap_map_url) {
	    this.openstreetmap_map_url = openstreetmap_map_url;
	}

	public void setCountry_flag_url(String country_flag_url) {
	    this.country_flag_url = country_flag_url;
	}



	public void setLevel(Integer level) {
	    this.level = level;
	}



	public void setArea(Double area) {
	    this.area = area;
	}



	public void setTld(String tld) {
	    this.tld = tld;
	}



	public void setCapitalName(String capitalName) {
	    this.capitalName = capitalName;
	}



	public void setContinent(String continent) {
	    this.continent = continent;
	}



	public void setPostalCodeRegex(String postalCodeRegex) {
	    this.postalCodeRegex = postalCodeRegex;
	}



	public void setCurrencyCode(String currencyCode) {
	    this.currencyCode = currencyCode;
	}



	public void setCurrencyName(String currencyName) {
	    this.currencyName = currencyName;
	}



	public void setEquivalentFipsCode(String equivalentFipsCode) {
	    this.equivalentFipsCode = equivalentFipsCode;
	}



	public void setFipsCode(String fipsCode) {
	    this.fipsCode = fipsCode;
	}



	public void setIso3166Alpha2Code(String iso3166Alpha2Code) {
	    this.iso3166Alpha2Code = iso3166Alpha2Code;
	}



	public void setIso3166Alpha3Code(String iso3166Alpha3Code) {
	    this.iso3166Alpha3Code = iso3166Alpha3Code;
	}



	public void setIso3166NumericCode(Integer iso3166NumericCode) {
	    this.iso3166NumericCode = iso3166NumericCode;
	}



	public void setPhonePrefix(String phonePrefix) {
	    this.phonePrefix = phonePrefix;
	}



	public void setPostalCodeMask(String postalCodeMask) {
	    this.postalCodeMask = postalCodeMask;
	}



	public void setIsIn(String isIn) {
	    this.isIn = isIn;
	}



	public String getAdm5Code() {
		return adm5Code;
	}



	public void setAdm5Code(String adm5Code) {
		this.adm5Code = adm5Code;
	}



	public String getAdm5Name() {
		return adm5Name;
	}



	public void setAdm5Name(String adm5Name) {
		this.adm5Name = adm5Name;
	}



	public String getOpenstreetmap_map_url() {
		return openstreetmap_map_url;
	}

	public String getAmenity() {
		return amenity;
	}

	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}

	public void setLength(Double length) {
		this.length = length;
	}
	
	/**
	     * update the calculated fields (GoogleMapUrl,YahooMapURL,CountryFlag,...)
	     * 
	     */
	/*TODOM    public  void  updateFields() {
		setGoogle_map_url(URLUtils.createGoogleMapUrl(getLocation(),
			getName()));
		setYahoo_map_url(URLUtils.createYahooMapUrl(getLocation()));
		setCountry_flag_url(URLUtils.createCountryFlagUrl(getCountryCode()));
		if (getLocation() != null) {
		    setLat(getLocation().getY());
		    setLng(getLocation().getX());
		}
		if (getFeatureClass() != null && getFeatureCode() != null) {
		    try {
			setPlaceType(FeatureCode.valueOf(
				getFeatureClass() + "_" + getFeatureCode()).getObject()
				.getClass().getSimpleName());
		    } catch (RuntimeException e) {
			logger.warn("no feature code for "+getFeatureClass() + "_" + getFeatureCode());
		    }
		}
	    }
*/
}
