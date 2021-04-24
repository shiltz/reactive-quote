package za.co.shilton.reactivequote.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import za.co.shilton.reactivequote.dto.CreateQuoteRequestDto;

@WebFluxTest(controllers = QuoteController.class)
class QuoteControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void getQuote() {
    webTestClient.get()
        .uri(QuoteController.QUOTE_SERVICE + "/quote/" + "/123")
        .exchange()
        .expectStatus().is2xxSuccessful()
    .expectBody(String.class)
    .isEqualTo("Hello world!123");
  }

  @Test
  void createQuote() {
    webTestClient.post()
        .uri(QuoteController.QUOTE_SERVICE + "/quote")
        .bodyValue(new CreateQuoteRequestDto())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("$.referenceNumber")
        .isEqualTo(null)
        .jsonPath("$.amount.currency")
        .isEqualTo("R");
  }

}