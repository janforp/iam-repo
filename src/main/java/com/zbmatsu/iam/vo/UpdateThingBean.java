package com.zbmatsu.iam.vo;

import com.zbmatsu.iam.annotations.JsonObject;

/**
 * Created by Administrator on 2017/3/5.
 */
public class UpdateThingBean {

    private String description;

    @JsonObject(message = "extension is not json object")
    private String extension;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
