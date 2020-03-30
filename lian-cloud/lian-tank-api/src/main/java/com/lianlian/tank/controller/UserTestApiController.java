package com.lianlian.tank.controller;

import com.lianlian.common.result.ResultCode;
import com.lianlian.common.result.ResultData;
import com.lianlian.entity.UserEntity;
import com.lianlian.tank.service.IUserTestApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/api/UserTestApi")
@Api(value="tank",description="用户信息查询")
public class UserTestApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserTestApiService userTestApiService;

    /**
     * 查询
     */
    @GetMapping("/findUserById")
    @ApiOperation("查询用户接口")
    public ResultData findUserById(@RequestParam(value = "id") Long id) {
        UserEntity userEntity=userTestApiService.findUserById(1l);
        return ResultData.success(userEntity);

    }

    /**
     * 新增
     */
    @GetMapping("/saveUser")
    @ApiOperation("新增用户接口")
    public  ResultData saveUser(@RequestBody UserEntity userEntity){
         return ResultData.success(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @GetMapping("deleteUserById")
    @ApiOperation("删除用户接口")
    public ResultData deleteUserById(@RequestParam(value = "id") Long id) {
        return ResultData.success(ResultCode.SUCCESS);
    }
    /**
     * 修改
     */
    @GetMapping("updateUser")
    @ApiOperation("修改用户接口")
    public  ResultData updateUser(@RequestBody UserEntity userEntity){
        return ResultData.success(ResultCode.SUCCESS);
    }



}
