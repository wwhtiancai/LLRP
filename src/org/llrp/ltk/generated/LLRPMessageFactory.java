/*
 *
 * This file was generated by LLRP Code Generator
 * see http://llrp-toolkit.cvs.sourceforge.net/llrp-toolkit/ for more information
 * Generated on: $utility.getDateNTime();
 *
 */

/*
 *  Copyright 2007 Pramari, LLC.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
*/
package org.llrp.ltk.generated;

import org.apache.log4j.Logger;

import org.jdom.Document;
import org.jdom.Element;

import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.generated.messages.*;
import org.llrp.ltk.types.*;

import java.util.HashMap;


/**
 *
 * LLRPMessageFactory generates LLRPMessage objects from LLRP Messages
 * in binary or LTK XML encoding.
 *
 * LLRPMessageFactory detects the message types in a binary or XML message and
 * instantiates the corresponding LTK Java message object, e.g. ADD_ROSPEC.
 * The static factory methods return the abstract LLRPMessage.
 *
 * Example: Input a LLRP message in LTK XML format and output the binary
 * representation:
 *
 * // build JDOM document from file
 * org.jdom.Document doc = new org.jdom.input.SAXBuilder().build(new
 *                                                FileReader(filename));
 * // create LTK-Java object
 * LLRPMessage message = LLRPMessageFactory.createLLRPMessage(doc);
 *
 * // output as a binary message
 * byte[] output = message.encodeBinary();
 *
 *
 *
 * @author Andreas Huebner - andreas@pramari.com
 * @author Christian Floerkemeier - floerkem@mit.edu
 *
 */
public class LLRPMessageFactory {
    /* logger for LLRPMessageFactory */
    private static final Logger logger = Logger.getLogger("LLRPMessageFactory");
    public static final int reservedLength = 64; //change 3 to 64 by wuwh
    public static final int versionLength = 8; //change 3 to 8 by wuwh

