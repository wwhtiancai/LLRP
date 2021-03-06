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
import org.llrp.ltk.generated.enumerations.HbTargetTagMatchType;
import org.llrp.ltk.generated.enumerations.HbTargetTagMemoryType;
import org.llrp.ltk.types.BitArray;
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
public class HbTargetTag extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(454);
    private static final Logger LOGGER = Logger.getLogger(HbTargetTag.class);
    protected HbTargetTagMemoryType memoryType;
    protected HbTargetTagMatchType matchType;
    protected BitList reserved0 = new BitList(7);
    protected UnsignedShort pointer;
    protected BitArray tagMask;
    protected BitArray tagData;

    /**
     * empty constructor to create new parameter.
     */
    public HbTargetTag() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public HbTargetTag(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public HbTargetTag(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (memoryType == null) {
            LOGGER.warn(" memoryType not set");
            throw new MissingParameterException(
                " memoryType not set  for Parameter of Type HbTargetTag");
        }

        resultBits.append(memoryType.encodeBinary());

        if (matchType == null) {
            LOGGER.warn(" matchType not set");
            throw new MissingParameterException(
                " matchType not set  for Parameter of Type HbTargetTag");
        }

        resultBits.append(matchType.encodeBinary());
        resultBits.append(reserved0.encodeBinary());

        if (pointer == null) {
            LOGGER.warn(" pointer not set");
            throw new MissingParameterException(
                " pointer not set  for Parameter of Type HbTargetTag");
        }

        resultBits.append(pointer.encodeBinary());

        if (tagMask == null) {
            LOGGER.warn(" tagMask not set");
            throw new MissingParameterException(
                " tagMask not set  for Parameter of Type HbTargetTag");
        }

        resultBits.append(tagMask.encodeBinary());

        if (tagData == null) {
            LOGGER.warn(" tagData not set");
            throw new MissingParameterException(
                " tagData not set  for Parameter of Type HbTargetTag");
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

        if (memoryType == null) {
            LOGGER.warn(" memoryType not set");
            throw new MissingParameterException(" memoryType not set");
        } else {
            element.addContent(memoryType.encodeXML("MemoryType", ns));
        }

        if (matchType == null) {
            LOGGER.warn(" matchType not set");
            throw new MissingParameterException(" matchType not set");
        } else {
            element.addContent(matchType.encodeXML("MatchType", ns));
        }

        //element.addContent(reserved0.encodeXML("reserved",ns));
        if (pointer == null) {
            LOGGER.warn(" pointer not set");
            throw new MissingParameterException(" pointer not set");
        } else {
            element.addContent(pointer.encodeXML("Pointer", ns));
        }

        if (tagMask == null) {
            LOGGER.warn(" tagMask not set");
            throw new MissingParameterException(" tagMask not set");
        } else {
            element.addContent(tagMask.encodeXML("TagMask", ns));
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
        memoryType = new HbTargetTagMemoryType(binary.subList(position,
                    HbTargetTagMemoryType.length()));
        position += HbTargetTagMemoryType.length();
        matchType = new HbTargetTagMatchType(binary.subList(position,
                    HbTargetTagMatchType.length()));
        position += HbTargetTagMatchType.length();
        position += reserved0.length();
        pointer = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (BitArray.length() * fieldCount) + UnsignedShort.length();
        tagMask = new BitArray(binary.subList(position, tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: BitArray with " + tempLength +
            " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for tagMask ");
        }

        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (BitArray.length() * fieldCount) + UnsignedShort.length();
        tagData = new BitArray(binary.subList(position, tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: BitArray with " + tempLength +
            " length");

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

        temp = element.getChild("MemoryType", ns);

        if (temp != null) {
            memoryType = new HbTargetTagMemoryType(temp);
        }

        element.removeChild("MemoryType", ns);
        temp = element.getChild("MatchType", ns);

        if (temp != null) {
            matchType = new HbTargetTagMatchType(temp);
        }

        element.removeChild("MatchType", ns);
        temp = element.getChild("Pointer", ns);

        if (temp != null) {
            pointer = new UnsignedShort(temp);
        }

        element.removeChild("Pointer", ns);
        temp = element.getChild("TagMask", ns);

        if (temp != null) {
            tagMask = new BitArray(temp);
        }

        element.removeChild("TagMask", ns);
        temp = element.getChild("TagData", ns);

        if (temp != null) {
            tagData = new BitArray(temp);
        }

        element.removeChild("TagData", ns);

        if (element.getChildren().size() > 0) {
            String message = "HbTargetTag has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set memoryType of type HbTargetTagMemoryType .
    * @param  memoryType to be set
    */
    public void setMemoryType(final HbTargetTagMemoryType memoryType) {
        this.memoryType = memoryType;
    }

    /**
    * set matchType of type HbTargetTagMatchType .
    * @param  matchType to be set
    */
    public void setMatchType(final HbTargetTagMatchType matchType) {
        this.matchType = matchType;
    }

    /**
    * set   pointer of type UnsignedShort .
    * @param   pointer to be set
    */
    public void setPointer(final UnsignedShort pointer) {
        this.pointer = pointer;
    }

    /**
    * set   tagMask of type BitArray .
    * @param   tagMask to be set
    */
    public void setTagMask(final BitArray tagMask) {
        this.tagMask = tagMask;
    }

    /**
    * set   tagData of type BitArray .
    * @param   tagData to be set
    */
    public void setTagData(final BitArray tagData) {
        this.tagData = tagData;
    }

    // end setter

    //getters
    /**
    * get memoryType of type HbTargetTagMemoryType.
    * @return  HbTargetTagMemoryType
    */
    public HbTargetTagMemoryType getMemoryType() {
        return memoryType;
    }

    /**
    * get matchType of type HbTargetTagMatchType.
    * @return  HbTargetTagMatchType
    */
    public HbTargetTagMatchType getMatchType() {
        return matchType;
    }

    /**
    * get   pointer of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getPointer() {
        return this.pointer;
    }

    /**
    * get   tagMask of type BitArray.
    * @return   type BitArray to be set
    */
    public BitArray getTagMask() {
        return this.tagMask;
    }

    /**
    * get   tagData of type BitArray.
    * @return   type BitArray to be set
    */
    public BitArray getTagData() {
        return this.tagData;
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
        return "HbTargetTag";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "HbTargetTag: ";
        result += ", memoryType: ";
        result += memoryType;
        result += ", matchType: ";
        result += matchType;

        result += ", pointer: ";
        result += pointer;
        result += ", tagMask: ";
        result += tagMask;
        result += ", tagData: ";
        result += tagData;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
