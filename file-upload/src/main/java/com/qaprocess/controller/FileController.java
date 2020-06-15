package com.qaprocess.controller;

import com.qaprocess.entity.TfilesEntity;
import com.qaprocess.exception.MyException;
import com.qaprocess.service.IStorageService;
import com.qaprocess.vo.FileInfoVo;
import com.qaprocess.vo.ResponseResult;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class FileController {
    @Autowired
    IStorageService storageService;
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public ResponseResult singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, MyException {
        TfilesEntity tfilesEntity=storageService.store(file);

       return fileInfo(tfilesEntity.getId());

        //return new ResponseResult(FileInfoVo.valueOf(storageService.store(file)));
    }


    @GetMapping("/file_info")
    public ResponseResult fileInfo(@Param("id") Long id) throws MyException {
        TfilesEntity tfilesEntity=storageService.fileInfo(id);
        FileInfoVo fileInfoVo=new DefaultMapperFactory.Builder().build().getMapperFacade().map(tfilesEntity, FileInfoVo.class);
        fileInfoVo.setFileName(tfilesEntity.getOldFileName());
        fileInfoVo.setUploadTime(tfilesEntity.getUploadTime());
        return new ResponseResult(fileInfoVo);
    }

    /**
     *
     * @param filename {filename:.+}中的:.+是指解析.号后面的
     * @param id
     * @param openStyle
     * @param response
     * @throws IOException
     */
    @GetMapping("/down/{filename:.+}")
    public void download(@PathVariable String filename, @Param("id") Long id, @Param("style")String openStyle, HttpServletResponse response) throws IOException, MyException {
        //判断用户是在线打开还是下载
        openStyle = openStyle == null ? "attachment" : openStyle;
        //获取文件输入流
        FileInputStream is = storageService.download(id);
        //附件下载
        response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(filename,"UTF-8"));
        //获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);

    }

}