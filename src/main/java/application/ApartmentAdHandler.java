package application;

import application.ApartmentInfo.ApartmentAd;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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
  private final NotificationHandler notificationHandler;
  private final ObjectMapper objectMapper;

  private static final String filename = "apartment_ads";

  public ApartmentAdHandler(final NotificationHandler notificationHandler, final ObjectMapper objectMapper) {
    this.notificationHandler = notificationHandler;
    this.objectMapper = objectMapper;
    apartmentAds = new ArrayList<>();
    apartmentsById = new HashMap<>();

    initializeAdLists();
  }

  private void initializeAdLists() {
    try (final BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      apartmentAds.addAll(parseResponse(reader.readLine()));
      apartmentAds.stream().filter(ad -> LocalDate.now().isBefore(LocalDate.parse(ad.getAnnonseradTill(), DateTimeFormatter.ISO_DATE).plusDays(1L)))
          .forEach(ad -> apartmentsById.put(ad.getAnnonsId(), ad));
    } catch (final Exception e) {
      LOG.info("Failed to read ads from file");
      LOG.info(e.getMessage());
    }
  }

  private List<ApartmentAd> parseResponse(final String response) {
    try {
      return objectMapper.readValue(response, new TypeReference<List<ApartmentAd>>() {
      });
    } catch (final IOException e) {
      LOG.info("Failed to parse saved ads");
      return Collections.EMPTY_LIST;
    }
  }

  void addApartments(final List<ApartmentAd> newAds) {
    newAds.stream()
        .filter(apartmentAd -> !apartmentsById.containsKey(apartmentAd.getAnnonsId()))
        .map(this::sendNotification)
        .forEach(this::addApartment);
  }

  private ApartmentAd sendNotification(final ApartmentAd apartmentAd) {
    notificationHandler.handleNotification(apartmentAd);
    return apartmentAd;
  }

  private void addApartment(final ApartmentAd apartmentAd) {
    LOG.info("Adding new apartment ad: {}", apartmentAd.toString());
    apartmentAds.add(apartmentAd);
    apartmentsById.put(apartmentAd.getAnnonsId(), apartmentAd);
    saveToFile();
  }

  private void saveToFile() {
    try (final BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write(objectMapper.writeValueAsString(apartmentAds));
    } catch (final IOException e) {
      LOG.info("Failed to save apartment ad");
      LOG.info(e.getMessage());
    }
  }

  Optional<ApartmentAd> getApartmentById(final int apartment) {
    return Optional.ofNullable(apartmentsById.getOrDefault(apartment, null));
  }

  Flux<ApartmentAd> getAllApartments() {
    return Flux.fromStream(apartmentAds.stream());
  }
}
