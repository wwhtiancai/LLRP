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
import org.llrp.ltk.generated.enumerations.SelectSpecStopTriggerType;
import org.llrp.ltk.generated.parameters.GPITrigger;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
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
public class SelectSpecStopTrigger extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(404);
    private static final Logger LOGGER = Logger.getLogger(SelectSpecStopTrigger.class);
    protected SelectSpecStopTriggerType selectSpecStopTriggerType;
    protected UnsignedInteger durationValue;
    protected GPITrigger gPITrigger;

    /**
     * empty constructor to create new parameter.
     */
    public SelectSpecStopTrigger() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public SelectSpecStopTrigger(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public SelectSpecStopTrigger(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (selectSpecStopTriggerType == null) {
            LOGGER.warn(" selectSpecStopTriggerType not set");
            throw new MissingParameterException(
                " selectSpecStopTriggerType not set  for Parameter of Type SelectSpecStopTrigger");
        }

        resultBits.append(selectSpecStopTriggerType.encodeBinary());

        if (durationValue == null) {
            LOGGER.warn(" durationValue not set");
            throw new MissingParameterException(
                " durationValue not set  for Parameter of Type SelectSpecStopTrigger");
        }

        resultBits.append(durationValue.encodeBinary());

        if (gPITrigger == null) {
            // optional parameter, may be null
            LOGGER.info(" gPITrigger not set");
        } else {
            resultBits.append(gPITrigger.encodeBinary());
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

        if (selectSpecStopTriggerType == null) {
            LOGGER.warn(" selectSpecStopTriggerType not set");
            throw new MissingParameterException(
                " selectSpecStopTriggerType not set");
        } else {
            element.addContent(selectSpecStopTriggerType.encodeXML(
                    "SelectSpecStopTriggerType", ns));
        }

        if (durationValue == null) {
            LOGGER.warn(" durationValue not set");
            throw new MissingParameterException(" durationValue not set");
        } else {
            element.addContent(durationValue.encodeXML("DurationValue", ns));
        }

        //parameters
        if (gPITrigger == null) {
            LOGGER.info("gPITrigger not set");
        } else {
            element.addContent(gPITrigger.encodeXML(gPITrigger.getClass()
                                                              .getSimpleName(),
                    ns));
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
        selectSpecStopTriggerType = new SelectSpecStopTriggerType(binary.subList(
                    position, SelectSpecStopTriggerType.length()));
        position += SelectSpecStopTriggerType.length();
        durationValue = new UnsignedInteger(binary.subList(position,
                    UnsignedInteger.length()));
        position += UnsignedInteger.length();

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
                "SelectSpecStopTrigger misses optional parameter of type GPITrigger");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = gPITrigger.length();
        }

        if ((type != null) && type.equals(GPITrigger.TYPENUM)) {
            gPITrigger = new GPITrigger(binary.subList(position, tempLength));
            position += tempLength;
            LOGGER.debug(
                " gPITrigger is instantiated with GPITrigger with length" +
                tempLength);
        } else {
            LOGGER.info(
                "SelectSpecStopTrigger misses optional parameter of type GPITrigger");
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

        temp = element.getChild("SelectSpecStopTriggerType", ns);

        if (temp != null) {
            selectSpecStopTriggerType = new SelectSpecStopTriggerType(temp);
        }

        element.removeChild("SelectSpecStopTriggerType", ns);
        temp = element.getChild("DurationValue", ns);

        if (temp != null) {
            durationValue = new UnsignedInteger(temp);
        }

        element.removeChild("DurationValue", ns);

        //parameter - not choices - no special actions needed
        temp = element.getChild("GPITrigger", ns);

        if (temp != null) {
            gPITrigger = new GPITrigger(temp);
            LOGGER.info(
                "setting parameter gPITrigger for parameter SelectSpecStopTrigger");
        }

        if (temp == null) {
            LOGGER.info(
                "SelectSpecStopTrigger misses optional parameter of type gPITrigger");
        }

        element.removeChild("GPITrigger", ns);

        if (element.getChildren().size() > 0) {
            String message = "SelectSpecStopTrigger has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set selectSpecStopTriggerType of type SelectSpecStopTriggerType .
    * @param  selectSpecStopTriggerType to be set
    */
    public void setSelectSpecStopTriggerType(
        final SelectSpecStopTriggerType selectSpecStopTriggerType) {
        this.selectSpecStopTriggerType = selectSpecStopTriggerType;
    }

    /**
    * set   durationValue of type UnsignedInteger .
    * @param   durationValue to be set
    */
    public void setDurationValue(final UnsignedInteger durationValue) {
        this.durationValue = durationValue;
    }

    /**
    * set gPITrigger of type GPITrigger.
    * @param  gPITrigger to be set
    */
    public void setGPITrigger(final GPITrigger gPITrigger) {
        this.gPITrigger = gPITrigger;
    }

    // end setter

    //getters
    /**
    * get selectSpecStopTriggerType of type SelectSpecStopTriggerType.
    * @return  SelectSpecStopTriggerType
    */
    public SelectSpecStopTriggerType getSelectSpecStopTriggerType() {
        return selectSpecStopTriggerType;
    }

    /**
    * get   durationValue of type UnsignedInteger.
    * @return   type UnsignedInteger to be set
    */
    public UnsignedInteger getDurationValue() {
        return this.durationValue;
    }

    /**
    * get gPITrigger of type GPITrigger .
    * @return  GPITrigger
    */
    public GPITrigger getGPITrigger() {
        return gPITrigger;
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
        return "SelectSpecStopTrigger";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "SelectSpecStopTrigger: ";
        result += ", selectSpecStopTriggerType: ";
        result += selectSpecStopTriggerType;
        result += ", durationValue: ";
        result += durationValue;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
