package com.info.dao;

import org.apache.ibatis.annotations.Param;

public interface NoticeTaskDao {

    public int insertNewNoticeTask(@Param("noticeTask") com.info.pojo.NoticeTaskDo noticeTask);

}