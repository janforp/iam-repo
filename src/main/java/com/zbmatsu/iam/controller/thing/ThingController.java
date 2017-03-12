package com.zbmatsu.iam.controller.thing;

import com.zbmatsu.iam.service.ThingService;
import com.zbmatsu.iam.vo.ThingBean;
import com.zbmatsu.iam.vo.UpdateThingBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */
@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class ThingController {

    @Autowired
    protected ThingService thingService;

    @ApiOperation(value="创建thing", notes="根据Thing创建对象")
    @PostMapping("/thing")
    @ResponseStatus(HttpStatus.CREATED)
    public ThingBean createThing(@Validated @RequestBody ThingBean thingBean) {

        return thingService.createThing(thingBean);
    }

    @ApiOperation(value = "删除Thing",notes = "删除Thing")
    @DeleteMapping("/thing/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeThing(@PathVariable String id){

        thingService.deleteThing(id);
    }

    @ApiOperation(value = "修改Thing名字",notes = "修改Thing名字")
    @PutMapping("/thing/{id}/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ThingBean updateThingName(@PathVariable String id, @PathVariable String name){

        ThingBean thingBean = thingService.getThingById(id);
        thingBean.setName(name);
        return thingService.updateThing(thingBean);
    }

    @ApiOperation(value = "修改Thing状态",notes = "修改Thing状态")
    @PutMapping("/thing/{id}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ThingBean updateThingStatus(@PathVariable String id, @PathVariable String status){

        ThingBean thingBean = thingService.getThingById(id);
        thingBean.setStatus(status);
        return thingService.updateThing(thingBean);
    }

    @ApiOperation(value = "修改Thing信息",notes = "修改Thing信息")
    @PutMapping("/thing/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThingBean updateThing(@PathVariable String id, @Validated @RequestBody UpdateThingBean updateThingBean){

        return thingService.updateThing(id, updateThingBean);
    }

    @ApiOperation(value="根据ID获取Thing信息", notes="根据ID获取Thing信息")
    @GetMapping("/thing/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThingBean getThingById(@PathVariable String id){

        return thingService.getThingById(id);
    }

    @ApiOperation(value="根据IDs获取Thing列表", notes="根据IDs获取Thing列表")
    @PostMapping("/thing/batch/.search")
    @ResponseStatus(HttpStatus.OK)
    public List<ThingBean> getThingListByIds(@RequestBody List<String> ids){

        List<Serializable> list = new ArrayList<>();
        ids.stream().forEach(id -> list.add(id));

        return thingService.getThingListByIds(list);
    }

    @ApiOperation(value="根据IDs批量删除", notes="根据IDs获取Thing列表")
    @DeleteMapping("/things")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchDeleteThings(@RequestBody List<String> ids){

        List<Serializable> list = new ArrayList<>();
        ids.stream().forEach(id -> list.add(id));

        thingService.batchDeleteThings(list);
    }
}
