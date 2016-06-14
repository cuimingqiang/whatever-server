package com.cmq.whatever.uc.repositories;

import com.cmq.whatever.uc.entities.UserEntity;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 16/6/1.
 */
@Repository
public interface UserRedisRepository extends KeyValueRepository<UserEntity,Long> {

}
