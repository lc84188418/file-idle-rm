package com.cvmaster.fileidlerm.utils;

import java.util.Set;

/**
 * @ClassName:ListUtil
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/12/012 22:40
 * @Version:1.0
 **/
public class ListUtil {

    public static boolean containsChildChar(Set<String> list, CharSequence cs){
        for (String s : list) {
            if(s.contains(cs)){
                return true;
            }
        }
        return false;

    }
}
