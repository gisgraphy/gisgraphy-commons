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
package com.gisgraphy.geoloc;



import com.gisgraphy.domain.valueobject.GisgraphyConfig;
import com.gisgraphy.domain.valueobject.Output;
import com.gisgraphy.domain.valueobject.Pagination;
import com.gisgraphy.service.AbstractGisQuery;
import com.vividsolutions.jts.geom.Point;

/**
 * A GeolocQuery Query
 * 
 * @see Pagination
 * @see Output
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public class GeolocQuery extends AbstractGisQuery {
	
	 public static final String PLACETYPE_PARAMETER = "placetype";
	    public static final String LAT_PARAMETER = "lat";
	    public static final String LONG_PARAMETER = "lng";
	    public static final String RADIUS_PARAMETER = "radius";
	    public static final String DISTANCE_PARAMETER = "distance";
	    public static final String CALLBACK_PARAMETER = "callback";
	    public static final String MUNICIPALITY_PARAMETER = "municipalityFilter";

    public static final int DEFAULT_MAX_RESULTS = 20;
    
    public static final int DEFAULT_NB_RESULTS = 10;
   



    /**
     * needed by CGlib
     */
    protected GeolocQuery() {
	super();
    }

    /**
     * The type of gis to search , if null : search for all place type.
     */
    private Class<?> placeType = GisgraphyConfig.defaultGeolocSearchPlaceTypeClass;

    /**
     * Default radius in meters
     */
    public static final double DEFAULT_RADIUS = 10000;

    protected Point point;
    private double radius = DEFAULT_RADIUS;
    private boolean distanceField = true;
    private boolean municipalityFilter = false;

    

    /**
     * @param point
     *                the point to query around
     * @param radius
     *                The radius (distance) in meters
     * @param pagination
     *                The pagination specification, if null : use default
     * @param output
     *                {@link Output} The output specification , if null : use
     *                default
     * @param placeType
     *                the type of gis to search , if null : search for all place
     *                type.
     * @throws An
     *                 {@link IllegalArgumentException} if the point is null
     */
    public GeolocQuery(Point point, double radius, Pagination pagination,
	    Output output, Class<?> placeType) {
	super(pagination, output);
	if (point==null){
	    throw new IllegalArgumentException("point(lat and long) must not be null");
	}
	this.point = point;
	withRadius(radius);
	withPlaceType(placeType);
    }

    /**
     * Constructor with default {@linkplain Pagination}, {@linkplain Output},
     * and placetype (see
     * {@link GisgraphyConfig#defaultGeolocSearchPlaceTypeClass}
     * 
     * @param point
     *                The point from which we want to find GIS Object
     * @param radius
     *                The radius (distance) in meters
     */
    public GeolocQuery(Point point, double radius) {
	this(point, radius, null, null,
		GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);
    }

    /**
     * @return The latitude (north-south) .
     * @see #getLongitude()
     */
    public Double getLatitude() {
	Double latitude = null;
	if (this.point != null) {
	    latitude = this.point.getY();
	}
	return latitude;
    }

    /**
     * @return Returns the longitude (east-West).
     * @see #getLongitude()
     */
    public Double getLongitude() {
	Double longitude = null;
	if (this.point != null) {
	    longitude = this.point.getX();
	}
	return longitude;
    }

    /**
     * Constructor with default {@linkplain Pagination}, {@linkplain Output},
     * radius and placetype
     * 
     * @param point
     *                The point from which we want to find GIS Object
     * @see #DEFAULT_RADIUS
     */
    public GeolocQuery(Point point) {
	this(point, GeolocQuery.DEFAULT_RADIUS, null, null,
		GisgraphyConfig.defaultGeolocSearchPlaceTypeClass);
    }

    /**
     * @return The point from which we want to find GIS Object
     */
    public Point getPoint() {
	return this.point;
    }

    /**
     * @param radius
     *                The radius to set in meters. Limit the query to the specified
     *                radius, if the radius is <=0 , it will be set to the
     *                default radius.
     */
    public GeolocQuery withRadius(double radius) {
	if (radius <= 0) {
	    this.radius = DEFAULT_RADIUS;
	} else {
	    this.radius = radius;
	}
	return this;
    }

    /**
     * @return The radius
     */
    public double getRadius() {
	return this.radius;
    }

    /**
     * @return Wether the output will be indented
     * @see Output#isIndented()
     */
    public boolean isOutputIndented() {
	return output.isIndented();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	String asString = "GeolocQuery with apikey="+getApikey()+" and (lat='"
		+ point.getY() + "',long='" + point.getX() + "') and radius="
		+ this.radius + " for ";
	if (this.placeType == null) {
	    asString += "all placeType";
	} else {
	    asString += this.placeType.getSimpleName();
	}
	asString += " with " + getOutput() + " and " + pagination +" and distance = "+distanceField + " and municipalityFilter="+municipalityFilter;
	return asString;
    }

    /**
     * @return the placeType : it limits the search to an object of a specific
     *         class
     */
    public Class<?> getPlaceType() {
	return this.placeType;
    }

    /**
     * @param placeType
     *                The placeType to set, if null, search for all placetype
     * @return The current query to chain methods
     */
    public GeolocQuery withPlaceType(Class<?> placeType) {
	this.placeType = placeType;
	return this;
    }

    @Override
    public int getMaxLimitResult() {
    	return DEFAULT_MAX_RESULTS;
    }

    /**
     * @return true if the distance should be calculate or not
     */
    public boolean hasDistanceField() {
        return distanceField;
    }

    /**
     * @param distanceField If the distance should be include or not
     * @return Whether the distance field should be output 
     */
    public GeolocQuery withDistanceField(boolean distanceField) {
        this.distanceField = distanceField;
        return this;
    }
    
    /**
     * @return true if we should filter city that are flagged as 'municipalityFilter'
     */
    public boolean hasMunicipalityFilter() {
        return municipalityFilter;
    }

    /**
     * @param distanceField If the distance should be include or not
     * @return Whether the distance field should be output 
     */
    public GeolocQuery withMunicipalityFilter(boolean municipality) {
        this.municipalityFilter = municipality;
        return this;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((placeType == null) ? 0 : placeType.hashCode());
	result = prime * result + ((point == null) ? 0 : point.hashCode());
	long temp;
	temp = Double.doubleToLongBits(radius);
	result = prime * result + (int) (temp ^ (temp >>> 32));
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
	final GeolocQuery other = (GeolocQuery) obj;
	if (placeType == null) {
	    if (other.placeType != null)
		return false;
	} else if (!placeType.equals(other.placeType))
	    return false;
	if (point == null) {
	    if (other.point != null)
		return false;
	} else if (point.getX() != (other.point.getX())){
	    return false;
	}
	else if (point.getY() != (other.point.getY())){
	    return false;
	}
	else if (municipalityFilter != (other.municipalityFilter)){
	    return false;
	}
	if (Double.doubleToLongBits(radius) != Double
		.doubleToLongBits(other.radius))
	    return false;
	return true;
    }
  
    

}
