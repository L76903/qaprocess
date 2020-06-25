package com.qaprocess.service;

import com.qaprocess.entity.TfilesEntity;
import com.qaprocess.resultful.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStorageService {
    TfilesEntity store(MultipartFile file) throws IOException, MyException;

    void delete(Long id) throws FileNotFoundException, MyException;

    FileInputStream download(Long id) throws FileNotFoundException, MyException;
    TfilesEntity fileInfo(Long id) throws MyException;



}
