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
import org.llrp.ltk.generated.enumerations.IPAddressVersion;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedIntegerArray;
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
public class IPAddress extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(673);
    private static final Logger LOGGER = Logger.getLogger(IPAddress.class);
    protected IPAddressVersion version;
    protected UnsignedIntegerArray address;

    /**
     * empty constructor to create new parameter.
     */
    public IPAddress() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public IPAddress(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public IPAddress(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (version == null) {
            LOGGER.warn(" version not set");
            throw new MissingParameterException(
                " version not set  for Parameter of Type IPAddress");
        }

        resultBits.append(version.encodeBinary());

        if (address == null) {
            LOGGER.warn(" address not set");
            throw new MissingParameterException(
                " address not set  for Parameter of Type IPAddress");
        }

        resultBits.append(address.encodeBinary());

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

        if (version == null) {
            LOGGER.warn(" version not set");
            throw new MissingParameterException(" version not set");
        } else {
            element.addContent(version.encodeXML("Version", ns));
        }

        if (address == null) {
            LOGGER.warn(" address not set");
            throw new MissingParameterException(" address not set");
        } else {
            element.addContent(address.encodeXML("Address", ns));
        }

        //parameters
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
        version = new IPAddressVersion(binary.subList(position,
                    IPAddressVersion.length()));
        position += IPAddressVersion.length();
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UnsignedIntegerArray.length() * fieldCount) +
            UnsignedShort.length();
        address = new UnsignedIntegerArray(binary.subList(position, tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedIntegerArray with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for address ");
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

        temp = element.getChild("Version", ns);

        if (temp != null) {
            version = new IPAddressVersion(temp);
        }

        element.removeChild("Version", ns);
        temp = element.getChild("Address", ns);

        if (temp != null) {
            address = new UnsignedIntegerArray(temp);
        }

        element.removeChild("Address", ns);

        if (element.getChildren().size() > 0) {
            String message = "IPAddress has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set version of type IPAddressVersion .
    * @param  version to be set
    */
    public void setVersion(final IPAddressVersion version) {
        this.version = version;
    }

    /**
    * set   address of type UnsignedIntegerArray .
    * @param   address to be set
    */
    public void setAddress(final UnsignedIntegerArray address) {
        this.address = address;
    }

    // end setter

    //getters
    /**
    * get version of type IPAddressVersion.
    * @return  IPAddressVersion
    */
    public IPAddressVersion getVersion() {
        return version;
    }

    /**
    * get   address of type UnsignedIntegerArray.
    * @return   type UnsignedIntegerArray to be set
    */
    public UnsignedIntegerArray getAddress() {
        return this.address;
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
        return "IPAddress";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "IPAddress: ";
        result += ", version: ";
        result += version;
        result += ", address: ";
        result += address;
        result = result.replaceFirst(", ", "");

        return result;
    }
}