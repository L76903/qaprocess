package com.qaprocess.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qaprocess.entity.TfilesEntity;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.sql.Timestamp;

@Data
public class FileInfoVo {
    Long id;
    Long size;
    String type;
    Long uploadTime;
    String fileName;
/*    public Timestamp getUploadTime() {
        return new Timestamp(uploadTime);
    }*/


    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime.getTime();;
    }

    public Long getUploadTime(){
        return this.uploadTime;
    }

}
