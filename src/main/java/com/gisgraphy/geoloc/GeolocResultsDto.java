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
package com.gisgraphy.geoloc;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.gisgraphy.domain.valueobject.Constants;
import com.gisgraphy.domain.valueobject.GisFeatureDistance;

/**
 * DTO (data transfer object) for a list of {@link GisFeatureDistance} The node
 * name is {@link Constants#GEOLOCRESULTSDTO_JAXB_NAME}
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
@XmlRootElement(name = GeolocResultsDto.GEOLOCRESULTSDTO_JAXB_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
public class GeolocResultsDto {
    

    /**
     * The node name for {@link GeolocResultsDto} node in JAXB
     */
    public final static String GEOLOCRESULTSDTO_JAXB_NAME = "results";

    private Integer numFound = 0;
    private Long QTime = null;
    private List<GisFeatureDistance> result;


    /**
     * @param result
     *                The {@link GisFeatureDistance}'s Collection
     * @param QTime
     *                The execution time
     */
    public GeolocResultsDto(List<GisFeatureDistance> result, Long QTime) {
	super();
	this.result = result;
	this.numFound = result == null ? 0 : result.size();
	this.QTime = QTime;
    }

    /**
     * Default Constructor
     */
    public GeolocResultsDto() {
	super();
    }

    /**
     * @return The list of {@link GisFeatureDistance}
     */
    public List<GisFeatureDistance> getResult() {
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

   
}
