package com.fpt.myweb.service.impl;


import com.fpt.myweb.common.Contants;
import com.fpt.myweb.dto.request.NewRequet;
import com.fpt.myweb.entity.New;
import com.fpt.myweb.exception.AppException;
import com.fpt.myweb.exception.ErrorCode;
import com.fpt.myweb.repository.NewRepository;
import com.fpt.myweb.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class NewServiceImpl implements NewService {

    @Autowired
    private NewRepository newRepository;

    @Override
    public New addNew(NewRequet newRequet) {
        New aNew = new New();
        aNew.setTitle(newRequet.getTitle());
        aNew.setDecription(newRequet.getDecription());
        aNew.setImageUrl(newRequet.getImageUrl());
        aNew.setCreatedDate(new Date());
        aNew.setActive(true);
        newRepository.save(aNew);
        return aNew;
    }

    @Override
    public New editNew(NewRequet newRequet) {
        New aNew = newRepository.getById(newRequet.getId());
        if(aNew != null){
            aNew.setTitle(newRequet.getTitle());
            aNew.setDecription(newRequet.getDecription());
            aNew.setImageUrl(newRequet.getImageUrl());
            aNew.setModifiedDate(new Date());
            aNew.setActive(true);
            newRepository.save(aNew);
        }else{
            throw new AppException(ErrorCode.NOT_FOUND_ID.getKey(), ErrorCode.NOT_FOUND_ID.getValue() + newRequet.getId());
        }
        return aNew;
    }

    @Override
    public void deleteNew(Integer id) {
        New aNew = newRepository.getById((long) id);
        if(aNew != null){
            aNew.setActive(false);
            aNew.setModifiedDate(new Date());
            newRepository.save(aNew);
        }else{
            throw new AppException(ErrorCode.NOT_FOUND_ID.getKey(), ErrorCode.NOT_FOUND_ID.getValue() + id);
        }
    }

    @Override
    public List<New> getNew(Integer page) {
        if(page == null){
            page = 1;
        }
        Integer offset = Contants.PAGE_SIZE * (page - 1);
        return newRepository.getNew(Contants.PAGE_SIZE, offset);
    }
}
