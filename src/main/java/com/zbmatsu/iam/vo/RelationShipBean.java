package com.zbmatsu.iam.vo;

import com.zbmatsu.iam.annotations.ThingType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/3/2.
 */
public class RelationShipBean {

    private String id;

    @NotBlank(message = "userId is not empty")
    @Length(max = 36, message = "userId length > 36")
    private String userId;

    @NotBlank(message = "targetId is not empty")
    @Length(max = 36, message = "targetId length > 36")
    private String targetId;

    @NotBlank(message = "targetType is not empty")
    @Length(max = 20, message = "targetType length > 20")
    @ThingType(message = "type is not initialize")
    private String targetType;

    private String description;

    private LocalDateTime creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
