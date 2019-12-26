package org.llrp.ltk.test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class xmlDecode {
 public static void main(String[] args) throws Exception {
	 SAXReader sax=new SAXReader();//创建一个SAXReader对象  
	    File xmlFile=new File(System.getProperty("user.dir") +"/ADD_ACCESSSPEC.xml");//根据指定的路径创建file对象  
	    Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束  
	    Element root=document.getRootElement();//获取根节点  
	    xmlDecode.getNodes(root);//从根节点开始遍历所有节点  

   //已知元素名情况下
//   System.out.println("title: " + element.elementText("title"));
//   System.out.println("author: " + element.elementText("author"));
//   System.out.println();
  }
public static void  getNodes(Element node){  
	    System.out.println("--------------------");  
	      
	    //当前节点的名称、文本内容和属性  
	    System.out.println("当前节点名称："+node.getName());//当前节点名称  
	    System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称  
	    List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list  
	    for(Attribute attr:listAttr){//遍历当前节点的所有属性  
	        String name=attr.getName();//属性名称  
	        String value=attr.getValue();//属性的值  
	        System.out.println("属性名称："+name+"属性值："+value);  
	    }  
	      
	    //递归遍历当前节点所有的子节点  
	    List<Element> listElement=node.elements();//所有一级子节点的list  
	    for(Element e:listElement){//遍历所有一级子节点  
	    	xmlDecode.getNodes(e);//递归  
	    }  
	}  
}