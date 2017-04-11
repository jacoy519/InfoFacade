package com.info.dao;

import org.apache.ibatis.annotations.Param;

public interface NoticeDao {

    public int insertNotice(@Param("notice") com.info.pojo.NoticeDo notice);

}