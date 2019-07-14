package application;

import application.ApartmentInfo.ApartmentArea;
import application.ApartmentInfo.SubArea;
import application.NotificationRules.RuleHandler;
import application.NotificationRules.RuleInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;


@Configuration
public class ApplicationConfiguration {

  @Bean
  ApplicationEntryPoint entryPoint(final ApartmentAdRetriever apartmentAdRetriever) {
    return new ApplicationEntryPoint(apartmentAdRetriever);
  }

  @Bean
  RequestHeadersSpec requestHeadersSpec() {
    return WebClient.create("https://bostad.stockholm.se").get()
        .uri("/Lista/AllaAnnonser")
        .header(HttpHeaders.CONTENT_TYPE)
        .accept(MediaType.APPLICATION_JSON)
        .acceptCharset(Charset.forName("UTF-8"));
  }

  @Bean
  ApartmentAdRetriever apartmentAdRetriever(final RequestHeadersSpec requestHeadersSpec, final ObjectMapper objectMapper,
      final ApartmentAdHandler apartmentAdHandler) {
    return new ApartmentAdRetriever(requestHeadersSpec, objectMapper, apartmentAdHandler);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  ApartmentAdHandler adHandler(final NotificationHandler notificationHandler, final ObjectMapper objectMapper) {
    return new ApartmentAdHandler(notificationHandler, objectMapper);
  }

  @Bean
  RuleHandler ruleHandler(final ObjectMapper objectMapper) {
    final RuleHandler ruleHandler = new RuleHandler(objectMapper);
    final RuleInfo ruleInfo = new RuleInfo("therese.askling@gmail.com");

    ruleInfo.setAreas(Arrays.asList(ApartmentArea.STOCKHOLM));
    ruleHandler.addRule(ruleInfo);

    final RuleInfo momRule = setParentRuleInfo(new RuleInfo("britt.askling@gmail.com"));
    final RuleInfo dadRule = setParentRuleInfo(new RuleInfo("Janneaskling@gmail.com"));
    ruleHandler.addRule(momRule);
    ruleHandler.addRule(dadRule);

    return ruleHandler;
  }

  private RuleInfo setParentRuleInfo(final RuleInfo rule) {
    rule.setAreas(Arrays.asList(ApartmentArea.STOCKHOLM));
    rule.setSubAreas(Arrays.asList(SubArea.HOGDALEN, SubArea.SODERMALM, SubArea.MIDSOMMARKRANSEN,
        SubArea.FRUANGEN, SubArea.HOKARANGEN, SubArea.LILJEHOLMEN, SubArea.BREDANG, SubArea.GUBBANGEN, SubArea.HAGERSTEN,
        SubArea.STORA_ESSINGEN, SubArea.VASTBERGA, SubArea.VASASTADEN, SubArea.KUNGSHOLMEN, SubArea.TALLKROGEN,
        SubArea.NORRA_DJURGARDEN, SubArea.BAGARMOSSEN, SubArea.STADSHAGEN, SubArea.ENSKEDE_GARD, SubArea.LILLA_ESSINGEN, SubArea.OSTERMALM,
        SubArea.HJORTHAGEN, SubArea.KARRTORP, SubArea.SKARHOLMEN, SubArea.SVEDMYRA, SubArea.SODRA_HAMMARBYHAMNEN, SubArea.VASTERTORP,
        SubArea.ARSTA, SubArea.ASPUDDEN, SubArea.ORNSBERG, SubArea.AXELSBERG, SubArea.ALVSJO, SubArea.HAGERSTENSASEN, SubArea.STUREBY,
        SubArea.OSTBERGA, SubArea.GRONDAL, SubArea.LANGBRO));
    rule.setYouth(false);
    rule.setStudent(false);
    rule.setTemporaryQueue(false);
    rule.setSenior(false);
    rule.setShortTime(false);
    rule.setElevator(true);
    rule.setBalcony(true);
    rule.setRooms(Arrays.asList(2, 3, 4, 5, 6, 7, 8));
    rule.setSizeRange(Pair.of(70, 150));
    return rule;
  }

  @Bean
  NotificationHandler notificationHandler(final RuleHandler ruleHandler, final EmailSender emailSender) {
    return new NotificationHandler(ruleHandler, emailSender);
  }

  @Bean
  EmailSender emailSender() {
    return new EmailSender("lediga.lagenheter.stockhom@gmail.com", "ApartmentNotifications1337!");
  }
}
