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
import org.llrp.ltk.generated.enumerations.EnumIsLastedFrame;
import org.llrp.ltk.generated.parameters.Status;
import org.llrp.ltk.generated.parameters.TagLog;
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
public class UploadTagLogAck extends LLRPMessage {
    public static final SignedShort TYPENUM = new SignedShort(621);
    private static final Logger LOGGER = Logger.getLogger(UploadTagLogAck.class);
    public static final String RESPONSETYPE = "";
    protected UnsignedShort sequenceId;
    protected EnumIsLastedFrame isLastedFrame;
    protected Status status;
    protected List<TagLog> tagLogList = new LinkedList<TagLog>();

    /**
     * empty constructor to create new message
     * with LLRP version set to 1.0 (0x1).
     */
    public UploadTagLogAck() {
        //		setVersion(new BitList(0,0,1));
        setVersion(new BitList(0, 0, 0, 0, 0, 0, 0, 1)); // change by wuwh 
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param list to be decoded
     */
    public UploadTagLogAck(final LLRPBitList list)
        throws InvalidLLRPMessageException {
        decodeBinary(list.toByteArray());
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param byteArray representing message
     */
    public UploadTagLogAck(final byte[] byteArray)
        throws InvalidLLRPMessageException {
        decodeBinary(byteArray);
    }

    /**
    * Constructor to create message from xml encoded message
    * calls decodeXML to decode message.
    * @param document to be decoded
    */
    public UploadTagLogAck(final Document document)
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
                " sequenceId not set  for Parameter of Type UploadTagLogAck");
        }

        resultBits.append(sequenceId.encodeBinary());

        if (isLastedFrame == null) {
            LOGGER.warn(" isLastedFrame not set");
            throw new InvalidLLRPMessageException(
                " isLastedFrame not set  for Parameter of Type UploadTagLogAck");
        }

        resultBits.append(isLastedFrame.encodeBinary());

        if (status == null) {
            // single parameter, may not be null
            LOGGER.warn(" status not set");
            throw new InvalidLLRPMessageException(" status not set");
        } else {
            resultBits.append(status.encodeBinary());
        }

        if (tagLogList == null) {
            //just warn - it is optional 
            LOGGER.info(" tagLogList not set");
        } else {
            for (TagLog field : tagLogList) {
                resultBits.append(field.encodeBinary());
            }
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

            Element root = new Element("UploadTagLogAck", ns);
            //	Element root = new Element("UploadTagLogAck");
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

            if (isLastedFrame == null) {
                LOGGER.warn(" isLastedFrame not set");
                throw new MissingParameterException(" isLastedFrame not set");
            } else {
                root.addContent(isLastedFrame.encodeXML("IsLastedFrame", ns));
            }

            //parameters
            if (status == null) {
                LOGGER.info("status not set");
                throw new MissingParameterException("status not set");
            } else {
                root.addContent(status.encodeXML(status.getClass()
                                                       .getSimpleName(), ns));
            }

            if (tagLogList == null) {
                LOGGER.info("tagLogList not set");
            } else {
                for (TagLog field : tagLogList) {
                    root.addContent(field.encodeXML(field.getClass().getName()
                                                         .replaceAll(field.getClass()
                                                                          .getPackage()
                                                                          .getName() +
                                ".", ""), ns));
                }
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
        sequenceId = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        isLastedFrame = new EnumIsLastedFrame(binary.subList(position,
                    EnumIsLastedFrame.length()));
        position += EnumIsLastedFrame.length();

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
                "UploadTagLogAck misses non optional parameter of type Status");
            throw new InvalidLLRPMessageException(
                "UploadTagLogAck misses non optional parameter of type Status");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = status.length();
        }

        if ((type != null) && type.equals(Status.TYPENUM)) {
            status = new Status(binary.subList(position, tempLength));
            position += tempLength;
            LOGGER.debug(" status is instantiated with Status with length" +
                tempLength);
        } else {
            LOGGER.warn(
                "UploadTagLogAck misses non optional parameter of type Status");
            throw new InvalidLLRPMessageException(
                "UploadTagLogAck misses non optional parameter of type Status");
        }

        // list of parameters
        tagLogList = new LinkedList<TagLog>();
        LOGGER.debug("decoding parameter tagLogList ");

