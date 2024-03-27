package com.cvmaster.fileidlerm.executer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Traverse4 {
    public static void main(String[] args) {
        //调用遍历方法
        Run(" E:\\test\\file_idle_rm");
    }
    //递归遍历文件
    public static void Run(String AllFile) {
        File tfile = new File(AllFile);
        //构建文件数组
        String[] files = tfile.list();
        for(int i = 0; i < files.length; i++) {
            File file = new File(AllFile + files[i]);
            //判定该文件是否为目录
            if(file.isDirectory()) {
                //是，递归调用函数
                Run(file.getAbsolutePath());
            }else {
                //不是，是文件
                ReadFile(file.getAbsolutePath());
            }
        }
    }
    //统计单词个数，排序，输出
    public static void ReadFile(String fname) {
        System.out.println("开始读取文件，文件名："+fname);
        File file=new File(fname);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            String s = null;
            //创建哈希表
            Map<String,Integer> hm = new HashMap<>();
            while((s=bufr.readLine())!=null){
                //利用正则表达式分割出单词
                String[]strs = s.split("[^a-zA-Z0-9]");
                //向哈希表存入单词，统计个数
                for(int i = 0; i < strs.length; i++){
                    strs[i].toLowerCase();
                    if(!hm.containsKey(strs[i])){
                        hm.put(strs[i], 1);
                    }else{
                        Integer counts = hm.get(strs[i]);
                        hm.put(strs[i], counts+1);
                    }
                }
            }
            //调用排序方法
            sort(hm);
            bufr.close();
            fr.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void sort(Map<String,Integer>map) {
        //重写List降序排序
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        //输出
        for (int i = 0; i < infoIds.size(); i++) {
            Entry<String, Integer> id = infoIds.get(i);
            System.out.println(id.getKey()+":"+id.getValue());
        }
    }
}