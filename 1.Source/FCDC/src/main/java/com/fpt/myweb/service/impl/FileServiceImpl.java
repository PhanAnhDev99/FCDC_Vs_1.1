package com.fpt.myweb.service.impl;


import com.fpt.myweb.common.Contants;
import com.fpt.myweb.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


@Service
public class FileServiceImpl implements FileService {

    @Value("${folder.user.images}")
    private String userImage;

    @Value("${folder.user.news}")
    private String userNew;

    @Override
    public String saveFile(MultipartFile file, String type) throws IOException {
        String result = "";
        File fileUpload = null;
        if(type.equalsIgnoreCase(Contants.TYPE_USER)){
            fileUpload = new File(userImage);
            if(!fileUpload.exists()){
                fileUpload.mkdir();
            }
            result = userImage;
        } else if(type.equalsIgnoreCase(Contants.TYPE_NEW)){
            fileUpload = new File(userNew);
            if(!fileUpload.exists()){
                fileUpload.mkdir();
            }
            result = userNew;
        }
        if(fileUpload != null){
            InputStream inputStream = null;
            File fileUrl = null;
            inputStream = file.getInputStream();
            String name = file.getName();
            String namebase64 = new String(Base64.getUrlEncoder().encode(name.getBytes())) +
                    System.currentTimeMillis() + name.split("\\.(?=[^\\.]+$)");
            result += namebase64;
            fileUrl = new File(result);
            OutputStream outStream = new FileOutputStream(fileUrl);
            FileCopyUtils.copy(inputStream, outStream);
        }
        return result;
    }
}
