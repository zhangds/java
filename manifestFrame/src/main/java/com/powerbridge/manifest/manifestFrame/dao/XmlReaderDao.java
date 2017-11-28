package com.powerbridge.manifest.manifestFrame.dao;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Repository;

import com.powerbridge.manifest.manifestFrame.service.XmlReaderService;

@Repository
public class XmlReaderDao implements XmlReaderService{

	private SAXReader saxReader = new SAXReader();
	
	public synchronized Map<String, String> readXmlByInputStream(InputStream inputStream) throws DocumentException {
		Document document = saxReader.read(inputStream);
		return analysisMap("",getDatasMap(document));
	}
	
	public synchronized Map<String, String> readXmlByFile(File file) throws DocumentException {
		Document document = saxReader.read(file);
		return analysisMap("",getDatasMap(document));
	}
	
	public String readXmlByFileToString(File file)  throws DocumentException{
		Document document = saxReader.read(file);
		return document.asXML();
	}
	
	public synchronized Map<String, String> readXmlByString(String xmlString) throws DocumentException {
		Document document = DocumentHelper.parseText(xmlString);
		return analysisMap("",getDatasMap(document));
	}
	
	public Map<String, Object> getDatasMap(Document document){
		Map<String, Object> map = null;
		if ( document != null ){
			Element rootElement = document.getRootElement();
			//"Manifest"
			//System.out.println(rootElement.getNamespace().getText());
			//System.out.println(rootElement.getNamespaceForPrefix("xsd").getText());
			map = readNodesToLinkedHashMap(rootElement,null);
		}
		return map;
	}
	
	//
	public Map<String, Object> readNodesToLinkedHashMap(Element node,Map<String, Object> map){
		if ( map == null )
			map = new LinkedHashMap<String, Object>();
		if (!(node.getTextTrim().equals(""))) {
			map.put(node.getName(), node.getText());
		}else{
			Map<String, Object> _map = new LinkedHashMap<String, Object>();
			@SuppressWarnings("unchecked")
			Iterator<Element> iterator = node.elementIterator();
			while (iterator.hasNext()) {
				Element e = iterator.next();
				readNodesToLinkedHashMap(e,_map);
			}
			map.put(node.getName(), _map);
		}
		return map;
	}
	
	// 遍历当前节点下的所有节点
	@Deprecated
	public void listNodes(Element node) {
		System.out.println("当前节点的名称：" + node.getName());
		// 首先获取当前节点的所有属性节点
		@SuppressWarnings("unchecked")
		List<Attribute> list = node.attributes();
		// 遍历属性节点
		for (Attribute attribute : list) {
			System.out.println("属性" + attribute.getName() + ":" + attribute.getValue());
		}
		// 如果当前节点内容不为空，则输出
		if (!(node.getTextTrim().equals(""))) {
			System.out.println(node.getName() + "：" + node.getText());
		}
		// 同时迭代当前节点下面的所有子节点
		// 使用递归
		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = node.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			listNodes(e);
		}
	}
	
	public Map<String, String> analysisMap(String pixString,Map<String, Object> map){
		
		Map<String, String> resultMap = null;
		if (map != null){
			resultMap = new LinkedHashMap<String, String>();
			for (String key : map.keySet()) {
			    Object object = map.get(key);
			    if (object instanceof Map){
			    	@SuppressWarnings("unchecked")
					Map<String, String> _subMap = analysisMap(pixString+"_"+key,(Map<String,Object>)object);
			    	resultMap.putAll(_subMap);
			    }else if (object instanceof String){
			    	resultMap.put(pixString+"_"+key, object.toString());
			    }
			}
		}
		
		return resultMap;
	}

	@Deprecated
	public Map<String, String> analysisMap(Map<String, Object> map) {
		return analysisMap("",map);
	}
	
	
}
