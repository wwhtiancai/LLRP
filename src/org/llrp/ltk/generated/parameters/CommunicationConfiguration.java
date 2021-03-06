/*
 *
 * This file was generated by LLRP Code Generator
 * see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit
 * for more information
 * Generated on: Mon Apr 10 16:16:18 CST 2017;
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
import org.llrp.ltk.generated.interfaces.EthernetConfiguration;
import org.llrp.ltk.generated.parameters.CommLinkConfiguration;
import org.llrp.ltk.generated.parameters.EthernetIpv4Configuration;
import org.llrp.ltk.generated.parameters.EthernetIpv6Configuration;
import org.llrp.ltk.generated.parameters.NTPConfiguration;
import org.llrp.ltk.generated.parameters.SerialPortConfiguration;
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
public class CommunicationConfiguration extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(668);
    private static final Logger LOGGER = Logger.getLogger(CommunicationConfiguration.class);
    protected List<CommLinkConfiguration> commLinkConfigurationList = new LinkedList<CommLinkConfiguration>();
    protected List<EthernetConfiguration> ethernetConfigurationList = new LinkedList<EthernetConfiguration>();
    protected List<SerialPortConfiguration> serialPortConfigurationList = new LinkedList<SerialPortConfiguration>();
    protected NTPConfiguration nTPConfiguration;

    /**
     * empty constructor to create new parameter.
     */
    public CommunicationConfiguration() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public CommunicationConfiguration(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public CommunicationConfiguration(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (commLinkConfigurationList == null) {
            //just warn - it is optional 
            LOGGER.info(" commLinkConfigurationList not set");
        } else {
            for (CommLinkConfiguration field : commLinkConfigurationList) {
                resultBits.append(field.encodeBinary());
            }
        }

        if (ethernetConfigurationList == null) {
            //just warn - it is optional 
            LOGGER.info(" ethernetConfigurationList not set");
        } else {
            for (EthernetConfiguration field : ethernetConfigurationList) {
                resultBits.append(field.encodeBinary());
            }
        }

        if (serialPortConfigurationList == null) {
            //just warn - it is optional 
            LOGGER.info(" serialPortConfigurationList not set");
        } else {
            for (SerialPortConfiguration field : serialPortConfigurationList) {
                resultBits.append(field.encodeBinary());
            }
        }

        if (nTPConfiguration == null) {
            // optional parameter, may be null
            LOGGER.info(" nTPConfiguration not set");
        } else {
            resultBits.append(nTPConfiguration.encodeBinary());
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

        //parameters
        if (commLinkConfigurationList == null) {
            LOGGER.info("commLinkConfigurationList not set");
        } else {
            for (CommLinkConfiguration field : commLinkConfigurationList) {
                element.addContent(field.encodeXML(field.getClass().getName()
                                                        .replaceAll(field.getClass()
                                                                         .getPackage()
                                                                         .getName() +
                            ".", ""), ns));
            }
        }

        if (ethernetConfigurationList == null) {
            LOGGER.info("ethernetConfigurationList not set");
        } else {
            for (EthernetConfiguration field : ethernetConfigurationList) {
                element.addContent(field.encodeXML(field.getClass().getName()
                                                        .replaceAll(field.getClass()
                                                                         .getPackage()
                                                                         .getName() +
                            ".", ""), ns));
            }
        }

        if (serialPortConfigurationList == null) {
            LOGGER.info("serialPortConfigurationList not set");
        } else {
            for (SerialPortConfiguration field : serialPortConfigurationList) {
                element.addContent(field.encodeXML(field.getClass().getName()
                                                        .replaceAll(field.getClass()
                                                                         .getPackage()
                                                                         .getName() +
                            ".", ""), ns));
            }
        }

        if (nTPConfiguration == null) {
            LOGGER.info("nTPConfiguration not set");
        } else {
            element.addContent(nTPConfiguration.encodeXML(
                    nTPConfiguration.getClass().getSimpleName(), ns));
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

        // list of parameters
        commLinkConfigurationList = new LinkedList<CommLinkConfiguration>();
        LOGGER.debug("decoding parameter commLinkConfigurationList ");

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
            if ((type != null) && type.equals(CommLinkConfiguration.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = CommLinkConfiguration.length();
                }

                commLinkConfigurationList.add(new CommLinkConfiguration(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding CommLinkConfiguration to commLinkConfigurationList ");
                atLeastOnce = true;
                position += tempLength;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (commLinkConfigurationList.isEmpty()) {
            LOGGER.info(
                "encoded message does not contain parameter for optional commLinkConfigurationList");
        }

        // list of parameters
        ethernetConfigurationList = new LinkedList<EthernetConfiguration>();
        LOGGER.debug("decoding parameter ethernetConfigurationList ");

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

            //choiceRef
            if ((type != null) &&
                    type.equals(EthernetIpv4Configuration.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = EthernetIpv4Configuration.length();
                }

                ethernetConfigurationList.add(new EthernetIpv4Configuration(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding EthernetIpv4Configuration to ethernetConfigurationList ");
                position += tempLength;
                atLeastOnce = true;
            }

            if ((type != null) &&
                    type.equals(EthernetIpv6Configuration.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = EthernetIpv6Configuration.length();
                }

                ethernetConfigurationList.add(new EthernetIpv6Configuration(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding EthernetIpv6Configuration to ethernetConfigurationList ");
                position += tempLength;
                atLeastOnce = true;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (ethernetConfigurationList.isEmpty()) {
            LOGGER.info(
                "encoded message does not contain parameter for optional ethernetConfigurationList");
        }

        // list of parameters
        serialPortConfigurationList = new LinkedList<SerialPortConfiguration>();
        LOGGER.debug("decoding parameter serialPortConfigurationList ");

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
            if ((type != null) && type.equals(SerialPortConfiguration.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = SerialPortConfiguration.length();
                }

                serialPortConfigurationList.add(new SerialPortConfiguration(
                        binary.subList(position, tempLength)));
                LOGGER.debug(
                    "adding SerialPortConfiguration to serialPortConfigurationList ");
                atLeastOnce = true;
                position += tempLength;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (serialPortConfigurationList.isEmpty()) {
            LOGGER.info(
                "encoded message does not contain parameter for optional serialPortConfigurationList");
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
            LOGGER.info(
                "CommunicationConfiguration misses optional parameter of type NTPConfiguration");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = nTPConfiguration.length();
        }

        if ((type != null) && type.equals(NTPConfiguration.TYPENUM)) {
            nTPConfiguration = new NTPConfiguration(binary.subList(position,
                        tempLength));
            position += tempLength;
            LOGGER.debug(
                " nTPConfiguration is instantiated with NTPConfiguration with length" +
                tempLength);
        } else {
            LOGGER.info(
                "CommunicationConfiguration misses optional parameter of type NTPConfiguration");
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

        //parameter - not choices - no special actions needed
        //we expect a list of parameters
        commLinkConfigurationList = new LinkedList<CommLinkConfiguration>();
        tempList = element.getChildren("CommLinkConfiguration", ns);

        if ((tempList == null) || tempList.isEmpty()) {
            LOGGER.info(
                "CommunicationConfiguration misses optional parameter of type commLinkConfigurationList");
        } else {
            for (Element e : tempList) {
                commLinkConfigurationList.add(new CommLinkConfiguration(e));
                LOGGER.debug(
                    "adding CommLinkConfiguration to commLinkConfigurationList ");
            }
        }

        element.removeChildren("CommLinkConfiguration", ns);
        //choices - must check all possible subtypes
        //list of Choice Type Parameter
        ethernetConfigurationList = new LinkedList<EthernetConfiguration>();
        // for each possible subtype get all childs
        tempList = element.getChildren("EthernetIpv4Configuration", ns);

        for (Element e : tempList) {
            ethernetConfigurationList.add(new EthernetIpv4Configuration(e));
            LOGGER.debug(
                "adding EthernetIpv4Configuration to ethernetConfigurationList ");
            atLeastOnce = true;
        }

        element.removeChildren("EthernetIpv4Configuration", ns);

        tempList = element.getChildren("EthernetIpv6Configuration", ns);

        for (Element e : tempList) {
            ethernetConfigurationList.add(new EthernetIpv6Configuration(e));
            LOGGER.debug(
                "adding EthernetIpv6Configuration to ethernetConfigurationList ");
            atLeastOnce = true;
        }

        element.removeChildren("EthernetIpv6Configuration", ns);

        if (!atLeastOnce) {
            LOGGER.info(
                "CommunicationConfiguration misses optional parameter of type ethernetConfigurationList");
        }

        atLeastOnce = false;
        //parameter - not choices - no special actions needed
        //we expect a list of parameters
        serialPortConfigurationList = new LinkedList<SerialPortConfiguration>();
        tempList = element.getChildren("SerialPortConfiguration", ns);

        if ((tempList == null) || tempList.isEmpty()) {
            LOGGER.info(
                "CommunicationConfiguration misses optional parameter of type serialPortConfigurationList");
        } else {
            for (Element e : tempList) {
                serialPortConfigurationList.add(new SerialPortConfiguration(e));
                LOGGER.debug(
                    "adding SerialPortConfiguration to serialPortConfigurationList ");
            }
        }

        element.removeChildren("SerialPortConfiguration", ns);
        //parameter - not choices - no special actions needed
        temp = element.getChild("NTPConfiguration", ns);

        if (temp != null) {
            nTPConfiguration = new NTPConfiguration(temp);
            LOGGER.info(
                "setting parameter nTPConfiguration for parameter CommunicationConfiguration");
        }

        if (temp == null) {
            LOGGER.info(
                "CommunicationConfiguration misses optional parameter of type nTPConfiguration");
        }

        element.removeChild("NTPConfiguration", ns);

        if (element.getChildren().size() > 0) {
            String message = "CommunicationConfiguration has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters

    /**
    * set commLinkConfigurationList of type  List &lt;CommLinkConfiguration>.
    * @param  commLinkConfigurationList to be set
    */
    public void setCommLinkConfigurationList(
        final List<CommLinkConfiguration> commLinkConfigurationList) {
        this.commLinkConfigurationList = commLinkConfigurationList;
    }

    /**
    * set ethernetConfigurationList of type  List &lt;EthernetConfiguration>.
    * @param  ethernetConfigurationList to be set
    */
    public void setEthernetConfigurationList(
        final List<EthernetConfiguration> ethernetConfigurationList) {
        this.ethernetConfigurationList = ethernetConfigurationList;
    }

    /**
    * set serialPortConfigurationList of type  List &lt;SerialPortConfiguration>.
    * @param  serialPortConfigurationList to be set
    */
    public void setSerialPortConfigurationList(
        final List<SerialPortConfiguration> serialPortConfigurationList) {
        this.serialPortConfigurationList = serialPortConfigurationList;
    }

    /**
    * set nTPConfiguration of type NTPConfiguration.
    * @param  nTPConfiguration to be set
    */
    public void setNTPConfiguration(final NTPConfiguration nTPConfiguration) {
        this.nTPConfiguration = nTPConfiguration;
    }

    // end setter

    //getters

    /**
    * get commLinkConfigurationList of type List &lt;CommLinkConfiguration> .
    * @return  List &lt;CommLinkConfiguration>
    */
    public List<CommLinkConfiguration> getCommLinkConfigurationList() {
        return commLinkConfigurationList;
    }

    /**
    * get ethernetConfigurationList of type List &lt;EthernetConfiguration> .
    * @return  List &lt;EthernetConfiguration>
    */
    public List<EthernetConfiguration> getEthernetConfigurationList() {
        return ethernetConfigurationList;
    }

    /**
    * get serialPortConfigurationList of type List &lt;SerialPortConfiguration> .
    * @return  List &lt;SerialPortConfiguration>
    */
    public List<SerialPortConfiguration> getSerialPortConfigurationList() {
        return serialPortConfigurationList;
    }

    /**
    * get nTPConfiguration of type NTPConfiguration .
    * @return  NTPConfiguration
    */
    public NTPConfiguration getNTPConfiguration() {
        return nTPConfiguration;
    }

    // end getters

    //add methods

    /**
    * add element commLinkConfiguration of type CommLinkConfiguration .
    * @param  commLinkConfiguration of type CommLinkConfiguration
    */
    public void addToCommLinkConfigurationList(
        CommLinkConfiguration commLinkConfiguration) {
        if (this.commLinkConfigurationList == null) {
            this.commLinkConfigurationList = new LinkedList<CommLinkConfiguration>();
        }

        this.commLinkConfigurationList.add(commLinkConfiguration);
    }

    /**
    * add element ethernetConfiguration of type EthernetConfiguration .
    * @param  ethernetConfiguration of type EthernetConfiguration
    */
    public void addToEthernetConfigurationList(
        EthernetConfiguration ethernetConfiguration) {
        if (this.ethernetConfigurationList == null) {
            this.ethernetConfigurationList = new LinkedList<EthernetConfiguration>();
        }

        this.ethernetConfigurationList.add(ethernetConfiguration);
    }

    /**
    * add element serialPortConfiguration of type SerialPortConfiguration .
    * @param  serialPortConfiguration of type SerialPortConfiguration
    */
    public void addToSerialPortConfigurationList(
        SerialPortConfiguration serialPortConfiguration) {
        if (this.serialPortConfigurationList == null) {
            this.serialPortConfigurationList = new LinkedList<SerialPortConfiguration>();
        }

        this.serialPortConfigurationList.add(serialPortConfiguration);
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
        return "CommunicationConfiguration";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "CommunicationConfiguration: ";
        result = result.replaceFirst(", ", "");

        return result;
    }
}
