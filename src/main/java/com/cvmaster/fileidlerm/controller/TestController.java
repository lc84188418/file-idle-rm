package com.cvmaster.fileidlerm.controller;

import com.cvmaster.fileidlerm.dao.UserDao;
import com.cvmaster.fileidlerm.entity.R;
import com.cvmaster.fileidlerm.entity.User;
import com.cvmaster.fileidlerm.utils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.Random;

/**
 * @ClassName:TestController
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/12/012 23:00
 * @Version:1.0
 **/
@RestController
public class TestController {

    @Resource
    private UserDao userDao;

    @GetMapping("/testBatch")
    public void testBatch() throws IOException {
        //随机获取布尔值
        boolean existStatus = new Random().nextBoolean();
        //拷贝文件
        File resourceFile = new File("E:\\test\\file_idle_rm\\iu.jpeg");
        String fileName = DateUtil.currentTime() + ".jpeg";
        String pathName = "\\img\\2024\\03\\05\\" + fileName;
        String copyPath = "E:\\test\\file_idle_rm\\" + pathName;
        FileUtils.copyFile(resourceFile,new File(copyPath),true, StandardCopyOption.REPLACE_EXISTING);
        User user = new User();
        user.setName("mafei");
        user.setAge(11);
        String dbFilePath = "https:\\\\scms.jindingaus.com\\file_idle_rm\\img\\2023\\06\\13\\" + fileName;
        if(existStatus){
            //存在的
            user.setImg(pathName);
        }else {
            //不存在的
            user.setImg("\\img\\2024\\03\\05\\notExist.jpeg");
        }
        userDao.insert(user);
    }
    @GetMapping("/testBatch2")
    public void testBatch2() throws IOException {
        //随机获取布尔值
        boolean existStatus = new Random().nextBoolean();
        //拷贝文件
        File resourceFile = new File("E:\\test\\file_idle_rm\\iu.jpeg");
        String fileName = DateUtil.currentTime() + ".jpeg";
        String pathName = "\\img\\2024\\03\\05\\" + fileName;
        String copyPath = "E:\\test\\file_idle_rm\\" + pathName;
        FileUtils.copyFile(resourceFile,new File(copyPath),true, StandardCopyOption.REPLACE_EXISTING);
        User user = new User();
        user.setName("mafei");
        user.setAge(11);
        String absolutePath = "http://192.168.31.80:8080/myStatic" + pathName;
        if(existStatus){
            //存在的
            user.setImg(absolutePath);
        }else {
            //不存在的
            user.setImg("http://192.168.31.80:8080/myStatic/notExist.jpeg");
        }
        userDao.insert(user);
    }

    @GetMapping("test/jenkins")
    public R testJenkins(){
        return new R<>().ok("jenkins2",null);
    }
}
