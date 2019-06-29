package application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

  private static final Logger LOG = LoggerFactory.getLogger(ApartmentController.class);

  private final ApartmentAdHandler apartmentAdHandler;
  private final ObjectMapper objectMapper;

  public ApartmentController(final ApartmentAdHandler apartmentAdHandler, ObjectMapper objectMapper) {
    this.apartmentAdHandler = apartmentAdHandler;
    this.objectMapper = objectMapper;
  }

  @GetMapping("/{id}")
  private Mono<ResponseEntity<String>> getApartmentById(@PathVariable int id) {
    final Optional<ApartmentAd> apartmentAd = apartmentAdHandler.getApartmentById(id);
    if (apartmentAd.isPresent()) {
      try {
        return Mono.just(ResponseEntity.ok().body(objectMapper.writeValueAsString(apartmentAd.get())));
      } catch (final JsonProcessingException e) {
        LOG.info("Failed to parse apertment ad to JSon. ad: {}", apartmentAd);
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve apartment info"));
      }
    }
    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to retrieve apartment info"));
  }

  @GetMapping
  private Flux<ApartmentAd> getAllEmployees() {
    return apartmentAdHandler.getAllApartments();
  }
}
