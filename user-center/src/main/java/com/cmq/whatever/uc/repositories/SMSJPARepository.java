package com.cmq.whatever.uc.repositories;

import com.cmq.whatever.uc.entities.SMSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 16/7/3.
 */
@Repository
@Transactional(readOnly = true)
public interface SMSJPARepository extends JpaRepository<SMSEntity,Long>{

    @Query(value = "select a from #{#entityName} a where a.phone=:phone")
    SMSEntity findSMSByPhone(@Param("phone")String phone);
}
