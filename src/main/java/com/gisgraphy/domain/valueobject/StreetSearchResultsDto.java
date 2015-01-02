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
package com.gisgraphy.domain.valueobject;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO (data transfer object) for a list of {@link StreetDistance} The node
 * name is {@link Constants#STREETSEARCHRESULTSDTO_JAXB_NAME}
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
@XmlRootElement(name = Constants.STREETSEARCHRESULTSDTO_JAXB_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
public class StreetSearchResultsDto {


    private int numFound = 0;

    private Long QTime = null;
    
    private String query = null;

    private List<StreetDistance> result;

    /**
     * @param result
     *                The {@link StreetDistance}'s Collection
     * @param QTime
     *                The execution time
     * @param query The search term
     */
    public StreetSearchResultsDto(List<StreetDistance> result, Long QTime,String query) {
	super();
	this.numFound = result == null ? 0 : result.size();
	this.QTime = QTime;
	this.query = query;
	this.result = result;
    }

    /**
     * Default Constructor
     */
    public StreetSearchResultsDto() {
	super();
    }

    /**
     * @return The list of {@link StreetDistance}
     */
    public List<StreetDistance> getResult() {
	return result;
    }

   
    /**
     * @return the numFound
     */
    public int getNumFound() {
	return numFound;
    }

   
    /**
     * @return the qTime (aka : the execution Time) in ms
     */
    public Long getQTime() {
	return QTime;
    }

  
    /**
     * @return the name (prefix) of the street that has been searched
     */
    public String getQuery() {
        return query;
    }

   

}
