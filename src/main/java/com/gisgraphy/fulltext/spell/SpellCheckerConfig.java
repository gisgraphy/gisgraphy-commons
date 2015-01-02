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
package com.gisgraphy.fulltext.spell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gisgraphy.fulltext.FulltextQuery;

/**
 * 
 * SpecllChecker options
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 * 
 */
public class SpellCheckerConfig {
	
	 /**
     * The logger
     */
    public static final Logger logger = LoggerFactory
	    .getLogger(SpellCheckerConfig.class);

	/**
	 * Wether the spellchecker is active or not. default is true
	 */
	public static boolean enabled = true;

	/**
	 * Wether the default value is active or not, if we don't specify the value
	 * in the {@link FulltextQuery}.default is true
	 */
	public static boolean activeByDefault = true;

	/**
	 * the default spellchecker name, default value is the value returned by
	 * {@link SpellCheckerDictionaryNames#getDefault()}
	 */
	public static SpellCheckerDictionaryNames spellcheckerDictionaryName = SpellCheckerDictionaryNames
			.getDefault();

	/**
	 * The number of suggestion spellchecked, default is 2
	 */
	public static int numberOfSuggestion = 2;

	/**
	 * suggest a query with all the terms with the best suggestion for each
	 * word. default is true
	 */
	public static boolean collateResults = true;

	/**
	 * @return the enabled
	 */
	public static boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		SpellCheckerConfig.enabled = enabled;
		logger.info("the spellchecker enabled parameter has been set to "+enabled);
	}

	/**
	 * @return the activeByDefault
	 */
	public boolean isActiveByDefault() {
		return activeByDefault;
	}

	/**
	 * @param activeByDefault
	 *            the activeByDefault to set
	 */
	public void setActiveByDefault(boolean activeByDefault) {
		SpellCheckerConfig.activeByDefault = activeByDefault;
		logger.info("the spellchecker active by default parameter has been set to "+activeByDefault);
	}

	/**
	 * @return the spellcheckerName
	 */
	public SpellCheckerDictionaryNames getSpellcheckerDictionaryName() {
		return spellcheckerDictionaryName;
	}

	/**
	 * @param spellcheckerDictionaryName
	 *            the spellcheckerName to set
	 */
	public void setSpellcheckerDictionaryName(SpellCheckerDictionaryNames spellcheckerDictionaryName) {
		SpellCheckerConfig.spellcheckerDictionaryName = spellcheckerDictionaryName;
		logger.info("the spellchecker dictionary name parameter has been set to "+spellcheckerDictionaryName);
	}

	/**
	 * @return the numberOfSuggestion
	 */
	public int getNumberOfSuggestion() {
		return numberOfSuggestion;
	}

	/**
	 * @param numberOfSuggestion
	 *            the numberOfSuggestion to set
	 */
	public void setNumberOfSuggestion(int numberOfSuggestion) {
		SpellCheckerConfig.numberOfSuggestion = numberOfSuggestion;
		logger.info("the spellchecker number of suggetion parameter has been set to "+numberOfSuggestion);
	}

	/**
	 * @return the collateResults
	 */
	public boolean isCollateResults() {
		return collateResults;
	}

	/**
	 * @param collateResults
	 *            the collateResults to set
	 */
	public void setCollateResults(boolean collateResults) {
		SpellCheckerConfig.collateResults = collateResults;
		logger.info("the spellchecker collate results parameter has been set to "+collateResults);
	}

}
