package com.orangelabs.cloud4netportal.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Collectors.reducing;

/**
 * @author Ch. LE TOQUIN, 2016.02.05
 */
@Entity
@Table(name = "NcsConfig")
@NamedQueries({

})
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class NcsConfigEntity implements Serializable {

    private static final long serialVersionUID = 1249824815158908981L;
    

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name", unique = true, length = 255, nullable = true)
    @Size(max = 255)
    private String name;
    
    @Column(name = "PRNservice", unique = true, length = 255, nullable = true)
    @Size(max = 255)    
    private String PRNservice;

    @Column(name = "poolIpAddress", unique = false, length = 255, nullable = true)
    @Size(max = 255)
    private String poolIpAddress;
    
    
    @Column(name = "netmask", unique = false, length = 255, nullable = true)
    @Size(max = 255)
    private String netmask;

    
    @Column(name = "GWdevice", unique = false, length = 255, nullable = true)
    @Size(max = 255)
    private String GWdevice;
    
    
    @Column(name = "vpnIpAddress", length = 255, nullable = true)
    @Size(max = 255)
    private String vpnIpAddress ;

    @Column(name = "customDNS")
    private Boolean customDNS ;
    
    @Column(name = "pEdevice", length = 255, nullable = true)
    private String pEdevice ;

    
    @Column(name = "primaryDNS", length = 255, nullable = true)
    @Size(max = 255)
    private String primaryDNS ;



    
    @Column(name = "secondaryDNS", length = 255, nullable = true)
    @Size(max = 255)
    private String secondaryDNS ;
    
    
    @Column(name = "APN", length = 255, nullable = true)
    @Size(max = 255)
    private String APN;





    public NcsConfigEntity() {
    }

    public NcsConfigEntity(String name) {
	this.name = name;
	
    }

    


	public NcsConfigEntity(Integer id, String name, String pRNservice, String poolIpAddress, String netmask,
			String gWdevice, String vpnIpAddress, Boolean customDNS, String primaryDNS, String secondaryDNS,
			String aPN) {
		super();
		this.id = id;
		this.name = name;
		PRNservice = pRNservice;
		this.poolIpAddress = poolIpAddress;
		this.netmask = netmask;
		GWdevice = gWdevice;
		this.vpnIpAddress = vpnIpAddress;
		this.customDNS = customDNS;
		this.primaryDNS = primaryDNS;
		this.secondaryDNS = secondaryDNS;
		
		APN = aPN;
	}

	public Integer getId() {
	return this.id;
    }

	
	
	
    public String getpEdevice() {
		return pEdevice;
	}

	public void setpEdevice(String pEdevice) {
		this.pEdevice = pEdevice;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
	return this.name;
    }

    public String getpoolIpAddress() {
	return this.poolIpAddress;
    }

    public void setpoolIpAddress(String poolIpAddress) {
	this.poolIpAddress = poolIpAddress;
    }
    
    @Override
    public int hashCode() {
	int hash = 7;
	hash = 97 * hash + Objects.hashCode(this.name);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final NcsConfigEntity other = (NcsConfigEntity) obj;
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	return true;
    }

	public Boolean getCustomDNS() {
		return customDNS;
	}

	public void setCustomDNS(Boolean customDNS) {
		this.customDNS = customDNS;
	}

	public String getPrimaryDNS() {
		return primaryDNS;
	}

	public void setPrimaryDNS(String primaryDNS) {
		this.primaryDNS = primaryDNS;
	}

	public String getSecondaryDNS() {
		return secondaryDNS;
	}

	public void setSecondaryDNS(String secondaryDNS) {
		this.secondaryDNS = secondaryDNS;

	}

	public String getPRNservice() {
		return PRNservice;
	}

	public void setPRNservice(String pRNservice) {
		PRNservice = pRNservice;
	}

	public String getPoolIpAddress() {
		return poolIpAddress;
	}

	public void setPoolIpAddress(String poolIpAddress) {
		this.poolIpAddress = poolIpAddress;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getGWdevice() {
		return GWdevice;
	}

	public void setGWdevice(String gWdevice) {
		GWdevice = gWdevice;
	}

	public String getVpnIpAddress() {
		return vpnIpAddress;
	}

	public void setVpnIpAddress(String vpnIpAddress) {
		this.vpnIpAddress = vpnIpAddress;
	}

	public String getAPN() {
		return APN;
	}

	public void setAPN(String aPN) {
		APN = aPN;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NcsConfigEntity [id=" + id + ", name=" + name + ", PRNservice=" + PRNservice + ", poolIpAddress="
				+ poolIpAddress + ", netmask=" + netmask + ", GWdevice=" + GWdevice + ", vpnIpAddress=" + vpnIpAddress
				+ ", customDNS=" + customDNS + ", pEdevice=" + pEdevice + ", primaryDNS=" + primaryDNS
				+ ", secondaryDNS=" + secondaryDNS + ", APN=" + APN + "]";
	}
	
	
}
