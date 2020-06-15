package com.qaprocess.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_files")
@Data
public class TfilesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "old_file_name")
    String oldFileName;
    @Column(name = "new_file_name")
    String newFileName;
    String ext;
    String path;
    Long size;
    String type;
    @LastModifiedDate
    @Column(name = "upload_time")
    Timestamp uploadTime;
}
