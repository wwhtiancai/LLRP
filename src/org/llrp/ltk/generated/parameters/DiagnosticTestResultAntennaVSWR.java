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
import org.llrp.ltk.generated.interfaces.DiagnosticTestResultData;
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
public class DiagnosticTestResultAntennaVSWR extends TLVParameter
    implements DiagnosticTestResultData {
    public static final SignedShort TYPENUM = new SignedShort(743);
    private static final Logger LOGGER = Logger.getLogger(DiagnosticTestResultAntennaVSWR.class);
    protected UnsignedByte antennaID;
    protected UnsignedShort antennaVSWR;

    /**
     * empty constructor to create new parameter.
     */
    public DiagnosticTestResultAntennaVSWR() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public DiagnosticTestResultAntennaVSWR(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public DiagnosticTestResultAntennaVSWR(Element element)
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
                " antennaID not set  for Parameter of Type DiagnosticTestResultAntennaVSWR");
        }

        resultBits.append(antennaID.encodeBinary());

        if (antennaVSWR == null) {
            LOGGER.warn(" antennaVSWR not set");
            throw new MissingParameterException(
                " antennaVSWR not set  for Parameter of Type DiagnosticTestResultAntennaVSWR");
        }

        resultBits.append(antennaVSWR.encodeBinary());

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

        if (antennaVSWR == null) {
            LOGGER.warn(" antennaVSWR not set");
            throw new MissingParameterException(" antennaVSWR not set");
        } else {
            element.addContent(antennaVSWR.encodeXML("AntennaVSWR", ns));
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
        antennaVSWR = new UnsignedShort(binary.subList(position,
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
        temp = element.getChild("AntennaVSWR", ns);

        if (temp != null) {
            antennaVSWR = new UnsignedShort(temp);
        }

        element.removeChild("AntennaVSWR", ns);

        if (element.getChildren().size() > 0) {
            String message = "DiagnosticTestResultAntennaVSWR has unknown element " +
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
    * set   antennaVSWR of type UnsignedShort .
    * @param   antennaVSWR to be set
    */
    public void setAntennaVSWR(final UnsignedShort antennaVSWR) {
        this.antennaVSWR = antennaVSWR;
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
    * get   antennaVSWR of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getAntennaVSWR() {
        return this.antennaVSWR;
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
        return "DiagnosticTestResultAntennaVSWR";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "DiagnosticTestResultAntennaVSWR: ";
        result += ", antennaID: ";
        result += antennaID;
        result += ", antennaVSWR: ";
        result += antennaVSWR;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
