package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.List;

public class RuleHandler implements RuleChecker {

  private final RuleChecker ruleChecker;

  public RuleHandler() {
    this.ruleChecker = new ApartmentAreaRuleChecker();
  }

  @Override
  public void addRule(final RuleInfo ruleInfo) {
    ruleChecker.addRule(ruleInfo);
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    return ruleChecker.getMatchingRecipients(apartmentAd);
  }
}
