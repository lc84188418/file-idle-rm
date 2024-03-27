package com.cvmaster.fileidlerm.controller;

import com.cvmaster.fileidlerm.dao.UserDao;
import com.cvmaster.fileidlerm.entity.User;
import com.cvmaster.fileidlerm.utils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

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
        //拷贝文件
        File resourceFile = new File("E:\\test\\file_idle_rm\\img\\2023\\06\\13\\iu.jpeg");
        String fileName = DateUtil.currentTime() + ".jpeg";
        String copyPath = "E:\\test\\file_idle_rm\\img\\2023\\06\\13\\" + fileName;
        FileUtils.copyFile(resourceFile,new File(copyPath),true, StandardCopyOption.REPLACE_EXISTING);
        User user = new User();
        user.setName("mafei");
        user.setAge(11);
        String dbFilePath = "https:\\\\scms.jindingaus.com\\file_idle_rm\\img\\2023\\06\\13\\" + fileName;
        user.setImg(dbFilePath);
        user.setImg2(dbFilePath);
        userDao.insert(user);
    }

}
