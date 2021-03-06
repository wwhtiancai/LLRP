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
import org.llrp.ltk.generated.enumerations.AirProtocolType;
import org.llrp.ltk.generated.enumerations.RfSpecSelectType;
import org.llrp.ltk.generated.parameters.AntennaConfiguration;
import org.llrp.ltk.generated.parameters.MemoryBank;
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
public class RfSpec extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(408);
    private static final Logger LOGGER = Logger.getLogger(RfSpec.class);
    protected UnsignedShort rfSpecID;
    protected AirProtocolType protocolID;
    protected RfSpecSelectType selectType;
    protected MemoryBank memoryBank;
    protected List<AntennaConfiguration> antennaConfigurationList = new LinkedList<AntennaConfiguration>();

    /**
     * empty constructor to create new parameter.
     */
    public RfSpec() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public RfSpec(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public RfSpec(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (rfSpecID == null) {
            LOGGER.warn(" rfSpecID not set");
            throw new MissingParameterException(
                " rfSpecID not set  for Parameter of Type RfSpec");
        }

        resultBits.append(rfSpecID.encodeBinary());

        if (protocolID == null) {
            LOGGER.warn(" protocolID not set");
            throw new MissingParameterException(
                " protocolID not set  for Parameter of Type RfSpec");
        }

        resultBits.append(protocolID.encodeBinary());

        if (selectType == null) {
            LOGGER.warn(" selectType not set");
            throw new MissingParameterException(
                " selectType not set  for Parameter of Type RfSpec");
        }

        resultBits.append(selectType.encodeBinary());

        if (memoryBank == null) {
            // optional parameter, may be null
            LOGGER.info(" memoryBank not set");
        } else {
            resultBits.append(memoryBank.encodeBinary());
        }

        if (antennaConfigurationList == null) {
            //just warn - it is optional 
            LOGGER.info(" antennaConfigurationList not set");
        } else {
            for (AntennaConfiguration field : antennaConfigurationList) {
                resultBits.append(field.encodeBinary());
            }
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

        if (rfSpecID == null) {
            LOGGER.warn(" rfSpecID not set");
            throw new MissingParameterException(" rfSpecID not set");
        } else {
            element.addContent(rfSpecID.encodeXML("RfSpecID", ns));
        }

        if (protocolID == null) {
            LOGGER.warn(" protocolID not set");
            throw new MissingParameterException(" protocolID not set");
        } else {
            element.addContent(protocolID.encodeXML("ProtocolID", ns));
        }

        if (selectType == null) {
            LOGGER.warn(" selectType not set");
            throw new MissingParameterException(" selectType not set");
        } else {
            element.addContent(selectType.encodeXML("SelectType", ns));
        }

        //parameters
        if (memoryBank == null) {
            LOGGER.info("memoryBank not set");
        } else {
            element.addContent(memoryBank.encodeXML(memoryBank.getClass()
                                                              .getSimpleName(),
                    ns));
        }

        if (antennaConfigurationList == null) {
            LOGGER.info("antennaConfigurationList not set");
        } else {
            for (AntennaConfiguration field : antennaConfigurationList) {
                element.addContent(field.encodeXML(field.getClass().getName()
                                                        .replaceAll(field.getClass()
                                                                         .getPackage()
                                                                         .getName() +
                            ".", ""), ns));
            }
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
        rfSpecID = new UnsignedShort(binary.subList(position,
                    UnsignedShort.length()));
        position += UnsignedShort.length();
        protocolID = new AirProtocolType(binary.subList(position,
                    AirProtocolType.length()));
        position += AirProtocolType.length();
        selectType = new RfSpecSelectType(binary.subList(position,
                    RfSpecSelectType.length()));
        position += RfSpecSelectType.length();

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
            LOGGER.info("RfSpec misses optional parameter of type MemoryBank");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = memoryBank.length();
        }

        if ((type != null) && type.equals(MemoryBank.TYPENUM)) {
            memoryBank = new MemoryBank(binary.subList(position, tempLength));
            position += tempLength;
            LOGGER.debug(
                " memoryBank is instantiated with MemoryBank with length" +
                tempLength);
        } else {
            LOGGER.info("RfSpec misses optional parameter of type MemoryBank");
        }

        // list of parameters
        antennaConfigurationList = new LinkedList<AntennaConfiguration>();
        LOGGER.debug("decoding parameter antennaConfigurationList ");

        while (position < binary.length()) {
            // store if one parameter matched
            boolean atLeastOnce = false;

            // look ahead to see type
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

            //add parameter to list if type number matches
            if ((type != null) && type.equals(AntennaConfiguration.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = AntennaConfiguration.length();
                }

                antennaConfigurationList.add(new AntennaConfiguration(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding AntennaConfiguration to antennaConfigurationList ");
                atLeastOnce = true;
                position += tempLength;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (antennaConfigurationList.isEmpty()) {
            LOGGER.info(
                "encoded message does not contain parameter for optional antennaConfigurationList");
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

        temp = element.getChild("RfSpecID", ns);

        if (temp != null) {
            rfSpecID = new UnsignedShort(temp);
        }

        element.removeChild("RfSpecID", ns);
        temp = element.getChild("ProtocolID", ns);

        if (temp != null) {
            protocolID = new AirProtocolType(temp);
        }

        element.removeChild("ProtocolID", ns);
        temp = element.getChild("SelectType", ns);

        if (temp != null) {
            selectType = new RfSpecSelectType(temp);
        }

        element.removeChild("SelectType", ns);

        //parameter - not choices - no special actions needed
        temp = element.getChild("MemoryBank", ns);

        if (temp != null) {
            memoryBank = new MemoryBank(temp);
            LOGGER.info("setting parameter memoryBank for parameter RfSpec");
        }

        if (temp == null) {
            LOGGER.info("RfSpec misses optional parameter of type memoryBank");
        }

        element.removeChild("MemoryBank", ns);
        //parameter - not choices - no special actions needed
        //we expect a list of parameters
        antennaConfigurationList = new LinkedList<AntennaConfiguration>();
        tempList = element.getChildren("AntennaConfiguration", ns);

        if ((tempList == null) || tempList.isEmpty()) {
            LOGGER.info(
                "RfSpec misses optional parameter of type antennaConfigurationList");
        } else {
            for (Element e : tempList) {
                antennaConfigurationList.add(new AntennaConfiguration(e));
                LOGGER.debug(
                    "adding AntennaConfiguration to antennaConfigurationList ");
            }
        }

        element.removeChildren("AntennaConfiguration", ns);

        if (element.getChildren().size() > 0) {
            String message = "RfSpec has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   rfSpecID of type UnsignedShort .
    * @param   rfSpecID to be set
    */
    public void setRfSpecID(final UnsignedShort rfSpecID) {
        this.rfSpecID = rfSpecID;
    }

    /**
    * set protocolID of type AirProtocolType .
    * @param  protocolID to be set
    */
    public void setProtocolID(final AirProtocolType protocolID) {
        this.protocolID = protocolID;
    }

    /**
    * set selectType of type RfSpecSelectType .
    * @param  selectType to be set
    */
    public void setSelectType(final RfSpecSelectType selectType) {
        this.selectType = selectType;
    }

    /**
    * set memoryBank of type MemoryBank.
    * @param  memoryBank to be set
    */
    public void setMemoryBank(final MemoryBank memoryBank) {
        this.memoryBank = memoryBank;
    }

    /**
    * set antennaConfigurationList of type  List &lt;AntennaConfiguration>.
    * @param  antennaConfigurationList to be set
    */
    public void setAntennaConfigurationList(
        final List<AntennaConfiguration> antennaConfigurationList) {
        this.antennaConfigurationList = antennaConfigurationList;
    }

    // end setter

    //getters
    /**
    * get   rfSpecID of type UnsignedShort.
    * @return   type UnsignedShort to be set
    */
    public UnsignedShort getRfSpecID() {
        return this.rfSpecID;
    }

    /**
    * get protocolID of type AirProtocolType.
    * @return  AirProtocolType
    */
    public AirProtocolType getProtocolID() {
        return protocolID;
    }

    /**
    * get selectType of type RfSpecSelectType.
    * @return  RfSpecSelectType
    */
    public RfSpecSelectType getSelectType() {
        return selectType;
    }

    /**
    * get memoryBank of type MemoryBank .
    * @return  MemoryBank
    */
    public MemoryBank getMemoryBank() {
        return memoryBank;
    }

    /**
    * get antennaConfigurationList of type List &lt;AntennaConfiguration> .
    * @return  List &lt;AntennaConfiguration>
    */
    public List<AntennaConfiguration> getAntennaConfigurationList() {
        return antennaConfigurationList;
    }

    // end getters

    //add methods

    /**
    * add element antennaConfiguration of type AntennaConfiguration .
    * @param  antennaConfiguration of type AntennaConfiguration
    */
    public void addToAntennaConfigurationList(
        AntennaConfiguration antennaConfiguration) {
        if (this.antennaConfigurationList == null) {
            this.antennaConfigurationList = new LinkedList<AntennaConfiguration>();
        }

        this.antennaConfigurationList.add(antennaConfiguration);
    }

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
        return "RfSpec";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "RfSpec: ";
        result += ", rfSpecID: ";
        result += rfSpecID;
        result += ", protocolID: ";
        result += protocolID;
        result += ", selectType: ";
        result += selectType;
        result = result.replaceFirst(", ", "");

        return result;
    }
}
