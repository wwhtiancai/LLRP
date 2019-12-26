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
import org.llrp.ltk.generated.enumerations.SelectSpecState;
import org.llrp.ltk.generated.interfaces.SpecParameter;
import org.llrp.ltk.generated.parameters.AntennaSpec;
import org.llrp.ltk.generated.parameters.SelectReportSpec;
import org.llrp.ltk.generated.parameters.SelectSpecStartTrigger;
import org.llrp.ltk.generated.parameters.SelectSpecStopTrigger;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.BitList;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.TLVParameter;
import org.llrp.ltk.types.TVParameter;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedInteger;
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
public class SelectSpec extends TLVParameter {
    public static final SignedShort TYPENUM = new SignedShort(400);
    private static final Logger LOGGER = Logger.getLogger(SelectSpec.class);
    protected UnsignedInteger selectSpecID;
    protected UnsignedByte priority;
    protected SelectSpecState currentState;
    protected Bit persistence;
    protected BitList reserved0 = new BitList(7);
    protected SelectSpecStartTrigger selectSpecStartTrigger;
    protected SelectSpecStopTrigger selectSpecStopTrigger;
    protected List<SpecParameter> specParameterList = new LinkedList<SpecParameter>();
    protected SelectReportSpec selectReportSpec;

    /**
     * empty constructor to create new parameter.
     */
    public SelectSpec() {
    }

    /**
     * Constructor to create parameter from binary encoded parameter
     * calls decodeBinary to decode parameter.
     * @param list to be decoded
     */
    public SelectSpec(LLRPBitList list) {
        decodeBinary(list);
    }

    /**
    * Constructor to create parameter from xml encoded parameter
    * calls decodeXML to decode parameter.
    * @param element to be decoded
    */
    public SelectSpec(Element element) throws InvalidLLRPMessageException {
        decodeXML(element);
    }

