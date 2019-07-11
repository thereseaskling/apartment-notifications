package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import java.util.List;

public interface RuleChecker {

  /*Ordering of the rules goes:
   * 1. Apartment area
   * 2. Apartment subarea
   * 3. Number of rooms
   * 4. Apartment size
   * 5. Student apartment
   * 6. Senior apartment
   * 7. Elevator
   * 8. youth apartment
   * 8. temp queue
   * 8. Short time
   * 8. New Production
   * 8. Balcony
   */

  public void addRule(final RuleInfo ruleInfo);

  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd);
}



