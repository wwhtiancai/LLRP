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
import org.llrp.ltk.generated.parameters.DiagnosticTestItem;
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
public class DiagnosticTest extends LLRPMessage {
    public static final SignedShort TYPENUM = new SignedShort(740);
    private static final Logger LOGGER = Logger.getLogger(DiagnosticTest.class);
    public static final String RESPONSETYPE = "";
    protected List<DiagnosticTestItem> diagnosticTestItemList = new LinkedList<DiagnosticTestItem>();

    /**
     * empty constructor to create new message
     * with LLRP version set to 1.0 (0x1).
     */
    public DiagnosticTest() {
        //		setVersion(new BitList(0,0,1));
        setVersion(new BitList(0, 0, 0, 0, 0, 0, 0, 1)); // change by wuwh 
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param list to be decoded
     */
    public DiagnosticTest(final LLRPBitList list)
        throws InvalidLLRPMessageException {
        decodeBinary(list.toByteArray());
    }

    /**
     * Constructor to create message from binary encoded message
     * calls decodeBinary to decode message.
     * @param byteArray representing message
     */
    public DiagnosticTest(final byte[] byteArray)
        throws InvalidLLRPMessageException {
        decodeBinary(byteArray);
    }

    /**
    * Constructor to create message from xml encoded message
    * calls decodeXML to decode message.
    * @param document to be decoded
    */
    public DiagnosticTest(final Document document)
        throws InvalidLLRPMessageException {
        decodeXML(document);
    }

    /**
    * {@inheritDoc}
    */
    protected LLRPBitList encodeBinarySpecific()
        throws InvalidLLRPMessageException {
        LLRPBitList resultBits = new LLRPBitList();

        if (diagnosticTestItemList == null) {
            LOGGER.warn(" diagnosticTestItemList not set");

            //parameter has to be set - throw exception
            throw new InvalidLLRPMessageException(
                " diagnosticTestItemList not set");
        } else {
            for (DiagnosticTestItem field : diagnosticTestItemList) {
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

            Element root = new Element("DiagnosticTest", ns);
            //	Element root = new Element("DiagnosticTest");
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
            if (diagnosticTestItemList == null) {
                LOGGER.warn(" diagnosticTestItemList not set");
                throw new MissingParameterException(
                    "  diagnosticTestItemList not set");
            }

            for (DiagnosticTestItem field : diagnosticTestItemList) {
                root.addContent(field.encodeXML(field.getClass().getName()
                                                     .replaceAll(field.getClass()
                                                                      .getPackage()
                                                                      .getName() +
                            ".", ""), ns));
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

        // list of parameters
        diagnosticTestItemList = new LinkedList<DiagnosticTestItem>();
        LOGGER.debug("decoding parameter diagnosticTestItemList ");

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
            if ((type != null) && type.equals(DiagnosticTestItem.TYPENUM)) {
                //if first bit is 1 it is a TV Parameter
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = DiagnosticTestItem.length();
                }

                diagnosticTestItemList.add(new DiagnosticTestItem(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding DiagnosticTestItem to diagnosticTestItemList ");
                atLeastOnce = true;
                position += tempLength;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (diagnosticTestItemList.isEmpty()) {
            LOGGER.warn(
                "encoded message does not contain parameter for non optional diagnosticTestItemList");
            throw new InvalidLLRPMessageException(
                "DiagnosticTest misses non optional parameter of type DiagnosticTestItem");
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
            //we expect a list of parameters
            diagnosticTestItemList = new LinkedList<DiagnosticTestItem>();
            tempList = root.getChildren("DiagnosticTestItem", ns);

            if ((tempList == null) || tempList.isEmpty()) {
                LOGGER.warn(
                    "DiagnosticTest misses non optional parameter of type diagnosticTestItemList");
                throw new MissingParameterException(
                    "DiagnosticTest misses non optional parameter of type diagnosticTestItemList");
            } else {
                for (Element e : tempList) {
                    diagnosticTestItemList.add(new DiagnosticTestItem(e));
                    LOGGER.debug(
                        "adding DiagnosticTestItem to diagnosticTestItemList ");
                }
            }

            root.removeChildren("DiagnosticTestItem", ns);

            if (root.getChildren().size() > 0) {
                String message = "DiagnosticTest has unknown element " +
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
    * set diagnosticTestItemList of type  List &lt;DiagnosticTestItem>.
    * @param  diagnosticTestItemList to be set
    */
    public void setDiagnosticTestItemList(
        final List<DiagnosticTestItem> diagnosticTestItemList) {
        this.diagnosticTestItemList = diagnosticTestItemList;
    }

    // end setter

    //getters

    /**
    * get diagnosticTestItemList of type List &lt;DiagnosticTestItem> .
    * @return  List &lt;DiagnosticTestItem>
    */
    public List<DiagnosticTestItem> getDiagnosticTestItemList() {
        return diagnosticTestItemList;
    }

    // end getters

    //add methods

    /**
    * add element diagnosticTestItem of type DiagnosticTestItem .
    * @param  diagnosticTestItem of type DiagnosticTestItem
    */
    public void addToDiagnosticTestItemList(
        DiagnosticTestItem diagnosticTestItem) {
        if (this.diagnosticTestItemList == null) {
            this.diagnosticTestItemList = new LinkedList<DiagnosticTestItem>();
        }

        this.diagnosticTestItemList.add(diagnosticTestItem);
    }

    // end add
    @Override
    public String getResponseType() {
        return RESPONSETYPE;
    }

    @Override
    public String getName() {
        return "DiagnosticTest";
    }

    /**
    * return uniuque type number.
    * @return SignedShort holding unique type number
    */
    public SignedShort getTypeNum() {
        return TYPENUM;
    }
}