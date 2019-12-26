/*
*
* This file was generated by LLRP Code Generator
* see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit
* for more information
* Generated on: Mon Apr 10 16:16:14 CST 2017;
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
import org.llrp.ltk.generated.enumerations.GetDeviceConfigRequestedDataType;
import org.llrp.ltk.types.BitList;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;

import java.util.LinkedList;
import java.util.List;


/**
 *

See also

 */
public class GetDeviceConfig extends LLRPMessage {
    public static final SignedShort TYPENUM = new SignedShort(660);
    private static final Logger LOGGER = Logger.getLogger(GetDeviceConfig.class);
    public static final String RESPONSETYPE = "GetDeviceConfigAck";
    protected GetDeviceConfigRequestedDataType requestedData;
    protected UnsignedByte antennaID;

    /**
     * empty constructor to create new message
     * with LLRP version set to 1.0 (0x1).
     */
    public GetDeviceConfig() {
        //		setVersion(new BitList(0,0,1));
        setVersion(new BitList(0, 0, 0, 0, 0, 0, 0, 1)); // change by wuwh 
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param list to be decoded
     */
    public GetDeviceConfig(final LLRPBitList list)
        throws InvalidLLRPMessageException {
        decodeBinary(list.toByteArray());
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param byteArray representing message
     */
    public GetDeviceConfig(final byte[] byteArray)
        throws InvalidLLRPMessageException {
        decodeBinary(byteArray);
    }

    /**
    * Constructor to create message from xml encoded message
    * calls decodeXML to decode message.
    * @param document to be decoded
    */
    public GetDeviceConfig(final Document document)
        throws InvalidLLRPMessageException {
        decodeXML(document);
    }

    /**
    * {@inheritDoc}
    */
    protected LLRPBitList encodeBinarySpecific()
        throws InvalidLLRPMessageException {
        LLRPBitList resultBits = new LLRPBitList();

        if (requestedData == null) {
            LOGGER.warn(" requestedData not set");
            throw new InvalidLLRPMessageException(
                " requestedData not set  for Parameter of Type GetDeviceConfig");
        }

        resultBits.append(requestedData.encodeBinary());

        if (antennaID == null) {
            LOGGER.warn(" antennaID not set");
            throw new InvalidLLRPMessageException(
                " antennaID not set  for Parameter of Type GetDeviceConfig");
        }

        resultBits.append(antennaID.encodeBinary());

        return resultBits;
    }

    /**
    * {@inheritDoc}
    */
    public Document encodeXML() throws InvalidLLRPMessageException {
        try {
            Namespace ns = Namespace.getNamespace("",
                    LLRPConstants.LLRPNAMESPACE);

            Element root = new Element("GetDeviceConfig", ns);
            //	Element root = new Element("GetDeviceConfig");
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

            if (requestedData == null) {
                LOGGER.warn(" requestedData not set");
                throw new MissingParameterException(" requestedData not set");
            } else {
                root.addContent(requestedData.encodeXML("RequestedData", ns));
            }

            if (antennaID == null) {
                LOGGER.warn(" antennaID not set");
                throw new MissingParameterException(" antennaID not set");
            } else {
                root.addContent(antennaID.encodeXML("AntennaID", ns));
            }

            //parameters
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
        requestedData = new GetDeviceConfigRequestedDataType(binary.subList(
                    position, GetDeviceConfigRequestedDataType.length()));
        position += GetDeviceConfigRequestedDataType.length();
        antennaID = new UnsignedByte(binary.subList(position,
                    UnsignedByte.length()));
        position += UnsignedByte.length();
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
            temp = root.getChild("RequestedData", root.getNamespace());

            //check if this element exist
            if (temp != null) {
                requestedData = new GetDeviceConfigRequestedDataType(temp);
            } else {
                LOGGER.warn(
                    "Element requestedData not provided in xml as child of " +
                    root.getName());
                throw new MissingParameterException(
                    "Element requestedData not provided");
            }

            root.removeChild("RequestedData", root.getNamespace());
            temp = root.getChild("AntennaID", root.getNamespace());

            //check if this element exist
            if (temp != null) {
                antennaID = new UnsignedByte(temp);
            } else {
                LOGGER.warn(
                    "Element antennaID not provided in xml as child of " +
                    root.getName());
                throw new MissingParameterException(
                    "Element antennaID not provided");
            }

            root.removeChild("AntennaID", root.getNamespace());

            if (root.getChildren().size() > 0) {
                String message = "GetDeviceConfig has unknown element " +
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
    * set requestedData of type GetDeviceConfigRequestedDataType .
    * @param  requestedData to be set
    */
    public void setRequestedData(
        final GetDeviceConfigRequestedDataType requestedData) {
        this.requestedData = requestedData;
    }

    /**
    * set   antennaID of type UnsignedByte .
    * @param   antennaID to be set
    */
    public void setAntennaID(final UnsignedByte antennaID) {
        this.antennaID = antennaID;
    }

    // end setter

    //getters
    /**
    * get requestedData of type GetDeviceConfigRequestedDataType.
    * @return  GetDeviceConfigRequestedDataType
    */
    public GetDeviceConfigRequestedDataType getRequestedData() {
        return requestedData;
    }

    /**
    * get   antennaID of type UnsignedByte.
    * @return   type UnsignedByte to be set
    */
    public UnsignedByte getAntennaID() {
        return this.antennaID;
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
        return "GetDeviceConfig";
    }

    /**
    * return uniuque type number.
    * @return SignedShort holding unique type number
    */
    public SignedShort getTypeNum() {
        return TYPENUM;
    }
}