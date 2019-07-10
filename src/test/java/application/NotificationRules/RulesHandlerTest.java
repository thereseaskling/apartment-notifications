package application.NotificationRules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.ApartmentInfo.ApartmentAd;
import application.ApartmentInfo.ApartmentArea;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class RulesHandlerTest {

  private final String myEmail = "MyEmail";

  @Test
  void shouldGetCorrectRecipientWhenAddingArea() {
    final RuleHandler rulesHandler = new RuleHandler();

    final RuleInfo rule = new RuleInfo(myEmail);
    rule.setAreas(Arrays.asList(ApartmentArea.STOCKHOLM));

    final ApartmentAd apartmentAd = new ApartmentAd();
    apartmentAd.setKommun("Stockholm");
    apartmentAd.setAntalRum(5);
    apartmentAd.setStudent(true);
    apartmentAd.setYta(45);

    rulesHandler.addRule(rule);
    assertTrue(!rulesHandler.getMatchingRecipients(apartmentAd).isEmpty());
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).get(0), myEmail);
  }
}