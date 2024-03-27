package com.cvmaster.fileidlerm.utils;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName:DateUtil
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/12/012 23:16
 * @Version:1.0
 **/
public class DateUtil {
    public static String currentTime(){
        synchronized (DateUtil.class){
            long l = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return String.valueOf(l);
        }
    }
}
