package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class ApartmentAdHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ApartmentAdHandler.class);

  private final List<ApartmentAd> apartmentAds;
  private final Map<Integer, ApartmentAd> apartmentsById;

  public ApartmentAdHandler() {
    apartmentAds = new ArrayList<>();
    apartmentsById = new HashMap<>();
  }

  void addApartments(final List<ApartmentAd> newAds) {
    newAds.stream()
        .filter(apartmentAd -> !apartmentsById.containsKey(apartmentAd.getAnnonsId()))
        .forEach(this::addApartment);
  }

  private void addApartment(final ApartmentAd apartmentAd) {
    LOG.info("Adding new apartment ad: {}", apartmentAd);
    apartmentAds.add(apartmentAd);
    apartmentsById.put(apartmentAd.getAnnonsId(), apartmentAd);
  }


  Optional<ApartmentAd> getApartmentById(final int apartment) {
    return Optional.ofNullable(apartmentsById.getOrDefault(apartment, null));
  }


  Flux<ApartmentAd> getAllApartments() {
    return Flux.fromStream(apartmentAds.stream());
  }
}
