package application;

import application.ApartmentInfo.ApartmentArea;
import application.NotificationRules.RuleHandler;
import application.NotificationRules.RuleInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.util.Arrays;
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
  ApartmentAdHandler adHandler(final NotificationHandler notificationHandler) {
    return new ApartmentAdHandler(notificationHandler);
  }

  @Bean
  RuleHandler ruleHandler(){
    final RuleHandler ruleHandler = new RuleHandler();
    final RuleInfo ruleInfo = new RuleInfo("therese.askling@gmail.com");

    ruleInfo.setAreas(Arrays.asList(ApartmentArea.STOCKHOLM));
    ruleHandler.addRule(ruleInfo);
    return ruleHandler;
  }

  @Bean
  NotificationHandler notificationHandler(final RuleHandler ruleHandler) {
    return new NotificationHandler(ruleHandler);
  }
}
