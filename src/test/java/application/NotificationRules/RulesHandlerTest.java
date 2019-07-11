package application.NotificationRules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.ApartmentInfo.ApartmentAd;
import application.ApartmentInfo.ApartmentArea;
import application.ApartmentInfo.SubArea;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

class RulesHandlerTest {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final String myEmail = "MyEmail";

  @Test
  void shouldGetCorrectRecipientWhenAddingArea() {
    final RuleHandler rulesHandler = new RuleHandler(objectMapper);

    final RuleInfo rule = new RuleInfo(myEmail);
    rule.setAreas(Arrays.asList(ApartmentArea.STOCKHOLM));

    final ApartmentAd apartmentAd = new ApartmentAd();
    apartmentAd.setKommun("Stockholm");
    apartmentAd.setAntalRum(5);
    apartmentAd.setStudent(false);
    apartmentAd.setSenior(false);
    apartmentAd.setHiss(true);
    apartmentAd.setBalkong(false);
    apartmentAd.setUngdom(true);
    apartmentAd.setKorttid(true);
    apartmentAd.setNyproduktion(true);
    apartmentAd.setYta(45);

    rulesHandler.addRule(rule);
    assertTrue(!rulesHandler.getMatchingRecipients(apartmentAd).isEmpty());
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).size(), 1);
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).get(0), myEmail);
  }

  @Test
  void shouldGetCorrectRecipientWhenCheckingForStudent() {
    final RuleHandler rulesHandler = new RuleHandler(objectMapper);

    final RuleInfo rule = new RuleInfo(myEmail);
    rule.setStudent(true);

    final ApartmentAd apartmentAd = new ApartmentAd();
    apartmentAd.setKommun("Stockholm");
    apartmentAd.setAntalRum(5);
    apartmentAd.setStudent(true);
    apartmentAd.setSenior(true);
    apartmentAd.setHiss(false);
    apartmentAd.setBalkong(false);
    apartmentAd.setUngdom(true);
    apartmentAd.setKorttid(true);
    apartmentAd.setNyproduktion(true);
    apartmentAd.setYta(45);

    rulesHandler.addRule(rule);
    assertTrue(!rulesHandler.getMatchingRecipients(apartmentAd).isEmpty());
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).size(), 1);
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).get(0), myEmail);
  }

  @Test
  void shouldGetCorrectRecipientWhenCheckingForSize() {
    final RuleHandler rulesHandler = new RuleHandler(objectMapper);

    final RuleInfo rule = new RuleInfo(myEmail);
    rule.setSizeRange(Pair.of(40, 60));

    final ApartmentAd apartmentAd = new ApartmentAd();
    apartmentAd.setKommun("Stockholm");
    apartmentAd.setAntalRum(5);
    apartmentAd.setStudent(true);
    apartmentAd.setSenior(false);
    apartmentAd.setHiss(false);
    apartmentAd.setBalkong(true);
    apartmentAd.setUngdom(false);
    apartmentAd.setKorttid(true);
    apartmentAd.setNyproduktion(false);
    apartmentAd.setYta(45);

    rulesHandler.addRule(rule);
    assertTrue(!rulesHandler.getMatchingRecipients(apartmentAd).isEmpty());
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).size(), 1);
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).get(0), myEmail);
  }

  @Test
  void shouldGetCorrectRecipientWhenCheckingForSubArea() {
    final RuleHandler rulesHandler = new RuleHandler(objectMapper);

    final RuleInfo rule = new RuleInfo(myEmail);
    rule.setSubAreas(Arrays.asList((SubArea.OSTERMALM)));

    final ApartmentAd apartmentAd = new ApartmentAd();
    apartmentAd.setStadsdel("Östermalm");
    apartmentAd.setAntalRum(5);
    apartmentAd.setStudent(false);
    apartmentAd.setSenior(false);
    apartmentAd.setHiss(true);
    apartmentAd.setBalkong(false);
    apartmentAd.setUngdom(false);
    apartmentAd.setKorttid(true);
    apartmentAd.setNyproduktion(true);
    apartmentAd.setYta(45);

    rulesHandler.addRule(rule);
    assertTrue(!rulesHandler.getMatchingRecipients(apartmentAd).isEmpty());
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).size(), 1);
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).get(0), myEmail);
  }

  @Test
  void shouldGetCorrectRecipientWhenCheckingForNumberOfRooms() {
    final RuleHandler rulesHandler = new RuleHandler(objectMapper);

    final RuleInfo rule = new RuleInfo(myEmail);
    rule.setRooms(Arrays.asList(5));

    final ApartmentAd apartmentAd = new ApartmentAd();
    apartmentAd.setStadsdel("Östermalm");
    apartmentAd.setAntalRum(5);
    apartmentAd.setStudent(true);
    apartmentAd.setSenior(true);
    apartmentAd.setHiss(true);
    apartmentAd.setBalkong(true);
    apartmentAd.setUngdom(true);
    apartmentAd.setKorttid(false);
    apartmentAd.setNyproduktion(true);
    apartmentAd.setYta(45);

    rulesHandler.addRule(rule);
    assertTrue(!rulesHandler.getMatchingRecipients(apartmentAd).isEmpty());
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).size(), 1);
    assertEquals(rulesHandler.getMatchingRecipients(apartmentAd).get(0), myEmail);
  }
}