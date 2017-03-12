package com.zbmatsu.iam.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zbmatsu.iam.entity.User;


/**
 * Created by Janita on 2016/12/31.
 * spring-data packaged hibernate
 *
 * you can also create your sql
 *
 * PagingAndSortingRepository
 *
 * 相当于dao
 */
@Repository("userRepository")
public interface IUserRepository extends TypedIdCassandraRepository<User, Serializable> {

    @Query("select * from user where user_name = :userName and password = :password ALLOW FILTERING")
    List<User> getUserByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
