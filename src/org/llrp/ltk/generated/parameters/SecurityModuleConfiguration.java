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
import org.llrp.ltk.generated.parameters.GenaralConfigData;
import org.llrp.ltk.generated.parameters.PrivateConfigData;
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
public class SecurityModuleConfiguration extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(684);
    private static final Logger LOGGER = Logger.getLogger(SecurityModuleConfiguration.class);
    protected GenaralConfigData genaralConfigData;
    protected PrivateConfigData privateConfigData;

    /**
     * empty constructor to create new parameter.
     */
    public SecurityModuleConfiguration() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public SecurityModuleConfiguration(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public SecurityModuleConfiguration(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (genaralConfigData == null) {
            // optional parameter, may be null
            LOGGER.info(" genaralConfigData not set");
        } else {
            resultBits.append(genaralConfigData.encodeBinary());
        }

        if (privateConfigData == null) {
            // optional parameter, may be null
            LOGGER.info(" privateConfigData not set");
        } else {
            resultBits.append(privateConfigData.encodeBinary());
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
        if (genaralConfigData == null) {
            LOGGER.info("genaralConfigData not set");
        } else {
            element.addContent(genaralConfigData.encodeXML(
                    genaralConfigData.getClass().getSimpleName(), ns));
        }

        if (privateConfigData == null) {
            LOGGER.info("privateConfigData not set");
        } else {
            element.addContent(privateConfigData.encodeXML(
                    privateConfigData.getClass().getSimpleName(), ns));
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
                "SecurityModuleConfiguration misses optional parameter of type GenaralConfigData");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = genaralConfigData.length();
        }

        if ((type != null) && type.equals(GenaralConfigData.TYPENUM)) {
            genaralConfigData = new GenaralConfigData(binary.subList(position,
                        tempLength));
            position += tempLength;
            LOGGER.debug(
                " genaralConfigData is instantiated with GenaralConfigData with length" +
                tempLength);
        } else {
            LOGGER.info(
                "SecurityModuleConfiguration misses optional parameter of type GenaralConfigData");
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
                "SecurityModuleConfiguration misses optional parameter of type PrivateConfigData");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = privateConfigData.length();
        }

        if ((type != null) && type.equals(PrivateConfigData.TYPENUM)) {
            privateConfigData = new PrivateConfigData(binary.subList(position,
                        tempLength));
            position += tempLength;
            LOGGER.debug(
                " privateConfigData is instantiated with PrivateConfigData with length" +
                tempLength);
        } else {
            LOGGER.info(
                "SecurityModuleConfiguration misses optional parameter of type PrivateConfigData");
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
        temp = element.getChild("GenaralConfigData", ns);

        if (temp != null) {
            genaralConfigData = new GenaralConfigData(temp);
            LOGGER.info(
                "setting parameter genaralConfigData for parameter SecurityModuleConfiguration");
        }

        if (temp == null) {
            LOGGER.info(
                "SecurityModuleConfiguration misses optional parameter of type genaralConfigData");
        }

        element.removeChild("GenaralConfigData", ns);
        //parameter - not choices - no special actions needed
        temp = element.getChild("PrivateConfigData", ns);

        if (temp != null) {
            privateConfigData = new PrivateConfigData(temp);
            LOGGER.info(
                "setting parameter privateConfigData for parameter SecurityModuleConfiguration");
        }

        if (temp == null) {
            LOGGER.info(
                "SecurityModuleConfiguration misses optional parameter of type privateConfigData");
        }

        element.removeChild("PrivateConfigData", ns);

        if (element.getChildren().size() > 0) {
            String message = "SecurityModuleConfiguration has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters

    /**
    * set genaralConfigData of type GenaralConfigData.
    * @param  genaralConfigData to be set
    */
    public void setGenaralConfigData(final GenaralConfigData genaralConfigData) {
        this.genaralConfigData = genaralConfigData;
    }

    /**
    * set privateConfigData of type PrivateConfigData.
    * @param  privateConfigData to be set
    */
    public void setPrivateConfigData(final PrivateConfigData privateConfigData) {
        this.privateConfigData = privateConfigData;
    }

    // end setter

    //getters

    /**
    * get genaralConfigData of type GenaralConfigData .
    * @return  GenaralConfigData
    */
    public GenaralConfigData getGenaralConfigData() {
        return genaralConfigData;
    }

    /**
    * get privateConfigData of type PrivateConfigData .
    * @return  PrivateConfigData
    */
    public PrivateConfigData getPrivateConfigData() {
        return privateConfigData;
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
        return "SecurityModuleConfiguration";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "SecurityModuleConfiguration: ";
        result = result.replaceFirst(", ", "");

        return result;
    }
}
