package org.llrp.ltk.generated.interfaces;

import org.jdom.Content;
import org.jdom.Element;
import org.jdom.Namespace;

import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.types.LLRPBitList;


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

/**
 * interface SelectSpecResult
 * representing choice construct in llrpdef.xml.
 */
public interface SelectSpecResult {
    /**
     * encode parameter to binary.
     * @return LLRPBitList
     */
    public LLRPBitList encodeBinary();

    /**
     * encode parameter to xml.
     * @param name of element
    * @param ns Namespace of elements
     * @return JDom Content object
     */
    public Content encodeXML(String name, Namespace ns);

    /**
     * decode parameter from binary.
     * @param list to be decoded
     */
    public void decodeBinary(LLRPBitList list);

    /**
     * decode parameter from xml.
     * @param element to be decoded
     */
    public void decodeXML(Element element) throws InvalidLLRPMessageException;
}
