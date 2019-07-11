package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class YouthApartmentRuleChecker implements RuleChecker {

  private final Map<Boolean, RuleChecker> youthApartmentRules;

  public YouthApartmentRuleChecker(final RuleInfo rule) {
    youthApartmentRules = new HashMap<>();
    addRule(rule);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.getYouth().isPresent()) {
      addRule(rule.getYouth().get(), rule);
    } else {
      addRule(true, rule);
      addRule(false, rule);
    }
  }

  private void addRule(final boolean isYouthApartment, final RuleInfo rule) {
    if (youthApartmentRules.containsKey(isYouthApartment)) {
      youthApartmentRules.get(isYouthApartment).addRule(rule);
    } else {
      youthApartmentRules.put(isYouthApartment, new ShortTimeRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<RuleChecker> rules = Optional.ofNullable(youthApartmentRules.get(apartmentAd.getUngdom()));
    rules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
