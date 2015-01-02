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
package com.gisgraphy.fulltext;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gisgraphy.domain.valueobject.Output;
import com.gisgraphy.domain.valueobject.Pagination;
import com.gisgraphy.fulltext.spell.SpellCheckerConfig;
import com.gisgraphy.service.AbstractGisQuery;
import com.vividsolutions.jts.geom.Point;

/**
 * A fulltext Query
 * 
 * @see Pagination
 * @see Output
 * @see IFullTextSearchEngine
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public class FulltextQuery extends AbstractGisQuery {
	
	public static final String COUNTRY_PARAMETER = "country";
	public static final String LANG_PARAMETER = "lang";
	public static final String STYLE_PARAMETER = "style";
	public static final String PLACETYPE_PARAMETER = "placetype";
	public static final String QUERY_PARAMETER = "q";
	public static final String ALLWORDSREQUIRED_PARAMETER = "allwordsrequired";
	public static final String SPELLCHECKING_PARAMETER = "spellchecking";
	public static final String LAT_PARAMETER = "lat";
	public static final String LONG_PARAMETER = "lng";
	public static final String RADIUS_PARAMETER = "radius";
	public static final String SUGGEST_PARAMETER = "suggest";
	
	public final static int REGEXP_CASESENSITIVE_FLAG =  Pattern.UNICODE_CASE;
	private static final Pattern CLEAN_QUERY_PATTERN = Pattern.compile("([\\{\\}\\(\\)\\=\\!\"\'\\\\]+)");
	private static final Pattern COMMA_PATTERN = Pattern.compile("([\\,]+)");

	public static final int DEFAULT_MAX_RESULTS = 20;
	
    public final static int QUERY_MAX_LENGTH = 200;
    
    public final static boolean ALL_WORDS_REQUIRED_DEFAULT_OPTION = false;
    
    public static final int DEFAULT_NB_RESULTS = 10;
    
    /**
     * Default radius in meters
     */
    public static final double DEFAULT_RADIUS = 10000;

    protected Point point;
    private double radius = DEFAULT_RADIUS;
    
    /**
     * The logger
     */
    public static final Logger logger = LoggerFactory
	    .getLogger(FulltextQuery.class);

    /**
     * Constructor needed by CGLib
     */
    @SuppressWarnings("unused")
    private FulltextQuery() {
	super();
    }
    
    /**
     * @param point
     *                The point to search around
     *                @see #withRadius(double)
     */
    public FulltextQuery around(Point point) {
	    this.point = point;
	    return this;
    }
    
    /**
     * @return The radius
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
    public FulltextQuery withRadius(double radius) {
	if (radius < 0) {
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
     * The type of GIS to search , if null : search for all place type.
     */
    private Class<?>[] placeTypes = null;


    private String query = "";
    private String countryCode;
    private boolean allWordsRequired = ALL_WORDS_REQUIRED_DEFAULT_OPTION;
    private boolean suggest = false;
    
    private boolean spellchecking = SpellCheckerConfig.activeByDefault;

   

    /**
     * @param query
     *                The text to query, if the query is a number zipcode will
     *                be searched
     * @param pagination
     *                The pagination specification, if null : use default
     * @param output
     *                {@link Output} The output specification , if null : use
     *                default
     * @param placeType
     *                the type of gis to search , if null : search for all place
     *                type.
     * @param countryCode
     *                Limit the search to the specified countryCode. If null or
     *                wrong it would be ignored (no check is done)
     * @throws An
     *                 {@link IllegalArgumentException} if the query is null or
     *                 empty string
     */
    public FulltextQuery(String query, Pagination pagination, Output output,
	    Class<?>[] placeType, String countryCode) {
	super(pagination, output);
	withQuery(query);
	withPlaceTypes(placeType);
	limitToCountryCode(countryCode);
    }
    
    public FulltextQuery withQuery(String queryString){
	if (queryString== null){throw new IllegalArgumentException("Query must not be empty");}
	if (queryString.length() > FulltextQuery.QUERY_MAX_LENGTH) {
	    throw new IllegalArgumentException("query is limited to "
		    + FulltextQuery.QUERY_MAX_LENGTH + "characters");
	}
	this.query = queryString.trim();
	cleanQueryString();
	if ("".equals(this.query.trim())){
		throw new IllegalArgumentException("Query must not be empty");
	}
	return this;
    }

    /**
     * @param query
     *                The text to search
     * @throws An
     *                 {@link IllegalArgumentException} if the query is null or
     *                 an empty string
     */
    public FulltextQuery(String query) {
	super();
	withQuery(query); 
    }

    /**
     * @return The searched text for this FullTextQuery
     */
    public String getQuery() {
	return query;
    }

    /**
     * @param countryCode
     *                the countryCode to set. Limit the query to the specified
     *                countrycode, if the country code is null, it will be
     *                ignored. If null or invalid, it will be ignored (no check
     *                is done)
     */
    public FulltextQuery limitToCountryCode(String countryCode) {
	this.countryCode = countryCode;
	return this;
    }

    /**
     * @return the countryCode of the country that the query will be restricted
     *         to
     */
    public String getCountryCode() {
	return countryCode;
    }

    /**
     * @return Wether the output will be indented
     * @see Output#isIndented()
     */
    public boolean isOutputIndented() {
	return output.isIndented();
    }

   

    /**
     * @return the placeType : it limits the search to object of one or more specifics
     *         class, if the array contains null values it is the responsibility
     *                 of the client to take it into account
     */
    public Class<?>[] getPlaceTypes() {
	return this.placeTypes;
    }

    /**
     * @param placeTypes
     *                The placeTypes to set, if null, search for all placetype, 
     *                if the array contains null values it is the responsibility
     *                 of the client to take it into account
     * @return The current query to chain methods
     */
    public FulltextQuery withPlaceTypes(Class<?>[] placeTypes) {
	this.placeTypes = placeTypes;
	return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	String allwordsRequiredString = allWordsRequired?" with all words required ":" without all words required ";
    	String autosuggestionString = isSuggest()?" for auto suggestion ":" ";
		String asString = "FullTextQuery '" + this.query + "' "+allwordsRequiredString+autosuggestionString;
		if (point!=null){
		    asString+="with apikey="+getApikey()+" around (lat='"
			+ point.getY() + "',long='" + point.getX() + "') and radius="
			+ this.radius + " for ";
		}
		if (this.placeTypes == null) {
			asString += "all placeType";
		} else {
			for (int i = 0; i < this.placeTypes.length; i++) {
				if (this.placeTypes[i] != null) {
					asString += this.placeTypes[i].getSimpleName();
					if (i != this.placeTypes.length - 1) {
						asString += " and ";
					}
				}
			}
		}
		asString += " with " + getOutput() + " and " + pagination + " for countrycode " + countryCode;
		return asString;
	}

    
    
    /**
     *  Enable the spellchecking for this query
     * @return The current query to chain methods
     */
    public FulltextQuery withSpellChecking(){
    	this.spellchecking = true;
    	return this;
    }
    
    /**
     *  Disable the spellchecking for this query
     * @return The current query to chain methods
     */
    public FulltextQuery withoutSpellChecking(){
    	this.spellchecking = false;
    	return this;
    }
    
    /**
     *  Wether the spellchecking is enabled for this query
     * @return The current query to chain methods
     */
    public boolean hasSpellChecking(){
    	return this.spellchecking; 
    }

   
    
    @Override
    public int getMaxLimitResult() {
    	return DEFAULT_MAX_RESULTS;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((countryCode == null) ? 0 : countryCode.hashCode());
	result = prime * result
		+ ((placeTypes == null) ? 0 : placeTypes.hashCode());
	result = prime * result + ((query == null) ? 0 : query.hashCode());
	result = prime * result + (spellchecking ? 1231 : 1237);
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
	final FulltextQuery other = (FulltextQuery) obj;
	if (countryCode == null) {
	    if (other.countryCode != null)
		return false;
	} else if (!countryCode.equals(other.countryCode))
	    return false;
	if (placeTypes == null) {
	    if (other.placeTypes != null)
		return false;
	} else if (!placeTypes.equals(other.placeTypes))
	    return false;
	if (query == null) {
	    if (other.query != null)
		return false;
	} else if (!query.equals(other.query))
	    return false;
	if (spellchecking != other.spellchecking)
	    return false;
	return true;
    }
    
    public void cleanQueryString(){
	if (this.query != null){
	   this.query =  CLEAN_QUERY_PATTERN.matcher(this.query).replaceAll(" ");
	   this.query = COMMA_PATTERN.matcher(this.query).replaceAll(", ");
	   this.query =  Pattern.compile("\\b(AND)\\b").matcher(this.query).replaceAll("and");
	   this.query =  Pattern.compile("\\b(OR)\\b").matcher(this.query).replaceAll("or").trim();
	} 
    }

	/**
	 * wether all the query terms are mandatory or not
	 * @return the allRequired
	 */
	public boolean isAllwordsRequired() {
		return allWordsRequired;
	}

	/**
	 * @param allWordsRequired if all the query terms are mandatory
	 * @return The current query to chain methods
	 */
	public FulltextQuery withAllWordsRequired(boolean allWordsRequired) {
		this.allWordsRequired = allWordsRequired;
		return this;
	}

	public boolean isSpellcheckingEnabled() {
		return spellchecking;
	}

	/**
	 * @return true if the fulltext query is for auto suggestion/ autocompletion
	 */
	public boolean isSuggest() {
		return suggest;
	}

	/**
	 * @param suggest wheter the fulltext query is used for auto suggection
	 */
	public FulltextQuery withSuggest(boolean suggest) {
		this.suggest = suggest;
		return this;
	}
    

}
