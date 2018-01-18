package com.ibm.ams.sap.jco;

import com.ibm.ams.util.AmsUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 Logger logger = Logger.getLogger(Test.class);
//		 logger.error("asdfasdfasdf");
		boolean propInstance = AmsUtil.propInstance();
		//ConnectionRFC.createDataFile(name, suffix, properties);
		 Test.generateReqXml("ZFM_DMSIF131");
	}
	
	
	public static void generateReqXml(String functionName) {
		//JCoDestination destination = DmsSapConn.connect();
		JCoDestination destination = SapConnector.connect();
		JCoFunction function = null;
		try {
			function = destination.getRepository().getFunction(functionName);
			if (null != function) {
			//	String reqXml = RfcXmlUtils.generateXmlTemplete(function);
				System.out.println("---------------reqXml---------------\n" + function);

			//	System.out.println(reqXml.trim().replace("\n", "").replace("\t", "").replace("\"", "\\\""));

//				function.execute(destination);
				//String rspXml = RfcXmlUtils.fillParam2Xml(function);
			//	System.out.println("---------------rspXml---------------\n" + rspXml);
			} else {
				System.out.println("" + functionName + "");
			}
		} catch (JCoException e) {
			e.printStackTrace();
		}
	}

}
