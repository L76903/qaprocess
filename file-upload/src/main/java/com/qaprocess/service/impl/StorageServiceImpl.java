package com.qaprocess.service.impl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import com.qaprocess.dao.TfilesDao;
import com.qaprocess.entity.TfilesEntity;
import com.qaprocess.exception.MyException;
import com.qaprocess.service.IStorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("ALL")
@Slf4j
@Service("StorageService")
public class StorageServiceImpl implements IStorageService {
    @Autowired
    TfilesDao tfilesDao;
    @Value("${file.upload-dir}")
    String UPLOADED_FOLDER="./";


    @Override
    public TfilesEntity store(MultipartFile file) throws IOException, MyException {
        log.info("开始保存文件");
        if (!StringUtils.isNotBlank(file.getOriginalFilename())){
            //空文件
            log.warn("接收到空文件");
            throw new MyException(MyException.ResponseCodeEnum.IO_ERROR,"接收到空文件");
        }
            //获取原始文件名
            String oldFileName = file.getOriginalFilename();
            //获取扩展名
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (extension!=null&&!extension.isEmpty()){
                extension="."+extension;

            }
            //生成新的文件名称
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-","").substring(6) + extension;
            //文件大小
            long size = file.getSize();
            //文件类型
            String type = file.getContentType();
            //String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
            //处理根据日期生成目录
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dateDirPath = UPLOADED_FOLDER + "/" + format;
            File dataDir = new File(dateDirPath);

            if (!dataDir.exists()){
                dataDir.mkdirs();
            }


            //处理文件上传

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(dateDirPath +"/" + newFileName);
            log.info("处理文件上传:"+path.toString());
            Files.write(path, bytes);
            TfilesEntity tfilesEntity=new TfilesEntity();
            tfilesEntity.setOldFileName(oldFileName);
            tfilesEntity.setNewFileName(newFileName);
            tfilesEntity.setExt(extension);
            tfilesEntity.setPath(format);
            tfilesEntity.setSize(size);
            tfilesEntity.setType(type);
            tfilesEntity.setUploadTime(new Timestamp(new java.util.Date().getTime()));
            //保存全部
            log.info("保存文件");
            tfilesDao.save(tfilesEntity);
            log.info("完成");
            return tfilesEntity;

    }

    @Override
    public void delete(Long id) throws MyException {
        Optional<TfilesEntity> tfilesEntityOptional = tfilesDao.findById(id);
        if (!tfilesEntityOptional.isPresent()){
            throw new MyException(MyException.ResponseCodeEnum.IO_ERROR);
        }
        TfilesEntity tfilesEntity =tfilesEntityOptional.get();
        //删除文件
        //String realpath = ResourceUtils.getURL("classpath:").getPath() + "/static" + tfilesEntity.getPath();
        File file = new File(UPLOADED_FOLDER+"/"+tfilesEntity.getPath(), tfilesEntity.getNewFileName());
        if (!file.exists()){
            throw new MyException(MyException.ResponseCodeEnum.IO_ERROR);
        }
        //立即删除
        if (file.delete()){
            throw new MyException(MyException.ResponseCodeEnum.IO_ERROR);
        }
        tfilesDao.deleteById(id);//删除数据库中的内容
    }

    @Override
    public FileInputStream download(Long id) throws FileNotFoundException, MyException {
        //获取文件信息
        Optional<TfilesEntity> tfilesEntityOptional=tfilesDao.findById(id);
        if (!tfilesEntityOptional.isPresent()){
            //没有在数据库中查到该文件
            throw new MyException(MyException.ResponseCodeEnum.DATA_MISSION,"该文件不存在");
        }
        TfilesEntity tfilesEntity =tfilesEntityOptional.get();
        File file = new File(UPLOADED_FOLDER+"/"+tfilesEntity.getPath(), tfilesEntity.getNewFileName());
        //根据文件信息中文件的名字和文件存储的路径获取文件输入流
        String realpath = UPLOADED_FOLDER+"/"+tfilesEntity.getPath();
        //获取文件输入流
        FileInputStream is = new FileInputStream(file);
        return is;
    }

    @Override
    public TfilesEntity fileInfo(Long id) throws MyException {
        Optional<TfilesEntity> tfilesEntityOptional = tfilesDao.findById(id);
        if (!tfilesEntityOptional.isPresent()){
            throw new MyException(MyException.ResponseCodeEnum.DATA_MISSION,"该文件不存在");
        }
        return tfilesEntityOptional.get();
    }

}
