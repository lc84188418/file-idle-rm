package com.cvmaster.fileidlerm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import xyz.erupt.core.annotation.EruptScan;

@SpringBootApplication(scanBasePackages = {"xyz.erupt","com.cvmaster.fileidlerm"})
@EnableScheduling
@MapperScan({"com.cvmaster.fileidlerm.dao"})
@EntityScan({"xyz.erupt","com.cvmaster.fileidlerm"})
@EruptScan({"xyz.erupt","com.cvmaster.fileidlerm"})
public class FileIdleRmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileIdleRmApplication.class, args);
    }

}