        while (position < binary.length()) {
            // store if one parameter matched
            boolean atLeastOnce = false;

            // look ahead to see type
            // if first bit is one it is a TV Parameter
            if (binary.get(position)) {
                // do not take the first bit as it is always 1
                type = new SignedShort(binary.subList(position + 1, 7));
            } else {
                //type = new SignedShort(binary.subList(position+RESERVEDLENGTH, TYPENUMBERLENGTH));
                //	tempByteLength = new UnsignedShort(binary.subList(position+RESERVEDLENGTH+ TYPENUMBERLENGTH, UnsignedShort.length())).toShort();			
                //tempLength=8*tempByteLength;
                type = new SignedShort(binary.subList(position,
                            PARAMETERTYPELENGTH)); //change by wuwh
                tempByteLength = new UnsignedShort(binary.subList(position +
                            PARAMETERTYPELENGTH, UnsignedShort.length())).toShort(); //change by wuwh		
                tempLength = 8 * (tempByteLength + 4); //change by wuwh
            }

            //add parameter to list if type number matches
            if ((type != null) && type.equals(TagLog.TYPENUM)) {
                //if first bit is 1 it is a TV Parameter
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = TagLog.length();
                }

                tagLogList.add(new TagLog(binary.subList(position, tempLength)));
                LOGGER.debug("adding TagLog to tagLogList ");
                atLeastOnce = true;
                position += tempLength;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (tagLogList.isEmpty()) {
            LOGGER.info(
                "encoded message does not contain parameter for optional tagLogList");
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
            temp = root.getChild("IsLastedFrame", root.getNamespace());

            //check if this element exist
            if (temp != null) {
                isLastedFrame = new EnumIsLastedFrame(temp);
            } else {
                LOGGER.warn(
                    "Element isLastedFrame not provided in xml as child of " +
                    root.getName());
                throw new MissingParameterException(
                    "Element isLastedFrame not provided");
            }

            root.removeChild("IsLastedFrame", root.getNamespace());

            //parameter - not choices - no special actions needed
            temp = root.getChild("Status", ns);

            if (temp != null) {
                status = new Status(temp);
                LOGGER.info(
                    "setting parameter status for parameter UploadTagLogAck");
            } else {
                LOGGER.warn(
                    "UploadTagLogAck misses non optional parameter of type status");
                throw new MissingParameterException(
                    "UploadTagLogAck misses non optional parameter of type status");
            }

            root.removeChild("Status", ns);
            //parameter - not choices - no special actions needed
            //we expect a list of parameters
            tagLogList = new LinkedList<TagLog>();
            tempList = root.getChildren("TagLog", ns);

            if ((tempList == null) || tempList.isEmpty()) {
                LOGGER.info(
                    "UploadTagLogAck misses optional parameter of type tagLogList");
            } else {
                for (Element e : tempList) {
                    tagLogList.add(new TagLog(e));
                    LOGGER.debug("adding TagLog to tagLogList ");
                }
            }

            root.removeChildren("TagLog", ns);

            if (root.getChildren().size() > 0) {
                String message = "UploadTagLogAck has unknown element " +
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

    /**
    * set isLastedFrame of type EnumIsLastedFrame .
    * @param  isLastedFrame to be set
    */
    public void setIsLastedFrame(final EnumIsLastedFrame isLastedFrame) {
        this.isLastedFrame = isLastedFrame;
    }

    /**
    * set status of type Status.
    * @param  status to be set
    */
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
    * set tagLogList of type  List &lt;TagLog>.
    * @param  tagLogList to be set
    */
    public void setTagLogList(final List<TagLog> tagLogList) {
        this.tagLogList = tagLogList;
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

    /**
    * get isLastedFrame of type EnumIsLastedFrame.
    * @return  EnumIsLastedFrame
    */
    public EnumIsLastedFrame getIsLastedFrame() {
        return isLastedFrame;
    }

    /**
    * get status of type Status .
    * @return  Status
    */
    public Status getStatus() {
        return status;
    }

    /**
    * get tagLogList of type List &lt;TagLog> .
    * @return  List &lt;TagLog>
    */
    public List<TagLog> getTagLogList() {
        return tagLogList;
    }

    // end getters

    //add methods

    /**
    * add element tagLog of type TagLog .
    * @param  tagLog of type TagLog
    */
    public void addToTagLogList(TagLog tagLog) {
        if (this.tagLogList == null) {
            this.tagLogList = new LinkedList<TagLog>();
        }

        this.tagLogList.add(tagLog);
    }

    // end add
    @Override
    public String getResponseType() {
        return RESPONSETYPE;
    }

    @Override
    public String getName() {
        return "UploadTagLogAck";
    }

    /**
    * return uniuque type number.
    * @return SignedShort holding unique type number
    */
    public SignedShort getTypeNum() {
        return TYPENUM;
    }
}
