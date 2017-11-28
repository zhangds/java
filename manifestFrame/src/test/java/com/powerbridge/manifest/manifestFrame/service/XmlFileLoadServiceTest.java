/**
 * 
 */
package com.powerbridge.manifest.manifestFrame.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.powerbridge.manifest.manifestFrame.App;

/**
 * @author dongshengzhang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class XmlFileLoadServiceTest {
	
	@Autowired
	XmlFileLoadService xmlFileLoadService;
	
	@Test
    public void test() throws Exception{
		System.out.println(xmlFileLoadService.getFileCatalog());
		xmlFileLoadService.getall();
	}

}
