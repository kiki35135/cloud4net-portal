package com.orangelabs.cloud4netportal.web.rest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ch LE TOQUIN, 2016.02.05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class NcsConfigDTO {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String poolIpClient;

    @NotBlank
    @Size(max = 255)
    private String vpnIpAddress;

    @NotNull
    private Boolean customDNSAddresess;

    @NotBlank
    @Size(max = 255)
    private String primaryAddressDNS;
    
    
    @NotBlank
    @Size(max = 255)
    private String secondaryAddressDNS;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPoolIpClient() {
		return poolIpClient;
	}


	public void setPoolIpClient(String poolIpClient) {
		this.poolIpClient = poolIpClient;
	}


	public String getVpnIpAddress() {
		return vpnIpAddress;
	}


	public void setVpnIpAddress(String vpnIpAddress) {
		this.vpnIpAddress = vpnIpAddress;
	}


	public Boolean getCustomDNSAddresess() {
		return customDNSAddresess;
	}


	public void setCustomDNSAddresess(Boolean customDNSAddresess) {
		this.customDNSAddresess = customDNSAddresess;
	}


	public String getPrimaryAddressDNS() {
		return primaryAddressDNS;
	}


	public void setPrimaryAddressDNS(String primaryAddressDNS) {
		this.primaryAddressDNS = primaryAddressDNS;
	}


	public String getSecondaryAddressDNS() {
		return secondaryAddressDNS;
	}


	public void setSecondaryAddressDNS(String secondaryAddressDNS) {
		this.secondaryAddressDNS = secondaryAddressDNS;
	}
    
    


}
