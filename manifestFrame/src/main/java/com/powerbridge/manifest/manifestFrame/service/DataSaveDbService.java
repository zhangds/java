/**
 * 
 */
package com.powerbridge.manifest.manifestFrame.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author dongshengzhang
 *
 */
@Service
public interface DataSaveDbService {

	public void saveDataToDB(Map<String, String> map) throws Exception;
}
