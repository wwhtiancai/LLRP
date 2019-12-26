/*
 *
 * This file was generated by LLRP Code Generator
 * see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit
 * for more information
 * Generated on: Mon Apr 10 16:16:17 CST 2017;
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
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedShort;
import org.llrp.ltk.types.UnsignedShortArray;

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
public class AntennaConfiguration extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(663);
    private static final Logger LOGGER = Logger.getLogger(AntennaConfiguration.class);
    protected UnsignedByte antennaID;
    protected UnsignedShort transmitPowerIndex;
    protected UnsignedShortArray frequencyIndexes;
    protected UnsignedShort forDataRateIndex;
    protected UnsignedShort revDataRateIndex;
    protected UnsignedShort forModulationIndex;
    protected UnsignedShort revDataEncodingIndex;

    /**
     * empty constructor to create new parameter.
     */
    public AntennaConfiguration() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public AntennaConfiguration(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public AntennaConfiguration(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (antennaID == null) {
            LOGGER.warn(" antennaID not set");
            throw new MissingParameterException(
                " antennaID not set  for Parameter of Type AntennaConfiguration");
        }

        resultBits.append(antennaID.encodeBinary());

        if (transmitPowerIndex == null) {
            LOGGER.warn(" transmitPowerIndex not set");
            throw new MissingParameterException(
                " transmitPowerIndex not set  for Parameter of Type AntennaConfiguration");
        }

        resultBits.append(transmitPowerIndex.encodeBinary());

        if (frequencyIndexes == null) {
            LOGGER.warn(" frequencyIndexes not set");
            throw new MissingParameterException(
                " frequencyIndexes not set  for Parameter of Type AntennaConfiguration");
        }

        resultBits.append(frequencyIndexes.encodeBinary());

        if (forDataRateIndex == null) {
            LOGGER.warn(" forDataRateIndex not set");
            throw new MissingParameterException(
                " forDataRateIndex not set  for Parameter of Type AntennaConfiguration");
        }

        resultBits.append(forDataRateIndex.encodeBinary());

        if (revDataRateIndex == null) {
            LOGGER.warn(" revDataRateIndex not set");
            throw new MissingParameterException(
                " revDataRateIndex not set  for Parameter of Type AntennaConfiguration");
        }

        resultBits.append(revDataRateIndex.encodeBinary());

        if (forModulationIndex == null) {
            LOGGER.warn(" forModulationIndex not set");
            throw new MissingParameterException(
                " forModulationIndex not set  for Parameter of Type AntennaConfiguration");
        }

        resultBits.append(forModulationIndex.encodeBinary());

        if (revDataEncodingIndex == null) {
            LOGGER.warn(" revDataEncodingIndex not set");
            throw new MissingParameterException(
                " revDataEncodingIndex not set  for Parameter of Type AntennaConfiguration");
        }

        resultBits.append(revDataEncodingIndex.encodeBinary());

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

        if (antennaID == null) {
            LOGGER.warn(" antennaID not set");
            throw new MissingParameterException(" antennaID not set");
        } else {
            element.addContent(antennaID.encodeXML("AntennaID", ns));
        }

        if (transmitPowerIndex == null) {
            LOGGER.warn(" transmitPowerIndex not set");
            throw new MissingParameterException(" transmitPowerIndex not set");
        } else {
            element.addContent(transmitPowerIndex.encodeXML(
                    "TransmitPowerIndex", ns));
        }

        if (frequencyIndexes == null) {
            LOGGER.warn(" frequencyIndexes not set");
            throw new MissingParameterException(" frequencyIndexes not set");
        } else {
            element.addContent(frequencyIndexes.encodeXML("FrequencyIndexes", ns));
        }

        if (forDataRateIndex == null) {
            LOGGER.warn(" forDataRateIndex not set");
            throw new MissingParameterException(" forDataRateIndex not set");
        } else {
            element.addContent(forDataRateIndex.encodeXML("ForDataRateIndex", ns));
        }

        if (revDataRateIndex == null) {
            LOGGER.warn(" revDataRateIndex not set");
            throw new MissingParameterException(" revDataRateIndex not set");
        } else {
            element.addContent(revDataRateIndex.encodeXML("RevDataRateIndex", ns));
        }

        if (forModulationIndex == null) {
            LOGGER.warn(" forModulationIndex not set");
            throw new MissingParameterException(" forModulationIndex not set");
        } else {
            element.addContent(forModulationIndex.encodeXML(
                    "ForModulationIndex", ns));
        }

        if (revDataEncodingIndex == null) {
            LOGGER.warn(" revDataEncodingIndex not set");
            throw new MissingParameterException(" revDataEncodingIndex not set");
        } else {
            element.addContent(revDataEncodingIndex.encodeXML(
                    "RevDataEncodingIndex", ns));
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
        antennaID = new UnsignedByte(binary.subList(position,
                    UnsignedByte.length()));
        position += UnsignedByte.length();
        transmitPowerIndex = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UnsignedShortArray.length() * fieldCount) +
            UnsignedShort.length();
        frequencyIndexes = new UnsignedShortArray(binary.subList(position,
                    tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedShortArray with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for frequencyIndexes ");
        }

        forDataRateIndex = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        revDataRateIndex = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        forModulationIndex = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        revDataEncodingIndex = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
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

        temp = element.getChild("AntennaID", ns);

        if (temp != null) {
            antennaID = new UnsignedByte(temp);
        }

        element.removeChild("AntennaID", ns);
        temp = element.getChild("TransmitPowerIndex", ns);

        if (temp != null) {
            transmitPowerIndex = new UnsignedShort(temp);
        }

        element.removeChild("TransmitPowerIndex", ns);
        temp = element.getChild("FrequencyIndexes", ns);

        if (temp != null) {
            frequencyIndexes = new UnsignedShortArray(temp);
        }

        element.removeChild("FrequencyIndexes", ns);
        temp = element.getChild("ForDataRateIndex", ns);

        if (temp != null) {
            forDataRateIndex = new UnsignedShort(temp);
        }

        element.removeChild("ForDataRateIndex", ns);
        temp = element.getChild("RevDataRateIndex", ns);

        if (temp != null) {
            revDataRateIndex = new UnsignedShort(temp);
        }

        element.removeChild("RevDataRateIndex", ns);
        temp = element.getChild("ForModulationIndex", ns);

        if (temp != null) {
            forModulationIndex = new UnsignedShort(temp);
        }

        element.removeChild("ForModulationIndex", ns);
        temp = element.getChild("RevDataEncodingIndex", ns);

        if (temp != null) {
            revDataEncodingIndex = new UnsignedShort(temp);
        }

        element.removeChild("RevDataEncodingIndex", ns);

        if (element.getChildren().size() > 0) {
            String message = "AntennaConfiguration has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   antennaID of type UnsignedByte .
    * @param   antennaID to be set
    */
    public void setAntennaID(final UnsignedByte antennaID) {
        this.antennaID = antennaID;
    }

    /**
    * set   transmitPowerIndex of type UnsignedShort .
    * @param   transmitPowerIndex to be set
    */
    public void setTransmitPowerIndex(final UnsignedShort transmitPowerIndex) {
        this.transmitPowerIndex = transmitPowerIndex;
    }

    /**
    * set   frequencyIndexes of type UnsignedShortArray .
    * @param   frequencyIndexes to be set
    */
    public void setFrequencyIndexes(final UnsignedShortArray frequencyIndexes) {
        this.frequencyIndexes = frequencyIndexes;
    }

    /**
    * set   forDataRateIndex of type UnsignedShort .
    * @param   forDataRateIndex to be set
    */
    public void setForDataRateIndex(final UnsignedShort forDataRateIndex) {
        this.forDataRateIndex = forDataRateIndex;
    }

    /**
    * set   revDataRateIndex of type UnsignedShort .
    * @param   revDataRateIndex to be set
    */
    public void setRevDataRateIndex(final UnsignedShort revDataRateIndex) {
        this.revDataRateIndex = revDataRateIndex;
    }

    /**
    * set   forModulationIndex of type UnsignedShort .
    * @param   forModulationIndex to be set
    */
    public void setForModulationIndex(final UnsignedShort forModulationIndex) {
        this.forModulationIndex = forModulationIndex;
    }

    /**
    * set   revDataEncodingIndex of type UnsignedShort .
    * @param   revDataEncodingIndex to be set
    */
    public void setRevDataEncodingIndex(
        final UnsignedShort revDataEncodingIndex) {
        this.revDataEncodingIndex = revDataEncodingIndex;
    }

    // end setter

    //getters
    /**
    * get   antennaID of type UnsignedByte.
    * @return   type UnsignedByte to be set
    */
    public UnsignedByte getAntennaID() {
        return this.antennaID;
    }

    /**
    * get   transmitPowerIndex of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getTransmitPowerIndex() {
        return this.transmitPowerIndex;
    }

    /**
    * get   frequencyIndexes of type UnsignedShortArray.
    * @return   type UnsignedShortArray to be set
    */
    public UnsignedShortArray getFrequencyIndexes() {
        return this.frequencyIndexes;
    }

    /**
    * get   forDataRateIndex of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getForDataRateIndex() {
        return this.forDataRateIndex;
    }

    /**
    * get   revDataRateIndex of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getRevDataRateIndex() {
        return this.revDataRateIndex;
    }

    /**
    * get   forModulationIndex of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getForModulationIndex() {
        return this.forModulationIndex;
    }

    /**
    * get   revDataEncodingIndex of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getRevDataEncodingIndex() {
        return this.revDataEncodingIndex;
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
        return "AntennaConfiguration";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "AntennaConfiguration: ";
        result += ", antennaID: ";
        result += antennaID;
        result += ", transmitPowerIndex: ";
        result += transmitPowerIndex;
        result += ", frequencyIndexes: ";
        result += frequencyIndexes;
        result += ", forDataRateIndex: ";
        result += forDataRateIndex;
        result += ", revDataRateIndex: ";
        result += revDataRateIndex;
        result += ", forModulationIndex: ";
        result += forModulationIndex;
        result += ", revDataEncodingIndex: ";
        result += revDataEncodingIndex;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
