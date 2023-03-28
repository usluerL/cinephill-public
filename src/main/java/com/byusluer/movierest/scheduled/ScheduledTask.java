package com.byusluer.movierest.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTask {


    @Scheduled(cron = "0 * * * * ?")
    public void checkForNewReleases() {
        System.out.println("Working Yo!!");
    }


}
