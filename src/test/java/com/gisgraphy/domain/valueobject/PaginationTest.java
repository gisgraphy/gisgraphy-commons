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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PaginationTest {

    @Test
    public void paginate() {
	Pagination.paginate().from(1).to(5);
    }

    @Test
    public void defaultPaginate() {
	assertEquals(Pagination.DEFAULT_FROM, Pagination.DEFAULT_PAGINATION
		.getFrom());
	assertEquals(Pagination.DEFAULT_MAX_RESULTS,
		Pagination.DEFAULT_PAGINATION.getMaxNumberOfResults());
    }

    @Test
    public void paginateShouldHaveToGreaterThanFrom() {

	Pagination pagination = Pagination.paginate().from(5).to(1);
	assertEquals(5, pagination.getFrom());
	assertEquals(Pagination.DEFAULT_MAX_RESULTS, pagination
		.getMaxNumberOfResults());

	pagination = Pagination.paginate().from(2).to(2);
	assertTrue("pagination can have from=to", pagination.getFrom() == 2
		&& pagination.getTo() == 2);

    }

    @Test
    public void paginateShouldNotHaveFromParametersNegativeOrZero() {
	Pagination pagination = Pagination.paginate().from(-1).to(1);
	assertEquals("pagination with negative from should have from="
		+ Pagination.DEFAULT_FROM, Pagination.DEFAULT_FROM, pagination
		.getFrom());

	pagination = Pagination.paginate().from(0).to(1);
	assertEquals("pagination with from=0 from should have from="
		+ Pagination.DEFAULT_FROM, Pagination.DEFAULT_FROM, pagination
		.getFrom());
    }

    @Test
    public void paginateShouldNotHaveToParametersNegativeOrZero() {
	Pagination pagination = Pagination.paginate().from(1).to(-1);
	assertTrue(
		"pagination with negative to should be set to a value to have numberOfResults="
			+ Pagination.DEFAULT_MAX_RESULTS,
		pagination.getFrom() == 1
			&& pagination.getMaxNumberOfResults() == Pagination.DEFAULT_MAX_RESULTS);

	pagination = Pagination.paginate().from(1).to(0);
	assertTrue(
		"pagination with 0 to should be set to a value to have numberOfResults="
			+ Pagination.DEFAULT_MAX_RESULTS,
		pagination.getFrom() == 1
			&& pagination.getMaxNumberOfResults() == Pagination.DEFAULT_MAX_RESULTS);

    }

    @Test
    public void paginateShouldHaveFromGreaterOrEqualsToToParameter() {

	Pagination pagination = Pagination.paginate().from(2).to(1);
	assertTrue(
		"pagination with to < from should be set to a value to have numberOfResults="
			+ Pagination.DEFAULT_MAX_RESULTS,
		pagination.getFrom() == 2
			&& pagination.getMaxNumberOfResults() == Pagination.DEFAULT_MAX_RESULTS);

	pagination = Pagination.paginate().from(2).to(2);
	assertTrue(
		"pagination with to = from should Have the specified values",
		pagination.getFrom() == 2 && pagination.getTo() == 2);
    }

    @Test
    public void limitNumberOfResultsShouldLimit() {
	// with negative limit=>no effect
	assertEquals(16, Pagination.paginate().from(4).to(16)
		.limitNumberOfResults(-3).getTo());
	assertEquals(13, Pagination.paginate().from(4).to(16)
			.limitNumberOfResults(-3).getMaxNumberOfResults());
	// with limit=0 =>no effect
	assertEquals(16, Pagination.paginate().from(4).to(16)
		.limitNumberOfResults(0).getTo());
	assertEquals(13, Pagination.paginate().from(4).to(16)
			.limitNumberOfResults(0).getMaxNumberOfResults());

	assertEquals(13, Pagination.paginate().from(4).to(16)
		.getMaxNumberOfResults());
	assertEquals(16, Pagination.paginate().from(4).to(16)
			.getTo());
	// with numberOfResults > limit
	assertEquals(10, Pagination.paginate().from(4).to(16)
		.limitNumberOfResults(10).getMaxNumberOfResults());
	// with numberOfResults < limit
	assertEquals(2, Pagination.paginate().from(4).to(5)
		.limitNumberOfResults(10).getMaxNumberOfResults());
	// with numberOfResults = limit
	assertEquals(10, Pagination.paginate().from(4).to(13)
		.limitNumberOfResults(10).getMaxNumberOfResults());

    }

   
    @Test
    public void getNumberOfResultsShouldReturnCorrectValue() {
	assertEquals(1, Pagination.paginate().from(1).to(1)
		.getMaxNumberOfResults());
	assertEquals(2, Pagination.paginate().from(1).to(2)
		.getMaxNumberOfResults());
	assertEquals(5, Pagination.paginate().from(5).to(9)
		.getMaxNumberOfResults());
    }
    
    @Test
    public void paginateWithMaxResults(){
	Pagination pagination = Pagination.paginateWithMaxResults(32).from(1).to(-1);
	assertEquals(1, pagination.getFrom());
	assertEquals(10, pagination.getTo());
	
	pagination = Pagination.paginateWithMaxResults(32).from(5).to(-1);
	assertEquals(5, pagination.getFrom());
	assertEquals(14, pagination.getTo());
	
	pagination = Pagination.paginateWithMaxResults(3).from(5).to(-1);
	assertEquals(5, pagination.getFrom());
	assertEquals(7, pagination.getTo());
	
	pagination = Pagination.paginateWithMaxResults(3).from(5).to(2);
	assertEquals(5, pagination.getFrom());
	assertEquals(7, pagination.getTo());
	
	
    }

}
