package com.jim.sprjfx.mapper;

import com.jim.sprjfx.entity.DataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataMapper {
    public int insertData(DataEntity data);
}
