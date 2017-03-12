package com.zbmatsu.iam.vo;

import com.zbmatsu.iam.annotations.JsonObject;
import com.zbmatsu.iam.annotations.ThingType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/3/2.
 */
public class ThingBean {

    @NotBlank(message = "id is not empty")
    @Length(max = 36, message = "id length > 36")
    private String id;

    @NotBlank(message = "name is not empty")
    private String name;

    @NotBlank(message = "type is not empty")
    @Length(max = 20, message = "type length > 20")
    @ThingType(message = "type is not initialize")
    private String type;

    @NotBlank(message = "status is not empty")
    @Length(max = 20, message = "status length > 20")
    private String status;

    private String description;

    private LocalDateTime creationTime;

    private LocalDateTime modificationTime;

    @NotBlank(message = "createUserId is not empty")
    @Length(max = 36, message = "id length > 36")
    private String createUserId;

    private String lastUpdateUserId;

    @JsonObject(message = "extension is not json object")
    private String extension;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
