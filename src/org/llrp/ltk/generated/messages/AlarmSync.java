/*
*
* This file was generated by LLRP Code Generator
* see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit
* for more information
* Generated on: Mon Apr 10 16:16:15 CST 2017;
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
package org.llrp.ltk.generated.messages;

import org.apache.log4j.Logger;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.exceptions.MissingParameterException;
import org.llrp.ltk.generated.LLRPConstants;
import org.llrp.ltk.generated.parameters.AlarmSyncInfo;
import org.llrp.ltk.types.BitList;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;

import java.util.LinkedList;
import java.util.List;


/**
 *

See also

 */
public class AlarmSync extends LLRPMessage {
    public static final SignedShort TYPENUM = new SignedShort(723);
    private static final Logger LOGGER = Logger.getLogger(AlarmSync.class);
    public static final String RESPONSETYPE = "";
    protected AlarmSyncInfo alarmSyncInfo;

    /**
     * empty constructor to create new message
     * with LLRP version set to 1.0 (0x1).
     */
    public AlarmSync() {
        //		setVersion(new BitList(0,0,1));
        setVersion(new BitList(0, 0, 0, 0, 0, 0, 0, 1)); // change by wuwh 
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param list to be decoded
     */
    public AlarmSync(final LLRPBitList list) throws InvalidLLRPMessageException {
        decodeBinary(list.toByteArray());
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param byteArray representing message
     */
    public AlarmSync(final byte[] byteArray) throws InvalidLLRPMessageException {
        decodeBinary(byteArray);
    }

    /**
    * Constructor to create message from xml encoded message
    * calls decodeXML to decode message.
    * @param document to be decoded
    */
    public AlarmSync(final Document document)
        throws InvalidLLRPMessageException {
        decodeXML(document);
    }

    /**
    * {@inheritDoc}
    */
    protected LLRPBitList encodeBinarySpecific()
        throws InvalidLLRPMessageException {
        LLRPBitList resultBits = new LLRPBitList();

        if (alarmSyncInfo == null) {
            // single parameter, may not be null
            LOGGER.warn(" alarmSyncInfo not set");
            throw new InvalidLLRPMessageException(" alarmSyncInfo not set");
        } else {
            resultBits.append(alarmSyncInfo.encodeBinary());
        }

        return resultBits;
    }

    /**
    * {@inheritDoc}
    */
    public Document encodeXML() throws InvalidLLRPMessageException {
        try {
            Namespace ns = Namespace.getNamespace("",
                    LLRPConstants.LLRPNAMESPACE);

            Element root = new Element("AlarmSync", ns);
            //	Element root = new Element("AlarmSync");
            root.addNamespaceDeclaration(Namespace.getNamespace("llrp",
                    LLRPConstants.LLRPNAMESPACE));

            if (version == null) {
                throw new InvalidLLRPMessageException("Version not set");
            } else {
                root.setAttribute("Version", version.toInteger().toString());
            }

            if (messageID == null) {
                throw new InvalidLLRPMessageException("MessageID not set");
            } else {
                root.setAttribute("MessageID", messageID.toString(10));
            }

            //parameters
            if (alarmSyncInfo == null) {
                LOGGER.info("alarmSyncInfo not set");
                throw new MissingParameterException("alarmSyncInfo not set");
            } else {
                root.addContent(alarmSyncInfo.encodeXML(
                        alarmSyncInfo.getClass().getSimpleName(), ns));
            }

            Document doc = new Document(root);

            if (isValidXMLMessage(doc, LLRPConstants.LLRPMESSAGESCHEMAPATH)) {
                return doc;
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidLLRPMessageException(e.getMessage());
        } catch (MissingParameterException e) {
            throw new InvalidLLRPMessageException(e.getMessage());
        }
    }

    /**
    * {@inheritDoc}
    */
    protected void decodeBinarySpecific(LLRPBitList binary)
        throws InvalidLLRPMessageException {
        int position = 0;
        int tempByteLength;
        int tempLength = 0;
        int count;
        SignedShort type;
        int fieldCount;

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
                //type = new SignedShort(binary.subList(position+RESERVEDLENGTH, TYPENUMBERLENGTH));
                //tempByteLength = new UnsignedShort(binary.subList(position+RESERVEDLENGTH+ TYPENUMBERLENGTH, UnsignedShort.length())).toShort();			
                //tempLength=8*tempByteLength;
                type = new SignedShort(binary.subList(position,
                            PARAMETERTYPELENGTH)); //change by wuwh
                tempByteLength = new UnsignedShort(binary.subList(position +
                            PARAMETERTYPELENGTH, UnsignedShort.length())).toShort(); //change by wuwh		
                tempLength = 8 * (tempByteLength + 4); //change by wuwh
            }
        } catch (IllegalArgumentException le) {
            // if an IllegalArgumentException is thrown, list was not long enough so the parameter is missing
            LOGGER.warn(
                "AlarmSync misses non optional parameter of type AlarmSyncInfo");
            throw new InvalidLLRPMessageException(
                "AlarmSync misses non optional parameter of type AlarmSyncInfo");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = alarmSyncInfo.length();
        }

        if ((type != null) && type.equals(AlarmSyncInfo.TYPENUM)) {
            alarmSyncInfo = new AlarmSyncInfo(binary.subList(position,
                        tempLength));
            position += tempLength;
            LOGGER.debug(
                " alarmSyncInfo is instantiated with AlarmSyncInfo with length" +
                tempLength);
        } else {
            LOGGER.warn(
                "AlarmSync misses non optional parameter of type AlarmSyncInfo");
            throw new InvalidLLRPMessageException(
                "AlarmSync misses non optional parameter of type AlarmSyncInfo");
        }
    }

    /**
    * {@inheritDoc}
    */
    public void decodeXML(final Document document)
        throws InvalidLLRPMessageException {
        Element temp = null;

        // child element are always in default LLRP namespace
        Namespace ns = Namespace.getNamespace(LLRPConstants.LLRPNAMESPACE);

        try {
            isValidXMLMessage(document, LLRPConstants.LLRPMESSAGESCHEMAPATH);

            Element root = (Element) document.getRootElement().clone();
            List<Element> tempList = null;

            // the version field is always 3 bit long 
            // if the version attribute is not set in the LTK-XML message,
            // it is set to version 001
            String versionAttribute = root.getAttributeValue("Version");

            if (versionAttribute != null) {
                version = new BitList(8); //change by wuwh 3 to 8
                version.setValue(new Integer(versionAttribute));
            } else {
                version = new BitList(0, 0, 0, 0, 0, 0, 0, 1); //change by wuwh 
            }

            messageID = new UnsignedInteger(root.getAttributeValue("MessageID"));

            //parameter - not choices - no special actions needed
            temp = root.getChild("AlarmSyncInfo", ns);

            if (temp != null) {
                alarmSyncInfo = new AlarmSyncInfo(temp);
                LOGGER.info(
                    "setting parameter alarmSyncInfo for parameter AlarmSync");
            } else {
                LOGGER.warn(
                    "AlarmSync misses non optional parameter of type alarmSyncInfo");
                throw new MissingParameterException(
                    "AlarmSync misses non optional parameter of type alarmSyncInfo");
            }

            root.removeChild("AlarmSyncInfo", ns);

            if (root.getChildren().size() > 0) {
                String message = "AlarmSync has unknown element " +
                    ((Element) root.getChildren().get(0)).getName();
                throw new InvalidLLRPMessageException(message);
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidLLRPMessageException(e.getMessage());
        } catch (MissingParameterException e) {
            throw new InvalidLLRPMessageException(e.getMessage());
        }
    }

    //setters

    /**
    * set alarmSyncInfo of type AlarmSyncInfo.
    * @param  alarmSyncInfo to be set
    */
    public void setAlarmSyncInfo(final AlarmSyncInfo alarmSyncInfo) {
        this.alarmSyncInfo = alarmSyncInfo;
    }

    // end setter

    //getters

    /**
    * get alarmSyncInfo of type AlarmSyncInfo .
    * @return  AlarmSyncInfo
    */
    public AlarmSyncInfo getAlarmSyncInfo() {
        return alarmSyncInfo;
    }

    // end getters

    //add methods

    // end add
    @Override
    public String getResponseType() {
        return RESPONSETYPE;
    }

    @Override
    public String getName() {
        return "AlarmSync";
    }

    /**
    * return uniuque type number.
    * @return SignedShort holding unique type number
    */
    public SignedShort getTypeNum() {
        return TYPENUM;
    }
}
