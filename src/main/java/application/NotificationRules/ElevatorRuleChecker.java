package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ElevatorRuleChecker implements RuleChecker {

  private final Map<Boolean, RuleChecker> elevatorRules;

  public ElevatorRuleChecker(final RuleInfo rule) {
    elevatorRules = new HashMap<>();
    addRule(rule);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.isElevator().isPresent()) {
      addRule(rule.isElevator().get(), rule);
    } else {
      addRule(true, rule);
      addRule(false, rule);
    }
  }

  private void addRule(final boolean isElevator, final RuleInfo rule) {
    if (elevatorRules.containsKey(isElevator)) {
      elevatorRules.get(isElevator).addRule(rule);
    } else {
      elevatorRules.put(isElevator, new YouthApartmentRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<RuleChecker> rules = Optional.ofNullable(elevatorRules.get(apartmentAd.getHiss()));
    rules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
