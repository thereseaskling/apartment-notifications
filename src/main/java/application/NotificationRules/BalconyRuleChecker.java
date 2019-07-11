package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalconyRuleChecker implements RuleChecker {

  private final Map<Boolean, List<String>> matchingRecipients;

  public BalconyRuleChecker(final RuleInfo ruleInfo) {
    matchingRecipients = new HashMap<>();
    addRule(ruleInfo);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.isBalcony().isPresent()) {
      addRule(rule.isBalcony().get(), rule);
    } else {
      addRule(true, rule);
      addRule(false, rule);
    }
  }

  private void addRule(final boolean isBalcony, final RuleInfo rule) {
    if (matchingRecipients.containsKey(isBalcony)) {
      matchingRecipients.get(isBalcony).add(rule.getEmail());
    } else {
      matchingRecipients.put(isBalcony, Arrays.asList(rule.getEmail()));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    return matchingRecipients.getOrDefault(apartmentAd.getStudent(), new ArrayList<>());
  }
}