    /**
    * {@inheritDoc}
    */
    public LLRPBitList encodeBinarySpecific() {
        LLRPBitList resultBits = new LLRPBitList();

        if (selectSpecID == null) {
            LOGGER.warn(" selectSpecID not set");
            throw new MissingParameterException(
                " selectSpecID not set  for Parameter of Type SelectSpec");
        }

        resultBits.append(selectSpecID.encodeBinary());

        if (priority == null) {
            LOGGER.warn(" priority not set");
            throw new MissingParameterException(
                " priority not set  for Parameter of Type SelectSpec");
        }

        resultBits.append(priority.encodeBinary());

        if (currentState == null) {
            LOGGER.warn(" currentState not set");
            throw new MissingParameterException(
                " currentState not set  for Parameter of Type SelectSpec");
        }

        resultBits.append(currentState.encodeBinary());

        if (persistence == null) {
            LOGGER.warn(" persistence not set");
            throw new MissingParameterException(
                " persistence not set  for Parameter of Type SelectSpec");
        }

        resultBits.append(persistence.encodeBinary());
        resultBits.append(reserved0.encodeBinary());

        if (selectSpecStartTrigger == null) {
            // single parameter, may not be null
            LOGGER.warn(" selectSpecStartTrigger not set");
            throw new MissingParameterException(
                " selectSpecStartTrigger not set");
        } else {
            resultBits.append(selectSpecStartTrigger.encodeBinary());
        }

        if (selectSpecStopTrigger == null) {
            // single parameter, may not be null
            LOGGER.warn(" selectSpecStopTrigger not set");
            throw new MissingParameterException(
                " selectSpecStopTrigger not set");
        } else {
            resultBits.append(selectSpecStopTrigger.encodeBinary());
        }

        if (specParameterList == null) {
            LOGGER.warn(" specParameterList not set");

            //parameter has to be set - throw exception
            throw new MissingParameterException(" specParameterList not set");
        } else {
            for (SpecParameter field : specParameterList) {
                resultBits.append(field.encodeBinary());
            }
        }

        if (selectReportSpec == null) {
            // optional parameter, may be null
            LOGGER.info(" selectReportSpec not set");
        } else {
            resultBits.append(selectReportSpec.encodeBinary());
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

        if (selectSpecID == null) {
            LOGGER.warn(" selectSpecID not set");
            throw new MissingParameterException(" selectSpecID not set");
        } else {
            element.addContent(selectSpecID.encodeXML("SelectSpecID", ns));
        }

        if (priority == null) {
            LOGGER.warn(" priority not set");
            throw new MissingParameterException(" priority not set");
        } else {
            element.addContent(priority.encodeXML("Priority", ns));
        }

        if (currentState == null) {
            LOGGER.warn(" currentState not set");
            throw new MissingParameterException(" currentState not set");
        } else {
            element.addContent(currentState.encodeXML("CurrentState", ns));
        }

        if (persistence == null) {
            LOGGER.warn(" persistence not set");
            throw new MissingParameterException(" persistence not set");
        } else {
            element.addContent(persistence.encodeXML("Persistence", ns));
        }

        //element.addContent(reserved0.encodeXML("reserved",ns));
        //parameters
        if (selectSpecStartTrigger == null) {
            LOGGER.info("selectSpecStartTrigger not set");
            throw new MissingParameterException(
                "selectSpecStartTrigger not set");
        } else {
            element.addContent(selectSpecStartTrigger.encodeXML(
                    selectSpecStartTrigger.getClass().getSimpleName(), ns));
        }

        if (selectSpecStopTrigger == null) {
            LOGGER.info("selectSpecStopTrigger not set");
            throw new MissingParameterException("selectSpecStopTrigger not set");
        } else {
            element.addContent(selectSpecStopTrigger.encodeXML(
                    selectSpecStopTrigger.getClass().getSimpleName(), ns));
        }

        if (specParameterList == null) {
            LOGGER.warn(" specParameterList not set");
            throw new MissingParameterException("  specParameterList not set");
        }

        for (SpecParameter field : specParameterList) {
            element.addContent(field.encodeXML(field.getClass().getName()
                                                    .replaceAll(field.getClass()
                                                                     .getPackage()
                                                                     .getName() +
                        ".", ""), ns));
        }

        if (selectReportSpec == null) {
            LOGGER.info("selectReportSpec not set");
        } else {
            element.addContent(selectReportSpec.encodeXML(
                    selectReportSpec.getClass().getSimpleName(), ns));
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
        selectSpecID = new UnsignedInteger(binary.subList(position,
                    UnsignedInteger.length()));
        position += UnsignedInteger.length();
        priority = new UnsignedByte(binary.subList(position,
                    UnsignedByte.length()));
        position += UnsignedByte.length();
        currentState = new SelectSpecState(binary.subList(position,
                    SelectSpecState.length()));
        position += SelectSpecState.length();
        persistence = new Bit(binary.subList(position, Bit.length()));
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
            LOGGER.warn(
                "SelectSpec misses non optional parameter of type SelectSpecStartTrigger");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type SelectSpecStartTrigger");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = selectSpecStartTrigger.length();
        }

        if ((type != null) && type.equals(SelectSpecStartTrigger.TYPENUM)) {
            selectSpecStartTrigger = new SelectSpecStartTrigger(binary.subList(
                        position, tempLength));
            position += tempLength;
            LOGGER.debug(
                " selectSpecStartTrigger is instantiated with SelectSpecStartTrigger with length" +
                tempLength);
        } else {
            LOGGER.warn(
                "SelectSpec misses non optional parameter of type SelectSpecStartTrigger");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type SelectSpecStartTrigger");
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
                "SelectSpec misses non optional parameter of type SelectSpecStopTrigger");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type SelectSpecStopTrigger");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = selectSpecStopTrigger.length();
        }

        if ((type != null) && type.equals(SelectSpecStopTrigger.TYPENUM)) {
            selectSpecStopTrigger = new SelectSpecStopTrigger(binary.subList(
                        position, tempLength));
            position += tempLength;
            LOGGER.debug(
                " selectSpecStopTrigger is instantiated with SelectSpecStopTrigger with length" +
                tempLength);
        } else {
            LOGGER.warn(
                "SelectSpec misses non optional parameter of type SelectSpecStopTrigger");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type SelectSpecStopTrigger");
        }

        // list of parameters
        specParameterList = new LinkedList<SpecParameter>();
        LOGGER.debug("decoding parameter specParameterList ");

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
            if ((type != null) && type.equals(AntennaSpec.TYPENUM)) {
                if (binary.get(position)) {
                    // length can statically be determined for TV Parameters
                    tempLength = AntennaSpec.length();
                }

                specParameterList.add(new AntennaSpec(binary.subList(position,
                            tempLength)));
                LOGGER.debug("adding AntennaSpec to specParameterList ");
                position += tempLength;
                atLeastOnce = true;
            }

            if (!atLeastOnce) {
                //no parameter matched therefore we jump out of the loop
                break;
            }
        }

