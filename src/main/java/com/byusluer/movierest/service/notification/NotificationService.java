package com.byusluer.movierest.service.notification;

import com.byusluer.movierest.entity.UserProfile;

public interface NotificationService {
    void sendNotification(UserProfile userProfile, String message);

}
