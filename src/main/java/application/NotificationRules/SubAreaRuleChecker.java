package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import application.ApartmentInfo.SubArea;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SubAreaRuleChecker implements RuleChecker {

  private final Map<SubArea, RoomRuleChecker> subAreas;

  public SubAreaRuleChecker(final Map<SubArea, RoomRuleChecker> subAreas) {
    this.subAreas = subAreas;
  }

  SubAreaRuleChecker(final RuleInfo ruleInfo) {
    subAreas = new HashMap<>();
    addRule(ruleInfo);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.getSubAreas().isEmpty()) {
      addRule(SubArea.EMPTY, rule);
    } else {
      rule.getSubAreas().forEach(area -> addRule(area, rule));
    }
  }

  private void addRule(final SubArea subArea, final RuleInfo rule) {
    if (subAreas.containsKey(subArea)) {
      subAreas.get(subArea).addRule(rule);
    } else {
      subAreas.put(subArea, new RoomRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<RoomRuleChecker> roomRuleChecker = Optional
        .ofNullable(subAreas.get(SubArea.getApartmentArea(apartmentAd.getStadsdel()).orElse(SubArea.EMPTY)));
    roomRuleChecker.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }


}
