package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Pair;

public class SizeRuleChecker implements RuleChecker {

  private final Map<Integer, StudentRuleChecker> sizeRules;

  public SizeRuleChecker(final RuleInfo rule) {
    sizeRules = new HashMap<>();
    addRule(rule);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    final Pair<Integer, Integer> sizeRange = rule.getSizeRange().orElse(Pair.of(0, 150));
    IntStream.range(sizeRange.getLeft(), sizeRange.getRight()).forEach(size -> addRule(size, rule));
  }

  private void addRule(final Integer size, final RuleInfo rule) {
    if (sizeRules.containsKey(size)) {
      sizeRules.get(size).addRule(rule);
    } else {
      sizeRules.put(size, new StudentRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<StudentRuleChecker> rules = Optional.ofNullable(sizeRules.get(apartmentAd.getYta()));
    rules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
