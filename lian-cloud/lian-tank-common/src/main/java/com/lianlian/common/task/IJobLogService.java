package com.lianlian.common.task;

import com.lianlian.common.task.entitys.Joblog;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IJobLogService {
    @RequestMapping(method = RequestMethod.POST, value = "jobLog/saveJobLog", consumes = "application/json")
    void saveJobLog(@RequestBody Joblog jobLog);
}
