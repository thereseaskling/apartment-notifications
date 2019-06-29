package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ApplicationEntryPoint implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationEntryPoint.class);

  private final ApartmentAdRetriever apartmentAdRetriever;

  public ApplicationEntryPoint(final ApartmentAdRetriever apartmentAdRetriever) {

    this.apartmentAdRetriever = apartmentAdRetriever;
  }

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    LOG.info("Requesting info");
    apartmentAdRetriever.requestAds();
    LOG.info("Done");
  }
}
