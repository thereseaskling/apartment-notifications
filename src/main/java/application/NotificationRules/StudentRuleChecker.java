package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRuleChecker implements RuleChecker {

  private final Map<Boolean, List<String>> matchingRecipients;

  public StudentRuleChecker(final RuleInfo rule) {
    matchingRecipients = new HashMap<>();
    addRule(rule);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.isStudent().isPresent()) {
      addRule(rule.isStudent().get(), rule);
    }
    else {
      addRule(true, rule);
      addRule(false, rule);
    }
  }

  private void addRule(final boolean isStudent, final RuleInfo rule) {
    if (matchingRecipients.containsKey(isStudent)) {
      matchingRecipients.get(isStudent).add(rule.getEmail());
    } else {
      matchingRecipients.put(isStudent, Arrays.asList(rule.getEmail()));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    return matchingRecipients.getOrDefault(apartmentAd.getStudent(), new ArrayList<>());
  }
}
