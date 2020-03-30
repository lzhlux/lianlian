package com.lianlian.tank.service;


import com.lianlian.common.result.ResultData;
import com.lianlian.entity.UserEntity;

public interface IUserTestApiService  {


     ResultData saveUser(UserEntity userEntity);

     ResultData updateUser(UserEntity userEntity);

     UserEntity findUserById(Long id);

     ResultData deleteUserById(Long id);
}
