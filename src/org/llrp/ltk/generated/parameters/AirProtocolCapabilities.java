/*
 *
 * This file was generated by LLRP Code Generator
 * see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit
 * for more information
 * Generated on: Mon Apr 10 16:16:15 CST 2017;
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
import org.llrp.ltk.generated.interfaces.DeviceAirProtocolCapabilities;
import org.llrp.ltk.generated.parameters.HbProtocolCapabilities;
import org.llrp.ltk.generated.parameters.PerAntennaAirProtocol;
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
public class AirProtocolCapabilities extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(366);
    private static final Logger LOGGER = Logger.getLogger(AirProtocolCapabilities.class);
    protected List<PerAntennaAirProtocol> perAntennaAirProtocolList = new LinkedList<PerAntennaAirProtocol>();
    protected List<DeviceAirProtocolCapabilities> deviceAirProtocolCapabilitiesList =
        new LinkedList<DeviceAirProtocolCapabilities>();

    /**
     * empty constructor to create new parameter.
     */
    public AirProtocolCapabilities() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public AirProtocolCapabilities(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public AirProtocolCapabilities(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (perAntennaAirProtocolList == null) {
            //just warn - it is optional 
            LOGGER.info(" perAntennaAirProtocolList not set");
        } else {
            for (PerAntennaAirProtocol field : perAntennaAirProtocolList) {
                resultBits.append(field.encodeBinary());
            }
        }

        if (deviceAirProtocolCapabilitiesList == null) {
            //just warn - it is optional 
            LOGGER.info(" deviceAirProtocolCapabilitiesList not set");
        } else {
            for (DeviceAirProtocolCapabilities field : deviceAirProtocolCapabilitiesList) {
                resultBits.append(field.encodeBinary());
            }
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

        //parameters
        if (perAntennaAirProtocolList == null) {
            LOGGER.info("perAntennaAirProtocolList not set");
        } else {
            for (PerAntennaAirProtocol field : perAntennaAirProtocolList) {
                element.addContent(field.encodeXML(field.getClass().getName()
                                                        .replaceAll(field.getClass()
                                                                         .getPackage()
                                                                         .getName() +
                            ".", ""), ns));
            }
        }

        if (deviceAirProtocolCapabilitiesList == null) {
            LOGGER.info("deviceAirProtocolCapabilitiesList not set");
        } else {
            for (DeviceAirProtocolCapabilities field : deviceAirProtocolCapabilitiesList) {
                element.addContent(field.encodeXML(field.getClass().getName()
                                                        .replaceAll(field.getClass()
                                                                         .getPackage()
                                                                         .getName() +
                            ".", ""), ns));
            }
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

        // list of parameters
        perAntennaAirProtocolList = new LinkedList<PerAntennaAirProtocol>();
        LOGGER.debug("decoding parameter perAntennaAirProtocolList ");

        while (position < binary.length()) {
            // store if one parameter matched
            boolean atLeastOnce = false;

            // look ahead to see type
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

            //add parameter to list if type number matches
            if ((type != null) && type.equals(PerAntennaAirProtocol.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = PerAntennaAirProtocol.length();
                }

                perAntennaAirProtocolList.add(new PerAntennaAirProtocol(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding PerAntennaAirProtocol to perAntennaAirProtocolList ");
                atLeastOnce = true;
                position += tempLength;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (perAntennaAirProtocolList.isEmpty()) {
            LOGGER.info(
                "encoded message does not contain parameter for optional perAntennaAirProtocolList");
        }

        // list of parameters
        deviceAirProtocolCapabilitiesList = new LinkedList<DeviceAirProtocolCapabilities>();
        LOGGER.debug("decoding parameter deviceAirProtocolCapabilitiesList ");

        while (position < binary.length()) {
            // store if one parameter matched
            boolean atLeastOnce = false;

            // look ahead to see type
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

            //choiceRef
            if ((type != null) && type.equals(HbProtocolCapabilities.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = HbProtocolCapabilities.length();
                }

                deviceAirProtocolCapabilitiesList.add(new HbProtocolCapabilities(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding HbProtocolCapabilities to deviceAirProtocolCapabilitiesList ");
                position += tempLength;
                atLeastOnce = true;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (deviceAirProtocolCapabilitiesList.isEmpty()) {
            LOGGER.info(
                "encoded message does not contain parameter for optional deviceAirProtocolCapabilitiesList");
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

        //parameter - not choices - no special actions needed
        //we expect a list of parameters
        perAntennaAirProtocolList = new LinkedList<PerAntennaAirProtocol>();
        tempList = element.getChildren("PerAntennaAirProtocol", ns);

        if ((tempList == null) || tempList.isEmpty()) {
            LOGGER.info(
                "AirProtocolCapabilities misses optional parameter of type perAntennaAirProtocolList");
        } else {
            for (Element e : tempList) {
                perAntennaAirProtocolList.add(new PerAntennaAirProtocol(e));
                LOGGER.debug(
                    "adding PerAntennaAirProtocol to perAntennaAirProtocolList ");
            }
        }

        element.removeChildren("PerAntennaAirProtocol", ns);
        //choices - must check all possible subtypes
        //list of Choice Type Parameter
        deviceAirProtocolCapabilitiesList = new LinkedList<DeviceAirProtocolCapabilities>();
        // for each possible subtype get all childs
        tempList = element.getChildren("HbProtocolCapabilities", ns);

        for (Element e : tempList) {
            deviceAirProtocolCapabilitiesList.add(new HbProtocolCapabilities(e));
            LOGGER.debug(
                "adding HbProtocolCapabilities to deviceAirProtocolCapabilitiesList ");
            atLeastOnce = true;
        }

        element.removeChildren("HbProtocolCapabilities", ns);

        if (!atLeastOnce) {
            LOGGER.info(
                "AirProtocolCapabilities misses optional parameter of type deviceAirProtocolCapabilitiesList");
        }

        atLeastOnce = false;

        if (element.getChildren().size() > 0) {
            String message = "AirProtocolCapabilities has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters

    /**
    * set perAntennaAirProtocolList of type  List &lt;PerAntennaAirProtocol>.
    * @param  perAntennaAirProtocolList to be set
    */
    public void setPerAntennaAirProtocolList(
        final List<PerAntennaAirProtocol> perAntennaAirProtocolList) {
        this.perAntennaAirProtocolList = perAntennaAirProtocolList;
    }

    /**
    * set deviceAirProtocolCapabilitiesList of type  List &lt;DeviceAirProtocolCapabilities>.
    * @param  deviceAirProtocolCapabilitiesList to be set
    */
    public void setDeviceAirProtocolCapabilitiesList(
        final List<DeviceAirProtocolCapabilities> deviceAirProtocolCapabilitiesList) {
        this.deviceAirProtocolCapabilitiesList = deviceAirProtocolCapabilitiesList;
    }

    // end setter

    //getters

    /**
    * get perAntennaAirProtocolList of type List &lt;PerAntennaAirProtocol> .
    * @return  List &lt;PerAntennaAirProtocol>
    */
    public List<PerAntennaAirProtocol> getPerAntennaAirProtocolList() {
        return perAntennaAirProtocolList;
    }

    /**
    * get deviceAirProtocolCapabilitiesList of type List &lt;DeviceAirProtocolCapabilities> .
    * @return  List &lt;DeviceAirProtocolCapabilities>
    */
    public List<DeviceAirProtocolCapabilities> getDeviceAirProtocolCapabilitiesList() {
        return deviceAirProtocolCapabilitiesList;
    }

    // end getters

    //add methods

    /**
    * add element perAntennaAirProtocol of type PerAntennaAirProtocol .
    * @param  perAntennaAirProtocol of type PerAntennaAirProtocol
    */
    public void addToPerAntennaAirProtocolList(
        PerAntennaAirProtocol perAntennaAirProtocol) {
        if (this.perAntennaAirProtocolList == null) {
            this.perAntennaAirProtocolList = new LinkedList<PerAntennaAirProtocol>();
        }

        this.perAntennaAirProtocolList.add(perAntennaAirProtocol);
    }

    /**
    * add element deviceAirProtocolCapabilities of type DeviceAirProtocolCapabilities .
    * @param  deviceAirProtocolCapabilities of type DeviceAirProtocolCapabilities
    */
    public void addToDeviceAirProtocolCapabilitiesList(
        DeviceAirProtocolCapabilities deviceAirProtocolCapabilities) {
        if (this.deviceAirProtocolCapabilitiesList == null) {
            this.deviceAirProtocolCapabilitiesList = new LinkedList<DeviceAirProtocolCapabilities>();
        }

        this.deviceAirProtocolCapabilitiesList.add(deviceAirProtocolCapabilities);
    }

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
        return "AirProtocolCapabilities";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "AirProtocolCapabilities: ";
        result = result.replaceFirst(", ", "");

        return result;
    }
}