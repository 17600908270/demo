/**
 ****    Dom4jUtil
 ****    Dom4jUtil class build 2006/04/28 10:00
 ****    CopyRight 2005-2006 Xiaoyuer
 ****    Author : ChenXiaoyuer
 ****    Email  : windflowers1976@gmail.com
 ****    �����ʽ:
 ****    ���з���:
 ****    �޸���־:
 */
package com.example.demo.util;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * Dom4j ��һ��������ʵ��
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author xiaoyuer
 * @version 1.0
 *          <p>
 *          modifyed by pisces
 *          </p>
 */
public class Dom4jUtil {

	public static final String DEFAULT_ENCODING = "GB2312";

	/** ȱʡ�ַ��� */

	/**
	 * 
	 */
	private Dom4jUtil() {

	}

	/**
	 * Create dom4j document from xmlSource
	 * 
	 * @param xmlSource
	 *            Object
	 * @param validate
	 *            boolean
	 * @param encoding
	 *            String
	 * @throws DocumentException
	 * @throws IOException
	 * @return Document
	 */
	public static Document createDOM4jDocument(Object xmlSource,
                                               boolean validate, String encoding) throws DocumentException,
			IOException {
		// Use xerces and validate XML file
		Document doc = null;
		SAXReader saxReader = new SAXReader(true);
		saxReader.setValidation(validate);
		if (encoding == null || encoding.equals("")) {
			encoding = DEFAULT_ENCODING;
		}

		// Check input source type
		if (xmlSource instanceof String) {
			if (((String) xmlSource).startsWith("<")) {
				// Treat the String as XML code
				StringReader reader = new StringReader(xmlSource.toString());
				doc = saxReader.read(reader, encoding);
			} else {
				doc = saxReader.read(new File((String) xmlSource));
			}
		} else if (xmlSource instanceof File) {
			doc = saxReader.read((File) xmlSource);
		} else if (xmlSource instanceof InputStream) {
			doc = saxReader.read((InputStream) xmlSource);
		} else if (xmlSource instanceof Reader) {
			doc = saxReader.read((Reader) xmlSource);
		} else if (xmlSource instanceof URL) {
			doc = saxReader.read((URL) xmlSource);
		}

		return doc;
	}

	/**
	 * create a new Document
	 * 
	 * @return Document
	 * @added by jiayc
	 */
	public static Document createDocument() {
		DocumentFactory factory = new DocumentFactory();
		Document document = factory.createDocument();
		return document;
	}

	/**
	 * save xml to file
	 * 
	 * @param xmlSource
	 * @param fileName
	 * @throws IOException
	 */
	public static void saveXML(Object xmlSource, String fileName)
			throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter output = new XMLWriter(new FileWriter(fileName), format);
		if (xmlSource instanceof Document) {
			output.write((Document) xmlSource);
		} else if (xmlSource instanceof String) {
			output.write((String) xmlSource);
		} else if (xmlSource instanceof Text) {
			output.write((Text) xmlSource);
		} else if (xmlSource instanceof Node) {
			output.write((Node) xmlSource);
		} else {
			output.write(xmlSource);
		}

