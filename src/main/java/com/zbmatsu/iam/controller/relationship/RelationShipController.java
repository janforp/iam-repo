package com.zbmatsu.iam.controller.relationship;

import com.zbmatsu.iam.service.RelationShipService;
import com.zbmatsu.iam.vo.RelationShipBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/3
 */
@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class RelationShipController {

    @Autowired
    protected RelationShipService relationShipService;

    @ApiOperation(value="创建relationship", notes="根据relationship创建对象")
    @PostMapping("/relationship")
    @ResponseStatus(HttpStatus.CREATED)
    public RelationShipBean createRelationShip(@Validated @RequestBody RelationShipBean relationShipBean) {

        return relationShipService.createRelationShip(relationShipBean);
    }

    @ApiOperation(value = "根据userId和targetId删除relationship",notes = "根据userId和targetId删除relationship")
    @DeleteMapping("/relationship/user/{userId}/target/{targetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRelationShipByUserIdAndTargetId(@PathVariable String userId, @PathVariable String targetId){

        relationShipService.deleteRelationShip(userId, targetId);
    }

    @ApiOperation(value = "根据id删除relationship",notes = "根据id删除relationship")
    @DeleteMapping("/relationship/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRelationShipById(@PathVariable String id){

        relationShipService.deleteRelationShip(id);
    }

    @ApiOperation(value="根据ID获取Thing信息", notes="根据ID获取Thing信息")
    @GetMapping("/relationship/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RelationShipBean getRelationShipById(@PathVariable String id){

        return relationShipService.getRelationShipById(id);
    }

    @ApiOperation(value = "获取relationship",notes = "获取relationship")
    @GetMapping("/relationship/unique/.search")
    @ResponseStatus(HttpStatus.OK)
    public RelationShipBean getRelationShipByUserIdAndTargetId(@RequestParam("userId") String userId, @RequestParam("targetId") String targetId){

        return relationShipService.getRelationShipByUserIdAndTargetId(userId, targetId);
    }

    @ApiOperation(value = "获取relationship列表",notes = "获取relationship列表")
    @GetMapping("/relationship/list/.search")
    @ResponseStatus(HttpStatus.OK)
    public List<RelationShipBean> getRelationShipListByUserIdAndTargetType(@RequestParam("userId") String userId, @RequestParam("targetType") String targetType){

        return relationShipService.getRelationShipByUserIdAndTargetType(userId, targetType);
    }
}
