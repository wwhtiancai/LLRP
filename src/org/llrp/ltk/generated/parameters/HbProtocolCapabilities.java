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
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.BitList;
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
public class HbProtocolCapabilities extends TLVParameter
    implements DeviceAirProtocolCapabilities {
    public static final SignedShort TYPENUM = new SignedShort(368);
    private static final Logger LOGGER = Logger.getLogger(HbProtocolCapabilities.class);
    protected Bit supportQuery;
    protected Bit supportReadData;
    protected Bit supportWriteData;
    protected Bit supportWriteKey;
    protected BitList reserved0 = new BitList(4);

    /**
     * empty constructor to create new parameter.
     */
    public HbProtocolCapabilities() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public HbProtocolCapabilities(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public HbProtocolCapabilities(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (supportQuery == null) {
            LOGGER.warn(" supportQuery not set");
            throw new MissingParameterException(
                " supportQuery not set  for Parameter of Type HbProtocolCapabilities");
        }

        resultBits.append(supportQuery.encodeBinary());

        if (supportReadData == null) {
            LOGGER.warn(" supportReadData not set");
            throw new MissingParameterException(
                " supportReadData not set  for Parameter of Type HbProtocolCapabilities");
        }

        resultBits.append(supportReadData.encodeBinary());

        if (supportWriteData == null) {
            LOGGER.warn(" supportWriteData not set");
            throw new MissingParameterException(
                " supportWriteData not set  for Parameter of Type HbProtocolCapabilities");
        }

        resultBits.append(supportWriteData.encodeBinary());

        if (supportWriteKey == null) {
            LOGGER.warn(" supportWriteKey not set");
            throw new MissingParameterException(
                " supportWriteKey not set  for Parameter of Type HbProtocolCapabilities");
        }

        resultBits.append(supportWriteKey.encodeBinary());
        resultBits.append(reserved0.encodeBinary());

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

        if (supportQuery == null) {
            LOGGER.warn(" supportQuery not set");
            throw new MissingParameterException(" supportQuery not set");
        } else {
            element.addContent(supportQuery.encodeXML("SupportQuery", ns));
        }

        if (supportReadData == null) {
            LOGGER.warn(" supportReadData not set");
            throw new MissingParameterException(" supportReadData not set");
        } else {
            element.addContent(supportReadData.encodeXML("SupportReadData", ns));
        }

        if (supportWriteData == null) {
            LOGGER.warn(" supportWriteData not set");
            throw new MissingParameterException(" supportWriteData not set");
        } else {
            element.addContent(supportWriteData.encodeXML("SupportWriteData", ns));
        }

        if (supportWriteKey == null) {
            LOGGER.warn(" supportWriteKey not set");
            throw new MissingParameterException(" supportWriteKey not set");
        } else {
            element.addContent(supportWriteKey.encodeXML("SupportWriteKey", ns));
        }

        //element.addContent(reserved0.encodeXML("reserved",ns));
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
        supportQuery = new Bit(binary.subList(position, Bit.length()));
        position += Bit.length();
        supportReadData = new Bit(binary.subList(position, Bit.length()));
        position += Bit.length();
        supportWriteData = new Bit(binary.subList(position, Bit.length()));
        position += Bit.length();
        supportWriteKey = new Bit(binary.subList(position, Bit.length()));
        position += Bit.length();
        position += reserved0.length();
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

        temp = element.getChild("SupportQuery", ns);

        if (temp != null) {
            supportQuery = new Bit(temp);
        }

        element.removeChild("SupportQuery", ns);
        temp = element.getChild("SupportReadData", ns);

        if (temp != null) {
            supportReadData = new Bit(temp);
        }

        element.removeChild("SupportReadData", ns);
        temp = element.getChild("SupportWriteData", ns);

        if (temp != null) {
            supportWriteData = new Bit(temp);
        }

        element.removeChild("SupportWriteData", ns);
        temp = element.getChild("SupportWriteKey", ns);

        if (temp != null) {
            supportWriteKey = new Bit(temp);
        }

        element.removeChild("SupportWriteKey", ns);

        if (element.getChildren().size() > 0) {
            String message = "HbProtocolCapabilities has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   supportQuery of type Bit .
    * @param   supportQuery to be set
    */
    public void setSupportQuery(final Bit supportQuery) {
        this.supportQuery = supportQuery;
    }

    /**
    * set   supportReadData of type Bit .
    * @param   supportReadData to be set
    */
    public void setSupportReadData(final Bit supportReadData) {
        this.supportReadData = supportReadData;
    }

    /**
    * set   supportWriteData of type Bit .
    * @param   supportWriteData to be set
    */
    public void setSupportWriteData(final Bit supportWriteData) {
        this.supportWriteData = supportWriteData;
    }

    /**
    * set   supportWriteKey of type Bit .
    * @param   supportWriteKey to be set
    */
    public void setSupportWriteKey(final Bit supportWriteKey) {
        this.supportWriteKey = supportWriteKey;
    }

    // end setter

    //getters
    /**
    * get   supportQuery of type Bit.
    * @return   type Bit to be set
    */
    public Bit getSupportQuery() {
        return this.supportQuery;
    }

    /**
    * get   supportReadData of type Bit.
    * @return   type Bit to be set
    */
    public Bit getSupportReadData() {
        return this.supportReadData;
    }

    /**
    * get   supportWriteData of type Bit.
    * @return   type Bit to be set
    */
    public Bit getSupportWriteData() {
        return this.supportWriteData;
    }

    /**
    * get   supportWriteKey of type Bit.
    * @return   type Bit to be set
    */
    public Bit getSupportWriteKey() {
        return this.supportWriteKey;
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
        return "HbProtocolCapabilities";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "HbProtocolCapabilities: ";
        result += ", supportQuery: ";
        result += supportQuery;
        result += ", supportReadData: ";
        result += supportReadData;
        result += ", supportWriteData: ";
        result += supportWriteData;
        result += ", supportWriteKey: ";
        result += supportWriteKey;

        result = result.replaceFirst(", ", "");

        return result;
    }
}