package com.powerbridge.manifest.manifestFrame.service;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.powerbridge.manifest.manifestFrame.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class DataSaveDbServiceTest {

	@Autowired
	XmlReaderService xmlReaderService;
	
	@Autowired
	DataSaveDbService dataSaveDbService;
	
	//水运出口预配舱单主要/其它数据申报及变更报文
	private String xml = null;
	
	@Before
	public void init(){
		xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<Manifest xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"urn:Declaration:datamodel:standard:CN:MT2201:1\">"
				+ "<Head><MessageID>CN_MT2201_1P0_4712666767427_20171031093052916</MessageID>"
				+ "<FunctionCode>9</FunctionCode><MessageType>MT2201</MessageType>"
				+ "<SenderID>4712666767427_47001002</SenderID><ReceiverID>4712</ReceiverID>"
				+ "<SendTime>20171031093052461</SendTime><Version>1.0</Version></Head>"
				+ "<Declaration><RepresentativePerson><Name>4201910028</Name></RepresentativePerson>"
				+ "<ExitCustomsOffice><ID>WUH</ID></ExitCustomsOffice><Carrier><ID>MI</ID>"
				+ "</Carrier><BorderTransportMeans><JourneyID>MI987/20171031</JourneyID><TypeCode>4</TypeCode>"
				+ "</BorderTransportMeans><Consignment><TransportContractDocument><ID>61843844743</ID>"
				+ "<ConditionCode>10</ConditionCode></TransportContractDocument>"
				+ "<LoadingLocation><ID>WUH/4712</ID><LoadingDate>201710310930086</LoadingDate></LoadingLocation>"
				+ "<UnloadingLocation><ID>WUH/4712</ID><ArrivalDate>20171031</ArrivalDate></UnloadingLocation>"
				+ "<TransportSplitIndicator>0</TransportSplitIndicator>"
				+ "<FreightPayment><MethodCode>PP</MethodCode></FreightPayment>"
				+ "<ConsignmentPackaging><QuantityQuantity>9</QuantityQuantity></ConsignmentPackaging>"
				+ "<TotalGrossMassMeasure>680</TotalGrossMassMeasure>"
				+ "<Consignee><Name>MIQ LOGISTICS THAILAND CO LTD</Name>"
				+ "<Address><Line>21F 253 SOI ASOKE SUKHUMVIT 21 ROAD</Line>"
				+ "<CityName>SINGAPORE</CityName><CountryCode>SG</CountryCode></Address></Consignee>"
				+ "<Consignor><Name>JHJ INTERNATIONAL TRANSPORTATION</Name><Address>"
				+ "<Line>ROOM 904 FLOOR TUROLL PLAZA WUSHENG</Line><CityName>WUH</CityName></Address></Consignor>"
				+ "<ConsignmentItem><SequenceNumeric>1</SequenceNumeric>"
				+ "<ConsignmentItemPackaging><QuantityQuantity>9</QuantityQuantity></ConsignmentItemPackaging>"
				+ "<Commodity><CargoDescription>CONSOL</CargoDescription></Commodity>"
				+ "<GoodsMeasure><GrossMassMeasure>680</GrossMassMeasure></GoodsMeasure></ConsignmentItem></Consignment>"
				+ "</Declaration></Manifest>";

	}
	
	@Test
    public void test() throws Exception{
		assertTrue( true );
		Map<String, String> maps = xmlReaderService.readXmlByString(xml);
		//System.out.println(maps);
		//assertTrue(maps.size() == 1);
		dataSaveDbService.saveDataToDB(maps);
		assertTrue(maps.size() > 1);
		
    }

}
