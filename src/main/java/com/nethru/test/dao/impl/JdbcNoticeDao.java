package com.nethru.test.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nethru.test.dao.NoticeDao;
import com.nethru.test.model.Notice;

@Repository
@Profile("dev")
public class JdbcNoticeDao implements NoticeDao {
    
    private static final Logger logger = LoggerFactory.getLogger(JdbcNoticeDao.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Notice getNewestNotice() {
        
        logger.debug("getNewestNotice() start");
        
        RowMapper<Notice> mapper = new RowMapper<Notice>() {
            @Override
            public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
                Notice notice = new Notice();
                notice.setId(rs.getInt("NTC_ID"));
                notice.setTitle(rs.getString("NTC_TTL"));
                notice.setContent(rs.getString("NTC_CNTNT"));
                return notice;
            }
        };
        
        Notice notice = this.jdbcTemplate.queryForObject("select * from CTOC_NTC order by NTC_ID desc limit 1;", mapper);
        
        logger.debug("getNewestNotice() end");
        return notice;
    }

}