    /**
     * Generates a LLRPMessage out of the given LLRPBitList.
     *
     * @param bits
     *         LLRPBitList of a LLRPMessage
     * @return appropriate LLRPMessage out of the given LLRPBitList
     *                    or null when Message couldn't be generated
     */
    public static LLRPMessage createLLRPMessage(LLRPBitList bits)
        throws InvalidLLRPMessageException {
        // determine messageType
        // 		Short messageType = new SignedShort(bits.subList(reservedLength
        // 				+ versionLength, SignedShort.length()
        // 				- (reservedLength + versionLength))).toShort();
        // change by wuwh
        Short messageType = new SignedShort(bits.subList(reservedLength +
                    versionLength, SignedShort.length())).toShort();
        LLRPMessage message = null;
        
        // construct appropriate message object
        if (messageType == ErrorAck.TYPENUM.intValue()) {
            message = new ErrorAck(bits);
        }

        if (messageType == Keepalive.TYPENUM.intValue()) {
            message = new Keepalive(bits);
        }

        if (messageType == KeepaliveAck.TYPENUM.intValue()) {
            message = new KeepaliveAck(bits);
        }

        if (messageType == DeviceEventNotification.TYPENUM.intValue()) {
            message = new DeviceEventNotification(bits);
        }

        if (messageType == GetDeviceCapabilities.TYPENUM.intValue()) {
            message = new GetDeviceCapabilities(bits);
        }

        if (messageType == GetDeviceCapabilitiesAck.TYPENUM.intValue()) {
            message = new GetDeviceCapabilitiesAck(bits);
        }

        if (messageType == AddSelectSpec.TYPENUM.intValue()) {
            message = new AddSelectSpec(bits);
        }

        if (messageType == AddSelectSpecAck.TYPENUM.intValue()) {
            message = new AddSelectSpecAck(bits);
        }

        if (messageType == DeleteSelectSpec.TYPENUM.intValue()) {
            message = new DeleteSelectSpec(bits);
        }

        if (messageType == DeleteSelectSpecAck.TYPENUM.intValue()) {
            message = new DeleteSelectSpecAck(bits);
        }

        if (messageType == StartSelectSpec.TYPENUM.intValue()) {
            message = new StartSelectSpec(bits);
        }

        if (messageType == StartSelectSpecAck.TYPENUM.intValue()) {
            message = new StartSelectSpecAck(bits);
        }

        if (messageType == StopSelectSpec.TYPENUM.intValue()) {
            message = new StopSelectSpec(bits);
        }

        if (messageType == StopSelectSpecAck.TYPENUM.intValue()) {
            message = new StopSelectSpecAck(bits);
        }

        if (messageType == EnableSelectSpec.TYPENUM.intValue()) {
            message = new EnableSelectSpec(bits);
        }

        if (messageType == EnableSelectSpecAck.TYPENUM.intValue()) {
            message = new EnableSelectSpecAck(bits);
        }

        if (messageType == DisableSelectSpec.TYPENUM.intValue()) {
            message = new DisableSelectSpec(bits);
        }

        if (messageType == DisableSelectSpecAck.TYPENUM.intValue()) {
            message = new DisableSelectSpecAck(bits);
        }

        if (messageType == GetSelectSpec.TYPENUM.intValue()) {
            message = new GetSelectSpec(bits);
        }

        if (messageType == GetSelectSpecAck.TYPENUM.intValue()) {
            message = new GetSelectSpecAck(bits);
        }

        if (messageType == AddAccessSpec.TYPENUM.intValue()) {
            message = new AddAccessSpec(bits);
        }

        if (messageType == AddAccessSpecAck.TYPENUM.intValue()) {
            message = new AddAccessSpecAck(bits);
        }

        if (messageType == DeleteAccessSpec.TYPENUM.intValue()) {
            message = new DeleteAccessSpec(bits);
        }

        if (messageType == DeleteAccessSpecAck.TYPENUM.intValue()) {
            message = new DeleteAccessSpecAck(bits);
        }

        if (messageType == EnableAccessSpec.TYPENUM.intValue()) {
            message = new EnableAccessSpec(bits);
        }

        if (messageType == EnableAccessSpecAck.TYPENUM.intValue()) {
            message = new EnableAccessSpecAck(bits);
        }

        if (messageType == DisableAccessSpec.TYPENUM.intValue()) {
            message = new DisableAccessSpec(bits);
        }

        if (messageType == DisableAccessSpecAck.TYPENUM.intValue()) {
            message = new DisableAccessSpecAck(bits);
        }

        if (messageType == GetAccessSpec.TYPENUM.intValue()) {
            message = new GetAccessSpec(bits);
        }

        if (messageType == GetAccessSpecAck.TYPENUM.intValue()) {
            message = new GetAccessSpecAck(bits);
        }

        if (messageType == TagSelectAccessReport.TYPENUM.intValue()) {
            message = new TagSelectAccessReport(bits);
        }

        if (messageType == ClientRequestOp.TYPENUM.intValue()) {
            message = new ClientRequestOp(bits);
        }

        if (messageType == ClientRequestOpAck.TYPENUM.intValue()) {
            message = new ClientRequestOpAck(bits);
        }

        if (messageType == DeviceBinding.TYPENUM.intValue()) {
            message = new DeviceBinding(bits);
        }

        if (messageType == DeviceBindingAck.TYPENUM.intValue()) {
            message = new DeviceBindingAck(bits);
        }

        if (messageType == UploadTagLog.TYPENUM.intValue()) {
            message = new UploadTagLog(bits);
        }

        if (messageType == UploadTagLogAck.TYPENUM.intValue()) {
            message = new UploadTagLogAck(bits);
        }

        if (messageType == ClearTagLog.TYPENUM.intValue()) {
            message = new ClearTagLog(bits);
        }

        if (messageType == ClearTagLogAck.TYPENUM.intValue()) {
            message = new ClearTagLogAck(bits);
        }

        if (messageType == UploadDeviceLog.TYPENUM.intValue()) {
            message = new UploadDeviceLog(bits);
        }

        if (messageType == UploadDeviceLogAck.TYPENUM.intValue()) {
            message = new UploadDeviceLogAck(bits);
        }

        if (messageType == ClearDeviceLog.TYPENUM.intValue()) {
            message = new ClearDeviceLog(bits);
        }

        if (messageType == ClearDeviceLogAck.TYPENUM.intValue()) {
            message = new ClearDeviceLogAck(bits);
        }

        if (messageType == GetDeviceConfig.TYPENUM.intValue()) {
            message = new GetDeviceConfig(bits);
        }

        if (messageType == GetDeviceConfigAck.TYPENUM.intValue()) {
            message = new GetDeviceConfigAck(bits);
        }

        if (messageType == SetDeviceConfig.TYPENUM.intValue()) {
            message = new SetDeviceConfig(bits);
        }

        if (messageType == SetDeviceConfigAck.TYPENUM.intValue()) {
            message = new SetDeviceConfigAck(bits);
        }

        if (messageType == GetVersion.TYPENUM.intValue()) {
            message = new GetVersion(bits);
        }

        if (messageType == GetVersionAck.TYPENUM.intValue()) {
            message = new GetVersionAck(bits);
        }

        if (messageType == SetVersion.TYPENUM.intValue()) {
            message = new SetVersion(bits);
        }

        if (messageType == SetVersionAck.TYPENUM.intValue()) {
            message = new SetVersionAck(bits);
        }

        if (messageType == ActiveVersion.TYPENUM.intValue()) {
            message = new ActiveVersion(bits);
        }

        if (messageType == ActiveVersionAck.TYPENUM.intValue()) {
            message = new ActiveVersionAck(bits);
        }

        if (messageType == UnActiveVersion.TYPENUM.intValue()) {
            message = new UnActiveVersion(bits);
        }

        if (messageType == UnActiveVersionAck.TYPENUM.intValue()) {
            message = new UnActiveVersionAck(bits);
        }

        if (messageType == AlarmReport.TYPENUM.intValue()) {
            message = new AlarmReport(bits);
        }

        if (messageType == AlarmRestore.TYPENUM.intValue()) {
            message = new AlarmRestore(bits);
        }

        if (messageType == AlarmDelete.TYPENUM.intValue()) {
            message = new AlarmDelete(bits);
        }

        if (messageType == AlarmSync.TYPENUM.intValue()) {
            message = new AlarmSync(bits);
        }

        if (messageType == DiagnosticTest.TYPENUM.intValue()) {
            message = new DiagnosticTest(bits);
        }

        if (messageType == DiagnosticTestAck.TYPENUM.intValue()) {
            message = new DiagnosticTestAck(bits);
        }

        if (messageType == ResetDevice.TYPENUM.intValue()) {
            message = new ResetDevice(bits);
        }

        if (messageType == ResetDeviceAck.TYPENUM.intValue()) {
            message = new ResetDeviceAck(bits);
        }

        // check whether the message type exists
        if (message == null) {
            throw new InvalidLLRPMessageException("The message type (\"" +
                messageType +
                "\") specified in the binary LLRP message is not known.");
        } else {
            return message;
        }
    }

