package application;

import application.ApartmentInfo.ApartmentAd;
import application.NotificationRules.RuleHandler;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ApartmentAdHandler.class);

  private final RuleHandler ruleHandler;
  private final EmailSender emailSender;

  public NotificationHandler(final RuleHandler ruleHandler, final EmailSender emailSender) {
    this.ruleHandler = ruleHandler;
    this.emailSender = emailSender;
  }

  void handleNotification(final ApartmentAd apartmentAd) {
    final List<String> notificationRecipients = ruleHandler.getMatchingRecipients(apartmentAd);
    notificationRecipients.forEach(recipient -> notifyRecipient(recipient, apartmentAd));
  }

  private void notifyRecipient(final String recipient, final ApartmentAd apartmentAd) {
    LOG.info("Notifying recipient {} about ad {}", recipient, apartmentAd);
    final String apartmentAdLink = "bostad.stockholm.se" + apartmentAd.getUrl();
    final String messageFormat = "Nu finns det en ny lägenhet tillgänglig på bostadsförmedlingen som matchar dina sökkriterier: \n%s";
    final String apartmentInformationFormat = "Kommun: %s \nStadsdel:%s \nAddress: %s \nAntal rum: %s \nYta: %s \nHyra: %s\nLänk till annons:%s";
    final String apartmentInformation = String.format(apartmentInformationFormat, apartmentAd.getKommun(), apartmentAd.getStadsdel(),
        apartmentAd.getGatuadress(), apartmentAd.getAntalRum(), apartmentAd.getYta(), apartmentAd.getHyra(), apartmentAdLink);
    final String message = String.format(messageFormat, apartmentInformation);
    final String subject = "Ny lägenhet tillgänglig på bostadsförmedlingen";

    emailSender.sendMail(recipient, subject, message);
  }
}
