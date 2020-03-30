package com.lianlian.tank.service;


import com.lianlian.common.result.ResultData;
import com.lianlian.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserTestApiService  {


     ResultData saveUser( UserEntity userEntity);

     ResultData updateUser(UserEntity userEntity);

     UserEntity findUserById(Long id);

     ResultData deleteUserById(Long id);
}
