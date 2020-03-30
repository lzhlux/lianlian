package com.lianlian.common.lock;

public interface DistLock {

    public boolean lock();

    public boolean tryLock(long time);

    public boolean unlock();

}
