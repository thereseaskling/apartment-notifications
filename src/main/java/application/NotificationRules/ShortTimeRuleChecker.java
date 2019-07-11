package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShortTimeRuleChecker implements RuleChecker {

  private final Map<Boolean, RuleChecker> shortTimeRules;

  public ShortTimeRuleChecker(final RuleInfo ruleInfo) {
    shortTimeRules = new HashMap<>();
    addRule(ruleInfo);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.getShortTime().isPresent()) {
      addRule(rule.getShortTime().get(), rule);
    } else {
      addRule(true, rule);
      addRule(false, rule);
    }
  }

  private void addRule(final boolean isShortTime, final RuleInfo rule) {
    if (shortTimeRules.containsKey(isShortTime)) {
      shortTimeRules.get(isShortTime).addRule(rule);
    } else {
      shortTimeRules.put(isShortTime, new NewProductionRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<RuleChecker> rules = Optional.ofNullable(shortTimeRules.get(apartmentAd.getKorttid()));
    rules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}