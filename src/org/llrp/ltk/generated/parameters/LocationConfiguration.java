/*
 *
 * This file was generated by LLRP Code Generator
 * see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit
 * for more information
 * Generated on: Mon Apr 10 16:16:18 CST 2017;
 *
 */

/*
 * Copyright 2007 ETH Zurich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 */
package org.llrp.ltk.generated.parameters;

import org.apache.log4j.Logger;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.exceptions.MissingParameterException;
import org.llrp.ltk.generated.LLRPConstants;
import org.llrp.ltk.generated.enumerations.ReaderLocationType;
import org.llrp.ltk.generated.interfaces.LocationInfo;
import org.llrp.ltk.generated.parameters.BdsLocation;
import org.llrp.ltk.generated.parameters.GpsLocation;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedShort;

import java.util.LinkedList;
import java.util.List;


/**
 *

See also

 */

/**
 *

See also
      .
 */
public class LocationConfiguration extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(681);
    private static final Logger LOGGER = Logger.getLogger(LocationConfiguration.class);
    protected ReaderLocationType locationType;
    protected LocationInfo locationInfo;

    /**
     * empty constructor to create new parameter.
     */
    public LocationConfiguration() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public LocationConfiguration(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public LocationConfiguration(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (locationType == null) {
            LOGGER.warn(" locationType not set");
            throw new MissingParameterException(
                " locationType not set  for Parameter of Type LocationConfiguration");
        }

        resultBits.append(locationType.encodeBinary());

        if (locationInfo == null) {
            // single parameter, may not be null
            LOGGER.warn(" locationInfo not set");
            throw new MissingParameterException(" locationInfo not set");
        } else {
            resultBits.append(locationInfo.encodeBinary());
        }

        return resultBits;
    }

    /**
    * {@inheritDoc}
    */
    public Content encodeXML(String name, Namespace ns) {
        // element in namespace defined by parent element
        Element element = new Element(name, ns);
        // child element are always in default LLRP namespace
        ns = Namespace.getNamespace("", LLRPConstants.LLRPNAMESPACE);

        if (locationType == null) {
            LOGGER.warn(" locationType not set");
            throw new MissingParameterException(" locationType not set");
        } else {
            element.addContent(locationType.encodeXML("LocationType", ns));
        }

        //parameters
        if (locationInfo == null) {
            LOGGER.info("locationInfo not set");
            throw new MissingParameterException("locationInfo not set");
        } else {
            element.addContent(locationInfo.encodeXML(
                    locationInfo.getClass().getSimpleName(), ns));
        }

        return element;
    }

    /**
    * {@inheritDoc}
    */
    protected void decodeBinarySpecific(LLRPBitList binary) {
        int position = 0 + TYPENUMBERLENGTH + PARAMETERTYPELENGTH; //change by wuwh 
        int tempByteLength;
        int tempLength = 0;
        int count;
        SignedShort type;
        int fieldCount;
        locationType = new ReaderLocationType(binary.subList(position,
                    ReaderLocationType.length()));
        position += ReaderLocationType.length();

        // look ahead to see type
        // may be optional or exactly once
        type = null;
        tempByteLength = 0;
        tempLength = 0;

        try {
            // if first bit is one it is a TV Parameter
            if (binary.get(position)) {
                // do not take the first bit as it is always 1
                type = new SignedShort(binary.subList(position + 1, 7));
            } else {
                type = new SignedShort(binary.subList(position,
                            PARAMETERTYPELENGTH)); //change by wuwh
                tempByteLength = new UnsignedShort(binary.subList(position +
                            PARAMETERTYPELENGTH, UnsignedShort.length())).toShort(); //change by wuwh	
                tempLength = 8 * (tempByteLength + 4); //change by wuwh
            }
        } catch (IllegalArgumentException le) {
            // if an IllegalArgumentException is thrown, list was not long enough so the parameter is missing
            LOGGER.warn(
                "LocationConfiguration misses non optional parameter of type LocationInfo");
            throw new MissingParameterException(
                "LocationConfiguration misses non optional parameter of type LocationInfo");
        }

        boolean found = false;
        LOGGER.debug("decoding choice type LocationInfo ");

        //if first bit is 1 it is a TV Parameter
        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = GpsLocation.length();
        }

        if ((type != null) && type.equals(GpsLocation.TYPENUM)) {
            locationInfo = new GpsLocation(binary.subList(position, tempLength));
            LOGGER.debug(
                " locationInfo instatiated to GpsLocation with length " +
                tempLength);
            position += tempLength;
            found = true;
        }

        //if first bit is 1 it is a TV Parameter
        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = BdsLocation.length();
        }

        if ((type != null) && type.equals(BdsLocation.TYPENUM)) {
            locationInfo = new BdsLocation(binary.subList(position, tempLength));
            LOGGER.debug(
                " locationInfo instatiated to BdsLocation with length " +
                tempLength);
            position += tempLength;
            found = true;
        }

        if (!found) {
            LOGGER.warn(
                "encoded message misses non optional parameter locationInfo");
            throw new MissingParameterException(
                "LocationConfiguration misses non optional parameter of type LocationInfo");
        }
    }

    /**
    * {@inheritDoc}
    */
    public void decodeXML(Element element) throws InvalidLLRPMessageException {
        List<Element> tempList = null;
        boolean atLeastOnce = false;

        Element temp = null;

        // child element are always in default LLRP namespace
        Namespace ns = Namespace.getNamespace(LLRPConstants.LLRPNAMESPACE);

        temp = element.getChild("LocationType", ns);

        if (temp != null) {
            locationType = new ReaderLocationType(temp);
        }

        element.removeChild("LocationType", ns);

        //choices - must check all possible subtypes
        boolean found = false;
        LOGGER.debug("decoding choice type LocationInfo ");
        // try to get child for each possible subtype
        temp = element.getChild("GpsLocation", ns);

        if (temp != null) {
            locationInfo = new GpsLocation(temp);
            LOGGER.debug(" locationInfo instatiated to GpsLocation with");
            found = true;
        }

        element.removeChild("GpsLocation", ns);
        // try to get child for each possible subtype
        temp = element.getChild("BdsLocation", ns);

        if (temp != null) {
            locationInfo = new BdsLocation(temp);
            LOGGER.debug(" locationInfo instatiated to BdsLocation with");
            found = true;
        }

        element.removeChild("BdsLocation", ns);

        if (!found) {
            LOGGER.info(
                "LocationConfiguration misses optional parameter of type locationInfoList");
        }

        if (element.getChildren().size() > 0) {
            String message = "LocationConfiguration has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set locationType of type ReaderLocationType .
    * @param  locationType to be set
    */
    public void setLocationType(final ReaderLocationType locationType) {
        this.locationType = locationType;
    }

    /**
    * set locationInfo of type LocationInfo.
    * @param  locationInfo to be set
    */
    public void setLocationInfo(final LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    // end setter

    //getters
    /**
    * get locationType of type ReaderLocationType.
    * @return  ReaderLocationType
    */
    public ReaderLocationType getLocationType() {
        return locationType;
    }

    /**
    * get locationInfo of type LocationInfo .
    * @return  LocationInfo
    */
    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    // end getters

    //add methods

    // end add

    /**
    * For TLV Parameter length can not be determined at compile time. This method therefore always returns 0.
    * @return Integer always zero
    */
    public static Integer length() {
        return 0;
    }

    /**
    * {@inheritDoc}
    */
    public SignedShort getTypeNum() {
        return TYPENUM;
    }

    /**
    * {@inheritDoc}
    */
    public String getName() {
        return "LocationConfiguration";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "LocationConfiguration: ";
        result += ", locationType: ";
        result += locationType;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
