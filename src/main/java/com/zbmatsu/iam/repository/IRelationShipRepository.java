package com.zbmatsu.iam.repository;

import com.zbmatsu.iam.entity.RelationShip;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */
@Repository("relationShipRepository")
public interface IRelationShipRepository extends TypedIdCassandraRepository<RelationShip, Serializable> {

    @Query("select * from relationship where user_id = :userId and target_id = :targetId ALLOW FILTERING")
    List<RelationShip> getByUserIdAndTargetId(@Param("userId") String userId, @Param("targetId") String targetId);

    /**
     * 只要where后面的不是主键就要加上ALLOW FILTERING
     * @param userId
     * @param targetType
     * @return
     */
    @Query("select * from relationship where user_id = :userId and target_type = :targetType ALLOW FILTERING")
    List<RelationShip> getByUserIdAndTargetType(@Param("userId") String userId, @Param("targetType")String targetType);
}
