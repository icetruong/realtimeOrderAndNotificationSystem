package com.ice.realtimecache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RealTimeCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeCacheApplication.class, args);
    }

}
