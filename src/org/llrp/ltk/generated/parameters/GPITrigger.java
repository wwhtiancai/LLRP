/*
 *
 * This file was generated by LLRP Code Generator
 * see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit
 * for more information
 * Generated on: Mon Apr 10 16:16:16 CST 2017;
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
import org.llrp.ltk.generated.enumerations.GPIEventType;
import org.llrp.ltk.types.BitList;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedInteger;
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
public class GPITrigger extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(403);
    private static final Logger LOGGER = Logger.getLogger(GPITrigger.class);
    protected UnsignedByte gPIPortNum;
    protected GPIEventType gPIEvent;
    protected BitList reserved0 = new BitList(7);
    protected UnsignedInteger timeout;

    /**
     * empty constructor to create new parameter.
     */
    public GPITrigger() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public GPITrigger(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public GPITrigger(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (gPIPortNum == null) {
            LOGGER.warn(" gPIPortNum not set");
            throw new MissingParameterException(
                " gPIPortNum not set  for Parameter of Type GPITrigger");
        }

        resultBits.append(gPIPortNum.encodeBinary());

        if (gPIEvent == null) {
            LOGGER.warn(" gPIEvent not set");
            throw new MissingParameterException(
                " gPIEvent not set  for Parameter of Type GPITrigger");
        }

        resultBits.append(gPIEvent.encodeBinary());
        resultBits.append(reserved0.encodeBinary());

        if (timeout == null) {
            LOGGER.warn(" timeout not set");
            throw new MissingParameterException(
                " timeout not set  for Parameter of Type GPITrigger");
        }

        resultBits.append(timeout.encodeBinary());

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

        if (gPIPortNum == null) {
            LOGGER.warn(" gPIPortNum not set");
            throw new MissingParameterException(" gPIPortNum not set");
        } else {
            element.addContent(gPIPortNum.encodeXML("GPIPortNum", ns));
        }

        if (gPIEvent == null) {
            LOGGER.warn(" gPIEvent not set");
            throw new MissingParameterException(" gPIEvent not set");
        } else {
            element.addContent(gPIEvent.encodeXML("GPIEvent", ns));
        }

        //element.addContent(reserved0.encodeXML("reserved",ns));
        if (timeout == null) {
            LOGGER.warn(" timeout not set");
            throw new MissingParameterException(" timeout not set");
        } else {
            element.addContent(timeout.encodeXML("Timeout", ns));
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
        gPIPortNum = new UnsignedByte(binary.subList(position,
                    UnsignedByte.length()));
        position += UnsignedByte.length();
        gPIEvent = new GPIEventType(binary.subList(position,
                    GPIEventType.length()));
        position += GPIEventType.length();
        position += reserved0.length();
        timeout = new UnsignedInteger(binary.subList(position,
                    UnsignedInteger.length()));
        position += UnsignedInteger.length();
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

        temp = element.getChild("GPIPortNum", ns);

        if (temp != null) {
            gPIPortNum = new UnsignedByte(temp);
        }

        element.removeChild("GPIPortNum", ns);
        temp = element.getChild("GPIEvent", ns);

        if (temp != null) {
            gPIEvent = new GPIEventType(temp);
        }

        element.removeChild("GPIEvent", ns);
        temp = element.getChild("Timeout", ns);

        if (temp != null) {
            timeout = new UnsignedInteger(temp);
        }

        element.removeChild("Timeout", ns);

        if (element.getChildren().size() > 0) {
            String message = "GPITrigger has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   gPIPortNum of type UnsignedByte .
    * @param   gPIPortNum to be set
    */
    public void setGPIPortNum(final UnsignedByte gPIPortNum) {
        this.gPIPortNum = gPIPortNum;
    }

    /**
    * set gPIEvent of type GPIEventType .
    * @param  gPIEvent to be set
    */
    public void setGPIEvent(final GPIEventType gPIEvent) {
        this.gPIEvent = gPIEvent;
    }

    /**
    * set   timeout of type UnsignedInteger .
    * @param   timeout to be set
    */
    public void setTimeout(final UnsignedInteger timeout) {
        this.timeout = timeout;
    }

    // end setter

    //getters
    /**
    * get   gPIPortNum of type UnsignedByte.
    * @return   type UnsignedByte to be set
    */
    public UnsignedByte getGPIPortNum() {
        return this.gPIPortNum;
    }

    /**
    * get gPIEvent of type GPIEventType.
    * @return  GPIEventType
    */
    public GPIEventType getGPIEvent() {
        return gPIEvent;
    }

    /**
    * get   timeout of type UnsignedInteger.
    * @return   type UnsignedInteger to be set
    */
    public UnsignedInteger getTimeout() {
        return this.timeout;
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
        return "GPITrigger";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "GPITrigger: ";
        result += ", gPIPortNum: ";
        result += gPIPortNum;
        result += ", gPIEvent: ";
        result += gPIEvent;

        result += ", timeout: ";
        result += timeout;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
