package com.ibm.ams.sap.jco;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibm.ams.util.AmsUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;


public class SapConnector {

	private static Logger logger = Logger.getLogger(SapConnector.class);
	private static final String AMS_AS_POOLED = "AMS_RFC_POOL";

	static {
		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, AmsUtil.getvalue("AMS_JCO_ASHOST"));// ������
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, AmsUtil.getvalue("AMS_JCO_SYSNR")); // ϵͳ���
		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, AmsUtil.getvalue("AMS_JCO_CLIENT")); // SAP����
		connectProperties.setProperty(DestinationDataProvider.JCO_USER, AmsUtil.getvalue("AMS_JCO_USER")); // SAP�û���
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, AmsUtil.getvalue("AMS_JCO_PASSWD")); // ����
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, AmsUtil.getvalue("AMS_JCO_LANG")); // ��¼����
		connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "200"); // ���������
		connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "50"); // ��������߳�
		createDataFile(AMS_AS_POOLED, "jcoDestination", connectProperties);
	}

	private static void createDataFile(String name, String suffix, Properties properties) {
		File cfg = new File(name + "." + suffix);
		if (cfg.exists()) {
			cfg.deleteOnExit();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(cfg, false);
			properties.store(fos, "for ams sap destination");
		} catch (Exception e) {
			logger.error("SAPdestination异常 : " + e.getMessage());
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				logger.error("SAPdestination异常: " + e.getMessage());
			}
		}
	}

	public static JCoDestination connect() {
		JCoDestination destination = null;
		try {
			destination = JCoDestinationManager.getDestination(AMS_AS_POOLED);
		} catch (JCoException e) {
			logger.error("SAPconnect异常 : " + e.getMessage());
		}
		return destination;
	}
}
