package application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ApartmentControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void shouldReturnCorrectResponse() {
    webTestClient.get().uri("http://localhost:8080/apartments/23456")
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .exchange()
        .expectStatus().isOk();
  }

}