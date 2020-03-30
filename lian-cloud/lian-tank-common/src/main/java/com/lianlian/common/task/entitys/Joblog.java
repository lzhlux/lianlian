package com.lianlian.common.task.entitys;

import lombok.Data;

import java.util.Date;

@Data
public class Joblog {

    /** 任务名称 */
    private String jobName;

    /** 任务组名 */
    private String jobGroup;

    /** 任务方法 */
    private String targetName;

    /** 方法参数 */
    private String methodParams;

    /** 日志信息 */
    private String jobMessage;

    /** 执行状态（0正常 1失败） */
    private String status;

    /** 异常信息 */
    private String exceptionInfo;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;
}
