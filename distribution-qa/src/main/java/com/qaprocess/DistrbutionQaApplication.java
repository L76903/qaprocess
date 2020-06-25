package com.qaprocess;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DistrbutionQaApplication {
//    private static int i;
    public static void main(String[] args) {
//        try {
            SpringApplication.run(DistrbutionQaApplication.class,args);
/*        }catch (Exception e){
            if (i<10){
                log.error(e.getMessage());
                i++;
                main(args);
            }
        }*/
    }
}