    /**
     * Generates a LLRPMessage out of the given Byte Array.
     *
     * @param byteArray
     *         byte[] of a LLRPMessage
     * @return appropriate LLRPMessage out of the given byte[]
     *                    or null when Message couldn't be generated
     */
    public static LLRPMessage createLLRPMessage(byte[] byteArray)
        throws InvalidLLRPMessageException {
        LLRPBitList bits = new LLRPBitList(byteArray);

        return createLLRPMessage(bits);
    }

    public static LLRPMessage createLLRPMessage(Document document)
        throws InvalidLLRPMessageException {
        // determine messageType
        Element root = document.getRootElement();
        String className = root.getName();
        logger.debug("Root element of input document is: " + className);

        // construct appropriate message object
        LLRPMessage message = null;

        if (className.equals("ErrorAck")) {
            message = new ErrorAck(document);
        }

        if (className.equals("Keepalive")) {
            message = new Keepalive(document);
        }

        if (className.equals("KeepaliveAck")) {
            message = new KeepaliveAck(document);
        }

        if (className.equals("DeviceEventNotification")) {
            message = new DeviceEventNotification(document);
        }

        if (className.equals("GetDeviceCapabilities")) {
            message = new GetDeviceCapabilities(document);
        }

        if (className.equals("GetDeviceCapabilitiesAck")) {
            message = new GetDeviceCapabilitiesAck(document);
        }

        if (className.equals("AddSelectSpec")) {
            message = new AddSelectSpec(document);
        }

        if (className.equals("AddSelectSpecAck")) {
            message = new AddSelectSpecAck(document);
        }

        if (className.equals("DeleteSelectSpec")) {
            message = new DeleteSelectSpec(document);
        }

        if (className.equals("DeleteSelectSpecAck")) {
            message = new DeleteSelectSpecAck(document);
        }

        if (className.equals("StartSelectSpec")) {
            message = new StartSelectSpec(document);
        }

        if (className.equals("StartSelectSpecAck")) {
            message = new StartSelectSpecAck(document);
        }

        if (className.equals("StopSelectSpec")) {
            message = new StopSelectSpec(document);
        }

        if (className.equals("StopSelectSpecAck")) {
            message = new StopSelectSpecAck(document);
        }

        if (className.equals("EnableSelectSpec")) {
            message = new EnableSelectSpec(document);
        }

        if (className.equals("EnableSelectSpecAck")) {
            message = new EnableSelectSpecAck(document);
        }

        if (className.equals("DisableSelectSpec")) {
            message = new DisableSelectSpec(document);
        }

        if (className.equals("DisableSelectSpecAck")) {
            message = new DisableSelectSpecAck(document);
        }

        if (className.equals("GetSelectSpec")) {
            message = new GetSelectSpec(document);
        }

        if (className.equals("GetSelectSpecAck")) {
            message = new GetSelectSpecAck(document);
        }

        if (className.equals("AddAccessSpec")) {
            message = new AddAccessSpec(document);
        }

        if (className.equals("AddAccessSpecAck")) {
            message = new AddAccessSpecAck(document);
        }

        if (className.equals("DeleteAccessSpec")) {
            message = new DeleteAccessSpec(document);
        }

        if (className.equals("DeleteAccessSpecAck")) {
            message = new DeleteAccessSpecAck(document);
        }

        if (className.equals("EnableAccessSpec")) {
            message = new EnableAccessSpec(document);
        }

        if (className.equals("EnableAccessSpecAck")) {
            message = new EnableAccessSpecAck(document);
        }

        if (className.equals("DisableAccessSpec")) {
            message = new DisableAccessSpec(document);
        }

        if (className.equals("DisableAccessSpecAck")) {
            message = new DisableAccessSpecAck(document);
        }

        if (className.equals("GetAccessSpec")) {
            message = new GetAccessSpec(document);
        }

        if (className.equals("GetAccessSpecAck")) {
            message = new GetAccessSpecAck(document);
        }

        if (className.equals("TagSelectAccessReport")) {
            message = new TagSelectAccessReport(document);
        }

        if (className.equals("ClientRequestOp")) {
            message = new ClientRequestOp(document);
        }

        if (className.equals("ClientRequestOpAck")) {
            message = new ClientRequestOpAck(document);
        }

        if (className.equals("DeviceBinding")) {
            message = new DeviceBinding(document);
        }

        if (className.equals("DeviceBindingAck")) {
            message = new DeviceBindingAck(document);
        }

        if (className.equals("UploadTagLog")) {
            message = new UploadTagLog(document);
        }

        if (className.equals("UploadTagLogAck")) {
            message = new UploadTagLogAck(document);
        }

        if (className.equals("ClearTagLog")) {
            message = new ClearTagLog(document);
        }

        if (className.equals("ClearTagLogAck")) {
            message = new ClearTagLogAck(document);
        }

        if (className.equals("UploadDeviceLog")) {
            message = new UploadDeviceLog(document);
        }

        if (className.equals("UploadDeviceLogAck")) {
            message = new UploadDeviceLogAck(document);
        }

        if (className.equals("ClearDeviceLog")) {
            message = new ClearDeviceLog(document);
        }

        if (className.equals("ClearDeviceLogAck")) {
            message = new ClearDeviceLogAck(document);
        }

        if (className.equals("GetDeviceConfig")) {
            message = new GetDeviceConfig(document);
        }

        if (className.equals("GetDeviceConfigAck")) {
            message = new GetDeviceConfigAck(document);
        }

        if (className.equals("SetDeviceConfig")) {
            message = new SetDeviceConfig(document);
        }

        if (className.equals("SetDeviceConfigAck")) {
            message = new SetDeviceConfigAck(document);
        }

        if (className.equals("GetVersion")) {
            message = new GetVersion(document);
        }

        if (className.equals("GetVersionAck")) {
            message = new GetVersionAck(document);
        }

        if (className.equals("SetVersion")) {
            message = new SetVersion(document);
        }

        if (className.equals("SetVersionAck")) {
            message = new SetVersionAck(document);
        }

        if (className.equals("ActiveVersion")) {
            message = new ActiveVersion(document);
        }

        if (className.equals("ActiveVersionAck")) {
            message = new ActiveVersionAck(document);
        }

        if (className.equals("UnActiveVersion")) {
            message = new UnActiveVersion(document);
        }

        if (className.equals("UnActiveVersionAck")) {
            message = new UnActiveVersionAck(document);
        }

        if (className.equals("AlarmReport")) {
            message = new AlarmReport(document);
        }

        if (className.equals("AlarmRestore")) {
            message = new AlarmRestore(document);
        }

        if (className.equals("AlarmDelete")) {
            message = new AlarmDelete(document);
        }

        if (className.equals("AlarmSync")) {
            message = new AlarmSync(document);
        }

        if (className.equals("DiagnosticTest")) {
            message = new DiagnosticTest(document);
        }

        if (className.equals("DiagnosticTestAck")) {
            message = new DiagnosticTestAck(document);
        }

        if (className.equals("ResetDevice")) {
            message = new ResetDevice(document);
        }

        if (className.equals("ResetDeviceAck")) {
            message = new ResetDeviceAck(document);
        }

        // check whether the message type exists
        if (message == null) {
            throw new InvalidLLRPMessageException("The message type (\"" +
                className +
                "\") specified in the LTK-XML message is not known.");
        } else {
            return message;
        }
    }
}