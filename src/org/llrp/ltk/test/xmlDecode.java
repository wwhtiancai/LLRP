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
	 SAXReader sax=new SAXReader();//����һ��SAXReader����  
	    File xmlFile=new File(System.getProperty("user.dir") +"/ADD_ACCESSSPEC.xml");//����ָ����·������file����  
	    Document document=sax.read(xmlFile);//��ȡdocument����,����ĵ��޽ڵ㣬����׳�Exception��ǰ����  
	    Element root=document.getRootElement();//��ȡ���ڵ�  
	    xmlDecode.getNodes(root);//�Ӹ��ڵ㿪ʼ�������нڵ�  

   //��֪Ԫ���������
//   System.out.println("title: " + element.elementText("title"));
//   System.out.println("author: " + element.elementText("author"));
//   System.out.println();
  }
public static void  getNodes(Element node){  
	    System.out.println("--------------------");  
	      
	    //��ǰ�ڵ�����ơ��ı����ݺ�����  
	    System.out.println("��ǰ�ڵ����ƣ�"+node.getName());//��ǰ�ڵ�����  
	    System.out.println("��ǰ�ڵ�����ݣ�"+node.getTextTrim());//��ǰ�ڵ�����  
	    List<Attribute> listAttr=node.attributes();//��ǰ�ڵ���������Ե�list  
	    for(Attribute attr:listAttr){//������ǰ�ڵ����������  
	        String name=attr.getName();//��������  
	        String value=attr.getValue();//���Ե�ֵ  
	        System.out.println("�������ƣ�"+name+"����ֵ��"+value);  
	    }  
	      
	    //�ݹ������ǰ�ڵ����е��ӽڵ�  
	    List<Element> listElement=node.elements();//����һ���ӽڵ��list  
	    for(Element e:listElement){//��������һ���ӽڵ�  
	    	xmlDecode.getNodes(e);//�ݹ�  
	    }  
	}  
}