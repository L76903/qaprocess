package com.qaprocess;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.PortInUseException;

@Log4j2
@SpringBootApplication
public class AuthServerApplication {
//    private static int i;
    public static void main(String[] args) {
//        try {
            SpringApplication.run(AuthServerApplication.class,args);
/*        }catch (PortInUseException e){
            log.error(e.getMessage());
            if (i<10){
                i++;
                main(args);
            }
        }*/
    }
}
