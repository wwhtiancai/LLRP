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
import org.llrp.ltk.generated.enumerations.DeviceLogType;
import org.llrp.ltk.generated.parameters.UTCTimestamp;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UTF8String_UTF_8;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedLong;
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
public class DeviceLog extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(640);
    private static final Logger LOGGER = Logger.getLogger(DeviceLog.class);
    protected UnsignedLong logSequence;
    protected DeviceLogType logType;
    protected UnsignedInteger logCode;
    protected UTF8String_UTF_8 logDescInfo;
    protected UTCTimestamp uTCTimestamp;

    /**
     * empty constructor to create new parameter.
     */
    public DeviceLog() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public DeviceLog(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public DeviceLog(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (logSequence == null) {
            LOGGER.warn(" logSequence not set");
            throw new MissingParameterException(
                " logSequence not set  for Parameter of Type DeviceLog");
        }

        resultBits.append(logSequence.encodeBinary());

        if (logType == null) {
            LOGGER.warn(" logType not set");
            throw new MissingParameterException(
                " logType not set  for Parameter of Type DeviceLog");
        }

        resultBits.append(logType.encodeBinary());

        if (logCode == null) {
            LOGGER.warn(" logCode not set");
            throw new MissingParameterException(
                " logCode not set  for Parameter of Type DeviceLog");
        }

        resultBits.append(logCode.encodeBinary());

        if (logDescInfo == null) {
            LOGGER.warn(" logDescInfo not set");
            throw new MissingParameterException(
                " logDescInfo not set  for Parameter of Type DeviceLog");
        }

        resultBits.append(logDescInfo.encodeBinary());

        if (uTCTimestamp == null) {
            // single parameter, may not be null
            LOGGER.warn(" uTCTimestamp not set");
            throw new MissingParameterException(" uTCTimestamp not set");
        } else {
            resultBits.append(uTCTimestamp.encodeBinary());
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

        if (logSequence == null) {
            LOGGER.warn(" logSequence not set");
            throw new MissingParameterException(" logSequence not set");
        } else {
            element.addContent(logSequence.encodeXML("LogSequence", ns));
        }

        if (logType == null) {
            LOGGER.warn(" logType not set");
            throw new MissingParameterException(" logType not set");
        } else {
            element.addContent(logType.encodeXML("LogType", ns));
        }

        if (logCode == null) {
            LOGGER.warn(" logCode not set");
            throw new MissingParameterException(" logCode not set");
        } else {
            element.addContent(logCode.encodeXML("LogCode", ns));
        }

        if (logDescInfo == null) {
            LOGGER.warn(" logDescInfo not set");
            throw new MissingParameterException(" logDescInfo not set");
        } else {
            element.addContent(logDescInfo.encodeXML("LogDescInfo", ns));
        }

        //parameters
        if (uTCTimestamp == null) {
            LOGGER.info("uTCTimestamp not set");
            throw new MissingParameterException("uTCTimestamp not set");
        } else {
            element.addContent(uTCTimestamp.encodeXML(
                    uTCTimestamp.getClass().getSimpleName(), ns));
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
        logSequence = new UnsignedLong(binary.subList(position,
                    UnsignedLong.length()));
        position += UnsignedLong.length();
        logType = new DeviceLogType(binary.subList(position,
                    DeviceLogType.length()));
        position += DeviceLogType.length();
        logCode = new UnsignedInteger(binary.subList(position,
                    UnsignedInteger.length()));
        position += UnsignedInteger.length();
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UTF8String_UTF_8.length() * fieldCount) +
            UnsignedShort.length();
        logDescInfo = new UTF8String_UTF_8(binary.subList(position, tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UTF8String_UTF_8 with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for logDescInfo ");
        }

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
            LOGGER.warn(
                "DeviceLog misses non optional parameter of type UTCTimestamp");
            throw new MissingParameterException(
                "DeviceLog misses non optional parameter of type UTCTimestamp");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = uTCTimestamp.length();
        }

        if ((type != null) && type.equals(UTCTimestamp.TYPENUM)) {
            uTCTimestamp = new UTCTimestamp(binary.subList(position, tempLength));
            position += tempLength;
            LOGGER.debug(
                " uTCTimestamp is instantiated with UTCTimestamp with length" +
                tempLength);
        } else {
            LOGGER.warn(
                "DeviceLog misses non optional parameter of type UTCTimestamp");
            throw new MissingParameterException(
                "DeviceLog misses non optional parameter of type UTCTimestamp");
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

        temp = element.getChild("LogSequence", ns);

        if (temp != null) {
            logSequence = new UnsignedLong(temp);
        }

        element.removeChild("LogSequence", ns);
        temp = element.getChild("LogType", ns);

        if (temp != null) {
            logType = new DeviceLogType(temp);
        }

        element.removeChild("LogType", ns);
        temp = element.getChild("LogCode", ns);

        if (temp != null) {
            logCode = new UnsignedInteger(temp);
        }

        element.removeChild("LogCode", ns);
        temp = element.getChild("LogDescInfo", ns);

        if (temp != null) {
            logDescInfo = new UTF8String_UTF_8(temp);
        }

        element.removeChild("LogDescInfo", ns);

        //parameter - not choices - no special actions needed
        temp = element.getChild("UTCTimestamp", ns);

        if (temp != null) {
            uTCTimestamp = new UTCTimestamp(temp);
            LOGGER.info(
                "setting parameter uTCTimestamp for parameter DeviceLog");
        }

        if (temp == null) {
            LOGGER.warn(
                "DeviceLog misses non optional parameter of type uTCTimestamp");
            throw new MissingParameterException(
                "DeviceLog misses non optional parameter of type uTCTimestamp");
        }

        element.removeChild("UTCTimestamp", ns);

        if (element.getChildren().size() > 0) {
            String message = "DeviceLog has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   logSequence of type UnsignedLong .
    * @param   logSequence to be set
    */
    public void setLogSequence(final UnsignedLong logSequence) {
        this.logSequence = logSequence;
    }

    /**
    * set logType of type DeviceLogType .
    * @param  logType to be set
    */
    public void setLogType(final DeviceLogType logType) {
        this.logType = logType;
    }

    /**
    * set   logCode of type UnsignedInteger .
    * @param   logCode to be set
    */
    public void setLogCode(final UnsignedInteger logCode) {
        this.logCode = logCode;
    }

    /**
    * set logDescInfo of type UTF8String_UTF_8 .
    * @param  logDescInfo to be set
    */
    public void setLogDescInfo(final UTF8String_UTF_8 logDescInfo) {
        this.logDescInfo = logDescInfo;
    }

    /**
    * set uTCTimestamp of type UTCTimestamp.
    * @param  uTCTimestamp to be set
    */
    public void setUTCTimestamp(final UTCTimestamp uTCTimestamp) {
        this.uTCTimestamp = uTCTimestamp;
    }

    // end setter

    //getters
    /**
    * get   logSequence of type UnsignedLong.
    * @return   type UnsignedLong to be set
    */
    public UnsignedLong getLogSequence() {
        return this.logSequence;
    }

    /**
    * get logType of type DeviceLogType.
    * @return  DeviceLogType
    */
    public DeviceLogType getLogType() {
        return logType;
    }

    /**
    * get   logCode of type UnsignedInteger.
    * @return   type UnsignedInteger to be set
    */
    public UnsignedInteger getLogCode() {
        return this.logCode;
    }

    /**
    * get logDescInfo of type  UTF8String_UTF_8.
    * @return  UTF8String_UTF_8
    */
    public UTF8String_UTF_8 getLogDescInfo() {
        return logDescInfo;
    }

    /**
    * get uTCTimestamp of type UTCTimestamp .
    * @return  UTCTimestamp
    */
    public UTCTimestamp getUTCTimestamp() {
        return uTCTimestamp;
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
        return "DeviceLog";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "DeviceLog: ";
        result += ", logSequence: ";
        result += logSequence;
        result += ", logType: ";
        result += logType;
        result += ", logCode: ";
        result += logCode;
        result += ", logDescInfo: ";
        result += logDescInfo;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
