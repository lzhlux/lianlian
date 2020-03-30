package com.lianlian.common.task.entitys;

import lombok.Data;

@Data
public class JobInfo {

    private String jobId;

    private String jobName;

    private String jobGroup;

    private String jobTime;

    private String jobTarget;

    private String jobRepeat;

    private Integer jobTimeout;

    private String param;
}
