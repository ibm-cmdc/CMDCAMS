package com.ibm.ams.service.token.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import com.ibm.ams.entity.token.TokenModel;
import com.ibm.ams.service.token.TokenManager;
import com.ibm.ams.util.Const;


 @Component
 public class RedisTokenManager implements TokenManager{
	   
	 @Autowired
     private RedisTemplate<String,String> redisTemplate;
	 
	 @Autowired
     public void setRedis(RedisTemplate redis){
         this.redisTemplate = redis;
         //泛型设置成Long后必须更改对应的序列化方案
         //redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
     }

     public TokenModel createToken(String userId){
         //使用uuid作为源token
         String token = UUID.randomUUID().toString().replace("-","");
         TokenModel model = new TokenModel(userId,token); 
         //存储到redis并设置过期时间
         redisTemplate.boundValueOps(userId).set(token, Const.TOKEN_EXPIRES_HOUR,TimeUnit.HOURS);
         return model;
     }

  

     public TokenModel getToken(String authentication){

         if(authentication==null||authentication.length()==0){
             return null;
         }

         String[] param = authentication.split("_");
         if(param.length != 2){
             return null;
         }

         //使用userId和源token简单拼接成的token，可以增加加密措施

         String userId = param[0];
         String token = param[1];
         return new TokenModel(userId,token);

     }

  

     public boolean checkToken(TokenModel model){

         if(model == null){
             return false;
         }

         String token = redisTemplate.boundValueOps(model.getUserId()).get();
         if(token == null||!token.equals(model.getToken())){
             return false;
         }
         
         //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
         redisTemplate.boundValueOps(model.getUserId()).expire(Const.TOKEN_EXPIRES_HOUR,TimeUnit.HOURS);
         return true;
     }

  

     public void deleteToken(String userId){
    	 redisTemplate.delete(userId);
     }

 }