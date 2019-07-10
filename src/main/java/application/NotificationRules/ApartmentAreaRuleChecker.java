package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import application.ApartmentInfo.ApartmentArea;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class ApartmentAreaRuleChecker implements RuleChecker {

  private final Map<ApartmentArea, SubAreaRuleChecker> areas;

  ApartmentAreaRuleChecker() {
    areas = new HashMap<>();
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.getAreas().isEmpty()) {
      addRule(ApartmentArea.EMPTY, rule);
    } else {
      rule.getAreas().forEach(area -> addRule(area, rule));
    }
  }

  private void addRule(final ApartmentArea area, final RuleInfo rule) {
    if (areas.containsKey(area)) {
      areas.get(area).addRule(rule);
    } else {
      areas.put(area, new SubAreaRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<ApartmentArea> apartmentArea = ApartmentArea.getApartmentArea(apartmentAd.getKommun());
    apartmentArea.ifPresent(area -> Optional.ofNullable(areas.get(area))
        .ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd))));
    Optional.ofNullable(areas.get(ApartmentArea.EMPTY))
        .ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
