package com.laioffer.onlineorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
//The last step of using caching is adding annotation above
public class OnlineOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineOrderApplication.class, args);
    }

}
