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
/**
 *
 */
package com.gisgraphy.reversegeocoding;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.gisgraphy.domain.valueobject.Output;
import com.gisgraphy.domain.valueobject.Output.OutputStyle;
import com.gisgraphy.serializer.common.OutputFormat;
import com.vividsolutions.jts.geom.Point;

/**
 * A reverse geocoding query
 * 
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public class ReverseGeocodingQuery {
    
	protected Point point;

	private String callback ; 
    
    private String apikey;
    
    protected Output output = Output.DEFAULT_OUTPUT;
    
    private static Pattern callbackValidationPattern = Pattern.compile("\\w+");
    
    
    private static Logger logger = Logger.getLogger(ReverseGeocodingQuery.class); 
    
    public static final String LAT_PARAMETER = "lat";
    public static final String LONG_PARAMETER = "lng";
    public static final String CALLBACK_PARAMETER = "callback";

   


    /**
     * @param point
     *                the point to query around
     * @param output
     *                {@link Output} The output specification , if null : use
     *                default
     * @throws An
     *                 {@link IllegalArgumentException} if the point is null
     */
    public ReverseGeocodingQuery(Point point, 
	    Output output) {
	if (point==null){
	    throw new IllegalArgumentException("point(lat and long) must not be null");
	}
	withOutput(output);
	this.point = point;
    }
    
    /**
     * @param point
     *                the point to query around
     * * @throws An
     *                 {@link IllegalArgumentException} if the point is null
     */
    public ReverseGeocodingQuery(Point point) {
	if (point==null){
	    throw new IllegalArgumentException("point(lat and long) must not be null");
	}
	this.point = point;
    }

    /**
     * needed by cglib
     */
    protected ReverseGeocodingQuery() {
	super();
    }
    


    /**
     * @return The
     * @link {@link Output} object
     */
    public Output getOutput() {
	return output;
    }

    /**
     * @param output
     *                The {@link Output} Object to set. If the output is null :
     *                the {@link Output#DEFAULT_OUTPUT} is used
     * @return the current object to chain setters
     */
    public ReverseGeocodingQuery withOutput(Output output) {
	if (output == null) {
	    this.output = Output.DEFAULT_OUTPUT;
	} else {
	    this.output = output;
	}
	return this;
    }
    
    /**
     * @param callback the callback function name, it is use for script language (python, json, ruby, ...)
     * the result will be wrap with callback(DATA);
     * the callback should be alphanumeric, if not it won't be set
     * @return the current object to chain setters
     */
    public ReverseGeocodingQuery withCallback(String callback){
	if (callback!=null && callbackValidationPattern.matcher(callback).matches()){
	    this.callback= callback;
	} else if (callback==null){
		this.callback=null;
	}
	else { 
	    logger.warn("wrong callback specify : "+callback+", callback method sould be alphanumeric");
	}
	return this;
    }
    

    /**
     * @return The point from which we want to find GIS Object
     */
    public Point getPoint() {
	return this.point;
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
     * @return The verbose style mode
     * @see OutputStyle
     */
    public OutputStyle getOutputStyle() {
	return this.output.getStyle();
    }


    /**
     * @return The output format
     * @see OutputFormat
     */
    public OutputFormat getOutputFormat() {
	return this.output.getFormat();
    }

    /**
     * @return The iso639 Alpha2 LanguageCode that the output results should be
     */
    public String getOutputLanguage() {
	return this.output.getLanguageCode();
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
	String asString = this.getClass().getSimpleName() + "around "+point+" with "
		+ getOutput() + " and " + " and callback="+this.callback;
	return asString;
    }

    /**
     * @return the callback method name
     */
    public String getCallback() {
        return callback;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((callback == null) ? 0 : callback.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReverseGeocodingQuery other = (ReverseGeocodingQuery) obj;
		if (callback == null) {
			if (other.callback != null)
				return false;
		} else if (!callback.equals(other.callback))
			return false;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
    
   
}