        //if list is still empty no parameter matched
        if (specParameterList.isEmpty()) {
            LOGGER.warn(
                "encoded message does not contain parameter for non optional specParameterList");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type SpecParameter");
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
                "SelectSpec misses optional parameter of type SelectReportSpec");
        }

        if (binary.get(position)) {
            // length can statically be determined for TV Parameters
            tempLength = selectReportSpec.length();
        }

        if ((type != null) && type.equals(SelectReportSpec.TYPENUM)) {
            selectReportSpec = new SelectReportSpec(binary.subList(position,
                        tempLength));
            position += tempLength;
            LOGGER.debug(
                " selectReportSpec is instantiated with SelectReportSpec with length" +
                tempLength);
        } else {
            LOGGER.info(
                "SelectSpec misses optional parameter of type SelectReportSpec");
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

        temp = element.getChild("SelectSpecID", ns);

        if (temp != null) {
            selectSpecID = new UnsignedInteger(temp);
        }

        element.removeChild("SelectSpecID", ns);
        temp = element.getChild("Priority", ns);

        if (temp != null) {
            priority = new UnsignedByte(temp);
        }

        element.removeChild("Priority", ns);
        temp = element.getChild("CurrentState", ns);

        if (temp != null) {
            currentState = new SelectSpecState(temp);
        }

        element.removeChild("CurrentState", ns);
        temp = element.getChild("Persistence", ns);

        if (temp != null) {
            persistence = new Bit(temp);
        }

        element.removeChild("Persistence", ns);

        //parameter - not choices - no special actions needed
        temp = element.getChild("SelectSpecStartTrigger", ns);

        if (temp != null) {
            selectSpecStartTrigger = new SelectSpecStartTrigger(temp);
            LOGGER.info(
                "setting parameter selectSpecStartTrigger for parameter SelectSpec");
        }

        if (temp == null) {
            LOGGER.warn(
                "SelectSpec misses non optional parameter of type selectSpecStartTrigger");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type selectSpecStartTrigger");
        }

        element.removeChild("SelectSpecStartTrigger", ns);
        //parameter - not choices - no special actions needed
        temp = element.getChild("SelectSpecStopTrigger", ns);

        if (temp != null) {
            selectSpecStopTrigger = new SelectSpecStopTrigger(temp);
            LOGGER.info(
                "setting parameter selectSpecStopTrigger for parameter SelectSpec");
        }

        if (temp == null) {
            LOGGER.warn(
                "SelectSpec misses non optional parameter of type selectSpecStopTrigger");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type selectSpecStopTrigger");
        }

        element.removeChild("SelectSpecStopTrigger", ns);
        //choices - must check all possible subtypes
        //list of Choice Type Parameter
        specParameterList = new LinkedList<SpecParameter>();
        // for each possible subtype get all childs
        tempList = element.getChildren("AntennaSpec", ns);

        for (Element e : tempList) {
            specParameterList.add(new AntennaSpec(e));
            LOGGER.debug("adding AntennaSpec to specParameterList ");
            atLeastOnce = true;
        }

        element.removeChildren("AntennaSpec", ns);

        if (!atLeastOnce) {
            LOGGER.warn(
                "SelectSpec misses non optional parameter of type specParameterList");
            throw new MissingParameterException(
                "SelectSpec misses non optional parameter of type specParameterList");
        }

        atLeastOnce = false;
        //parameter - not choices - no special actions needed
        temp = element.getChild("SelectReportSpec", ns);

        if (temp != null) {
            selectReportSpec = new SelectReportSpec(temp);
            LOGGER.info(
                "setting parameter selectReportSpec for parameter SelectSpec");
        }

        if (temp == null) {
            LOGGER.info(
                "SelectSpec misses optional parameter of type selectReportSpec");
        }

        element.removeChild("SelectReportSpec", ns);

        if (element.getChildren().size() > 0) {
            String message = "SelectSpec has unknown element " +
                ((Element) element.getChildren().get(0)).getName();
            throw new InvalidLLRPMessageException(message);
        }
    }

    //setters
    /**
    * set   selectSpecID of type UnsignedInteger .
    * @param   selectSpecID to be set
    */
    public void setSelectSpecID(final UnsignedInteger selectSpecID) {
        this.selectSpecID = selectSpecID;
    }

    /**
    * set   priority of type UnsignedByte .
    * @param   priority to be set
    */
    public void setPriority(final UnsignedByte priority) {
        this.priority = priority;
    }

    /**
    * set currentState of type SelectSpecState .
    * @param  currentState to be set
    */
    public void setCurrentState(final SelectSpecState currentState) {
        this.currentState = currentState;
    }

    /**
    * set   persistence of type Bit .
    * @param   persistence to be set
    */
    public void setPersistence(final Bit persistence) {
        this.persistence = persistence;
    }

    /**
    * set selectSpecStartTrigger of type SelectSpecStartTrigger.
    * @param  selectSpecStartTrigger to be set
    */
    public void setSelectSpecStartTrigger(
        final SelectSpecStartTrigger selectSpecStartTrigger) {
        this.selectSpecStartTrigger = selectSpecStartTrigger;
    }

    /**
    * set selectSpecStopTrigger of type SelectSpecStopTrigger.
    * @param  selectSpecStopTrigger to be set
    */
    public void setSelectSpecStopTrigger(
        final SelectSpecStopTrigger selectSpecStopTrigger) {
        this.selectSpecStopTrigger = selectSpecStopTrigger;
    }

    /**
    * set specParameterList of type  List &lt;SpecParameter>.
    * @param  specParameterList to be set
    */
    public void setSpecParameterList(
        final List<SpecParameter> specParameterList) {
        this.specParameterList = specParameterList;
    }

    /**
    * set selectReportSpec of type SelectReportSpec.
    * @param  selectReportSpec to be set
    */
    public void setSelectReportSpec(final SelectReportSpec selectReportSpec) {
        this.selectReportSpec = selectReportSpec;
    }

    // end setter

    //getters
    /**
    * get   selectSpecID of type UnsignedInteger.
    * @return   type UnsignedInteger to be set
    */
    public UnsignedInteger getSelectSpecID() {
        return this.selectSpecID;
    }

    /**
    * get   priority of type UnsignedByte.
    * @return   type UnsignedByte to be set
    */
    public UnsignedByte getPriority() {
        return this.priority;
    }

    /**
    * get currentState of type SelectSpecState.
    * @return  SelectSpecState
    */
    public SelectSpecState getCurrentState() {
        return currentState;
    }

    /**
    * get   persistence of type Bit.
    * @return   type Bit to be set
    */
    public Bit getPersistence() {
        return this.persistence;
    }

    /**
    * get selectSpecStartTrigger of type SelectSpecStartTrigger .
    * @return  SelectSpecStartTrigger
    */
    public SelectSpecStartTrigger getSelectSpecStartTrigger() {
        return selectSpecStartTrigger;
    }

    /**
    * get selectSpecStopTrigger of type SelectSpecStopTrigger .
    * @return  SelectSpecStopTrigger
    */
    public SelectSpecStopTrigger getSelectSpecStopTrigger() {
        return selectSpecStopTrigger;
    }

    /**
    * get specParameterList of type List &lt;SpecParameter> .
    * @return  List &lt;SpecParameter>
    */
    public List<SpecParameter> getSpecParameterList() {
        return specParameterList;
    }

    /**
    * get selectReportSpec of type SelectReportSpec .
    * @return  SelectReportSpec
    */
    public SelectReportSpec getSelectReportSpec() {
        return selectReportSpec;
    }

    // end getters

    //add methods

    /**
    * add element specParameter of type SpecParameter .
    * @param  specParameter of type SpecParameter
    */
    public void addToSpecParameterList(SpecParameter specParameter) {
        if (this.specParameterList == null) {
            this.specParameterList = new LinkedList<SpecParameter>();
        }

        this.specParameterList.add(specParameter);
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
        return "SelectSpec";
    }

    /**
    * return string representation. All field values but no parameters are included
    * @return String
    */
    public String toString() {
        String result = "SelectSpec: ";
        result += ", selectSpecID: ";
        result += selectSpecID;
        result += ", priority: ";
        result += priority;
        result += ", currentState: ";
        result += currentState;
        result += ", persistence: ";
        result += persistence;

        result = result.replaceFirst(", ", "");

        return result;
    }
}