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
package com.gisgraphy.street;

import com.gisgraphy.domain.valueobject.Output;
import com.gisgraphy.domain.valueobject.Pagination;
import com.gisgraphy.geoloc.GeolocQuery;
import com.vividsolutions.jts.geom.Point;

/**
 *  a query to be execute by the @link {@link StreetSearchEngine}
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 *
 */
public class StreetSearchQuery extends GeolocQuery {
    
	 public static final String STREETTYPE_PARAMETER = "streettype";
	    public static final String ONEWAY_PARAMETER = "oneway";
	    public static final String NAME_PARAMETER = "name";
    public static final int DEFAULT_MAX_RESULTS = 50;
    public static final int DEFAULT_NB_RESULTS = 10;

    /**
     * @param point
     * @param radius
     * @param pagination
     * @param output
     * @param placeType
     */
    public StreetSearchQuery(Point point, double radius, Pagination pagination, Output output, Class<?> placeType) {
	withPagination(pagination);
	withOutput(output);
	this.point = point;
	withRadius(radius);
	withPlaceType(placeType);
    }

   
    
    /**
     * @param point
     * @param radius
     */
    public StreetSearchQuery(Point point, double radius) {
	this.point = point;
	withRadius(radius);
    }

    /**
     * @param point
     */
    public StreetSearchQuery(Point point) {
	this.point=point;
    }
    
    /**
     * @param name the name of the street to search
     */
    public StreetSearchQuery(String name) {
	this.name=name;
    }

    public final static int NAME_MAX_LENGTH = 200;

    private StreetType streetType = null;
    
    private String name = null;

    private Boolean oneWay= null;
    
    private StreetSearchMode streetSearchMode = StreetSearchMode.getDefault();
    


    
   

    /**
     * @param point
     *                the text to query, if the query is a number zipcode will
     *                be searched
     * @param radius
     *                The radius (distance)
     * @param pagination
     *                The pagination specification, if null : use default
     * @param output
     *                {@link Output} The output specification , if null : use
     *                default
     * @param streetType
     *                the type of street to search , if null : search for all street
     *                type.
     * @param oneWay the oneWay type criteria of the street
     * @param name the name the street must contains
     * @param streetSearchMode the street search mode for the specified name
     * @throws An
     *                 {@link IllegalArgumentException} if the point is null
     */
    public StreetSearchQuery(Point point, double radius, Pagination pagination,
	    Output output, StreetType streetType,Boolean oneWay,String name,StreetSearchMode streetSearchMode) {
	super(point, radius, pagination, output, null);
	withStreetType(streetType).
	withName(name).withOneWay(oneWay);
	if (name != null && streetSearchMode==null){
		withStreetSearchMode(StreetSearchMode.getDefault());
	}else {
		withStreetSearchMode(streetSearchMode);
	}
    }

    /**
     *  @param point
     *                the point to search street around
     * @param radius
     *                The radius (distance)
     * @param streetType
     *                the type of street to search , if null : search for all street
     *                type.
     * @throws An
     *                 {@link IllegalArgumentException} if the point is null
     */
    public StreetSearchQuery(Point point, double radius, StreetType streetType) {
	super(point, radius);
	withStreetType(streetType);
    }

    /**
     * @param point
     *                the point to search street around
     * @param streetType
     *                the type of street to search , if null : search for all street
     *                type.
     */
    public StreetSearchQuery(Point point, StreetType streetType) {
	super(point);
	withStreetType(streetType);
    }

    /**
     * @return the type of street we'd like to query
     */
    public StreetType getStreetType() {
	return this.streetType;
    }

    /**
     * @param streetType the StreetType of street we'd like to query
     * @return The current query to chain methods
     */
    public StreetSearchQuery withStreetType(StreetType streetType) {
	this.streetType = streetType;
	return this;
    }
    
    /**
     * @return the string the street must contains (aka : '%name%'). 
     */
    public String getName() {
	return this.name;
    }

    /**
     * @param name the string that the street must  contains (aka : '%name%').
     * not taken into account if empty string or null.
     * @throws StreetSearchException if length is greater than @see {@link StreetSearchQuery#NAME_MAX_LENGTH}
     * @return The current query to chain methods
     */
    public StreetSearchQuery withName(String name) {
	if (name == null || "".equals(name.trim())){
	    return this;
	}
	
	if (name.length() > StreetSearchQuery.NAME_MAX_LENGTH) {
	    throw new StreetSearchException("name is limited to "
		    + StreetSearchQuery.NAME_MAX_LENGTH + "characters");
	}
	
	this.name = name;
	return this;
    }
    
    /**
     * @param oneWay The oneWay type criteria of the street
     * @return  The current query to chain methods
     */
    public StreetSearchQuery withOneWay(Boolean oneWay){
	this.oneWay = oneWay;
	return this;
    }

    @Override
    public int getMaxLimitResult() {
	return DEFAULT_MAX_RESULTS;
    }

    /**
     * @return the oneWay criteria
     */
    public Boolean getOneWay() {
	return this.oneWay;
    }
    
    /**
	 * @return the street {@link StreetSearchMode}
	 */
	public StreetSearchMode getStreetSearchMode() {
		return streetSearchMode;
	}

	/**
	 * @param streetSearchMode the {@link StreetSearchMode}
	 * @return  The current query to chain methods
	 */
	public StreetSearchQuery withStreetSearchMode(StreetSearchMode streetSearchMode) {
		this.streetSearchMode = streetSearchMode;
		return this;
	}
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	Double lat = getPoint()==null?null:getPoint().getY();
	Double lng = getPoint()==null?null:getPoint().getY();
	String asString = "StreetSearchQuery (lat='"
		+ lat + "',long='" + lng + "') with apikey="+getApikey()+" and name="+this.name+" and streetsearchmode="+this.streetSearchMode+" and radius="
		+ getRadius() + " for streetType="+streetType+" and oneWay="+this.oneWay;
	asString += " with " + getOutput() + " and " + pagination +" and distance = "+this.hasDistanceField();
	return asString;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((oneWay == null) ? 0 : oneWay.hashCode());
	result = prime * result + ((streetSearchMode == null) ? 0 : streetSearchMode.hashCode());
	result = prime * result
		+ ((streetType == null) ? 0 : streetType.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final StreetSearchQuery other = (StreetSearchQuery) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (oneWay == null) {
	    if (other.oneWay != null)
		return false;
	} else if (!oneWay.equals(other.oneWay))
	    return false;
	if (streetSearchMode == null) {
	    if (other.streetSearchMode != null)
		return false;
	} else if (!streetSearchMode.equals(other.streetSearchMode))
	    return false;
	if (streetType == null) {
	    if (other.streetType != null)
		return false;
	} else if (!streetType.equals(other.streetType))
	    return false;
	return true;
    }

	
    
}
