package application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

public class ApartmentAdRetriever {

  private static final Logger LOG = LoggerFactory.getLogger(ApartmentAdRetriever.class);

  private final RequestHeadersSpec requestHeadersSpec;
  private final ObjectMapper objectMapper;
  private final ApartmentAdHandler apartmentAdHandler;
  private final Timer timer;

  public ApartmentAdRetriever(final RequestHeadersSpec requestHeadersSpec, final ObjectMapper objectMapper,
      ApartmentAdHandler apartmentAdHandler) {
    this.requestHeadersSpec = requestHeadersSpec;
    this.objectMapper = objectMapper;
    this.apartmentAdHandler = apartmentAdHandler;
    timer = new Timer();
  }

  void requestAds() {
    final TimerTask request = new TimerTask() {
      @Override
      public void run() {
        LOG.info("Requesting apartment ads");
        final String requestResponse = requestHeadersSpec.retrieve().bodyToMono(String.class).block();
        apartmentAdHandler.addApartments(parseResponse(requestResponse));
      }
    };

    timer.scheduleAtFixedRate(request, Duration.ofSeconds(5).toMillis(), Duration.ofMinutes(5).toMillis());
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

  @PreDestroy
  void destroy(){
    timer.cancel();
  }
}
