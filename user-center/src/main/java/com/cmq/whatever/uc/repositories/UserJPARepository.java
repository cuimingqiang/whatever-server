package com.cmq.whatever.uc.repositories;

import com.cmq.whatever.uc.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 16/5/29.
 */

@Transactional
@Repository
public interface UserJPARepository extends JpaRepository<UserEntity,Long>{

    @Query(value = "select a from #{#entityName} a where a.phone=:phone and 1=1")
    UserEntity findUserByPhone(@Param("phone") String phone);

    @Query(value = "select a from #{#entityName} a where a.phone=:phone and password=:password")
    UserEntity login(@Param("phone")String phone,@Param("password")String password);
}
