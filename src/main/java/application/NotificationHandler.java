package application;

import application.ApartmentInfo.ApartmentAd;
import application.NotificationRules.RuleHandler;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ApartmentAdHandler.class);

  private final RuleHandler ruleHandler;

  public NotificationHandler(final RuleHandler ruleHandler) {
    this.ruleHandler = ruleHandler;
  }

  void handleNotification(final ApartmentAd apartmentAd) {
    final List<String> notificationRecipients = ruleHandler.getMatchingRecipients(apartmentAd);
    notificationRecipients.forEach(recipient -> notifyRecipient(recipient, apartmentAd));
  }

  private void notifyRecipient(final String recipient, final ApartmentAd apartmentAd) {
    LOG.info("Notifying recipient {} about ad {}", recipient, apartmentAd);
  }
}
