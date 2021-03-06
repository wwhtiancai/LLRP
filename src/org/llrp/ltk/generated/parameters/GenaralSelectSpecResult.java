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
import org.llrp.ltk.generated.interfaces.SelectSpecResult;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedShort;
import org.llrp.ltk.types.UnsignedShortArray_HEX;

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
public class GenaralSelectSpecResult extends TLVParameter
    implements SelectSpecResult {
    public static final SignedShort TYPENUM = new SignedShort(510);
    private static final Logger LOGGER = Logger.getLogger(GenaralSelectSpecResult.class);
    protected UnsignedByte result;
    protected UnsignedShortArray_HEX tagData;

    /**
     * empty constructor to create new parameter.
     */
    public GenaralSelectSpecResult() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public GenaralSelectSpecResult(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public GenaralSelectSpecResult(Element element)
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
                " result not set  for Parameter of Type GenaralSelectSpecResult");
        }

        resultBits.append(result.encodeBinary());

        if (tagData == null) {
            LOGGER.warn(" tagData not set");
            throw new MissingParameterException(
                " tagData not set  for Parameter of Type GenaralSelectSpecResult");
        }

        resultBits.append(tagData.encodeBinary());

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

        if (tagData == null) {
            LOGGER.warn(" tagData not set");
            throw new MissingParameterException(" tagData not set");
        } else {
            element.addContent(tagData.encodeXML("TagData", ns));
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
        tempLength = (UnsignedShortArray_HEX.length() * fieldCount) +
            UnsignedShort.length();
        tagData = new UnsignedShortArray_HEX(binary.subList(position, tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedShortArray_HEX with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for tagData ");
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

        temp = element.getChild("Result", ns);

        if (temp != null) {
            result = new UnsignedByte(temp);
        }

        element.removeChild("Result", ns);
        temp = element.getChild("TagData", ns);

        if (temp != null) {
            tagData = new UnsignedShortArray_HEX(temp);
        }

        element.removeChild("TagData", ns);

        if (element.getChildren().size() > 0) {
            String message = "GenaralSelectSpecResult has unknown element " +
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
    * set tagData of type UnsignedShortArray_HEX .
    * @param  tagData to be set
    */
    public void setTagData(final UnsignedShortArray_HEX tagData) {
        this.tagData = tagData;
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
    * get tagData of type  UnsignedShortArray_HEX.
    * @return  UnsignedShortArray_HEX
    */
    public UnsignedShortArray_HEX getTagData() {
        return tagData;
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
        return "GenaralSelectSpecResult";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "GenaralSelectSpecResult: ";
        result += ", result: ";
        result += result;
        result += ", tagData: ";
        result += tagData;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
