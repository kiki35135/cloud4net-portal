package com.orangelabs.cloud4netportal.client.ncs.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@XmlRootElement(name="input")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InputObject {
	@XmlElement(name="chassis-name")	
	private String chassis_name;
	
	

	public InputObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InputObject(String chassis_name) {
		super();
		this.chassis_name = chassis_name;
	}

	public String getChassis_name() {
		return chassis_name;
	}

	public void setChassis_name(String chassis_name) {
		this.chassis_name = chassis_name;
	}
};
