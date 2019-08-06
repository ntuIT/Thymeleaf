package jp.ivs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.ivs.mapper")
public class ThyApplication
{

    public static void main(String[] args) {
        SpringApplication.run(ThyApplication.class, args);
    }

}
