/*
 * Copyright 2016 Orange Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orangelabs.cloud4netportal.web.rest;





import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.cloud4netportal.client.ansible.AnsibleClient;
import com.orangelabs.cloud4netportal.client.ncs.NCSRestClient;
import com.orangelabs.cloud4netportal.data.NcsConfigEntity;
import com.orangelabs.cloud4netportal.data.NcsConfigRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Ch LE TOQUIN, 2016.02.05
 */
@RestController
@RequestMapping("/api")
class NcsConfigController {

    private final NcsConfigRepository ncsConfigRepository;


  // Define the logger object for this class
  private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public NcsConfigController(final NcsConfigRepository ncsConfigRepository) {
	this.ncsConfigRepository = ncsConfigRepository;
    }

    @RequestMapping(value = "/ncsConfig", method = GET)
    public List<NcsConfigEntity> getNCSConfig() {
		List<NcsConfigEntity> rv;
	    rv = ncsConfigRepository.findAll();	
		return rv;
    }

    
    
    @RequestMapping(value = "/ncsConfig", method = POST) 
    public String createNCSConfig(final @RequestBody @Valid NcsConfigDTO newNCSConfig, final BindingResult bindingResult) {
	
	 
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}

	log.info("# Receive new value "); 
	

	/* Create a new NCS deployment*/
	log.info("# Create a new NCS deployment:");


	log.info("# Deploy vPGW:");	
	AnsibleClient sshClient=new AnsibleClient();
	sshClient.testSSHConnection();
	

	
/*	
  AnsibleClient sshClient=new AnsibleClient("10.194.60.222",22,"philippe","cloud4net");
	sshClient.deployPGW("@hosts-cvn.txt", "multisite.yml");
	*/
	
	
	final NcsConfigEntity ncsConfig = new NcsConfigEntity();
	ncsConfig.setId(2);
	
	//ncsConfig.setName("PGW-SVC-NGPoP-2");
	ncsConfig.setName("PGW-SVC-NGPoP-2");
	
	//ncsConfig.setGWdevice("vPGW-NGPoP-2");
	ncsConfig.setGWdevice("PGW-SVC-NGPoP-2");
	ncsConfig.setPRNservice("99");
	ncsConfig.setpEdevice("PE-Cevennes");
	
	
	ncsConfig.setAPN(newNCSConfig.getApnName());
	
	
	ncsConfig.setPoolIpAddress(newNCSConfig.getPoolIpClient());
	ncsConfig.setNetmask(newNCSConfig.getNetmask());
	
	ncsConfig.setVpnIpAddress(newNCSConfig.getVpnIpAddress());
	ncsConfig.setCustomDNS((newNCSConfig.getUseOrangeDns().compareTo("True")==0)?true:false);
	ncsConfig.setPrimaryDNS(newNCSConfig.getprimaryDNS());
	ncsConfig.setSecondaryDNS(newNCSConfig.getsecondaryDNS());
	
	this.ncsConfigRepository.save(ncsConfig);
	
	/*NCSRestClient restClient= new NCSRestClient("127.0.0.1",5000,"admin","admin");
	try {
		restClient.test("127.0.0.1",5000,"api/v1/test",ncsConfig);
	}
	catch (Exception e){
		log.info("# Error  " + e.getMessage());
	}
	*/
	
