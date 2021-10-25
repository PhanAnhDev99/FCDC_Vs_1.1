package com.fpt.myweb.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GetUtils {

    public static List<Long> convertStringToListLong(String arr){
        List<Long> result = new ArrayList<>();
        if(StringUtils.isEmpty(arr)){
            return result;
        }
        try {
            String[] strings = arr.split(",");
            for (String s:strings){
                result.add(Long.parseLong(s));
            }
            return result;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
}
