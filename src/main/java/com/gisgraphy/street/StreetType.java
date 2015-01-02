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


/**
 * type of street
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 *
 */
public enum StreetType {

	RESIDENTIAL,
	SERVICE,
	UNCLASSIFIED, 
	TRACK,
	FOOTWAY,
	TERTIARY,
	PATH,
	SECONDARY,
	PRIMARY,
	BUS_STOP,
	TURNING_CIRCLE,
	CROSSING,
	CYCLEWAY,
	TRUNK, TRAFFIC_SIGNALS,
	MOTORWAY,
	MOTORWAY_LINK,
	ROAD,
	LIVING_STREET,
	STEPS,
	PEDESTRIAN,
	TRUNK_LINK,
	STREET_LAMP, PRIMARY_LINK,
	MOTORWAY_JUNCTION,
	SECONDARY_LINK,
	STOP,
	CONSTRUCTION,
	TERTIARY_LINK,
	BRIDLEWAY,
	MINI_ROUNDABOUT,
	PLATFORM,
	PROPOSED,
	GIVE_WAY,
	SPEED_CAMERA,
	EMERGENCY_ACCESS_POINT,
	MILESTONE,
	RACEWAY,
	FORD,
	REST_AREA,
	PASSING_PLACE,
	SERVICES,
	ABANDONED,
	ELEVATOR,
	UNSURFACED,
	EMERGENCY_BAY, 
	STILE,
	INCLINE_STEEP,
	INCLINE, 
	STREET_LIGHT;
    //BYWAY, MINOR, SECONDARY_LINK, CONSTRUCTION, UNSURFACED, BRIDLEWAY, PRIMARY_LINK, LIVING_STREET, TRUNK_LINK, STEPS, PATH, ROAD, PEDESTRIAN, TRUNK, MOTORWAY, CYCLEWAY, MOTORWAY_LINK, PRIMARY, FOOTWAY, TERTIARY, SECONDARY, TRACK, UNCLASSIFIED, SERVICE, RESIDENTIAL;
   

    /**
     * @param type
     *            the type as String (case insensitive)
     * @return the streetType from the String or null if the StreetType can not
     *         be determine
     */
    public static StreetType getFromString(String type) {
	StreetType streetType = null;
	try {
	    streetType = StreetType.valueOf(type.toUpperCase());
	} catch (RuntimeException e) {
	}
	return streetType;

    }

}
