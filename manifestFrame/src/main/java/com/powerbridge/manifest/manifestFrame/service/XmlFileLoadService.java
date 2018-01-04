/**
 * 
 */
package com.powerbridge.manifest.manifestFrame.service;

import java.io.File;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Data;

/**
 * @author dongshengzhang
 *
 */
@ConfigurationProperties(prefix="com.powerbridge.manifest.manifestFrame.scan")
@Service
@Data
public class XmlFileLoadService {

	private final static Logger logger = LoggerFactory.getLogger(XmlFileLoadService.class);
	@Autowired
	XmlReaderService xmlReaderService;
	
	@Autowired
	DataSaveDbService dataSaveDbService;
	
	String fileCatalog;
	
	//@Scheduled(cron = "0 0/30 * * * ?")
	public void getall(){
		getFileList("");
	}
	public void getFileList(String strPath) {
		if (strPath == null ||strPath.equals("")){
			strPath = fileCatalog;
		}
		File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                //String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else { 
                    logger.info("======"+files[i].getName()+"开始解析");
                	try {
    					Map<String, String> maps = xmlReaderService.readXmlByFile(files[i]);
    					dataSaveDbService.saveDataToDB(maps);
    				} catch (Exception e) {
    					logger.error("%%%%%%%%%%%%错误的解析:"+e.getMessage());
    					try {
    						logger.error(xmlReaderService.readXmlByFileToString(files[i]));
    					} catch (DocumentException e1) {
    						logger.error("解析错误");
    					}
    				}
                	logger.info("======"+files[i].getName()+"解析完成");
                }
            }

        }
        
    }
	
	//@Scheduled(cron = "0 0/30 * * * ?")
	public void getAllFile(){
		File f = new File(fileCatalog);
        if (!f.exists()) {
            System.out.println(fileCatalog + " not exists");
            logger.info(fileCatalog + " not exists");
            return;
        }

        File file[] = f.listFiles();
        for (int i = 0; i < file.length; i++) {
            File fs = file[i];
            if (fs.isDirectory()) {
            	logger.debug(fs.getName() + " [目录]");
            } else {
            	logger.info("======"+fs.getName()+"开始解析");
            	try {
					Map<String, String> maps = xmlReaderService.readXmlByFile(fs);
					dataSaveDbService.saveDataToDB(maps);
				} catch (Exception e) {
					logger.error("%%%%%%%%%%%%错误的解析:"+e.getMessage());
					try {
						logger.error(xmlReaderService.readXmlByFileToString(fs));
					} catch (DocumentException e1) {
						logger.error("解析错误");
					}
				}
            	logger.info("======"+fs.getName()+"解析完成");
            }
        }
	}
}
