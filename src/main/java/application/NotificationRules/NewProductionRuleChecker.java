package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NewProductionRuleChecker implements RuleChecker {

  private final Map<Boolean, BalconyRuleChecker> newProductionRules;

  public NewProductionRuleChecker(final RuleInfo rule) {
    newProductionRules = new HashMap<>();
    addRule(rule);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.getNewProduction().isPresent()) {
      addRule(rule.getNewProduction().get(), rule);
    } else {
      addRule(true, rule);
      addRule(false, rule);
    }
  }

  private void addRule(final boolean isNewProduction, final RuleInfo rule) {
    if (newProductionRules.containsKey(isNewProduction)) {
      newProductionRules.get(isNewProduction).addRule(rule);
    } else {
      newProductionRules.put(isNewProduction, new BalconyRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<BalconyRuleChecker> rules = Optional.ofNullable(newProductionRules.get(apartmentAd.getNyproduktion()));
    rules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
