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
 */

package org.llrp.ltk.net;

import org.apache.log4j.Logger;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.types.LLRPMessage;

import java.math.BigInteger;

/**
	 * LLRPBinaryDecoder decodes incoming binary LLRP messages to LLRPMessage objects.
 */

public class LLRPBinaryDecoder extends CumulativeProtocolDecoder {

	private static final String MESSAGE_VERSION_KEY = "MessageVersion";
	private static final String MESSAGE_LENGTH_ARRAY = "LengthArray";
	private static final String MESSAGE_LENGTH_KEY = "MessageLength";
	private static final String MESSAGE_TYPE_KEY = "MessageType";//create by wuwh
	private static final String MESSAGE_DEVIESN_KEY = "DeviceSn";//create by wuwh
	private Logger log = Logger.getLogger(LLRPBinaryDecoder.class);

	
	@Override
	protected boolean doDecode(IoSession session, ByteBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// if 6 bytes in the buffer we can determine the next length to see
		// if buffer contains a completely delivered message.
		// in.getInt(2) will throw a BufferUnderflowException if there are not
		// even enough bytes to determine length
		int length = -1;
		byte[] lengthArray = null;
		byte[] devicesn = null;
		byte[] version = null;
		byte[] messageType = null;
//		if (in.remaining() >= 6 
		if (in.remaining() >= 15		
				&& session.getAttribute(MESSAGE_LENGTH_KEY) == null) {
			// enough bytes to decode length
			log.debug("determine length of message");
//			version = new byte[2];
//			version[0] = in.get();
//			version[1] = in.get();
			devicesn = new byte[8];
			devicesn[0] = in.get();
			devicesn[1] = in.get();
			devicesn[2] = in.get();
			devicesn[3] = in.get();
			devicesn[4] = in.get();
			devicesn[5] = in.get();
			devicesn[6] = in.get();
			devicesn[7] = in.get();
			version = new byte[1];
			version[0] = in.get();
			messageType = new byte[2];
			messageType[0] = in.get();
			messageType[1] = in.get();
			lengthArray = new byte[4];
			lengthArray[0] = in.get();
			lengthArray[1] = in.get();
			lengthArray[2] = in.get();
			lengthArray[3] = in.get();
			length = new BigInteger(lengthArray).intValue();
			session.setAttribute(MESSAGE_LENGTH_ARRAY, lengthArray);
			session.setAttribute(MESSAGE_LENGTH_KEY, new Integer(length));
			session.setAttribute(MESSAGE_VERSION_KEY, version);
			session.setAttribute(MESSAGE_TYPE_KEY, messageType);//create by wuwh
			session.setAttribute(MESSAGE_DEVIESN_KEY, devicesn);//create by wuwh
			// if the entire message is already available, call doDecode again.
			return (in.remaining()>=length+19-15);
		} else if (session.getAttribute(MESSAGE_LENGTH_KEY) != null) {
			log.debug("length already determined, see if enough bytes are available");
			length = ((Integer)session.getAttribute(MESSAGE_LENGTH_KEY)).intValue();
			version = (byte[]) session.getAttribute(MESSAGE_VERSION_KEY);
			lengthArray = (byte[]) session.getAttribute(MESSAGE_LENGTH_ARRAY);
			messageType = (byte[]) session.getAttribute(MESSAGE_TYPE_KEY);
			devicesn = (byte[]) session.getAttribute(MESSAGE_DEVIESN_KEY);
			if (in.remaining() >= length+19-15) {
				// all bytes received to decode message
				byte[] msg = new byte[length+19];
				msg[0] = devicesn[0];
				msg[1] = devicesn[1];
				msg[2] = devicesn[2];
				msg[3] = devicesn[3];
				msg[4] = devicesn[4];
				msg[5] = devicesn[5];
				msg[6] = devicesn[6];
				msg[7] = devicesn[7];
				msg[8] = version[0];
				msg[9] = messageType[0];
				msg[10] = messageType[1];
				msg[11] = lengthArray[0];
				msg[12] = lengthArray[1];
				msg[13] = lengthArray[2];
				msg[14] = lengthArray[3];
				for (int i = 15; i <  15+ 4 +length; i++) {
					msg[i] = (byte) in.get();
				}
				log.debug("message completely received");
				log.debug("start decoding message");
//				String hexStr =  "0123456789ABCDEF";
//				 String result = "";
//			        String hex = "";
//			        for(int i=0;i<msg.length;i++){
//			            //字节高4位
//			            hex = String.valueOf(hexStr.charAt((msg[i]&0xF0)>>4));
//			            //字节低4位
//			            hex += String.valueOf(hexStr.charAt(msg[i]&0x0F));
//			            result +=hex+" ";
//			        }
//				System.out.println(result);
				
				LLRPMessage message = LLRPMessageFactory.createLLRPMessage(msg);
				log.debug("message decoded: " + message.getClass());
				out.write(message);
				session.removeAttribute(MESSAGE_LENGTH_ARRAY);
				session.removeAttribute(MESSAGE_LENGTH_KEY);
				session.removeAttribute(MESSAGE_VERSION_KEY);
				session.removeAttribute(MESSAGE_TYPE_KEY);
				session.removeAttribute(MESSAGE_DEVIESN_KEY);
				// there might be an other message to be decoded

				// see if there's another completly delivered message in the
				// buffer
				// in this case, we would have to return true
				try {
					if (in.remaining() >= 15) {
//						version = new byte[11];
//						version[0] = in.get();
//						version[1] = in.get();
//						version[2] = in.get();
//						version[3] = in.get();
//						version[4] = in.get();
//						version[5] = in.get();
//						version[6] = in.get();
//						version[7] = in.get();
//						version[8] = in.get();
//						version[9] = in.get();
//						version[10] = in.get();
//						lengthArray = new byte[4];
//						lengthArray[0] = in.get();
//						lengthArray[1] = in.get();
//						lengthArray[2] = in.get();
//						lengthArray[3] = in.get();
						devicesn = new byte[8];
						devicesn[0] = in.get();
						devicesn[1] = in.get();
						devicesn[2] = in.get();
						devicesn[3] = in.get();
						devicesn[4] = in.get();
						devicesn[5] = in.get();
						devicesn[6] = in.get();
						devicesn[7] = in.get();
						version = new byte[1];
						version[0] = in.get();
						messageType = new byte[2];
						messageType[0] = in.get();
						messageType[1] = in.get();
						lengthArray = new byte[4];
						lengthArray[0] = in.get();
						lengthArray[1] = in.get();
						lengthArray[2] = in.get();
						lengthArray[3] = in.get();
						length = new BigInteger(lengthArray).intValue();
						session.setAttribute(MESSAGE_LENGTH_ARRAY, lengthArray);
						session.setAttribute(MESSAGE_LENGTH_KEY, new Integer(
								length));
						session.setAttribute(MESSAGE_VERSION_KEY, version);
						session.setAttribute(MESSAGE_TYPE_KEY, messageType);//create by wuwh
						session.setAttribute(MESSAGE_DEVIESN_KEY, devicesn);//create by wuwh
						if (in.remaining() - in.markValue() >= length-15) {
							log.debug("another message already in the buffer");
							return true;
						} else {
							log.debug("message not yet completly delivered");
							return false;
						}
					}
				} catch (Exception e) {
					// not enough bytes to determine length
					log.debug("not enough bytes to determine message length");
					return false;
				}
			} else {
				// not enough bytes to determin length

				log.debug("not enough bytes to determine message length");
				return false;
			}
		} else {
			log.debug("not enough bytes to determine length");
			return false;
		}
		return false;
	}

}
