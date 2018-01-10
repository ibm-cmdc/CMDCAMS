package com.ibm.ams.test;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.ams.sap.jco.SapConnector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestAction  {
	
	private static Logger logger = Logger.getLogger(TestAction.class);
	
	@Autowired
	private  Print ps;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String execute() throws Exception {
		
		ps.print();
		
		logger.info("asffddfdf--0-0-0-0-0-");
		return "S";
	}

}
