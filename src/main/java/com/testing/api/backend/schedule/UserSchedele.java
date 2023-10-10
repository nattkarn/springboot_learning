package com.testing.api.backend.schedule;


import com.testing.api.backend.business.UserBusiness;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserSchedele {

    private final UserBusiness userBusiness;

    public UserSchedele(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }


    // Schedule Note
    //1 => second
    //2 => minute
    //3 => hour
    //4 => day
    //5 => month
    //6 => year

    /**
     * Every Minute
     */
//    @Scheduled(cron = "0 * * * * *")
//    public void TetsEveryMinute(){
//       log.info("Hello");
//    }

    /**
     * Everyday at 00:00
     */
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Bangkok")
    public void testEveryMidNight(){

    }


}