	NCSRestClient restClient= new NCSRestClient("172.20.34.190",8080,"admin","admin");
	try {
		restClient.initPGW("172.20.34.190",8080,"api/running/services/iCSRpGWsVC/","PGW-SVC-NGPoP-2",ncsConfig);
		//restClient.initPGW("172.20.34.190",8080,"api/running/services/iCSRpGWsVC/","vPGW-Cevennes",ncsConfig);
		
		
		
	}
	catch (Exception e){
		log.info("# Error  " + e.getMessage());
	}
	/*
	ncsConfig.setId(1);
	ncsConfig.setName("PGW-SVC-NGPOP-1");
	
	ncsConfig.setGWdevice("vPGW-NGPOP-1");
	ncsConfig.setPRNservice("100");
	
	ncsConfig.setpEdevice("PE-Cevennes"); // TO DE CONFIRMED
	
	
	ncsConfig.setAPN(newNCSConfig.getApnName());
	
	
	ncsConfig.setPoolIpAddress(newNCSConfig.getPoolIpClient());
	ncsConfig.setNetmask(newNCSConfig.getNetmask());
	
	ncsConfig.setVpnIpAddress(newNCSConfig.getVpnIpAddress());
	
	ncsConfig.setPrimaryDNS(newNCSConfig.getprimaryDNS());
	ncsConfig.setSecondaryDNS(newNCSConfig.getsecondaryDNS());
	log.info("# Add new Config: " + ncsConfig.toString());
	this.ncsConfigRepository.save(ncsConfig);
	try {
	restClient.test("127.0.0.1",5000,"api/v1/test",ncsConfig);
    }
	catch (Exception e){
		log.info("# Add new Config: " + e.getMessage());
	}
	*/
	log.info("# Save Data received ");			
	//final NcsConfigEntity ncsConfig = new NcsConfigEntity();
	return "FINISHED";	
    }
    
    

    
    @RequestMapping(value = "/ncsConfig/modify", method = POST) 
    public NcsConfigEntity updateNCSConfig(final @RequestBody @Valid NcsConfigDTO newNCSConfig, final BindingResult bindingResult) {
	
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}


	/* Create a new NCS deployment*/
	log.info("# Modify the config on NCS deployment:");


	AnsibleClient sshClient=new AnsibleClient("10.194.60.222",22,"philippe","cloud4net");
	sshClient.deployPGW("ncs-cvn", "multisite.yml");
	
	NCSRestClient restClient= new NCSRestClient("127.0.0.1",5000,"admin","admin");
	try {
		restClient.activateBoostButton("127.0.0.1",5000,"api/v1/test");
	}
	catch (Exception e){
		log.info("# Error  " + e.getMessage());
	}
	//NCSRestClient ncs=new NCSRestClient();
	
			
	final NcsConfigEntity ncsConfig = new NcsConfigEntity();
	return this.ncsConfigRepository.save(ncsConfig);	
    }
    
    

    @RequestMapping(value = "/ncsConfig/delete", method = POST ) 
    public String destroyPGW(final @RequestBody  String test, 
    		final BindingResult bindingResult) {
	
	 /*
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}*/

	log.info("# Destroy vSPGW "); 
	

	/* Create a new NCS deployment*/
	


	AnsibleClient sshClient=new AnsibleClient("10.194.60.222",22,"philippe","cloud4net");
	sshClient.deployPGW("ctcm-cvn", "vepc-clean.yml");
	
	//NCSRestClient ncs=new NCSRestClient();
		
	
	return "FINISHED";	
    }

    
    @RequestMapping(value = "/ncsConfig/boost", method = POST) 
    public String  boostButton(final @RequestBody @Valid NcsConfigDTO newNCSConfig, final BindingResult bindingResult) {
	
	 
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}

	log.info("# Boost Button: Modify the options "); 
	AnsibleClient sshClient=new AnsibleClient("10.194.60.222",22,"philippe","cloud4net");
	sshClient.deployPGW("ncs-cvn", "multisite.yml");

	/* Create a new NCS deployment*/
	log.info("# Boost Button:");

	NCSRestClient restClient= new NCSRestClient("127.0.0.1",5000,"admin","admin");
	try {
		restClient.activateBoostButton("127.0.0.1",5000,"api/v1/test");
	}
	catch (Exception e){
		log.info("# Error  " + e.getMessage());
	}
	
	/*AnsibleClient sshClient=new AnsibleClient();
	sshClient.testSSHConnection();*/
	
	//NCSRestClient ncs=new NCSRestClient();
	final NcsConfigEntity ncsConfig = new NcsConfigEntity();
	return "FINISHED";	
    }
        
         
}
