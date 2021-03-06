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
import org.llrp.ltk.generated.parameters.DiagnosticTestResultAntennaConnected;
import org.llrp.ltk.generated.parameters.DiagnosticTestResultAntennaVSWR;
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
public class DiagnosticTestResultItem extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(741);
    private static final Logger LOGGER = Logger.getLogger(DiagnosticTestResultItem.class);
    protected UnsignedShort diagnosticTestID;
    protected UnsignedInteger diagnosticTestResultCode;
    protected DiagnosticTestResultData diagnosticTestResultData;

    /**
     * empty constructor to create new parameter.
     */
    public DiagnosticTestResultItem() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public DiagnosticTestResultItem(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public DiagnosticTestResultItem(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (diagnosticTestID == null) {
            LOGGER.warn(" diagnosticTestID not set");
            throw new MissingParameterException(
                " diagnosticTestID not set  for Parameter of Type DiagnosticTestResultItem");
        }

        resultBits.append(diagnosticTestID.encodeBinary());

        if (diagnosticTestResultCode == null) {
            LOGGER.warn(" diagnosticTestResultCode not set");
            throw new MissingParameterException(
                " diagnosticTestResultCode not set  for Parameter of Type DiagnosticTestResultItem");
        }

        resultBits.append(diagnosticTestResultCode.encodeBinary());

        if (diagnosticTestResultData == null) {
            // optional parameter, may be null
            LOGGER.info(" diagnosticTestResultData not set");
        } else {
            resultBits.append(diagnosticTestResultData.encodeBinary());
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

        if (diagnosticTestID == null) {
            LOGGER.warn(" diagnosticTestID not set");
            throw new MissingParameterException(" diagnosticTestID not set");
        } else {
            element.addContent(diagnosticTestID.encodeXML("DiagnosticTestID", ns));
        }

        if (diagnosticTestResultCode == null) {
            LOGGER.warn(" diagnosticTestResultCode not set");
            throw new MissingParameterException(
                " diagnosticTestResultCode not set");
        } else {
            element.addContent(diagnosticTestResultCode.encodeXML(
                    "DiagnosticTestResultCode", ns));
        }

        //parameters
        if (diagnosticTestResultData == null) {
            LOGGER.info("diagnosticTestResultData not set");
        } else {
            element.addContent(diagnosticTestResultData.encodeXML(
                    diagnosticTestResultData.getClass().getSimpleName(), ns));
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
        diagnosticTestID = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        diagnosticTestResultCode = new UnsignedInteger(binary.subList(
                    position, UnsignedInteger.length()));
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
                "DiagnosticTestResultItem misses optional parameter of type DiagnosticTestResultData");
        }

        boolean found = false;
        LOGGER.debug("decoding choice type DiagnosticTestResultData ");

        //if first bit is 1 it is a TV Parameter
        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = DiagnosticTestResultAntennaConnected.length();
        }

        if ((type != null) &&
                type.equals(DiagnosticTestResultAntennaConnected.TYPENUM)) {
            diagnosticTestResultData = new DiagnosticTestResultAntennaConnected(binary.subList(
                        position, tempLength));
            LOGGER.debug(
                " diagnosticTestResultData instatiated to DiagnosticTestResultAntennaConnected with length " +
                tempLength);
            position += tempLength;
            found = true;
        }

        //if first bit is 1 it is a TV Parameter
        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = DiagnosticTestResultAntennaVSWR.length();
        }

        if ((type != null) &&
                type.equals(DiagnosticTestResultAntennaVSWR.TYPENUM)) {
            diagnosticTestResultData = new DiagnosticTestResultAntennaVSWR(binary.subList(
                        position, tempLength));
            LOGGER.debug(
                " diagnosticTestResultData instatiated to DiagnosticTestResultAntennaVSWR with length " +
                tempLength);
            position += tempLength;
            found = true;
        }

        if (!found) {
            LOGGER.info(
                "encoded message misses optional parameter diagnosticTestResultData");
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

        temp = element.getChild("DiagnosticTestID", ns);

        if (temp != null) {
            diagnosticTestID = new UnsignedShort(temp);
        }

        element.removeChild("DiagnosticTestID", ns);
        temp = element.getChild("DiagnosticTestResultCode", ns);

        if (temp != null) {
            diagnosticTestResultCode = new UnsignedInteger(temp);
        }

        element.removeChild("DiagnosticTestResultCode", ns);

        //choices - must check all possible subtypes
        boolean found = false;
        LOGGER.debug("decoding choice type DiagnosticTestResultData ");
        // try to get child for each possible subtype
        temp = element.getChild("DiagnosticTestResultAntennaConnected", ns);

        if (temp != null) {
            diagnosticTestResultData = new DiagnosticTestResultAntennaConnected(temp);
            LOGGER.debug(
                " diagnosticTestResultData instatiated to DiagnosticTestResultAntennaConnected with");
            found = true;
        }

        element.removeChild("DiagnosticTestResultAntennaConnected", ns);
        // try to get child for each possible subtype
        temp = element.getChild("DiagnosticTestResultAntennaVSWR", ns);

        if (temp != null) {
            diagnosticTestResultData = new DiagnosticTestResultAntennaVSWR(temp);
            LOGGER.debug(
                " diagnosticTestResultData instatiated to DiagnosticTestResultAntennaVSWR with");
            found = true;
        }

        element.removeChild("DiagnosticTestResultAntennaVSWR", ns);

        if (!found) {
            LOGGER.info(
                "DiagnosticTestResultItem misses optional parameter of type diagnosticTestResultDataList");
        }

        if (element.getChildren().size() > 0) {
            String message = "DiagnosticTestResultItem has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   diagnosticTestID of type UnsignedShort .
    * @param   diagnosticTestID to be set
    */
    public void setDiagnosticTestID(final UnsignedShort diagnosticTestID) {
        this.diagnosticTestID = diagnosticTestID;
    }

    /**
    * set   diagnosticTestResultCode of type UnsignedInteger .
    * @param   diagnosticTestResultCode to be set
    */
    public void setDiagnosticTestResultCode(
        final UnsignedInteger diagnosticTestResultCode) {
        this.diagnosticTestResultCode = diagnosticTestResultCode;
    }

    /**
    * set diagnosticTestResultData of type DiagnosticTestResultData.
    * @param  diagnosticTestResultData to be set
    */
    public void setDiagnosticTestResultData(
        final DiagnosticTestResultData diagnosticTestResultData) {
        this.diagnosticTestResultData = diagnosticTestResultData;
    }

    // end setter

    //getters
    /**
    * get   diagnosticTestID of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getDiagnosticTestID() {
        return this.diagnosticTestID;
    }

    /**
    * get   diagnosticTestResultCode of type UnsignedInteger.
    * @return   type UnsignedInteger to be set
    */
    public UnsignedInteger getDiagnosticTestResultCode() {
        return this.diagnosticTestResultCode;
    }

    /**
    * get diagnosticTestResultData of type DiagnosticTestResultData .
    * @return  DiagnosticTestResultData
    */
    public DiagnosticTestResultData getDiagnosticTestResultData() {
        return diagnosticTestResultData;
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
        return "DiagnosticTestResultItem";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "DiagnosticTestResultItem: ";
        result += ", diagnosticTestID: ";
        result += diagnosticTestID;
        result += ", diagnosticTestResultCode: ";
        result += diagnosticTestResultCode;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
