package com.lianlian.tank.service.impl;

import com.lianlian.common.result.ResultCode;
import com.lianlian.common.result.ResultData;
import com.lianlian.entity.UserEntity;
import com.lianlian.tank.dao.UserMapper;
import com.lianlian.tank.service.IUserTestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTestApiServiceImpl implements IUserTestApiService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultData saveUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
        return ResultData.success(ResultCode.SUCCESS);

    }

    @Override
    public ResultData updateUser(UserEntity userEntity) {
        userMapper.updateByPrimaryKey(userEntity);
        return ResultData.success(ResultCode.SUCCESS);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultData deleteUserById(Long id) {
        userMapper.deleteByPrimaryKey(id);
        return  ResultData.success(ResultCode.SUCCESS);
    }
}
