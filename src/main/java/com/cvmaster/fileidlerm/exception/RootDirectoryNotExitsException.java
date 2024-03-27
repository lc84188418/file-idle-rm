package com.cvmaster.fileidlerm.exception;

/**
 * @ClassName:RootDirectoryNotExitsException
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/11/011 23:00
 * @Version:1.0
 **/
public class RootDirectoryNotExitsException extends Exception{
    public RootDirectoryNotExitsException() {
        super("The root directory does not exist！");
    }

}
