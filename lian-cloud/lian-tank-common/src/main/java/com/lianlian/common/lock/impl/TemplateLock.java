package com.lianlian.common.lock.impl;

import com.lianlian.common.lock.DistLock;
import com.lianlian.common.lock.LockKeyPattern;
import com.lianlian.common.utils.RedisUtil;

import java.util.UUID;

public class TemplateLock implements DistLock {
    private String lockKey;
    private String value = UUID.randomUUID().toString().replace("-", "");
    private int expireTime = 60*60*2;

    public TemplateLock(String table, String value){
        this.lockKey = String.format(LockKeyPattern.CACHE_DATA.getPattern(), table, value);
    }
    @Override
    public boolean lock() {
        return RedisUtil.getLock(lockKey,value,expireTime);
       // return false;
    }

    @Override
    public boolean tryLock(long time) {

        long start = System.currentTimeMillis();
        while (true) {
            //检测是否超时
            if (System.currentTimeMillis() - start > time) {
                return false;
            }
            boolean le = RedisUtil.getLock(lockKey, value, expireTime);
            if(le)
                return true;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean unlock() {
       return RedisUtil.releaseLock(lockKey,value);
    }
}
