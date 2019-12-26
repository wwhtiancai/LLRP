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
public class QZBFQ extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(1011);
    private static final Logger LOGGER = Logger.getLogger(QZBFQ.class);
    protected UnsignedByteArray_HEX qZBFQData;

    /**
     * empty constructor to create new parameter.
     */
    public QZBFQ() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public QZBFQ(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public QZBFQ(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (qZBFQData == null) {
            LOGGER.warn(" qZBFQData not set");
            throw new MissingParameterException(
                " qZBFQData not set  for Parameter of Type QZBFQ");
        }

        resultBits.append(qZBFQData.encodeBinary());

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

        if (qZBFQData == null) {
            LOGGER.warn(" qZBFQData not set");
            throw new MissingParameterException(" qZBFQData not set");
        } else {
            element.addContent(qZBFQData.encodeXML("QZBFQData", ns));
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
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UnsignedByteArray_HEX.length() * fieldCount) +
            UnsignedShort.length();
        qZBFQData = new UnsignedByteArray_HEX(binary.subList(position,
                    tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedByteArray_HEX with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for qZBFQData ");
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

        temp = element.getChild("QZBFQData", ns);

        if (temp != null) {
            qZBFQData = new UnsignedByteArray_HEX(temp);
        }

        element.removeChild("QZBFQData", ns);

        if (element.getChildren().size() > 0) {
            String message = "QZBFQ has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set qZBFQData of type UnsignedByteArray_HEX .
    * @param  qZBFQData to be set
    */
    public void setQZBFQData(final UnsignedByteArray_HEX qZBFQData) {
        this.qZBFQData = qZBFQData;
    }

    // end setter

    //getters
    /**
    * get qZBFQData of type  UnsignedByteArray_HEX.
    * @return  UnsignedByteArray_HEX
    */
    public UnsignedByteArray_HEX getQZBFQData() {
        return qZBFQData;
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
        return "QZBFQ";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "QZBFQ: ";
        result += ", qZBFQData: ";
        result += qZBFQData;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
