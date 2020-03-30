package cn.com.tichain.gateway.controller;

import cn.com.tichain.gateway.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
//  @Autowired
//  String setUrl;



  @RequestMapping(value="/", method= RequestMethod.GET)
  String check() throws Exception{
//    accountService.login();
    return "consul check local";
  }

  @RequestMapping(value="/hello", method= RequestMethod.POST)
  String hello(@RequestBody Object body) throws Exception{

    return "hello world post";
  }

  @RequestMapping(value="/hello", method= RequestMethod.GET)
  String hello2() throws Exception{
    return "hello world get2";
  }

  @RequestMapping(value="/file", method= RequestMethod.GET)
  String helloFile() {
    System.out.println("file of hello");

    return "file of hello";
  }

  @RequestMapping(value="/role", method= RequestMethod.GET)
  String role() {
    System.out.println("role role");

    return "role role2";
  }

  @RequestMapping(value="/err", method= RequestMethod.GET)
  String error() throws Exception{

    throw new Exception("1I am error ");
  }


}
