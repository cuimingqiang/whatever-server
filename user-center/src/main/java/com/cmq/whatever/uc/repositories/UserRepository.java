package com.cmq.whatever.uc.repositories;

import com.cmq.whatever.uc.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 16/5/29.
 */

@Repository
public interface UserRepository extends JpaRepository<UsersEntity,Long>{
}
