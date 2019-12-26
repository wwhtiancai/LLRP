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
import org.llrp.ltk.generated.parameters.SerialAttribute;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedByte;
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
public class SupportRS232 extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(353);
    private static final Logger LOGGER = Logger.getLogger(SupportRS232.class);
    protected UnsignedByte portNum;
    protected List<SerialAttribute> serialAttributeList = new LinkedList<SerialAttribute>();

    /**
     * empty constructor to create new parameter.
     */
    public SupportRS232() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public SupportRS232(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public SupportRS232(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (portNum == null) {
            LOGGER.warn(" portNum not set");
            throw new MissingParameterException(
                " portNum not set  for Parameter of Type SupportRS232");
        }

        resultBits.append(portNum.encodeBinary());

        if (serialAttributeList == null) {
            LOGGER.warn(" serialAttributeList not set");

            //parameter has to be set - throw exception
            throw new MissingParameterException(" serialAttributeList not set");
        } else {
            for (SerialAttribute field : serialAttributeList) {
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

        if (portNum == null) {
            LOGGER.warn(" portNum not set");
            throw new MissingParameterException(" portNum not set");
        } else {
            element.addContent(portNum.encodeXML("PortNum", ns));
        }

        //parameters
        if (serialAttributeList == null) {
            LOGGER.warn(" serialAttributeList not set");
            throw new MissingParameterException("  serialAttributeList not set");
        }

        for (SerialAttribute field : serialAttributeList) {
            element.addContent(field.encodeXML(field.getClass().getName()
                                                    .replaceAll(field.getClass()
                                                                     .getPackage()
                                                                     .getName() +
                        ".", ""), ns));
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
        portNum = new UnsignedByte(binary.subList(position,
                    UnsignedByte.length()));
        position += UnsignedByte.length();

        // list of parameters
        serialAttributeList = new LinkedList<SerialAttribute>();
        LOGGER.debug("decoding parameter serialAttributeList ");

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
            if ((type != null) && type.equals(SerialAttribute.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = SerialAttribute.length();
                }

                serialAttributeList.add(new SerialAttribute(binary.subList(
                            position, tempLength)));
                LOGGER.debug("adding SerialAttribute to serialAttributeList ");
                atLeastOnce = true;
                position += tempLength;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (serialAttributeList.isEmpty()) {
            LOGGER.warn(
                "encoded message does not contain parameter for non optional serialAttributeList");
            throw new MissingParameterException(
                "SupportRS232 misses non optional parameter of type SerialAttribute");
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

        temp = element.getChild("PortNum", ns);

        if (temp != null) {
            portNum = new UnsignedByte(temp);
        }

        element.removeChild("PortNum", ns);

        //parameter - not choices - no special actions needed
        //we expect a list of parameters
        serialAttributeList = new LinkedList<SerialAttribute>();
        tempList = element.getChildren("SerialAttribute", ns);

        if ((tempList == null) || tempList.isEmpty()) {
            LOGGER.warn(
                "SupportRS232 misses non optional parameter of type serialAttributeList");
            throw new MissingParameterException(
                "SupportRS232 misses non optional parameter of type serialAttributeList");
        } else {
            for (Element e : tempList) {
                serialAttributeList.add(new SerialAttribute(e));
                LOGGER.debug("adding SerialAttribute to serialAttributeList ");
            }
        }

        element.removeChildren("SerialAttribute", ns);

        if (element.getChildren().size() > 0) {
            String message = "SupportRS232 has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   portNum of type UnsignedByte .
    * @param   portNum to be set
    */
    public void setPortNum(final UnsignedByte portNum) {
        this.portNum = portNum;
    }

    /**
    * set serialAttributeList of type  List &lt;SerialAttribute>.
    * @param  serialAttributeList to be set
    */
    public void setSerialAttributeList(
        final List<SerialAttribute> serialAttributeList) {
        this.serialAttributeList = serialAttributeList;
    }

    // end setter

    //getters
    /**
    * get   portNum of type UnsignedByte.
    * @return   type UnsignedByte to be set
    */
    public UnsignedByte getPortNum() {
        return this.portNum;
    }

    /**
    * get serialAttributeList of type List &lt;SerialAttribute> .
    * @return  List &lt;SerialAttribute>
    */
    public List<SerialAttribute> getSerialAttributeList() {
        return serialAttributeList;
    }

    // end getters

    //add methods

    /**
    * add element serialAttribute of type SerialAttribute .
    * @param  serialAttribute of type SerialAttribute
    */
    public void addToSerialAttributeList(SerialAttribute serialAttribute) {
        if (this.serialAttributeList == null) {
            this.serialAttributeList = new LinkedList<SerialAttribute>();
        }

        this.serialAttributeList.add(serialAttribute);
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
        return "SupportRS232";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "SupportRS232: ";
        result += ", portNum: ";
        result += portNum;
        result = result.replaceFirst(", ", "");

        return result;
    }
}