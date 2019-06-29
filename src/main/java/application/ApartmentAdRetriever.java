package application;

import java.time.Duration;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ApartmentAdRetriever {

  private static final Logger LOG = LoggerFactory.getLogger(ApartmentAdRetriever.class);

  private final RequestHeadersSpec requestHeadersSpec;

  public ApartmentAdRetriever(final RequestHeadersSpec requestHeadersSpec) {
    this.requestHeadersSpec = requestHeadersSpec;
  }

  Flux<Optional<String>> requestAds() {
    final Flux<Long> timer = Flux.interval(Duration.ofMinutes(1));
    final Flux<Optional<String>> response = Flux.generate(sink -> {
      LOG.info("Requesting ads");
      sink.next(Optional.ofNullable(requestHeadersSpec.retrieve().bodyToMono(String.class).block()));
    });

    return Flux.zip(response, timer, (key, value) -> key).delayUntil(n -> Mono.just(1).delayElement(Duration.ofSeconds(5)));
  }
}
