package com.jim.sprjfx.service;

import com.jim.sprjfx.entity.DataEntity;
import com.jim.sprjfx.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataServiceImpl implements DataService{

    @Autowired
    private DataMapper dataMapper;
    @Override
    public boolean insertData(DataEntity data) {
        return dataMapper.insertData(data) > 0 ? true : false ;
    }
}
