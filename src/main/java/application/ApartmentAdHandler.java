package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import application.ApartmentInfo.ApartmentAd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import static application.ApartmentInfo.ApartmentArea.STOCKHOLM;

public class ApartmentAdHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ApartmentAdHandler.class);

    private final List<ApartmentAd> apartmentAds;
    private final Map<Integer, ApartmentAd> apartmentsById;
    private final NotificationHandler notificationHandler;

    public ApartmentAdHandler(final NotificationHandler notificationHandler) {
        apartmentAds = new ArrayList<>();
        apartmentsById = new HashMap<>();
        this.notificationHandler = notificationHandler;
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
    }


    Optional<ApartmentAd> getApartmentById(final int apartment) {
        return Optional.ofNullable(apartmentsById.getOrDefault(apartment, null));
    }


    Flux<ApartmentAd> getAllApartments() {
        return Flux.fromStream(apartmentAds.stream());
    }
}
