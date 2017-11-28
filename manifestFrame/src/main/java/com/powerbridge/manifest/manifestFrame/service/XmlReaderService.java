package com.powerbridge.manifest.manifestFrame.service;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

@Service
public interface XmlReaderService {

	public Map<String, String> readXmlByInputStream(InputStream inputStream) throws DocumentException;
	
	public Map<String, String> readXmlByFile(File file) throws DocumentException;
	
	public String readXmlByFileToString(File file)  throws DocumentException;
	
	public Map<String, String> readXmlByString(String xmlString) throws DocumentException;
	
	/**
	 * @category 解析xml生成的map转换成Map对象
	 * @param map
	 * @return
	 */
	public Map<String, String> analysisMap(Map<String, Object> map);
	
}
