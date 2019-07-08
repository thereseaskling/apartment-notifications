package application;

import application.ApartmentInfo.ApartmentAd;

import java.util.List;

public class NotificationHandler {

    void handleNotification(final ApartmentAd apartmentAd){
        final List<String> notificationRecipients = getRecipients(apartmentAd);
    }

    private List<String> getRecipients(ApartmentAd apartmentAd) {
        return null;
    }

    void createRules(){

    }

}
