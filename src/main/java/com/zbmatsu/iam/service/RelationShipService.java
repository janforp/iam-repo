package com.zbmatsu.iam.service;

import com.zbmatsu.iam.entity.RelationShip;
import com.zbmatsu.iam.exception.NotFoundException;
import com.zbmatsu.iam.exception.ResourceMismatchingException;
import com.zbmatsu.iam.repository.IRelationShipRepository;
import com.zbmatsu.iam.vo.RelationShipBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/3/3.
 */
@Service("relationShipService")
public class RelationShipService {

    @Autowired
    protected IRelationShipRepository relationShipRepository;

    /**
     * create relationship
     * @param relationShipBean
     * @return
     */
    public RelationShipBean createRelationShip(RelationShipBean relationShipBean) {

        String uuid = UUID.randomUUID().toString();
        relationShipBean.setId(uuid);

        LocalDateTime current = LocalDateTime.now();
        relationShipBean.setCreationTime(current);

        RelationShip relationShip = new RelationShip();
        BeanUtils.copyProperties(relationShipBean, relationShip);

        relationShipRepository.save(relationShip);
        return relationShipBean;
    }

    /**
     * 根据userId和targetId删除RelationShip
     * @param userId
     * @param targetId
     */
    public void deleteRelationShip(String userId, String targetId){

        RelationShipBean relationShipBean = getRelationShipByUserIdAndTargetId(userId, targetId);
        relationShipRepository.delete(relationShipBean.getId());
    }

    /**
     * 根据Id删除RelationShip
     * @param id
     */
    public void deleteRelationShip(String id){

        if(!exists(id)){
            throw new NotFoundException("relationship is not exists");
        }
        relationShipRepository.delete(id);
    }

    /**
     * 根据userId和targetId 查询唯一 RelationShip
     * @param userId
     * @param targetId
     * @return
     */
    public RelationShipBean getRelationShipByUserIdAndTargetId(String userId, String targetId){

        List<RelationShip> list = relationShipRepository.getByUserIdAndTargetId(userId, targetId);

        RelationShipBean relationShipBean = new RelationShipBean();

        if(list.size() <= 0){
            throw new NotFoundException("relationship is not exists");
        }else if(list.size() == 1){
            RelationShip relationShip = list.get(0);
            BeanUtils.copyProperties(relationShip, relationShipBean);
        }else{
            throw new ResourceMismatchingException("relationship quantity is not mismatching");
        }

        return relationShipBean;
    }

    /**
     * 根据userId和targetType 查询 RelationShip 集合
     * @param userId
     * @param targetType
     * @return
     */
    public List<RelationShipBean> getRelationShipByUserIdAndTargetType(String userId, String targetType){

        List<RelationShip> list = relationShipRepository.getByUserIdAndTargetType(userId, targetType);
        List<RelationShipBean> result = list.stream().map(x -> {
            RelationShipBean relationShipBean = new RelationShipBean();
            BeanUtils.copyProperties(x, relationShipBean);
            return relationShipBean;
        }).collect(Collectors.toList());

        return result;
    }

    /**
     * 根据Id获取RelationShip对象
     * @return
     */
    public RelationShipBean getRelationShipById(String id){

        if(!exists(id)){
            throw new NotFoundException("relationship is not exists");
        }

        RelationShip relationShip = relationShipRepository.findOne(id);

        RelationShipBean relationShipBean = new RelationShipBean();
        BeanUtils.copyProperties(relationShip, relationShipBean);

        return relationShipBean;
    }

    /**
     * 根据Id 判断RelationShip 是否存在
     * @param id
     * @return
     */
    private boolean exists (String id){

        return relationShipRepository.exists(id);
    }
}
