package cn.kn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("cn.kn.dao.mapper")
@Configuration
@ServletComponentScan
@SpringBootApplication
public class KnApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnApplication.class, args);
    }

}
