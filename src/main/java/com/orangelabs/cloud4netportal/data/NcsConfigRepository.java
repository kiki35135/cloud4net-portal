package com.orangelabs.cloud4netportal.data;

import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ch. LE TOQUIN, 2016.02.05
 */
public interface NcsConfigRepository extends JpaRepository<NcsConfigEntity, Integer> {
    
    NcsConfigEntity findByName(final String name);
     

}
