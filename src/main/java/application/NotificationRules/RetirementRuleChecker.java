package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RetirementRuleChecker implements RuleChecker {

  private final Map<Boolean, ElevatorRuleChecker> retirementRules;

  public RetirementRuleChecker(final RuleInfo ruleInfo) {
    retirementRules = new HashMap<>();
    addRule(ruleInfo);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.isSenior().isPresent()) {
      addRule(rule.isSenior().get(), rule);
    } else {
      addRule(true, rule);
      addRule(false, rule);
    }
  }

  private void addRule(final boolean isSenior, final RuleInfo rule) {
    if (retirementRules.containsKey(isSenior)) {
      retirementRules.get(isSenior).addRule(rule);
    } else {
      retirementRules.put(isSenior, new ElevatorRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<ElevatorRuleChecker> rules = Optional.ofNullable(retirementRules.get(apartmentAd.getSenior()));
    rules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