		output.close();
	}

	/**
	 * This method will generate XML file in a StringBuffer based on the given
	 * Dom4j object.
	 * 
	 * @param dom4jObj
	 *            Object
	 * @param encoding
	 *            String
	 * @throws IOException
	 * @return StringBuffer
	 */
	public static/* synchronized closed 20070622 by cxy */StringBuffer GenerateXMLStringBuffer(
			Object dom4jObj, String encoding) throws IOException {
		StringWriter writer = new StringWriter();
		OutputFormat outformat = OutputFormat.createPrettyPrint();
		// ���ñ�������
		if (encoding == null || encoding.trim().equals("")) {
			encoding = DEFAULT_ENCODING;
		}
		outformat.setEncoding(encoding);
		// dom4j ֧��ֱ�����OBJECT
		XMLWriter xmlWriter = null;
		xmlWriter = new XMLWriter(writer, outformat);
		xmlWriter.write(dom4jObj);
		xmlWriter.flush();

		return writer.getBuffer();
	}

	/**
	 * �������DOM4J��ĳ����������BUFFER�����Ǻ����ã�ֻ�ܵ�һ�㣿
	 * 
	 * @param dom4jObj
	 *            Object
	 * @param encoding
	 *            String
	 * @param className
	 *            String
	 * @param methodName
	 *            String
	 * @param parameterTypes
	 *            Class[]
	 * @param parameterValues
	 *            Object[]
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @return StringBuffer
	 */
	public static/** synchronized */
	StringBuffer GenerateXMLStringBufferByMethodInvoke(Object dom4jObj,
			String encoding, String className, String methodName,
			Class[] parameterTypes, Object[] parameterValues)
			throws IOException, ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class componentClass = null;
		componentClass = Class.forName(className);
		Method method;
		method = componentClass.getMethod(methodName, parameterTypes);
		Object o = method.invoke(dom4jObj, parameterValues);
		return GenerateXMLStringBuffer(o, encoding);
	}

	/**
	 * ����ڵ㣬���Ǻ÷��������к÷��������
	 * 
	 * @param parent
	 *            Element param insert
	 * @param insertBefore
	 *            Element
	 * @return Element
	 */
	public static Element insertChild(Element parent, Element toInsert,
                                      Element insertBefore) {
		String str = parent.asXML();
		String before = insertBefore.asXML();
		String insert = toInsert.asXML();
		int pos = -1;
		if ((pos = str.indexOf(before)) >= 0) {
			str = str.substring(0, pos) + insert + str.substring(pos);
		}

		try {
			parent = Dom4jUtil.createDOM4jDocument(str, false, "")
					.getRootElement();
		} catch (Exception ex) {
		}

		return parent;
	}

	/**
	 * ����ڵ㣬���Ǻ÷��������к÷��������
	 * 
	 * @param parent
	 *            Element param insert
	 * @param insertBefore
	 *            Element
	 * @return Element
	 */
	public static Element insertChildAfter(Element parent, Element toInsert,
                                           Element insertAfter) {
		String str = parent.asXML();
		String after = insertAfter.asXML();
		String insert = toInsert.asXML();
		int pos = -1;
		if ((pos = str.indexOf(after)) >= 0) {
			pos += after.length();
			str = str.substring(0, pos) + insert + str.substring(pos);
		}

		try {
			parent = Dom4jUtil.createDOM4jDocument(str, false, "")
					.getRootElement();
		} catch (Exception ex) {
		}

		return parent;
	}

	/**
	 * �����Element�ڵ㣬ֵΪString����
	 * 
	 * @param parent
	 *            ���ڵ�
	 * @param name
	 *            �½ڵ�����
	 * @param value
	 *            �½ڵ�ֵ
	 * @return element
	 * @throws XMLDocException
	 */
	public static Element appendChild(Element parent, String name, String value) {
		if (parent == null) {
			return null;
		}

		Element element = parent.addElement(new QName(name, parent
				.getNamespace()));
		if (value != null) {
			element.addText(value);
		}
		return element;
	}

	/**
	 * ɾ���ӽڵ�
	 * 
	 * @param parent
	 *            Element ���ڵ�
	 * @param name
	 *            String ɾ���ڵ���
	 * @param value
	 *            String ɾ���ڵ�ֵ
	 * @return Element
	 */
	public static Element removeChild(Element parent, String name, String value) {
		if (parent == null) {
			return null;
		}

		Iterator iter = parent.elementIterator();
		Element del = null;
		while (iter.hasNext()) {
			Element tmp = (Element) iter.next();
			if (tmp.getName().equals(name) && tmp.getText().equals(value)) {
				del = tmp;
			}
		}
		if (del != null) {
			parent.remove(del);
		}
		return parent;
	}

	/**
	 * �������
	 * 
	 * @param ele
	 *            Element
	 * @param attributeName
	 *            String
	 * @param attributeValue
	 *            String
	 * @return Element
	 */
	public static Element appendAttribute(Element ele, String attributeName,
                                          String attributeValue) {
		if (ele == null) {
			return null;
		}

		ele.addAttribute(attributeName, attributeValue);
		return ele;
	}

	/**
	 * ɾ������
	 * 
	 * @param ele
	 *            Element
	 * @param attributeName
	 *            String
	 * @return Element
	 */
	public static Element removeAttribute(Element ele, String attributeName) {
		if (ele == null) {
			return null;
		}

		Attribute att = ele.attribute(attributeName);
		ele.remove(att);
		return ele;
	}

	/**
	 * �޸�����
	 * 
	 * @param ele
	 *            Element
	 * @param attributeName
	 *            String
	 * @param attributeValue
	 *            String
	 * @return Element
	 */
	public static Element setAttribute(Element ele, String attributeName,
                                       String attributeValue) {
		if (ele == null) {
			return null;
		}

		Attribute att = ele.attribute(attributeName);
		if (att != null) {
			att.setText(attributeValue);
		} else {
			Dom4jUtil.appendAttribute(ele, attributeName, attributeValue);
		}
		return ele;
	}

	/**
	 * ȡElement�ڵ��µ������ӽڵ����ݣ�ת�����ַ���
	 * 
	 * @param element
	 *            Element
	 * @return String
	 */
	public static String getElementContext(Element element) {
		if (element == null) {
			return null;
		}
		String str = element.getText();
		for (Iterator i = element.elementIterator(); i.hasNext();) {
			Element elementTmp = (Element) i.next();
			str = str + elementTmp.asXML();
			// do something
		}
		return str;
	}

	/**
	 * 
	 * @param element
	 *            Element
	 * @param path
	 *            String
	 * @param attr
	 *            String
	 * @return String
	 */
	public static String getElementContext(Element element, String path,
                                           String attr) {
		Element el = element.element(path);
		if (attr == null || attr.trim().equals("")) {
			return el.getText();
		} else {
			return el.attributeValue(attr);
		}
	}

	/**
	 * ����XPATH ��ȡԪ�����ݣ�text�� "/path/@seq" ��ȡ����ֵ "/path/" ��ȡԪ��
	 * 
	 * @param element
	 *            Element
	 * @param path
	 *            String
	 * @return String
	 */
	public static String getElementContext(Element element, String path) {
		if (element == null || path == null) {
			return null;
		}

		Object o = element.selectSingleNode(path);
		if (o == null) { // �޴˽ڵ�
			return null;
		}

		if (o instanceof Element) { // 1��Ԫ�� Element
			Element e = (Element) o;
			if (e.isTextOnly()) { // text only
				return e.getText();
			} else { // element
				try {
					return Dom4jUtil.GenerateXMLStringBuffer(e, "").toString();
				} catch (IOException ex1) {
					return null;
				}
			}
		} else if (o instanceof Attribute) { // 2������ Attribute
			return ((Attribute) o).getValue();
		} else { // 3������ Other node
			try {
				return Dom4jUtil.GenerateXMLStringBuffer(o, "").toString();
			} catch (IOException ex) {
				return null;
			}
		}
	}

	/**
	 * ����XPATH ��ȡԪ�����ݣ�text�� "/path/@seq" ��ȡ����ֵ "/path/" ��ȡԪ��
	 * 
	 * @param element
	 *            Element
	 * @param path
	 *            String
	 * @return String
	 */
	public static String[] getElementContextArray(Element element, String path) {
		if (element == null || path == null) {
			return null;
		}
		List nodes = element.selectNodes(path);
		String[] eleContext = new String[nodes.size()];
		Iterator iter = nodes.iterator();
		int length = 0;
		while (iter.hasNext()) {
			Object o = (Object) iter.next();
			// Object o = element.selectNodes(path);
			if (o instanceof Element) { // 1��Ԫ�� Element
				Element e = (Element) o;
				if (e.isTextOnly()) { // text only
					// return e.getText();
					eleContext[length] = e.getText();
					length++;
				} else { // element
					try {
						eleContext[length] = Dom4jUtil.GenerateXMLStringBuffer(
								e, "").toString();
						length++;
						// return Dom4jUtil.GenerateXMLStringBuffer(e,
						// "").toString();
					} catch (IOException ex1) {
						return null;
					}
				}
			} else if (o instanceof Attribute) { // 2������ Attribute
				// return ( (Attribute) o).getValue();
				eleContext[length] = ((Attribute) o).getValue();
				length++;
			} else { // 3������ Other node
				try {
					eleContext[length] = Dom4jUtil.GenerateXMLStringBuffer(o,
							"").toString();
					length++;
					// return Dom4jUtil.GenerateXMLStringBuffer(o,
					// "").toString();
				} catch (IOException ex) {
					return null;
				}
			}
		}
		return eleContext;
	}

	/**
	 * �����Element�ڵ㣬ֵΪString����
	 * 
	 * @param root
	 *            ���ڵ�
	 * @param xpath
	 *            �½ڵ�·�� eg:/root/a/a1 or /root/b/b1/@seq
	 * @param value
	 *            �½ڵ�ֵ
	 * @param isGroup
	 *            boolean
	 * @return element
	 */
	public static Element appendElement(Element root, String xpath,
                                        String value, boolean isGroup) {
		if (root == null) {
			return null;
		}
		int j = xpath.lastIndexOf("/");
		String path = xpath.substring(0, j);
		Element node = (Element) root.selectSingleNode(path);
		String[] paths = xpath.split("/");
		if (isGroup) {
			if (node == null) {
				for (int i = 2; i < paths.length; i++) {
					root = root.addElement(new QName(paths[i], root
							.getNamespace()));
				}
			} else {
				root = node.addElement(new QName(paths[paths.length - 1], root
						.getNamespace()));
			}
		} else {
			for (int i = 2; i < paths.length; i++) {
				root = root
						.addElement(new QName(paths[i], root.getNamespace()));
			}
		}

		if (value != null) {
			root.addText(value);

		}

		return root;
	}

	/**
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) throws IOException {
		Document document = Dom4jUtil.createDocument();
		Element root = document.addElement(new QName("root"));

		Dom4jUtil.appendChild(root, "hi", "hi");
		Dom4jUtil.appendChild(root, "hi1", "1");
		Dom4jUtil.appendChild(root, "hi2", "2");

		root.addElement("kaka");

		root = Dom4jUtil
				.insertChild(root, (Element) root.selectSingleNode("hi1"),
						(Element) root.selectSingleNode("hi"));
		root = Dom4jUtil
				.insertChildAfter(root, (Element) root.selectSingleNode("hi2"),
						(Element) root.selectSingleNode("hi"));

		System.out.println(Dom4jUtil.GenerateXMLStringBuffer(root, ""));
	}
}
