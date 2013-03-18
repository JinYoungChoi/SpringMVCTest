package com.nethru.test.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.nethru.test.model.Notice;

public interface NoticeMapper {

    @Select("select * from CTOC_NTC order by NTC_ID desc limit 1;")
    Notice getNewestNotice();

}
