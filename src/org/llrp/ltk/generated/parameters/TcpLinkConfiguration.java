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
import org.llrp.ltk.generated.enumerations.TcpLinkCommMode;
import org.llrp.ltk.generated.parameters.ClientModeConfiguration;
import org.llrp.ltk.generated.parameters.ServerModeConfiguration;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.BitList;
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
public class TcpLinkConfiguration extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(671);
    private static final Logger LOGGER = Logger.getLogger(TcpLinkConfiguration.class);
    protected TcpLinkCommMode commMode;
    protected Bit isSSL;
    protected BitList reserved0 = new BitList(7);
    protected ClientModeConfiguration clientModeConfiguration;
    protected ServerModeConfiguration serverModeConfiguration;

    /**
     * empty constructor to create new parameter.
     */
    public TcpLinkConfiguration() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public TcpLinkConfiguration(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public TcpLinkConfiguration(Element element)
        throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (commMode == null) {
            LOGGER.warn(" commMode not set");
            throw new MissingParameterException(
                " commMode not set  for Parameter of Type TcpLinkConfiguration");
        }

        resultBits.append(commMode.encodeBinary());

        if (isSSL == null) {
            LOGGER.warn(" isSSL not set");
            throw new MissingParameterException(
                " isSSL not set  for Parameter of Type TcpLinkConfiguration");
        }

        resultBits.append(isSSL.encodeBinary());
        resultBits.append(reserved0.encodeBinary());

        if (clientModeConfiguration == null) {
            // optional parameter, may be null
            LOGGER.info(" clientModeConfiguration not set");
        } else {
            resultBits.append(clientModeConfiguration.encodeBinary());
        }

        if (serverModeConfiguration == null) {
            // optional parameter, may be null
            LOGGER.info(" serverModeConfiguration not set");
        } else {
            resultBits.append(serverModeConfiguration.encodeBinary());
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

        if (commMode == null) {
            LOGGER.warn(" commMode not set");
            throw new MissingParameterException(" commMode not set");
        } else {
            element.addContent(commMode.encodeXML("CommMode", ns));
        }

        if (isSSL == null) {
            LOGGER.warn(" isSSL not set");
            throw new MissingParameterException(" isSSL not set");
        } else {
            element.addContent(isSSL.encodeXML("IsSSL", ns));
        }

        //element.addContent(reserved0.encodeXML("reserved",ns));
        //parameters
        if (clientModeConfiguration == null) {
            LOGGER.info("clientModeConfiguration not set");
        } else {
            element.addContent(clientModeConfiguration.encodeXML(
                    clientModeConfiguration.getClass().getSimpleName(), ns));
        }

        if (serverModeConfiguration == null) {
            LOGGER.info("serverModeConfiguration not set");
        } else {
            element.addContent(serverModeConfiguration.encodeXML(
                    serverModeConfiguration.getClass().getSimpleName(), ns));
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
        commMode = new TcpLinkCommMode(binary.subList(position,
                    TcpLinkCommMode.length()));
        position += TcpLinkCommMode.length();
        isSSL = new Bit(binary.subList(position, Bit.length()));
        position += Bit.length();
        position += reserved0.length();

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
                "TcpLinkConfiguration misses optional parameter of type ClientModeConfiguration");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = clientModeConfiguration.length();
        }

        if ((type != null) && type.equals(ClientModeConfiguration.TYPENUM)) {
            clientModeConfiguration = new ClientModeConfiguration(binary.subList(
                        position, tempLength));
            position += tempLength;
            LOGGER.debug(
                " clientModeConfiguration is instantiated with ClientModeConfiguration with length" +
                tempLength);
        } else {
            LOGGER.info(
                "TcpLinkConfiguration misses optional parameter of type ClientModeConfiguration");
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
                "TcpLinkConfiguration misses optional parameter of type ServerModeConfiguration");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = serverModeConfiguration.length();
        }

        if ((type != null) && type.equals(ServerModeConfiguration.TYPENUM)) {
            serverModeConfiguration = new ServerModeConfiguration(binary.subList(
                        position, tempLength));
            position += tempLength;
            LOGGER.debug(
                " serverModeConfiguration is instantiated with ServerModeConfiguration with length" +
                tempLength);
        } else {
            LOGGER.info(
                "TcpLinkConfiguration misses optional parameter of type ServerModeConfiguration");
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

        temp = element.getChild("CommMode", ns);

        if (temp != null) {
            commMode = new TcpLinkCommMode(temp);
        }

        element.removeChild("CommMode", ns);
        temp = element.getChild("IsSSL", ns);

        if (temp != null) {
            isSSL = new Bit(temp);
        }

        element.removeChild("IsSSL", ns);

        //parameter - not choices - no special actions needed
        temp = element.getChild("ClientModeConfiguration", ns);

        if (temp != null) {
            clientModeConfiguration = new ClientModeConfiguration(temp);
            LOGGER.info(
                "setting parameter clientModeConfiguration for parameter TcpLinkConfiguration");
        }

        if (temp == null) {
            LOGGER.info(
                "TcpLinkConfiguration misses optional parameter of type clientModeConfiguration");
        }

        element.removeChild("ClientModeConfiguration", ns);
        //parameter - not choices - no special actions needed
        temp = element.getChild("ServerModeConfiguration", ns);

        if (temp != null) {
            serverModeConfiguration = new ServerModeConfiguration(temp);
            LOGGER.info(
                "setting parameter serverModeConfiguration for parameter TcpLinkConfiguration");
        }

        if (temp == null) {
            LOGGER.info(
                "TcpLinkConfiguration misses optional parameter of type serverModeConfiguration");
        }

        element.removeChild("ServerModeConfiguration", ns);

        if (element.getChildren().size() > 0) {
            String message = "TcpLinkConfiguration has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set commMode of type TcpLinkCommMode .
    * @param  commMode to be set
    */
    public void setCommMode(final TcpLinkCommMode commMode) {
        this.commMode = commMode;
    }

    /**
    * set   isSSL of type Bit .
    * @param   isSSL to be set
    */
    public void setIsSSL(final Bit isSSL) {
        this.isSSL = isSSL;
    }

    /**
    * set clientModeConfiguration of type ClientModeConfiguration.
    * @param  clientModeConfiguration to be set
    */
    public void setClientModeConfiguration(
        final ClientModeConfiguration clientModeConfiguration) {
        this.clientModeConfiguration = clientModeConfiguration;
    }

    /**
    * set serverModeConfiguration of type ServerModeConfiguration.
    * @param  serverModeConfiguration to be set
    */
    public void setServerModeConfiguration(
        final ServerModeConfiguration serverModeConfiguration) {
        this.serverModeConfiguration = serverModeConfiguration;
    }

    // end setter

    //getters
    /**
    * get commMode of type TcpLinkCommMode.
    * @return  TcpLinkCommMode
    */
    public TcpLinkCommMode getCommMode() {
        return commMode;
    }

    /**
    * get   isSSL of type Bit.
    * @return   type Bit to be set
    */
    public Bit getIsSSL() {
        return this.isSSL;
    }

    /**
    * get clientModeConfiguration of type ClientModeConfiguration .
    * @return  ClientModeConfiguration
    */
    public ClientModeConfiguration getClientModeConfiguration() {
        return clientModeConfiguration;
    }

    /**
    * get serverModeConfiguration of type ServerModeConfiguration .
    * @return  ServerModeConfiguration
    */
    public ServerModeConfiguration getServerModeConfiguration() {
        return serverModeConfiguration;
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
        return "TcpLinkConfiguration";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "TcpLinkConfiguration: ";
        result += ", commMode: ";
        result += commMode;
        result += ", isSSL: ";
        result += isSSL;

        result = result.replaceFirst(", ", "");

        return result;
    }
}
