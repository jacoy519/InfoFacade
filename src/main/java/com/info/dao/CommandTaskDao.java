package com.info.dao;

import org.apache.ibatis.annotations.Param;

public interface CommandTaskDao {

    public java.util.List<com.info.pojo.CommandTaskDo> selectNeedRetryTaskList();

    public java.util.List<com.info.pojo.CommandTaskDo> selectSuccessTaskList();

    public java.util.List<com.info.pojo.CommandTaskDo> selectFailTaskList();

    public java.util.List<com.info.pojo.CommandTaskDo> selectAttemptTaskList();

    public int deleteCommandTaskById(@Param("commandTask") com.info.pojo.CommandTaskDo commandTask);

    public int updateCommandTaskStatusToFailure(@Param("commandTask") com.info.pojo.CommandTaskDo commandTask);

    public int updateCommandTaskStatusToSuccess(@Param("commandTask") com.info.pojo.CommandTaskDo commandTask);

    public int updateCommandTaskStatusToAttempt(@Param("commandTask") com.info.pojo.CommandTaskDo commandTask);

    public int insertNewCommandTask(@Param("commandTask") com.info.pojo.CommandTaskDo commandTask);

}