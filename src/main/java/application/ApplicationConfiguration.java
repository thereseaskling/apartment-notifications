package application;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;


@Configuration
public class ApplicationConfiguration {

  @Bean
  ApplicationEntryPoint entryPoint(final ApartmentAdRetriever apartmentAdRetriever, final ObjectMapper objectMapper,
      final ApartmentAdHandler apartmentAdHandler) {
    return new ApplicationEntryPoint(apartmentAdRetriever, objectMapper, apartmentAdHandler);
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
  ApartmentAdRetriever apartmentAdRetriever(final RequestHeadersSpec requestHeadersSpec) {
    return new ApartmentAdRetriever(requestHeadersSpec);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  ApartmentAdHandler adHandler() {
    return new ApartmentAdHandler();
  }
}
