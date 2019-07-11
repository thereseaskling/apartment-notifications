package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

class RoomRuleChecker implements RuleChecker {

  private final Map<Integer, RuleChecker> roomRules;

  RoomRuleChecker(final RuleInfo ruleInfo) {
    roomRules = new HashMap<>();
    addRule(ruleInfo);
  }

  @Override
  public void addRule(final RuleInfo rule) {
    if (rule.getRooms().isEmpty()) {
      IntStream.range(1, 20).forEach(rooms -> addRule(rooms, rule));
    } else {
      rule.getRooms().forEach(rooms -> addRule(rooms, rule));
    }
  }

  private void addRule(final Integer rooms, final RuleInfo rule) {
    if (roomRules.containsKey(rooms)) {
      roomRules.get(rooms).addRule(rule);
    } else {
      roomRules.put(rooms, new SizeRuleChecker(rule));
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    final List<String> matchingRecipients = new ArrayList<>();
    final Optional<RuleChecker> sizeRules = Optional.ofNullable(roomRules.get(apartmentAd.getAntalRum()));
    sizeRules.ifPresent(rule -> matchingRecipients.addAll(rule.getMatchingRecipients(apartmentAd)));
    return matchingRecipients;
  }
}
