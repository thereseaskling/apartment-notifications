package application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ApplicationEntryPoint implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationEntryPoint.class);

  private final ApartmentAdRetriever apartmentAdRetriever;
  private final ObjectMapper objectMapper;
  private final ApartmentAdHandler apartmentAdHandler;

  public ApplicationEntryPoint(final ApartmentAdRetriever apartmentAdRetriever, final ObjectMapper objectMapper,
      ApartmentAdHandler apartmentAdHandler) {

    this.apartmentAdRetriever = apartmentAdRetriever;
    this.objectMapper = objectMapper;
    this.apartmentAdHandler = apartmentAdHandler;
  }

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    LOG.info("Requesting info");

    apartmentAdRetriever.requestAds()
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(this::parseResponse)
        .doOnEach(apartmentAds -> apartmentAdHandler.addApartments(apartmentAds.get()))
        .blockLast();
  }

  private List<ApartmentAd> parseResponse(final String response) {
    try {
      return objectMapper.readValue(response, new TypeReference<List<ApartmentAd>>() {
      });
    } catch (final IOException e) {
      LOG.info("Failed to parse response");
      return Collections.EMPTY_LIST;
    }

  }
}
