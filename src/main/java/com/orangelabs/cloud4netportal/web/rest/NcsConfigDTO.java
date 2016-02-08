package com.orangelabs.cloud4netportal.web.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ch LE TOQUIN, 2016.02.05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class NcsConfigDTO {
	/*
 	parameters = {
 	 		name: 'PGW-SVC-NGPoP',
 	 		poolIpClient: '172.20.70.192',
 	 		netmask: "255.255.255.240",
 	 		vpnIpAddress: "172.20.70.192",
 	 		useOrangeDns: 'False',
 	 		primaryDNS:'172.20.4.148',
 	 		secondaryDNS:'172.20.104.1',
 	 		apnName:'vepc6co',
 	 		options:{
 	 			highResiliency: 'False',
 	 			autoElasticity: 'True',
 	 			advancedParam: 'False'
 	 		}
 	 	};
 	*/
    @Embeddable
	public static class Options {
		@NotBlank
	    @Size(max = 255)
		public String highResiliency;
		@NotBlank
	    @Size(max = 255)
		public  String autoElasticity;
		@NotBlank
	    @Size(max = 255)
		public String advancedParam;
		public String getHighResiliency() {
			return highResiliency;
		}
		public void setHighResiliency(String highResiliency) {
			this.highResiliency = highResiliency;
		}
		public String getAutoElasticity() {
			return autoElasticity;
		}
		public void setAutoElasticity(String autoElasticity) {
			this.autoElasticity = autoElasticity;
		}
		public String getAdvancedParam() {
			return advancedParam;
		}
		public void setAdvancedParam(String advancedParam) {
			this.advancedParam = advancedParam;
		}
		public Options() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "options [highResiliency=" + highResiliency + ", autoElasticity=" + autoElasticity
					+ ", advancedParam=" + advancedParam + "]";
		}
		public Options(String highResiliency, String autoElasticity, String advancedParam) {
			super();
			this.highResiliency = highResiliency;
			this.autoElasticity = autoElasticity;
			this.advancedParam = advancedParam;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((advancedParam == null) ? 0 : advancedParam.hashCode());
			result = prime * result + ((autoElasticity == null) ? 0 : autoElasticity.hashCode());
			result = prime * result + ((highResiliency == null) ? 0 : highResiliency.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Options other = (Options) obj;
			if (advancedParam == null) {
				if (other.advancedParam != null)
					return false;
			} else if (!advancedParam.equals(other.advancedParam))
				return false;
			if (autoElasticity == null) {
				if (other.autoElasticity != null)
					return false;
			} else if (!autoElasticity.equals(other.autoElasticity))
				return false;
			if (highResiliency == null) {
				if (other.highResiliency != null)
					return false;
			} else if (!highResiliency.equals(other.highResiliency))
				return false;
			return true;
		}
		
		
		
	}
    
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String poolIpClient;
    
    @NotBlank
    @Size(max = 255)
    private String netmask;

    @NotBlank
    @Size(max = 255)
    private String vpnIpAddress;

    
    @NotBlank
    @Size(max = 255)
    private String useOrangeDns;


    @NotBlank
    @Size(max = 255)
    private String primaryDNS;
    
    
    @NotBlank
    @Size(max = 255)
    private String secondaryDNS;
    
    @NotBlank
    @Size(max = 255)    
    private String apnName;

    @Embedded
    private Options options;
    
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





	public String getNetmask() {
		return netmask;
	}


	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}


	public String getUseOrangeDns() {
		return useOrangeDns;
	}


	public void setUseOrangeDns(String useOrangeDns) {
		this.useOrangeDns = useOrangeDns;
	}


	public String getprimaryDNS() {
		return primaryDNS;
	}


	public void setprimaryDNS(String primaryDNS) {
		this.primaryDNS = primaryDNS;
	}


	public String getsecondaryDNS() {
		return secondaryDNS;
	}


	public void setsecondaryDNS(String secondaryDNS) {
		this.secondaryDNS = secondaryDNS;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apnName == null) ? 0 : apnName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((netmask == null) ? 0 : netmask.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((poolIpClient == null) ? 0 : poolIpClient.hashCode());
		result = prime * result + ((primaryDNS == null) ? 0 : primaryDNS.hashCode());
		result = prime * result + ((secondaryDNS == null) ? 0 : secondaryDNS.hashCode());
		result = prime * result + ((useOrangeDns == null) ? 0 : useOrangeDns.hashCode());
		result = prime * result + ((vpnIpAddress == null) ? 0 : vpnIpAddress.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NcsConfigDTO other = (NcsConfigDTO) obj;
		if (apnName == null) {
			if (other.apnName != null)
				return false;
		} else if (!apnName.equals(other.apnName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (netmask == null) {
			if (other.netmask != null)
				return false;
		} else if (!netmask.equals(other.netmask))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (poolIpClient == null) {
			if (other.poolIpClient != null)
				return false;
		} else if (!poolIpClient.equals(other.poolIpClient))
			return false;
		if (primaryDNS == null) {
			if (other.primaryDNS != null)
				return false;
		} else if (!primaryDNS.equals(other.primaryDNS))
			return false;
		if (secondaryDNS == null) {
			if (other.secondaryDNS != null)
				return false;
		} else if (!secondaryDNS.equals(other.secondaryDNS))
			return false;
		if (useOrangeDns == null) {
			if (other.useOrangeDns != null)
				return false;
		} else if (!useOrangeDns.equals(other.useOrangeDns))
			return false;
		if (vpnIpAddress == null) {
			if (other.vpnIpAddress != null)
				return false;
		} else if (!vpnIpAddress.equals(other.vpnIpAddress))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "NcsConfigDTO [name=" + name + ", poolIpClient=" + poolIpClient + ", netmask=" + netmask
				+ ", vpnIpAddress=" + vpnIpAddress + ", useOrangeDns=" + useOrangeDns + ", primaryDNS="
				+ primaryDNS + ", secondaryDNS=" + secondaryDNS + ", apnName=" + apnName
				+ ", options=" + options + "]";
	}


	public Options getOptions() {
		return options;
	}


	public void setOptions(Options options) {
		this.options = options;
	}


	public String getApnName() {
		return apnName;
	}


	public void setApnName(String apnName) {
		this.apnName = apnName;
	}
    
	
	
    


}
