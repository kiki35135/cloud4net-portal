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
    public NcsConfigEntity createNCSConfig(final @RequestBody @Valid NcsConfigDTO newNCSConfig, final BindingResult bindingResult) {
	
	 
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}

	log.info("# Receive new value "); 
	

	/* Create a new NCS deployment*/
	log.info("# Create a new NCS deployment:");


	log.info("# Deploy vPGW:");	
	AnsibleClient sshClient=new AnsibleClient();
	sshClient.testSSHConnection();
	
	//NCSRestClient ncs=new NCSRestClient();
	
	log.info("# Save Data received .........................;");			
	final NcsConfigEntity ncsConfig = new NcsConfigEntity();
	return this.ncsConfigRepository.save(ncsConfig);	
    }
    
    

    
    @RequestMapping(value = "/ncsConfig/modify", method = POST) 
    public NcsConfigEntity updateNCSConfig(final @RequestBody @Valid NcsConfigDTO newNCSConfig, final BindingResult bindingResult) {
	
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}


	/* Create a new NCS deployment*/
	log.info("# Modify the config on NCS deployment:");


	
	AnsibleClient sshClient=new AnsibleClient();
	sshClient.testSSHConnection();
	
	//NCSRestClient ncs=new NCSRestClient();
	
			
	final NcsConfigEntity ncsConfig = new NcsConfigEntity();
	return this.ncsConfigRepository.save(ncsConfig);	
    }
    
    

    @RequestMapping(value = "/ncsConfig/delete", method = POST ) 
    public String destroyPGW(final @RequestBody @Valid NcsConfigDTO newNCSConfig, 
    		final BindingResult bindingResult) {
	
	 log.info("# Receive new value:");
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}

	log.info("# Destroy vSPGW "); 
	

	/* Create a new NCS deployment*/
	


		
	AnsibleClient sshClient=new AnsibleClient();
	sshClient.testSSHConnection();
	
	//NCSRestClient ncs=new NCSRestClient();
		
	
	return "FINISHED";	
    }

    
    @RequestMapping(value = "/ncsConfig/boost", method = POST) 
    public String  boostButton(final @RequestBody @Valid NcsConfigDTO newNCSConfig, final BindingResult bindingResult) {
	
	 
	if(bindingResult.hasErrors()) {
	    throw new IllegalArgumentException("Invalid arguments.");
	}

	log.info("# Boost Button: Modify the options "); 
	

	/* Create a new NCS deployment*/
	log.info("# Boost Button:");


	
	AnsibleClient sshClient=new AnsibleClient();
	sshClient.testSSHConnection();
	
	//NCSRestClient ncs=new NCSRestClient();
	final NcsConfigEntity ncsConfig = new NcsConfigEntity();
	return "FINISHED";	
    }
        
         
}
