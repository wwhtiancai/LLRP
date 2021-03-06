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
import org.llrp.ltk.generated.interfaces.AccessSpecResult;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray_HEX;
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
public class HbPrivateWriteSpecResult extends TLVParameter
    implements AccessSpecResult {
    public static final SignedShort TYPENUM = new SignedShort(514);
    private static final Logger LOGGER = Logger.getLogger(HbPrivateWriteSpecResult.class);
    protected UnsignedByte result;
    protected UnsignedByteArray_HEX resultDescription;
    protected UnsignedShort opSpecID;

    /**
     * empty constructor to create new parameter.
     */
    public HbPrivateWriteSpecResult() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public HbPrivateWriteSpecResult(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public HbPrivateWriteSpecResult(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (result == null) {
            LOGGER.warn(" result not set");
            throw new MissingParameterException(
                " result not set  for Parameter of Type HbPrivateWriteSpecResult");
        }

        resultBits.append(result.encodeBinary());

        if (resultDescription == null) {
            LOGGER.warn(" resultDescription not set");
            throw new MissingParameterException(
                " resultDescription not set  for Parameter of Type HbPrivateWriteSpecResult");
        }

        resultBits.append(resultDescription.encodeBinary());

        if (opSpecID == null) {
            LOGGER.warn(" opSpecID not set");
            throw new MissingParameterException(
                " opSpecID not set  for Parameter of Type HbPrivateWriteSpecResult");
        }

        resultBits.append(opSpecID.encodeBinary());

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

        if (result == null) {
            LOGGER.warn(" result not set");
            throw new MissingParameterException(" result not set");
        } else {
            element.addContent(result.encodeXML("Result", ns));
        }

        if (resultDescription == null) {
            LOGGER.warn(" resultDescription not set");
            throw new MissingParameterException(" resultDescription not set");
        } else {
            element.addContent(resultDescription.encodeXML(
                    "ResultDescription", ns));
        }

        if (opSpecID == null) {
            LOGGER.warn(" opSpecID not set");
            throw new MissingParameterException(" opSpecID not set");
        } else {
            element.addContent(opSpecID.encodeXML("OpSpecID", ns));
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
        result = new UnsignedByte(binary.subList(position, UnsignedByte.length()));
        position += UnsignedByte.length();
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UnsignedByteArray_HEX.length() * fieldCount) +
            UnsignedShort.length();
        resultDescription = new UnsignedByteArray_HEX(binary.subList(position,
                    tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedByteArray_HEX with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for resultDescription ");
        }

        opSpecID = new UnsignedShort(binary.subList(position,
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

        temp = element.getChild("Result", ns);

        if (temp != null) {
            result = new UnsignedByte(temp);
        }

        element.removeChild("Result", ns);
        temp = element.getChild("ResultDescription", ns);

        if (temp != null) {
            resultDescription = new UnsignedByteArray_HEX(temp);
        }

        element.removeChild("ResultDescription", ns);
        temp = element.getChild("OpSpecID", ns);

        if (temp != null) {
            opSpecID = new UnsignedShort(temp);
        }

        element.removeChild("OpSpecID", ns);

        if (element.getChildren().size() > 0) {
            String message = "HbPrivateWriteSpecResult has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   result of type UnsignedByte .
    * @param   result to be set
    */
    public void setResult(final UnsignedByte result) {
        this.result = result;
    }

    /**
    * set resultDescription of type UnsignedByteArray_HEX .
    * @param  resultDescription to be set
    */
    public void setResultDescription(
        final UnsignedByteArray_HEX resultDescription) {
        this.resultDescription = resultDescription;
    }

    /**
    * set   opSpecID of type UnsignedShort .
    * @param   opSpecID to be set
    */
    public void setOpSpecID(final UnsignedShort opSpecID) {
        this.opSpecID = opSpecID;
    }

    // end setter

    //getters
    /**
    * get   result of type UnsignedByte.
    * @return   type UnsignedByte to be set
    */
    public UnsignedByte getResult() {
        return this.result;
    }

    /**
    * get resultDescription of type  UnsignedByteArray_HEX.
    * @return  UnsignedByteArray_HEX
    */
    public UnsignedByteArray_HEX getResultDescription() {
        return resultDescription;
    }

    /**
    * get   opSpecID of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getOpSpecID() {
        return this.opSpecID;
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
        return "HbPrivateWriteSpecResult";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "HbPrivateWriteSpecResult: ";
        result += ", result: ";
        result += result;
        result += ", resultDescription: ";
        result += resultDescription;
        result += ", opSpecID: ";
        result += opSpecID;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
