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
package com.gisgraphy.service;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.gisgraphy.domain.valueobject.Output;
import com.gisgraphy.domain.valueobject.Output.OutputStyle;
import com.gisgraphy.domain.valueobject.Pagination;
import com.gisgraphy.serializer.common.OutputFormat;

/**
 * An abstract query for all GisQuery. define {@link Output},
 * {@link Pagination}, and a placetype
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public abstract class AbstractGisQuery {
    
    private String callback ; 
    
    private String apikey;
    
    private static Pattern callbackValidationPattern = Pattern.compile("\\w+");
    
    private static Logger logger = Logger.getLogger(AbstractGisQuery.class);
    
    public static final int DEFAULT_NB_RESULTS = 10;

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((output == null) ? 0 : output.hashCode());
	result = prime * result
		+ ((pagination == null) ? 0 : pagination.hashCode());
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
	final AbstractGisQuery other = (AbstractGisQuery) obj;
	if (output == null) {
	    if (other.output != null)
		return false;
	} else if (!output.equals(other.output))
	    return false;
	if (pagination == null) {
	    if (other.pagination != null)
		return false;
	} else if (!pagination.equals(other.pagination))
	    return false;
	return true;
    }

    /**
     * @param pagination
     *                The {@linkplain Pagination} specification, if null : use
     *                default
     * @param output
     *                {@linkplain Output} The output specification , if null :
     *                use default
     */
    public AbstractGisQuery(Pagination pagination, Output output) {
	super();
	withPagination(pagination);
	withOutput(output);
    }

    /**
     * Constructor with default {@linkplain Pagination}, {@linkplain Output},
     * and placetype
     */
    public AbstractGisQuery() {
	super();
    }
    
    /**
     * @return the maximum number of results that the query should return
     */
    public int getMaxLimitResult(){
	return 10;
    }

    /**
     * @see Pagination
     */
    protected Pagination pagination = Pagination.DEFAULT_PAGINATION;

    /**
     * @see Output
     */
    protected Output output = Output.DEFAULT_OUTPUT;

    /**
     * @return the {@link Pagination} Object
     */
    public Pagination getPagination() {
	return pagination;
    }

    /**
     * @param pagination
     *                the pagination to set. If the pagination is null the
     *                {@link Pagination#DEFAULT_PAGINATION} is used
     * @return The current query to chain methods
     * @see Pagination
     */
    public AbstractGisQuery withPagination(Pagination pagination) {
	if (pagination == null) {
	    this.pagination = Pagination.DEFAULT_PAGINATION;
	} else {
	    this.pagination = pagination;
	}
	return this;
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
    public AbstractGisQuery withOutput(Output output) {
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
    public AbstractGisQuery withCallback(String callback){
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
     * @return The verbose style mode
     * @see OutputStyle
     */
    public OutputStyle getOutputStyle() {
	return this.output.getStyle();
    }

    /**
     * @return The 'from' pagination value
     * @see Pagination
     */
    public int getFirstPaginationIndex() {
	return this.pagination.getFrom();
    }

    /**
     * @return The 'to' pagination value
     * @see Pagination
     */
    public int getLastPaginationIndex() {
	return this.pagination.getTo();
    }

    /**
     * @return The number of results that the query is limited
     * @see Pagination
     */
    public int getMaxNumberOfResults() {
	return this.pagination.getMaxNumberOfResults();
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	String asString = this.getClass().getSimpleName() + " with "
		+ getOutput() + " and " + pagination +" and callback="+this.callback;
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

}
