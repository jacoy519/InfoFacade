package com.info.dao;

import org.apache.ibatis.annotations.Param;

public interface NoticeTaskDao {

    public java.util.List<com.info.pojo.NoticeTaskDo> selectNeedRetryTaskList();

    public java.util.List<com.info.pojo.NoticeTaskDo> selectSuccessTaskList();

    public java.util.List<com.info.pojo.NoticeTaskDo> selectFailTaskList();

    public java.util.List<com.info.pojo.NoticeTaskDo> selectAttemptTaskList();

    public int deleteNoticeTaskById(@Param("noticeTask") com.info.pojo.NoticeTaskDo noticeTask);

    public int updateNoticeTaskStatusToFailure(@Param("noticeTask") com.info.pojo.NoticeTaskDo noticeTask);

    public int updateNoticeTaskStatusToSuccess(@Param("noticeTask") com.info.pojo.NoticeTaskDo noticeTask);

    public int updateNoticeTaskStatusToAttempt(@Param("noticeTask") com.info.pojo.NoticeTaskDo noticeTask);

    public int insertNewNoticeTask(@Param("noticeTask") com.info.pojo.NoticeTaskDo noticeTask);

}