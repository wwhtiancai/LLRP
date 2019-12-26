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
import org.llrp.ltk.generated.parameters.RTCTime;
import org.llrp.ltk.generated.parameters.ReadMode;
import org.llrp.ltk.generated.parameters.SecurityModuleSN;
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
public class GenaralConfigData extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(685);
    private static final Logger LOGGER = Logger.getLogger(GenaralConfigData.class);
    protected RTCTime rTCTime;
    protected SecurityModuleSN securityModuleSN;
    protected ReadMode readMode;

    /**
     * empty constructor to create new parameter.
     */
    public GenaralConfigData() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public GenaralConfigData(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public GenaralConfigData(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (rTCTime == null) {
            // optional parameter, may be null
            LOGGER.info(" rTCTime not set");
        } else {
            resultBits.append(rTCTime.encodeBinary());
        }

        if (securityModuleSN == null) {
            // optional parameter, may be null
            LOGGER.info(" securityModuleSN not set");
        } else {
            resultBits.append(securityModuleSN.encodeBinary());
        }

        if (readMode == null) {
            // optional parameter, may be null
            LOGGER.info(" readMode not set");
        } else {
            resultBits.append(readMode.encodeBinary());
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
        if (rTCTime == null) {
            LOGGER.info("rTCTime not set");
        } else {
            element.addContent(rTCTime.encodeXML(rTCTime.getClass()
                                                        .getSimpleName(), ns));
        }

        if (securityModuleSN == null) {
            LOGGER.info("securityModuleSN not set");
        } else {
            element.addContent(securityModuleSN.encodeXML(
                    securityModuleSN.getClass().getSimpleName(), ns));
        }

        if (readMode == null) {
            LOGGER.info("readMode not set");
        } else {
            element.addContent(readMode.encodeXML(readMode.getClass()
                                                          .getSimpleName(), ns));
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
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type RTCTime");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = rTCTime.length();
        }

        if ((type != null) && type.equals(RTCTime.TYPENUM)) {
            rTCTime = new RTCTime(binary.subList(position, tempLength));
            position += tempLength;
            LOGGER.debug(" rTCTime is instantiated with RTCTime with length" +
                tempLength);
        } else {
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type RTCTime");
        }

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
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type SecurityModuleSN");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = securityModuleSN.length();
        }

        if ((type != null) && type.equals(SecurityModuleSN.TYPENUM)) {
            securityModuleSN = new SecurityModuleSN(binary.subList(position,
                        tempLength));
            position += tempLength;
            LOGGER.debug(
                " securityModuleSN is instantiated with SecurityModuleSN with length" +
                tempLength);
        } else {
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type SecurityModuleSN");
        }

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
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type ReadMode");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = readMode.length();
        }

        if ((type != null) && type.equals(ReadMode.TYPENUM)) {
            readMode = new ReadMode(binary.subList(position, tempLength));
            position += tempLength;
            LOGGER.debug(" readMode is instantiated with ReadMode with length" +
                tempLength);
        } else {
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type ReadMode");
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
        temp = element.getChild("RTCTime", ns);

        if (temp != null) {
            rTCTime = new RTCTime(temp);
            LOGGER.info(
                "setting parameter rTCTime for parameter GenaralConfigData");
        }

        if (temp == null) {
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type rTCTime");
        }

        element.removeChild("RTCTime", ns);
        //parameter - not choices - no special actions needed
        temp = element.getChild("SecurityModuleSN", ns);

        if (temp != null) {
            securityModuleSN = new SecurityModuleSN(temp);
            LOGGER.info(
                "setting parameter securityModuleSN for parameter GenaralConfigData");
        }

        if (temp == null) {
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type securityModuleSN");
        }

        element.removeChild("SecurityModuleSN", ns);
        //parameter - not choices - no special actions needed
        temp = element.getChild("ReadMode", ns);

        if (temp != null) {
            readMode = new ReadMode(temp);
            LOGGER.info(
                "setting parameter readMode for parameter GenaralConfigData");
        }

        if (temp == null) {
            LOGGER.info(
                "GenaralConfigData misses optional parameter of type readMode");
        }

        element.removeChild("ReadMode", ns);

        if (element.getChildren().size() > 0) {
            String message = "GenaralConfigData has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters

    /**
    * set rTCTime of type RTCTime.
    * @param  rTCTime to be set
    */
    public void setRTCTime(final RTCTime rTCTime) {
        this.rTCTime = rTCTime;
    }

    /**
    * set securityModuleSN of type SecurityModuleSN.
    * @param  securityModuleSN to be set
    */
    public void setSecurityModuleSN(final SecurityModuleSN securityModuleSN) {
        this.securityModuleSN = securityModuleSN;
    }

    /**
    * set readMode of type ReadMode.
    * @param  readMode to be set
    */
    public void setReadMode(final ReadMode readMode) {
        this.readMode = readMode;
    }

    // end setter

    //getters

    /**
    * get rTCTime of type RTCTime .
    * @return  RTCTime
    */
    public RTCTime getRTCTime() {
        return rTCTime;
    }

    /**
    * get securityModuleSN of type SecurityModuleSN .
    * @return  SecurityModuleSN
    */
    public SecurityModuleSN getSecurityModuleSN() {
        return securityModuleSN;
    }

    /**
    * get readMode of type ReadMode .
    * @return  ReadMode
    */
    public ReadMode getReadMode() {
        return readMode;
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
        return "GenaralConfigData";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "GenaralConfigData: ";
        result = result.replaceFirst(", ", "");

        return result;
    }
}