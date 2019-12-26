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
import org.llrp.ltk.types.BitList;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.UnsignedByteArray_HEX;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;

import java.util.LinkedList;
import java.util.List;


/**
 *

See also

 */
public class DeviceCertificateConfig extends LLRPMessage {
    public static final SignedShort TYPENUM = new SignedShort(602);
    private static final Logger LOGGER = Logger.getLogger(DeviceCertificateConfig.class);
    public static final String RESPONSETYPE = "";
    protected UnsignedByteArray_HEX certificateData;
    protected UnsignedByteArray_HEX userData;

    /**
     * empty constructor to create new message
     * with LLRP version set to 1.0 (0x1).
     */
    public DeviceCertificateConfig() {
        //		setVersion(new BitList(0,0,1));
        setVersion(new BitList(0, 0, 0, 0, 0, 0, 0, 1)); // change by wuwh 
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param list to be decoded
     */
    public DeviceCertificateConfig(final LLRPBitList list)
        throws InvalidLLRPMessageException {
        decodeBinary(list.toByteArray());
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param byteArray representing message
     */
    public DeviceCertificateConfig(final byte[] byteArray)
        throws InvalidLLRPMessageException {
        decodeBinary(byteArray);
    }

    /**
    * Constructor to create message from xml encoded message
    * calls decodeXML to decode message.
    * @param document to be decoded
    */
    public DeviceCertificateConfig(final Document document)
        throws InvalidLLRPMessageException {
        decodeXML(document);
    }

    /**
    * {@inheritDoc}
    */
    protected LLRPBitList encodeBinarySpecific()
        throws InvalidLLRPMessageException {
        LLRPBitList resultBits = new LLRPBitList();

        if (certificateData == null) {
            LOGGER.warn(" certificateData not set");
            throw new InvalidLLRPMessageException(
                " certificateData not set  for Parameter of Type DeviceCertificateConfig");
        }

        resultBits.append(certificateData.encodeBinary());

        if (userData == null) {
            LOGGER.warn(" userData not set");
            throw new InvalidLLRPMessageException(
                " userData not set  for Parameter of Type DeviceCertificateConfig");
        }

        resultBits.append(userData.encodeBinary());

        return resultBits;
    }

    /**
    * {@inheritDoc}
    */
    public Document encodeXML() throws InvalidLLRPMessageException {
        try {
            Namespace ns = Namespace.getNamespace("",
                    LLRPConstants.LLRPNAMESPACE);

            Element root = new Element("DeviceCertificateConfig", ns);
            //	Element root = new Element("DeviceCertificateConfig");
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

            if (certificateData == null) {
                LOGGER.warn(" certificateData not set");
                throw new MissingParameterException(" certificateData not set");
            } else {
                root.addContent(certificateData.encodeXML("CertificateData", ns));
            }

            if (userData == null) {
                LOGGER.warn(" userData not set");
                throw new MissingParameterException(" userData not set");
            } else {
                root.addContent(userData.encodeXML("UserData", ns));
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
        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UnsignedByteArray_HEX.length() * fieldCount) +
            UnsignedShort.length();
        certificateData = new UnsignedByteArray_HEX(binary.subList(position,
                    tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedByteArray_HEX with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for certificateData ");
        }

        // array. first 16 bits indicate length of array
        fieldCount = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length())).toShort();
        tempLength = (UnsignedByteArray_HEX.length() * fieldCount) +
            UnsignedShort.length();
        userData = new UnsignedByteArray_HEX(binary.subList(position, tempLength));
        position += tempLength;
        LOGGER.debug("decoding array of type: UnsignedByteArray_HEX with " +
            tempLength + " length");

        //might need padding
        // must always be blocks of 8 bites, if it is a bitlist, this might not be automatically the case
        if ((tempLength % 8) > 0) {
            position += (8 - (tempLength % 8));
            LOGGER.info("padding needed for userData ");
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
            temp = root.getChild("CertificateData", root.getNamespace());

            //check if this element exist
            if (temp != null) {
                certificateData = new UnsignedByteArray_HEX(temp);
            } else {
                LOGGER.warn(
                    "Element certificateData not provided in xml as child of " +
                    root.getName());
                throw new MissingParameterException(
                    "Element certificateData not provided");
            }

            root.removeChild("CertificateData", root.getNamespace());
            temp = root.getChild("UserData", root.getNamespace());

            //check if this element exist
            if (temp != null) {
                userData = new UnsignedByteArray_HEX(temp);
            } else {
                LOGGER.warn("Element userData not provided in xml as child of " +
                    root.getName());
                throw new MissingParameterException(
                    "Element userData not provided");
            }

            root.removeChild("UserData", root.getNamespace());

            if (root.getChildren().size() > 0) {
                String message = "DeviceCertificateConfig has unknown element " +
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
    * set certificateData of type UnsignedByteArray_HEX .
    * @param  certificateData to be set
    */
    public void setCertificateData(final UnsignedByteArray_HEX certificateData) {
        this.certificateData = certificateData;
    }

    /**
    * set userData of type UnsignedByteArray_HEX .
    * @param  userData to be set
    */
    public void setUserData(final UnsignedByteArray_HEX userData) {
        this.userData = userData;
    }

    // end setter

    //getters
    /**
    * get certificateData of type  UnsignedByteArray_HEX.
    * @return  UnsignedByteArray_HEX
    */
    public UnsignedByteArray_HEX getCertificateData() {
        return certificateData;
    }

    /**
    * get userData of type  UnsignedByteArray_HEX.
    * @return  UnsignedByteArray_HEX
    */
    public UnsignedByteArray_HEX getUserData() {
        return userData;
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
        return "DeviceCertificateConfig";
    }

    /**
    * return uniuque type number.
    * @return SignedShort holding unique type number
    */
    public SignedShort getTypeNum() {
        return TYPENUM;
    }
}