package com.orangelabs.cloud4netportal.client.ncs;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.orangelabs.cloud4netportal.client.ncs.data.ConfigNcsObject;
import com.orangelabs.cloud4netportal.client.ncs.data.InputObject;
import com.orangelabs.cloud4netportal.data.NcsConfigEntity;

import groovy.util.ConfigObject;



public class NCSRestClient {
	
	private final String IP_NCS_LANNION="";
	private final String IP_NCS_PARIS="";
	private final String IP_NCS_RENNES="";
	
	
	private final String URL_NGPOP1="/api/running/services/iCSRpGWsVC/PGW-SVC-NGPoP-1";

	private final String URL_NGPOP2="/api/running/services/iCSRpGWsVC/PGW-SVC-NGPoP-2";



	private final String APLICATION_TYPE="application/vnd.yang.data+xml";

	
	
	private final String api_path_init ="/api/running/services/iCSRpGWsVC";
	private final String api_path_modify ="/api/running/services/";

	
	private String host="";
	private int port=8008;
	private String user="admin";
	private String pwd="admin";
	
	private String name="";
	



	public NCSRestClient()
	{
		this.host="127.0.0.0.1";
		this.port=8008;
		this.user="guest";
		this.pwd="guest";
	}
	
		
	public NCSRestClient(String host, int port, String user, String pwd, String name) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.pwd = pwd;
		this.name = name;
	}






	public boolean initPGW(String host, int port, String name, NcsConfigEntity value)
	{
		boolean ret=false;
		String url= new String("https://");
		url= url.concat(host);
		url= url.format("http://%s:%i/%s/%s", host,port,api_path_init,name);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String plainCreds = "admin:admin";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type",APLICATION_TYPE);
		
		
		//make the object
		ConfigNcsObject  obj = new ConfigNcsObject();
		obj.setName(value.getName());
		obj.setPrimDNS(value.getPrimaryDNS());
		obj.setSecDNS(value.getSecondaryDNS());
		obj.setiPsubnet(value.getpoolIpAddress());
		obj.setvPRNservice(value.getPRNservice());
		obj.setpGWdevice(value.getGWdevice());
		obj.setNetmask(value.getNetmask());
		obj.setaPNname(value.getAPN());
		
		
		//set your entity to send
		HttpEntity entity = new HttpEntity(obj,headers);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		
		
		// send it!
		ResponseEntity<String> out =
				restTemplate.exchange(url, HttpMethod.POST, entity,String.class );
		
		
		System.out.println(out.getBody());
	    System.out.println(out.getStatusCode());
		

		return ret;
	}


	public boolean activateBoostButton(String host, int port, String name)
	{
		boolean ret=false;
		String url= new String("https://");
		url= url.concat(host);
		url= url.format("http://%s:%i/%s/%s", host,port,api_path_modify,name);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String plainCreds = "ctcm-admin:cisco ";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type",APLICATION_TYPE);
		
		
		//make the object
		InputObject  obj = new InputObject();
		obj.setChassis_name(new String("vpc-01"));
		
		
		//set your entity to send
		HttpEntity entity = new HttpEntity(obj,headers);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		
		
		// send it!
		ResponseEntity<String> out =
				restTemplate.exchange(url, HttpMethod.POST, entity,String.class );
		
		
		System.out.println(out.getBody());
	    System.out.println(out.getStatusCode());
		

		return ret;
	}


};
