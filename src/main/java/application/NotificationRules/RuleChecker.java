package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.List;

public interface RuleChecker {

  public void addRule(final RuleInfo ruleInfo);

  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd);
}
