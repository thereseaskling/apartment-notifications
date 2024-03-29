package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentRuleChecker implements RuleChecker {

  private final Map<Boolean, RetirementRuleChecker> studentRules;

  public StudentRuleChecker(final RuleInfo rule) {
    studentRules = new HashMap<>();
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
    if (studentRules.containsKey(isStudent)) {
      studentRules.get(isStudent).addRule(rule);
    } else {
      studentRules.put(isStudent, new RetirementRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<RetirementRuleChecker> rules = Optional.ofNullable(studentRules.get(apartmentAd.getStudent()));
    rules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
