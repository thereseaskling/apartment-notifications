package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleHandler implements RuleChecker {

  private static final Logger LOG = LoggerFactory.getLogger(RuleHandler.class);
  private static final String filename = "C:\\Users\\there\\Repos\\apartment-notifications\\saved_information\\notification_rules";

  private final RuleChecker ruleChecker;
  private final ObjectMapper objectMapper;

  private final List<RuleInfo> rules;

  public RuleHandler(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.ruleChecker = new ApartmentAreaRuleChecker();
    rules = new ArrayList<>();
    initialiseRules();
  }

  private void initialiseRules() {
    try (final BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      rules.addAll(parse(reader.readLine()));
    } catch (final Exception e) {
      LOG.info("Failed to read ads from file");
      LOG.info(e.getMessage());
    }
  }

  private List<RuleInfo> parse(final String response) {
    try {
      return objectMapper.readValue(response, new TypeReference<List<RuleInfo>>() {
      });
    } catch (final IOException e) {
      LOG.info("Failed to parse saved rules");
      return Collections.EMPTY_LIST;
    }
  }

  @Override
  public void addRule(final RuleInfo ruleInfo) {
    ruleChecker.addRule(ruleInfo);
    rules.add(ruleInfo);
    saveRules();
  }

  private void saveRules() {
    try (final BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write(objectMapper.writeValueAsString(rules));
    } catch (final IOException e) {
      LOG.info("Failed to save apartment ad");
      LOG.info(e.getMessage());
    }
  }

  @Override
  public List<String> getMatchingRecipients(final ApartmentAd apartmentAd) {
    return ruleChecker.getMatchingRecipients(apartmentAd).stream().distinct().collect(Collectors.toList());
  }
}
