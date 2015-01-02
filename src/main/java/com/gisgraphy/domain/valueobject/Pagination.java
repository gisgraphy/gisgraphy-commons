/*******************************************************************************
 * Gisgraphy Project 
 *  
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *  
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *    Lesser General Public License for more details.
 *  
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA
 *  
 *   Copyright 2008  Gisgraphy project 
 * 
 *   David Masclet <davidmasclet@gisgraphy.com>
 ******************************************************************************/
package com.gisgraphy.domain.valueobject;
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
 * Represent a pagination specification.the design (with DSL and interface) does
 * not allow to set the 'to' properties before the from e.g :
 * <code>Paginate().from(1).to(9) will return [1,2,3,4,5,6,7,8,9].
 * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public class Pagination {

    /**
     * If the 'to' parameter is not specified or incorrect, it will be set to a
     * value to have {@link #DEFAULT_MAX_RESULTS} results this value is NOT the
     * default to parameters, we prefer to use a startegy to fix number of
     * results instead of the from
     */
    public static final int DEFAULT_MAX_RESULTS = 10;
    
    public static final int DEFAULT_NB_RESULTS = 10;
    
    
    /**
     * max result the pagination should have
     */
    public int maxResult = DEFAULT_MAX_RESULTS;

    /**
     * the default 'from' parameters if the one specified is missing or
     * incorrect
     */
    public static final int DEFAULT_FROM = 1;
    
    /**
     * the default 'from' parameters if the one specified is missing or
     * incorrect
     */
    public static final int DEFAULT_TO = 10;

    /**
     * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
     *         To specification
     */
    public interface ToSpecification {
	Pagination to(int to);
    }

    /**
     * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
     *         From Specification
     */
    public interface FromSpecification {
	ToSpecification from(int from);
    }

    /**
     * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a> A
     *         pagination builder
     */
    public static class PaginationBuilder implements FromSpecification,
	    ToSpecification {

	private int from;
	private int to;
	private int maxResults = 100000000;

	private PaginationBuilder() {

	}

	// builder is private
	private Pagination build() {
	    Pagination pagination = new Pagination();
	    pagination.maxResult=maxResults;
	    pagination.from(this.from)
		    .to(this.to);
	    return pagination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gisgraphy.domain.valueobject.Pagination.ToSpecification#to(int)
	 */
	public Pagination to(int to) {
	    this.to = to;
	    return build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gisgraphy.domain.valueobject.Pagination.FromSpecification#from(int)
	 */
	public ToSpecification from(int from) {
	    this.from = from;
	    return this;
	}

    }

    /**
     * Default Pagination
     */
    public static final Pagination DEFAULT_PAGINATION = new Pagination();
    
    /**
     * Default Pagination
     */
    public static final Pagination ONE_RESULT = new Pagination().from(1).to(1);

    /**
     * The from attribute (int to force non null value)
     */
    private int from = DEFAULT_FROM;

    /**
     * The to attribute
     */
    private int to = DEFAULT_FROM + DEFAULT_MAX_RESULTS - 1;

    /**
     * private contructor
     */
    private Pagination() {
	super();
    }

    /**
     * Static method to be used to create a pagination object. (tip : use Static
     * import)
     * 
     * @return An instance of {@link FromSpecification} to force the from
     *         parameter to be set first
     */
    public static FromSpecification paginate() {
	return new PaginationBuilder();
    }
    
    /**
     * Static method to be used to create a pagination object and define maxResults. (tip : use Static
     * import). because The to parameter is calculated with the masResultValue, you need to use this method befor set the to and the from.
     * 
     * @param maxResult the max number of results he pagination should have, if max results is incorrect,{@link #DEFAULT_MAX_RESULTS} will be used
     * @return An instance of {@link FromSpecification} to force the from
     *         parameter to be set first
     */
    public static FromSpecification paginateWithMaxResults(int maxResult) {
	PaginationBuilder builder = new PaginationBuilder();
	if (maxResult <= 0){
	    builder.maxResults = DEFAULT_MAX_RESULTS;
	}else {
	    builder.maxResults = maxResult;
	}
	return builder;
    }

    /**
     * @param from
     *                the from parameter for the current pagination, must be > 0
     *                (numberd from 1)
     * @return the current Pagination instance in order to chain setters if the
     *         parameter is not >0, it will be set to default :
     *         {@link #DEFAULT_FROM}
     * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
     */
    private Pagination from(int from) {
	this.from = from > 0 ? from : DEFAULT_FROM;
	return this;
    }

    /**
     * @param to
     *                the to parameter for the current pagination,must be > 0
     *                and > {@link #from(int)}
     * @return the current Pagination instance in order to chain setters If the
     *         parameter is < from or <= 0, it will be set at a specific value
     *         in order to have {@link #DEFAULT_MAX_RESULTS}
     * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
     */
    private Pagination to(int to) {
	this.to = (to > 0 && to >= this.from) ? to : this.from
		+ DEFAULT_NB_RESULTS - 1;
	limitNumberOfResults(maxResult);
	return this;
    }

    /**
     * @return the from
     */
    public int getFrom() {
	return from;
    }

    /**
     * @return the to
     */
    public int getTo() {
	return to;
    }

    /**
     * Do a post-treatment on the current pagination to limit number of results.
     * 'from will be unchanged, only 'to' will be changed. if limit is <=0, there
     * is no effect
     * 
     * @param limit
     *                the max number Of results
     * @return the current Pagination to chain methods
     */
    public Pagination limitNumberOfResults(int limit) {
	if (limit > 0) {
	    this.to = getMaxNumberOfResults() > limit ? from + limit - 1 : to;
	}
	return this;
    }

    /**
     * @return how many results will be return according the from and to
     *         parameters (the max expected numbers of results). it can be less
     *         than expected if there is less results to return. e.g :
     *         from(1).to(5) can only return 2 results if there is only 2
     *         parameters but this method return the max expected numbers of
     *         results) it will return 0 if from and to equals 0
     */
    public int getMaxNumberOfResults() {
    	return (to - from) + 1;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + from;
	result = prime * result + to;
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
	final Pagination other = (Pagination) obj;
	if (from != other.from)
	    return false;
	if (to != other.to)
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "paginate from " + from + " to " + to;
    }

}
