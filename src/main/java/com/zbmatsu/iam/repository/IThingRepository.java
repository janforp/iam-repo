package com.zbmatsu.iam.repository;

import com.zbmatsu.iam.entity.Thing;
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/2.
 */
@Repository("thingRepository")
public interface IThingRepository extends TypedIdCassandraRepository<Thing, Serializable> {
}
