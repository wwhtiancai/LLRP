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
import org.llrp.ltk.generated.enumerations.HbSpecMemoryBankIDType;
import org.llrp.ltk.generated.interfaces.OpSpec;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
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
public class HbWriteSpec extends TLVParameter implements OpSpec {
    public static final SignedShort TYPENUM = new SignedShort(458);
    private static final Logger LOGGER = Logger.getLogger(HbWriteSpec.class);
    protected UnsignedShort opSpecID;
    protected HbSpecMemoryBankIDType memoryBankID;
    protected UnsignedShort pointer;
    protected UnsignedShortArray_HEX data;

    /**
     * empty constructor to create new parameter.
     */
    public HbWriteSpec() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public HbWriteSpec(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public HbWriteSpec(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (opSpecID == null) {
            LOGGER.warn(" opSpecID not set");
            throw new MissingParameterException(
                " opSpecID not set  for Parameter of Type HbWriteSpec");
        }

        resultBits.append(opSpecID.encodeBinary());

        if (memoryBankID == null) {
            LOGGER.warn(" memoryBankID not set");
            throw new MissingParameterException(
                " memoryBankID not set  for Parameter of Type HbWriteSpec");
        }

        resultBits.append(memoryBankID.encodeBinary());

        if (pointer == null) {
            LOGGER.warn(" pointer not set");
            throw new MissingParameterException(
                " pointer not set  for Parameter of Type HbWriteSpec");
        }

        resultBits.append(pointer.encodeBinary());

        if (data == null) {
            LOGGER.warn(" data not set");
            throw new MissingParameterException(
                " data not set  for Parameter of Type HbWriteSpec");
        }

        resultBits.append(data.encodeBinary());

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

        if (opSpecID == null) {
            LOGGER.warn(" opSpecID not set");
            throw new MissingParameterException(" opSpecID not set");
        } else {
            element.addContent(opSpecID.encodeXML("OpSpecID", ns));
        }

        if (memoryBankID == null) {
            LOGGER.warn(" memoryBankID not set");
            throw new MissingParameterException(" memoryBankID not set");
        } else {
            element.addContent(memoryBankID.encodeXML("MemoryBankID", ns));
        }

        if (pointer == null) {
            LOGGER.warn(" pointer not set");
            throw new MissingParameterException(" pointer not set");
        } else {
            element.addContent(pointer.encodeXML("Pointer", ns));
        }

        if (data == null) {
            LOGGER.warn(" data not set");
            throw new MissingParameterException(" data not set");
        } else {
            element.addContent(data.encodeXML("Data", ns));
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
        opSpecID = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        memoryBankID = new HbSpecMemoryBankIDType(binary.subList(position,
                    HbSpecMemoryBankIDType.length()));
        position += HbSpecMemoryBankIDType.length();
        pointer = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UnsignedShortArray_HEX.length() * fieldCount) +
            UnsignedShort.length();
        data = new UnsignedShortArray_HEX(binary.subList(position, tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedShortArray_HEX with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for data ");
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

        temp = element.getChild("OpSpecID", ns);

        if (temp != null) {
            opSpecID = new UnsignedShort(temp);
        }

        element.removeChild("OpSpecID", ns);
        temp = element.getChild("MemoryBankID", ns);

        if (temp != null) {
            memoryBankID = new HbSpecMemoryBankIDType(temp);
        }

        element.removeChild("MemoryBankID", ns);
        temp = element.getChild("Pointer", ns);

        if (temp != null) {
            pointer = new UnsignedShort(temp);
        }

        element.removeChild("Pointer", ns);
        temp = element.getChild("Data", ns);

        if (temp != null) {
            data = new UnsignedShortArray_HEX(temp);
        }

        element.removeChild("Data", ns);

        if (element.getChildren().size() > 0) {
            String message = "HbWriteSpec has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   opSpecID of type UnsignedShort .
    * @param   opSpecID to be set
    */
    public void setOpSpecID(final UnsignedShort opSpecID) {
        this.opSpecID = opSpecID;
    }

    /**
    * set memoryBankID of type HbSpecMemoryBankIDType .
    * @param  memoryBankID to be set
    */
    public void setMemoryBankID(final HbSpecMemoryBankIDType memoryBankID) {
        this.memoryBankID = memoryBankID;
    }

    /**
    * set   pointer of type UnsignedShort .
    * @param   pointer to be set
    */
    public void setPointer(final UnsignedShort pointer) {
        this.pointer = pointer;
    }

    /**
    * set data of type UnsignedShortArray_HEX .
    * @param  data to be set
    */
    public void setData(final UnsignedShortArray_HEX data) {
        this.data = data;
    }

    // end setter

    //getters
    /**
    * get   opSpecID of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getOpSpecID() {
        return this.opSpecID;
    }

    /**
    * get memoryBankID of type HbSpecMemoryBankIDType.
    * @return  HbSpecMemoryBankIDType
    */
    public HbSpecMemoryBankIDType getMemoryBankID() {
        return memoryBankID;
    }

    /**
    * get   pointer of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getPointer() {
        return this.pointer;
    }

    /**
    * get data of type  UnsignedShortArray_HEX.
    * @return  UnsignedShortArray_HEX
    */
    public UnsignedShortArray_HEX getData() {
        return data;
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
        return "HbWriteSpec";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "HbWriteSpec: ";
        result += ", opSpecID: ";
        result += opSpecID;
        result += ", memoryBankID: ";
        result += memoryBankID;
        result += ", pointer: ";
        result += pointer;
        result += ", data: ";
        result += data;
        result = result.replaceFirst(", ", "");

        return result;
    }
}