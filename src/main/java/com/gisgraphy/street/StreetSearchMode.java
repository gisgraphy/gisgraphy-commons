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
 * define the different way to search for street (by name)
 * 
 *  @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 *
 */
public enum StreetSearchMode {
    
    /**
	 * mode suitable for autocompletion and search of a part of the street name
	 * (not entire word)
	 */
    CONTAINS,
    
    /**
	 * Mode suitable to search for severalentire words with fulltext
	 * functionality (case insensitive,accent insensitive,...)
	 */
    FULLTEXT;

    
    /**
     * @return the default streetSearch mode
     */
    public static StreetSearchMode getDefault(){
    	return StreetSearchMode.CONTAINS;
    }
    
    public static StreetSearchMode getFromString(String searchModeString) {
	    try {
		return StreetSearchMode.valueOf(searchModeString.toUpperCase());
	    } catch (RuntimeException e) {
	    	 return StreetSearchMode.getDefault();
	    }
}
    
   
}
