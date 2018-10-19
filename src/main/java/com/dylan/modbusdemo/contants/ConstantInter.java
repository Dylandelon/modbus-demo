package com.dylan.modbusdemo.contants;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface ConstantInter {
    Lock lock = new ReentrantLock();
    Condition condition=lock.newCondition();
}
