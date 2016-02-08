package com.orangelabs.cloud4netportal.client.ncs.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@XmlRootElement(name="iCSRpGWsVC")
public class ConfigNcsObject {
    private String name;
    
    private String aPNname;
    
    private String iPsubnet;
    
    @XmlElement(name="vPRNservice", required = true)	 
    private String PRNservice;	
    
    
    private String netmask;
    
    private String pGWdevice;
    
    private String pEdevice;
    
    
    private String primDNS;
    
    private String secDNS;


    
    
	public ConfigNcsObject() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	public ConfigNcsObject(String name, String aPNname, String iPsubnet, String netmask, String pGWdevice,
			String pEdevice, String primDNS, String secDNS, String vPRNservice) {
		super();
		this.name = name;
		this.aPNname = aPNname;
		this.iPsubnet = iPsubnet;
		this.netmask = netmask;
		this.pGWdevice = pGWdevice;
		this.pEdevice = pEdevice;
		this.primDNS = primDNS;
		this.secDNS = secDNS;
		this.PRNservice = vPRNservice;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getaPNname() {
		return aPNname;
	}
	public void setaPNname(String aPNname) {
		this.aPNname = aPNname;
	}
	public String getiPsubnet() {
		return iPsubnet;
	}
	public void setiPsubnet(String iPsubnet) {
		this.iPsubnet = iPsubnet;
	}
	public String getNetmask() {
		return netmask;
	}
	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}
	public String getpGWdevice() {
		return pGWdevice;
	}
	public void setpGWdevice(String pGWdevice) {
		this.pGWdevice = pGWdevice;
	}
	public String getpEdevice() {
		return pEdevice;
	}
	public void setpEdevice(String pEdevice) {
		this.pEdevice = pEdevice;
	}
	public String getPrimDNS() {
		return primDNS;
	}
	public void setPrimDNS(String primDNS) {
		this.primDNS = primDNS;
	}
	public String getSecDNS() {
		return secDNS;
	}
	public void setSecDNS(String secDNS) {
		this.secDNS = secDNS;
	}
	public String getPRNservice() {
		return PRNservice;
	}
	public void setvPRNservice(String vPRNservice) {
		this.PRNservice = vPRNservice;
	}
	@Override
	public String toString() {
		return "ConfigNcsObject [name=" + name + ", aPNname=" + aPNname + ", iPsubnet=" + iPsubnet + ", netmask="
				+ netmask + ", pGWdevice=" + pGWdevice + ", pEdevice=" + pEdevice + ", primDNS=" + primDNS + ", secDNS="
				+ secDNS + ", vPRNservice=" + PRNservice + "]";
	}
	

};

