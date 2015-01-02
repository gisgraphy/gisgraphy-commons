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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO (data transfer object) that contains a list of {@link SolrResponseDto}
 * and other metaData like the maximum score, The time the query has take
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public class FulltextResultsDto {

    private final static List<SolrResponseDto> DEFAULT_RESULTS = new ArrayList<SolrResponseDto>();


    protected long numFound = 0;

    protected Map<String, List<String>> suggestionMap = new HashMap<String,  List<String>>();

    protected String collatedResult;

    protected String spellCheckProposal;

    protected int QTime = 0;

    protected int resultsSize = 0;

    protected Float maxScore = 0F;

    protected List<SolrResponseDto> results = DEFAULT_RESULTS;

   

    /**
     * Default Constructor
     */
    public FulltextResultsDto() {
	super();
    }

    /**
     * @return The list of {@link SolrResponseDto}
     */
    public List<SolrResponseDto> getResults() {
	return results;
    }

    /**
     * @return the number of results that match the query. It is different from
     *         {@link #getResultsSize()} : due to pagination the numFound can be
     *         greater than the value returned by {@linkplain #getResultsSize()}
     */
    public long getNumFound() {
	return numFound;
    }

    /**
     * @return The execution time in ms
     */
    public int getQTime() {
	return QTime;
    }

    /**
     * @return The size of the results. It is different form {@link #numFound}
     *         It is different from {@link #getResultsSize()} : due to
     *         pagination the numFound can be greater than the value returned by
     *         {@linkplain #getResultsSize()}
     */
    public int getResultsSize() {
	return resultsSize;
    }

    /**
     * @return the maxScore
     */
    public Float getMaxScore() {
	return maxScore;
    }

    /**
     * @return the suggestionMap with the
     *         entered searched term as key and a list of suggestions as
     *         value. it
     *         will never return null but an empty map if there is no suggestion
     */
    public Map<String,  List<String>> getSuggestionMap() {
	return suggestionMap;
    }
    
    /**
     * @return the collatedResult returned by Solr
     */
    public String getCollatedResult() {
        return collatedResult;
    }

    /**
     * @return a string for the best proposal for spellChecking
     */
    public String getSpellCheckProposal() {
        return spellCheckProposal;
    }

}
