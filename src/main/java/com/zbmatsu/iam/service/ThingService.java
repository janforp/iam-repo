package com.zbmatsu.iam.service;

import com.zbmatsu.iam.entity.Thing;
import com.zbmatsu.iam.exception.NotFoundException;
import com.zbmatsu.iam.exception.ResourceMismatchingException;
import com.zbmatsu.iam.repository.IThingRepository;
import com.zbmatsu.iam.vo.ThingBean;
import com.zbmatsu.iam.vo.UpdateThingBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */
@Service("thingService")
public class ThingService {

    @Autowired
    protected IThingRepository thingRepository;


    /**
     * create thing
     * @param thingBean
     * @return
     */
    public ThingBean createThing(ThingBean thingBean){

        LocalDateTime current = LocalDateTime.now();
        thingBean.setCreationTime(current);
        thingBean.setModificationTime(current);

        Thing thing = new Thing();
        BeanUtils.copyProperties(thingBean, thing);

        thingRepository.save(thing);
        return thingBean;
    }

    /**
     * delete thing
     * @param id
     */
    public void deleteThing(String id){

        if (!exists(id)){
            throw new NotFoundException("thing is not exists");
        }
        thingRepository.delete(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    public void batchDeleteThings(List<Serializable> ids){

        Iterable<Thing> list = getThings(ids);

        thingRepository.delete(list);
    }


    /**
     * update thing
     * @param thingBean
     * @return
     */
    public ThingBean updateThing(ThingBean thingBean){

        LocalDateTime current = LocalDateTime.now();
        thingBean.setModificationTime(current);

        Thing thing = new Thing();
        BeanUtils.copyProperties(thingBean, thing);

        thingRepository.save(thing);
        return thingBean;
    }


    /**
     * update thing
     * @param id
     * @param updateThingBean
     * @return
     */
    public ThingBean updateThing(String id, UpdateThingBean updateThingBean){

        ThingBean thingBean = getThingById(id);

        if(StringUtils.isEmpty(updateThingBean)){
            throw new ResourceMismatchingException("resource is not mismatching");
        }

        if(!StringUtils.isEmpty(updateThingBean.getDescription())){
            thingBean.setDescription(updateThingBean.getDescription());
        }

        if(!StringUtils.isEmpty(updateThingBean.getExtension())){
            thingBean.setExtension(updateThingBean.getExtension());
        }

        return updateThing(thingBean);
    }

    /**
     * get thing by id
     * @param id
     * @return
     */
    public ThingBean getThingById(String id){

        if(!exists(id)){
            throw new NotFoundException("thing is not exists");
        }

        Thing thing = thingRepository.findOne(id);

        ThingBean thingBean = new ThingBean();
        BeanUtils.copyProperties(thing, thingBean);

        return thingBean;
    }


    /**
     * 根据thing Id 批量查询
     * @param ids
     * @return
     */
    public List<ThingBean> getThingListByIds(List<Serializable> ids){

        Iterable<Thing> list = getThings(ids);

        List<ThingBean> result = new ArrayList<>();

        list.forEach(thing -> {

            ThingBean thingBean = new ThingBean();
            BeanUtils.copyProperties(thing, thingBean);
            result.add(thingBean);
        });

        return result;
    }

    private Iterable<Thing> getThings(List<Serializable> ids){
        return thingRepository.findAll(ids);
    }

    private boolean exists(String id){

        return thingRepository.exists(id);
    }

}
