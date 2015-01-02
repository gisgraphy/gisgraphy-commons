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


import com.gisgraphy.serializer.common.OutputFormat;

/**
 * Represent an output specification.
 * 
 * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public class Output {

    

    /**
     * All the possible fulltext search output style verbosity Short (basic
     * informations) : feature_id, name, fully_qualified_name, zipcode (if
     * city), placetype, country_code, country_name<br/> Medium (More
     * informations) : Short + lat, lon, feature_class, feature_code,
     * population, fips<br/> Long (AlternateNames +adm informations) : Medium +
     * alternateNames, adm1_name, adm2_name, adm3_name, Adm4_name, adm1_code,
     * Adm2_code, Adm3_code, Adm4_code<br/> Full (alternatenames for adm and
     * country): Long + country_alternate_name_, adm1_alternate_name_,
     * adm2_alternate_name_<br/>
     */
    public enum OutputStyle {
	SHORT ,	MEDIUM ,LONG,FULL ;


	/**
	 * @return the default outputStyle
	 */
	public static OutputStyle getDefault() {
	    return OutputStyle.MEDIUM;
	}

	/**
	 * @param style
	 *                the style as String
	 * @return the outputStyle from the String or the default OutputStyle if
	 *         the style can not be determine
	 * @see #getDefault()
	 */
	public static OutputStyle getFromString(String style) {
	    OutputStyle outputStyle = OutputStyle.getDefault();
	    try {
		outputStyle = OutputStyle.valueOf(style.toUpperCase());
	    } catch (RuntimeException e) {
	    }
	    return outputStyle;

	}

    }

    // output fields
    /**
     * The default output format
     */
  //  public static final OutputFormat DEFAULT_OUTPUT_FORMAT = OutputFormat.XML;

    /**
     * The Default Output style
     */
    //public static final OutputStyle DEFAULT_OUTPUT_STYLE = OutputStyle.getDefault();

    /**
     * The Default {@link #languageCode}
     */
    public static final String DEFAULT_LANGUAGE_CODE = null;
    
    public static final boolean DEFAULT_INDENTATION = false;
    
    public static final boolean DEFAULT_HOUSENUMBER_OUTPUT = true;

   
    /**
     * The output format value default to {@link OutputFormat#XML}
     */
    private OutputFormat format = OutputFormat.getDefault();

    /**
     * the iso639 Alpha2 LanguageCode, default to {@link #DEFAULT_LANGUAGE_CODE}
     */
    private String languageCode = Output.DEFAULT_LANGUAGE_CODE;
    
    /**
     * Wether the house number should be retrieved
     */
    private boolean houseNumberOutput = DEFAULT_HOUSENUMBER_OUTPUT;

    /**
     * The output style verbosity, default to {@link #DEFAULT_OUTPUT_STYLE}
     */
    private OutputStyle style =  OutputStyle.MEDIUM;

    private boolean indent = DEFAULT_INDENTATION;

    /**
     * Default contructor with format
     */
    private Output(OutputFormat format) {
	if (format == null) {
	    this.format = OutputFormat.getDefault();
	} else {
	    this.format = format;
	}
    }

    /**
     * @param format
     *                The format the output shoud have
     * @return an output specification with the specified format
     */
    public static Output withFormat(OutputFormat format) {
	return new Output(format);
    }

    /**
     * @return an output specification with default output format
     * @see OutputFormat#getDefault()
     */
    public static Output withDefaultFormat() {
	return new Output(OutputFormat.getDefault());
    }

   

    /**
     * @return the format, default is {@link OutputFormat#getDefault()}
     * @see #withFormat(com.gisgraphy.domain.valueobject.Output.OutputFormat)
     */
    public OutputFormat getFormat() {
	return format;
    }

    /**
     * @return the iso639 Alpha2 LanguageCode, default is
     *         {@link Output#DEFAULT_LANGUAGE_CODE}
     * @see #withLanguageCode(String)
     */
    public String getLanguageCode() {
	return languageCode;
    }

    /**
     * @return the style, default is {@link OutputStyle#getDefault()}
     * @see #withStyle(com.gisgraphy.domain.valueobject.Output.OutputStyle)
     */
    public OutputStyle getStyle() {
	return style;
    }

    /**
     * @return wether the output must be indent, default to false
     * @see #withIndentation()
     */
    public boolean isIndented() {
	return indent;
    }

    /**
     * This method force the output to be indented
     * 
     * @return the current Object instance in order to chain methods
     * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
     */
    public Output withIndentation() {
	this.indent = true;
	return this;
    }
    
    
    /**
     * @return wether the housenumber should be output, default to false
     * @see #withIndentation()
     */
    public boolean isHouseNumberOutput() {
	return houseNumberOutput;
    }

    /**
     * This method force the house number to NOT be output 
     * 
     * @return the current Object instance in order to chain methods
     * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
     */
    public Output withoutHouseNumberOutput() {
	this.houseNumberOutput = true;
	return this;
    }

    /**
     * @param languageCode
     *                The iso639Alpha2 LanguageCode parameter that the output
     *                format should be, the fulltext results will be in the
     *                specified language The language code can be null or a non
     *                existing language code (no check is done).if the specified
     *                languageCode is null or an empty string, it will be set to
     *                null
     * @return the current Object instance in order to chain methods
     * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
     */
    public Output withLanguageCode(String languageCode) {
	if (languageCode == null || "".equals(languageCode.trim())) {
	    this.languageCode = DEFAULT_LANGUAGE_CODE;
	} else {
	    this.languageCode = languageCode.toUpperCase();
	}
	return this;
    }

    /**
     * @param style
     *                The verbosity style parameter that the output format
     *                should have, the output will include more or less data
     *                according to the specified style
     * @return the current Object instance in order to chain methods
     * @see <a href="http://www.infoq.com/articles/internal-dsls-java/">DSL</a>
     */
    public Output withStyle(OutputStyle style) {
	if (style == null) {
	    this.style = OutputStyle.MEDIUM;
	} else {
	    this.style = style;
	}
	return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "output in " + format + ", in " + style
		+ " style with language " + languageCode + " and ident="
		+ isIndented();
    }
    
    /**
     * Output with default values
     */
    public static final Output DEFAULT_OUTPUT = Output.withFormat(OutputFormat.XML)
	    .withStyle(OutputStyle.MEDIUM).withLanguageCode(
		    Output.DEFAULT_LANGUAGE_CODE);

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((format == null) ? 0 : format.hashCode());
	result = prime * result + (indent ? 1231 : 1237);
	result = prime * result
		+ ((languageCode == null) ? 0 : languageCode.hashCode());
	result = prime * result + ((style == null) ? 0 : style.hashCode());
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
	final Output other = (Output) obj;
	if (format == null) {
	    if (other.format != null)
		return false;
	} else if (!format.equals(other.format))
	    return false;
	if (indent != other.indent)
	    return false;
	if (languageCode == null) {
	    if (other.languageCode != null)
		return false;
	} else if (!languageCode.equals(other.languageCode))
	    return false;
	if (style == null) {
	    if (other.style != null)
		return false;
	} else if (!style.equals(other.style))
	    return false;
	return true;
    }

}