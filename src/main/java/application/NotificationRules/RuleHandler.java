package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.List;
import java.util.stream.Collectors;

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
    return ruleChecker.getMatchingRecipients(apartmentAd).stream().distinct().collect(Collectors.toList());
  }
}
