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
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;

import java.util.LinkedList;
import java.util.List;


/**
 *

See also

 */
public class UploadDeviceLogConfirm extends LLRPMessage {
    public static final SignedShort TYPENUM = new SignedShort(646);
    private static final Logger LOGGER = Logger.getLogger(UploadDeviceLogConfirm.class);
    public static final String RESPONSETYPE = "";
    protected UnsignedShort sequenceId;

    /**
     * empty constructor to create new message
     * with LLRP version set to 1.0 (0x1).
     */
    public UploadDeviceLogConfirm() {
        //		setVersion(new BitList(0,0,1));
        setVersion(new BitList(0, 0, 0, 0, 0, 0, 0, 1)); // change by wuwh 
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param list to be decoded
     */
    public UploadDeviceLogConfirm(final LLRPBitList list)
        throws InvalidLLRPMessageException {
        decodeBinary(list.toByteArray());
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param byteArray representing message
     */
    public UploadDeviceLogConfirm(final byte[] byteArray)
        throws InvalidLLRPMessageException {
        decodeBinary(byteArray);
    }

    /**
    * Constructor to create message from xml encoded message
    * calls decodeXML to decode message.
    * @param document to be decoded
    */
    public UploadDeviceLogConfirm(final Document document)
        throws InvalidLLRPMessageException {
        decodeXML(document);
    }

    /**
    * {@inheritDoc}
    */
    protected LLRPBitList encodeBinarySpecific()
        throws InvalidLLRPMessageException {
        LLRPBitList resultBits = new LLRPBitList();

        if (sequenceId == null) {
            LOGGER.warn(" sequenceId not set");
            throw new InvalidLLRPMessageException(
                " sequenceId not set  for Parameter of Type UploadDeviceLogConfirm");
        }

        resultBits.append(sequenceId.encodeBinary());

        return resultBits;
    }

    /**
    * {@inheritDoc}
    */
    public Document encodeXML() throws InvalidLLRPMessageException {
        try {
            Namespace ns = Namespace.getNamespace("",
                    LLRPConstants.LLRPNAMESPACE);

            Element root = new Element("UploadDeviceLogConfirm", ns);
            //	Element root = new Element("UploadDeviceLogConfirm");
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

            if (sequenceId == null) {
                LOGGER.warn(" sequenceId not set");
                throw new MissingParameterException(" sequenceId not set");
            } else {
                root.addContent(sequenceId.encodeXML("SequenceId", ns));
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
        sequenceId = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
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
            temp = root.getChild("SequenceId", root.getNamespace());

            //check if this element exist
            if (temp != null) {
                sequenceId = new UnsignedShort(temp);
            } else {
                LOGGER.warn(
                    "Element sequenceId not provided in xml as child of " +
                    root.getName());
                throw new MissingParameterException(
                    "Element sequenceId not provided");
            }

            root.removeChild("SequenceId", root.getNamespace());

            if (root.getChildren().size() > 0) {
                String message = "UploadDeviceLogConfirm has unknown element " +
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
    * set   sequenceId of type UnsignedShort .
    * @param   sequenceId to be set
    */
    public void setSequenceId(final UnsignedShort sequenceId) {
        this.sequenceId = sequenceId;
    }

    // end setter

    //getters
    /**
    * get   sequenceId of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getSequenceId() {
        return this.sequenceId;
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
        return "UploadDeviceLogConfirm";
    }

    /**
    * return uniuque type number.
    * @return SignedShort holding unique type number
    */
    public SignedShort getTypeNum() {
        return TYPENUM;
    }
}
