package com.fpt.myweb.service.impl;


import com.fpt.myweb.common.Contants;
import com.fpt.myweb.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment env;

    @Override
    public String saveFile(MultipartFile file, String type) throws IOException {
        String result = "";
        File fileUpload = null;
        if(type.equalsIgnoreCase(Contants.TYPE_USER)){
            result = env.getProperty("folder.user.images");

        } else if(type.equalsIgnoreCase(Contants.TYPE_NEW)){
            result = env.getProperty("folder.user.images");
        }
        fileUpload = new File(result);
        if(!fileUpload.exists()){
            fileUpload.mkdir();
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
